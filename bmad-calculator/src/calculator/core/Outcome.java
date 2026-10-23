package calculator.core;

/** Result of {@link Calculator#evaluate}: either display text or the reason for failure. */
public sealed interface Outcome {

    record Success(String text) implements Outcome {
    }

    record Failure(ErrorKind kind) implements Outcome {
    }
}
