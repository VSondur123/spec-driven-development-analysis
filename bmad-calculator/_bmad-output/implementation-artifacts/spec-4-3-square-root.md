---
title: 'Story 4.3: Square root'
type: 'feature'
created: '2026-11-03'
status: 'done'
baseline_commit: '1e39815bd0ecc4e5f6bc43330abe6c4184c748f7'
route: 'dispatch'
review_loop_iteration: 0
story_key: '4-3-square-root'
context:
  - '{project-root}/_bmad-output/implementation-artifacts/epic-4-context.md'
  - '{project-root}/_bmad-output/implementation-artifacts/spec-4-2-reciprocal.md'
---

<frozen-after-approval reason="human-owned intent — do not modify unless human renegotiates">

## Intent

**Problem:** CR-01 needs a `√x` operation with a deterministic result rounded to 10 decimals HALF_UP, and an error for negative input. Stories 4.1 and 4.2 added the unary path, Square and Reciprocal.

**Approach:** Add `SQRT` (`√x`) as a unary `Operation` using the approved exact-integer algorithm (architecture CR-01 square-root rule), add `ErrorKind.NEGATIVE_SQRT` ("Cannot take square root of a negative number"), and make `Calculator` report it for negative input after the baseline checks.

## Boundaries & Constraints

**Always:** Algorithm: `scaled = a × 10^20` (exact); `r = floor(scaled).sqrt()` using `BigInteger.sqrt()`; add one to `r` when `scaled >= r² + r + 1/4` (exact `BigDecimal` comparison); result is `r × 10^-10`, formatted by `ResultFormatter`. Negative means numeric value below zero: `-0`, `-0.0` are zero and give `0`. The message comes only from `ErrorKind.NEGATIVE_SQRT.message()`, inline, no result. Check order unchanged: blank, no operation, invalid number, then the domain error. Second number is ignored. `Calculator.evaluate` stays total.

**Never:** `Math.sqrt`, `double`/`float`, or `BigDecimal.sqrt(MathContext)` in `core`. Other new error texts, dialogs, changes to `shared/`, the grammar, the display policy, or the presenter's state rules.

## I/O & Edge-Case Matrix

| Scenario | Input / State | Expected Output / Behavior | Error Handling |
|----------|--------------|---------------------------|----------------|
| Perfect square | `√x`, A=`9`, `0.25`, `1.21` | `3`, `0.5`, `1.1` | N/A |
| Irrational | `√x`, A=`2` | `1.4142135624` | N/A |
| Zero | `√x`, A=`0`, `0.0`, `-0.0`, `-0` | `0` | N/A |
| Rounds up | `√x`, A=`0.0000000002` | `0.0000141421` | N/A |
| Exact tie | `√x`, A=`0.0000000000000000000025` | `0.0000000001` (exact half rounds up) | N/A |
| Just below tie | `√x`, A=`0.0000000000000000000024` | `0.0000000000` shown as `0` | N/A |
| Large | `√x`, A=`99999999999999999999` and `123456789.123456789` | `9999999999.9999999999` and `11111.1110661111` | N/A |
| Negative | `√x`, A=`-1`, `-0.0000001` | "Cannot take square root of a negative number", no result | Inline |
| Order | `√x`, A=`-1`, B=`abc`; A=`abc`; A empty | negative error (B ignored); "Invalid number"; "Enter both numbers" | Inline |
| Second ignored | `√x`, A=`9`, B empty, `4`, `abc` | `3` in all cases | N/A |
| Recovery | `√x`, A=`-1` then A=`9` | error, then `3` with the error gone | Inline |
| Control | window open | `op-sqrt` toggle labelled `√x`, selectable, replaces the other selection | N/A |
| Enter key | `√x`, A=`9`, Enter in A or B | `3`, same as Calculate | N/A |
| Baseline unchanged | `7 ÷ 0`, `1/x` of `0`, `x²` of `-3`, `1/x` of `4` | "Cannot divide by zero" twice, `9`, `0.25` | Inline |

</frozen-after-approval>

## Code Map

- `bmad-calculator/src/calculator/core/ErrorKind.java` -- add `NEGATIVE_SQRT("Cannot take square root of a negative number")`; single source of error text (AD-4)
- `bmad-calculator/src/calculator/core/Operation.java` -- add `SQRT("√x", "Square root", true)` with the algorithm above; `DIVISION_SCALE` (10) is the rounding scale; `apply` expects a non-negative first operand
- `bmad-calculator/src/calculator/core/Calculator.java` -- after the grammar check: `SQRT` with `left.signum() < 0` returns `NEGATIVE_SQRT`; the zero guards from 4.2 stay
- `bmad-calculator/src/calculator/ui/CalculatorFrame.java`, `presenter/*` -- no edit expected
- `bmad-calculator/tests/calculator/core/OperationTest.java` -- count 6 becomes 7; `SQRT` apply and unary flag; algorithm rows
- `bmad-calculator/tests/calculator/core/CalculatorTest.java`, `presenter/CalculatorPresenterTest.java`, `ui/CalculatorFrameInteractionTest.java` -- add square-root rows; control-name lists need `op-sqrt`. Check any test that enumerates `ErrorKind` values or messages.

## Tasks & Acceptance

**Execution:**
- [x] `bmad-calculator/src/calculator/core/ErrorKind.java` -- add `NEGATIVE_SQRT` -- CR-FR-06
- [x] `bmad-calculator/src/calculator/core/Operation.java` -- add `SQRT` with the exact-integer algorithm -- CR-FR-05; CR-01 §4 items 6, 9
- [x] `bmad-calculator/src/calculator/core/Calculator.java` -- negative-radicand guard -- CR-FR-06
- [x] `bmad-calculator/tests/**` -- update `OperationTest` and control lists; add rows for the matrix in core, presenter and window tests -- CR-AC-05..08, 18

**Acceptance Criteria:**
- Given a display, when `√x` is selected and `9`, `2`, `0` are calculated, then `3`, `1.4142135624`, `0` show (CR-AC-05..07).
- Given `-1`, when calculated, then "Cannot take square root of a negative number" shows and no result (CR-AC-08), and after correcting the input the right result shows and the error is gone (CR-AC-18).
- Given the matrix tie and near-tie rows, when run, then the exact half rounds up and the near-tie rounds down, proving the integer algorithm.
- Given the whole suite, when `mvn test` runs, then all earlier tests still pass and display and headless runs are logged separately.

## Implementation Notes

- Implemented directly by the agent (D-13 applies). `ErrorKind.NEGATIVE_SQRT` added. `Operation.SQRT` uses a private `squareRoot` helper: `scaled = a.movePointRight(20)`, `root = scaled.toBigInteger().sqrt()`, add one when `scaled >= root*root + root + 0.25`, result `new BigDecimal(root, 10)`. `Calculator`: `SQRT` with `left.signum() < 0` returns `NEGATIVE_SQRT` after the zero guards. Frame and presenter not edited. No test enumerated `ErrorKind` values.
- Defect found by the first test run (test, not code): one `OperationTest` row used a 42-digit input (`1e41`) where `1e40` was intended; the code was right (`sqrt(1e41)` matches an independent calculation). Fixed the test input and added the `1e41` row with its independently derived value. First run: 1 failed of 302; second run: 303 run, 0 failed.
- Review diff was cumulative (4.1 to 4.3).

## Spec Change Log

## Review Triage Log

Layers run 2026-11-03 (three context-free subagents on the cumulative `src` and `tests` diff): Blind Hunter 9 items, Edge Case Hunter 6, Verification Gap 0 gaps + 1 other finding.

| # | Layer | Finding | Verdict | Evidence / route |
|---|---|---|---|---|
| 1 | BH | Possible scientific-notation output for tiny or large results | false | `ResultFormatter` uses `stripTrailingZeros().toPlainString()`. A tiny-square row was still added (patch, low). |
| 2 | BH | Reciprocal rounds a non-zero input to `0` | false | Agreed policy LOG-19, logged in `shared/experiment-log.md`. |
| 3 | BH, EC | "Enter both numbers" for a missing single operand | false | CR-01 §5 and LOG-21. |
| 4 | BH, EC | No bound on operand length (square, sqrt stall or overflow) | low | Pre-existing theme (F-08 (a)); a cap adds guard code. Rejected as in 4.1 and 4.2. |
| 5 | BH | Per-operation rules hard-coded by identity in `Calculator` | low | Third time raised; recorded as a study finding (AD-7 amended). Rejected. |
| 6 | BH, VG | `apply` Javadoc is missing a comma and is one long line | low | Real. Patched (reformatted, comma added). |
| 7 | BH | Presenter coverage uneven across unary operations | low | The presenter is operation-independent and covered by Square and baseline rows. Rejected. |
| 8 | BH | Weak layout assertions; frame not in the diff | low | Visual check deferred (4.1 entry); the frame builds buttons from `Operation.values()`. Rejected. |
| 9 | BH | Test naming and bundled criteria | low | Style only. Rejected. |
| 10 | EC | Square is not rounded to scale 10 | false | CR-01 §4 item 5: exact, no unnecessary rounding. |
| 11 | EC | `SQRT.apply` / `RECIPROCAL.apply` throw if called directly | low | Package-private, same contract as `DIVIDE` in the baseline. Rejected. |
| 12 | EC | `NEGATIVE_SQRT` message not asserted | false | Window test asserts the literal text and the presenter test asserts `message()`. |

After patches: `mvn test` 304 run, 0 failed, 0 skipped (display).

## Verification

**Commands:**
- `export JAVA_HOME=/Users/varunsondur/Library/Java/JavaVirtualMachines/corretto-22.0.2/Contents/Home && cd bmad-calculator && mvn -q test` -- expected: BUILD SUCCESS, 0 failures; display run records 0 skipped

**Manual checks (if no CLI):**
- `mvn -q compile exec:java`, select `√x`, enter `-1`, Calculate: the negative-number error; enter `2`, Calculate: `1.4142135624`.
