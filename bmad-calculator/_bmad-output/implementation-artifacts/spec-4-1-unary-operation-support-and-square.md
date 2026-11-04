---
title: 'Story 4.1: Unary operation support and Square'
type: 'feature'
created: '2026-11-03'
status: 'done'
baseline_commit: '1e39815bd0ecc4e5f6bc43330abe6c4184c748f7'
route: 'dispatch'
review_loop_iteration: 0
story_key: '4-1-unary-operation-support-and-square'
context:
  - '{project-root}/_bmad-output/implementation-artifacts/epic-4-context.md'
---

<frozen-after-approval reason="human-owned intent — do not modify unless human renegotiates">

## Intent

**Problem:** CR-01 needs a one-number operation. The baseline `Operation` and `Calculator.evaluate` treat every operation as binary and always require both operands.

**Approach:** Give `Operation` an arity, make validation depend on it (First number only for unary, Second number ignored), add the Square operation and its `x²` control, and keep all baseline behaviour. Reciprocal and square root are later stories.

## Boundaries & Constraints

**Always:** `Calculator.evaluate` stays total (never throws). Check order on the required operands: blank, no operation, invalid number, domain error. With no operation selected both operands are required. Square is exact (`BigDecimal.multiply`), no rounding, formatted by `ResultFormatter`. Result and error stay until the next Calculate or Clear. The Second number field stays visible and enabled. `core` and `presenter` stay free of AWT/Swing.

**Never:** Reciprocal, square root or `NEGATIVE_SQRT` (Stories 4.2, 4.3). New error texts. Dialogs. Changes to `shared/`, to the baseline display policy or grammar, or to the presenter's state rules. `double`/`float` in `core`.

## I/O & Edge-Case Matrix

| Scenario | Input / State | Expected Output / Behavior | Error Handling |
|----------|--------------|---------------------------|----------------|
| Square of negative | `x²`, A=`-3`, B empty | `9` | N/A |
| Square of decimal | `x²`, A=`1.2` | `1.44` | N/A |
| Square of zero | `x²`, A=`0` | `0` | N/A |
| Second number ignored | `x²`, A=`4`, B=`7` and B=`abc` | `16` in both cases | N/A |
| Empty First number | `x²`, A empty, B empty or `5` | "Enter both numbers", no result | Inline |
| Malformed First number | `x²`, A=`abc` | "Invalid number", no result | Inline |
| Operation missing | no operation, A=`3`, B empty | "Enter both numbers" (baseline rule) | Inline |
| Baseline unchanged | `7 + 5`, `7 ÷ 0`, `7 ÷ x` empty B | `12`, "Cannot divide by zero", "Enter both numbers" | Inline |
| Control present | window open | `op-square` toggle labelled `x²`, selectable with the other operations | N/A |
| Enter key | `x²` selected, A=`3`, Enter in A or B | `9`, same as Calculate | N/A |

</frozen-after-approval>

## Code Map

- `bmad-calculator/src/calculator/core/Operation.java` -- enum with `apply(a, b)`; add an arity flag (for example `isUnary()`), add `SQUARE("x²", "Square")` that ignores `b`; keep `DIVISION_SCALE`
- `bmad-calculator/src/calculator/core/Calculator.java` -- `evaluate`: compute which operands are required, skip B for unary (also its grammar check); keep the order and the zero-divisor guard
- `bmad-calculator/src/calculator/ui/CalculatorFrame.java` -- builds one `op-<name>` toggle per `Operation.values()`; the new constant appears automatically; check width and wrap if needed
- `bmad-calculator/src/calculator/presenter/CalculatorPresenter.java` -- generic over `Operation`; expected unchanged
- `bmad-calculator/tests/calculator/core/OperationTest.java` -- asserts `values().length == 4`; must change
- `bmad-calculator/tests/calculator/core/CalculatorTest.java` -- add unary rows
- `bmad-calculator/tests/calculator/presenter/CalculatorPresenterTest.java`, `bmad-calculator/tests/calculator/ui/CalculatorFrameInteractionTest.java` -- add unary rows (window rows skip when headless)

## Tasks & Acceptance

**Execution:**
- [x] `bmad-calculator/src/calculator/core/Operation.java` -- add arity and `SQUARE` -- CR-FR-01, 02, 04
- [x] `bmad-calculator/src/calculator/core/Calculator.java` -- arity-aware required-operand checks -- CR-FR-02, 09; LOG-17, 18
- [x] `bmad-calculator/src/calculator/ui/CalculatorFrame.java` -- confirm the `x²` button shows and fits; adjust layout only if it does not -- CR-FR-01, 03; LOG-20
- [x] `bmad-calculator/tests/**` -- update `OperationTest`; add rows for the matrix in core, presenter and window tests; keep all baseline tests passing -- CR-AC-01..04, 14..17; BASE regression

**Acceptance Criteria:**
- Given a display, when the window opens, then an `x²` control is visible and selecting it deselects the other operations (CR-AC-01).
- Given the matrix, when each row runs through `Calculator`, the presenter and the window, then results and errors match the matrix and no result shows beside an error.
- Given the whole suite, when `mvn test` runs, then all baseline tests still pass and display and headless runs are logged separately, a skip never counted as a pass.

## Implementation Notes

- Implemented directly by the agent, not by a dispatched subagent (no subagent use was authorised; deviation D-13).
- `Operation`: added `SQUARE("x²", "Square", true)` and `isUnary()`. `Calculator.evaluate`: `needsSecond = operation == null || !operation.isUnary()`; B is neither checked for blank/grammar nor parsed for unary; order unchanged.
- `CalculatorFrame` and the presenter were not edited: the `x²` button is created from `Operation.values()` as `op-square`.
- Tests: `OperationTest` updated (5 operations, `isUnary`); new rows in `CalculatorTest`, `CalculatorPresenterTest`, `CalculatorFrameInteractionTest` (also `everyControlHasAStableName` and the initial-state test now include `op-square`).
- Verification: display run `mvn test`: 216 run, 0 failed, 0 skipped (baseline was 172). Log: `evidence/story-4-1-test-log.txt`. Matrix rows covered: square negative/decimal/zero, second ignored (empty, 7, `abc`), empty and malformed First, no operation, baseline unchanged, control present, Enter in A and B.
- Not verified: window layout by eye with five operation buttons (needs a screenshot by Varun); headless run not done in this session.

## Spec Change Log

## Review Triage Log

Layers run 2026-11-03 (three context-free subagents, diff of `src` and `tests` since `baseline_commit`): Blind Hunter 7 items, Edge Case Hunter 6, Verification Gap 0 gaps + 2 other findings.

| # | Layer | Finding | Verdict | Evidence / route |
|---|---|---|---|---|
| 1 | BH, VG | "Enter both numbers" is misleading for an empty unary First number | false | Mandated by CR-01 §5 and agreed as LOG-21; CR-AC-14 expects baseline wording. Rejected. |
| 2 | BH | No UI/presenter change in the diff | false | `CalculatorFrame` builds one button per `Operation.values()`; tests find `op-square` and pass. |
| 3 | BH, EC, VG | `isShowing() \|\| isVisible()` too weak | low | Real. Patched: assert not clipped and inside its row. |
| 4 | EC | Selecting `x²` while an error is shown is untested | low | Real. Patched: presenter test added. |
| 5 | BH | No test for `SQUARE.apply`, trailing zeros (`2.50`), long-scale input | low | Real. Patched: `OperationTest` and two `CalculatorTest` rows. |
| 6 | BH | Switching from Square to a binary operation must require B again | false | Already covered: `unaryDoesNotChangeTheBaselineRulesWithoutAnOperation` and baseline rows (`7 + ''`). |
| 7 | BH | Select-time handling of stale invalid B | false | Selecting never validates; unary ignores B (tested with `abc`). |
| 8 | BH | CR IDs not mapped; no run result | false | CR-AC-01..22 are in `shared/acceptance-criteria.md` §3; log `evidence/story-4-1-test-log.txt`. |
| 9 | EC, BH | No cap on operand length; square doubles the scale | low | Pre-existing theme (F-08 (a), multiply behaves the same); a cap adds guard code. Rejected. |
| 10 | EC, BH | `BigDecimal.ZERO` placeholder for B | low | Developer-only; a `null` placeholder risks NPE for future unary ops. Rejected. |
| 11 | BH, EC | Layout with five buttons not verified | maybe-false | Needs Varun's visual check (CR-AC-01). Deferred (see `deferred-work.md`); the new clipping assertion passes on display. |

After patches: `mvn test` 220 run, 0 failed, 0 skipped (display).

## Verification

**Commands:**
- `export JAVA_HOME=/Users/varunsondur/Library/Java/JavaVirtualMachines/corretto-22.0.2/Contents/Home && cd bmad-calculator && mvn -q test` -- expected: BUILD SUCCESS, 0 failures; display run records 0 skipped

**Manual checks (if no CLI):**
- `mvn -q compile exec:java`, select `x²`, enter `-3`, leave Second number empty, Calculate: result `9`.
