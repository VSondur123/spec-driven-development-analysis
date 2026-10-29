package calculator.core;

/** The error cases, declared in priority order: the first one found is the one reported. */
public enum CalculationError {
    EMPTY_OPERAND,
    NO_OPERATION,
    INVALID_NUMBER,
    DIVISION_BY_ZERO
}
