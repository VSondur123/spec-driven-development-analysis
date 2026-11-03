# Epic 4 Context: Scientific operations (CR-01)

<!-- Compiled from planning artifacts. Edit freely. Regenerate with compile-epic-context if planning docs change. -->

## Goal

Extend the finished baseline calculator with three unary operations on the First number: square `x²`, reciprocal `1/x` and square root `√x`. The change must keep every baseline behaviour (`BASE-AC-01..19`) and add the CR-01 checks (`CR-AC-01..22`), with the same error handling and recovery as the baseline.

## Stories

- Story 4.1: Unary operation support and Square
- Story 4.2: Reciprocal
- Story 4.3: Square root
- Story 4.4: CR-01 regression and acceptance evidence

## Requirements & Constraints

- Unary operations use only the First number field. The Second number may be empty, may hold any value, and is ignored even when malformed (LOG-17). The field stays visible and enabled (LOG-20).
- Square: exact decimal square, no rounding (`-3` gives `9`, `1.2` gives `1.44`, `0` gives `0`).
- Reciprocal: 10 decimals, HALF_UP (`4` gives `0.25`, `3` gives `0.3333333333`, `-4` gives `-0.25`). `0`, `0.0`, `-0.0` give "Cannot divide by zero". A tiny result may round to `0` (LOG-19).
- Square root: 10 decimals, HALF_UP, deterministic (`9` gives `3`, `2` gives `1.4142135624`, `0` gives `0`). Negative input gives "Cannot take square root of a negative number".
- Error priority on the required operands only: empty ("Enter both numbers", also for unary, LOG-21), no operation, invalid number, domain error (LOG-18). With no operation selected both operands are required, as in the baseline.
- A shown result or error stays until the next Calculate or Clear (LOG-13, LOG-20). Errors stay inline, never in dialogs.
- Results use plain decimal text, no trailing zeros, zero as `0`.

## Technical Decisions

- `Calculator.evaluate(a, op, b)` stays the only core entry and stays total (AD-2). `ErrorKind.message()` stays the only source of error text (AD-4); `NEGATIVE_SQRT` is added in Story 4.3, reciprocal of zero reuses `DIVIDE_BY_ZERO`.
- `Operation` carries arity (binary or unary). Adding an operation also changes `Calculator.evaluate` (amended AD-7).
- Square root uses exact integers: `r = floor(a * 10^20).sqrt()`, add one when `a * 10^20 >= r^2 + r + 1/4` (exact `BigDecimal` comparison), result `r * 10^-10`. No `double`, no `BigDecimal.sqrt(MathContext)`.
- Control names `op-square`, `op-sqrt`, `op-reciprocal` (the existing `"op-" + name().toLowerCase()` rule). The presenter and `ViewState` are expected to stay unchanged.
- Tests name the check they verify (`crAc02_...`). `OperationTest` currently asserts exactly four operations and must be updated. GUI tests skip when headless, and display runs are recorded separately. Commands as in `BUILD.md` with `JAVA_HOME` pinned.

## UX & Interaction Patterns

- The operation row gets one toggle button per unary operation, in the same button group as the binary ones, so exactly one operation is selected at a time. Symbols: `x²`, `√x`, `1/x`.

## Cross-Story Dependencies

- Story 4.1 adds arity and the unary validation path used by 4.2 and 4.3. 4.4 needs 4.1 to 4.3 done.
