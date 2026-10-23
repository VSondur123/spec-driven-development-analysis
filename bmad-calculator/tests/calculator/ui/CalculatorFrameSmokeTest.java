package calculator.ui;

import calculator.Main;
import org.junit.jupiter.api.Test;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import java.awt.Frame;
import java.awt.GraphicsEnvironment;
import java.util.concurrent.atomic.AtomicReference;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assumptions.assumeFalse;

class CalculatorFrameSmokeTest {

    @Test
    void baseAc01_window_is_created_with_title_calculator() throws Exception {
        assumeFalse(GraphicsEnvironment.isHeadless(), "needs a display");
        AtomicReference<String> title = new AtomicReference<>();
        SwingUtilities.invokeAndWait(() -> {
            CalculatorFrame frame = new CalculatorFrame();
            try {
                title.set(frame.getTitle());
            } finally {
                frame.dispose();
            }
        });
        assertEquals(CalculatorFrame.TITLE, title.get());
    }

    @Test
    void baseAc01_closing_the_window_ends_the_application() throws Exception {
        assumeFalse(GraphicsEnvironment.isHeadless(), "needs a display");
        AtomicReference<Integer> closeOperation = new AtomicReference<>();
        SwingUtilities.invokeAndWait(() -> {
            CalculatorFrame frame = new CalculatorFrame();
            try {
                closeOperation.set(frame.getDefaultCloseOperation());
            } finally {
                frame.dispose();
            }
        });
        assertEquals(JFrame.EXIT_ON_CLOSE, closeOperation.get());
    }

    @Test
    void baseAc01_main_shows_the_calculator_window() throws Exception {
        assumeFalse(GraphicsEnvironment.isHeadless(), "needs a display");
        Main.main(new String[0]);
        AtomicReference<Frame> shown = new AtomicReference<>();
        // invokeAndWait runs after the invokeLater queued by Main, so the frame exists by now
        SwingUtilities.invokeAndWait(() -> {
            for (Frame f : Frame.getFrames()) {
                if (CalculatorFrame.TITLE.equals(f.getTitle()) && f.isShowing()) {
                    shown.set(f);
                }
            }
        });
        try {
            assertNotNull(shown.get(), "Main did not show a window titled " + CalculatorFrame.TITLE);
        } finally {
            if (shown.get() != null) {
                SwingUtilities.invokeAndWait(() -> shown.get().dispose());
            }
        }
    }
}
