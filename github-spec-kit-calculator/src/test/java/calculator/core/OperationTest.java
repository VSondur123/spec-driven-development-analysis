package calculator.core;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

/** The operations and their symbols: CR-FR-01 (CR-01 section 2.1) and the baseline symbols. */
class OperationTest {

    @Test
    void theThreeUnaryOperationsHaveTheirLabels() {
        assertEquals("x²", Operation.SQUARE.symbol());
        assertEquals("√x", Operation.SQUARE_ROOT.symbol());
        assertEquals("1/x", Operation.RECIPROCAL.symbol());
    }

    @Test
    void theThreeNewOperationsAreUnary() {
        assertTrue(Operation.SQUARE.isUnary());
        assertTrue(Operation.SQUARE_ROOT.isUnary());
        assertTrue(Operation.RECIPROCAL.isUnary());
    }

    @Test
    void theFourBasicOperationsAreNotUnary() {
        assertFalse(Operation.ADD.isUnary());
        assertFalse(Operation.SUBTRACT.isUnary());
        assertFalse(Operation.MULTIPLY.isUnary());
        assertFalse(Operation.DIVIDE.isUnary());
    }

    @Test
    void theBaselineSymbolsAreUnchanged() {
        assertEquals("+", Operation.ADD.symbol());
        assertEquals("−", Operation.SUBTRACT.symbol()); // U+2212
        assertEquals("×", Operation.MULTIPLY.symbol());
        assertEquals("÷", Operation.DIVIDE.symbol());
    }
}
