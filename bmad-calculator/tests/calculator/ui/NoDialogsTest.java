package calculator.ui;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/** Errors must appear inline; this guards against dialogs and hard exits creeping into the source. */
class NoDialogsTest {

    private static final Path SOURCE_ROOT = Path.of("src", "calculator");

    @Test
    void errorsAreNeverShownInDialogsAndNothingExitsTheJvmDirectly() throws IOException {
        List<String> forbidden = List.of("JOptionPane", "JDialog", "JFileChooser", "JColorChooser", "java.awt.Dialog",
                "System.exit", "Runtime.getRuntime()");
        assertTrue(Files.isDirectory(SOURCE_ROOT),
                "run the tests from the bmad-calculator project directory; not found: " + SOURCE_ROOT.toAbsolutePath());
        try (Stream<Path> files = Files.walk(SOURCE_ROOT)) {
            List<Path> sources = files.filter(f -> f.toString().endsWith(".java")).toList();
            assertTrue(sources.size() >= 8, "expected the application sources under " + SOURCE_ROOT);
            for (Path source : sources) {
                String text = Files.readString(source);
                for (String token : forbidden) {
                    assertFalse(text.contains(token), source + " must not contain " + token);
                }
            }
        }
    }
}
