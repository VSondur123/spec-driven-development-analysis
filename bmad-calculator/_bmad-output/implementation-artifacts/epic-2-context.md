# Epic 2 Context: Safe Input Handling

<!-- Compiled from planning artifacts. Edit freely. Regenerate with compile-epic-context if planning docs change. -->

## Goal

Invalid, incomplete or impossible input never crashes the calculator or produces a misleading result, and the user can recover and carry on. The core and the GUI already report these cases (built in Epic 1 because the core must be total); this epic proves the behaviour end to end, closes test gaps, and fixes anything the verification finds.

## Stories

- Story 2.1: Reject empty, operation-less and malformed input
- Story 2.2: Safe division by zero
- Story 2.3: Recover after an error

## Requirements & Constraints

- A blank first or second operand shows "Enter both numbers"; no operation selected shows "Select an operation"; text outside the Valid Number grammar shows "Invalid number"; division by zero (`0`, `0.0`, `-0`) shows "Cannot divide by zero". Each case shows no result.
- Precedence when several faults exist: empty operand, then no operation, then invalid number, then divide by zero.
- Errors appear inline in the error label, never in a dialog.
- The app never crashes or exits on bad input, and never shows Infinity or NaN.
- After any error the user can correct a field, or select an operation, and Calculate again to get the right result; a successful Calculate clears the error, and a failed Calculate clears the previous result. (Clear is Epic 3.)
- `0 / 5` is valid and gives `0`.

## Technical Decisions

- `Calculator.evaluate` is total and owns the validation order; `ErrorKind.message()` is the only source of error text.
- The presenter maps each `Outcome` to a `ViewState` in which exactly one of result and error is non-empty after Calculate; the view only renders it.
- GUI tests drive real components by their stable names (`operandA`, `operandB`, `op-add`, `op-subtract`, `op-multiply`, `op-divide`, `calculate`, `result`, `error`) and are skipped when headless; a skip is not a pass, so display runs are recorded separately in `evidence/`.
- Test methods name the acceptance check they verify (`baseAc12_...`). Maven/JDK commands as in `BUILD.md`, with `JAVA_HOME` pinned.
- `core` and `presenter` must stay free of AWT/Swing.

## UX & Interaction Patterns

- Result label and error label are separate; empty ones show a single space to keep the layout stable (tests trim).
- Selecting an operation keeps the current result or error until the next Calculate.

## Cross-Story Dependencies

- Builds on Epic 1 (core, presenter, GUI). Story 2.3 depends on 2.1 and 2.2 behaviour.
- Clear and the full verification pass are Epic 3.
