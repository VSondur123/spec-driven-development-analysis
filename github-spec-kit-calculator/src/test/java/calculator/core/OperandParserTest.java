package calculator.core;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.math.BigDecimal;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

/** Input rules: FR-009, FR-012; BASE-AC-02, BASE-AC-03, BASE-AC-12. */
class OperandParserTest {

    @ParameterizedTest
    @ValueSource(strings = {"", "   "})
    void emptyAndSpacesOnlyAreEmpty(String text) { // spaces-only: LOG-12 (agreed)
        OperandParser.Parsed parsed = OperandParser.parse(text);
        assertEquals(OperandParser.Kind.EMPTY, parsed.kind());
        assertNull(parsed.value());
    }

    @ParameterizedTest
    @ValueSource(strings = {"0", "7", "-7", "1.2", "-0.5", "  7 ", "007", "-0"})
    void validOperandsAreValid(String text) {
        assertEquals(OperandParser.Kind.VALID, OperandParser.parse(text).kind());
    }

    @Test
    void validOperandsKeepTheirValue() {
        assertEquals(new BigDecimal("7"), OperandParser.parse("7").value());
        assertEquals(new BigDecimal("-7"), OperandParser.parse("-7").value());
        assertEquals(new BigDecimal("1.2"), OperandParser.parse("1.2").value());
        assertEquals(new BigDecimal("-0.5"), OperandParser.parse("-0.5").value());
        assertEquals(new BigDecimal("7"), OperandParser.parse("  7 ").value());
    }

    @ParameterizedTest
    @ValueSource(strings = {"abc", "1.2.3", "1,5", "1e3", "--1", ".", "+1"})
    void malformedOperandsAreInvalid(String text) { // BASE-AC-12 and baseline section 4 item 9
        OperandParser.Parsed parsed = OperandParser.parse(text);
        assertEquals(OperandParser.Kind.INVALID, parsed.kind());
        assertNull(parsed.value());
    }

    @ParameterizedTest
    @ValueSource(strings = {".5", "5.", "-"})
    void incompleteDecimalFormsAreInvalid(String text) { // LOG-12 (agreed)
        assertEquals(OperandParser.Kind.INVALID, OperandParser.parse(text).kind());
    }
}
