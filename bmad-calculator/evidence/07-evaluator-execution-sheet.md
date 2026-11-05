# Evaluator Execution Sheet — Baseline (BASE-AC-01 to 19)

**Status:** Blank template. Nothing in this sheet has been executed. Every outcome is **Not run** until an evaluator fills it in from an observed run.
**Scope:** Baseline only. CR-01 is excluded. The denominator is 19 parent ACs; subcases never add to it.
**Authority:** `shared/acceptance-criteria.md` §2 (frozen 2026-10-23, with the LOG-12 to LOG-15 clarifications added 2026-11-03). If this sheet and that file differ, that file wins.
**Same sheet for both tracks.** This file is an identical copy in `bmad-calculator/evidence/` and `github-spec-kit-calculator/evidence/`. Use the same operands, order and expected outcomes. Do not edit one copy only.
**Snapshots to test (baseline):** BMAD: tag `baseline-frozen` = `740c78de062bb1edd8076e4a49dde768b0fe1e49`. Spec Kit: `88f56d0979e2fae1fb5d59e8681975e3a1fc2a27` (branch `george/spec-kit-baseline`), designated by Varun in experiment log LOG-26; George's confirmation pending.

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
- Do not invent a latency threshold for `BASE-NFR-02`. Record observations only.
- Engine-only unit tests do not establish GUI behaviour. Name the layer you observed.

## Checks

| Test ID | Requirement | Procedure (same operands for both tracks) | Expected | Layer / subcases | Outcome | Observed / actual | Evidence ref |
|---|---|---|---|---|---|---|---|
| `BASE-AC-01` | `BASE-FR-01` | Disable networking. Launch with the documented command. | GUI opens. Record a screenshot or note showing the network state and the window. | Manual; GUI | Not run | | |
| `BASE-AC-02` | `BASE-FR-02` | Enter integer `7` in First number. | Accepted and displayed clearly. | GUI | Not run | | |
| `BASE-AC-03` | `BASE-FR-02` | Enter decimal `1.5` in First number. | Accepted and displayed clearly. | GUI | Not run | | |
| `BASE-AC-04` | `BASE-FR-03`, `BASE-FR-04` | `7 + 5`, click Calculate. | `12` | GUI | Not run | | |
| `BASE-AC-05` | `BASE-FR-03`, `BASE-FR-04` | `7 - 5`, click Calculate. | `2` | GUI | Not run | | |
| `BASE-AC-06` | `BASE-FR-03`, `BASE-FR-04` | `7 * 5`, click Calculate. | `35` | GUI | Not run | | |
| `BASE-AC-07` | `BASE-FR-03`, `BASE-FR-04`, `BASE-FR-07` | `7 / 2`, click Calculate. | `3.5` | GUI | Not run | | |
| `BASE-AC-08` | Numeric behaviour | `-7 + 2`, click Calculate. | `-5` | GUI | Not run | | |
| `BASE-AC-09` | Numeric behaviour | `1.2 + 2.3`, click Calculate. | Exactly `3.5` | GUI | Not run | | |
| `BASE-AC-10` | `BASE-FR-05` | Enter values, select an operation, calculate, then Clear. | Inputs, operation, result and error reset to the initial state. | GUI | Not run | | |
| `BASE-AC-11` | `BASE-FR-06` | Calculate with (a) empty first, (b) empty second, (c) both empty, (d) a spaces-only field. | Clear error for each (suggested "Enter both numbers"); no result; no crash. | GUI; 4 subcases | Not run | | |
| `BASE-AC-12` | `BASE-FR-06` | Calculate `abc`, `1.2.3`, `1,5`, `1e3`, `--1`, `.`, `.5`, `5.`, `-` as First number with a valid Second number. | "Invalid number" feedback for each; no crash. | GUI; 9 subcases | Not run | | |
| `BASE-AC-13` | `BASE-FR-07` | (a) `7 / 0`, (b) `7 / 0.0`. | Error (suggested "Cannot divide by zero"); no infinity or NaN; no crash. | GUI; 2 subcases | Not run | | |
| `BASE-AC-14` | `BASE-FR-08` | Trigger an input error, correct the input, calculate again. | Correct result is shown. | GUI; stateful | Not run | | |
| `BASE-AC-15` | `BASE-NFR-05` | Clean checkout of the frozen commit. Follow the documented build and run steps. | Builds and launches, or prerequisites are documented. Record exact commands and exit status. | Build; clean clone | Not run | | |
| `BASE-AC-16` | Regression/usability | Run three or more different calculations in sequence without restarting. | Each result matches the current operands and operation. | GUI; stateful | Not run | | |
| `BASE-AC-17` | `BASE-FR-06` | Two valid numbers, no operation selected, click Calculate. | Error (suggested "Select an operation"); no result; no crash. | GUI | Not run | | |
| `BASE-AC-18` | `BASE-FR-04`, `BASE-FR-07` | (a) `0 / 5`, (b) `1 / 3`, (c) `1 / 2048`, (d) `-1 / 2048`. | (a) `0`; (b) `0.3333333333`; (c) `0.0004882813`; (d) `-0.0004882813` | GUI; 4 subcases | Not run | | |
| `BASE-AC-19` | `BASE-FR-04` | Two valid numbers, select an operation, press Enter in an operand field. | Same result as the Calculate button. Record which field was used. | GUI | Not run | | |

## Summary (fill in after the run)

| P | F | B | N | D | Verified pass coverage P/D | Decisive (P+F)/D | Complete C/D |
|---|---|---|---|---|---|---|---|
| 0 | 0 | 0 | 19 | 19 | 0/19 | 0/19 | 0/19 |

The row above is the unexecuted state, not a result. Replace it after the run.

## Defects found in this run

| Defect ID | AC / subcase | Expected | Actual | Reproduction | Evidence | Discovery source |
|---|---|---|---|---|---|---|
| | | | | | | |

## Deviations and notes

| Date | Note |
|---|---|
| | |
