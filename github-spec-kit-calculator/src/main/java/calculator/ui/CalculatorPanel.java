package calculator.ui;

import calculator.core.CalculationOutcome;
import calculator.core.Calculator;
import calculator.core.Operation;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.EnumMap;
import java.util.Map;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JToggleButton;

/**
 * The calculator screen as a panel (not a window). Component names are set so tests can find
 * them; see specs/001-baseline-calculator/contracts/ui-contract.md.
 */
public class CalculatorPanel extends JPanel {

    private static final int FIELD_COLUMNS = 14;

    private final JTextField firstOperandField = new JTextField(FIELD_COLUMNS);
    private final JTextField secondOperandField = new JTextField(FIELD_COLUMNS);
    private final Map<Operation, JToggleButton> operationButtons = new EnumMap<>(Operation.class);
    private final ButtonGroup operationGroup = new ButtonGroup();
    private final JButton calculateButton = new JButton("Calculate");
    private final JButton clearButton = new JButton("Clear");
    private final JLabel resultLabel = new JLabel("");
    private final JLabel errorLabel = new JLabel("");

    public CalculatorPanel() {
        super(new GridBagLayout());
        setBorder(BorderFactory.createEmptyBorder(12, 12, 12, 12));

        firstOperandField.setName("firstOperandField");
        secondOperandField.setName("secondOperandField");
        calculateButton.setName("calculateButton");
        clearButton.setName("clearButton");
        resultLabel.setName("resultLabel");
        errorLabel.setName("errorLabel");

        addOperationButton(Operation.ADD, "addButton");
        addOperationButton(Operation.SUBTRACT, "subtractButton");
        addOperationButton(Operation.MULTIPLY, "multiplyButton");
        addOperationButton(Operation.DIVIDE, "divideButton");

        calculateButton.addActionListener(event -> calculate());
        clearButton.addActionListener(event -> clear());
        firstOperandField.addActionListener(event -> calculate());
        secondOperandField.addActionListener(event -> calculate());

        layoutComponents();
    }

    private void addOperationButton(Operation operation, String name) {
        JToggleButton button = new JToggleButton(operation.symbol());
        button.setName(name);
        operationGroup.add(button);
        operationButtons.put(operation, button);
    }

    private void layoutComponents() {
        int row = 0;
        addRow(row++, labelFor("First number", firstOperandField), firstOperandField);
        addRow(row++, labelFor("Second number", secondOperandField), secondOperandField);

        JPanel operations = new JPanel();
        for (JToggleButton button : operationButtons.values()) {
            operations.add(button);
        }
        addRow(row++, new JLabel("Operation"), operations);

        JPanel actions = new JPanel();
        actions.add(calculateButton);
        actions.add(clearButton);
        GridBagConstraints actionRow = constraints(0, row++);
        actionRow.gridwidth = 2;
        add(actions, actionRow);

        resultLabel.setPreferredSize(valueAreaSize(resultLabel));
        addRow(row++, new JLabel("Result:"), resultLabel);

        errorLabel.setPreferredSize(valueAreaSize(errorLabel));
        addRow(row++, new JLabel("Error:"), errorLabel);
    }

    private static Dimension valueAreaSize(JLabel label) {
        return new Dimension(220, label.getFontMetrics(label.getFont()).getHeight() + 4);
    }

    private static JLabel labelFor(String text, JTextField field) {
        JLabel label = new JLabel(text);
        label.setLabelFor(field);
        return label;
    }

    private void addRow(int row, JLabel label, java.awt.Component control) {
        add(label, constraints(0, row));
        add(control, constraints(1, row));
    }

    private static GridBagConstraints constraints(int column, int row) {
        GridBagConstraints c = new GridBagConstraints();
        c.gridx = column;
        c.gridy = row;
        c.anchor = GridBagConstraints.WEST;
        c.insets = new Insets(4, 4, 4, 4);
        return c;
    }

    private Operation selectedOperation() {
        for (Map.Entry<Operation, JToggleButton> entry : operationButtons.entrySet()) {
            if (entry.getValue().isSelected()) {
                return entry.getKey();
            }
        }
        return null;
    }

    /**
     * Returns to the initial state: both fields empty, no operation selected, no result and no
     * error. Editing a field or choosing an operation does not do this; only Clear does.
     *
     * <p>Design choice DL-12 (not a requirement): keyboard focus then moves to the first field.
     */
    private void clear() {
        firstOperandField.setText("");
        secondOperandField.setText("");
        operationGroup.clearSelection();
        resultLabel.setText("");
        errorLabel.setText("");
        firstOperandField.requestFocusInWindow();
    }

    /**
     * Called by the Calculate button and by Enter in either operand field. The previous result
     * and error are emptied first, then the new result or the inline error is shown.
     *
     * <p>Design choice DL-13 (not a requirement): an unexpected exception is written to the
     * standard error stream and the window stays open.
     */
    private void calculate() {
        resultLabel.setText("");
        errorLabel.setText("");
        try {
            CalculationOutcome outcome = Calculator.calculate(
                    firstOperandField.getText(), secondOperandField.getText(), selectedOperation());
            switch (outcome) {
                case CalculationOutcome.Success success -> resultLabel.setText(success.text());
                case CalculationOutcome.Failure failure ->
                        errorLabel.setText(ErrorMessages.messageFor(failure.error()));
            }
        } catch (RuntimeException unexpected) {
            unexpected.printStackTrace();
        }
    }
}
