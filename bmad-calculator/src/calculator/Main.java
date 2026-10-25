package calculator;

import calculator.ui.CalculatorFrame;

import javax.swing.SwingUtilities;

public final class Main {

    private Main() {
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new CalculatorFrame().setVisible(true));
    }
}
