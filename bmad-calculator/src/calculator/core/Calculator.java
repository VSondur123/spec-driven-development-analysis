package calculator.core;

import java.math.BigDecimal;
import java.util.regex.Pattern;

/**
 * The only entry point into the core. Reports every user-input problem as a {@link Outcome.Failure}
 * and never throws for null, blank, malformed or zero-divisor input. Operand length is not capped, so
 * absurdly long input is limited only by available memory and time.
 */
public final class Calculator {

    /** Valid Number: optional leading minus, ASCII digits, optionally one decimal point followed by digits. */
    private static final Pattern VALID_NUMBER = Pattern.compile("-?[0-9]+(\\.[0-9]+)?");

    public Outcome evaluate(String operandA, Operation operation, String operandB) {
        String a = operandA == null ? "" : operandA.strip();
        String b = operandB == null ? "" : operandB.strip();
        // Without a selected operation both operands are required, as for a binary operation.
        boolean needsSecond = operation == null || !operation.isUnary();
        if (a.isEmpty() || (needsSecond && b.isEmpty())) {
            return new Outcome.Failure(ErrorKind.EMPTY_OPERAND);
        }
        if (operation == null) {
            return new Outcome.Failure(ErrorKind.NO_OPERATION);
        }
        if (!VALID_NUMBER.matcher(a).matches() || (needsSecond && !VALID_NUMBER.matcher(b).matches())) {
            return new Outcome.Failure(ErrorKind.INVALID_NUMBER);
        }
        BigDecimal left = new BigDecimal(a);
        BigDecimal right = needsSecond ? new BigDecimal(b) : BigDecimal.ZERO;
        if ((operation == Operation.DIVIDE && right.signum() == 0)
                || (operation == Operation.RECIPROCAL && left.signum() == 0)) {
            return new Outcome.Failure(ErrorKind.DIVIDE_BY_ZERO);
        }
        if (operation == Operation.SQRT && left.signum() < 0) {
            return new Outcome.Failure(ErrorKind.NEGATIVE_SQRT);
        }
        return new Outcome.Success(ResultFormatter.format(operation.apply(left, right)));
    }
}
