package calculator.ui;

import calculator.core.CalculationError;

/** The inline message for each error. The wording is the suggested wording; each one differs. */
final class ErrorMessages {

    private ErrorMessages() {
    }

    static String messageFor(CalculationError error) {
        return switch (error) {
            case EMPTY_OPERAND -> "Enter both numbers";
            case NO_OPERATION -> "Select an operation";
            case INVALID_NUMBER -> "Invalid number";
            case DIVISION_BY_ZERO -> "Cannot divide by zero";
        };
    }
}
