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
        if (a.isEmpty() || b.isEmpty()) {
            return new Outcome.Failure(ErrorKind.EMPTY_OPERAND);
        }
        if (operation == null) {
            return new Outcome.Failure(ErrorKind.NO_OPERATION);
        }
        if (!VALID_NUMBER.matcher(a).matches() || !VALID_NUMBER.matcher(b).matches()) {
            return new Outcome.Failure(ErrorKind.INVALID_NUMBER);
        }
        BigDecimal left = new BigDecimal(a);
        BigDecimal right = new BigDecimal(b);
        if (operation == Operation.DIVIDE && right.signum() == 0) {
            return new Outcome.Failure(ErrorKind.DIVIDE_BY_ZERO);
        }
        return new Outcome.Success(ResultFormatter.format(operation.apply(left, right)));
    }
}
