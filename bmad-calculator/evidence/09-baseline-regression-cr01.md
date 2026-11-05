# Evaluator Execution Sheet — CR-01 Baseline Regression (BASE-AC-01 to 19)
**Status:** Completed evaluator-reported BMAD CR-01 baseline regression. **Scope:** Repeat the 19 original baseline acceptance criteria against the **changed BMAD CR-01 application**, as required by `CR-AC-21` in Sheet 08. Denominator: 19 parent ACs; subcases do not increase it. **Authority:** `shared/acceptance-criteria.md` §2. **Original baseline record:** `07-evaluator-execution-sheet.md` (retained unchanged). **Changed snapshot required:** `bmad-cr01-frozen` (commit `72f2783`); actual checkout not confirmed by evaluator. **Evidence limitation:** Evaluator reported that everything worked as expected, like the first baseline test, but did not provide separate observed outputs for each regression check. The Pass labels below are a transcription of that overall report, **not individually evidenced rerun records**.

## Run record (fill in once per track)

| Field | Value |
|---|---|
| Track | BMAD CR-01 — baseline regression |
| Evaluator (name, role, prior exposure to this track) | Independent evaluator; name and prior exposure not confirmed for this run |
| Date / time / timezone | Not recorded for regression run |
| Snapshot (full commit SHA or tag; clean or dirty) | Not confirmed; required: `bmad-cr01-frozen` (`72f2783`) |
| OS / version | Not recorded for this run |
| JDK version and JAVA_HOME | JDK 22 reported; `JAVA_HOME` not provided |
| Maven version | Maven 3.9.11 reported |
| Build and run command used | Exact commands not provided for CR-01 run; GUI launch reported |
| Display available (GUI run) or headless | GUI launched successfully, no crash reported |
| Adopted protocol and rubric revision | Not separately confirmed for this regression run |

## Rules

Outcomes are Pass, Fail, Blocked or Not run. Pass only if the behaviour was observed in this run.

Start each check from the initial state unless the row says stateful.

The first planned run is the primary record. Keep reruns separate, with the reason.

Parent outcome: Fail if any subcase fails; else Blocked if any is blocked; else Not run if any is unexecuted; else Pass.

Suggested message wording is not an exact-string condition; the cases must be clearly distinguishable.

Do not invent a latency threshold for BASE-NFR-02. Record observations only.

Engine-only unit tests do not establish GUI behaviour. Name the layer you observed.

## Checks

| Test ID | Requirement | Procedure (same operands for both tracks) | Expected | Layer / subcases | Outcome | Observed / actual | Evidence ref |
|---|---|---|---|---|---|---|---|
| BASE-AC-01 | BASE-FR-01 | Disable networking. Launch with the documented command. | GUI opens. Record a screenshot or note showing the network state and the window. | Manual; GUI | Pass | Evaluator reports same expected behaviour as original baseline; per-case actual output not separately recorded | Evaluator aggregate regression statement; no per-case artifact |
| BASE-AC-02 | BASE-FR-02 | Enter integer 7 in First number. | Accepted and displayed clearly. | GUI | Pass | Evaluator reports same expected behaviour as original baseline; per-case actual output not separately recorded | Evaluator aggregate regression statement; no per-case artifact |
| BASE-AC-03 | BASE-FR-02 | Enter decimal 1.5 in First number. | Accepted and displayed clearly. | GUI | Pass | Evaluator reports same expected behaviour as original baseline; per-case actual output not separately recorded | Evaluator aggregate regression statement; no per-case artifact |
| BASE-AC-04 | BASE-FR-03, BASE-FR-04 | 7 + 5, click Calculate. | 12 | GUI | Pass | Evaluator reports same expected behaviour as original baseline; per-case actual output not separately recorded | Evaluator aggregate regression statement; no per-case artifact |
| BASE-AC-05 | BASE-FR-03, BASE-FR-04 | 7 - 5, click Calculate. | 2 | GUI | Pass | Evaluator reports same expected behaviour as original baseline; per-case actual output not separately recorded | Evaluator aggregate regression statement; no per-case artifact |
| BASE-AC-06 | BASE-FR-03, BASE-FR-04 | 7 * 5, click Calculate. | 35 | GUI | Pass | Evaluator reports same expected behaviour as original baseline; per-case actual output not separately recorded | Evaluator aggregate regression statement; no per-case artifact |
| BASE-AC-07 | BASE-FR-03, BASE-FR-04, BASE-FR-07 | 7 / 2, click Calculate. | 3.5 | GUI | Pass | Evaluator reports same expected behaviour as original baseline; per-case actual output not separately recorded | Evaluator aggregate regression statement; no per-case artifact |
| BASE-AC-08 | Numeric behaviour | -7 + 2, click Calculate. | -5 | GUI | Pass | Evaluator reports same expected behaviour as original baseline; per-case actual output not separately recorded | Evaluator aggregate regression statement; no per-case artifact |
| BASE-AC-09 | Numeric behaviour | 1.2 + 2.3, click Calculate. | Exactly 3.5 | GUI | Pass | Evaluator reports same expected behaviour as original baseline; per-case actual output not separately recorded | Evaluator aggregate regression statement; no per-case artifact |
| BASE-AC-10 | BASE-FR-05 | Enter values, select an operation, calculate, then Clear. | Inputs, operation, result and error reset to the initial state. | GUI | Pass | Evaluator reports same expected behaviour as original baseline; per-case actual output not separately recorded | Evaluator aggregate regression statement; no per-case artifact |
| BASE-AC-11 | BASE-FR-06 | Calculate with (a) empty first, (b) empty second, (c) both empty, (d) a spaces-only field. | Clear error for each (suggested "Enter both numbers"); no result; no crash. | GUI; 4 subcases | Pass | Evaluator reports same expected behaviour as original baseline; per-case actual output not separately recorded | Evaluator aggregate regression statement; no per-case artifact |
| BASE-AC-12 | BASE-FR-06 | Calculate abc, 1.2.3, 1,5, 1e3, --1, ., .5, 5., - as First number with a valid Second number. | "Invalid number" feedback for each; no crash. | GUI; 9 subcases | Pass | Evaluator reports same expected behaviour as original baseline; per-case actual output not separately recorded | Evaluator aggregate regression statement; no per-case artifact |
| BASE-AC-13 | BASE-FR-07 | (a) 7 / 0, (b) 7 / 0.0. | Error (suggested "Cannot divide by zero"); no infinity or NaN; no crash. | GUI; 2 subcases | Pass | Evaluator reports same expected behaviour as original baseline; per-case actual output not separately recorded | Evaluator aggregate regression statement; no per-case artifact |
| BASE-AC-14 | BASE-FR-08 | Trigger an input error, correct the input, calculate again. | Correct result is shown. | GUI; stateful | Pass | Evaluator reports same expected behaviour as original baseline; per-case actual output not separately recorded | Evaluator aggregate regression statement; no per-case artifact |
| BASE-AC-15 | BASE-NFR-05 | Clean checkout of the frozen commit. Follow the documented build and run steps. | Builds and launches, or prerequisites are documented. Record exact commands and exit status. | Build; clean clone | Pass | Evaluator reports same expected behaviour as original baseline; per-case actual output not separately recorded | Evaluator aggregate regression statement; no per-case artifact |
| BASE-AC-16 | Regression/usability | Run three or more different calculations in sequence without restarting. | Each result matches the current operands and operation. | GUI; stateful | Pass | Evaluator reports same expected behaviour as original baseline; per-case actual output not separately recorded | Evaluator aggregate regression statement; no per-case artifact |
| BASE-AC-17 | BASE-FR-06 | Two valid numbers, no operation selected, click Calculate. | Error (suggested "Select an operation"); no result; no crash. | GUI | Pass | Evaluator reports same expected behaviour as original baseline; per-case actual output not separately recorded | Evaluator aggregate regression statement; no per-case artifact |
| BASE-AC-18 | BASE-FR-04, BASE-FR-07 | (a) 0 / 5, (b) 1 / 3, (c) 1 / 2048, (d) -1 / 2048. | (a) 0; (b) 0.3333333333; (c) 0.0004882813; (d) -0.0004882813 | GUI; 4 subcases | Pass | Evaluator reports same expected behaviour as original baseline; per-case actual output not separately recorded | Evaluator aggregate regression statement; no per-case artifact |
| BASE-AC-19 | BASE-FR-04 | Two valid numbers, select an operation, press Enter in an operand field. | Same result as the Calculate button. Record which field was used. | GUI | Pass | Evaluator reports same expected behaviour as original baseline; per-case actual output not separately recorded | Evaluator aggregate regression statement; no per-case artifact |

## Summary (fill in after the run)

| P | F | B | N | D | Verified pass coverage P/D | Decisive (P+F)/D | Complete C/D |
|---:|---:|---:|---:|---:|---|---|---|
| 19 | 0 | 0 | 0 | 19 | 19/19 (evaluator-reported) | 19/19 (evaluator-reported) | 19/19 (evaluator-reported) |

**Interpretation:** The evaluator reported that the baseline functionality was rechecked on the changed calculator and all results matched the original baseline. This supports the reported `CR-AC-21: Pass`, but separate, item-by-item regression observations and artifacts were not supplied. The 19 Pass labels reflect the evaluator's aggregate statement; they should be individually reconfirmed if the protocol requires an independently auditable execution trail.

## Defects found in this run

| Defect ID | AC / subcase | Expected | Actual | Reproduction | Evidence | Discovery source |
|---|---|---|---|---|---|---|
| — | — | — | No defects reported | — | No defect artifacts supplied | Evaluator-reported regression |

## Deviations and notes

| Date | Note |
|---|---|
| Not supplied | Evaluator statement: "everything working as expected like the baseline". No per-test output or screenshot evidence was provided for this repeat run. |
| Not supplied | Actual checked-out CR-01 commit, network-disabled state for BASE-AC-01, individual subcase observations, Enter-key field, and clean-checkout details not documented for the regression run. |
| Not supplied | JDK 22, Maven 3.9.11, and successful GUI launch were reported for CR-01. Automated test counts, Maven logs, and exact commands not supplied. |
