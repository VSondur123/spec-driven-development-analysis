package calculator.ui;

import javax.swing.JFrame;
import java.awt.Dimension;

/** Main window. Controls are added in Story 1.3; for now it is an empty, titled window. */
public class CalculatorFrame extends JFrame {

    public static final String TITLE = "Calculator";

    public CalculatorFrame() {
        super(TITLE);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setMinimumSize(new Dimension(420, 260));
        pack();
        setLocationRelativeTo(null);
    }
}
