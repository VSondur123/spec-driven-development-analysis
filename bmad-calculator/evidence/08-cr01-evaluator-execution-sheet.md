# Evaluator Execution Sheet — CR-01 (CR-AC-01 to 22)

**Status:** Blank template. Nothing in this sheet has been executed. Every outcome is **Not run** until an evaluator fills it in from an observed run.
**Scope:** CR-01 change phase only. The denominator is 22 parent ACs; subcases never add to it.
**Authority:** `shared/acceptance-criteria.md` §3 (copied unchanged from `shared/change-request-CR-01.md` §6 v0.2, LOG-16), with the clarifications LOG-17 to LOG-21 applied. If this sheet and that file differ, that file wins.
**Same sheet for both tracks.** This file is an identical copy in `bmad-calculator/evidence/` and `github-spec-kit-calculator/evidence/`. Use the same operands, order and expected outcomes. Do not edit one copy only.
**Snapshot rule:** Run each track on its own frozen change snapshot, not on a branch tip. BMAD: tag `bmad-cr01-frozen` (commit `72f2783`). Spec Kit: to be declared by George after CR-01 (see experiment log Section G).
**Regression:** `CR-AC-21` is a separate run of the 19 baseline checks on the changed app. Use a fresh copy of `07-evaluator-execution-sheet.md` for it and keep the baseline record intact. A regression counts only where the baseline check was observed Pass and now fails.

## Run record (fill in once per track)

| Field | Value |
|---|---|
| Track | |
| Evaluator (name, role, prior exposure to this track) | |
| Date / time / timezone | |
| Snapshot (full commit SHA or tag; clean or dirty) | |
| OS / version | |
| JDK version and `JAVA_HOME` | |
| Maven version | |
| Build and run command used | |
| Display available (GUI run) or headless | |
| Adopted protocol and rubric revision | |

## Rules

- Outcomes are Pass, Fail, Blocked or Not run. Pass only if the behaviour was observed in this run.
- Start each check from the initial state unless the row says stateful.
- The first planned run is the primary record. Keep reruns separate, with the reason.
- Parent outcome: Fail if any subcase fails; else Blocked if any is blocked; else Not run if any is unexecuted; else Pass.
- Suggested message wording is not an exact-string condition; the cases must be clearly distinguishable.
- Engine-only unit tests do not establish GUI behaviour. Name the layer you observed.

## Checks

| Test ID | Procedure (same operands for both tracks) | Expected | Layer / subcases | Outcome | Observed / actual | Evidence ref |
|---|---|---|---|---|---|---|
| `CR-AC-01` | Launch the application and inspect the GUI. | Square, square-root and reciprocal controls are visible and identifiable. | GUI | Not run | | |
| `CR-AC-02` | Select square, enter `-3` in First number, leave Second number empty, calculate. | `9` | GUI | Not run | | |
| `CR-AC-03` | Select square, enter `1.2`, calculate. | `1.44` | GUI | Not run | | |
| `CR-AC-04` | Select square, enter `0`, calculate. | `0` | GUI | Not run | | |
| `CR-AC-05` | Select square root, enter `9`, calculate. | `3` | GUI | Not run | | |
| `CR-AC-06` | Select square root, enter `2`, calculate. | `1.4142135624` | GUI | Not run | | |
| `CR-AC-07` | Select square root, enter `0`, calculate. | `0` | GUI | Not run | | |
| `CR-AC-08` | Select square root, enter `-1`, calculate. | The specified negative-input error appears; no successful result is shown. | GUI | Not run | | |
| `CR-AC-09` | Select reciprocal, enter `4`, calculate. | `0.25` | GUI | Not run | | |
| `CR-AC-10` | Select reciprocal, enter `3`, calculate. | `0.3333333333` | GUI | Not run | | |
| `CR-AC-11` | Select reciprocal, enter `-4`, calculate. | `-0.25` | GUI | Not run | | |
| `CR-AC-12` | Select reciprocal, enter `0`, calculate. | The baseline division-by-zero error appears; no successful result is shown. | GUI | Not run | | |
| `CR-AC-13` | Repeat CR-AC-12 with `0.0` and `-0.0`. | Each input is treated as zero and rejected safely. | GUI; 2 subcases | Not run | | |
| `CR-AC-14` | Select each unary operation with an empty First number and calculate. | Clear validation feedback; no crash. | GUI; 3 subcases | Not run | | |
| `CR-AC-15` | Select each unary operation and enter malformed input such as `abc`. | Baseline invalid-number feedback; no crash. | GUI; 3 subcases | Not run | | |
| `CR-AC-16` | Select a unary operation, enter a valid First number, leave Second number empty. | The calculation succeeds without a second operand. | GUI | Not run | | |
| `CR-AC-17` | Select a unary operation, enter a valid First number and a different value in Second number, calculate. | The result depends only on First number. | GUI | Not run | | |
| `CR-AC-18` | Trigger a negative square-root error, correct the input, calculate again. | The application recovers and shows the correct result. | GUI; stateful | Not run | | |
| `CR-AC-19` | Trigger a reciprocal division-by-zero error, correct the input, calculate again. | The application recovers and shows the correct result. | GUI; stateful | Not run | | |
| `CR-AC-20` | Use Clear after a unary calculation. | The calculator returns to the agreed baseline initial state. | GUI | Not run | | |
| `CR-AC-21` | Execute all `BASE-AC-01` to `BASE-AC-19` against the changed application. Record each individually (use a fresh copy of the baseline sheet). | All baseline criteria retain their required behaviour. | Regression; 19 parents | Not run | | |
| `CR-AC-22` | Follow the documented build and test instructions for the changed application. | Builds and tests execute. Prerequisites, test outcomes and failures are documented. | Build | Not run | | |

## Summary (fill in after the run)

| P | F | B | N | D | Verified pass coverage P/D | Decisive (P+F)/D | Complete C/D |
|---|---|---|---|---|---|---|---|
| 0 | 0 | 0 | 22 | 22 | 0/22 | 0/22 | 0/22 |

The row above is the unexecuted state, not a result. Replace it after the run.

## Regressions (baseline Pass observed, now Fail)

| Baseline AC | Baseline outcome (sheet 07) | Change outcome | Evidence |
|---|---|---|---|
| | | | |

## Defects found in this run

| Defect ID | AC / subcase | Expected | Actual | Reproduction | Evidence | Discovery source |
|---|---|---|---|---|---|---|
| | | | | | | |

## Deviations and notes

| Date | Note |
|---|---|
| | |
