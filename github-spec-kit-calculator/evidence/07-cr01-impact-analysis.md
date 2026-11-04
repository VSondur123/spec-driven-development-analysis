# CR-01 pre-coding impact analysis — George / GitHub Spec Kit track

**Written:** 2026-11-04, before any CR-01 spec, plan, task or code change. Repo HEAD at writing time: `5795cb6` on `george/spec-kit-cr01`; frozen baseline `88f56d0` (tag `speckit-baseline-frozen`, local).
**Author:** agent (`claude-sonnet-5-5`) in the owner's session. **Status:** first version, preserved as the pre-coding record (protocol §5 step 2). Do not edit in place; later corrections go in a dated addendum at the end.
**Inputs read:** `shared/change-request-CR-01.md` v0.2, `shared/acceptance-criteria.md` CR-AC rows, `shared/experiment-protocol.md` §5, `shared/experiment-log.md` LOG-16 to LOG-21, and this track's own source, tests and baseline specs. Not read: the BMAD track.
**Nature:** a prediction from reading the code and documents. Nothing here has been built or run.

## 1. What CR-01 asks for (summary)

Three unary operations on the First number field: Square `x²`, Square root `√x`, Reciprocal `1/x` (`CR-FR-01` to `CR-FR-11`, `CR-AC-01` to `CR-AC-22`). Square is exact; square root and reciprocal round to 10 decimals HALF_UP. New error: `Cannot take square root of a negative number`. Reciprocal of zero (`0`, `0.0`, `-0.0`) reuses the baseline division-by-zero error. Second number is ignored, even if malformed (LOG-17). Error priority: empty First, no operation, invalid First, domain error (LOG-18). Second field stays visible and enabled (LOG-20). "Enter both numbers" wording is kept (LOG-21). Very large reciprocals may round to `0` (LOG-19).

## 2. Affected elements — code

| Element | Predicted change | Why |
|---|---|---|
| `core/Operation.java` | Add three constants with symbols `x²`, `√x`, `1/x`; add a way to tell unary from binary | `CR-FR-01`, `CR-FR-02` |
| `core/CalculationError.java` | Add `NEGATIVE_SQUARE_ROOT`, declared after `DIVISION_BY_ZERO` (the enum is declared in priority order) | `CR-FR-06`, LOG-18 |
| `core/Calculator.java` | `calculate(first, second, operation)` currently returns EMPTY_OPERAND when either operand is empty and parses the Second field first. For unary operations the Second field must not be parsed or checked at all. Add square (`multiply`, exact), square root (deterministic, 10 dp HALF_UP), reciprocal (`ONE.divide(x, 10, HALF_UP)`, zero check by `signum`). Priority stays: empty, no operation, invalid, domain error | `CR-FR-02`, `04`, `05`, `07`, `09`, LOG-17, LOG-18 |
| `ui/ErrorMessages.java` | New `case` for the new error. The `switch` is exhaustive, so it will not compile without it | `CR-FR-06` |
| `ui/CalculatorPanel.java` | Three more toggle buttons in the same `ButtonGroup`, with names (my choice, not in the CR: `squareButton`, `squareRootButton`, `reciprocalButton`). Second field stays enabled. `calculate()` and `clear()` need no logic change; `layoutComponents()` puts seven buttons in one row, which widens the window | `CR-FR-01`, `CR-FR-03`, LOG-20 |
| `core/OperandParser.java`, `core/ResultFormatter.java`, `core/CalculationOutcome.java`, `ui/CalculatorApp.java` | None expected | Grammar and formatting are unchanged (CR-01 §4 items 1, 8, 10) |
| `pom.xml` | None expected | No new dependency; `BigDecimal.sqrt` exists in the Java 22 runtime |

Open design point for the plan: square root to 10 dp HALF_UP must be deterministic and must not double-round. Using `BigDecimal.sqrt(MathContext)` and then `setScale` could round twice. The plan should choose and justify the method (for example an exact integer square root on a scaled value) and test the boundary cases.

## 3. Affected elements — specs and design (this track)

| Artefact | Predicted change |
|---|---|
| `specs/001-baseline-calculator/spec.md` | Baseline statements that CR-01 changes in practice: `FR-003` ("four operation buttons"), `FR-013` (priority list), `FR-014` (error list), `BASE-AC-11` wording "first operand, second operand, or both empty". The baseline still applies to the four binary operations. Decision needed: amend this spec or write a new one (section 6, question 1) |
| `plan.md`, `research.md`, `data-model.md` | Operation and error-code lists, Requirement Coverage table, a new decision for the square-root method |
| `contracts/calculation-contract.md` | New operations, new error, unary input rule |
| `contracts/ui-contract.md` | "The four operation buttons" becomes seven; new component names |
| `tasks.md` | New tasks. Existing ticked tasks stay as history |
| `checklists/requirements.md` | The skill forbids the agent editing it |
| `.specify/memory/constitution.md` | To be checked against the CR-01 plan; no change predicted |
| `README.md`, `quickstart.md` | Build and run commands unchanged; the quickstart's "what to expect" text may need the new buttons |

## 4. Affected elements — tests

| Test | Predicted effect |
|---|---|
| `CalculatorPanelTest.buttonName(Operation)` (line 69) | Exhaustive `switch` over `Operation`: **compile error** once three constants exist. Must be extended |
| `startsInTheInitialState`, `selectingAnOperationLeavesExactlyThatButtonSelected` | Loop over `Operation.values()` and call `buttonName`; they will cover the new buttons once the switch is fixed |
| `operationButtonsShowTheirSymbols` | Passes unchanged; needs three added assertions |
| `CalculatorTest` (23 tests), `CalculatorPanelTest` (32), `ResultFormatterTest` (5), `OperandParserTest` (1) | Behaviour for the four binary operations is expected to stay the same |
| New tests | One per `CR-AC-02` to `CR-AC-20` (core and GUI layer), plus boundary cases (`0.0`, `-0.0`, malformed Second field, very large reciprocal per LOG-19, square-root rounding cases) |
| Baseline result file `evidence/06-test-results.md` | Must stay intact. A separate CR-01 results file is needed for `CR-AC-01` to `CR-AC-22`, with the 19 `BASE-AC` re-runs recorded individually (`CR-AC-21`) |

Baseline count before the change: 81 tests (`evidence/logs/t033-final-verify.txt`).

## 5. Predicted risks and regressions

Predicted, to be confirmed or refuted by the re-run of all 19 baseline checks:

1. **Empty-operand rule.** The most likely regression source is `Calculator.calculate`: if the new code reuses the two-operand empty check, `CR-AC-16` fails (unary with empty Second). If the empty check is loosened too far, `BASE-AC-11` fails for binary operations. Both directions need tests.
2. **Malformed Second field.** If Second is still parsed first, a unary calculation with `abc` in Second returns `Invalid number` (violates LOG-17).
3. **Error priority.** Unary with no operation and empty First: the baseline order must hold (`BASE-AC-17`, `CR-AC-14`).
4. **`-0.0` reciprocal.** `signum()` handles it; a string comparison would not.
5. **Square-root rounding** (section 2): a `double`-based or double-rounding method gives wrong digits in rare cases.
6. **Layout.** Seven buttons in one row make the window wider. Screenshots for `BASE-AC-02` and `03` were taken at the baseline size; the real-window checks will need to be redone by the owner.
7. **Clear.** `clearSelection()` should deselect the new buttons; to be tested (`CR-AC-20`, `BASE-AC-10`).
8. **Enter key.** Enter in either field runs `calculate()`; expected to work for unary (`CR-FR-03`) but must be observed in the real window.
9. **Wording.** "Enter both numbers" for a unary empty First (LOG-21) is accepted as is; no change.
10. **Large reciprocal shows `0`** for a non-zero input (LOG-19); accepted policy, tested so the behaviour is recorded.

Baseline checks I consider most exposed: `BASE-AC-11`, `BASE-AC-17`, `BASE-AC-10`, `BASE-AC-16`, `BASE-AC-19`, `BASE-AC-02` and `03` (layout). I expect the others to be unaffected.

## 6. Questions for the owner before the Spec Kit steps

1. **Where does the change go in Spec Kit?** (a) A new feature folder `specs/002-scientific-operations/` via `/speckit-specify` (the tool's default for a new piece of work; the frozen baseline spec stays untouched, but the new spec has to restate which baseline rules it changes), or (b) amend `specs/001-baseline-calculator/` in place (one spec, but it rewrites the frozen baseline's history). I recommend (a). Either way it is recorded as a finding about how the tool handles change.
2. **Git.** Work stays on `george/spec-kit-cr01`; no commits or pushes unless you ask.
3. **Effort clock.** The effort log has no CR-01 start yet. I will add an entry with the time this analysis started (2026-11-04, about 22:00 local).

## 7. Not done yet

No `/speckit-*` command has been run for CR-01. No code, test or spec has been changed. No CR-01 results exist.


---

## Addendum 2026-11-05: predicted against actual (T048)

Added after the change was built and tested. The text above is unchanged. Actual changes are from `git diff 88f56d0 HEAD` (commit 411ab32), outside `evidence/` and `specs/002-scientific-operations/`: 9 files, 462 insertions, 30 deletions. Nothing under `specs/001-baseline-calculator/`, `pom.xml` or `README.md` changed.

### Code

| Predicted | Actual | Verdict |
|---|---|---|
| `Operation.java`: three constants, a unary flag | Done (`isUnary()`) | Correct |
| `CalculationError.java`: `NEGATIVE_SQUARE_ROOT` last | Done | Correct |
| `Calculator.java`: unary operands, three computations | Done; square root by the single-rounding integer method | Correct |
| `ErrorMessages.java`: one new case | Done (1 line) | Correct |
| `CalculatorPanel.java`: three buttons; `calculate()` and `clear()` unchanged; wider window | Three buttons added; `calculate()` and `clear()` unchanged. **Also** a new `errorAreaSize` method, because the error area was too narrow for the new message | Partly correct: the extra change was not predicted (see defect below) |
| `OperandParser`, `ResultFormatter`, `CalculationOutcome`, `CalculatorApp`, `pom.xml`: none | Unchanged | Correct |

### Tests

| Predicted | Actual | Verdict |
|---|---|---|
| `CalculatorPanelTest.buttonName` stops compiling | It did (red run `cr-t006`), fixed with 3 lines | Correct |
| The two tests that loop over `Operation.values()` "will cover the new buttons once the switch is fixed" | They would have failed until the buttons existed (they look the buttons up). Found by `/speckit-analyze` (finding I1), not by this analysis; the buttons were moved into the foundational step | Wrong in detail |
| `operationButtonsShowTheirSymbols` unchanged, three assertions added | Left unchanged; the new labels got their own tests | Partly correct |
| New tests for `CR-AC-02` to `20` plus boundary cases | 35 new tests (116 in total) | Correct |
| Baseline result file stays intact; separate CR-01 results file | `08-cr01-test-results.md` | Correct |
| Not predicted | `OperationTest.java` (new file, 4 tests) | Unpredicted addition |

### Specs and design

| Predicted | Actual | Verdict |
|---|---|---|
| `specs/001-baseline-calculator/spec.md` amended (or a new spec) | Owner chose a new folder; `001` untouched | Correct (the decision was left open) |
| plan, research, data model, contracts, tasks | New in `specs/002-scientific-operations/` | Correct |
| `checklists/requirements.md`: agent may not edit | New file in 002, written by `/speckit-specify` | Correct |
| `.specify/memory/constitution.md`: "no change predicted" | **Amended to 1.1.0** (principle II said scientific operations were out of scope; CR-01 had been released) | **Wrong**: the conflict was found when running `/speckit-specify`, not in this analysis |
| `README.md`, `quickstart.md`: build commands unchanged, "what to expect" may need the buttons | `README.md` unchanged; the new quickstart is in 002 | Correct |

### Risks and regressions

| Predicted | What happened |
|---|---|
| 1 Empty-operand rule | Handled by arity in `Calculator`; tested both ways; no regression |
| 2 Malformed Second field | Second is not parsed for unary operations; tested; no regression |
| 3 Error priority | Kept; tested; no regression |
| 4 `-0.0` reciprocal | Zero check by sign; tested for `0`, `0.0`, `-0.0`, `-0`; no regression |
| 5 Square-root rounding | Integer method, rounded once; tie and just-below-tie cases pass |
| 6 Layout (wider window) | Window wider; screenshots for `BASE-AC-02`, `03` re-taken; no baseline check failed |
| 7 Clear | Existing code deselects the new buttons; tested |
| 8 Enter key | Works for unary operations (owner's report, screenshot consistent) |
| 9 Wording | `Enter both numbers` kept (LOG-21) |
| 10 Large reciprocal shows `0` | Tested (LOG-19) |
| Most exposed baseline checks: `BASE-AC-11`, `17`, `10`, `16`, `19`, `02`, `03` | All 19 baseline checks re-run: 19 Pass, 0 regressions |

Not predicted: defect D-CR-01 (DL-57). The negative-square-root message was cut off in the real window because the error area was a fixed width. It came from the same cause as predicted risk 6 (layout) but was a CR-01 defect, not a baseline regression, and the automated test did not catch it before the owner's screenshot. Not predicted either: that the first green run of the US2 tests would fail because of one of my own US1 tests (DL-56).

### Analysis findings noted for this addendum (owner decision, 2026-11-05)

| Finding | Status at the end |
|---|---|
| I4: plan said the `BASE-AC-16` test would be extended; tasks added a new test instead | The baseline test was not edited (0 removed lines, `cr-t040`); the mixed-sequence test is new; the `BASE-AC-16` row cites the baseline test |
| I5: plan tree lacks `OperationTest.java` and the optional `SquareRoot.java` | `OperationTest.java` was added; no `SquareRoot.java` (the square root is a private method in `Calculator`, DL-53) |
| G1: automated rows for `BASE-AC-02` and `03` not in T038 | The rows were filled from the owner's real-window screenshots (cr01-18, cr01-19), with the automated test named in the notes |
| G2: `BASE-NFR-04` (NFR-002) not named in the review task | The change is confined to five source files plus tests; `specs/001` was not edited; the review log `cr-t046-review.txt` does not name NFR-002 explicitly, this addendum does |

### Accuracy summary

Of the 5 predicted source files, all 5 changed and the 5 predicted-unchanged files stayed unchanged. One predicted-unchanged item (the constitution) changed. One unpredicted source change (`errorAreaSize`) and one unpredicted new test file. One prediction (the loop tests) was wrong in detail. No predicted regression happened.
