# Contract: Calculation core

Package `calculator.core`. The GUI-free part of the application; the only place where numeric and error rules are implemented. Rules are defined in [../data-model.md](../data-model.md); this contract fixes the interface that tests and the GUI rely on.

## Interface

```text
Calculator.calculate(String first, String second, Operation operation) -> CalculationOutcome
```

- `first`, `second`: the raw text of the two operand fields (never null; an empty field is the empty string).
- `operation`: one of `ADD`, `SUBTRACT`, `MULTIPLY`, `DIVIDE`, or `null` when none is selected.
- Never throws for any text input or `operation` value listed above.
- Returns a `CalculationOutcome`: a success with plain decimal result text, or a failure with one `CalculationError`.

## Behaviour table

Inputs are `first`, `second`, `operation`. Source check IDs refer to `shared/acceptance-criteria.md`.

| Inputs | Outcome | Check |
|---|---|---|
| `7`, `5`, ADD | success `12` | `BASE-AC-04` |
| `7`, `5`, SUBTRACT | success `2` | `BASE-AC-05` |
| `7`, `5`, MULTIPLY | success `35` | `BASE-AC-06` |
| `7`, `2`, DIVIDE | success `3.5` | `BASE-AC-07` |
| `-7`, `2`, ADD | success `-5` | `BASE-AC-08` |
| `1.2`, `2.3`, ADD | success `3.5` | `BASE-AC-09` |
| `0`, `5`, DIVIDE | success `0` | `BASE-AC-18` |
| `1`, `3`, DIVIDE | success `0.3333333333` | `BASE-AC-18` |
| `2`, `2`, DIVIDE | success `1` | LOG-03 example |
| `  7 `, `5`, ADD | success `12` (surrounding spaces ignored) | Baseline §4 item 9 |
| empty, `5`, ADD (also `5`, empty; and both empty) | failure `EMPTY_OPERAND` | `BASE-AC-11` |
| spaces only, `5`, ADD | failure `EMPTY_OPERAND` | LOG-12 (agreed) |
| `7`, `5`, `null` | failure `NO_OPERATION` | `BASE-AC-17` |
| empty, `5`, `null` | failure `EMPTY_OPERAND` (outranks `NO_OPERATION`) | Baseline §4 item 10 |
| `abc`, `1.2.3`, `1,5`, `1e3`, `--1`, `.` as either operand, with a valid other operand and an operation | failure `INVALID_NUMBER` | `BASE-AC-12` |
| `+1`, `.5`, `5.`, `-` as an operand | failure `INVALID_NUMBER` | Baseline §4 item 9; LOG-12 (agreed) |
| `abc`, `5`, `null` | failure `NO_OPERATION` (outranks `INVALID_NUMBER`) | Baseline §4 item 10 |
| `abc`, `0`, DIVIDE | failure `INVALID_NUMBER` (outranks `DIVISION_BY_ZERO`) | Baseline §4 item 10 |
| `7`, `0`, DIVIDE | failure `DIVISION_BY_ZERO` | `BASE-AC-13` |
| `7`, `0.0`, DIVIDE | failure `DIVISION_BY_ZERO` | `BASE-AC-13` |
| `7`, `-0`, DIVIDE | failure `DIVISION_BY_ZERO` | research D-05, derived |
| `0`, `7`, SUBTRACT | success `-7` | Derived from the arithmetic rules |
| `0`, `-0`, ADD | success `0` (never `-0`) | FR-011 |

Rows derived only from the arithmetic rules are added as extra tests and do not change any requirement.

## Out of the contract

Message text, labels, layout and colours belong to the UI contract. Negative-tie rounding follows LOG-15 (agreed): `-1 ÷ 2048` gives `-0.0004882813`.
