# Evaluator Execution Sheet — CR-01 (CR-AC-01 to 22)

**Status:** Completed including Amin’s supplementary manual offline-launch report: **22 Pass, 0 Fail, 0 Blocked, 0 Not run** (22 parents). Earlier blocked results remain documented in the history.
**Scope:** CR-01 change phase only. The denominator is 22 parent ACs; subcases never add to it.
**Authority:** `shared/acceptance-criteria.md` §3 (copied unchanged from `shared/change-request-CR-01.md` §6 v0.2, LOG-16), with the clarifications LOG-17 to LOG-21 applied. If this sheet and that file differ, that file wins.
**Common test procedures:** This populated evaluator run retains the common procedures, operands and expected outcomes. Results are specific to Spec Kit; the offline check remains in the denominator.
**Snapshot tested:** `speckit-cr01-frozen` = `9e523775e31c42bb9f164ddf84e9ff736b2c2fdc`. BMAD was not evaluated in this run.
**Regression:** `CR-AC-21` is a separate run of the 19 baseline checks on the changed app. Use a fresh copy of `07-evaluator-execution-sheet.md` for it and keep the baseline record intact. A regression counts only where the baseline check was observed Pass and now fails.

## Run record (fill in once per track)

| Field | Value |
|---|---|
| Track | GitHub Spec Kit — CR-01 (changed snapshot) |
| Evaluator (name, role, prior exposure to this track) | Amin — methodology, independent of implementation per team record; AI-assisted by Codex. Codex performed the earlier baseline evaluation and inspected UI/build code to locate controls. This run uses real OS mouse/keyboard events and screenshots, not a claim of manual execution of every check by Amin or two independent human reviews. |
| Date / time / timezone | 2026-11-05, EET (UTC+02:00). CR GUI: 2026-11-05T23:14:45.478878+02:00 to 2026-11-05T23:16:25.952670+02:00; regression GUI: 2026-11-05T23:16:48.961164+02:00 to 2026-11-05T23:18:25.006097+02:00.  Manual offline follow-up reported 2026-11-05; actual observation time not supplied. |
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
- Engine-only unit tests do not establish GUI behaviour. Name the layer you observed.

## Checks

| Test ID | Procedure (same operands for both tracks) | Expected | Layer / subcases | Outcome | Observed / actual | Evidence ref |
|---|---|---|---|---|---|---|
| `CR-AC-01` | Launch the application and inspect the GUI. | Square, square-root and reciprocal controls are visible and identifiable. | GUI | Pass | CR-AC-01: first=; second=; result=; error= | [CR-AC-01](change/screenshots/CR-AC-01.png); [raw records](change/gui-observations.tsv). |
| `CR-AC-02` | Select square, enter `-3` in First number, leave Second number empty, calculate. | `9` | GUI | Pass | CR-AC-02: first=-3; second=; result=9; error= | [CR-AC-02](change/screenshots/CR-AC-02.png); [raw records](change/gui-observations.tsv). |
| `CR-AC-03` | Select square, enter `1.2`, calculate. | `1.44` | GUI | Pass | CR-AC-03: first=1.2; second=; result=1.44; error= | [CR-AC-03](change/screenshots/CR-AC-03.png); [raw records](change/gui-observations.tsv). |
| `CR-AC-04` | Select square, enter `0`, calculate. | `0` | GUI | Pass | CR-AC-04: first=0; second=; result=0; error= | [CR-AC-04](change/screenshots/CR-AC-04.png); [raw records](change/gui-observations.tsv). |
| `CR-AC-05` | Select square root, enter `9`, calculate. | `3` | GUI | Pass | CR-AC-05: first=9; second=; result=3; error= | [CR-AC-05](change/screenshots/CR-AC-05.png); [raw records](change/gui-observations.tsv). |
| `CR-AC-06` | Select square root, enter `2`, calculate. | `1.4142135624` | GUI | Pass | CR-AC-06: first=2; second=; result=1.4142135624; error= | [CR-AC-06](change/screenshots/CR-AC-06.png); [raw records](change/gui-observations.tsv). |
| `CR-AC-07` | Select square root, enter `0`, calculate. | `0` | GUI | Pass | CR-AC-07: first=0; second=; result=0; error= | [CR-AC-07](change/screenshots/CR-AC-07.png); [raw records](change/gui-observations.tsv). |
| `CR-AC-08` | Select square root, enter `-1`, calculate. | The specified negative-input error appears; no successful result is shown. | GUI | Pass | CR-AC-08: first=-1; second=; result=; error=Cannot take square root of a negative number | [CR-AC-08](change/screenshots/CR-AC-08.png); [raw records](change/gui-observations.tsv). |
| `CR-AC-09` | Select reciprocal, enter `4`, calculate. | `0.25` | GUI | Pass | CR-AC-09: first=4; second=; result=0.25; error= | [CR-AC-09](change/screenshots/CR-AC-09.png); [raw records](change/gui-observations.tsv). |
| `CR-AC-10` | Select reciprocal, enter `3`, calculate. | `0.3333333333` | GUI | Pass | CR-AC-10: first=3; second=; result=0.3333333333; error= | [CR-AC-10](change/screenshots/CR-AC-10.png); [raw records](change/gui-observations.tsv). |
| `CR-AC-11` | Select reciprocal, enter `-4`, calculate. | `-0.25` | GUI | Pass | CR-AC-11: first=-4; second=; result=-0.25; error= | [CR-AC-11](change/screenshots/CR-AC-11.png); [raw records](change/gui-observations.tsv). |
| `CR-AC-12` | Select reciprocal, enter `0`, calculate. | The baseline division-by-zero error appears; no successful result is shown. | GUI | Pass | CR-AC-12: first=0; second=; result=; error=Cannot divide by zero | [CR-AC-12](change/screenshots/CR-AC-12.png); [raw records](change/gui-observations.tsv). |
| `CR-AC-13` | Repeat CR-AC-12 with `0.0` and `-0.0`. | Each input is treated as zero and rejected safely. | GUI; 2 subcases | Pass | CR-AC-13-1: first=0.0; second=; result=; error=Cannot divide by zero; CR-AC-13-2: first=-0.0; second=; result=; error=Cannot divide by zero | [CR-AC-13-1](change/screenshots/CR-AC-13-1.png), [CR-AC-13-2](change/screenshots/CR-AC-13-2.png); [raw records](change/gui-observations.tsv). |
| `CR-AC-14` | Select each unary operation with an empty First number and calculate. | Clear validation feedback; no crash. | GUI; 3 subcases | Pass | CR-AC-14-1: first=; second=; result=; error=Enter both numbers; CR-AC-14-2: first=; second=; result=; error=Enter both numbers; CR-AC-14-3: first=; second=; result=; error=Enter both numbers | [CR-AC-14-1](change/screenshots/CR-AC-14-1.png), [CR-AC-14-2](change/screenshots/CR-AC-14-2.png), [CR-AC-14-3](change/screenshots/CR-AC-14-3.png); [raw records](change/gui-observations.tsv). |
| `CR-AC-15` | Select each unary operation and enter malformed input such as `abc`. | Baseline invalid-number feedback; no crash. | GUI; 3 subcases | Pass | CR-AC-15-1: first=abc; second=; result=; error=Invalid number; CR-AC-15-2: first=abc; second=; result=; error=Invalid number; CR-AC-15-3: first=abc; second=; result=; error=Invalid number | [CR-AC-15-1](change/screenshots/CR-AC-15-1.png), [CR-AC-15-2](change/screenshots/CR-AC-15-2.png), [CR-AC-15-3](change/screenshots/CR-AC-15-3.png); [raw records](change/gui-observations.tsv). |
| `CR-AC-16` | Select a unary operation, enter a valid First number, leave Second number empty. | The calculation succeeds without a second operand. | GUI | Pass | CR-AC-16-1: first=-3; second=; result=9; error=; CR-AC-16-2: first=9; second=; result=3; error=; CR-AC-16-3: first=4; second=; result=0.25; error= | [CR-AC-16-1](change/screenshots/CR-AC-16-1.png), [CR-AC-16-2](change/screenshots/CR-AC-16-2.png), [CR-AC-16-3](change/screenshots/CR-AC-16-3.png); [raw records](change/gui-observations.tsv). |
| `CR-AC-17` | Select a unary operation, enter a valid First number and a different value in Second number, calculate. | The result depends only on First number. | GUI | Pass | CR-AC-17-1: first=-3; second=123; result=9; error=; CR-AC-17-2: first=9; second=123; result=3; error=; CR-AC-17-3: first=4; second=123; result=0.25; error= | [CR-AC-17-1](change/screenshots/CR-AC-17-1.png), [CR-AC-17-2](change/screenshots/CR-AC-17-2.png), [CR-AC-17-3](change/screenshots/CR-AC-17-3.png); [raw records](change/gui-observations.tsv). |
| `CR-AC-18` | Trigger a negative square-root error, correct the input, calculate again. | The application recovers and shows the correct result. | GUI; stateful | Pass | CR-AC-18: first=9; second=; result=3; error= | [CR-AC-18](change/screenshots/CR-AC-18.png); [raw records](change/gui-observations.tsv). |
| `CR-AC-19` | Trigger a reciprocal division-by-zero error, correct the input, calculate again. | The application recovers and shows the correct result. | GUI; stateful | Pass | CR-AC-19: first=4; second=; result=0.25; error= | [CR-AC-19](change/screenshots/CR-AC-19.png); [raw records](change/gui-observations.tsv). |
| `CR-AC-20` | Use Clear after a unary calculation. | The calculator returns to the agreed baseline initial state. | GUI | Pass | CR-AC-20-1: first=; second=; result=; error=; CR-AC-20-2: first=; second=; result=; error=; CR-AC-20-3: first=; second=; result=; error= | [CR-AC-20-1](change/screenshots/CR-AC-20-1.png), [CR-AC-20-2](change/screenshots/CR-AC-20-2.png), [CR-AC-20-3](change/screenshots/CR-AC-20-3.png); [raw records](change/gui-observations.tsv). |
| `CR-AC-21` | Execute all `BASE-AC-01` to `BASE-AC-19` against the changed application. Record each individually (use a fresh copy of the baseline sheet). | All baseline criteria retain their required behaviour. | Regression; 19 parents | Pass | All 19 baseline checks now have Pass outcomes on the changed snapshot, including Amin’s supplementary manual offline-launch report. No observed regressions; offline continuity is human-reported. | [MANUAL-OFFLINE-01](offline-manual-follow-up.md) |
| `CR-AC-22` | Follow the documented build and test instructions for the changed application. | Builds and tests execute. Prerequisites, test outcomes and failures are documented. | Build | Pass | Fresh clone at frozen SHA, native clean verify exited 0: 116 tests, 0 failures, 0 errors, 0 skipped. Documented JAR launched in a real window; see native-launch.png. | [Build](build.txt), [environment](environment.txt), [native launch](native-launch.png). |

## Summary (fill in after the run)

| P | F | B | N | D | Verified pass coverage P/D | Decisive (P+F)/D | Complete C/D |
|---|---|---|---|---|---|---|---|
| 22 | 0 | 0 | 0 | 22 | 22/22 (100%) | 22/22 (100%) | 22/22 (100%) |

The denominator is unchanged. Amin’s supplementary manual report supplies the formerly blocked offline result. Current totals combine the initial automated/build observations with that named human follow-up; they are not a claim of one fully automated run.

## Regressions (baseline Pass observed, now Fail)

| Baseline AC | Baseline outcome (sheet 07) | Change outcome | Evidence |
|---|---|---|---|
| BASE-AC-01..19 | 19 Pass including manual offline follow-up | 19 Pass including manual offline follow-up | [Paired regression record](09-cr01-baseline-regression-sheet.md); offline provenance in MANUAL-OFFLINE-01. |

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
| CR-AC-01 | 2026-11-05T23:14:45.478878+02:00 | Pass | Square, square-root and reciprocal controls visible | first=; second=; result=; error= | [screenshot](change/screenshots/CR-AC-01.png) |
| CR-AC-02 | 2026-11-05T23:14:48.213150+02:00 | Pass | 9 | first=-3; second=; result=9; error= | [screenshot](change/screenshots/CR-AC-02.png) |
| CR-AC-03 | 2026-11-05T23:14:51.036727+02:00 | Pass | 1.44 | first=1.2; second=; result=1.44; error= | [screenshot](change/screenshots/CR-AC-03.png) |
| CR-AC-04 | 2026-11-05T23:14:53.609696+02:00 | Pass | 0 | first=0; second=; result=0; error= | [screenshot](change/screenshots/CR-AC-04.png) |
| CR-AC-05 | 2026-11-05T23:14:56.159068+02:00 | Pass | 3 | first=9; second=; result=3; error= | [screenshot](change/screenshots/CR-AC-05.png) |
| CR-AC-06 | 2026-11-05T23:14:58.720532+02:00 | Pass | 1.4142135624 | first=2; second=; result=1.4142135624; error= | [screenshot](change/screenshots/CR-AC-06.png) |
| CR-AC-07 | 2026-11-05T23:15:01.263156+02:00 | Pass | 0 | first=0; second=; result=0; error= | [screenshot](change/screenshots/CR-AC-07.png) |
| CR-AC-08 | 2026-11-05T23:15:03.922117+02:00 | Pass | Cannot take square root of a negative number | first=-1; second=; result=; error=Cannot take square root of a negative number | [screenshot](change/screenshots/CR-AC-08.png) |
| CR-AC-09 | 2026-11-05T23:15:06.475164+02:00 | Pass | 0.25 | first=4; second=; result=0.25; error= | [screenshot](change/screenshots/CR-AC-09.png) |
| CR-AC-10 | 2026-11-05T23:15:09.055959+02:00 | Pass | 0.3333333333 | first=3; second=; result=0.3333333333; error= | [screenshot](change/screenshots/CR-AC-10.png) |
| CR-AC-11 | 2026-11-05T23:15:11.716109+02:00 | Pass | -0.25 | first=-4; second=; result=-0.25; error= | [screenshot](change/screenshots/CR-AC-11.png) |
| CR-AC-12 | 2026-11-05T23:15:14.303021+02:00 | Pass | Cannot divide by zero | first=0; second=; result=; error=Cannot divide by zero | [screenshot](change/screenshots/CR-AC-12.png) |
| CR-AC-13-1 | 2026-11-05T23:15:17.082241+02:00 | Pass | Cannot divide by zero | first=0.0; second=; result=; error=Cannot divide by zero | [screenshot](change/screenshots/CR-AC-13-1.png) |
| CR-AC-13-2 | 2026-11-05T23:15:19.934750+02:00 | Pass | Cannot divide by zero | first=-0.0; second=; result=; error=Cannot divide by zero | [screenshot](change/screenshots/CR-AC-13-2.png) |
| CR-AC-14-1 | 2026-11-05T23:15:22.396815+02:00 | Pass | Enter both numbers | first=; second=; result=; error=Enter both numbers | [screenshot](change/screenshots/CR-AC-14-1.png) |
| CR-AC-14-2 | 2026-11-05T23:15:24.875419+02:00 | Pass | Enter both numbers | first=; second=; result=; error=Enter both numbers | [screenshot](change/screenshots/CR-AC-14-2.png) |
| CR-AC-14-3 | 2026-11-05T23:15:27.341282+02:00 | Pass | Enter both numbers | first=; second=; result=; error=Enter both numbers | [screenshot](change/screenshots/CR-AC-14-3.png) |
| CR-AC-15-1 | 2026-11-05T23:15:30.109361+02:00 | Pass | Invalid number | first=abc; second=; result=; error=Invalid number | [screenshot](change/screenshots/CR-AC-15-1.png) |
| CR-AC-15-2 | 2026-11-05T23:15:32.873896+02:00 | Pass | Invalid number | first=abc; second=; result=; error=Invalid number | [screenshot](change/screenshots/CR-AC-15-2.png) |
| CR-AC-15-3 | 2026-11-05T23:15:35.641046+02:00 | Pass | Invalid number | first=abc; second=; result=; error=Invalid number | [screenshot](change/screenshots/CR-AC-15-3.png) |
| CR-AC-16-1 | 2026-11-05T23:15:38.318007+02:00 | Pass | 9 | first=-3; second=; result=9; error= | [screenshot](change/screenshots/CR-AC-16-1.png) |
| CR-AC-16-2 | 2026-11-05T23:15:40.891732+02:00 | Pass | 3 | first=9; second=; result=3; error= | [screenshot](change/screenshots/CR-AC-16-2.png) |
| CR-AC-16-3 | 2026-11-05T23:15:43.439241+02:00 | Pass | 0.25 | first=4; second=; result=0.25; error= | [screenshot](change/screenshots/CR-AC-16-3.png) |
| CR-AC-17-1 | 2026-11-05T23:15:46.408067+02:00 | Pass | 9 | first=-3; second=123; result=9; error= | [screenshot](change/screenshots/CR-AC-17-1.png) |
| CR-AC-17-2 | 2026-11-05T23:15:49.277396+02:00 | Pass | 3 | first=9; second=123; result=3; error= | [screenshot](change/screenshots/CR-AC-17-2.png) |
| CR-AC-17-3 | 2026-11-05T23:15:52.164200+02:00 | Pass | 0.25 | first=4; second=123; result=0.25; error= | [screenshot](change/screenshots/CR-AC-17-3.png) |
| CR-AC-18 | 2026-11-05T23:15:55.980301+02:00 | Pass | Recover negative sqrt error to sqrt(9)=3 | first=9; second=; result=3; error= | [screenshot](change/screenshots/CR-AC-18.png) |
| CR-AC-19 | 2026-11-05T23:15:59.686148+02:00 | Pass | Recover reciprocal zero error to 1/4=0.25 | first=4; second=; result=0.25; error= | [screenshot](change/screenshots/CR-AC-19.png) |
| CR-AC-20-1 | 2026-11-05T23:16:02.678411+02:00 | Pass | Clear resets all inputs, seven operations and output | first=; second=; result=; error= | [screenshot](change/screenshots/CR-AC-20-1.png) |
| CR-AC-20-2 | 2026-11-05T23:16:05.572895+02:00 | Pass | Clear resets all inputs, seven operations and output | first=; second=; result=; error= | [screenshot](change/screenshots/CR-AC-20-2.png) |
| CR-AC-20-3 | 2026-11-05T23:16:08.452837+02:00 | Pass | Clear resets all inputs, seven operations and output | first=; second=; result=; error= | [screenshot](change/screenshots/CR-AC-20-3.png) |
| SUP-SECOND-1 | 2026-11-05T23:16:11.449573+02:00 | Pass | 9 | first=-3; second=abc; result=9; error= | [screenshot](change/screenshots/SUP-SECOND-1.png) |
| SUP-SECOND-2 | 2026-11-05T23:16:14.330216+02:00 | Pass | 3 | first=9; second=abc; result=3; error= | [screenshot](change/screenshots/SUP-SECOND-2.png) |
| SUP-SECOND-3 | 2026-11-05T23:16:17.207549+02:00 | Pass | 0.25 | first=4; second=abc; result=0.25; error= | [screenshot](change/screenshots/SUP-SECOND-3.png) |
| SUP-ENTER-1 | 2026-11-05T23:16:20.193345+02:00 | Pass | 9 | first=-3; second=; result=9; error= | [screenshot](change/screenshots/SUP-ENTER-1.png) |
| SUP-ENTER-2 | 2026-11-05T23:16:23.068120+02:00 | Pass | 3 | first=9; second=; result=3; error= | [screenshot](change/screenshots/SUP-ENTER-2.png) |
| SUP-ENTER-3 | 2026-11-05T23:16:25.952670+02:00 | Pass | 0.25 | first=4; second=; result=0.25; error= | [screenshot](change/screenshots/SUP-ENTER-3.png) |

## Deviations, provenance and interpretation

- **Initial automated run:** Offline launch was not attempted, preserving Amin’s explicit instruction to skip it. The earlier offline prerequisite problem remains unresolved; this run does not disable networking. BASE-AC-01 and aggregate CR-AC-21 remain Blocked/unverified. **Follow-up:** Amin subsequently confirmed offline launch passed for both versions; current statuses include MANUAL-OFFLINE-01.
- The original baseline record at commit 88f56d0 is intact. A regression requires a previously observed Pass becoming Fail under comparable conditions. The automated record has 18 comparable Pass outcomes. With Amin’s manual follow-up for both versions, all 19 paired criteria have Pass outcomes; no regressions observed. Offline provenance remains human-reported.
- Native quickstart shell/paths are adapted from Windows PowerShell to macOS. A separate local Maven cache is used. The original implementation, POM, tests, acceptance criteria and BMAD evidence are unchanged. Native test success alone is not GUI evidence.
- CR-FR-03 Enter support for each unary operation and LOG-17 ignoring malformed Second number input have supplementary SUP-ENTER and SUP-SECOND records. These do not increase parent denominators. CR-AC-16/17/20 were exercised for all three unary operations.
- Evaluator assistance is Codex (GPT-6 based agent; exact serving model identifier unavailable). This is not a second human review. Prior baseline exposure and AI assistance are disclosed. Results are limited to the recorded inputs; no general defect-free or tool-superiority claim is made. Relevant validity limits: VT-C02, VT-C06, VT-I05 and independent-human-review limits.
- No formal subjective rubric ratings, change-impact metrics, effort estimates or reconstructed operator evidence are included. This deliverable is the requested acceptance/regression evaluation.

## Supplementary manual offline follow-up — 2026-11-05

Amin confirmed that **both versions passed offline launch**. See [MANUAL-OFFLINE-01](offline-manual-follow-up.md) for the original statements, snapshot mapping, missing procedural details and prior blocked outcomes. This dated follow-up updates current totals without changing the automated raw evidence or backdating an automated Pass.
