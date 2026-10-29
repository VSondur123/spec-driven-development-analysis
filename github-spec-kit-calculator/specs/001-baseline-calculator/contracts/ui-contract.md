# Contract: User interface

Package `calculator.ui`. One window containing one `CalculatorPanel`. The interaction pattern is the one frozen in `shared/baseline-requirements.md` §5 (LOG-01). Component names below are set with `setName(...)` so tests can find the components; they are not shown to the user.

## Components

| Name (test handle) | Visible label | Kind | Purpose | Requirement |
|---|---|---|---|---|
| `firstOperandField` | "First number" | text field | First operand | FR-002 |
| `secondOperandField` | "Second number" | text field | Second operand | FR-002 |
| `addButton` | "+" | toggle button | Select addition | FR-003 |
| `subtractButton` | "−" (U+2212) | toggle button | Select subtraction | FR-003 |
| `multiplyButton` | "×" | toggle button | Select multiplication | FR-003 |
| `divideButton` | "÷" | toggle button | Select division | FR-003 |
| `calculateButton` | "Calculate" | button | Request the calculation | FR-004 |
| `clearButton` | "Clear" | button | Return to the initial state | FR-005 |
| `resultLabel` | "Result:" label plus value text | label | Shows the result, empty when none | FR-004 |
| `errorLabel` | "Error:" label plus message text | label | Shows the inline error, empty when none | FR-014 |

The four operation buttons form one selection group: at most one is selected, and the selected one is visibly indicated. Visual style stays the toolkit default (FR-015, no unrequested styling).

## Behaviour

| Trigger | Behaviour | Requirement / check |
|---|---|---|
| Launch | Initial state: fields empty, no operation selected, result and error empty | FR-005 |
| Click an operation button | That button is selected and the others are not. Result and error are left as they are. | FR-003, LOG-13 |
| Edit a field | Text is displayed as typed. Result and error are left as they are. | FR-002, LOG-13, `BASE-AC-02`, `-03` |
| Click `calculateButton` | Calls `Calculator.calculate` with both field texts and the selected operation; clears previous result and error; shows the new result in `resultLabel` or the message in `errorLabel` | FR-004, FR-006, FR-007 |
| Press Enter in either field | Exactly the same as clicking `calculateButton` | FR-004, `BASE-AC-19` |
| Click `clearButton` | Both fields empty, no operation selected, result and error empty | FR-005, `BASE-AC-10` |
| Click `clearButton` (design choice DL-12, not a requirement) | Keyboard focus moves to `firstOperandField` | None. Not in the shared documents; no `BASE-AC-*` check depends on it |

## Messages (suggested wording, FR-014)

| Error | Text in `errorLabel` |
|---|---|
| `EMPTY_OPERAND` | Enter both numbers |
| `NO_OPERATION` | Select an operation |
| `INVALID_NUMBER` | Invalid number |
| `DIVISION_BY_ZERO` | Cannot divide by zero |

Each message differs from the others, as the acceptance checks require. Errors appear only in `errorLabel`. The application MUST NOT open dialogs, message boxes or pop-ups.

## Exceptions (design choice DL-13, not a requirement)

As a safeguard, an unexpected exception inside a calculation must not close the application. `BASE-NFR-03` already requires no crash for invalid input and arithmetic errors, and the core never throws for any text input, so this is defence in depth and not an expected path. No `BASE-AC-*` check depends on it.
