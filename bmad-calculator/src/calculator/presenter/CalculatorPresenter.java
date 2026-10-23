package calculator.presenter;

import calculator.core.Calculator;
import calculator.core.Operation;
import calculator.core.Outcome;

import java.util.Objects;

/**
 * Owns the interaction state. Every event carries the current field texts so the view never has to
 * overwrite what the user typed.
 */
public final class CalculatorPresenter {

    private final Calculator calculator;
    private ViewState state = ViewState.initial();

    public CalculatorPresenter(Calculator calculator) {
        this.calculator = Objects.requireNonNull(calculator, "calculator");
    }

    public ViewState state() {
        return state;
    }

    /** Selecting an operation replaces the previous selection and keeps any result or error shown. */
    public ViewState onOperationSelected(Operation operation, String operandA, String operandB) {
        state = new ViewState(operandA, operandB, operation, state.result(), state.error());
        return state;
    }

    /** Calculates with the current texts and the selected operation; exactly one of result or error is set. */
    public ViewState onCalculate(String operandA, String operandB) {
        Outcome outcome = calculator.evaluate(operandA, state.selected(), operandB);
        state = switch (outcome) {
            case Outcome.Success success ->
                    new ViewState(operandA, operandB, state.selected(), success.text(), "");
            case Outcome.Failure failure ->
                    new ViewState(operandA, operandB, state.selected(), "", failure.kind().message());
        };
        return state;
    }
}
