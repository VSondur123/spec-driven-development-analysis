package calculator.core;

import java.math.BigDecimal;

/** Renders a result as plain decimal text: no trailing zeros, no exponent, zero as "0". */
final class ResultFormatter {

    private ResultFormatter() {
    }

    static String format(BigDecimal value) {
        if (value.signum() == 0) {
            return "0";
        }
        return value.stripTrailingZeros().toPlainString();
    }
}
