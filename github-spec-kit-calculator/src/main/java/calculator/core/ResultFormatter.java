package calculator.core;

import java.math.BigDecimal;

/** Formats a result as plain decimal text: no trailing zeros, no exponent, zero as {@code 0}. */
public final class ResultFormatter {

    private ResultFormatter() {
    }

    public static String format(BigDecimal value) {
        if (value.signum() == 0) {
            return "0";
        }
        return value.stripTrailingZeros().toPlainString();
    }
}
