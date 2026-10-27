# Baseline acceptance results and traceability (Varun / BMAD track)

Recorded 2026-10-23 (EEST). Track: Varun/BMAD. Phase: Baseline. **Commit hash:** `ebac834` (branch `bmad-baseline`; results were produced on the identical working tree just before this commit). Results are for the working tree on top of `0ace55c` (stories 1.1 to 1.3 committed). The single end-of-Epic-3 commit hash is to be added here after it is made.
Environment: macOS 15.7.1, Amazon Corretto 22.0.2 (`JAVA_HOME=$(/usr/libexec/java_home -v 22)`), Maven 3.9.16, Swing, JUnit Jupiter 6.1.3. Command: `cd bmad-calculator && mvn test`.
Raw logs: `evidence/story-3-3-final-test-log.txt` (display and headless runs), per-story logs `story-*-test-log.txt`, `story-3-2-clean-build-log.txt`, `ac-01-manual-check.md`.

Display run: 172 run, 0 failed, 0 skipped. Headless run: 172 run, 0 failed, **57 skipped** (all window tests). A skipped test is not a pass; the Pass column below relies on the display run.

## 1. BASE-AC results

The proposed behaviours (LOG-01..LOG-08, D-07) were frozen by the owner on 2026-10-23 without George's response (see `shared/experiment-log.md`, Section D); LOG-09..LOG-11 and F-09 remain open.

| Test ID | Status | Evidence (automated test, class) | Notes |
|---|---|---|---|
| BASE-AC-01 | **Pass (owner-observed, final GUI, commit `943c73f`)** | `ac-01-manual-check.md`, `screenshot/add.png`, `screenshot/substract.png`, `screenshot/error.png`; automated: `CalculatorFrameSmokeTest` (baseAc01_*) | Manual, Wi-Fi off as reported by Varun; the screenshots do not show the network indicator. Earlier Story 1.1 check also Pass. |
| BASE-AC-02 | Pass | `CalculatorFrameInteractionTest.baseAc02_and_03_...` | integer operand |
| BASE-AC-03 | Pass | same test | decimal operand |
| BASE-AC-04 | Pass | `CalculatorTest.baseAc04_7_plus_5_is_12`; `CalculatorPresenterTest.baseAc04to09_...`; `CalculatorFrameInteractionTest.baseAc04_clicking_calculate_shows_the_result` | |
| BASE-AC-05 | Pass | `CalculatorTest.baseAc05_...`; interaction `baseAc05_to_09_each_operation_through_the_gui` | |
| BASE-AC-06 | Pass | `CalculatorTest.baseAc06_...`; same GUI test | |
| BASE-AC-07 | Pass | `CalculatorTest.baseAc07_...`; same GUI test | policy: half-up, 10 places |
| BASE-AC-08 | Pass | `CalculatorTest.baseAc08_...`; same GUI test | |
| BASE-AC-09 | Pass | `CalculatorTest.baseAc09_...` (`3.5` exactly); same GUI test | |
| BASE-AC-10 | Pass | `CalculatorPresenterTest.baseAc10_*`; `CalculatorFrameInteractionTest.baseAc10_*` | Story 3.1 |
| BASE-AC-11 | Pass | `CalculatorTest.baseAc11_*`; interaction `baseAc11_*` | "Enter both numbers" |
| BASE-AC-12 | Pass | `CalculatorTest.baseAc12_*`; interaction `baseAc12_*` | abc, 1.2.3, 1,5, 1e3, --1, . covered |
| BASE-AC-13 | Pass | `CalculatorTest.baseAc13_*`; presenter and interaction `baseAc13_*` | incl. 7/0.0 |
| BASE-AC-14 | Pass | presenter and interaction `baseAc14_*` | |
| BASE-AC-15 | **Pass with limits** | `BUILD.md`; `story-3-2-clean-build-log.txt` | Built and tested from a file copy (only `pom.xml`, `BUILD.md`, `src`, `tests`): 172 pass. Not from a real git clone (no commit yet), and the launch step was not re-run from the copy (needs display; covered by AC-01 manual). |
| BASE-AC-16 | Pass | `CalculatorPresenterTest.baseAc16_*`; interaction `baseAc16_*` | |
| BASE-AC-17 | Pass | `CalculatorTest.baseAc17_*`; interaction `baseAc17_*` | "Select an operation" |
| BASE-AC-18 | Pass | `CalculatorTest.baseAc18_display_policy`; presenter and interaction `baseAc18_*` | 0/5 = 0, 1/3 = 0.3333333333 |
| BASE-AC-19 | Pass | interaction `baseAc19_enter_in_either_field_calculates_like_the_button` | |

## 2. Requirement to story to test traceability

| Requirement | Story | Tests |
|---|---|---|
| FR-1 enter operands (BASE-FR-02) | 1.3 | interaction baseAc02_and_03; presenter baseAc04to09 |
| FR-2 launch offline (BASE-FR-01) | 1.1 | `CalculatorFrameSmokeTest` baseAc01_*; manual AC-01 |
| FR-3 select an operation (BASE-FR-03) | 1.3 | interaction baseAc05_to_09; `OperationTest` |
| FR-4 calculate by button or Enter (BASE-FR-04) | 1.2, 1.3 | `CalculatorTest` baseAc04..09, 18; interaction baseAc04, baseAc19 |
| FR-5 consecutive calculations (BASE-AC-16) | 1.3 | baseAc16_* (presenter, interaction) |
| FR-6 clear (BASE-FR-05) | 3.1 | baseAc10_* (presenter, interaction) |
| FR-7 invalid or incomplete input (BASE-FR-06, D-07) | 2.1 | baseAc11, 12, 17 |
| FR-8 display policy (BASE-FR-07 numeric) | 1.2 | `ResultFormatterTest`, `CalculatorTest` baseAc07, 09, 18 |
| FR-9 division by zero (BASE-FR-07) | 2.2 | baseAc13_* |
| FR-10 error recovery (BASE-FR-08) | 2.3 | baseAc14_* |
| NFR-1 runs locally on agreed Java/OS (BASE-NFR-01) | 1.1 | `RuntimeEnvironmentTest` |
| NFR-2 interactive responsiveness (BASE-NFR-02) | 1.3 | **No automated or timed test.** Not measured; only the architecture (no work off the EDT beyond small BigDecimal maths). |
| NFR-3 no crash on invalid input or arithmetic errors (BASE-NFR-03) | 2.1, 2.2 | baseAc11 to 13; `NoDialogsTest` |
| NFR-4 reviewable code and test organisation (BASE-NFR-04) | 3.2 | `ArchitectureRulesTest` |
| NFR-5 documented reproducible build (BASE-NFR-05) | 3.2 | `BUILD.md`; clean-copy log |

Requirement text per the PRD numbering; shared IDs in brackets. Method names are abbreviated by prefix; all are in `bmad-calculator/tests/calculator/`.

## 3. Unresolved defects and untested or partly tested items

- No test failed in the final display run. No defect IDs were raised.
- **BASE-AC-01** final-GUI re-check done by Varun on 2026-10-23 (see `ac-01-manual-check.md`); the screenshots do not show the network state.
- **BASE-AC-15** not verified from a real git clone; launch from a clean copy not run.
- **NFR-2** responsiveness not measured.
- **Headless:** 57 window tests are skipped without a display; window behaviour was verified only in the display run on this machine (one OS, one Look and Feel).
- **Not verified by a deliberate break:** `ArchitectureRulesTest` failure path (Varun asked for minimal tests).
- **Reviewers skipped for 3.2 and 3.3** at Varun's request (deviation).
- **Open questions for the team:** LOG-01..LOG-11 incl. D-07 unanswered by George; F-09 (should a shown result or error clear when an operand is edited or an operation chosen?); whether Enter while the Clear button has focus should be Calculate (Edge Case Hunter finding in Story 3.1).
- Over-testing note for the report: 172 tests for 19 acceptance criteria; review layers pushed the count up (see 00-STATUS findings).

## Update 2026-10-27 (LOG-12 to LOG-15)

- LOG-15 negative-tie check: `-1 / 2048` gives `-0.0004882813` and `1 / 2048` gives `0.0004882813`. Two cases added to `CalculatorTest.baseAc18_display_policy`. Display run: 174 run, 0 failed (`log-15-tie-rounding-test-log.txt`); the GUI window tests were not run separately in this session.
- LOG-12, LOG-13, LOG-14 required no change; existing tests already cover them.
