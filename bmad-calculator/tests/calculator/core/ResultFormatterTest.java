package calculator.core;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ResultFormatterTest {

    @Test
    void stripsTrailingZeros() {
        assertEquals("3.5", ResultFormatter.format(new BigDecimal("3.5000000000")));
    }

    @Test
    void neverUsesExponentNotation() {
        assertEquals("100", ResultFormatter.format(new BigDecimal("100.0000000000")));
        assertEquals("1000000", ResultFormatter.format(new BigDecimal("1E+6")));
        assertEquals("0.0000000001", ResultFormatter.format(new BigDecimal("1E-10")));
    }

    @Test
    void zeroIsAlwaysPlainZero() {
        assertEquals("0", ResultFormatter.format(BigDecimal.ZERO));
        assertEquals("0", ResultFormatter.format(new BigDecimal("0.0000000000")));
        assertEquals("0", ResultFormatter.format(new BigDecimal("-0.0")));
    }

    @Test
    void keepsSignOfNegativeValues() {
        assertEquals("-5", ResultFormatter.format(new BigDecimal("-5.00")));
    }
}
