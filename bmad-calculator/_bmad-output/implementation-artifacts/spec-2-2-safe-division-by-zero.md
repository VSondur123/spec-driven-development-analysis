---
title: 'Story 2.2: Safe division by zero'
type: 'feature'
created: '2026-10-23'
status: 'done'
baseline_commit: '0ace55cb4fcecb14cc658d206c974c7460dc55f7'
route: 'dispatch'
review_loop_iteration: 0
story_key: '2-2-safe-division-by-zero'
context:
  - '{project-root}/_bmad-output/implementation-artifacts/epic-2-context.md'
---

<frozen-after-approval reason="human-owned intent — do not modify unless human renegotiates">

## Intent

**Problem:** BASE-AC-13 (and BASE-AC-18 for `0 / 5` and `1 / 3`) must hold in the running application, not only in the core: dividing by zero must give an error, never a crash, `Infinity` or `NaN`.

**Approach:** Verify the implemented behaviour through the presenter and the real window, add the missing tests, and fix any defect they expose. No new message or feature.

## Boundaries & Constraints

**Always:** The message is `ErrorKind.DIVIDE_BY_ZERO.message()` ("Cannot divide by zero"), shown inline; no result is shown with it; the divisor is judged by numeric value (`0`, `0.0`, `0.000`, `-0` all count as zero); `0 / 5` is valid and gives `0`.

**Never:** New error texts, dialogs, changes to `shared/`, a change to the display policy or the grammar.

## I/O & Edge-Case Matrix

| Scenario | Input / State | Expected Output / Behavior | Error Handling |
|----------|--------------|---------------------------|----------------|
| Divide by integer zero | `7`, `÷`, `0` | Error "Cannot divide by zero"; result empty | Inline |
| Decimal zero | `7`, `÷`, `0.0` and `0.000` | Same error | Inline |
| Negative zero | `7`, `÷`, `-0` | Same error | Inline |
| Zero numerator | `0`, `÷`, `5` | Result `0`; no error | N/A |
| Repeating decimal | `1`, `÷`, `3` | Result `0.3333333333` | N/A |
| After a result | `7 + 5` shown, then `7 ÷ 0` | Result cleared, error shown | Inline |
| Never a non-number | any divide by zero | The labels never contain `Infinity` or `NaN` | N/A |
| Other operations with zero | `7 × 0`, `0 − 0`, `0 + 0` | Results `0`, `0`, `0` | N/A |

</frozen-after-approval>

## Code Map

- `bmad-calculator/src/calculator/core/Calculator.java` -- guard `DIVIDE && right.signum() == 0` already present; change only if a test fails
- `bmad-calculator/tests/calculator/presenter/CalculatorPresenterTest.java` -- add presenter rows
- `bmad-calculator/tests/calculator/ui/CalculatorFrameInteractionTest.java` -- add window rows

## Tasks & Acceptance

**Execution:**
- [x] `bmad-calculator/tests/calculator/presenter/CalculatorPresenterTest.java` -- add divide-by-zero, zero-numerator and non-number rows -- BASE-AC-13, 18
- [x] `bmad-calculator/tests/calculator/ui/CalculatorFrameInteractionTest.java` -- add divide-by-zero variants, `0 / 5`, `1 / 3`, "after a result" through the window -- BASE-AC-13, 18
- [x] `bmad-calculator/src/**` -- fix any defect the tests expose -- FR-9

**Acceptance Criteria:**
- Given a display, when `7 ÷ 0`, `7 ÷ 0.0`, `7 ÷ 0.000` or `7 ÷ -0` is calculated, then the error label reads "Cannot divide by zero" and the result is empty.
- Given the matrix, when any row runs, then neither label ever contains `Infinity` or `NaN`.
- Given a headless run, when `mvn test` runs, then presenter rows pass and window rows are skipped, which the evidence log reports as skipped, not passed.

## Implementation Notes

- Implemented directly by the main session. No `src` change: the new tests passed against the Story 1.2 guard.
- Added presenter rows (4 zero spellings, 5 valid zero-operand cases) and window rows (4 zero spellings with a prior result, `0/5` and `1/3`).
- Verified 2026-10-23, Corretto 22: `mvn test` -> 128 run, 0 failed, 0 skipped with a display; after review patches 138 run, 0 failed (42 skipped headless). Log: `evidence/story-2-2-test-log.txt`.
- Same process deviation as Story 2.1: no commits until Epic 3 is complete, so the review diff is against a file snapshot taken at story start.

## Spec Change Log

## Review Triage Log

Layers: Blind Hunter (BH, 7 findings), Edge Case Hunter (EC, 8 edge cases + 2 claim checks), Verification Gap (VG, none). Reviewed 2026-10-23 at the same model capability as the session.

| # | Source | Finding | Verdict / route | Evidence |
|---|---|---|---|---|
| 1 | BH-1, EC-1, EC-2, EC claim (high) | The non-number assertion paired `Infinity` with the result and `NaN` with the error, and only ran on the divide-by-zero rows | medium / patch | Verified in both tests. Fixed: helper `assertNoNonNumbers` checks both tokens in both labels, and it is also applied to the valid zero-operand rows in the presenter and the window |
| 2 | BH-2, EC-3 | The "prior result" setup was `7 / 5` with DIVIDE selected, and never asserted | medium / patch | Presenter test now selects ADD, asserts `12`, then selects DIVIDE and divides by zero |
| 3 | BH-3, EC-4 | More zero spellings and a tiny non-zero divisor | low / patch | Added `00` and `-0.0` to both zero tests, and a presenter row `1 / 100000000000` giving `0` with no error (a non-zero divisor rounding to zero is not an error). `+0`, `.0`, `0.` are "Invalid number" by the grammar and are already covered in `CalculatorTest` |
| 4 | EC-5, BH-3 | Zero numerator with a negative divisor, negative multiplier, `-0` sums | low / patch | Added `0 / -5`, `0 * -7`, `-0 + 0` rows |
| 5 | EC-6 | No test with divide-by-zero as the very first action in a fresh window | low / patch | Added; also added `0 / 0` as a divide-by-zero case (BH-7) in the presenter and the window |
| 6 | EC claim (medium) | Window tests never ran `7 * 0`, `0 - 0`, `0 + 0` | low / patch | Added to the window test |
| 7 | EC-9 | Headless runs skip every window row | medium / patch | `evidence/story-2-2-test-log.txt` records the display run (138 run, 0 skipped) and the headless run (42 skipped) separately |
| 8 | BH-4, EC-7 | No recovery-after-error test | low / rejected | Owned by Story 2.3 |
| 9 | BH-5 | Missing imports in the window test | false | Compiled and ran: 138 tests |
| 10 | BH-6 | No production change and no evidence | false | Recorded in Implementation Notes and the evidence log: the tests passed against the Story 1.2 guard |
| 11 | BH-7 (naming) | Test names do not reference the story | low / rejected | Traceability is by `baseAcNN`; story references live in the spec |

No `intent_gap` or `bad_spec` findings, so no loopback (`review_loop_iteration` stays 0). No `defer` entries.

## Design Notes

Verification-led, as for Story 2.1: the guard exists from Story 1.2. `Operation.DIVIDE` uses `BigDecimal.divide` with an explicit scale, so a zero divisor would otherwise throw `ArithmeticException`; the core check prevents that, and these tests confirm it end to end.

## Verification

**Commands:**
- `export JAVA_HOME=$(/usr/libexec/java_home -v 22) && cd bmad-calculator && mvn test` -- expected: BUILD SUCCESS, 0 skipped with a display
