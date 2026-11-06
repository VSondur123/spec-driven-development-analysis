# CR-01 acceptance results and traceability (Varun / BMAD track)

Recorded 2026-11-03. Track: Varun/BMAD. Phase: CR-01. **Commit:** none; results are for the uncommitted working tree on branch `cr-01` over `main` at `1e39815` (baseline code from `740c78d`, tag `baseline-frozen`). Environment: macOS 15.x, Amazon Corretto 22.0.2 (`JAVA_HOME` pinned), Maven 3.9.16, Swing, JUnit Jupiter 6.1.3, BMAD 6.12.4, Claude Sonnet 5.5. Command: `cd bmad-calculator && mvn test`.
Raw logs: `story-4-4-final-test-log.txt` (display and headless counts), `story-4-4-clean-build-log.txt`, per-story logs `story-4-1-test-log.txt`, `story-4-2-test-log.txt`, `story-4-3-test-log.txt`, screenshots in `screenshot/`. Specs and review triage logs: `_bmad-output/implementation-artifacts/spec-4-*.md`. Impact analysis: `05-cr01-impact-analysis.md`.

**Display run: 316 run, 0 failed, 0 skipped. Headless run: 316 run, 0 failed, 80 skipped (all window tests).** A skipped test is not a pass; every Pass below relies on the display run or on Varun's observation.

How to read the Evidence column: counts of passing tests per layer (core, presenter, window) whose method names cite the check ID. Some methods cite several IDs (for example `crAc02to04_...`), so one test can appear under more than one check. IDs were taken from method names with a script over the surefire XML of the display run.

## 1. BASE-AC results on the changed application (CR-AC-21)

| Test ID | Status | Evidence (passing tests by layer) | Notes |
|---|---|---|---|
| BASE-AC-01 | Pass | smoke 3 | Automated `CalculatorFrameSmokeTest` (window opens) plus **manual, owner-observed with Wi-Fi off**: `screenshot/add.png`, `substract.png`, `error.png` (commit `943c73f`, baseline) and `sq-root-2.png`, `reciprocal-of-zero.png` (CR-01 build). Network state is not visible in the screenshots; offline rests on Varun's report. |
| BASE-AC-02 | Pass | window 1 | integer operand |
| BASE-AC-03 | Pass | window 1 | decimal operand |
| BASE-AC-04 | Pass | window 1, presenter 7, core 1 | add |
| BASE-AC-05 | Pass | window 1, presenter 7, core 1 | subtract |
| BASE-AC-06 | Pass | window 1, presenter 7, core 1 | multiply |
| BASE-AC-07 | Pass | window 1, presenter 7, core 1 | divide 7/2 |
| BASE-AC-08 | Pass | window 1, presenter 7, core 1 | negative operand |
| BASE-AC-09 | Pass | window 1, presenter 7, core 1 | decimal sum |
| BASE-AC-10 | Pass | window 5, presenter 5 | clear |
| BASE-AC-11 | Pass | window 5, core 6 | empty operand |
| BASE-AC-12 | Pass | window 12, core 14 | malformed input |
| BASE-AC-13 | Pass | window 7, presenter 7, core 4 | divide by zero |
| BASE-AC-14 | Pass | window 5, presenter 6 | error recovery |
| BASE-AC-15 | Pass with limits | `BUILD.md`; clean-copy log | Clean-copy build and test run: 316 run, 0 failed (`story-4-4-clean-build-log.txt`). Not a git clone (nothing is committed); the launch step was not re-run from the copy (Varun launched the working tree). |
| BASE-AC-16 | Pass | window 1, presenter 1 | consecutive calculations |
| BASE-AC-17 | Pass | window 1, core 1 | no operation selected |
| BASE-AC-18 | Pass | window 1, presenter 9, core 20 | 0/5, 1/3 and tie rounding |
| BASE-AC-19 | Pass | window 1 | Enter key |

## 2. CR-AC results

| Test ID | Status | Evidence (passing tests by layer) | Notes |
|---|---|---|---|
| CR-AC-01 | Pass | window 3 | Automated: controls visible, not clipped, selectable. Owner-observed: all seven operation buttons in one row, none clipped (`screenshot/sq-root-2.png`, `reciprocal-of-zero.png`). |
| CR-AC-02 | Pass | window 1, presenter 5, core 10 | square of -3 = 9 |
| CR-AC-03 | Pass | window 1, presenter 5, core 10 | 1.2 squared = 1.44 |
| CR-AC-04 | Pass | window 1, presenter 5, core 10 | 0 squared = 0 |
| CR-AC-05 | Pass | window 1, presenter 3, core 14 | square root of 9 = 3 |
| CR-AC-06 | Pass | window 1, presenter 3, core 14 | square root of 2 = 1.4142135624. Also owner-observed in `screenshot/sq-root-2.png`. |
| CR-AC-07 | Pass | window 1, presenter 3, core 14 | square root of 0 = 0 |
| CR-AC-08 | Pass | window 1, presenter 1, core 4 | square root of -1: negative-number error, no result |
| CR-AC-09 | Pass | window 1, presenter 3, core 9 | 1/4 = 0.25 |
| CR-AC-10 | Pass | window 1, presenter 3, core 9 | 1/3 = 0.3333333333 |
| CR-AC-11 | Pass | window 1, presenter 3, core 9 | 1/-4 = -0.25 |
| CR-AC-12 | Pass | window 1, presenter 3, core 6 | 1/0: error and no result. Also owner-observed in `screenshot/reciprocal-of-zero.png`. |
| CR-AC-13 | Pass | window 1, presenter 3, core 6 | 0.0 and -0.0 rejected (automated; not in a screenshot) |
| CR-AC-14 | Pass | window 3, presenter 1, core 5 | empty First number: baseline wording "Enter both numbers" (LOG-21) |
| CR-AC-15 | Pass | window 3, presenter 1, core 12 | malformed First number: "Invalid number" |
| CR-AC-16 | Pass | window 3, presenter 5, core 19 | Second number empty works |
| CR-AC-17 | Pass | window 3, presenter 5, core 19 | Second number ignored, including malformed text (LOG-17) |
| CR-AC-18 | Pass | window 1, presenter 1 | negative square root, correct, recalculate |
| CR-AC-19 | Pass | window 1, presenter 3 | reciprocal of zero, correct, recalculate |
| CR-AC-20 | Pass | window 6, presenter 6 | Clear after a unary result, after a unary error, and with Second number set; the window helper `assertInitialState` was extended in this story to check all seven operations |
| CR-AC-21 | Pass with limits | section 1 | All BASE-AC-01..19 are recorded individually in section 1: 18 Pass, BASE-AC-15 Pass with limits. Display run 316 run, 0 failed, 0 skipped; headless run 316 run, 0 failed, 80 skipped (window tests, not counted as pass). |
| CR-AC-22 | Pass with limits | `BUILD.md`; clean-copy log | Build and tests from a clean copy: 316 run, 0 failed (`story-4-4-clean-build-log.txt`). Prerequisites in `BUILD.md` unchanged and still correct. Not a git clone. Environment: macOS, Corretto 22.0.2, Maven 3.9.16, pinned JAVA_HOME. |

## 3. Requirement to story to test traceability

| Requirement | Story | Tests |
|---|---|---|
| CR-FR-01 identifiable controls (FR-11, 12, 13) | 4.1, 4.2, 4.3 | `everyControlHasAStableName`, `crAc01_*` (window); screenshots |
| CR-FR-02 first number only, no second operand needed (FR-14) | 4.1 | `crAc16and17_*` (core, presenter, window) |
| CR-FR-03 select and Calculate, Enter key (FR-14) | 4.1 to 4.3 | Enter-key tests for square, reciprocal and square root (window) |
| CR-FR-04 square (FR-11) | 4.1 | `crAc02to04_*` |
| CR-FR-05 square root (FR-13) | 4.3 | `crAc05to07_*`, `OperationTest` rounding rows |
| CR-FR-06 negative root error (FR-13) | 4.3 | `crAc08_*` |
| CR-FR-07 reciprocal (FR-12) | 4.2 | `crAc09to11_*` |
| CR-FR-08 reciprocal of zero (FR-12) | 4.2 | `crAc12_*`, `crAc12and13_*` |
| CR-FR-09 empty or malformed input (FR-14) | 4.1 to 4.3 | `crAc14_*`, `crAc15_*` |
| CR-FR-10 recovery and Clear (FR-15) | 4.2, 4.3, 4.4 | `crAc18_*`, `crAc19_*`, `crAc20_*` |
| CR-FR-11 baseline still applies (FR-15) | 4.4 | section 1; whole suite |

## 4. Defects, missed impacts and rework

- **Defects found by tests or checks:** 1 test defect (Story 4.3: a wrong test input, `1e41` instead of `1e40`; the code was correct). No product defect. No failing acceptance check in the final runs.
- **Missed impacts (not predicted in the Sprint Change Proposal):** (1) the two window tests that list the operation buttons (Story 4.1); (2) `assertInitialState`, a window-test helper that checked only the four baseline operation buttons, so it would not have caught a unary button staying selected after Clear (found in Story 4.4, fixed). The proposal's other predictions held: `OperationTest` needed an update, and the frame and presenter needed no source change.
- **Rework:** three one-round test-only patch passes after the review layers (4.1, 4.2, 4.3), one Javadoc correction, one test-input fix. No source behaviour was reworked after review.
- **Review layers:** run for 4.1, 4.2 and 4.3 (three subagents each); **not run for 4.4** (evidence story, as for baseline 3.2 and 3.3). Items per story: 4.1: 15 (3 low patched); 4.2: 13 (2 low patched); 4.3: 16 (2 low patched). No high or medium finding. About half of the items were false or already-agreed policy (see the Review Triage Log in each spec), the same pattern as baseline finding F-07.
- **Recurring finding for the report:** adding a unary operation changed `Calculator.evaluate` as well as the `Operation` enum, so the baseline architecture claim "adding an operation touches only the enum and the button list" did not hold (AD-7 amended).
- **Deferred or not verified:** NFR-2 responsiveness not measured; headless runs skip 80 window tests; one OS and one Look and Feel; BASE-AC-15 and CR-AC-22 not from a real git clone; screenshots do not show the network indicator; operand length is uncapped (square and square root of a very long input are not bounded, same theme as baseline F-08).
- **Open items for the team:** LOG-16 (the CR-01 header still says Proposed; the CR-AC checks are copied into `acceptance-criteria.md` §3); LOG-17 to LOG-21 agreed by Varun for the BMAD track, George's response pending.

**Status update 2026-11-07:** Varun confirmed that CR-01 is released and done, and that George agreed (LOG-16 to LOG-21 approved, as reported by Varun). The "Open items for the team" line above is the 2026-11-03 position and is closed. The shared `experiment-log.md` was not edited by this track; its wording is for the owner to update. The evaluator sheets are completed: `08-cr01-evaluator-execution-sheet.md` (CR-AC-01 to 22) and `09-baseline-regression-cr01.md` (BASE-AC-01 to 19 against the changed app). The results above are the 2026-11-03 record and are not rewritten. Sprint-status is `done` for Epic 4.

## 5. Effort and interventions (approximate, not timed)

The CR-01 phase ran on 2026-11-03 in one session. Human interventions: released CR-01 and asked for branch `cr-01`; agreed LOG-12..15 and LOG-16..21; approved the Epic 4 split, the square-root algorithm and each story spec at Checkpoint 1 (4.1 to 4.4); authorised the review subagents; ran the manual checks with screenshots; asked for no commits. Elapsed and active time per step was not recorded precisely.
