# Evaluator Execution Sheet — CR-01 (CR-AC-01 to 22)

**Status:** Completed BMAD evaluator-reported run; all 22 parent ACs marked Pass. **Scope:** CR-01 change phase only; denominator 22 parent ACs. **Authority:** `shared/acceptance-criteria.md` §3 and `shared/change-request-CR-01.md` §6 v0.2, with LOG-17 to LOG-21 clarifications. **Track:** BMAD only. **Snapshot rule:** BMAD frozen tag `bmad-cr01-frozen` (commit `72f2783`) is the required snapshot; evaluator did not report the actual checked-out commit or working-tree status. **Regression:** CR-AC-21 is evaluator-reported as Pass based on a repeat check of baseline behaviour; the separate regression record is `09-baseline-regression-cr01.md`; individual actual outputs were not separately submitted.

## Run record (fill in once per track)

| Field                                                | Value |
| :--------------------------------------------------- | :---- |
| Track                                                | BMAD |
| Evaluator (name, role, prior exposure to this track) | Thanh — Comparison Frameworks and Metrics; independent manual evaluator. Prior exposure: reviewed research and evaluation materials; did not implement the BMAD calculator. |
| Date / time / timezone | 2026-11-05;
| Snapshot (full commit SHA or tag; clean or dirty)    | Not confirmed (required frozen tag: `bmad-cr01-frozen`, commit `72f2783`) |
| OS / version                                         | OS / version | Windows 11 |
| JDK version and JAVA_HOME | Java 22 (Eclipse Temurin 22.0.2); `JAVA_HOME` path not recorded |
| Maven version | Apache Maven 3.9.11 |
| Build and run command used | `mvn -q test`; `mvn -q compile exec:java` |
| Display available (GUI run) or headless              | GUI displayed successfully; no crash reported |
| Adopted protocol and rubric revision | Protocol: `shared/experiment-protocol.md`, draft-5 (2026-11-03). Rubric: `shared/evaluation-rubric.md`, draft-5 (2026-11-03). Evaluator confirms official team adoption. Adoption decision date/reference not supplied. |

## Rules

- Outcomes are Pass, Fail, Blocked or Not run. Pass only if the behaviour was observed in this run.
- Start each check from the initial state unless the row says stateful.
- The first planned run is the primary record. Keep reruns separate, with the reason.
- Parent outcome: Fail if any subcase fails; else Blocked if any is blocked; else Not run if any is unexecuted; else Pass.
- Suggested message wording is not an exact-string condition; the cases must be clearly distinguishable.
- Engine-only unit tests do not establish GUI behaviour. Name the layer you observed.

## Checks

| Test ID    | Procedure (same operands for both tracks)                                                                                                    | Expected                                                                            | Layer / subcases       | Outcome | Observed / actual | Evidence ref |
| :--------- | :------------------------------------------------------------------------------------------------------------------------------------------- | :---------------------------------------------------------------------------------- | :--------------------- | :------ | :---------------- | :----------- |
| `CR-AC-01` | Launch the application and inspect the GUI.                                                                                                  | Square, square-root and reciprocal controls are visible and identifiable.           | GUI                    | Pass | All three new operation controls visible. | Evaluator report; no external artifact supplied |
| `CR-AC-02` | Select square, enter `-3` in First number, leave Second number empty, calculate.                                                             | `9`                                                                                 | GUI                    | Pass | 9 | Evaluator report; no external artifact supplied |
| `CR-AC-03` | Select square, enter `1.2`, calculate.                                                                                                       | `1.44`                                                                              | GUI                    | Pass | 1.44 | Evaluator report; no external artifact supplied |
| `CR-AC-04` | Select square, enter `0`, calculate.                                                                                                         | `0`                                                                                 | GUI                    | Pass | 0 | Evaluator report; no external artifact supplied |
| `CR-AC-05` | Select square root, enter `9`, calculate.                                                                                                    | `3`                                                                                 | GUI                    | Pass | 3 | Evaluator report; no external artifact supplied |
| `CR-AC-06` | Select square root, enter `2`, calculate.                                                                                                    | `1.4142135624`                                                                      | GUI                    | Pass | 1.4142135624 | Evaluator report; no external artifact supplied |
| `CR-AC-07` | Select square root, enter `0`, calculate.                                                                                                    | `0`                                                                                 | GUI                    | Pass | 0 | Evaluator report; no external artifact supplied |
| `CR-AC-08` | Select square root, enter `-1`, calculate.                                                                                                   | The specified negative-input error appears; no successful result is shown.          | GUI                    | Pass | Negative-input error displayed clearly; no successful result. | Evaluator report; no external artifact supplied |
| `CR-AC-09` | Select reciprocal, enter `4`, calculate.                                                                                                     | `0.25`                                                                              | GUI                    | Pass | 0.25 | Evaluator report; no external artifact supplied |
| `CR-AC-10` | Select reciprocal, enter `3`, calculate.                                                                                                     | `0.3333333333`                                                                      | GUI                    | Pass | 0.3333333333 | Evaluator report; no external artifact supplied |
| `CR-AC-11` | Select reciprocal, enter `-4`, calculate.                                                                                                    | `-0.25`                                                                             | GUI                    | Pass | -0.25 | Evaluator report; no external artifact supplied |
| `CR-AC-12` | Select reciprocal, enter `0`, calculate.                                                                                                     | The baseline division-by-zero error appears; no successful result is shown.         | GUI                    | Pass | Division-by-zero error displayed; no successful result. | Evaluator report; no external artifact supplied |
| `CR-AC-13` | Repeat CR-AC-12 with `0.0` and `-0.0`.                                                                                                       | Each input is treated as zero and rejected safely.                                  | GUI; 2 subcases        | Pass | Both `0.0` and `-0.0` rejected with division-by-zero feedback. | Evaluator report; no external artifact supplied |
| `CR-AC-14` | Select each unary operation with an empty First number and calculate.                                                                        | Clear validation feedback; no crash.                                                | GUI; 3 subcases        | Pass | Empty First number rejected for square, square root and reciprocal. | Evaluator report; no external artifact supplied |
| `CR-AC-15` | Select each unary operation and enter malformed input such as `abc`.                                                                         | Baseline invalid-number feedback; no crash.                                         | GUI; 3 subcases        | Pass | `abc` rejected for square, square root and reciprocal. | Evaluator report; no external artifact supplied |
| `CR-AC-16` | Select a unary operation, enter a valid First number, leave Second number empty.                                                             | The calculation succeeds without a second operand.                                  | GUI                    | Pass | Unary operation succeeded with Second number empty. | Evaluator report; no external artifact supplied |
| `CR-AC-17` | Select a unary operation, enter a valid First number and a different value in Second number, calculate.                                      | The result depends only on First number.                                            | GUI                    | Pass | Unary result unaffected by invalid Second number. | Evaluator report; no external artifact supplied |
| `CR-AC-18` | Trigger a negative square-root error, correct the input, calculate again.                                                                    | The application recovers and shows the correct result.                              | GUI; stateful          | Pass | Error cleared after correcting input; calculation succeeded. | Evaluator report; no external artifact supplied |
| `CR-AC-19` | Trigger a reciprocal division-by-zero error, correct the input, calculate again.                                                             | The application recovers and shows the correct result.                              | GUI; stateful          | Pass | Error cleared after correcting input; calculation succeeded. | Evaluator report; no external artifact supplied |
| `CR-AC-20` | Use Clear after a unary calculation.                                                                                                         | The calculator returns to the agreed baseline initial state.                        | GUI                    | Pass | Clear reset all fields to initial state. | Evaluator report; no external artifact supplied |
| `CR-AC-21` | Execute all `BASE-AC-01` to `BASE-AC-19` against the changed application. Record each individually (use a fresh copy of the baseline sheet). | All baseline criteria retain their required behaviour.                              | Regression; 19 parents | Pass | All got results like the first baseline test; everything working as expected like the baseline (evaluator-reported). | `09-baseline-regression-cr01.md` (evaluator-reported regression) |
| `CR-AC-22` | Follow the documented build and test instructions for the changed application.                                                               | Builds and tests execute. Prerequisites, test outcomes and failures are documented. | Build                  | Pass | JDK 22, Maven 3.9.11; calculator GUI launched as expected without crashing. Automated test counts/output not provided. | Evaluator report; build logs not supplied |

## Summary (fill in after the run)

| P  | F  | B  | N  | D  | Verified pass coverage P/D | Decisive (P+F)/D | Complete C/D |
| :- | :- | :- | :- | :- | :------------------------- | :--------------- | :----------- |
| 22 | 0 | 0 | 0 | 22 | 22/22 | 22/22 | 22/22 |

All 22 parent outcomes are evaluator-reported Pass. Detailed build test counts and independent evidence were not supplied.

## Regressions (baseline Pass observed, now Fail)

| Baseline AC | Baseline outcome (sheet 07) | Change outcome | Evidence |
| :---------- | :-------------------------- | :------------- | :------- |
| None reported | — | — | Evaluator reported baseline behaviour unchanged; see `09-baseline-regression-cr01.md` (aggregate evaluator report; per-case outputs not supplied) |

## Defects found in this run

| Defect ID | AC / subcase | Expected | Actual | Reproduction | Evidence | Discovery source |
| :-------- | :----------- | :------- | :----- | :----------- | :------- | :--------------- |
| None reported | — | — | — | — | Evaluator report | Manual GUI evaluation |

## Deviations and notes

| Date | Note |
| :--- | :--- |
| Not supplied | Evaluator reported all 22 criteria Pass; CR-AC-21 matched the first baseline test results. Regression recorded separately in `09-baseline-regression-cr01.md`; actual checkout, exact commands, Maven test totals and log evidence not provided. |
