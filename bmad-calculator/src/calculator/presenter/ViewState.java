package calculator.presenter;

import calculator.core.Operation;

/**
 * Everything the view needs to render. Immutable.
 *
 * @param operandA  text of the first operand as last reported by the view
 * @param operandB  text of the second operand as last reported by the view
 * @param selected  the selected operation, or {@code null} when none is selected
 * @param result    result text, empty when there is none
 * @param error     error message, empty when there is none
 */
public record ViewState(String operandA, String operandB, Operation selected, String result, String error) {

    public static ViewState initial() {
        return new ViewState("", "", null, "", "");
    }
}
