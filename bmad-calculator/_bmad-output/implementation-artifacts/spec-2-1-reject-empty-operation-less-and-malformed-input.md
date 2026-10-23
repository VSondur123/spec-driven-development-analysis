---
title: 'Story 2.1: Reject empty, operation-less and malformed input'
type: 'feature'
created: '2026-10-23'
status: 'done'
baseline_commit: '0ace55cb4fcecb14cc658d206c974c7460dc55f7'
route: 'dispatch'
review_loop_iteration: 0
story_key: '2-1-reject-empty-operation-less-and-malformed-input'
context:
  - '{project-root}/_bmad-output/implementation-artifacts/epic-2-context.md'
---

<frozen-after-approval reason="human-owned intent — do not modify unless human renegotiates">

## Intent

**Problem:** BASE-AC-11 and BASE-AC-12 (and BASE-AC-17) must hold for the real application, not only the core. Epic 1 already built the handling; what is missing is end-to-end proof for every malformed example, the error precedence in the GUI, and a guard that errors never become dialogs.

**Approach:** Verify the implemented behaviour through the real window, add the missing tests, and fix any defect they expose. No new feature or message.

## Boundaries & Constraints

**Always:** Messages come only from `ErrorKind.message()`; errors are inline labels; precedence is empty operand, then no operation, then invalid number; no result is shown with an error; no exception reaches the user.

**Never:** New error texts, dialogs (`JOptionPane`, `JDialog`) or `System.exit` on error; changes to `shared/`; Clear (Epic 3); changing the Valid Number grammar.

## I/O & Edge-Case Matrix

| Scenario | Input / State | Expected Output / Behavior | Error Handling |
|----------|--------------|---------------------------|----------------|
| Blank A | A empty, B `5`, `+` | Error "Enter both numbers", result empty | Inline |
| Blank B | A `7`, B `   `, `+` | Error "Enter both numbers" | Inline |
| Both blank | both empty, `+` | Error "Enter both numbers" | Inline |
| No operation | `7`, `5`, none selected | Error "Select an operation" | Inline |
| Malformed | A or B is `abc`, `1.2.3`, `1,5`, `1e3`, `--1`, `.`, `+1` with `+` | Error "Invalid number", result empty | Inline |
| Precedence 1 | A empty, no operation, B `abc` | "Enter both numbers" | Inline |
| Precedence 2 | A `abc`, no operation, B `5` | "Select an operation" | Inline |
| Valid after error | any error, then valid inputs and Calculate | Result shown, error cleared | N/A |
| No dialogs | any error | No dialog opens; no `JOptionPane`/`JDialog` in `src` | N/A |

</frozen-after-approval>

## Code Map

- `bmad-calculator/src/calculator/core/Calculator.java`, `ErrorKind.java` -- already implement the matrix; change only if a test fails
- `bmad-calculator/src/calculator/presenter/CalculatorPresenter.java` -- already maps `Outcome` to `ViewState`
- `bmad-calculator/src/calculator/ui/CalculatorFrame.java` -- already renders `error`; no change expected
- `bmad-calculator/tests/calculator/ui/CalculatorFrameInteractionTest.java` -- extend with the malformed table and precedence rows
- `bmad-calculator/tests/calculator/ui/NoDialogsTest.java` -- new, headless: scans `src/calculator` for dialog classes and `System.exit` outside `EXIT_ON_CLOSE`

## Tasks & Acceptance

**Execution:**
- [x] `bmad-calculator/tests/calculator/ui/CalculatorFrameInteractionTest.java` -- add parameterised malformed-input test and both precedence rows through the real window -- BASE-AC-11, 12, 17
- [x] `bmad-calculator/tests/calculator/ui/NoDialogsTest.java` -- add the source scan -- AD-9, FR-7
- [x] `bmad-calculator/src/**` -- fix any defect the new tests expose -- FR-7

**Acceptance Criteria:**
- Given a display, when each malformed example is entered with an operation selected and Calculate is clicked, then the error label reads "Invalid number" and the result is empty.
- Given a display, when A is empty, no operation is selected and B is `abc`, then the error reads "Enter both numbers"; when A is `abc`, no operation and B `5`, then "Select an operation".
- Given the source, when scanned, then no dialog class is used.

## Implementation Notes

- Implemented directly by the main session. No `src` change was needed: the new tests passed against the Epic 1 implementation, so the planned "fix any defect" task found nothing.
- Added to `CalculatorFrameInteractionTest`: malformed table through the window (7 examples, both operands), blank second/both blank, error precedence, valid-after-error. Added `NoDialogsTest` (source scan for `JOptionPane`, `JDialog`, `System.exit`).
- Verified 2026-10-23, Corretto 22: `mvn test` -> 100 run, 0 failed, 0 skipped; after review patches 114 run, 0 failed, 0 skipped (34 skipped headless). Log: `evidence/story-2-1-test-log.txt`.
- Process deviation: the owner asked for no commits until Epic 3 is complete, so `baseline_commit` stays at the last commit (`0ace55c`) and this story's review diff is computed against a file snapshot taken at story start (`diff -ruN`), not against a commit.

## Spec Change Log

## Review Triage Log

Layers: Blind Hunter (BH, 12 findings), Edge Case Hunter (EC, 10 edge cases + 4 claim checks), Verification Gap (VG, none; two "other" notes). Reviewed 2026-10-23 at the same model capability as the session.

| # | Source | Finding | Verdict / route | Evidence |
|---|---|---|---|---|
| 1 | EC-1, EC-2, EC claim (medium), BH-4 | "No result with an error" asserted only on fresh frames, so it passed vacuously | medium / patch | Verified: no prior success existed in the malformed or blank tests. Fixed: each case first calculates `1+1`, asserts `2`, then asserts the result is empty after the error |
| 2 | EC-3, EC claim (high) | Malformed loop is one `@Test`; spec said parameterised; first failure hides the rest | medium / patch | Now `@ParameterizedTest` over 11 inputs, each with a fresh window |
| 3 | EC-4, BH-10 | Whitespace-only and tab first operand untested | low / patch | Added `""`, `"   "`, `"\t"` for the first operand |
| 4 | EC-5, BH-1 | Grammar boundaries missing in the window test (`-`, `1.`, `.5`, `1 2`) | low / patch | Added to the parameter list. Accepting valid values (`-1`, `0.5`) is covered in `CalculatorTest`, so not repeated in the GUI |
| 5 | EC-6, BH-2, BH-3 | Precedence tested only with no operation selected | medium / patch | Added empty-versus-invalid with an operation selected; three precedence cases now each use a fresh window |
| 6 | EC-11 (runtime), BH (behaviour vs text) | No-dialog rule checked only by source text | low / patch | Added `anErrorOpensNoDialogOrExtraWindow`: after an error, no showing `Dialog` among `Window.getWindows()`. A modal dialog would block the EDT and hang the run rather than fail, which still surfaces the defect |
| 7 | EC-7, BH-7 (path), EC-9 | `NoDialogsTest` depends on the working directory and misses some dialog classes | low / patch | Added a clear failure message when the directory is missing and extra tokens (`JFileChooser`, `JColorChooser`, `java.awt.Dialog`, `Runtime.getRuntime()`); `assertFalse(contains)` for readable failures |
| 8 | EC-8, EC-10 | Source scan matches comments/string literals and spaced variants | low / rejected | No current source contains the tokens; false positives would be caught and fixed when they appear. Whitespace normalisation adds complexity for a reformatting case nobody has shown |
| 9 | EC claim (medium), BH | Spec wording "System.exit outside EXIT_ON_CLOSE" does not match the test | low / rejected | The test forbids the token `System.exit` everywhere, which is stricter; `EXIT_ON_CLOSE` does not contain it. Fix would edit the spec text |
| 10 | BH-8 (headless), VG other 1, EC-13 | Window tests are skipped headless, so a green headless run proves nothing about AC-11/12/17 | medium / patch | `evidence/story-2-1-test-log.txt` records the display run (114 run, 0 skipped) and the headless run (34 skipped) separately; `BUILD.md` already states a skip is not a pass |
| 11 | BH-8 (no red-then-green, no evidence) | Tests only, no production change, so never failed first | false | Recorded in the spec Implementation Notes and this log: the tests passed against Epic 1 code; no defect found |
| 12 | BH-5, BH-9, BH-11, BH-12, EC claim (low) | Helper `calculate` not visible in diff; naming and `@DisplayName`; focus/selection/accessible-name after an error; no `@Timeout` | false or low / rejected | `calculate` selects the operation each call (visible in the file, outside the diff); the blank-A matrix row is covered by `baseAc11_blank_operand_shows_the_error_inline` from Story 1.3. Naming, accessibility and timeouts are not baseline requirements |

No `intent_gap` or `bad_spec` findings, so no loopback (`review_loop_iteration` stays 0). No `defer` entries.

## Design Notes

This story is verification-led: the behaviour exists because Story 1.2 had to make the core total (AD-2). The planning slice therefore overlaps; recorded as finding F-06.

## Verification

**Commands:**
- `export JAVA_HOME=$(/usr/libexec/java_home -v 22) && cd bmad-calculator && mvn test` -- expected: BUILD SUCCESS, 0 skipped with a display
