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
            "-1,  DIVIDE,   2048, -0.0004882813",
            "1,   DIVIDE,   2048, 0.0004882813",
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

    @ParameterizedTest(name = "square of {0} is {1}")
    @CsvSource({"-3, 9", "1.2, 1.44", "0, 0", "-0.0, 0", "4, 16", "' 5 ', 25", "0.5, 0.25", "2.50, 6.25", "0.0000001, 0.00000000000001", "0.1234567890123, 0.01524157875322755800955129"})
    void crAc02to04_square_of_the_first_number(String a, String expected) {
        assertEquals(success(expected), calculator.evaluate(a, Operation.SQUARE, ""));
    }

    @ParameterizedTest(name = "square ignores second number [{0}]")
    @ValueSource(strings = {"", "   ", "7", "abc", "1.2.3", "-", "1e3"})
    void crAc16and17_second_number_is_ignored_for_square(String second) {
        assertEquals(success("16"), calculator.evaluate("4", Operation.SQUARE, second));
        assertEquals(success("16"), calculator.evaluate("4", Operation.SQUARE, null));
    }

    @ParameterizedTest(name = "square first number [{0}]")
    @CsvSource(value = {"'', 5", "'   ', ''", "'', abc"})
    void crAc14_empty_first_number_is_reported_for_square(String a, String b) {
        assertEquals(failure(ErrorKind.EMPTY_OPERAND), calculator.evaluate(a, Operation.SQUARE, b));
    }

    @ParameterizedTest(name = "square malformed [{0}]")
    @ValueSource(strings = {"abc", "1.2.3", "1,5", "1e3", "--1", ".", "+1", "1.", ".5", "-"})
    void crAc15_malformed_first_number_is_invalid_for_square(String text) {
        assertEquals(failure(ErrorKind.INVALID_NUMBER), calculator.evaluate(text, Operation.SQUARE, "5"));
    }

    @Test
    void unaryDoesNotChangeTheBaselineRulesWithoutAnOperation() {
        assertEquals(failure(ErrorKind.EMPTY_OPERAND), calculator.evaluate("3", null, ""));
        assertEquals(failure(ErrorKind.NO_OPERATION), calculator.evaluate("3", null, "5"));
        assertEquals(failure(ErrorKind.EMPTY_OPERAND), calculator.evaluate("7", Operation.ADD, ""));
        assertEquals(failure(ErrorKind.DIVIDE_BY_ZERO), calculator.evaluate("7", Operation.DIVIDE, "0"));
    }

    @ParameterizedTest(name = "reciprocal of {0} is {1}")
    @CsvSource({"4, 0.25", "3, 0.3333333333", "-4, -0.25", "0.3, 3.3333333333", "1, 1", "0.5, 2",
            "30000000000, 0", "-30000000000, 0", "' 8 ', 0.125"})
    void crAc09to11_reciprocal_of_the_first_number(String a, String expected) {
        assertEquals(success(expected), calculator.evaluate(a, Operation.RECIPROCAL, ""));
    }

    @ParameterizedTest(name = "reciprocal of zero [{0}]")
    @ValueSource(strings = {"0", "0.0", "-0.0", "0.000", "-0", "00"})
    void crAc12and13_reciprocal_of_zero_is_a_divide_by_zero_error(String zero) {
        assertEquals(failure(ErrorKind.DIVIDE_BY_ZERO), calculator.evaluate(zero, Operation.RECIPROCAL, ""));
        assertEquals(failure(ErrorKind.DIVIDE_BY_ZERO), calculator.evaluate(zero, Operation.RECIPROCAL, "5"));
    }

    @ParameterizedTest(name = "reciprocal ignores second number [{0}]")
    @ValueSource(strings = {"", "   ", "0", "7", "abc", "1e3"})
    void crAc16and17_second_number_is_ignored_for_reciprocal(String second) {
        assertEquals(success("0.25"), calculator.evaluate("4", Operation.RECIPROCAL, second));
        assertEquals(success("0.25"), calculator.evaluate("4", Operation.RECIPROCAL, null));
    }

    @Test
    void nullFirstNumberIsEmptyForUnaryOperations() {
        assertEquals(failure(ErrorKind.EMPTY_OPERAND), calculator.evaluate(null, Operation.SQUARE, "5"));
        assertEquals(failure(ErrorKind.EMPTY_OPERAND), calculator.evaluate(null, Operation.RECIPROCAL, null));
    }

    @Test
    void crAc14_15_reciprocal_empty_and_malformed_first_number() {
        assertEquals(failure(ErrorKind.EMPTY_OPERAND), calculator.evaluate("", Operation.RECIPROCAL, "5"));
        assertEquals(failure(ErrorKind.EMPTY_OPERAND), calculator.evaluate("  ", Operation.RECIPROCAL, ""));
        assertEquals(failure(ErrorKind.INVALID_NUMBER), calculator.evaluate("abc", Operation.RECIPROCAL, ""));
        assertEquals(failure(ErrorKind.INVALID_NUMBER), calculator.evaluate(".5", Operation.RECIPROCAL, "0"));
    }

    @ParameterizedTest(name = "square root of {0} is {1}")
    @CsvSource({"9, 3", "2, 1.4142135624", "0, 0", "0.0, 0", "-0.0, 0", "-0, 0", "0.25, 0.5", "1.21, 1.1",
            "0.0000000002, 0.0000141421", "0.0000000000000000000025, 0.0000000001",
            "0.0000000000000000000024, 0", "99999999999999999999, 9999999999.9999999999",
            "123456789.123456789, 11111.1110661111", "' 16 ', 4"})
    void crAc05to07_square_root_of_the_first_number(String a, String expected) {
        assertEquals(success(expected), calculator.evaluate(a, Operation.SQRT, ""));
    }

    @ParameterizedTest(name = "square root of negative [{0}]")
    @ValueSource(strings = {"-1", "-0.0000001", "-9", "-0.0000000000000000000001"})
    void crAc08_square_root_of_a_negative_number_is_an_error(String negative) {
        assertEquals(failure(ErrorKind.NEGATIVE_SQRT), calculator.evaluate(negative, Operation.SQRT, ""));
        assertEquals(failure(ErrorKind.NEGATIVE_SQRT), calculator.evaluate(negative, Operation.SQRT, "abc"));
    }

    @ParameterizedTest(name = "square root ignores second number [{0}]")
    @ValueSource(strings = {"", "   ", "4", "abc", "-1", "1e3"})
    void crAc16and17_second_number_is_ignored_for_square_root(String second) {
        assertEquals(success("3"), calculator.evaluate("9", Operation.SQRT, second));
        assertEquals(success("3"), calculator.evaluate("9", Operation.SQRT, null));
    }

    @Test
    void crAc14_15_square_root_check_order() {
        assertEquals(failure(ErrorKind.EMPTY_OPERAND), calculator.evaluate("", Operation.SQRT, "5"));
        assertEquals(failure(ErrorKind.EMPTY_OPERAND), calculator.evaluate(null, Operation.SQRT, null));
        assertEquals(failure(ErrorKind.INVALID_NUMBER), calculator.evaluate("abc", Operation.SQRT, ""));
        assertEquals(failure(ErrorKind.INVALID_NUMBER), calculator.evaluate("-1e3", Operation.SQRT, ""));
        assertEquals(failure(ErrorKind.INVALID_NUMBER), calculator.evaluate("1,5", Operation.SQRT, ""));
    }
}
