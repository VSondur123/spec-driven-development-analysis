package calculator.ui;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import calculator.core.Operation;
import java.awt.Component;
import java.awt.Container;
import javax.swing.AbstractButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Window behaviour on the panel itself (no top-level window, so it runs headless):
 * contracts/ui-contract.md.
 */
class CalculatorPanelTest {

    private CalculatorPanel panel;

    @BeforeEach
    void createPanel() {
        panel = new CalculatorPanel();
    }

    // ---- helpers ----

    private <T extends Component> T find(String name, Class<T> type) {
        T found = find(panel, name, type);
        assertNotNull(found, "component not found: " + name);
        return found;
    }

    private static <T extends Component> T find(Container parent, String name, Class<T> type) {
        for (Component child : parent.getComponents()) {
            if (name.equals(child.getName()) && type.isInstance(child)) {
                return type.cast(child);
            }
            if (child instanceof Container container) {
                T nested = find(container, name, type);
                if (nested != null) {
                    return nested;
                }
            }
        }
        return null;
    }

    private JTextField first() {
        return find("firstOperandField", JTextField.class);
    }

    private JTextField second() {
        return find("secondOperandField", JTextField.class);
    }

    private AbstractButton button(String name) {
        return find(name, AbstractButton.class);
    }

    private String result() {
        return find("resultLabel", JLabel.class).getText();
    }

    private String buttonName(Operation operation) {
        return switch (operation) {
            case ADD -> "addButton";
            case SUBTRACT -> "subtractButton";
            case MULTIPLY -> "multiplyButton";
            case DIVIDE -> "divideButton";
            case SQUARE -> "squareButton";
            case SQUARE_ROOT -> "squareRootButton";
            case RECIPROCAL -> "reciprocalButton";
        };
    }

    private void enter(String a, String b, Operation operation) {
        first().setText(a);
        second().setText(b);
        button(buttonName(operation)).doClick();
    }

    private void calculate() {
        button("calculateButton").doClick();
    }

    private void assertCalculation(String a, String b, Operation operation, String expected) {
        enter(a, b, operation);
        calculate();
        assertEquals(expected, result(), a + " " + operation + " " + b);
    }

    // ---- initial state ----

    @Test
    void startsInTheInitialState() {
        assertEquals("", first().getText());
        assertEquals("", second().getText());
        for (Operation operation : Operation.values()) {
            assertFalse(button(buttonName(operation)).isSelected(), operation.toString());
        }
        assertEquals("", result());
    }

    @Test
    void operationButtonsShowTheirSymbols() {
        assertEquals("+", button("addButton").getText());
        assertEquals("−", button("subtractButton").getText());
        assertEquals("×", button("multiplyButton").getText());
        assertEquals("÷", button("divideButton").getText());
    }

    // ---- BASE-AC-02, BASE-AC-03 ----

    @Test
    void integerAndDecimalTextIsKept() {
        first().setText("7");
        second().setText("1.5");
        assertEquals("7", first().getText());
        assertEquals("1.5", second().getText());
    }

    // ---- operation selection ----

    @Test
    void selectingAnOperationLeavesExactlyThatButtonSelected() {
        for (Operation chosen : Operation.values()) {
            button(buttonName(chosen)).doClick();
            for (Operation other : Operation.values()) {
                assertEquals(other == chosen, button(buttonName(other)).isSelected(),
                        "after choosing " + chosen + ", button " + other);
            }
        }
    }

    // ---- BASE-AC-04 to BASE-AC-09, BASE-AC-18 ----

    @Test
    void addition() {
        assertCalculation("7", "5", Operation.ADD, "12"); // BASE-AC-04
    }

    @Test
    void subtraction() {
        assertCalculation("7", "5", Operation.SUBTRACT, "2"); // BASE-AC-05
    }

    @Test
    void multiplication() {
        assertCalculation("7", "5", Operation.MULTIPLY, "35"); // BASE-AC-06
    }

    @Test
    void division() {
        assertCalculation("7", "2", Operation.DIVIDE, "3.5"); // BASE-AC-07
    }

    @Test
    void negativeOperand() {
        assertCalculation("-7", "2", Operation.ADD, "-5"); // BASE-AC-08
    }

    @Test
    void decimalOperands() {
        assertCalculation("1.2", "2.3", Operation.ADD, "3.5"); // BASE-AC-09
    }

    @Test
    void zeroDividedByFiveAndOneThird() {
        assertCalculation("0", "5", Operation.DIVIDE, "0"); // BASE-AC-18
        assertCalculation("1", "3", Operation.DIVIDE, "0.3333333333"); // BASE-AC-18
    }

    // ---- BASE-AC-19 ----

    @Test
    void enterInTheFirstFieldCalculatesLikeTheButton() {
        first().setText("7");
        second().setText("5");
        button("addButton").doClick();
        first().postActionEvent();
        assertEquals("12", result());
    }

    @Test
    void enterInTheSecondFieldCalculatesLikeTheButton() {
        first().setText("7");
        second().setText("5");
        button("multiplyButton").doClick();
        second().postActionEvent();
        assertEquals("35", result());
    }

    // ---- BASE-AC-16 ----

    @Test
    void severalCalculationsInARowEachUseTheCurrentFields() {
        assertCalculation("1", "2", Operation.ADD, "3");
        assertCalculation("10", "4", Operation.SUBTRACT, "6");
        assertCalculation("3", "3", Operation.MULTIPLY, "9");
        assertCalculation("9", "4", Operation.DIVIDE, "2.25");
        assertCalculation("0.1", "0.2", Operation.ADD, "0.3");
        assertTrue(button("calculateButton").isEnabled());
    }

    // ---- User story 2: inline errors ----

    private String error() {
        return find("errorLabel", JLabel.class).getText();
    }

    private void assertErrorShown(String message) {
        calculate();
        assertEquals(message, error());
        assertEquals("", result(), "no result may be shown with an error");
    }

    @Test
    void emptyOperandShowsEnterBothNumbers() { // BASE-AC-11
        enter("", "5", Operation.ADD);
        assertErrorShown("Enter both numbers");
        enter("5", "", Operation.ADD);
        assertErrorShown("Enter both numbers");
        enter("", "", Operation.ADD);
        assertErrorShown("Enter both numbers");
    }

    @Test
    void spacesOnlyOperandShowsEnterBothNumbers() { // LOG-12 (agreed)
        enter("   ", "5", Operation.ADD);
        assertErrorShown("Enter both numbers");
    }

    @Test
    void malformedOperandsShowInvalidNumber() { // BASE-AC-12
        for (String bad : new String[] {"abc", "1.2.3", "1,5", "1e3", "--1", "."}) {
            enter(bad, "5", Operation.ADD);
            assertErrorShown("Invalid number");
        }
    }

    @Test
    void incompleteDecimalFormsShowInvalidNumber() { // LOG-12 (agreed)
        for (String bad : new String[] {".5", "5.", "-"}) {
            enter(bad, "5", Operation.ADD);
            assertErrorShown("Invalid number");
        }
    }

    @Test
    void divisionByZeroShowsCannotDivideByZero() { // BASE-AC-13
        enter("7", "0", Operation.DIVIDE);
        assertErrorShown("Cannot divide by zero");
        enter("7", "0.0", Operation.DIVIDE);
        assertErrorShown("Cannot divide by zero");
    }

    @Test
    void noOperationShowsSelectAnOperation() { // BASE-AC-17
        first().setText("7");
        second().setText("5");
        assertErrorShown("Select an operation");
    }

    @Test
    void theFourMessagesAreDistinguishable() {
        java.util.Set<String> messages = new java.util.HashSet<>();
        enter("", "5", Operation.ADD);
        calculate();
        messages.add(error());
        first().setText("abc");
        calculate();
        messages.add(error());
        first().setText("7");
        second().setText("0");
        button("divideButton").doClick();
        calculate();
        messages.add(error());
        // no operation: use a fresh panel, because an operation button stays selected here
        CalculatorPanel fresh = new CalculatorPanel();
        find(fresh, "firstOperandField", JTextField.class).setText("7");
        find(fresh, "secondOperandField", JTextField.class).setText("5");
        find(fresh, "calculateButton", AbstractButton.class).doClick();
        messages.add(find(fresh, "errorLabel", JLabel.class).getText());
        assertEquals(4, messages.size(), messages.toString());
    }

    @Test
    void aNewCalculationClearsThePreviousError() {
        enter("abc", "5", Operation.ADD);
        calculate();
        assertEquals("Invalid number", error());
        first().setText("7");
        calculate();
        assertEquals("", error());
        assertEquals("12", result());
    }

    @Test
    void aNewErrorClearsThePreviousResult() {
        enter("7", "5", Operation.ADD);
        calculate();
        assertEquals("12", result());
        second().setText("abc");
        calculate();
        assertEquals("Invalid number", error());
        assertEquals("", result());
    }

    // ---- User story 3: recovery and Clear ----

    @Test
    void recoversAfterAnInputError() { // BASE-AC-14
        enter("1,5", "2", Operation.ADD);
        calculate();
        assertEquals("Invalid number", error());
        first().setText("1.5");
        calculate();
        assertEquals("3.5", result());
        assertEquals("", error());
    }

    @Test
    void clearAfterAResultReturnsToTheInitialState() { // BASE-AC-10
        assertCalculation("7", "5", Operation.ADD, "12");
        button("clearButton").doClick();
        assertInitialState();
    }

    @Test
    void clearAfterAnErrorReturnsToTheInitialState() { // BASE-AC-10
        enter("abc", "5", Operation.DIVIDE);
        calculate();
        assertEquals("Invalid number", error());
        button("clearButton").doClick();
        assertInitialState();
    }

    @Test
    void calculatorIsUsableAfterClear() {
        enter("abc", "5", Operation.DIVIDE);
        calculate();
        button("clearButton").doClick();
        assertCalculation("8", "2", Operation.DIVIDE, "4");
    }

    @Test
    void editingAFieldKeepsAShownResult() { // LOG-13 (agreed)
        assertCalculation("7", "5", Operation.ADD, "12");
        first().setText("100");
        assertEquals("12", result());
    }

    @Test
    void changingTheOperationKeepsAShownResult() { // LOG-13 (agreed)
        assertCalculation("7", "5", Operation.ADD, "12");
        button("multiplyButton").doClick();
        assertEquals("12", result());
    }

    @Test
    void editingAFieldKeepsAShownError() { // LOG-13 (agreed)
        enter("abc", "5", Operation.ADD);
        calculate();
        first().setText("7");
        assertEquals("Invalid number", error());
    }

    @Test
    void changingTheOperationKeepsAShownError() { // LOG-13 (agreed)
        enter("abc", "5", Operation.ADD);
        calculate();
        button("subtractButton").doClick();
        assertEquals("Invalid number", error());
    }

    private void assertInitialState() {
        assertEquals("", first().getText());
        assertEquals("", second().getText());
        for (Operation operation : Operation.values()) {
            assertFalse(button(buttonName(operation)).isSelected(), operation.toString());
        }
        assertEquals("", result());
        assertEquals("", error());
    }

    @Test
    void enterInAFieldShowsTheSameErrorAsTheButton() {
        first().setText("abc");
        second().setText("5");
        button("addButton").doClick();
        first().postActionEvent();
        assertEquals("Invalid number", error());
        assertEquals("", result());
    }

    // ---- CR-01 user story 1: the unary operations through the GUI ----

    @Test
    void theThreeUnaryButtonsExistWithTheirLabels() { // CR-AC-01 (automated part), CR-FR-01
        assertEquals("x²", button("squareButton").getText());
        assertEquals("√x", button("squareRootButton").getText());
        assertEquals("1/x", button("reciprocalButton").getText());
    }

    @Test
    void sevenOperationButtonsFormOneSelectionGroup() { // CR-FR-01
        Operation[] all = Operation.values();
        assertEquals(7, all.length);
        for (Operation chosen : all) {
            button(buttonName(chosen)).doClick();
            for (Operation other : all) {
                assertEquals(other == chosen, button(buttonName(other)).isSelected(),
                        "after choosing " + chosen + ", button " + other);
            }
        }
    }

    @Test
    void theSecondFieldStaysEnabledWithAUnaryOperationSelected() { // LOG-20 (agreed)
        for (Operation operation : new Operation[] {Operation.SQUARE, Operation.SQUARE_ROOT, Operation.RECIPROCAL}) {
            button(buttonName(operation)).doClick();
            assertTrue(second().isEnabled(), operation.toString());
            assertTrue(second().isVisible() || second().getParent() != null, operation.toString());
        }
    }

    @Test
    void unaryCalculationsShowTheirResults() { // CR-AC-02, CR-AC-06, CR-AC-10
        assertCalculation("-3", "", Operation.SQUARE, "9");
        assertCalculation("2", "", Operation.SQUARE_ROOT, "1.4142135624");
        assertCalculation("3", "", Operation.RECIPROCAL, "0.3333333333");
    }

    @Test
    void enterInEitherFieldRunsAUnaryCalculation() { // CR-FR-03
        button("squareRootButton").doClick();
        first().setText("9");
        first().postActionEvent();
        assertEquals("3", result());
        button("reciprocalButton").doClick();
        second().postActionEvent();
        assertEquals("0.1111111111", result());
        assertEquals("", error());
    }

    @Test
    void aDifferentSecondValueDoesNotChangeAUnaryResult() { // CR-AC-16, CR-AC-17, LOG-17 (agreed)
        assertCalculation("4", "", Operation.RECIPROCAL, "0.25");
        assertCalculation("4", "7", Operation.RECIPROCAL, "0.25");
        assertCalculation("4", "abc", Operation.RECIPROCAL, "0.25");
        assertEquals("", error());
    }

    @Test
    void aShownResultStaysWhenTheOperationChangesUntilTheNextCalculate() { // LOG-13 (agreed)
        assertCalculation("-3", "", Operation.SQUARE, "9");
        button("squareRootButton").doClick();
        assertEquals("9", result());
        first().setText("16");
        assertEquals("9", result());
        calculate();
        assertEquals("4", result());
        assertEquals("", error());
    }

    // ---- CR-01 user story 2: inline errors for the unary operations ----

    private static final Operation[] UNARY = {Operation.SQUARE, Operation.SQUARE_ROOT, Operation.RECIPROCAL};

    @Test
    void negativeSquareRootShowsItsMessageAndNoResult() { // CR-AC-08
        int windows = java.awt.Window.getWindows().length;
        enter("-1", "", Operation.SQUARE_ROOT);
        assertErrorShown("Cannot take square root of a negative number");
        assertEquals(windows, java.awt.Window.getWindows().length, "no dialog may open");
    }

    @Test
    void reciprocalOfZeroShowsTheDivisionByZeroMessage() { // CR-AC-12, CR-AC-13
        for (String zero : new String[] {"0", "0.0", "-0.0"}) {
            enter(zero, "", Operation.RECIPROCAL);
            assertErrorShown("Cannot divide by zero");
        }
    }

    @Test
    void emptyFirstNumberShowsEnterBothNumbersForEachUnaryOperation() { // CR-AC-14, LOG-21 (agreed)
        for (Operation operation : UNARY) {
            enter("", "", operation);
            assertErrorShown("Enter both numbers");
        }
    }

    @Test
    void malformedFirstNumberShowsInvalidNumberForEachUnaryOperation() { // CR-AC-15
        for (Operation operation : UNARY) {
            enter("abc", "", operation);
            assertErrorShown("Invalid number");
        }
    }

    @Test
    void theErrorAreaIsWideEnoughToShowEveryMessageInFull() { // CR-AC-08 (found in the real window, DL-57)
        JLabel errorLabel = find("errorLabel", JLabel.class);
        java.awt.FontMetrics metrics = errorLabel.getFontMetrics(errorLabel.getFont());
        for (calculator.core.CalculationError error : calculator.core.CalculationError.values()) {
            String message = ErrorMessages.messageFor(error);
            assertTrue(metrics.stringWidth(message) <= errorLabel.getPreferredSize().width,
                    "message cut off in the window: " + message);
        }
    }

    // ---- CR-01 user story 3: recovery and Clear after unary operations ----

    @Test
    void afterANegativeSquareRootErrorACorrectedInputGivesTheResult() { // CR-AC-18, CR-FR-10
        enter("-1", "", Operation.SQUARE_ROOT);
        assertErrorShown("Cannot take square root of a negative number");
        first().setText("16");
        calculate();
        assertEquals("4", result());
        assertEquals("", error());
    }

    @Test
    void afterAReciprocalOfZeroErrorACorrectedInputGivesTheResult() { // CR-AC-19, CR-FR-10
        enter("0", "", Operation.RECIPROCAL);
        assertErrorShown("Cannot divide by zero");
        first().setText("4");
        calculate();
        assertEquals("0.25", result());
        assertEquals("", error());
    }

    @Test
    void clearAfterAUnaryCalculationReturnsToTheInitialState() { // CR-AC-20, BASE-FR-05
        for (Operation operation : UNARY) {
            enter("4", "9", operation);
            calculate();
            assertFalse(result().isEmpty(), "a result is shown before Clear: " + operation);
            button("clearButton").doClick();
            assertEquals("", first().getText(), operation.toString());
            assertEquals("", second().getText(), operation.toString());
            for (Operation each : Operation.values()) {
                assertFalse(button(buttonName(each)).isSelected(), "after Clear, " + each + " (was " + operation + ")");
            }
            assertEquals("", result(), operation.toString());
            assertEquals("", error(), operation.toString());
        }
    }

    @Test
    void clearAfterAUnaryErrorReturnsToTheInitialState() { // CR-AC-20
        enter("-1", "", Operation.SQUARE_ROOT);
        calculate();
        button("clearButton").doClick();
        assertEquals("", error());
        assertEquals("", result());
        assertFalse(button("squareRootButton").isSelected());
    }

    @Test
    void aMixedSequenceOfBasicAndUnaryCalculationsStaysUsable() { // BASE-AC-16 idea for the changed application
        assertCalculation("7", "5", Operation.ADD, "12");
        assertCalculation("12", "", Operation.SQUARE, "144");
        assertCalculation("144", "", Operation.SQUARE_ROOT, "12");
        assertCalculation("4", "", Operation.RECIPROCAL, "0.25");
        assertCalculation("7", "2", Operation.DIVIDE, "3.5");
        enter("0", "", Operation.RECIPROCAL);
        assertErrorShown("Cannot divide by zero");
        assertCalculation("9", "", Operation.SQUARE_ROOT, "3");
        assertEquals("", error());
    }
}
