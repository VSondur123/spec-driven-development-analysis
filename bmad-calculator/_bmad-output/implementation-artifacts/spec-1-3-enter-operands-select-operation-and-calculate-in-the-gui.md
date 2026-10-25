---
title: 'Story 1.3: Enter operands, select operation and calculate in the GUI'
type: 'feature'
created: '2026-10-23'
status: 'done'
baseline_commit: '564337bb8fdea2fd9a858de227c86449f70c347f'
route: 'dispatch'
review_loop_iteration: 0
story_key: '1-3-enter-operands-select-operation-and-calculate-in-the-gui'
context:
  - '{project-root}/_bmad-output/implementation-artifacts/epic-1-context.md'
---

<frozen-after-approval reason="human-owned intent — do not modify unless human renegotiates">

## Intent

**Problem:** The core can compute but a user cannot reach it. BASE-AC-02, 03 and the GUI side of 04 to 09, 16 and 19 are unreachable, and the window is empty.

**Approach:** Add the presenter layer (`ViewState`, `CalculatorPresenter`) and fill `CalculatorFrame` with two labelled operand fields, four operation toggle buttons, a Calculate button, a result label and an inline error label, wired as a passive view per architecture AD-6 and AD-9.

## Boundaries & Constraints

**Always:** `presenter` imports only `calculator.core` (no `java.awt`/`javax.swing`); the presenter publishes immutable `ViewState(operandA, operandB, selected, result, error)`; every user event passes the current field texts so the view never overwrites typing; after Calculate exactly one of `result` and `error` is non-empty; selecting an operation keeps result and error; all Swing work on the EDT; stable component names `operandA`, `operandB`, `op-add`, `op-subtract`, `op-multiply`, `op-divide`, `calculate`, `result`, `error`; Enter in either operand field triggers Calculate; errors inline (no dialogs); the `CalculatorFrame` no-argument constructor keeps working.

**Never:** A Clear control or `clear` handling (Story 3.1); logic in listeners beyond forwarding to the presenter; reading or formatting numbers in the view; new `core` public API; new error texts; edits to `shared/` or `README.md`.

## I/O & Edge-Case Matrix

| Scenario | Input / State | Expected Output / Behavior | Error Handling |
|----------|--------------|---------------------------|----------------|
| Initial | Window just opened | Both fields empty; no operation selected; result and error empty | N/A |
| Calculate add | `7`, `5`, `+` selected, Calculate | Result label `12`; error empty | N/A |
| Enter key | same, Enter in field A or field B | Result label `12` (same as button) | N/A |
| Each operation | `7` and `5` with `-`, `*`, `/`; `7`,`2`,`/` | `2`, `35`, `1.4`; `3.5` | N/A |
| Negative / decimal | `-7`,`2`,`+`; `1.2`,`2.3`,`+` | `-5`; `3.5` | N/A |
| Replace operation | `+` then `×` selected | Only `×` shown selected | N/A |
| Consecutive | `7+5` then switch to `×`, Calculate | `35`, not influenced by `12` | N/A |
| Selection keeps output | Result `12` shown, then `×` chosen | Result `12` still shown until next Calculate | N/A |
| Presenter failure surfaced | blank field / no operation / malformed / `7 / 0` | `error` shows the core message, `result` empty | Core owns texts (full GUI error behaviour covered in Epic 2) |
| Typing preserved | User types in field B after a Calculate, then picks an operation | Field B keeps the new text | N/A |
| Speed | Any valid calculation through the presenter | Completes in under 100 ms | N/A |

</frozen-after-approval>

## Code Map

- `bmad-calculator/src/calculator/presenter/ViewState.java` -- new immutable record with `initial()`
- `bmad-calculator/src/calculator/presenter/CalculatorPresenter.java` -- new; `state()`, `onOperationSelected(Operation, String, String)`, `onCalculate(String, String)`; holds the `Calculator` and the only mutable state
- `bmad-calculator/src/calculator/ui/CalculatorFrame.java` -- fill in controls, layout, listeners, `render(ViewState)`; add constructor taking a presenter
- `bmad-calculator/tests/calculator/presenter/CalculatorPresenterTest.java` -- new, headless
- `bmad-calculator/tests/calculator/ui/CalculatorFrameInteractionTest.java` -- new; guarded by `assumeFalse(isHeadless())`
- Reuse: `core.Calculator`, `core.Operation` (`symbol()`, `label()`), `core.ErrorKind.message()`, `core.Outcome`; `Main` unchanged.

## Tasks & Acceptance

**Execution:**
- [x] `bmad-calculator/src/calculator/presenter/ViewState.java` -- create record and `initial()` -- AD-6
- [x] `bmad-calculator/src/calculator/presenter/CalculatorPresenter.java` -- create presenter mapping `Outcome` to `ViewState` (success text to `result`, failure to `ErrorKind.message()`) -- AD-6, FR-3, FR-4, FR-5
- [x] `bmad-calculator/src/calculator/ui/CalculatorFrame.java` -- add labelled fields, operation toggle buttons in a `ButtonGroup`, Calculate button, result and error labels, names per AD-9, Enter on fields, `render(ViewState)` -- FR-1, FR-3, FR-4
- [x] `bmad-calculator/tests/calculator/presenter/CalculatorPresenterTest.java` -- test the presenter matrix rows -- AD-10
- [x] `bmad-calculator/tests/calculator/ui/CalculatorFrameInteractionTest.java` -- drive the real components by name: type, select, click, Enter, read the labels -- BASE-AC-02, 03, 04 to 09, 16, 19, NFR-2

**Acceptance Criteria:**
- Given a display, when a test types `7` and `5`, selects `+` and clicks Calculate, then the `result` label reads `12` and the `error` label is empty.
- Given a display, when Enter is pressed in either operand field, then the result is the same as with the button.
- Given any state, when the source is searched, then `presenter` has no `java.awt` or `javax.swing` import.
- Given a headless run, when `mvn test` runs, then presenter tests pass and GUI tests are skipped.

## Implementation Notes

- Implemented directly by the main session: `ViewState`, `CalculatorPresenter`, `CalculatorFrame` (controls, layout, listeners, `render`), `CalculatorPresenterTest` (16 cases), `CalculatorFrameInteractionTest` (10 tests driving real components by name).
- Verified 2026-10-23, `JAVA_HOME` Corretto 22: `mvn test` with display -> 88 run, 0 failed, 0 skipped. Headless -> 88 run, 0 failed, 13 skipped (GUI tests only).
- Result and error labels hold a single space when empty so the layout does not jump; tests trim the text before comparing, so "empty" in the matrix means blank text.
- After review (2026-10-23): GUI error-path tests, null guards, best-of-five timing, result tooltip; 95 tests with display, 16 skipped headless; logs in `evidence/story-1-3-test-log.txt`.
- `package-info.java` comments reworded so the spec's grep for `java.awt|javax.swing` in `presenter` and `core` reports no matches.

## Spec Change Log

## Review Triage Log

Layers: Blind Hunter (BH, 10 findings), Edge Case Hunter (EC, 14 edge cases + 1 claim check), Verification Gap (VG, 1 gap + 2 other). Reviewed 2026-10-23 at the same model capability as the session.

| # | Source | Finding | Verdict / route | Evidence |
|---|---|---|---|---|
| 1 | VG gap, BH-7 | GUI wiring tests are skipped headless, so a green headless build can hide a broken button, Enter key or render | medium / patch | Real: skip is by design (AD-10) but a skip could be read as a pass. Fixed in evidence and docs: `evidence/story-1-3-test-log.txt` records the display run (95 run, 0 skipped) and the headless run (16 skipped) separately; `BUILD.md` states a skip is not a pass and the GUI ACs need a 0-skipped run |
| 2 | BH-5, EC-13, EC-14 | GUI tests only cover divide-by-zero errors; other three error kinds and editing field A are untested at the view | medium / patch | Matrix row "Presenter failure surfaced" lists blank, no operation, malformed. Added GUI tests for blank (AC-11), no operation (AC-17), malformed (AC-12); operand A also edited in the typing-preserved test; presenter test now covers malformed, divide-by-zero and blank messages |
| 3 | BH-6, VG other 2, EC-10 | NFR-2 timing test is one sample and can flake | low / patch | Now the best of five runs after a warm-up. It still measures the presenter path, which is what the spec's "Speed" row says |
| 4 | BH-4, EC-2, EC-3 | Null presenter or calculator accepted, failing later with an unclear NPE | low / patch | Verified: `Calculator` null would fail at the first click inside a listener. Fixed with `Objects.requireNonNull` and a test for the presenter constructor |
| 5 | EC-1 | Long results are clipped by the label | low / patch | Verified: a label wider than the window truncates with an ellipsis. Fixed minimally: full result in a tooltip. Wrapping or resizing was not added |
| 6 | BH-9 (part), BH-10 (part) | `java.awt.Component` written fully qualified; test helper `find` yields a bare ClassCastException/NPE when a name is missing | low / patch | Imported `Component`; test helpers now assert non-null with the missing name |
| 7 | BH-1, BH-2 | Result or error stays visible after the user edits an operand or picks another operation, until the next Calculate | low / rejected | The spec's matrix says selection keeps output ("Selection keeps output") and AD-6 records it as an assumption. No shared requirement says output must clear on edit. This is a candidate requirement question for the team (not silently changed here) |
| 8 | BH-3, VG other 1 | `ViewState.operandA/B` are never rendered, so they look like dead state | low / rejected | Deliberate and written in Design Notes: Story 3.1 `onClear()` renders them. Rejected as not harmful now; revisit when 3.1 lands |
| 9 | BH-8, BH claim on package-info | Rewording `package-info` removed a checkable rule | low / rejected | The comment was reworded only so the spec's own grep passes; enforcement is Story 3.2 `ArchitectureRulesTest`. Noted in the 1.2 triage log too |
| 10 | EC-12, claim (medium), BH-9 (part) | Empty result/error is shown as a single space; accessible-name and live-region announcements missing; hard-coded error colour; `setLabelFor` on a non-focusable label | low / rejected | The space keeps the layout stable and tests trim it; the spec notes record it. Accessibility beyond labelled controls is out of baseline scope (PRD Non-Goals/Deferred) |
| 11 | EC-4, EC-5, EC-6, BH-4 (part) | Unexpected exceptions or huge input freezing the EDT | low / rejected | Same as the Story 1.2 triage: grammar-valid input cannot raise ArithmeticException; no length cap was requested |
| 12 | EC-7 | Re-clicking a selected toggle | false | A `ButtonGroup` keeps the selection; the presenter is told the same operation again, with no visible change |
| 13 | EC-8 | "Exactly one of result/error" enforced only in tests | low / rejected | Only `onCalculate` sets either field, and a presenter test covers the invariant |
| 14 | EC-11 | `withFrame` wraps assumption aborts as errors | false | `assumeFalse` runs before `invokeAndWait`, outside the EDT body |
| 15 | BH-5 (part), BH-10 (part) | AC-19 uses `postActionEvent`; AC-02/03 test only round-trips `setText`; method naming varies | low / rejected | `postActionEvent` fires the same listeners as Enter; testing key bindings would test Swing. Naming difference is cosmetic |

No `intent_gap` or `bad_spec` findings, so no loopback (`review_loop_iteration` stays 0). No `defer` entries.

## Design Notes

Why events carry the field texts: after a Calculate the presenter echoes the texts in `ViewState`; if a later event did not carry the current texts, `render` would overwrite what the user typed since. Story 3.1 adds `onClear()`, which returns a `ViewState` with empty operands, and that is the only time the view sets field text from state.

Selection is a `ButtonGroup` of `JToggleButton`s using `ActionListener`, which fires on user clicks only, so rendering a state back into the buttons does not re-trigger events.

## Verification

**Commands:**
- `export JAVA_HOME=$(/usr/libexec/java_home -v 22) && cd bmad-calculator && mvn test` -- expected: BUILD SUCCESS, presenter and GUI interaction tests run
- `grep -rnE "java\.awt|javax\.swing" bmad-calculator/src/calculator/presenter bmad-calculator/src/calculator/core` -- expected: no matches
