package calculator.core;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;

/**
 * The calculation rules. Works on the raw text of the two operand fields and the selected
 * operation; has no dependency on the GUI.
 */
public final class Calculator {

    /** Division, reciprocal and square root are rounded half-up to this many decimal places. */
    private static final int RESULT_SCALE = 10;

    /** 4 x 10^20: with {@link #squareRoot} this gives the root rounded once to 10 places. */
    private static final BigDecimal FOUR_TIMES_TEN_POW_20 = new BigDecimal("4E+20");

    private Calculator() {
    }

    /**
     * Calculates {@code first operation second}, or for a unary operation (square, square root,
     * reciprocal) the operation applied to {@code first} alone; the {@code second} text is then
     * not examined at all. Never throws for any text or a null operation; a problem is returned
     * as a failure holding the first error found in priority order: empty operand, then no
     * operation selected, then invalid number, then a domain error.
     *
     * @param operation the selected operation, or {@code null} when none is selected
     */
    public static CalculationOutcome calculate(String first, String second, Operation operation) {
        boolean unary = operation != null && operation.isUnary();
        OperandParser.Parsed a = OperandParser.parse(first);
        OperandParser.Parsed b = unary ? null : OperandParser.parse(second);

        if (a.kind() == OperandParser.Kind.EMPTY || (b != null && b.kind() == OperandParser.Kind.EMPTY)) {
            return new CalculationOutcome.Failure(CalculationError.EMPTY_OPERAND);
        }
        if (operation == null) {
            return new CalculationOutcome.Failure(CalculationError.NO_OPERATION);
        }
        if (a.kind() == OperandParser.Kind.INVALID || (b != null && b.kind() == OperandParser.Kind.INVALID)) {
            return new CalculationOutcome.Failure(CalculationError.INVALID_NUMBER);
        }
        if (unary) {
            if (operation == Operation.SQUARE_ROOT && a.value().signum() < 0) {
                return new CalculationOutcome.Failure(CalculationError.NEGATIVE_SQUARE_ROOT);
            }
            if (operation == Operation.RECIPROCAL && a.value().signum() == 0) {
                return new CalculationOutcome.Failure(CalculationError.DIVISION_BY_ZERO);
            }
            return new CalculationOutcome.Success(ResultFormatter.format(computeUnary(a.value(), operation)));
        }
        if (operation == Operation.DIVIDE && b.value().signum() == 0) {
            return new CalculationOutcome.Failure(CalculationError.DIVISION_BY_ZERO);
        }
        BigDecimal result = compute(a.value(), b.value(), operation);
        return new CalculationOutcome.Success(ResultFormatter.format(result));
    }

    private static BigDecimal compute(BigDecimal a, BigDecimal b, Operation operation) {
        return switch (operation) {
            case ADD -> a.add(b);
            case SUBTRACT -> a.subtract(b);
            case MULTIPLY -> a.multiply(b);
            case DIVIDE -> a.divide(b, RESULT_SCALE, RoundingMode.HALF_UP);
            case SQUARE, SQUARE_ROOT, RECIPROCAL -> throw new IllegalStateException("unary operation in binary path");
        };
    }

    private static BigDecimal computeUnary(BigDecimal x, Operation operation) {
        return switch (operation) {
            case SQUARE -> x.multiply(x);
            case SQUARE_ROOT -> squareRoot(x);
            case RECIPROCAL -> BigDecimal.ONE.divide(x, RESULT_SCALE, RoundingMode.HALF_UP);
            case ADD, SUBTRACT, MULTIPLY, DIVIDE -> throw new IllegalStateException("binary operation in unary path");
        };
    }

    /**
     * The square root of a non-negative value, rounded once to 10 decimal places half-up
     * (research C-01). With {@code T = 4 x 10^20 x}, {@code q = floor(sqrt(floor(T)))} and
     * {@code R = floor((q + 1) / 2)}, {@code R x 10^-10} equals {@code floor(sqrt(x) x 10^10 + 1/2)}
     * scaled back: only the final integer step rounds, and no {@code double} is used.
     */
    private static BigDecimal squareRoot(BigDecimal x) {
        BigDecimal t = x.multiply(FOUR_TIMES_TEN_POW_20);
        BigInteger n = t.setScale(0, RoundingMode.FLOOR).toBigInteger();
        BigInteger q = n.sqrt();
        BigInteger r = q.add(BigInteger.ONE).shiftRight(1);
        return new BigDecimal(r, RESULT_SCALE);
    }
}
