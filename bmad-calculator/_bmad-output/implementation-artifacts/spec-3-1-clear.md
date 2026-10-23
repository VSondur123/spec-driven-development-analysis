---
title: 'Story 3.1: Clear'
type: 'feature'
created: '2026-10-23'
status: 'done'
baseline_commit: '0ace55cb4fcecb14cc658d206c974c7460dc55f7'
route: 'dispatch'
review_loop_iteration: 0
story_key: '3-1-clear'
context:
  - '{project-root}/_bmad-output/implementation-artifacts/epic-3-context.md'
---

<frozen-after-approval reason="human-owned intent — do not modify unless human renegotiates">

## Intent

**Problem:** There is no way to reset the calculator. BASE-FR-05 and BASE-AC-10 are unmet: after entering values and getting a result or an error, the user cannot return to a clean initial state without restarting.

**Approach:** Add `onClear()` to the presenter, returning `ViewState.initial()`, and a `Clear` button (`clear`) to the window that renders it, including emptying both operand fields and clearing the operation selection.

## Boundaries & Constraints

**Always:** Clear resets both operand fields, the selected operation, the result and the error; it works from every state (initial, result, each error, operation selected); the view sets operand text from state only on Clear; the button is labelled "Clear" and named `clear`; `presenter` stays free of AWT/Swing; after Clear the user can enter and calculate normally; Enter in a field still means Calculate, not Clear.

**Never:** Confirmation dialogs; a keyboard shortcut for Clear; keeping any previous state after Clear; changes to `core`; changes to `shared/`.

## I/O & Edge-Case Matrix

| Scenario | Input / State | Expected Output / Behavior | Error Handling |
|----------|--------------|---------------------------|----------------|
| Clear after a result | `7 + 5` shows `12`, click Clear | Fields empty, no operation selected, result and error empty | N/A |
| Clear after each error | Error "Enter both numbers", "Select an operation", "Invalid number" or "Cannot divide by zero", click Clear | Same initial state; no message left | N/A |
| Clear with only typing | Fields typed, nothing calculated, click Clear | Fields empty, operation unselected | N/A |
| Clear from the initial state | Nothing entered, click Clear | Unchanged initial state; no error | N/A |
| Use after Clear | Clear, then `2 × 3` and Calculate | Result `6`, no stale text | N/A |
| Operation after Clear | Clear, then Calculate without selecting an operation | "Select an operation" (the old selection is gone) | Inline |
| Repeated Clear | Click Clear twice | Still the initial state | N/A |

</frozen-after-approval>

## Code Map

- `bmad-calculator/src/calculator/presenter/CalculatorPresenter.java` -- add `onClear()`
- `bmad-calculator/src/calculator/ui/CalculatorFrame.java` -- add the `clear` button, wire it, and render operand text from `ViewState` on Clear
- `bmad-calculator/tests/calculator/presenter/CalculatorPresenterTest.java` -- add Clear rows
- `bmad-calculator/tests/calculator/ui/CalculatorFrameInteractionTest.java` -- add Clear rows through the window; add `clear` to the named-controls test

## Tasks & Acceptance

**Execution:**
- [x] `bmad-calculator/src/calculator/presenter/CalculatorPresenter.java` -- add `onClear()` returning the initial state -- FR-6, AD-6
- [x] `bmad-calculator/src/calculator/ui/CalculatorFrame.java` -- add Clear button, listener, and render operand fields from the state on Clear -- FR-6, AD-9
- [x] `bmad-calculator/tests/calculator/presenter/CalculatorPresenterTest.java` -- presenter Clear tests -- BASE-AC-10
- [x] `bmad-calculator/tests/calculator/ui/CalculatorFrameInteractionTest.java` -- window Clear tests -- BASE-AC-10

**Acceptance Criteria:**
- Given operands, a selected operation and a result or error are present, when Clear is clicked, then both fields are empty, no operation button is selected, and the result and error labels are empty.
- Given Clear was clicked, when the user enters `2`, `3`, selects `×` and calculates, then the result is `6`.
- Given a headless run, when `mvn test` runs, then presenter tests pass and window tests are skipped and reported as skipped.

## Implementation Notes

- `CalculatorPresenter.onClear()` returns `ViewState.initial()`. `CalculatorFrame` gains a `clear` button (in an `actions` panel beside Calculate); `clear()` writes the operand fields from the state, then `render(state)`. Clear is the only event that writes the fields from state.
- Tests: 4 presenter rows, 5 window rows (one parameterized x4 errors), `clear` added to the named-controls test. Display run: 170 run, 0 failed, 0 skipped. Headless: 57 skipped (GUI tests), 0 failed. Log: `evidence/story-3-1-test-log.txt`.
- Incident: `core/package-info.java` and `presenter/package-info.java` were found missing from disk (staged as deleted) during review. Cause not traced to a Story 3.1 edit; restored from the pre-story snapshot (identical to HEAD). Forbidden-import grep: no matches.

## Spec Change Log

## Review Triage Log

Three layers run (Blind Hunter 12 items, Edge Case Hunter 12, Verification Gap 8).

| Finding | Layer | Decision |
|---|---|---|
| `package-info.java` deletions in core/presenter | Blind, Verification Gap | **Fixed**: files restored (see Implementation Notes); not a Clear change. |
| `enterStillCalculatesAndNeverClears` asserts only operandA | all three | **Fixed**: now also asserts operandB, `op-add` still selected, empty error. |
| Presenter result test has no precondition | Blind | **Fixed**: asserts the result `12` before Clear. |
| Return focus to operandA after Clear | Blind, Edge | Rejected: not in spec; no unrequested features. |
| Enter on a focused Clear button might clear / want `setDefaultButton` | Edge | Rejected for this story: spec says Enter in a field means Calculate (tested); button focus behaviour is Look-and-Feel default. Noted as a possible team question. |
| Blank placeholder `" "` vs `""` in assertions | Blind | Rejected: `label()` helper trims by design since Story 1.3; not a Clear issue. |
| Core changed / `core` constraint | Verification Gap | Rejected: false alarm from the package-info incident; `core` has no diff. |
| Window tests per operation, repeat Clear after result/error, Clear from initial in window, Enter on operandB | Verification Gap, Edge | Rejected as low: `onClear` is stateless and covered at presenter level; same code path. |
| No shortcut / dialog tests | Verification Gap | Covered: `NoDialogsTest` scans for dialogs; no key binding exists in code. |
| Stale visuals, undo history, off-EDT calls, state divergence | Edge | Rejected: not applicable or low. |

## Design Notes

Story 1.3 chose that every presenter event carries the field texts so the view never overwrites typing. Clear is the one event that does not need texts: it returns `ViewState.initial()` and the view writes the (empty) operand text from it. `render` therefore gains an optional operand-update; the simplest form is a separate `renderAll(state)` used only by Clear, leaving `render(state)` untouched.

## Verification

**Commands:**
- `export JAVA_HOME=$(/usr/libexec/java_home -v 22) && cd bmad-calculator && mvn test` -- expected: BUILD SUCCESS, 0 skipped with a display
- `grep -rnE "java\.awt|javax\.swing" bmad-calculator/src/calculator/presenter bmad-calculator/src/calculator/core` -- expected: no matches

- 2026-10-23 (after Epic 3): Varun asked to remove both `package-info.java` files as redundant now that `ArchitectureRulesTest` enforces the layering. Removed; 172 tests still pass.
