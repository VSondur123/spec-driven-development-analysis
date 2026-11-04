# Contract: User interface (CR-01 additions)

Package `calculator.ui`. Only the differences from the frozen baseline contract ([../../001-baseline-calculator/contracts/ui-contract.md](../../001-baseline-calculator/contracts/ui-contract.md)) are listed. Component names are set with `setName(...)` for tests and are not shown to the user. The names below are this track's design choice; CR-01 specifies the labels only.

## New components

| Name (test handle) | Visible label | Kind | Purpose | Requirement |
|---|---|---|---|---|
| `squareButton` | `x²` | toggle button | Select square | `CR-FR-01`, FR-001 |
| `squareRootButton` | `√x` | toggle button | Select square root | `CR-FR-01`, FR-001 |
| `reciprocalButton` | `1/x` | toggle button | Select reciprocal | `CR-FR-01`, FR-001 |

The three buttons join the existing selection group, after `divideButton`. The group now has seven buttons: at most one is selected and the selected one is visibly indicated. The Second number field stays visible and enabled while any of them is selected (LOG-20). Visual style stays the toolkit default; the only visible layout change is a wider operations row.

## Behaviour changes

| Trigger | Behaviour | Requirement / check |
|---|---|---|
| Launch | Initial state unchanged: no operation selected, which includes the three new buttons | `CR-AC-01`, `CR-AC-20` |
| Click a new button | That button is selected and every other is not. Result and error stay (LOG-13). | `CR-FR-01`, LOG-20 |
| Click `calculateButton`, or Enter in either field, with a unary operation | Same path as for the basic operations: both field texts and the operation go to `Calculator.calculate`; the previous result and error are cleared first; the result or the inline message is shown | `CR-FR-03`, `CR-AC-02` to `CR-AC-13` |
| Click `clearButton` after a unary calculation | Both fields empty, no operation selected (none of the seven), result and error empty | `CR-AC-20` |

## Messages

| Error | Text in `errorLabel` |
|---|---|
| `EMPTY_OPERAND` | Enter both numbers (unchanged, kept for unary operations too, LOG-21) |
| `NO_OPERATION` | Select an operation |
| `INVALID_NUMBER` | Invalid number |
| `DIVISION_BY_ZERO` | Cannot divide by zero (also the message for reciprocal of zero) |
| `NEGATIVE_SQUARE_ROOT` | Cannot take square root of a negative number (mandated by CR-01 §5) |

Errors appear only in `errorLabel`. No dialogs, message boxes or pop-ups. The baseline design choices DL-12 (focus after Clear) and DL-13 (exception safeguard) stay as they are and remain "not a requirement".
