# CR-01 Baseline Regression Execution Sheet (BASE-AC-01 to 19)

**Status:** Completed for requested online scope: 18 Pass, 0 Fail, 1 Blocked, 0 Not run (19 parents). Offline launch remains unverified at Amin’s request.
**Scope:** All 19 baseline checks against the changed CR-01 application. This is a separate regression run; the original baseline record is unchanged.
**Authority:** `shared/acceptance-criteria.md` §2 (frozen 2026-10-23, with the LOG-12 to LOG-15 clarifications added 2026-11-03). If this sheet and that file differ, that file wins.
**Common test procedures:** This populated evaluator run retains the common procedures, operands and expected outcomes. Results are specific to Spec Kit; the offline check remains in the denominator.
**Snapshot tested:** CR-01 `9e523775e31c42bb9f164ddf84e9ff736b2c2fdc`. Comparison baseline `88f56d0979e2fae1fb5d59e8681975e3a1fc2a27`.

## Run record (fill in once per track)

| Field | Value |
|---|---|
| Track | GitHub Spec Kit — CR-01 (changed snapshot) |
| Evaluator (name, role, prior exposure to this track) | Amin — methodology, independent of implementation per team record; AI-assisted by Codex. Codex performed the earlier baseline evaluation and inspected UI/build code to locate controls. This run uses real OS mouse/keyboard events and screenshots, not a claim of manual execution of every check by Amin or two independent human reviews. |
| Date / time / timezone | 2026-11-05, EET (UTC+02:00). CR GUI: 2026-11-05T23:14:45.478878+02:00 to 2026-11-05T23:16:25.952670+02:00; regression GUI: 2026-11-05T23:16:48.961164+02:00 to 2026-11-05T23:18:25.006097+02:00. |
| Snapshot (full commit SHA or tag; clean or dirty) | `speckit-cr01-frozen` resolves to `9e523775e31c42bb9f164ddf84e9ff736b2c2fdc`; fresh detached local clone; tracked tree clean before/after evaluation. |
| OS / version | macOS 27.0.1 (26A434), Apple Silicon aarch64 |
| JDK version and `JAVA_HOME` | Amazon Corretto 22.0.2; `/Library/Java/JavaVirtualMachines/amazon-corretto-22.jdk/Contents/Home` |
| Maven version | Apache Maven 3.9.16, portable workspace installation |
| Build and run command used | `mvn -B -Dmaven.repo.local=<workspace-cache> clean verify`; `java -jar target/baseline-calculator-1.0.0.jar`; separate live GUI harness runs load the same unmodified JAR. See README.md and build.txt for commands/outcomes. |
| Display available (GUI run) or headless | Display available. Native Maven suite explicitly headless; separate live window automation with Java Robot supplies OS mouse/keyboard input. Swing is queried for control positions and actual output; no setText, doClick or engine calls supply outcomes. |
| Adopted protocol and rubric revision | draft-5 identified by Thanh as adopted in PR #15. Formal adoption date/reference remains unavailable and shared headers still say proposal. Common acceptance/check-sheet revision: `42e9c41`. No subjective rubric ratings or adoption date invented. |

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
| `BASE-AC-01` | `BASE-FR-01` | Disable networking. Launch with the documented command. | GUI opens. Record a screenshot or note showing the network state and the window. | Manual; GUI | Blocked | Offline launch skipped under Amin’s standing instruction following the earlier unsuccessful offline setup. No network settings changed during this CR-01 evaluation; offline capability remains unverified. | See README.md, offline deviation. |
| `BASE-AC-02` | `BASE-FR-02` | Enter integer `7` in First number. | Accepted and displayed clearly. | GUI | Pass | BASE-AC-02: first=7; second=; result=; error= | [BASE-AC-02](regression/screenshots/BASE-AC-02.png); [raw records](regression/gui-observations.tsv). |
| `BASE-AC-03` | `BASE-FR-02` | Enter decimal `1.5` in First number. | Accepted and displayed clearly. | GUI | Pass | BASE-AC-03: first=1.5; second=; result=; error= | [BASE-AC-03](regression/screenshots/BASE-AC-03.png); [raw records](regression/gui-observations.tsv). |
| `BASE-AC-04` | `BASE-FR-03`, `BASE-FR-04` | `7 + 5`, click Calculate. | `12` | GUI | Pass | BASE-AC-04: first=7; second=5; result=12; error= | [BASE-AC-04](regression/screenshots/BASE-AC-04.png); [raw records](regression/gui-observations.tsv). |
| `BASE-AC-05` | `BASE-FR-03`, `BASE-FR-04` | `7 - 5`, click Calculate. | `2` | GUI | Pass | BASE-AC-05: first=7; second=5; result=2; error= | [BASE-AC-05](regression/screenshots/BASE-AC-05.png); [raw records](regression/gui-observations.tsv). |
| `BASE-AC-06` | `BASE-FR-03`, `BASE-FR-04` | `7 * 5`, click Calculate. | `35` | GUI | Pass | BASE-AC-06: first=7; second=5; result=35; error= | [BASE-AC-06](regression/screenshots/BASE-AC-06.png); [raw records](regression/gui-observations.tsv). |
| `BASE-AC-07` | `BASE-FR-03`, `BASE-FR-04`, `BASE-FR-07` | `7 / 2`, click Calculate. | `3.5` | GUI | Pass | BASE-AC-07: first=7; second=2; result=3.5; error= | [BASE-AC-07](regression/screenshots/BASE-AC-07.png); [raw records](regression/gui-observations.tsv). |
| `BASE-AC-08` | Numeric behaviour | `-7 + 2`, click Calculate. | `-5` | GUI | Pass | BASE-AC-08: first=-7; second=2; result=-5; error= | [BASE-AC-08](regression/screenshots/BASE-AC-08.png); [raw records](regression/gui-observations.tsv). |
| `BASE-AC-09` | Numeric behaviour | `1.2 + 2.3`, click Calculate. | Exactly `3.5` | GUI | Pass | BASE-AC-09: first=1.2; second=2.3; result=3.5; error= | [BASE-AC-09](regression/screenshots/BASE-AC-09.png); [raw records](regression/gui-observations.tsv). |
| `BASE-AC-10` | `BASE-FR-05` | Enter values, select an operation, calculate, then Clear. | Inputs, operation, result and error reset to the initial state. | GUI | Pass | BASE-AC-10: first=; second=; result=; error=; BASE-AC-10-error: first=; second=; result=; error= | [BASE-AC-10](regression/screenshots/BASE-AC-10.png), [BASE-AC-10-error](regression/screenshots/BASE-AC-10-error.png); [raw records](regression/gui-observations.tsv). |
| `BASE-AC-11` | `BASE-FR-06` | Calculate with (a) empty first, (b) empty second, (c) both empty, (d) a spaces-only field. | Clear error for each (suggested "Enter both numbers"); no result; no crash. | GUI; 4 subcases | Pass | BASE-AC-11-1: first=; second=5; result=; error=Enter both numbers; BASE-AC-11-2: first=7; second=; result=; error=Enter both numbers; BASE-AC-11-3: first=; second=; result=; error=Enter both numbers; BASE-AC-11-4: first=   ; second=5; result=; error=Enter both numbers | [BASE-AC-11-1](regression/screenshots/BASE-AC-11-1.png), [BASE-AC-11-2](regression/screenshots/BASE-AC-11-2.png), [BASE-AC-11-3](regression/screenshots/BASE-AC-11-3.png), [BASE-AC-11-4](regression/screenshots/BASE-AC-11-4.png); [raw records](regression/gui-observations.tsv). |
| `BASE-AC-12` | `BASE-FR-06` | Calculate `abc`, `1.2.3`, `1,5`, `1e3`, `--1`, `.`, `.5`, `5.`, `-` as First number with a valid Second number. | "Invalid number" feedback for each; no crash. | GUI; 9 subcases | Pass | BASE-AC-12-1: first=abc; second=2; result=; error=Invalid number; BASE-AC-12-2: first=1.2.3; second=2; result=; error=Invalid number; BASE-AC-12-3: first=1,5; second=2; result=; error=Invalid number; BASE-AC-12-4: first=1e3; second=2; result=; error=Invalid number; BASE-AC-12-5: first=--1; second=2; result=; error=Invalid number; BASE-AC-12-6: first=.; second=2; result=; error=Invalid number; BASE-AC-12-7: first=.5; second=2; result=; error=Invalid number; BASE-AC-12-8: first=5.; second=2; result=; error=Invalid number; BASE-AC-12-9: first=-; second=2; result=; error=Invalid number | [BASE-AC-12-1](regression/screenshots/BASE-AC-12-1.png), [BASE-AC-12-2](regression/screenshots/BASE-AC-12-2.png), [BASE-AC-12-3](regression/screenshots/BASE-AC-12-3.png), [BASE-AC-12-4](regression/screenshots/BASE-AC-12-4.png), [BASE-AC-12-5](regression/screenshots/BASE-AC-12-5.png), [BASE-AC-12-6](regression/screenshots/BASE-AC-12-6.png), [BASE-AC-12-7](regression/screenshots/BASE-AC-12-7.png), [BASE-AC-12-8](regression/screenshots/BASE-AC-12-8.png), [BASE-AC-12-9](regression/screenshots/BASE-AC-12-9.png); [raw records](regression/gui-observations.tsv). |
| `BASE-AC-13` | `BASE-FR-07` | (a) `7 / 0`, (b) `7 / 0.0`. | Error (suggested "Cannot divide by zero"); no infinity or NaN; no crash. | GUI; 2 subcases | Pass | BASE-AC-13-1: first=7; second=0; result=; error=Cannot divide by zero; BASE-AC-13-2: first=7; second=0.0; result=; error=Cannot divide by zero | [BASE-AC-13-1](regression/screenshots/BASE-AC-13-1.png), [BASE-AC-13-2](regression/screenshots/BASE-AC-13-2.png); [raw records](regression/gui-observations.tsv). |
| `BASE-AC-14` | `BASE-FR-08` | Trigger an input error, correct the input, calculate again. | Correct result is shown. | GUI; stateful | Pass | BASE-AC-14: first=1.5; second=2; result=3.5; error= | [BASE-AC-14](regression/screenshots/BASE-AC-14.png); [raw records](regression/gui-observations.tsv). |
| `BASE-AC-15` | `BASE-NFR-05` | Clean checkout of the frozen commit. Follow the documented build and run steps. | Builds and launches, or prerequisites are documented. Record exact commands and exit status. | Build; clean clone | Pass | Fresh clone at frozen SHA, native clean verify exited 0: 116 tests, 0 failures, 0 errors, 0 skipped. Documented JAR launched in a real window; see native-launch.png. | [Build](build.txt), [environment](environment.txt), [native launch](native-launch.png). |
| `BASE-AC-16` | Regression/usability | Run three or more different calculations in sequence without restarting. | Each result matches the current operands and operation. | GUI; stateful | Pass | BASE-AC-16-1: first=7; second=5; result=12; error=; BASE-AC-16-2: first=7; second=2; result=3.5; error=; BASE-AC-16-3: first=-7; second=2; result=-5; error= | [BASE-AC-16-1](regression/screenshots/BASE-AC-16-1.png), [BASE-AC-16-2](regression/screenshots/BASE-AC-16-2.png), [BASE-AC-16-3](regression/screenshots/BASE-AC-16-3.png); [raw records](regression/gui-observations.tsv). |
| `BASE-AC-17` | `BASE-FR-06` | Two valid numbers, no operation selected, click Calculate. | Error (suggested "Select an operation"); no result; no crash. | GUI | Pass | BASE-AC-17: first=7; second=5; result=; error=Select an operation | [BASE-AC-17](regression/screenshots/BASE-AC-17.png); [raw records](regression/gui-observations.tsv). |
| `BASE-AC-18` | `BASE-FR-04`, `BASE-FR-07` | (a) `0 / 5`, (b) `1 / 3`, (c) `1 / 2048`, (d) `-1 / 2048`. | (a) `0`; (b) `0.3333333333`; (c) `0.0004882813`; (d) `-0.0004882813` | GUI; 4 subcases | Pass | BASE-AC-18-1: first=0; second=5; result=0; error=; BASE-AC-18-2: first=1; second=3; result=0.3333333333; error=; BASE-AC-18-3: first=1; second=2048; result=0.0004882813; error=; BASE-AC-18-4: first=-1; second=2048; result=-0.0004882813; error= | [BASE-AC-18-1](regression/screenshots/BASE-AC-18-1.png), [BASE-AC-18-2](regression/screenshots/BASE-AC-18-2.png), [BASE-AC-18-3](regression/screenshots/BASE-AC-18-3.png), [BASE-AC-18-4](regression/screenshots/BASE-AC-18-4.png); [raw records](regression/gui-observations.tsv). |
| `BASE-AC-19` | `BASE-FR-04` | Two valid numbers, select an operation, press Enter in an operand field. | Same result as the Calculate button. Record which field was used. | GUI | Pass | BASE-AC-19: first=7; second=5; result=12; error= | [BASE-AC-19](regression/screenshots/BASE-AC-19.png); [raw records](regression/gui-observations.tsv). |

## Summary (fill in after the run)

| P | F | B | N | D | Verified pass coverage P/D | Decisive (P+F)/D | Complete C/D |
|---|---|---|---|---|---|---|---|
| 18 | 0 | 1 | 0 | 19 | 18/19 (94.74%) | 18/19 (94.74%) | 18/19 (94.74%) |

The denominator is unchanged. Missing offline evidence is not a Pass and is not a product Fail. CR-AC-21 includes the blocked offline subcheck; it therefore cannot be marked Pass.

## Defects found in this run

| Defect ID | AC / subcase | Expected | Actual | Reproduction | Evidence | Discovery source |
|---|---|---|---|---|---|---|
| None observed | — | — | — | — | Raw observations and screenshots | Independent evaluator automation |

## Deviations and notes

| Date | Note |
|---|---|
| | |

## Detailed observations

| Observation | Timestamp | Outcome | Expected | Actual | Evidence |
|---|---|---|---|---|---|
| BASE-AC-02 | 2026-11-05T23:16:48.961164+02:00 | Pass | 7 displayed | first=7; second=; result=; error= | [screenshot](regression/screenshots/BASE-AC-02.png) |
| BASE-AC-03 | 2026-11-05T23:16:50.411216+02:00 | Pass | 1.5 displayed | first=1.5; second=; result=; error= | [screenshot](regression/screenshots/BASE-AC-03.png) |
| BASE-AC-04 | 2026-11-05T23:16:53.080071+02:00 | Pass | 12 | first=7; second=5; result=12; error= | [screenshot](regression/screenshots/BASE-AC-04.png) |
| BASE-AC-05 | 2026-11-05T23:16:55.750852+02:00 | Pass | 2 | first=7; second=5; result=2; error= | [screenshot](regression/screenshots/BASE-AC-05.png) |
| BASE-AC-06 | 2026-11-05T23:16:58.426340+02:00 | Pass | 35 | first=7; second=5; result=35; error= | [screenshot](regression/screenshots/BASE-AC-06.png) |
| BASE-AC-07 | 2026-11-05T23:17:01.096411+02:00 | Pass | 3.5 | first=7; second=2; result=3.5; error= | [screenshot](regression/screenshots/BASE-AC-07.png) |
| BASE-AC-08 | 2026-11-05T23:17:03.862509+02:00 | Pass | -5 | first=-7; second=2; result=-5; error= | [screenshot](regression/screenshots/BASE-AC-08.png) |
| BASE-AC-09 | 2026-11-05T23:17:06.940994+02:00 | Pass | 3.5 | first=1.2; second=2.3; result=3.5; error= | [screenshot](regression/screenshots/BASE-AC-09.png) |
| BASE-AC-10 | 2026-11-05T23:17:09.923924+02:00 | Pass | Inputs, operation, result and error reset | first=; second=; result=; error= | [screenshot](regression/screenshots/BASE-AC-10.png) |
| BASE-AC-10-error | 2026-11-05T23:17:12.800452+02:00 | Pass | Error cleared | first=; second=; result=; error= | [screenshot](regression/screenshots/BASE-AC-10-error.png) |
| BASE-AC-11-1 | 2026-11-05T23:17:15.368270+02:00 | Pass | Enter both numbers | first=; second=5; result=; error=Enter both numbers | [screenshot](regression/screenshots/BASE-AC-11-1.png) |
| BASE-AC-11-2 | 2026-11-05T23:17:17.933367+02:00 | Pass | Enter both numbers | first=7; second=; result=; error=Enter both numbers | [screenshot](regression/screenshots/BASE-AC-11-2.png) |
| BASE-AC-11-3 | 2026-11-05T23:17:20.394933+02:00 | Pass | Enter both numbers | first=; second=; result=; error=Enter both numbers | [screenshot](regression/screenshots/BASE-AC-11-3.png) |
| BASE-AC-11-4 | 2026-11-05T23:17:23.266304+02:00 | Pass | Enter both numbers | first=   ; second=5; result=; error=Enter both numbers | [screenshot](regression/screenshots/BASE-AC-11-4.png) |
| BASE-AC-12-1 | 2026-11-05T23:17:26.131064+02:00 | Pass | Invalid number | first=abc; second=2; result=; error=Invalid number | [screenshot](regression/screenshots/BASE-AC-12-1.png) |
| BASE-AC-12-2 | 2026-11-05T23:17:29.200426+02:00 | Pass | Invalid number | first=1.2.3; second=2; result=; error=Invalid number | [screenshot](regression/screenshots/BASE-AC-12-2.png) |
| BASE-AC-12-3 | 2026-11-05T23:17:32.092387+02:00 | Pass | Invalid number | first=1,5; second=2; result=; error=Invalid number | [screenshot](regression/screenshots/BASE-AC-12-3.png) |
| BASE-AC-12-4 | 2026-11-05T23:17:34.976780+02:00 | Pass | Invalid number | first=1e3; second=2; result=; error=Invalid number | [screenshot](regression/screenshots/BASE-AC-12-4.png) |
| BASE-AC-12-5 | 2026-11-05T23:17:37.833668+02:00 | Pass | Invalid number | first=--1; second=2; result=; error=Invalid number | [screenshot](regression/screenshots/BASE-AC-12-5.png) |
| BASE-AC-12-6 | 2026-11-05T23:17:40.482640+02:00 | Pass | Invalid number | first=.; second=2; result=; error=Invalid number | [screenshot](regression/screenshots/BASE-AC-12-6.png) |
| BASE-AC-12-7 | 2026-11-05T23:17:43.247825+02:00 | Pass | Invalid number | first=.5; second=2; result=; error=Invalid number | [screenshot](regression/screenshots/BASE-AC-12-7.png) |
| BASE-AC-12-8 | 2026-11-05T23:17:46.022510+02:00 | Pass | Invalid number | first=5.; second=2; result=; error=Invalid number | [screenshot](regression/screenshots/BASE-AC-12-8.png) |
| BASE-AC-12-9 | 2026-11-05T23:17:48.658164+02:00 | Pass | Invalid number | first=-; second=2; result=; error=Invalid number | [screenshot](regression/screenshots/BASE-AC-12-9.png) |
| BASE-AC-13-1 | 2026-11-05T23:17:51.305248+02:00 | Pass | Cannot divide by zero | first=7; second=0; result=; error=Cannot divide by zero | [screenshot](regression/screenshots/BASE-AC-13-1.png) |
| BASE-AC-13-2 | 2026-11-05T23:17:54.163288+02:00 | Pass | Cannot divide by zero | first=7; second=0.0; result=; error=Cannot divide by zero | [screenshot](regression/screenshots/BASE-AC-13-2.png) |
| BASE-AC-14 | 2026-11-05T23:17:58.371626+02:00 | Pass | Recover abc+2 error to 1.5+2=3.5 | first=1.5; second=2; result=3.5; error= | [screenshot](regression/screenshots/BASE-AC-14.png) |
| BASE-AC-16-1 | 2026-11-05T23:18:01.006250+02:00 | Pass | 12 | first=7; second=5; result=12; error= | [screenshot](regression/screenshots/BASE-AC-16-1.png) |
| BASE-AC-16-2 | 2026-11-05T23:18:03.340615+02:00 | Pass | 3.5 | first=7; second=2; result=3.5; error= | [screenshot](regression/screenshots/BASE-AC-16-2.png) |
| BASE-AC-16-3 | 2026-11-05T23:18:05.783388+02:00 | Pass | -5 | first=-7; second=2; result=-5; error= | [screenshot](regression/screenshots/BASE-AC-16-3.png) |
| BASE-AC-17 | 2026-11-05T23:18:08.118066+02:00 | Pass | Select an operation | first=7; second=5; result=; error=Select an operation | [screenshot](regression/screenshots/BASE-AC-17.png) |
| BASE-AC-18-1 | 2026-11-05T23:18:10.774190+02:00 | Pass | 0 | first=0; second=5; result=0; error= | [screenshot](regression/screenshots/BASE-AC-18-1.png) |
| BASE-AC-18-2 | 2026-11-05T23:18:13.408423+02:00 | Pass | 0.3333333333 | first=1; second=3; result=0.3333333333; error= | [screenshot](regression/screenshots/BASE-AC-18-2.png) |
| BASE-AC-18-3 | 2026-11-05T23:18:16.365003+02:00 | Pass | 0.0004882813 | first=1; second=2048; result=0.0004882813; error= | [screenshot](regression/screenshots/BASE-AC-18-3.png) |
| BASE-AC-18-4 | 2026-11-05T23:18:19.425845+02:00 | Pass | -0.0004882813 | first=-1; second=2048; result=-0.0004882813; error= | [screenshot](regression/screenshots/BASE-AC-18-4.png) |
| BASE-AC-19 | 2026-11-05T23:18:25.006097+02:00 | Pass | Enter in First number gives same 12 as Calculate | first=7; second=5; result=12; error= | [screenshot](regression/screenshots/BASE-AC-19.png) |

## Deviations, provenance and interpretation

- Offline launch was not attempted, preserving Amin’s explicit instruction to skip it. The earlier offline prerequisite problem remains unresolved; this run does not disable networking. BASE-AC-01 and aggregate CR-AC-21 remain Blocked/unverified.
- The original baseline record at commit 88f56d0 is intact. A regression requires a previously observed Pass becoming Fail under comparable conditions. There are 0 observed regressions among 18 comparable parents; offline regression status is undetermined.
- Native quickstart shell/paths are adapted from Windows PowerShell to macOS. A separate local Maven cache is used. The original implementation, POM, tests, acceptance criteria and BMAD evidence are unchanged. Native test success alone is not GUI evidence.
- CR-FR-03 Enter support for each unary operation and LOG-17 ignoring malformed Second number input have supplementary SUP-ENTER and SUP-SECOND records. These do not increase parent denominators. CR-AC-16/17/20 were exercised for all three unary operations.
- Evaluator assistance is Codex (GPT-6 based agent; exact serving model identifier unavailable). This is not a second human review. Prior baseline exposure and AI assistance are disclosed. Results are limited to the recorded inputs; no general defect-free or tool-superiority claim is made. Relevant validity limits: VT-C02, VT-C06, VT-I05 and independent-human-review limits.
- No formal subjective rubric ratings, change-impact metrics, effort estimates or reconstructed operator evidence are included. This deliverable is the requested acceptance/regression evaluation.
