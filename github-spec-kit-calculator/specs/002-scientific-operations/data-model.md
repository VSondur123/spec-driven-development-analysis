# Data Model: Scientific Operations (CR-01)

Date: 2026-11-04. Source: [spec.md](spec.md) (Key Entities, FR-001 to FR-015). Only the differences from the frozen baseline model ([../001-baseline-calculator/data-model.md](../001-baseline-calculator/data-model.md)) are listed; everything else is unchanged. Nothing is stored.

## Changed entities

### Operation

- **Values**: `ADD` (+), `SUBTRACT` (−), `MULTIPLY` (×), `DIVIDE` (÷) as before, plus `SQUARE` (`x²`), `SQUARE_ROOT` (`√x`), `RECIPROCAL` (`1/x`), or none selected (the initial state).
- **New attribute**: arity. `SQUARE`, `SQUARE_ROOT` and `RECIPROCAL` are unary (need only the First number); the other four are binary (need both).
- **Source**: `CR-FR-01`, `CR-FR-02`, FR-001, FR-002.

### Operand

- Classification is unchanged (empty, valid, invalid, in that order, after removing surrounding spaces; grammar `-?[0-9]+(\.[0-9]+)?`).
- **Which operands are examined** depends on the operation:

| Operation selected | First number examined | Second number examined |
|---|---|---|
| Binary | yes | yes |
| Unary | yes | **no**: never parsed, never an error, never used (LOG-17) |
| None | yes | yes (baseline behaviour kept; arity unknown) |

### CalculationError

- **Values**: `EMPTY_OPERAND`, `NO_OPERATION`, `INVALID_NUMBER`, `DIVISION_BY_ZERO`, **`NEGATIVE_SQUARE_ROOT`** (new).
- **Priority** (lower number is reported first): 1 `EMPTY_OPERAND`, 2 `NO_OPERATION`, 3 `INVALID_NUMBER`, 4 `DIVISION_BY_ZERO`, 5 `NEGATIVE_SQUARE_ROOT`. The two domain errors cannot occur together because they belong to different operations.
- **Source**: `CR-FR-06`, `CR-FR-08`, `CR-FR-09`, LOG-18.

### CalculationOutcome

Unchanged: exactly one of a result text or one error.

### Display state (GUI)

Unchanged fields. The set of selectable operations is now seven. Initial state is unchanged: no operation selected, which includes the three new ones (`CR-AC-20`).

## Rules added

| Rule | Definition | Source |
|---|---|---|
| Square | Exact `x × x`; no scale, no rounding | `CR-FR-04`, CR-01 §4 item 5 |
| Square root | Input must not be negative. Result `R × 10^-10` where `R = (q + 1) >> 1` and `q` is the integer square root of `floor(4 × x × 10^20)`. This equals the root rounded once to 10 decimals `HALF_UP` (proof in research C-01). | `CR-FR-05`, CR-01 §4 items 6, 9 |
| Square root of a negative number | `NEGATIVE_SQUARE_ROOT`, no root is computed | `CR-FR-06` |
| Reciprocal | Zero input (`signum == 0`: `0`, `0.0`, `-0.0`, `-0`) gives `DIVISION_BY_ZERO`; otherwise `1 ÷ x` at scale 10, `HALF_UP` | `CR-FR-07`, `CR-FR-08`, CR-01 §4 item 7, §5 |
| Result text | Unchanged baseline formatting: no trailing zeros, no exponent, zero is `0` | FR-012 |
| Error priority | Empty operand, no operation, invalid number, domain error, applied to the examined operands only | FR-009, LOG-18 |
| Error message | `NEGATIVE_SQUARE_ROOT`: "Cannot take square root of a negative number". Reciprocal of zero shows the existing "Cannot divide by zero". | `CR-FR-06`, CR-01 §5 |
| Square root of `-0.0` or `-0` | **Not defined** (owner decision, DL-46) | None |

## State transitions

Unchanged. Selecting a unary operation changes only `selectedOperation`; the Second number field stays enabled and its text is left alone (LOG-20). Calculate, Clear and editing behave as in the baseline model.
