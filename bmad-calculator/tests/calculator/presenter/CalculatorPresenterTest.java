package calculator.presenter;

import calculator.core.Calculator;
import calculator.core.ErrorKind;
import calculator.core.Operation;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class CalculatorPresenterTest {

    private final CalculatorPresenter presenter = new CalculatorPresenter(new Calculator());

    @Test
    void initialStateHasNoOperationResultOrError() {
        ViewState state = presenter.state();
        assertEquals("", state.operandA());
        assertEquals("", state.operandB());
        assertNull(state.selected());
        assertEquals("", state.result());
        assertEquals("", state.error());
    }

    @ParameterizedTest(name = "{0} {1} {2} = {3}")
    @CsvSource({
            "7,   ADD,      5,   12",
            "7,   SUBTRACT, 5,   2",
            "7,   MULTIPLY, 5,   35",
            "7,   DIVIDE,   2,   3.5",
            "7,   DIVIDE,   5,   1.4",
            "-7,  ADD,      2,   -5",
            "1.2, ADD,      2.3, 3.5"
    })
    void baseAc04to09_calculate_through_the_presenter(String a, Operation op, String b, String expected) {
        presenter.onOperationSelected(op, a, b);
        ViewState state = presenter.onCalculate(a, b);
        assertEquals(expected, state.result());
        assertEquals("", state.error());
        assertEquals(op, state.selected());
        assertEquals(a, state.operandA());
        assertEquals(b, state.operandB());
    }

    @Test
    void selectingAnotherOperationReplacesTheSelection() {
        presenter.onOperationSelected(Operation.ADD, "7", "5");
        ViewState state = presenter.onOperationSelected(Operation.MULTIPLY, "7", "5");
        assertEquals(Operation.MULTIPLY, state.selected());
    }

    @Test
    void baseAc16_consecutive_calculations_use_only_current_inputs() {
        presenter.onOperationSelected(Operation.ADD, "7", "5");
        assertEquals("12", presenter.onCalculate("7", "5").result());
        presenter.onOperationSelected(Operation.MULTIPLY, "7", "5");
        assertEquals("35", presenter.onCalculate("7", "5").result());
        assertEquals("6", presenter.onCalculate("2", "3").result());
    }

    @Test
    void selectingAnOperationKeepsTheCurrentResult() {
        presenter.onOperationSelected(Operation.ADD, "7", "5");
        presenter.onCalculate("7", "5");
        ViewState state = presenter.onOperationSelected(Operation.MULTIPLY, "7", "5");
        assertEquals("12", state.result());
    }

    @Test
    void selectingAnOperationEchoesTheTextsTheViewPassed() {
        presenter.onOperationSelected(Operation.ADD, "7", "5");
        presenter.onCalculate("7", "5");
        ViewState state = presenter.onOperationSelected(Operation.SUBTRACT, "7", "99");
        assertEquals("99", state.operandB());
    }

    @Test
    void aFailedCalculationShowsTheCoreMessageAndNoResult() {
        presenter.onOperationSelected(Operation.ADD, "7", "5");
        presenter.onCalculate("7", "5");
        ViewState state = presenter.onCalculate("", "5");
        assertEquals("", state.result());
        assertEquals(ErrorKind.EMPTY_OPERAND.message(), state.error());
    }

    @ParameterizedTest(name = "[{0}] / [{1}] gives \"{2}\"")
    @CsvSource(value = {"abc, 5, Invalid number", "7, 0, Cannot divide by zero", "'', '', Enter both numbers"})
    void aFailedCalculationShowsTheMatchingCoreMessage(String a, String b, String message) {
        presenter.onOperationSelected(Operation.DIVIDE, a, b);
        ViewState state = presenter.onCalculate(a, b);
        assertEquals("", state.result());
        assertEquals(message, state.error());
    }

    @Test
    void constructorsRejectNull() {
        assertThrows(NullPointerException.class, () -> new CalculatorPresenter(null));
    }

    @Test
    void calculateWithoutAnOperationReportsIt() {
        ViewState state = presenter.onCalculate("7", "5");
        assertEquals("", state.result());
        assertEquals(ErrorKind.NO_OPERATION.message(), state.error());
    }

    @Test
    void exactlyOneOfResultAndErrorIsSetAfterEveryCalculate() {
        String[][] inputs = {{"7", "5"}, {"", "5"}, {"abc", "1"}, {"7", "0"}, {"2", "3"}};
        presenter.onOperationSelected(Operation.DIVIDE, "", "");
        for (String[] in : inputs) {
            ViewState state = presenter.onCalculate(in[0], in[1]);
            assertTrue(state.result().isEmpty() != state.error().isEmpty(),
                    "result=[" + state.result() + "] error=[" + state.error() + "]");
        }
    }

    @Test
    void nfr02_a_calculation_through_the_presenter_takes_under_100_ms() {
        presenter.onOperationSelected(Operation.DIVIDE, "7", "3");
        presenter.onCalculate("7", "3"); // warm up
        long fastest = Long.MAX_VALUE; // best of five, so one scheduler or GC pause cannot fail the test
        for (int i = 0; i < 5; i++) {
            long start = System.nanoTime();
            presenter.onCalculate("7", "3");
            fastest = Math.min(fastest, System.nanoTime() - start);
        }
        long millis = fastest / 1_000_000;
        assertTrue(millis < 100, "fastest of five took " + millis + " ms");
    }

    private static void assertNoNonNumbers(ViewState state) {
        for (String text : new String[]{state.result(), state.error()}) {
            assertFalse(text.contains("Infinity") || text.contains("NaN"), "shown: " + text);
        }
    }

    @ParameterizedTest(name = "7 / {0}")
    @CsvSource({"0", "0.0", "0.000", "-0", "00", "-0.0"})
    void baseAc13_division_by_zero_shows_the_error_and_no_result(String zero) {
        presenter.onOperationSelected(Operation.ADD, "7", "5");
        assertEquals("12", presenter.onCalculate("7", "5").result()); // a real prior result
        presenter.onOperationSelected(Operation.DIVIDE, "7", zero);
        ViewState state = presenter.onCalculate("7", zero);
        assertEquals("", state.result());
        assertEquals("Cannot divide by zero", state.error());
        assertNoNonNumbers(state);
    }

    @Test
    void baseAc13_zero_divided_by_zero_is_a_division_by_zero_error() {
        presenter.onOperationSelected(Operation.DIVIDE, "0", "0");
        ViewState state = presenter.onCalculate("0", "0");
        assertEquals("", state.result());
        assertEquals("Cannot divide by zero", state.error());
    }

    @ParameterizedTest(name = "{0} {1} {2} = {3}")
    @CsvSource({
            "0, DIVIDE,   5,            0",
            "0, DIVIDE,   -5,           0",
            "1, DIVIDE,   3,            0.3333333333",
            "1, DIVIDE,   100000000000, 0",
            "7, MULTIPLY, 0,            0",
            "0, MULTIPLY, -7,           0",
            "0, SUBTRACT, 0,            0",
            "0, ADD,      0,            0",
            "-0, ADD,     0,            0"
    })
    void baseAc18_zero_operands_that_are_valid(String a, Operation op, String b, String expected) {
        presenter.onOperationSelected(op, a, b);
        ViewState state = presenter.onCalculate(a, b);
        assertEquals(expected, state.result());
        assertEquals("", state.error());
        assertNoNonNumbers(state);
    }

    @ParameterizedTest(name = "recover from [{0}] [{1}] with {2} to get {5}")
    @CsvSource(delimiter = '|', value = {
            "'' | 5  | ADD | 2   | 5 | 7",
            "7  | '' | ADD | 7   | 2 | 9",
            "'' | '' | ADD | 1   | 2 | 3",
            "1,5 | 5 | ADD | 1.5 | 5 | 6.5"
    })
    void baseAc14_each_input_error_recovers_to_a_correct_result(String badA, String badB, Operation op,
                                                                String goodA, String goodB, String expected) {
        presenter.onOperationSelected(op, badA, badB);
        ViewState failed = presenter.onCalculate(badA, badB);
        assertTrue(failed.result().isEmpty() && !failed.error().isEmpty(), "expected an error");
        ViewState fixed = presenter.onCalculate(goodA, goodB);
        assertEquals(expected, fixed.result());
        assertEquals("", fixed.error());
    }

    @Test
    void baseAc14_division_by_zero_recovers_by_fixing_the_divisor_or_changing_the_operation() {
        presenter.onOperationSelected(Operation.DIVIDE, "7", "0");
        assertEquals(ErrorKind.DIVIDE_BY_ZERO.message(), presenter.onCalculate("7", "0").error());
        ViewState fixed = presenter.onCalculate("7", "2");
        assertEquals("3.5", fixed.result());
        assertEquals("", fixed.error());

        presenter.onCalculate("7", "0"); // the error is back, DIVIDE is still selected
        presenter.onOperationSelected(Operation.ADD, "7", "0");
        ViewState viaOperation = presenter.onCalculate("7", "0");
        assertEquals("7", viaOperation.result());
        assertEquals("", viaOperation.error());
    }

    @ParameterizedTest(name = "error [{0}] / [{1}] stays after selecting an operation")
    @CsvSource({"'', 5", "abc, 5"})
    void anErrorStaysAfterSelectingAnOperationUntilTheNextCalculate(String a, String b) {
        presenter.onOperationSelected(Operation.ADD, a, b);
        String error = presenter.onCalculate(a, b).error();
        assertFalse(error.isEmpty());
        ViewState afterSelect = presenter.onOperationSelected(Operation.MULTIPLY, a, b);
        assertEquals(error, afterSelect.error());
        assertEquals("", afterSelect.result());
    }

    @Test
    void aDivisionByZeroErrorStaysAfterSelectingAnotherOperationUntilTheNextCalculate() {
        presenter.onOperationSelected(Operation.DIVIDE, "7", "0");
        presenter.onCalculate("7", "0");
        ViewState afterSelect = presenter.onOperationSelected(Operation.ADD, "7", "0");
        assertEquals(ErrorKind.DIVIDE_BY_ZERO.message(), afterSelect.error());
    }

    @Test
    void baseAc14_choosing_the_missing_operation_then_calculating_recovers() {
        assertEquals(ErrorKind.NO_OPERATION.message(), presenter.onCalculate("7", "5").error());
        ViewState afterSelect = presenter.onOperationSelected(Operation.ADD, "7", "5");
        assertEquals(ErrorKind.NO_OPERATION.message(), afterSelect.error()); // stays until Calculate
        ViewState fixed = presenter.onCalculate("7", "5");
        assertEquals("12", fixed.result());
        assertEquals("", fixed.error());
    }

    @Test
    void baseAc10_clear_after_a_result_returns_the_initial_state() {
        presenter.onOperationSelected(Operation.ADD, "7", "5");
        assertEquals("12", presenter.onCalculate("7", "5").result());
        ViewState state = presenter.onClear();
        assertEquals(ViewState.initial(), state);
        assertEquals(ViewState.initial(), presenter.state());
    }

    @ParameterizedTest(name = "clear after error from [{0}] [{1}]")
    @CsvSource(delimiter = '|', value = {"'' | 5", "abc | 5", "7 | 0"})
    void baseAc10_clear_after_each_error_removes_the_message(String a, String b) {
        presenter.onOperationSelected(Operation.DIVIDE, a, b);
        assertFalse(presenter.onCalculate(a, b).error().isEmpty());
        assertEquals(ViewState.initial(), presenter.onClear());
    }

    @Test
    void baseAc10_clear_after_a_missing_operation_error_and_from_the_initial_state() {
        presenter.onCalculate("7", "5");
        assertEquals(ErrorKind.NO_OPERATION.message(), presenter.state().error());
        assertEquals(ViewState.initial(), presenter.onClear());
        assertEquals(ViewState.initial(), presenter.onClear());
    }

    @Test
    void afterClearTheSelectedOperationIsGoneAndTheNextCalculationWorksNormally() {
        presenter.onOperationSelected(Operation.ADD, "7", "5");
        presenter.onCalculate("7", "5");
        presenter.onClear();
        assertEquals(ErrorKind.NO_OPERATION.message(), presenter.onCalculate("7", "5").error());
        presenter.onOperationSelected(Operation.MULTIPLY, "2", "3");
        assertEquals("6", presenter.onCalculate("2", "3").result());
    }

    private record Step(String a, String b, String result, ErrorKind error) {
    }

    @Test
    void aFailingCalculateClearsThePreviousResultAndLabelsAlwaysMatchTheLatestCalculate() {
        presenter.onOperationSelected(Operation.DIVIDE, "", "");
        Step[] steps = {
                new Step("7", "2", "3.5", null),
                new Step("", "5", "", ErrorKind.EMPTY_OPERAND),
                new Step("abc", "1", "", ErrorKind.INVALID_NUMBER),
                new Step("8", "2", "4", null),
                new Step("7", "0", "", ErrorKind.DIVIDE_BY_ZERO),
                new Step("", "", "", ErrorKind.EMPTY_OPERAND),
                new Step("9", "3", "3", null)
        };
        for (int i = 0; i < steps.length; i++) {
            ViewState state = presenter.onCalculate(steps[i].a(), steps[i].b());
            assertEquals(steps[i].result(), state.result(), "result at step " + i);
            String expectedError = steps[i].error() == null ? "" : steps[i].error().message();
            assertEquals(expectedError, state.error(), "error at step " + i);
        }
    }

    @Test
    void aMissingOperationAfterAResultClearsTheResult() {
        presenter.onOperationSelected(Operation.ADD, "7", "5");
        assertEquals("12", presenter.onCalculate("7", "5").result());
        CalculatorPresenter fresh = new CalculatorPresenter(new Calculator());
        fresh.onCalculate("7", "5");
        assertEquals(ErrorKind.NO_OPERATION.message(), fresh.state().error());
        assertEquals("", fresh.state().result());
    }
}
