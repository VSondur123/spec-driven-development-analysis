package calculator.core;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;

/**
 * The arithmetic operations. Binary operations use both operands; unary operations use only the first and
 * ignore the second. Adding an operation means adding a constant here and handling any domain rule in
 * {@link Calculator}.
 */
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
    },
    SQUARE("x²", "Square", true) {
        @Override
        BigDecimal apply(BigDecimal a, BigDecimal b) {
            return a.multiply(a);
        }
    },
    RECIPROCAL("1/x", "Reciprocal", true) {
        @Override
        BigDecimal apply(BigDecimal a, BigDecimal b) {
            return BigDecimal.ONE.divide(a, DIVISION_SCALE, RoundingMode.HALF_UP);
        }
    },
    SQRT("√x", "Square root", true) {
        @Override
        BigDecimal apply(BigDecimal a, BigDecimal b) {
            return squareRoot(a);
        }
    };

    /** Decimal places kept when dividing (display policy). */
    static final int DIVISION_SCALE = 10;

    private final String symbol;
    private final String label;
    private final boolean unary;

    Operation(String symbol, String label) {
        this(symbol, label, false);
    }

    Operation(String symbol, String label, boolean unary) {
        this.symbol = symbol;
        this.label = label;
        this.unary = unary;
    }

    public String symbol() {
        return symbol;
    }

    public String label() {
        return label;
    }

    /**
     * Square root of a non-negative value, rounded half-up to {@link #DIVISION_SCALE} decimal places using only
     * exact integer arithmetic: {@code r = floor(sqrt(floor(a * 10^(2*scale))))}, then one is added when
     * {@code a * 10^(2*scale) >= r^2 + r + 1/4}, which is exactly when the root reaches {@code r + 1/2}.
     */
    private static BigDecimal squareRoot(BigDecimal a) {
        BigDecimal scaled = a.movePointRight(2 * DIVISION_SCALE);
        BigInteger root = scaled.toBigInteger().sqrt();
        BigDecimal halfwayPoint = new BigDecimal(root.multiply(root).add(root)).add(new BigDecimal("0.25"));
        if (scaled.compareTo(halfwayPoint) >= 0) {
            root = root.add(BigInteger.ONE);
        }
        return new BigDecimal(root, DIVISION_SCALE);
    }

    /** True when the operation uses only the first operand. */
    public boolean isUnary() {
        return unary;
    }

    /**
     * Applies the operation. Package-private so callers go through {@link Calculator}. Never pass a zero divisor to
     * {@link #DIVIDE}, a zero first operand to {@link #RECIPROCAL}, or a negative first operand to {@link #SQRT}.
     * The second operand is ignored by unary operations.
     */
    abstract BigDecimal apply(BigDecimal a, BigDecimal b);
}
