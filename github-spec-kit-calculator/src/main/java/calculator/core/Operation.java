package calculator.core;

/** The four basic operations. Each carries the symbol shown on its button. */
public enum Operation {
    ADD("+"),
    SUBTRACT("−"), // U+2212 MINUS SIGN
    MULTIPLY("×"), // U+00D7 MULTIPLICATION SIGN
    DIVIDE("÷");   // U+00F7 DIVISION SIGN

    private final String symbol;

    Operation(String symbol) {
        this.symbol = symbol;
    }

    public String symbol() {
        return symbol;
    }
}
