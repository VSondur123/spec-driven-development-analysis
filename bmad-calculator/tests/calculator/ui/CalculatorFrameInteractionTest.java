package calculator.ui;

import org.junit.jupiter.api.Test;

import javax.swing.AbstractButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import java.awt.Component;
import java.awt.Container;
import java.awt.GraphicsEnvironment;
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
                    "op-divide", "calculate", "result", "error"}) {
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
}
