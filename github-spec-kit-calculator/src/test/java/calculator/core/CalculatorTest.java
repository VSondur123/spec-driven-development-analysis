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
}
