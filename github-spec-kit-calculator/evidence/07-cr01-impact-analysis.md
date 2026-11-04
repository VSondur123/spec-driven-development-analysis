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
