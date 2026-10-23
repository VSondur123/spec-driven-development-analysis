package calculator.architecture;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/** Guards the layering ui -> presenter -> core, with no AWT/Swing below the ui package (AD-1, NFR-4). */
class ArchitectureRulesTest {

    private static final Path SOURCE_ROOT = Path.of("src", "calculator");
    private static final Pattern IMPORT = Pattern.compile("^\\s*import\\s+(?:static\\s+)?([\\w.]+)", Pattern.MULTILINE);

    @Test
    void coreDoesNotDependOnAwtSwingPresenterOrUi() throws IOException {
        assertNoImports("core", "java.awt", "javax.swing", "calculator.presenter", "calculator.ui");
    }

    @Test
    void presenterDoesNotDependOnAwtSwingOrUi() throws IOException {
        assertNoImports("presenter", "java.awt", "javax.swing", "calculator.ui");
    }

    private static void assertNoImports(String layer, String... forbiddenPrefixes) throws IOException {
        Path dir = SOURCE_ROOT.resolve(layer);
        assertTrue(Files.isDirectory(dir),
                "run the tests from the bmad-calculator project directory; not found: " + dir.toAbsolutePath());
        try (Stream<Path> files = Files.walk(dir)) {
            List<Path> sources = files.filter(f -> f.toString().endsWith(".java")).toList();
            assertFalse(sources.isEmpty(), "no sources found in " + dir);
            for (Path source : sources) {
                Matcher m = IMPORT.matcher(Files.readString(source));
                while (m.find()) {
                    for (String prefix : forbiddenPrefixes) {
                        assertFalse(m.group(1).startsWith(prefix),
                                source + " must not import " + prefix + " (found " + m.group(1) + ")");
                    }
                }
            }
        }
    }
}
