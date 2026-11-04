package calculator.core;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

import calculator.core.CalculationOutcome.Failure;
import calculator.core.CalculationOutcome.Success;
import org.junit.jupiter.api.Test;

/** Calculation rules: contracts/calculation-contract.md. */
class CalculatorTest {

    private static void assertResult(String expected, String first, String second, Operation operation) {
        assertEquals(new Success(expected), Calculator.calculate(first, second, operation),
                first + " " + operation + " " + second);
    }

    // ---- User story 1: success cases ----

    @Test
    void addition() { // BASE-AC-04
        assertResult("12", "7", "5", Operation.ADD);
    }

    @Test
    void subtraction() { // BASE-AC-05
        assertResult("2", "7", "5", Operation.SUBTRACT);
    }

    @Test
    void multiplication() { // BASE-AC-06
        assertResult("35", "7", "5", Operation.MULTIPLY);
    }

    @Test
    void divisionWithDecimalResult() { // BASE-AC-07
        assertResult("3.5", "7", "2", Operation.DIVIDE);
    }

    @Test
    void negativeOperand() { // BASE-AC-08
        assertResult("-5", "-7", "2", Operation.ADD);
    }

    @Test
    void decimalOperandsGiveNoFloatingPointArtefact() { // BASE-AC-09
        assertResult("3.5", "1.2", "2.3", Operation.ADD);
    }

    @Test
    void zeroDividedByFive() { // BASE-AC-18
        assertResult("0", "0", "5", Operation.DIVIDE);
    }

    @Test
    void oneThirdIsRoundedToTenPlaces() { // BASE-AC-18
        assertResult("0.3333333333", "1", "3", Operation.DIVIDE);
    }

    @Test
    void wholeNumberQuotientHasNoTrailingZeros() { // LOG-03 example
        assertResult("1", "2", "2", Operation.DIVIDE);
    }

    @Test
    void surroundingSpacesAreIgnored() { // baseline section 4 item 9
        assertResult("12", "  7 ", " 5  ", Operation.ADD);
    }

    @Test
    void zeroMinusSeven() {
        assertResult("-7", "0", "7", Operation.SUBTRACT);
    }

    @Test
    void negativeZeroIsNeverShown() { // FR-011
        assertResult("0", "0", "-0", Operation.ADD);
    }

    @Test
    void negativeTieRoundsAwayFromZero() { // LOG-15 (agreed)
        assertResult("-0.0004882813", "-1", "2048", Operation.DIVIDE);
    }

    @Test
    void positiveTieRoundsUp() { // LOG-15 (agreed)
        assertResult("0.0004882813", "1", "2048", Operation.DIVIDE);
    }

    // ---- User story 2: error cases ----

    private static void assertError(CalculationError expected, String first, String second,
            Operation operation) {
        assertEquals(new Failure(expected), Calculator.calculate(first, second, operation),
                "'" + first + "' " + operation + " '" + second + "'");
    }

    @Test
    void emptyOperandIsReportedWhicheverOperandIsEmpty() { // BASE-AC-11
        assertError(CalculationError.EMPTY_OPERAND, "", "5", Operation.ADD);
        assertError(CalculationError.EMPTY_OPERAND, "5", "", Operation.ADD);
        assertError(CalculationError.EMPTY_OPERAND, "", "", Operation.ADD);
    }

    @Test
    void spacesOnlyOperandCountsAsEmpty() { // LOG-12 (agreed)
        assertError(CalculationError.EMPTY_OPERAND, "   ", "5", Operation.ADD);
        assertError(CalculationError.EMPTY_OPERAND, "5", "  ", Operation.ADD);
    }

    @Test
    void noOperationSelected() { // BASE-AC-17
        assertError(CalculationError.NO_OPERATION, "7", "5", null);
    }

    @Test
    void malformedOperandsAreInvalidInEitherPosition() { // BASE-AC-12
        for (String bad : new String[] {"abc", "1.2.3", "1,5", "1e3", "--1", ".", "+1"}) {
            assertError(CalculationError.INVALID_NUMBER, bad, "5", Operation.ADD);
            assertError(CalculationError.INVALID_NUMBER, "5", bad, Operation.ADD);
        }
    }

    @Test
    void incompleteDecimalFormsAreInvalid() { // LOG-12 (agreed)
        for (String bad : new String[] {".5", "5.", "-"}) {
            assertError(CalculationError.INVALID_NUMBER, bad, "5", Operation.ADD);
            assertError(CalculationError.INVALID_NUMBER, "5", bad, Operation.ADD);
        }
    }

    @Test
    void divisionByZero() { // BASE-AC-13
        assertError(CalculationError.DIVISION_BY_ZERO, "7", "0", Operation.DIVIDE);
        assertError(CalculationError.DIVISION_BY_ZERO, "7", "0.0", Operation.DIVIDE);
        assertError(CalculationError.DIVISION_BY_ZERO, "7", "-0", Operation.DIVIDE);
    }

    @Test
    void zeroDividendIsNotAnError() {
        assertResult("0", "0", "7", Operation.DIVIDE);
    }

    @Test
    void errorPriorityEmptyThenNoOperationThenInvalidThenArithmetic() { // baseline section 4 item 10
        assertError(CalculationError.EMPTY_OPERAND, "", "5", null);
        assertError(CalculationError.EMPTY_OPERAND, "", "abc", Operation.ADD);
        assertError(CalculationError.NO_OPERATION, "abc", "5", null);
        assertError(CalculationError.INVALID_NUMBER, "abc", "0", Operation.DIVIDE);
        assertError(CalculationError.INVALID_NUMBER, "7", "abc", Operation.DIVIDE);
    }

    @Test
    void neverThrowsForAnyCombination() {
        String[] inputs = {"", " ", "7", "-7", "0", "0.0", "-0", "1.5", "abc", "1.2.3", "1,5", "1e3",
            "--1", ".", "+1", ".5", "5.", "-", "\t", "99999999999999999999999999999999999999"};
        Operation[] operations = {null, Operation.ADD, Operation.SUBTRACT, Operation.MULTIPLY,
            Operation.DIVIDE};
        for (String a : inputs) {
            for (String b : inputs) {
                for (Operation operation : operations) {
                    assertDoesNotThrow(() -> Calculator.calculate(a, b, operation),
                            "'" + a + "' " + operation + " '" + b + "'");
                }
            }
        }
    }

    // ---- CR-01 user story 1: square, square root and reciprocal of valid input ----

    @Test
    void squareOfValidInput() { // CR-AC-02 to CR-AC-04, CR-FR-04
        assertResult("9", "-3", "", Operation.SQUARE); // CR-AC-02
        assertResult("1.44", "1.2", "", Operation.SQUARE); // CR-AC-03
        assertResult("0", "0", "", Operation.SQUARE); // CR-AC-04
        assertResult("0", "-0.0", "", Operation.SQUARE);
        assertResult("6.25", "2.5", "", Operation.SQUARE);
        assertResult("100", "10", "", Operation.SQUARE);
    }

    @Test
    void squareIsExactAndInPlainNotation() { // CR-01 section 4 items 5 and 8
        assertResult("152415787532388367501905199875019052100", "12345678901234567890", "", Operation.SQUARE);
        assertResult("0.0000000000000000000001", "0.00000000001", "", Operation.SQUARE);
    }

    @Test
    void squareRootOfValidInput() { // CR-AC-05 to CR-AC-07, CR-FR-05
        assertResult("3", "9", "", Operation.SQUARE_ROOT); // CR-AC-05
        assertResult("1.4142135624", "2", "", Operation.SQUARE_ROOT); // CR-AC-06
        assertResult("0", "0", "", Operation.SQUARE_ROOT); // CR-AC-07
        assertResult("0", "0.0", "", Operation.SQUARE_ROOT);
        assertResult("2", "4", "", Operation.SQUARE_ROOT);
        assertResult("1.2", "1.44", "", Operation.SQUARE_ROOT);
        assertResult("0.5", "0.25", "", Operation.SQUARE_ROOT);
    }

    @Test
    void squareRootIsRoundedOnceHalfUpToTenDecimals() { // CR-01 section 4 items 6 and 9
        assertResult("1.7320508076", "3", "", Operation.SQUARE_ROOT); // 1.73205080756...
        assertResult("2.2360679775", "5", "", Operation.SQUARE_ROOT); // 2.23606797749979...
        assertResult("0.7071067812", "0.5", "", Operation.SQUARE_ROOT); // 0.70710678118...
        assertResult("351364182882014.4253111222", "123456789012345678901234567890", "", Operation.SQUARE_ROOT);
    }

    @Test
    void squareRootTiesRoundUpAndJustBelowATieRoundsDown() {
        // The root is exactly 5E-11, half of the last kept place: rounds up.
        assertResult("0.0000000001", "0.0000000000000000000025", "", Operation.SQUARE_ROOT);
        // Just below the tie (a double rounding through 20 places would round this up): rounds down.
        assertResult("0", "0.0000000000000000000024999999999999999999", "", Operation.SQUARE_ROOT);
        // About 3.16E-11, below half of 1E-10.
        assertResult("0", "0.000000000000000000001", "", Operation.SQUARE_ROOT);
    }

    @Test
    void reciprocalOfNonZeroInput() { // CR-AC-09 to CR-AC-11, CR-FR-07
        assertResult("0.25", "4", "", Operation.RECIPROCAL); // CR-AC-09
        assertResult("0.3333333333", "3", "", Operation.RECIPROCAL); // CR-AC-10
        assertResult("-0.25", "-4", "", Operation.RECIPROCAL); // CR-AC-11
        assertResult("1", "1", "", Operation.RECIPROCAL);
        assertResult("2", "0.5", "", Operation.RECIPROCAL);
        assertResult("-1", "-1", "", Operation.RECIPROCAL);
    }

    @Test
    void reciprocalTiesRoundAwayFromZero() { // same policy as baseline division (LOG-15, agreed)
        assertResult("0.0004882813", "2048", "", Operation.RECIPROCAL); // exact 0.00048828125
        assertResult("-0.0004882813", "-2048", "", Operation.RECIPROCAL);
    }

    @Test
    void reciprocalOfAVeryLargeValueShowsZero() { // LOG-19 (agreed)
        assertResult("0", "30000000000", "", Operation.RECIPROCAL);
        assertResult("0", "-30000000000", "", Operation.RECIPROCAL); // never "-0"
    }

    @Test
    void theSecondNumberIsIgnoredForUnaryOperations() { // CR-AC-16, CR-AC-17, LOG-17 (agreed)
        for (Operation operation : new Operation[] {Operation.SQUARE, Operation.SQUARE_ROOT, Operation.RECIPROCAL}) {
            Object withSecondEmpty = Calculator.calculate("4", "", operation);
            assertEquals(withSecondEmpty, Calculator.calculate("4", "7", operation), operation + " with 7");
            assertEquals(withSecondEmpty, Calculator.calculate("4", "abc", operation), operation + " with abc");
            assertEquals(withSecondEmpty, Calculator.calculate("4", "1,5", operation), operation + " with 1,5");
            assertEquals(withSecondEmpty, Calculator.calculate("4", "   ", operation), operation + " with spaces");
            assertEquals(Success.class, withSecondEmpty.getClass(), operation + " succeeds with Second empty");
        }
        assertError(CalculationError.EMPTY_OPERAND, "4", "", Operation.ADD); // a basic operation is unchanged
    }

    // ---- CR-01 user story 2: impossible or invalid unary input ----

    private static final Operation[] UNARY = {Operation.SQUARE, Operation.SQUARE_ROOT, Operation.RECIPROCAL};

    @Test
    void squareRootOfANegativeNumberIsRejected() { // CR-AC-08, CR-FR-06
        assertError(CalculationError.NEGATIVE_SQUARE_ROOT, "-1", "", Operation.SQUARE_ROOT);
        assertError(CalculationError.NEGATIVE_SQUARE_ROOT, "-0.5", "", Operation.SQUARE_ROOT);
        assertError(CalculationError.NEGATIVE_SQUARE_ROOT, "-1000", "", Operation.SQUARE_ROOT);
    }

    @Test
    void reciprocalOfZeroIsRejectedInEveryZeroForm() { // CR-AC-12, CR-AC-13, CR-FR-08
        for (String zero : new String[] {"0", "0.0", "-0.0", "-0"}) {
            assertError(CalculationError.DIVISION_BY_ZERO, zero, "", Operation.RECIPROCAL);
        }
    }

    @Test
    void anEmptyFirstNumberIsRejectedForEveryUnaryOperation() { // CR-AC-14, LOG-21 (agreed), LOG-18 (agreed)
        for (Operation operation : UNARY) {
            assertError(CalculationError.EMPTY_OPERAND, "", "", operation);
            assertError(CalculationError.EMPTY_OPERAND, "", "5", operation);
            assertError(CalculationError.EMPTY_OPERAND, "   ", "5", operation);
        }
    }

    @Test
    void aMalformedFirstNumberIsRejectedForEveryUnaryOperation() { // CR-AC-15
        for (Operation operation : UNARY) {
            assertError(CalculationError.INVALID_NUMBER, "abc", "", operation);
            assertError(CalculationError.INVALID_NUMBER, "1,5", "5", operation);
        }
    }

    @Test
    void unaryErrorPriorityFollowsTheBaselineOnTheFirstNumber() { // LOG-18 (agreed)
        assertError(CalculationError.EMPTY_OPERAND, "", "5", null);
        assertError(CalculationError.NO_OPERATION, "5", "5", null);
        assertError(CalculationError.NO_OPERATION, "abc", "5", null);
        assertError(CalculationError.INVALID_NUMBER, "abc", "", Operation.SQUARE_ROOT); // not a domain error
        assertError(CalculationError.INVALID_NUMBER, "--1", "", Operation.RECIPROCAL);
    }
}
