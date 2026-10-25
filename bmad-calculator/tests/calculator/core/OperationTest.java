package calculator.core;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

class OperationTest {

    @Test
    void symbolsAndLabelsIdentifyEachOperation() {
        assertEquals("+", Operation.ADD.symbol());
        assertEquals("−", Operation.SUBTRACT.symbol());
        assertEquals("×", Operation.MULTIPLY.symbol());
        assertEquals("÷", Operation.DIVIDE.symbol());
        assertEquals("Division", Operation.DIVIDE.label());
        assertEquals(4, Operation.values().length);
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
}
