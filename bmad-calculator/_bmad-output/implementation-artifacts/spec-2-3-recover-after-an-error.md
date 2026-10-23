---
title: 'Story 2.3: Recover after an error'
type: 'feature'
created: '2026-10-23'
status: 'done'
baseline_commit: '0ace55cb4fcecb14cc658d206c974c7460dc55f7'
route: 'dispatch'
review_loop_iteration: 0
story_key: '2-3-recover-after-an-error'
context:
  - '{project-root}/_bmad-output/implementation-artifacts/epic-2-context.md'
---

<frozen-after-approval reason="human-owned intent — do not modify unless human renegotiates">

## Intent

**Problem:** BASE-AC-14 (recover after an error and still get a correct result) and BASE-FR-08 must hold in the running application for every kind of error, and a failed calculation must not leave an old result behind.

**Approach:** Verify recovery through the presenter and the real window for all four error kinds, add missing tests, and fix any defect they expose. No new feature.

## Boundaries & Constraints

**Always:** A successful Calculate clears the error; a failed Calculate clears the previous result; after any error the user can correct a field, or select an operation, and Calculate again; selecting an operation alone does not clear a shown error until the next Calculate.

**Never:** Clear/reset behaviour (Story 3.1), new messages, dialogs, changes to `shared/`.

## I/O & Edge-Case Matrix

| Scenario | Input / State | Expected Output / Behavior | Error Handling |
|----------|--------------|---------------------------|----------------|
| Fix a blank field | Error "Enter both numbers", then fill the field, Calculate | Correct result; error cleared | N/A |
| Fix a malformed field | Error "Invalid number", then `1.5`, Calculate | Correct result; error cleared | N/A |
| Fix division by zero | Error "Cannot divide by zero", then divisor `2`, Calculate | Correct result; error cleared | N/A |
| Choose the missing operation | Error "Select an operation", select `+`, Calculate | Correct result; error cleared | N/A |
| Operation choice alone | Any error shown, then select an operation | Error still shown until Calculate | N/A |
| Error after result | Result shown, then a failing Calculate | Result empty; only the error shown | N/A |
| Repeated errors | Several different errors in a row | Each shows only its own message; no stale text | N/A |
| Alternate | error, result, error, result | Labels always match the latest Calculate | N/A |

</frozen-after-approval>

## Code Map

- `bmad-calculator/src/calculator/presenter/CalculatorPresenter.java` -- `onCalculate` replaces result and error together; change only if a test fails
- `bmad-calculator/tests/calculator/presenter/CalculatorPresenterTest.java` -- add recovery rows
- `bmad-calculator/tests/calculator/ui/CalculatorFrameInteractionTest.java` -- add recovery rows through the window

## Tasks & Acceptance

**Execution:**
- [x] `bmad-calculator/tests/calculator/presenter/CalculatorPresenterTest.java` -- add the recovery matrix rows -- BASE-AC-14
- [x] `bmad-calculator/tests/calculator/ui/CalculatorFrameInteractionTest.java` -- add recovery through the window for all four error kinds -- BASE-AC-14
- [x] `bmad-calculator/src/**` -- fix any defect the tests expose -- FR-10

**Acceptance Criteria:**
- Given a display, when each of the four errors is corrected and Calculate is clicked, then the correct result appears and the error label is empty.
- Given a result is shown, when a failing Calculate follows, then the result label is empty and only the error shows.
- Given a headless run, when `mvn test` runs, then window rows are reported as skipped, not passed.

## Implementation Notes

- Implemented directly by the main session. No `src` change: the new tests passed against the Epic 1 presenter.
- Added 3 presenter tests and 6 window tests covering the matrix: fixing a blank, malformed or zero-divisor input, choosing the missing operation (error stays until Calculate), failing after a result, and alternating outcomes.
- Verified 2026-10-23, Corretto 22: `mvn test` -> 147 run, 0 failed, 0 skipped with a display; after review patches 157 run, 0 failed (50 skipped headless). Log: `evidence/story-2-3-test-log.txt`.
- Same process deviation as Stories 2.1 and 2.2: no commits until Epic 3 completes; the review diff is against a file snapshot.

## Spec Change Log

## Review Triage Log

Layers: Blind Hunter (BH, 4 findings), Edge Case Hunter (EC, 8 edge cases + 2 claim checks), Verification Gap (VG, none). Reviewed 2026-10-23 at the same model capability as the session.

| # | Source | Finding | Verdict / route | Evidence |
|---|---|---|---|---|
| 1 | EC-1, EC-2, EC claim (medium) | "Error stays after selecting an operation" was verified for one error kind only | medium / patch | Matrix row says any error. Added presenter tests for blank, malformed and divide-by-zero, and a window test chaining all three |
| 2 | EC-3, EC claim (low) | Error-to-error transitions missing (empty, no operation) | low / patch | The alternating window test now runs success, empty, invalid, divide-by-zero twice, success; a second window runs no-operation then empty |
| 3 | BH-4, EC-7 | Presenter sequence test used a `!` prefix for errors and a short list | low / patch | Replaced by a `Step` record that uses `ErrorKind.message()`; sequence now includes divide-by-zero after a success and a double-blank |
| 4 | BH-2 | One presenter test ran three recoveries over hidden shared state | low / patch | Split: a parameterised test per input error (fresh presenter each) and a separate divide-by-zero recovery test |
| 5 | EC-4 | Only blank first operand was recovered in the window | low / patch | Added blank second operand and both blank |
| 6 | EC-5, EC-6 | Recovery after divide-by-zero by changing the operation, or with the Enter key, untested | low / patch | Window test now recovers by editing the divisor, by choosing `+`, and by pressing Enter; presenter test recovers via the operation |
| 7 | EC-8 | `withFrame` may wrap `TestAbortedException` | false | `assumeFalse` is outside the EDT body, as in the Story 1.3 triage |
| 8 | BH-1 | The rule "error stays until Calculate" pins behaviour nobody confirmed | low / rejected | It is the matrix row and the AD-6 assumption in the frozen spec; the open question is already recorded as F-09 for the team, not changed here |
| 9 | BH-3 | Tests mix `setText`+click with the `calculate` helper | low / rejected | Cosmetic; error-to-error coverage added under finding 2 |

During patching one new test failed because of a CSV fixture mistake in the test itself (escaped comma); fixed by using a `|` delimiter. No application defect was found.

No `intent_gap` or `bad_spec` findings, so no loopback (`review_loop_iteration` stays 0). No `defer` entries.

## Design Notes

Verification-led like Stories 2.1 and 2.2. `CalculatorPresenter.onCalculate` builds a fresh `ViewState` with exactly one of result and error, so recovery is structural; these tests prove it through the window.

## Verification

**Commands:**
- `export JAVA_HOME=$(/usr/libexec/java_home -v 22) && cd bmad-calculator && mvn test` -- expected: BUILD SUCCESS, 0 skipped with a display
