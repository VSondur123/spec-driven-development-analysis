# Contract: Calculation core (CR-01 additions)

Package `calculator.core`. Only the differences from the frozen baseline contract ([../../001-baseline-calculator/contracts/calculation-contract.md](../../001-baseline-calculator/contracts/calculation-contract.md)) are listed. Rules are in [../data-model.md](../data-model.md); the baseline rows all stay valid.

## Interface

```text
Calculator.calculate(String first, String second, Operation operation) -> CalculationOutcome
```

Same signature. `operation` may now also be `SQUARE`, `SQUARE_ROOT` or `RECIPROCAL`. For these, `second` may be any text (empty, valid or malformed) and is ignored. Never throws.

## Behaviour table (new rows)

Inputs are `first`, `second`, `operation`. Check IDs are from `shared/acceptance-criteria.md`.

| Inputs | Outcome | Check |
|---|---|---|
| `-3`, empty, SQUARE | success `9` | `CR-AC-02`, `CR-AC-16` |
| `1.2`, empty, SQUARE | success `1.44` | `CR-AC-03` |
| `0`, empty, SQUARE | success `0` | `CR-AC-04` |
| `9`, empty, SQUARE_ROOT | success `3` | `CR-AC-05` |
| `2`, empty, SQUARE_ROOT | success `1.4142135624` | `CR-AC-06` |
| `0`, empty, SQUARE_ROOT | success `0` | `CR-AC-07` |
| `-1`, empty, SQUARE_ROOT | failure `NEGATIVE_SQUARE_ROOT` | `CR-AC-08` |
| `4`, empty, RECIPROCAL | success `0.25` | `CR-AC-09` |
| `3`, empty, RECIPROCAL | success `0.3333333333` | `CR-AC-10` |
| `-4`, empty, RECIPROCAL | success `-0.25` | `CR-AC-11` |
| `0`, empty, RECIPROCAL | failure `DIVISION_BY_ZERO` | `CR-AC-12` |
| `0.0` or `-0.0`, empty, RECIPROCAL | failure `DIVISION_BY_ZERO` | `CR-AC-13` |
| `5`, `7`, any unary operation | same outcome as with `second` empty | `CR-AC-17` |
| `5`, `abc`, any unary operation | same outcome as with `second` empty (no `INVALID_NUMBER`) | LOG-17 |
| empty, anything, any unary operation | failure `EMPTY_OPERAND` | `CR-AC-14` |
| `abc`, anything, any unary operation | failure `INVALID_NUMBER` | `CR-AC-15` |
| empty, `5`, `null` | failure `EMPTY_OPERAND` | baseline row, unchanged |
| `5`, empty, `null` | failure `EMPTY_OPERAND` | baseline row, unchanged (arity unknown, both fields examined) |
| `5`, `5`, `null` | failure `NO_OPERATION` | baseline row, unchanged |
| `2048`, empty, RECIPROCAL | success `0.0004882813` (tie rounds up) | research C-02 |
| `-2048`, empty, RECIPROCAL | success `-0.0004882813` (tie away from zero) | research C-02 |
| `30000000000`, empty, RECIPROCAL | success `0` | LOG-19 |
| `0.0000000000000000000025`, empty, SQUARE_ROOT | success `0.0000000001` (exact tie rounds up) | research C-01 |
| `0.0000000000000000000024999999999999999999`, empty, SQUARE_ROOT | success `0` (just below the tie) | research C-01 |
| `3` / `5` / `0.5`, empty, SQUARE_ROOT | success `1.7320508076` / `2.2360679775` / `0.7071067812` | research C-01 |
| `0.00000000001`, empty, SQUARE | success `0.0000000000000000000001` (1E-22, exact, plain notation) | `CR-FR-04` |

The four basic operations behave exactly as in the baseline contract (all baseline rows unchanged).

## Not in the contract

- Square root of `-0.0` and `-0`: not defined (owner decision, DL-46). No row, no test.
- Message text, labels and layout belong to the UI contract.
