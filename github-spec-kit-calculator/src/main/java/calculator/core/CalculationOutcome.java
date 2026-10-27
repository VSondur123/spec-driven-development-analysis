package calculator.core;

/** Exactly one of a result text or one error. A failure carries no result text. */
public sealed interface CalculationOutcome {

    /** A successful calculation; {@code text} is the result as plain decimal text. */
    record Success(String text) implements CalculationOutcome {
    }

    /** A failed calculation with the single error that was found. */
    record Failure(CalculationError error) implements CalculationOutcome {
    }
}
