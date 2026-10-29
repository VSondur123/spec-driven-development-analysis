package calculator.core;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * The calculation rules. Works on the raw text of the two operand fields and the selected
 * operation; has no dependency on the GUI.
 */
public final class Calculator {

    /** Division is rounded half-up (ties away from zero) to this many decimal places. */
    private static final int DIVISION_SCALE = 10;

    private Calculator() {
    }

    /**
     * Calculates {@code first operation second}. Never throws for any text or a null operation;
     * a problem is returned as a failure holding the first error found in priority order: empty
     * operand, then no operation selected, then invalid number, then division by zero.
     *
     * @param operation the selected operation, or {@code null} when none is selected
     */
    public static CalculationOutcome calculate(String first, String second, Operation operation) {
        OperandParser.Parsed a = OperandParser.parse(first);
        OperandParser.Parsed b = OperandParser.parse(second);

        if (a.kind() == OperandParser.Kind.EMPTY || b.kind() == OperandParser.Kind.EMPTY) {
            return new CalculationOutcome.Failure(CalculationError.EMPTY_OPERAND);
        }
        if (operation == null) {
            return new CalculationOutcome.Failure(CalculationError.NO_OPERATION);
        }
        if (a.kind() == OperandParser.Kind.INVALID || b.kind() == OperandParser.Kind.INVALID) {
            return new CalculationOutcome.Failure(CalculationError.INVALID_NUMBER);
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
            case DIVIDE -> a.divide(b, DIVISION_SCALE, RoundingMode.HALF_UP);
        };
    }
}
