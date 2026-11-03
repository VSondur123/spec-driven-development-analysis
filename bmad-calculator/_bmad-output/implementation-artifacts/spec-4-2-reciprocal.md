---
title: 'Story 4.2: Reciprocal'
type: 'feature'
created: '2026-11-03'
status: 'done'
baseline_commit: '1e39815bd0ecc4e5f6bc43330abe6c4184c748f7'
route: 'dispatch'
review_loop_iteration: 0
story_key: '4-2-reciprocal'
context:
  - '{project-root}/_bmad-output/implementation-artifacts/epic-4-context.md'
  - '{project-root}/_bmad-output/implementation-artifacts/spec-4-1-unary-operation-support-and-square.md'
---

<frozen-after-approval reason="human-owned intent — do not modify unless human renegotiates">

## Intent

**Problem:** CR-01 needs a `1/x` operation. Story 4.1 added the unary path and Square; reciprocal needs its own 10-decimal rounding and a zero-input error.

**Approach:** Add `RECIPROCAL` (`1/x`) as a unary `Operation` that divides one by the First number with scale 10 and HALF_UP, and make `Calculator` report `DIVIDE_BY_ZERO` when its input is zero. No new error text.

## Boundaries & Constraints

**Always:** Reuse the baseline division policy (`Operation.DIVISION_SCALE`, HALF_UP) and `ResultFormatter`. Zero input is judged by numeric value: `0`, `0.0`, `-0.0`, `0.000` all count as zero. The message is `ErrorKind.DIVIDE_BY_ZERO.message()` ("Cannot divide by zero"), inline, with no result. Second number is ignored (empty, any value, malformed). Check order unchanged (blank, no operation, invalid, domain). `Calculator.evaluate` stays total. A tiny result may round to `0` (LOG-19) and is accepted.

**Never:** Square root or `NEGATIVE_SQRT` (Story 4.3). New error texts, dialogs, `double`/`float` in `core`, changes to `shared/`, the grammar, or the presenter's state rules.

## I/O & Edge-Case Matrix

| Scenario | Input / State | Expected Output / Behavior | Error Handling |
|----------|--------------|---------------------------|----------------|
| Exact | `1/x`, A=`4` | `0.25` | N/A |
| Repeating | `1/x`, A=`3` | `0.3333333333` | N/A |
| Negative | `1/x`, A=`-4` | `-0.25` | N/A |
| Rounds up | `1/x`, A=`0.3` | `3.3333333333` | N/A |
| Whole result | `1/x`, A=`1` and A=`0.5` | `1` and `2` | N/A |
| Zero input | `1/x`, A=`0`, `0.0`, `-0.0`, `0.000` | "Cannot divide by zero", no result | Inline |
| Tiny non-zero | `1/x`, A=`30000000000` | `0` (policy rounding, LOG-19) | N/A |
| Second ignored | `1/x`, A=`4`, B=`0`, B=`abc`, B empty | `0.25` in all cases | N/A |
| Empty / malformed | `1/x`, A empty; A=`abc` | "Enter both numbers"; "Invalid number"; no result | Inline |
| Recovery | `1/x`, A=`0` then A=`4` | error, then `0.25` with the error gone | Inline |
| Control | window open | `op-reciprocal` toggle labelled `1/x`, selectable, replaces the other selection | N/A |
| Enter key | `1/x`, A=`4`, Enter in A or B | `0.25`, same as Calculate | N/A |
| Baseline unchanged | `7 ÷ 0`, `7 ÷ 2`, `x²` of `-3` | "Cannot divide by zero", `3.5`, `9` | Inline |

</frozen-after-approval>

## Code Map

- `bmad-calculator/src/calculator/core/Operation.java` -- add `RECIPROCAL("1/x", "Reciprocal", true)`: `BigDecimal.ONE.divide(a, DIVISION_SCALE, RoundingMode.HALF_UP)`; reuse existing constants
- `bmad-calculator/src/calculator/core/Calculator.java` -- zero guard: `DIVIDE` with zero B, or `RECIPROCAL` with zero A, returns `DIVIDE_BY_ZERO`; the rest is arity-aware from Story 4.1
- `bmad-calculator/src/calculator/ui/CalculatorFrame.java`, `presenter/*` -- no edit expected (button built from `Operation.values()`)
- `bmad-calculator/tests/calculator/core/OperationTest.java` -- operation count assertion (5) must become 6; add `RECIPROCAL` apply and `isUnary`
- `bmad-calculator/tests/calculator/core/CalculatorTest.java`, `presenter/CalculatorPresenterTest.java`, `ui/CalculatorFrameInteractionTest.java` -- add reciprocal rows; the control-name lists (`everyControlHasAStableName`, initial-state test) need `op-reciprocal`

## Tasks & Acceptance

**Execution:**
- [x] `bmad-calculator/src/calculator/core/Operation.java` -- add `RECIPROCAL` -- CR-FR-01, 07
- [x] `bmad-calculator/src/calculator/core/Calculator.java` -- zero-input guard for `RECIPROCAL` -- CR-FR-08
- [x] `bmad-calculator/tests/**` -- update `OperationTest` and the control lists; add rows for the matrix in core, presenter and window tests -- CR-AC-09..13, 19; LOG-19

**Acceptance Criteria:**
- Given a display, when `1/x` is selected and `4`, `3`, `-4` are calculated, then `0.25`, `0.3333333333`, `-0.25` show (CR-AC-09..11).
- Given `0`, `0.0` or `-0.0`, when calculated, then "Cannot divide by zero" shows and no result (CR-AC-12, 13), and after correcting the input the right result shows and the error is gone (CR-AC-19).
- Given the whole suite, when `mvn test` runs, then all earlier tests still pass and display and headless runs are logged separately.

## Implementation Notes

- Implemented directly by the agent (D-13 applies). `Operation`: added `RECIPROCAL("1/x", "Reciprocal", true)` using `BigDecimal.ONE.divide(a, DIVISION_SCALE, HALF_UP)`. `Calculator`: zero guard extended to `RECIPROCAL` with a zero first operand. Frame and presenter not edited.
- Tests: `OperationTest` count 5 to 6, reciprocal apply and unary flag; new core, presenter and window rows; control-name lists include `op-reciprocal`. Display run before review: 255 run, 0 failed, 0 skipped; after review patches see below. Logs: `evidence/story-4-2-test-log.txt`.
- The review diff was cumulative (Stories 4.1 and 4.2) because nothing is committed, so reviewers also saw 4.1 code.

## Spec Change Log

## Review Triage Log

Layers run 2026-11-03 (three context-free subagents; the diff of `src` and `tests` since `baseline_commit` also contained Story 4.1 code): Blind Hunter 9 items, Edge Case Hunter 4, Verification Gap 0 gaps.

| # | Layer | Finding | Verdict | Evidence / route |
|---|---|---|---|---|
| 1 | BH | No UI or presenter change in the diff | false | `CalculatorFrame` builds buttons from `Operation.values()`; the window tests find `op-reciprocal`. Same as 4.1 row 2. |
| 2 | BH | "Enter both numbers" is misleading for unary | false | Mandated by CR-01 §5; agreed as LOG-21. |
| 3 | BH | Second number stays enabled and gives no hint | false | Agreed layout, LOG-20; CR-AC-16/17 test the ignoring. |
| 4 | BH, EC | Tiny reciprocals show `0` | false | Agreed policy, LOG-19, and tested on purpose. |
| 5 | BH | Square output has no length or magnitude policy | false | CR-01 §4 item 5: exact, no unnecessary rounding. |
| 6 | BH | Domain rule hard-coded by enum identity in `Calculator` | low | Real, developer-only; moving it into `Operation` adds a hook. Rejected; it is the study finding already recorded for AD-7. |
| 7 | EC | `RECIPROCAL.apply(0)` throws if called outside `Calculator` | low | Package-private, same contract as `DIVIDE` in the baseline. Rejected. |
| 8 | EC, BH | No operand length cap; square doubles digits | low | Pre-existing theme (F-08 (a)). Rejected as in 4.1. |
| 9 | BH | Missing tests: null First number for unary | low | Real. Patched (`nullFirstNumberIsEmptyForUnaryOperations`). |
| 10 | EC | Reciprocal selection replacing other selections untested | low | Real. Patched (`reciprocalSelectionReplacesTheOtherSelections`). |
| 11 | BH | Missing reciprocal malformed rows (`1e3`, `1,5`), presenter keep-error rows | low | The grammar and presenter paths are operation-independent and already covered by square and baseline rows. Rejected. |
| 12 | BH | Weak layout assertions; brittle operation-count test | low | Layout check deferred (4.1 entry). Count test is the intended guard. Rejected. |
| 13 | EC | Diff is not isolated to Story 4.2 | low | Process note: nothing is committed, so the diff is cumulative. Recorded; no code change. |

After patches: `mvn test` 257 run, 0 failed, 0 skipped (display).

## Verification

**Commands:**
- `export JAVA_HOME=/Users/varunsondur/Library/Java/JavaVirtualMachines/corretto-22.0.2/Contents/Home && cd bmad-calculator && mvn -q test` -- expected: BUILD SUCCESS, 0 failures; display run records 0 skipped

**Manual checks (if no CLI):**
- `mvn -q compile exec:java`, select `1/x`, enter `0`, Calculate: "Cannot divide by zero"; enter `4`, Calculate: `0.25`.
