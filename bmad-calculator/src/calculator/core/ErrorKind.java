package calculator.core;

/** Reasons a calculation does not produce a result. The only source of user-visible error text. */
public enum ErrorKind {
    EMPTY_OPERAND("Enter both numbers"),
    NO_OPERATION("Select an operation"),
    INVALID_NUMBER("Invalid number"),
    DIVIDE_BY_ZERO("Cannot divide by zero");

    private final String message;

    ErrorKind(String message) {
        this.message = message;
    }

    public String message() {
        return message;
    }
}
