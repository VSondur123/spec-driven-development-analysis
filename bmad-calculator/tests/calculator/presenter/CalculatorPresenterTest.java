package calculator.presenter;

import calculator.core.Calculator;
import calculator.core.ErrorKind;
import calculator.core.Operation;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
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
}
