package calculator.ui;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import javax.swing.AbstractButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dialog;
import java.awt.GraphicsEnvironment;
import java.awt.Window;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Consumer;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assumptions.assumeFalse;

/** Drives the real Swing components by their stable names. Skipped when no display is available. */
class CalculatorFrameInteractionTest {

    private static Component find(Container root, String name) {
        for (Component child : root.getComponents()) {
            if (name.equals(child.getName())) {
                return child;
            }
            if (child instanceof Container container) {
                Component found = find(container, name);
                if (found != null) {
                    return found;
                }
            }
        }
        return null;
    }

    /** Runs {@code body} on the EDT against a fresh frame and always disposes it. */
    private static void withFrame(Consumer<CalculatorFrame> body) throws Exception {
        assumeFalse(GraphicsEnvironment.isHeadless(), "needs a display");
        AtomicReference<Throwable> failure = new AtomicReference<>();
        SwingUtilities.invokeAndWait(() -> {
            CalculatorFrame frame = new CalculatorFrame();
            try {
                body.accept(frame);
            } catch (Throwable t) {
                failure.set(t);
            } finally {
                frame.dispose();
            }
        });
        if (failure.get() instanceof AssertionError error) {
            throw error;
        }
        if (failure.get() != null) {
            throw new RuntimeException(failure.get());
        }
    }

    private static Component require(CalculatorFrame frame, String name) {
        Component component = find(frame.getContentPane(), name);
        assertNotNull(component, "missing component " + name);
        return component;
    }

    private static JTextField field(CalculatorFrame frame, String name) {
        return (JTextField) require(frame, name);
    }

    private static AbstractButton button(CalculatorFrame frame, String name) {
        return (AbstractButton) require(frame, name);
    }

    private static String label(CalculatorFrame frame, String name) {
        return ((JLabel) require(frame, name)).getText().trim();
    }

    private static void calculate(CalculatorFrame frame, String a, String op, String b) {
        field(frame, "operandA").setText(a);
        field(frame, "operandB").setText(b);
        button(frame, op).doClick();
        button(frame, "calculate").doClick();
    }

    @Test
    void everyControlHasAStableName() throws Exception {
        withFrame(frame -> {
            for (String name : new String[]{"operandA", "operandB", "op-add", "op-subtract", "op-multiply",
                    "op-divide", "calculate", "clear", "result", "error"}) {
                assertNotNull(find(frame.getContentPane(), name), "missing component " + name);
            }
        });
    }

    @Test
    void initialStateHasNothingSelectedAndNoOutput() throws Exception {
        withFrame(frame -> {
            assertEquals("", field(frame, "operandA").getText());
            assertEquals("", field(frame, "operandB").getText());
            for (String op : new String[]{"op-add", "op-subtract", "op-multiply", "op-divide"}) {
                assertFalse(button(frame, op).isSelected());
            }
            assertEquals("", label(frame, "result"));
            assertEquals("", label(frame, "error"));
        });
    }

    @Test
    void baseAc02_and_03_integer_negative_and_decimal_operands_are_accepted_and_shown() throws Exception {
        withFrame(frame -> {
            JTextField a = field(frame, "operandA");
            for (String text : new String[]{"7", "-7", "1.2"}) {
                a.setText(text);
                assertEquals(text, a.getText());
            }
        });
    }

    @Test
    void baseAc04_clicking_calculate_shows_the_result() throws Exception {
        withFrame(frame -> {
            calculate(frame, "7", "op-add", "5");
            assertEquals("12", label(frame, "result"));
            assertEquals("", label(frame, "error"));
        });
    }

    @Test
    void baseAc05_to_09_each_operation_through_the_gui() throws Exception {
        withFrame(frame -> {
            calculate(frame, "7", "op-subtract", "5");
            assertEquals("2", label(frame, "result"));
            calculate(frame, "7", "op-multiply", "5");
            assertEquals("35", label(frame, "result"));
            calculate(frame, "7", "op-divide", "2");
            assertEquals("3.5", label(frame, "result"));
            calculate(frame, "-7", "op-add", "2");
            assertEquals("-5", label(frame, "result"));
            calculate(frame, "1.2", "op-add", "2.3");
            assertEquals("3.5", label(frame, "result"));
        });
    }

    @Test
    void baseAc19_enter_in_either_field_calculates_like_the_button() throws Exception {
        withFrame(frame -> {
            field(frame, "operandA").setText("7");
            field(frame, "operandB").setText("5");
            button(frame, "op-add").doClick();
            field(frame, "operandA").postActionEvent();
            assertEquals("12", label(frame, "result"));

            button(frame, "op-multiply").doClick();
            field(frame, "operandB").postActionEvent();
            assertEquals("35", label(frame, "result"));
        });
    }

    @Test
    void selectedOperationIsIndicatedAndReplaced() throws Exception {
        withFrame(frame -> {
            button(frame, "op-add").doClick();
            assertTrue(button(frame, "op-add").isSelected());
            button(frame, "op-multiply").doClick();
            assertTrue(button(frame, "op-multiply").isSelected());
            assertFalse(button(frame, "op-add").isSelected());
        });
    }

    @Test
    void baseAc16_consecutive_calculations_follow_the_current_inputs() throws Exception {
        withFrame(frame -> {
            calculate(frame, "7", "op-add", "5");
            assertEquals("12", label(frame, "result"));
            calculate(frame, "7", "op-multiply", "5");
            assertEquals("35", label(frame, "result"));
            calculate(frame, "2", "op-add", "3");
            assertEquals("5", label(frame, "result"));
        });
    }

    @Test
    void selectingAnOperationKeepsResultAndTypedText() throws Exception {
        withFrame(frame -> {
            calculate(frame, "7", "op-add", "5");
            field(frame, "operandA").setText("42");
            field(frame, "operandB").setText("99");
            button(frame, "op-multiply").doClick();
            assertEquals("12", label(frame, "result"));
            assertEquals("42", field(frame, "operandA").getText());
            assertEquals("99", field(frame, "operandB").getText());
        });
    }

    @Test
    void baseAc11_blank_operand_shows_the_error_inline() throws Exception {
        withFrame(frame -> {
            calculate(frame, "", "op-add", "5");
            assertEquals("Enter both numbers", label(frame, "error"));
            assertEquals("", label(frame, "result"));
        });
    }

    @Test
    void baseAc17_no_operation_selected_shows_the_error_inline() throws Exception {
        withFrame(frame -> {
            field(frame, "operandA").setText("7");
            field(frame, "operandB").setText("5");
            button(frame, "calculate").doClick();
            assertEquals("Select an operation", label(frame, "error"));
            assertEquals("", label(frame, "result"));
        });
    }

    @Test
    void baseAc12_malformed_input_shows_the_error_inline() throws Exception {
        withFrame(frame -> {
            calculate(frame, "abc", "op-add", "5");
            assertEquals("Invalid number", label(frame, "error"));
            assertEquals("", label(frame, "result"));
        });
    }

    @Test
    void anErrorIsShownInlineAndTheResultIsCleared() throws Exception {
        withFrame(frame -> {
            calculate(frame, "7", "op-add", "5");
            calculate(frame, "7", "op-divide", "0");
            assertEquals("", label(frame, "result"));
            assertEquals("Cannot divide by zero", label(frame, "error"));
        });
    }

    /** A successful calculation comes first so "no result with an error" is not vacuously true. */
    @ParameterizedTest(name = "malformed [{0}]")
    @ValueSource(strings = {"abc", "1.2.3", "1,5", "1e3", "--1", ".", "+1", "-", "1.", ".5", "1 2"})
    void baseAc12_a_malformed_operand_is_rejected_in_the_window(String bad) throws Exception {
        withFrame(frame -> {
            calculate(frame, "1", "op-add", "1");
            assertEquals("2", label(frame, "result"));
            calculate(frame, bad, "op-add", "5");
            assertEquals("Invalid number", label(frame, "error"), "first operand");
            assertEquals("", label(frame, "result"), "first operand");
            calculate(frame, "1", "op-add", "1");
            calculate(frame, "5", "op-add", bad);
            assertEquals("Invalid number", label(frame, "error"), "second operand");
            assertEquals("", label(frame, "result"), "second operand");
        });
    }

    @ParameterizedTest(name = "blank first operand [{0}]")
    @ValueSource(strings = {"", "   ", "\t"})
    void baseAc11_a_blank_first_operand_is_rejected_and_clears_the_result(String blank) throws Exception {
        withFrame(frame -> {
            calculate(frame, "1", "op-add", "1");
            assertEquals("2", label(frame, "result"));
            calculate(frame, blank, "op-add", "5");
            assertEquals("Enter both numbers", label(frame, "error"));
            assertEquals("", label(frame, "result"));
        });
    }

    @Test
    void baseAc11_a_blank_second_operand_and_both_blank_are_rejected() throws Exception {
        withFrame(frame -> {
            calculate(frame, "1", "op-add", "1");
            calculate(frame, "7", "op-add", "   ");
            assertEquals("Enter both numbers", label(frame, "error"));
            assertEquals("", label(frame, "result"));
            calculate(frame, "1", "op-add", "1");
            calculate(frame, "", "op-add", "");
            assertEquals("Enter both numbers", label(frame, "error"));
            assertEquals("", label(frame, "result"));
        });
    }

    @Test
    void errorPrecedenceIsEmptyThenNoOperationThenInvalid() throws Exception {
        withFrame(frame -> {
            field(frame, "operandA").setText("");
            field(frame, "operandB").setText("abc");
            button(frame, "calculate").doClick();
            assertEquals("Enter both numbers", label(frame, "error"), "empty beats no operation");
        });
        withFrame(frame -> {
            calculate(frame, "", "op-add", "abc");
            assertEquals("Enter both numbers", label(frame, "error"), "empty beats invalid");
        });
        withFrame(frame -> {
            field(frame, "operandA").setText("abc");
            field(frame, "operandB").setText("5");
            button(frame, "calculate").doClick();
            assertEquals("Select an operation", label(frame, "error"), "no operation beats invalid");
        });
    }

    @Test
    void anErrorOpensNoDialogOrExtraWindow() throws Exception {
        withFrame(frame -> {
            calculate(frame, "abc", "op-add", "5");
            for (Window window : Window.getWindows()) {
                assertFalse(window instanceof Dialog && window.isShowing(), "dialog showing: " + window);
            }
        });
    }

    @Test
    void aValidCalculationAfterAnErrorShowsTheResultAndClearsTheError() throws Exception {
        withFrame(frame -> {
            calculate(frame, "abc", "op-add", "5");
            assertEquals("Invalid number", label(frame, "error"));
            calculate(frame, "7", "op-add", "5");
            assertEquals("12", label(frame, "result"));
            assertEquals("", label(frame, "error"));
        });
    }

    private static void assertNoNonNumbers(CalculatorFrame frame) {
        for (String name : new String[]{"result", "error"}) {
            String text = label(frame, name);
            assertFalse(text.contains("Infinity") || text.contains("NaN"), name + " shows " + text);
        }
    }

    @ParameterizedTest(name = "7 / {0}")
    @ValueSource(strings = {"0", "0.0", "0.000", "-0", "00", "-0.0"})
    void baseAc13_division_by_zero_in_the_window_shows_the_error_and_clears_the_result(String zero)
            throws Exception {
        withFrame(frame -> {
            calculate(frame, "7", "op-add", "5");
            assertEquals("12", label(frame, "result"));
            calculate(frame, "7", "op-divide", zero);
            assertEquals("Cannot divide by zero", label(frame, "error"));
            assertEquals("", label(frame, "result"));
            assertNoNonNumbers(frame);
        });
    }

    @Test
    void baseAc13_division_by_zero_as_the_very_first_action_and_zero_by_zero() throws Exception {
        withFrame(frame -> {
            calculate(frame, "7", "op-divide", "0");
            assertEquals("Cannot divide by zero", label(frame, "error"));
            assertEquals("", label(frame, "result"));
        });
        withFrame(frame -> {
            calculate(frame, "0", "op-divide", "0");
            assertEquals("Cannot divide by zero", label(frame, "error"));
            assertEquals("", label(frame, "result"));
        });
    }

    @Test
    void baseAc18_zero_numerator_repeating_decimal_and_other_operations_with_zero() throws Exception {
        withFrame(frame -> {
            calculate(frame, "0", "op-divide", "5");
            assertEquals("0", label(frame, "result"));
            assertEquals("", label(frame, "error"));
            calculate(frame, "1", "op-divide", "3");
            assertEquals("0.3333333333", label(frame, "result"));
            calculate(frame, "7", "op-multiply", "0");
            assertEquals("0", label(frame, "result"));
            calculate(frame, "0", "op-subtract", "0");
            assertEquals("0", label(frame, "result"));
            calculate(frame, "0", "op-add", "0");
            assertEquals("0", label(frame, "result"));
            assertNoNonNumbers(frame);
        });
    }

    @Test
    void baseAc14_a_blank_field_is_fixed_and_the_calculation_succeeds() throws Exception {
        withFrame(frame -> {
            calculate(frame, "", "op-add", "5");
            assertEquals("Enter both numbers", label(frame, "error"));
            calculate(frame, "2", "op-add", "5");
            assertEquals("7", label(frame, "result"));
            assertEquals("", label(frame, "error"));
        });
    }

    @Test
    void baseAc14_a_malformed_field_is_fixed_and_the_calculation_succeeds() throws Exception {
        withFrame(frame -> {
            calculate(frame, "1,5", "op-add", "5");
            assertEquals("Invalid number", label(frame, "error"));
            calculate(frame, "1.5", "op-add", "5");
            assertEquals("6.5", label(frame, "result"));
            assertEquals("", label(frame, "error"));
        });
    }

    @Test
    void baseAc14_a_blank_second_operand_and_both_blank_are_fixed() throws Exception {
        withFrame(frame -> {
            calculate(frame, "2", "op-add", "");
            assertEquals("Enter both numbers", label(frame, "error"));
            calculate(frame, "2", "op-add", "5");
            assertEquals("7", label(frame, "result"));
            calculate(frame, "", "op-add", "");
            assertEquals("Enter both numbers", label(frame, "error"));
            calculate(frame, "3", "op-add", "4");
            assertEquals("7", label(frame, "result"));
            assertEquals("", label(frame, "error"));
        });
    }

    @Test
    void baseAc14_a_zero_divisor_is_fixed_by_the_divisor_by_the_operation_or_by_the_enter_key()
            throws Exception {
        withFrame(frame -> {
            calculate(frame, "7", "op-divide", "0");
            assertEquals("Cannot divide by zero", label(frame, "error"));
            field(frame, "operandB").setText("2");
            button(frame, "calculate").doClick();
            assertEquals("3.5", label(frame, "result"));
            assertEquals("", label(frame, "error"));

            calculate(frame, "7", "op-divide", "0");
            button(frame, "op-add").doClick();
            button(frame, "calculate").doClick();
            assertEquals("7", label(frame, "result"));
            assertEquals("", label(frame, "error"));

            calculate(frame, "7", "op-divide", "0");
            field(frame, "operandB").setText("7");
            field(frame, "operandB").postActionEvent();
            assertEquals("1", label(frame, "result"));
            assertEquals("", label(frame, "error"));
        });
    }

    @Test
    void anErrorStaysAfterSelectingAnOperationForEveryErrorKind() throws Exception {
        withFrame(frame -> {
            calculate(frame, "", "op-add", "5");
            button(frame, "op-multiply").doClick();
            assertEquals("Enter both numbers", label(frame, "error"));
            calculate(frame, "abc", "op-add", "5");
            button(frame, "op-multiply").doClick();
            assertEquals("Invalid number", label(frame, "error"));
            calculate(frame, "7", "op-divide", "0");
            button(frame, "op-add").doClick();
            assertEquals("Cannot divide by zero", label(frame, "error"));
            assertEquals("", label(frame, "result"));
        });
    }

    @Test
    void baseAc14_choosing_the_missing_operation_keeps_the_error_until_calculate() throws Exception {
        withFrame(frame -> {
            field(frame, "operandA").setText("7");
            field(frame, "operandB").setText("5");
            button(frame, "calculate").doClick();
            assertEquals("Select an operation", label(frame, "error"));
            button(frame, "op-add").doClick();
            assertEquals("Select an operation", label(frame, "error"));
            button(frame, "calculate").doClick();
            assertEquals("12", label(frame, "result"));
            assertEquals("", label(frame, "error"));
        });
    }

    @Test
    void aFailingCalculateAfterAResultLeavesOnlyTheError() throws Exception {
        withFrame(frame -> {
            calculate(frame, "7", "op-add", "5");
            assertEquals("12", label(frame, "result"));
            calculate(frame, "", "op-add", "5");
            assertEquals("", label(frame, "result"));
            assertEquals("Enter both numbers", label(frame, "error"));
        });
    }

    @Test
    void alternatingErrorsAndResultsAlwaysShowOnlyTheLatestOutcome() throws Exception {
        withFrame(frame -> {
            calculate(frame, "7", "op-add", "5");
            calculate(frame, "", "op-add", "5");
            assertEquals("Enter both numbers", label(frame, "error"));
            calculate(frame, "abc", "op-add", "5");
            assertEquals("Invalid number", label(frame, "error"));
            assertEquals("", label(frame, "result"));
            calculate(frame, "7", "op-divide", "0");
            assertEquals("Cannot divide by zero", label(frame, "error"));
            assertEquals("", label(frame, "result"));
            calculate(frame, "7", "op-divide", "0");
            assertEquals("Cannot divide by zero", label(frame, "error"));
            calculate(frame, "6", "op-multiply", "7");
            assertEquals("42", label(frame, "result"));
            assertEquals("", label(frame, "error"));
        });
        withFrame(frame -> {
            field(frame, "operandA").setText("7");
            field(frame, "operandB").setText("5");
            button(frame, "calculate").doClick();
            assertEquals("Select an operation", label(frame, "error"));
            calculate(frame, "", "op-add", "5");
            assertEquals("Enter both numbers", label(frame, "error"));
            assertEquals("", label(frame, "result"));
        });
    }

    private static void assertInitialState(CalculatorFrame frame) {
        assertEquals("", field(frame, "operandA").getText());
        assertEquals("", field(frame, "operandB").getText());
        for (String op : new String[]{"op-add", "op-subtract", "op-multiply", "op-divide"}) {
            assertFalse(button(frame, op).isSelected(), op + " still selected");
        }
        assertEquals("", label(frame, "result"));
        assertEquals("", label(frame, "error"));
    }

    @Test
    void baseAc10_clear_after_a_result_resets_everything() throws Exception {
        withFrame(frame -> {
            calculate(frame, "7", "op-add", "5");
            assertEquals("12", label(frame, "result"));
            button(frame, "clear").doClick();
            assertInitialState(frame);
        });
    }

    @ParameterizedTest(name = "clear after error [{0}] [{1}] {2}")
    @CsvSource(delimiter = '|', value = {
            "'' | 5 | op-add", "abc | 5 | op-add", "7 | 0 | op-divide"})
    void baseAc10_clear_after_each_error_resets_everything(String a, String b, String op) throws Exception {
        withFrame(frame -> {
            calculate(frame, a, op, b);
            assertFalse(label(frame, "error").isEmpty());
            button(frame, "clear").doClick();
            assertInitialState(frame);
        });
    }

    @Test
    void baseAc10_clear_after_a_missing_operation_error_typed_only_and_repeated() throws Exception {
        withFrame(frame -> {
            field(frame, "operandA").setText("7");
            field(frame, "operandB").setText("5");
            button(frame, "calculate").doClick();
            assertEquals("Select an operation", label(frame, "error"));
            button(frame, "clear").doClick();
            assertInitialState(frame);
        });
        withFrame(frame -> {
            field(frame, "operandA").setText("12");
            field(frame, "operandB").setText("34");
            button(frame, "op-multiply").doClick();
            button(frame, "clear").doClick();
            assertInitialState(frame);
            button(frame, "clear").doClick();
            assertInitialState(frame);
        });
    }

    @Test
    void afterClearTheCalculatorWorksNormallyAndTheOldOperationIsGone() throws Exception {
        withFrame(frame -> {
            calculate(frame, "7", "op-add", "5");
            button(frame, "clear").doClick();
            field(frame, "operandA").setText("7");
            field(frame, "operandB").setText("5");
            button(frame, "calculate").doClick();
            assertEquals("Select an operation", label(frame, "error"));
            calculate(frame, "2", "op-multiply", "3");
            assertEquals("6", label(frame, "result"));
            assertEquals("", label(frame, "error"));
        });
    }

    @Test
    void enterStillCalculatesAndNeverClears() throws Exception {
        withFrame(frame -> {
            field(frame, "operandA").setText("7");
            field(frame, "operandB").setText("5");
            button(frame, "op-add").doClick();
            field(frame, "operandA").postActionEvent();
            assertEquals("12", label(frame, "result"));
            assertEquals("7", field(frame, "operandA").getText());
            assertEquals("5", field(frame, "operandB").getText());
            assertTrue(button(frame, "op-add").isSelected());
            assertEquals("", label(frame, "error"));
        });
    }
}
