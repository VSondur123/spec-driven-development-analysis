package calculator.core;

/**
 * The four basic operations and the three CR-01 unary operations. Each carries the label shown
 * on its button; the unary ones need only the First number.
 */
public enum Operation {
    ADD("+", false),
    SUBTRACT("−", false), // U+2212 MINUS SIGN
    MULTIPLY("×", false), // U+00D7 MULTIPLICATION SIGN
    DIVIDE("÷", false),   // U+00F7 DIVISION SIGN
    SQUARE("x²", true),   // U+00B2 SUPERSCRIPT TWO
    SQUARE_ROOT("√x", true), // U+221A SQUARE ROOT
    RECIPROCAL("1/x", true);

    private final String symbol;
    private final boolean unary;

    Operation(String symbol, boolean unary) {
        this.symbol = symbol;
        this.unary = unary;
    }

    public String symbol() {
        return symbol;
    }

    /** True when the operation uses only the First number (CR-FR-02). */
    public boolean isUnary() {
        return unary;
    }
}
