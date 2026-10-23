package calculator.ui;

import calculator.core.Calculator;
import calculator.core.Operation;
import calculator.presenter.CalculatorPresenter;
import calculator.presenter.ViewState;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JToggleButton;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.EnumMap;
import java.util.Map;
import java.util.Objects;

/** Passive Swing view: forwards user events to the presenter and renders the {@link ViewState} it returns. */
public class CalculatorFrame extends JFrame {

    public static final String TITLE = "Calculator";

    private final CalculatorPresenter presenter;
    private final JTextField operandA = new JTextField(12);
    private final JTextField operandB = new JTextField(12);
    private final Map<Operation, JToggleButton> operationButtons = new EnumMap<>(Operation.class);
    private final ButtonGroup operationGroup = new ButtonGroup();
    private final JButton calculateButton = new JButton("Calculate");
    private final JLabel resultLabel = new JLabel(" ");
    private final JLabel errorLabel = new JLabel(" ");

    public CalculatorFrame() {
        this(new CalculatorPresenter(new Calculator()));
    }

    public CalculatorFrame(CalculatorPresenter presenter) {
        super(TITLE);
        this.presenter = Objects.requireNonNull(presenter, "presenter");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        buildContent();
        render(presenter.state());
        setMinimumSize(new Dimension(420, 260));
        pack();
        setLocationRelativeTo(null);
    }

    private void buildContent() {
        operandA.setName("operandA");
        operandB.setName("operandB");
        calculateButton.setName("calculate");
        resultLabel.setName("result");
        errorLabel.setName("error");
        errorLabel.setForeground(new Color(0xB00020));

        JLabel labelA = new JLabel("First number");
        labelA.setLabelFor(operandA);
        JLabel labelB = new JLabel("Second number");
        labelB.setLabelFor(operandB);

        JPanel operations = new JPanel(new FlowLayout(FlowLayout.LEFT, 6, 0));
        for (Operation operation : Operation.values()) {
            JToggleButton button = new JToggleButton(operation.symbol());
            button.setName("op-" + operation.name().toLowerCase());
            button.setToolTipText(operation.label());
            button.getAccessibleContext().setAccessibleName(operation.label());
            button.addActionListener(e -> render(presenter.onOperationSelected(
                    operation, operandA.getText(), operandB.getText())));
            operationGroup.add(button);
            operationButtons.put(operation, button);
            operations.add(button);
        }

        calculateButton.addActionListener(e -> calculate());
        operandA.addActionListener(e -> calculate());
        operandB.addActionListener(e -> calculate());

        JLabel resultCaption = new JLabel("Result");
        resultCaption.setLabelFor(resultLabel);

        JPanel content = new JPanel(new GridBagLayout());
        content.setBorder(BorderFactory.createEmptyBorder(12, 12, 12, 12));
        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(4, 4, 4, 4);
        c.anchor = GridBagConstraints.WEST;

        addRow(content, c, 0, labelA, operandA);
        addRow(content, c, 1, new JLabel("Operation"), operations);
        addRow(content, c, 2, labelB, operandB);
        addRow(content, c, 3, new JLabel(), calculateButton);
        addRow(content, c, 4, resultCaption, resultLabel);
        addRow(content, c, 5, new JLabel(), errorLabel);
        setContentPane(content);
    }

    private static void addRow(JPanel panel, GridBagConstraints c, int row, Component left,
                               Component right) {
        c.gridy = row;
        c.gridx = 0;
        c.fill = GridBagConstraints.NONE;
        panel.add(left, c);
        c.gridx = 1;
        c.fill = right instanceof JTextField ? GridBagConstraints.HORIZONTAL : GridBagConstraints.NONE;
        panel.add(right, c);
    }

    private void calculate() {
        render(presenter.onCalculate(operandA.getText(), operandB.getText()));
    }

    private void render(ViewState state) {
        resultLabel.setText(state.result().isEmpty() ? " " : state.result());
        resultLabel.setToolTipText(state.result().isEmpty() ? null : state.result());
        errorLabel.setText(state.error().isEmpty() ? " " : state.error());
        if (state.selected() == null) {
            operationGroup.clearSelection();
        } else {
            operationButtons.get(state.selected()).setSelected(true);
        }
    }
}
