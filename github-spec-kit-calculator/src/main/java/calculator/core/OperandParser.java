package calculator.core;

import java.math.BigDecimal;
import java.util.regex.Pattern;

/**
 * Classifies the text of one operand field.
 *
 * <p>Only the space character (U+0020) is removed at both ends. Empty text is EMPTY. Text matching
 * an optional leading minus, digits, and optionally one point followed by digits is VALID. Anything
 * else is INVALID. Digits are written as [0-9] so that only ASCII digits are accepted.
 */
public final class OperandParser {

    private static final Pattern GRAMMAR = Pattern.compile("-?[0-9]+(\\.[0-9]+)?");

    private OperandParser() {
    }

    /** The three possible classifications of an operand. */
    public enum Kind {
        EMPTY,
        VALID,
        INVALID
    }

    /** The classification and, for VALID operands only, the number. */
    public record Parsed(Kind kind, BigDecimal value) {
    }

    public static Parsed parse(String text) {
        String trimmed = stripSpaces(text);
        if (trimmed.isEmpty()) {
            return new Parsed(Kind.EMPTY, null);
        }
        if (GRAMMAR.matcher(trimmed).matches()) {
            return new Parsed(Kind.VALID, new BigDecimal(trimmed));
        }
        return new Parsed(Kind.INVALID, null);
    }

    private static String stripSpaces(String text) {
        int start = 0;
        int end = text.length();
        while (start < end && text.charAt(start) == ' ') {
            start++;
        }
        while (end > start && text.charAt(end - 1) == ' ') {
            end--;
        }
        return text.substring(start, end);
    }
}
