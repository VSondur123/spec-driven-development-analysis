package calculator.core;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class OperationTest {

    @Test
    void symbolsAndLabelsIdentifyEachOperation() {
        assertEquals("+", Operation.ADD.symbol());
        assertEquals("−", Operation.SUBTRACT.symbol());
        assertEquals("×", Operation.MULTIPLY.symbol());
        assertEquals("÷", Operation.DIVIDE.symbol());
        assertEquals("Division", Operation.DIVIDE.label());
        assertEquals("x²", Operation.SQUARE.symbol());
        assertEquals("Square", Operation.SQUARE.label());
        assertEquals("1/x", Operation.RECIPROCAL.symbol());
        assertEquals("Reciprocal", Operation.RECIPROCAL.label());
        assertEquals("√x", Operation.SQRT.symbol());
        assertEquals("Square root", Operation.SQRT.label());
        assertEquals(7, Operation.values().length);
    }

    @Test
    void divisionKeepsTenDecimalPlacesRoundedHalfUp() {
        assertEquals(new BigDecimal("0.3333333333"),
                Operation.DIVIDE.apply(BigDecimal.ONE, new BigDecimal("3")));
        assertEquals(new BigDecimal("0.6666666667"),
                Operation.DIVIDE.apply(new BigDecimal("2"), new BigDecimal("3")));
        assertEquals(new BigDecimal("0.0000000001"),
                Operation.DIVIDE.apply(new BigDecimal("1"), new BigDecimal("10000000000")));
    }

    @Test
    void squareMultipliesTheFirstOperandAndIgnoresTheSecond() {
        assertEquals(new BigDecimal("1.44"), Operation.SQUARE.apply(new BigDecimal("1.2"), BigDecimal.ZERO));
        assertEquals(new BigDecimal("9"), Operation.SQUARE.apply(new BigDecimal("-3"), new BigDecimal("100")));
    }

    @Test
    void reciprocalKeepsTenDecimalPlacesRoundedHalfUpAndIgnoresTheSecondOperand() {
        assertEquals(new BigDecimal("0.3333333333"), Operation.RECIPROCAL.apply(new BigDecimal("3"), BigDecimal.ZERO));
        assertEquals(new BigDecimal("0.6666666667"), Operation.RECIPROCAL.apply(new BigDecimal("1.5"), new BigDecimal("9")));
        assertEquals(new BigDecimal("0.2500000000"), Operation.RECIPROCAL.apply(new BigDecimal("4"), BigDecimal.ZERO));
    }

    @ParameterizedTest(name = "sqrt({0}) = {1}")
    @CsvSource({"9, 3.0000000000", "2, 1.4142135624", "0, 0E-10", "0.25, 0.5000000000", "1.21, 1.1000000000",
            "0.0000000002, 0.0000141421", "0.0000000000000000000025, 0.0000000001",
            "0.0000000000000000000024, 0E-10", "99999999999999999999, 9999999999.9999999999",
            "123456789.123456789, 11111.1110661111", "10000000000000000000000000000000000000000, 100000000000000000000.0000000000", "100000000000000000000000000000000000000000, 316227766016837933199.8893544433"})
    void squareRootIsRoundedHalfUpToTenPlacesUsingExactIntegers(String a, String expected) {
        assertEquals(new BigDecimal(expected), Operation.SQRT.apply(new BigDecimal(a), BigDecimal.ZERO));
    }

    @Test
    void onlyTheNewOperationIsUnary() {
        assertTrue(Operation.SQUARE.isUnary());
        assertTrue(Operation.RECIPROCAL.isUnary());
        assertTrue(Operation.SQRT.isUnary());
        for (Operation op : new Operation[]{Operation.ADD, Operation.SUBTRACT, Operation.MULTIPLY, Operation.DIVIDE}) {
            assertFalse(op.isUnary());
        }
    }
}
