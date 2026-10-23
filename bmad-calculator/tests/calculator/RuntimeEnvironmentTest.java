package calculator;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

class RuntimeEnvironmentTest {

    @Test
    void baseNfr01_runs_on_java_22_or_newer() {
        assertTrue(Runtime.version().feature() >= 22,
                "Java 22+ required, found " + Runtime.version());
    }
}
