# Data Model: Baseline Calculator

Date: 2026-10-27. Source: [spec.md](spec.md) (Key Entities, FR-009 to FR-016). Nothing is stored; these are in-memory values only.

## Entities

### Operand (input)

- **Fields**: `text` (what the user typed).
- **Classification** (applied in this order to the text after removing leading and trailing space characters):
  1. **Empty**: the remaining text has length zero (this includes a spaces-only field).
  2. **Valid**: matches `-?[0-9]+(\.[0-9]+)?`. Becomes a `BigDecimal`.
  3. **Invalid**: anything else (for example `abc`, `1.2.3`, `1,5`, `1e3`, `--1`, `.`, `+1`, `.5`, `5.`, `-`).
- **Source**: FR-009, FR-012; LOG-12 (agreed).

### Operation

- **Values**: `ADD` (+), `SUBTRACT` (−), `MULTIPLY` (×), `DIVIDE` (÷), or none selected (the initial state).
- **Source**: FR-003, FR-005.

### CalculationError

- **Values**: `EMPTY_OPERAND`, `NO_OPERATION`, `INVALID_NUMBER`, `DIVISION_BY_ZERO`.
- **Priority** (lower number is reported first): 1 `EMPTY_OPERAND`, 2 `NO_OPERATION`, 3 `INVALID_NUMBER`, 4 `DIVISION_BY_ZERO`.
- **Source**: FR-006, FR-007, FR-013.

### CalculationOutcome

- Exactly one of:
  - **Success** with `text`: the result as plain decimal text.
  - **Failure** with one `CalculationError`.
- A failure carries no result text, so a result and an error can never be shown together for the same calculation.
- **Source**: FR-006, NFR-003.

### Display state (GUI)

- **Fields**: `firstOperandText`, `secondOperandText`, `selectedOperation` (or none), `resultText` (or none), `errorMessage` (or none).
- **Initial state**: both operand texts empty, no operation, no result, no error.
- **Source**: FR-005, FR-016.

## Rules

| Rule | Definition | Source |
|---|---|---|
| Add, subtract, multiply | Exact `BigDecimal` result | FR-009 |
| Divide | Exact quotient rounded to scale 10 with `HALF_UP` (ties away from zero, LOG-15 agreed) | FR-010 |
| Division by zero | Divisor equal to zero (`0`, `0.0`, `-0`) gives `DIVISION_BY_ZERO`; no division is attempted | FR-007 |
| Result text | Remove trailing zeros, then plain notation (no exponent). Zero is `0` | FR-011 |
| Error priority | Report the highest-priority fault found; one message only | FR-013 |
| Error message | Each error maps to one inline message: `EMPTY_OPERAND` "Enter both numbers", `NO_OPERATION` "Select an operation", `INVALID_NUMBER` "Invalid number", `DIVISION_BY_ZERO` "Cannot divide by zero" (suggested wording, distinguishable) | FR-014 |

## State transitions (Display state)

| Event | Result |
|---|---|
| Edit an operand field | Only that field's text changes. Result and error stay (LOG-13, agreed). |
| Select an operation button | `selectedOperation` changes. Result and error stay. |
| Calculate (button, or Enter in an operand field) | Previous result and error are removed. The outcome is computed from the current fields and operation. On success `resultText` is set and `errorMessage` is none; on failure `errorMessage` is set and `resultText` is none. |
| Clear | Return to the initial state. |

There is no other state. The calculator keeps no history or memory.
