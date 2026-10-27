package calculator.core;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.math.BigDecimal;
import org.junit.jupiter.api.Test;

/** Result display: FR-011. */
class ResultFormatterTest {

    @Test
    void trailingZerosAreRemoved() {
        assertEquals("3.5", ResultFormatter.format(new BigDecimal("3.50")));
    }

    @Test
    void wholeNumbersStayAsTheyAre() {
        assertEquals("12", ResultFormatter.format(new BigDecimal("12")));
        assertEquals("-5", ResultFormatter.format(new BigDecimal("-5")));
    }

    @Test
    void zeroIsShownAsZero() {
        assertEquals("0", ResultFormatter.format(new BigDecimal("0")));
        assertEquals("0", ResultFormatter.format(new BigDecimal("0.0000")));
    }

    @Test
    void noExponentNotation() {
        assertEquals("1000", ResultFormatter.format(new BigDecimal("1E+3")));
        assertEquals("1000", ResultFormatter.format(new BigDecimal("1000.000")));
        assertEquals("0.00000001", ResultFormatter.format(new BigDecimal("1E-8")));
    }

    @Test
    void outputNeverHasExponentOrTrailingZeroAfterThePoint() {
        String[] inputs = {"3.50", "12", "0.0000", "1E+3", "-5", "100.100", "2.000"};
        for (String input : inputs) {
            String text = ResultFormatter.format(new BigDecimal(input));
            assertFalse(text.contains("E") || text.contains("e"), text);
            assertFalse(text.contains(".") && text.endsWith("0"), text);
        }
    }
}
