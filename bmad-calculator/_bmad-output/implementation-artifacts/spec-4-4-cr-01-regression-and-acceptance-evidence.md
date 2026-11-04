---
title: 'Story 4.4: CR-01 regression and acceptance evidence'
type: 'chore'
created: '2026-11-03'
status: 'done'
baseline_commit: '1e39815bd0ecc4e5f6bc43330abe6c4184c748f7'
route: 'dispatch'
review_loop_iteration: 0
story_key: '4-4-cr-01-regression-and-acceptance-evidence'
context:
  - '{project-root}/_bmad-output/implementation-artifacts/epic-4-context.md'
  - '{project-root}/evidence/04-acceptance-results-and-traceability.md'
---

<frozen-after-approval reason="human-owned intent — do not modify unless human renegotiates">

## Intent

**Problem:** CR-01 is built (Stories 4.1 to 4.3) but not yet verified as a whole. CR-01 §7 requires each `BASE-AC-01..19` result and each `CR-AC-01..22` result to be recorded individually, with Clear after a unary calculation and the build instructions re-checked.

**Approach:** Add the missing Clear-after-unary tests, run the full suite on display and headless, run a clean-copy build, and write a CR-01 results and traceability file in `evidence/` with one row per check, reporting Pass only for what was observed. Manual checks that need a person are listed for Varun, not marked Pass.

## Boundaries & Constraints

**Always:** Statuses are exactly Pass, Fail, Blocked or Not run. Pass requires an observed run (a named test in a display run with 0 skipped, or a manual observation by Varun). Each result row has the check ID, status, evidence location and notes. Record commit hash (or "uncommitted working tree on `1e39815`"), environment, command, and display versus headless counts. A skipped test is never counted as a pass. Defects, missed impacts, rework and interventions are listed. Decision (Varun, 2026-11-03): CR-AC-22 and BASE-AC-15 use a clean copy of the working tree as in the baseline, not a git clone; BASE-AC-15 stays "Pass with limits" and the limit is stated.

**Never:** Change `shared/*`, edit source code except to fix a defect the checks expose (then record it as a defect), mark a manual check Pass without Varun's observation, or hide a failure.

## I/O & Edge-Case Matrix

| Scenario | Input / State | Expected Output / Behavior | Error Handling |
|----------|--------------|---------------------------|----------------|
| Clear after unary result | `x²` of `-3` shown, then Clear | Fields empty, no operation selected, no result, no error (CR-AC-20) | N/A |
| Clear after unary error | `√x` of `-1`, `1/x` of `0`, then Clear | Same initial state; error gone | N/A |
| Clear with Second number set | `x²`, A=`4`, B=`7`, then Clear | Both fields empty | N/A |
| Baseline regression | all `BASE-AC-01..19` | Each recorded individually, none merged into one pass | A failure is recorded as Fail with a defect entry |
| CR checks | `CR-AC-01..22` | Each recorded individually | Same |
| Headless run | no display | Window tests reported as skipped, counted separately, not as pass | N/A |
| Clean copy | `pom.xml`, `BUILD.md`, `src`, `tests` copied to a temp folder (no `target/`) | Build and tests pass with pinned `JAVA_HOME` (CR-AC-22, BASE-AC-15 with limits: not a git clone) | Failure documented |

</frozen-after-approval>

## Code Map

- `bmad-calculator/tests/calculator/ui/CalculatorFrameInteractionTest.java` -- `baseAc10_clear_after_a_result_resets_everything` and `baseAc10_clear_after_each_error_resets_everything` show the Clear test pattern; add unary rows
- `bmad-calculator/tests/calculator/presenter/CalculatorPresenterTest.java` -- presenter `onClear` rows; add unary rows
- `bmad-calculator/evidence/04-acceptance-results-and-traceability.md` -- baseline results file to mirror in structure; do not rewrite it
- `bmad-calculator/evidence/story-4-1-test-log.txt`, `story-4-2-test-log.txt`, `story-4-3-test-log.txt` -- raw logs of earlier runs
- `bmad-calculator/BUILD.md` -- build, run and test commands; add a short CR-01 note only if a command or prerequisite changed
- `bmad-calculator/_bmad-output/implementation-artifacts/sprint-status.yaml` -- set stories and epic status

## Tasks & Acceptance

**Execution:**
- [x] `bmad-calculator/tests/**` -- add Clear-after-unary rows (result, error, Second number set) in presenter and window tests -- CR-AC-20
- [x] `bmad-calculator/evidence/story-4-4-final-test-log.txt` -- display run and headless run of `mvn test`, counts and skips stated -- CR-AC-21, 22
- [x] `bmad-calculator/evidence/story-4-4-clean-build-log.txt` -- clean-copy build and test run per the answer to the Open Question -- CR-AC-22, BASE-AC-15
- [x] `bmad-calculator/evidence/06-cr01-acceptance-results-and-traceability.md` -- one row per `BASE-AC-01..19` and `CR-AC-01..22`, traceability from CR-FR and FR-11..15 to stories and tests, defects, deferred items, missed impacts, rework, effort and interventions -- CR-AC-21; CR-01 §7
- [x] `bmad-calculator/BUILD.md` -- note CR-01 only if needed; `bmad-calculator/evidence/00-STATUS.md` and `sprint-status.yaml` -- update

**Acceptance Criteria:**
- Given the final tree, when `mvn test` runs on display, then 0 failures and 0 skipped, and the log is saved.
- Given the evidence file, when I read it, then every `BASE-AC-01..19` and `CR-AC-01..22` has its own row with a status of Pass, Fail, Blocked or Not run and an evidence location; no row is a Pass without an observed run.
- Given checks that need a person (BASE-AC-01 offline on the changed app, CR-AC-01 controls visible), when they are not yet observed, then they are listed as Not run with the exact steps for Varun.

## Implementation Notes

- Added Clear-after-unary rows (presenter and window, 12 test cases). The window helper `assertInitialState` checked only the four baseline operation buttons; extended it to all seven (missed impact, recorded in file 06).
- Display run: 316 run, 0 failed, 0 skipped. Headless run (`mvn -DargLine=-Djava.awt.headless=true test`): 316 run, 0 failed, 80 skipped. Clean-copy build: 316 run, 0 failed. Per-check results written to `evidence/06-cr01-acceptance-results-and-traceability.md` from the surefire XML of the display run (IDs read from test method names).
- `BUILD.md` needed no change. Review layers not run for this story (deviation D-15). Owner-observed checks: Varun's screenshots (Wi-Fi off as reported); recorded as Pass with limits where the network state is not visible.

## Spec Change Log

## Review Triage Log

## Verification

**Commands:**
- `export JAVA_HOME=/Users/varunsondur/Library/Java/JavaVirtualMachines/corretto-22.0.2/Contents/Home && cd bmad-calculator && mvn test` -- expected: BUILD SUCCESS, display run 0 failed and 0 skipped
- Headless run: `mvn -Djava.awt.headless=true test` -- expected: BUILD SUCCESS with window tests skipped and counted separately

**Manual checks (if no CLI):**
- Varun: Wi-Fi off, `mvn -q compile exec:java`, screenshot of the window showing the unary controls; calculate `√x` of `2` and `1/x` of `0`; save under `evidence/screenshot/`.
