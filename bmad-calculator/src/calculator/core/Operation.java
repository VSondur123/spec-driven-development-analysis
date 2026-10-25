package calculator.core;

import java.math.BigDecimal;
import java.math.RoundingMode;

/** The basic arithmetic operations. Adding an operation means adding a constant here. */
public enum Operation {
    ADD("+", "Addition") {
        @Override
        BigDecimal apply(BigDecimal a, BigDecimal b) {
            return a.add(b);
        }
    },
    SUBTRACT("−", "Subtraction") {
        @Override
        BigDecimal apply(BigDecimal a, BigDecimal b) {
            return a.subtract(b);
        }
    },
    MULTIPLY("×", "Multiplication") {
        @Override
        BigDecimal apply(BigDecimal a, BigDecimal b) {
            return a.multiply(b);
        }
    },
    DIVIDE("÷", "Division") {
        @Override
        BigDecimal apply(BigDecimal a, BigDecimal b) {
            return a.divide(b, DIVISION_SCALE, RoundingMode.HALF_UP);
        }
    };

    /** Decimal places kept when dividing (display policy). */
    static final int DIVISION_SCALE = 10;

    private final String symbol;
    private final String label;

    Operation(String symbol, String label) {
        this.symbol = symbol;
        this.label = label;
    }

    public String symbol() {
        return symbol;
    }

    public String label() {
        return label;
    }

    /** Applies the operation. Package-private so callers go through {@link Calculator}; never pass a zero divisor to {@link #DIVIDE}. */
    abstract BigDecimal apply(BigDecimal a, BigDecimal b);
}
