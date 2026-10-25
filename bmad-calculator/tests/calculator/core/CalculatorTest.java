package calculator.core;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

class CalculatorTest {

    private final Calculator calculator = new Calculator();

    private static Outcome success(String text) {
        return new Outcome.Success(text);
    }

    private static Outcome failure(ErrorKind kind) {
        return new Outcome.Failure(kind);
    }

    @Test
    void baseAc04_7_plus_5_is_12() {
        assertEquals(success("12"), calculator.evaluate("7", Operation.ADD, "5"));
    }

    @Test
    void baseAc05_7_minus_5_is_2() {
        assertEquals(success("2"), calculator.evaluate("7", Operation.SUBTRACT, "5"));
    }

    @Test
    void baseAc06_7_times_5_is_35() {
        assertEquals(success("35"), calculator.evaluate("7", Operation.MULTIPLY, "5"));
    }

    @Test
    void baseAc07_7_divided_by_2_is_3_5() {
        assertEquals(success("3.5"), calculator.evaluate("7", Operation.DIVIDE, "2"));
    }

    @Test
    void baseAc08_negative_minus7_plus_2_is_minus5() {
        assertEquals(success("-5"), calculator.evaluate("-7", Operation.ADD, "2"));
    }

    @Test
    void baseAc09_1_2_plus_2_3_is_exactly_3_5() {
        assertEquals(success("3.5"), calculator.evaluate("1.2", Operation.ADD, "2.3"));
    }

    @ParameterizedTest(name = "{0} {1} {2} = {3}")
    @CsvSource({
            "1,   DIVIDE,   3,  0.3333333333",
            "2,   DIVIDE,   3,  0.6666666667",
            "2,   DIVIDE,   2,  1",
            "100, DIVIDE,   1,  100",
            "0,   DIVIDE,   5,  0",
            "0,   MULTIPLY, -1, 0",
            "-0,  ADD,      0,  0",
            "0.5, MULTIPLY, 0.5, 0.25",
            "0.1, ADD,      0.2, 0.3",
            "' 7 ', ADD,    ' 5 ', 12",
            "-7,  MULTIPLY, -5, 35",
            "-7,  DIVIDE,   2,  -3.5",
            "7,   SUBTRACT, 10, -3",
            "-2,  DIVIDE,   3,  -0.6666666667",
            "-1,  DIVIDE,   3,  -0.3333333333",
            "-1,  DIVIDE,   30000000000, 0",
            "-0.5, ADD,     0.5, 0",
            "-0.0, ADD,     5,  5"
    })
    void baseAc18_display_policy(String a, Operation op, String b, String expected) {
        assertEquals(success(expected), calculator.evaluate(a, op, b));
    }

    @ParameterizedTest(name = "blank operand [{0}] [{1}]")
    @CsvSource(value = {"'', 5", "7, ''", "'   ', 5", "7, '   '", "'', ''"})
    void baseAc11_empty_operand_is_reported(String a, String b) {
        assertEquals(failure(ErrorKind.EMPTY_OPERAND), calculator.evaluate(a, Operation.ADD, b));
    }

    @Test
    void baseAc11_null_operand_is_reported_as_empty() {
        assertEquals(failure(ErrorKind.EMPTY_OPERAND), calculator.evaluate(null, Operation.ADD, "5"));
        assertEquals(failure(ErrorKind.EMPTY_OPERAND), calculator.evaluate("5", Operation.ADD, null));
    }

    @Test
    void baseAc17_missing_operation_is_reported() {
        assertEquals(failure(ErrorKind.NO_OPERATION), calculator.evaluate("7", null, "5"));
    }

    @ParameterizedTest(name = "malformed [{0}]")
    @ValueSource(strings = {"abc", "1.2.3", "1,5", "1e3", "--1", ".", "+1", "1.", ".5", "1 2", "٣", "-", "-.5", "-1."})
    void baseAc12_malformed_input_is_invalid_number(String text) {
        assertEquals(failure(ErrorKind.INVALID_NUMBER), calculator.evaluate(text, Operation.ADD, "5"));
        assertEquals(failure(ErrorKind.INVALID_NUMBER), calculator.evaluate("5", Operation.ADD, text));
    }

    @Test
    void validationOrderIsEmptyThenNoOperationThenInvalid() {
        assertEquals(failure(ErrorKind.EMPTY_OPERAND), calculator.evaluate("", null, "abc"));
        assertEquals(failure(ErrorKind.NO_OPERATION), calculator.evaluate("abc", null, "5"));
        assertEquals(failure(ErrorKind.INVALID_NUMBER), calculator.evaluate("abc", Operation.ADD, "5"));
    }

    @ParameterizedTest(name = "7 / {0}")
    @ValueSource(strings = {"0", "0.0", "-0", "0.000"})
    void baseAc13_division_by_zero_is_reported(String zero) {
        assertEquals(failure(ErrorKind.DIVIDE_BY_ZERO), calculator.evaluate("7", Operation.DIVIDE, zero));
    }

    @Test
    void evaluateNeverThrows() {
        String big = "9".repeat(5000);
        String bigDecimal = big + "." + big;
        String[] samples = {null, "", " ", "abc", "1e9999", big, bigDecimal, "-" + big, "-".repeat(3), "0", "-1.5"};
        for (Operation op : new Operation[]{null, Operation.ADD, Operation.SUBTRACT,
                Operation.MULTIPLY, Operation.DIVIDE}) {
            for (String a : samples) {
                for (String b : samples) {
                    assertDoesNotThrow(() -> calculator.evaluate(a, op, b));
                }
            }
        }
    }

    /** Texts proposed as LOG-04 / LOG-05 in shared/experiment-log.md and PRD decision D-07. */
    @Test
    void errorMessagesAreTheProposedTexts() {
        assertEquals("Enter both numbers", ErrorKind.EMPTY_OPERAND.message());
        assertEquals("Select an operation", ErrorKind.NO_OPERATION.message());
        assertEquals("Invalid number", ErrorKind.INVALID_NUMBER.message());
        assertEquals("Cannot divide by zero", ErrorKind.DIVIDE_BY_ZERO.message());
    }
}
