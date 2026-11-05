---

description: "Task list for CR-01 scientific operations"
---

# Tasks: Scientific Operations (CR-01)

**Input**: Design documents from `/specs/002-scientific-operations/`

**Prerequisites**: plan.md (approved 2026-11-04, AP-03), spec.md, research.md, data-model.md, contracts/, quickstart.md

**Tests**: Included. The plan maps every `CR-AC` to a test or a manual check, and the owner asked for tests written first and run red, as in the baseline. Within each story, tests come before the code they check.

**Organization**: Tasks are grouped by user story. The code is an extension of the frozen baseline (commit `88f56d0`), so the stories build on each other in priority order (US1, then US2, then US3). US4 re-runs the baseline and the build against the finished change.

## Format: `[ID] [P?] [Story] Description`

- **[P]**: Can run in parallel (different files, no dependencies on unfinished tasks)
- **[Story]**: User story the task belongs to (US1 to US4)
- Paths are relative to the track folder `github-spec-kit-calculator/`
- **OWNER** at the start of a description marks a step only the owner can do or confirm.

## Path Conventions

- Source: `src/main/java/calculator/core/` and `src/main/java/calculator/ui/`
- Tests: `src/test/java/calculator/core/` and `src/test/java/calculator/ui/`
- Evidence: `evidence/` (CR-01 results file: `evidence/08-cr01-test-results.md`; logs: `evidence/logs/cr-tNNN-*.txt`)

## Rules that apply to every task

- Run every Maven or Java command with `JAVA_HOME` pinned: `$env:JAVA_HOME = 'C:\Program Files\Amazon Corretto\jdk22.0.2_9'` and `$env:Path = "$env:JAVA_HOME\bin;$env:Path"` (PowerShell). Save command output under `evidence/logs/`.
- The 81 baseline tests stay as they are. The only existing test code that may change is the `buttonName` helper in `src/test/java/calculator/ui/CalculatorPanelTest.java` (an exhaustive `switch` that stops compiling when the new `Operation` constants exist). New tests are new methods. Do not edit or delete a baseline test to make it pass.
- Do not edit anything in `specs/001-baseline-calculator/` (frozen). Do not read the other track.
- Do not add behaviour that is not in spec.md or the contracts. Do not add a task, test or documented behaviour for the square root of `-0.0` or `-0`: the owner left it undefined (DL-46).
- Tests that depend on a team decision carry a comment so they can be found if it is reopened: `// LOG-17 (agreed)`, `// LOG-18 (agreed)`, `// LOG-19 (agreed)`, `// LOG-20 (agreed)`, `// LOG-21 (agreed)`.
- Record every result in `evidence/08-cr01-test-results.md` as Pass, Fail, Blocked or Not run. Pass only if the behaviour was observed. Baseline results stay unchanged in `evidence/06-test-results.md`.
- After each checkpoint task (T009, T023, T031, T036, T045), also update `evidence/00-STATUS.md`, `evidence/05-effort-intervention-log.md` and, if a decision or deviation arose, `evidence/03-decisions-log.md`.
- The agent cannot see the window. A step that needs someone to look at the window is done or confirmed by the owner. Until the owner confirms, its result is Not run or Blocked, never Pass.
- Do not commit, tag or push. The owner asks for each commit separately and approves the file list first. Commit rules: no `Co-Authored-By` line; repository-local `user.email` is the noreply address; `evidence/logs/` is ignored by the root `.gitignore`, so new log files need `git add -f`. No freeze or tag is part of these tasks.

---

## Phase 1: Setup (Shared Infrastructure)

**Purpose**: Results file, a green starting point and the baseline snapshot.

- [X] T001 Create `evidence/08-cr01-test-results.md` with one row per check using the template fields in `shared/acceptance-criteria.md` §4 (Test ID, Track = George/Spec Kit, Phase = Change CR-01, Commit hash, Environment / Java version, Status, Expected, Actual, Evidence location, Defect / issue ID, Notes). Rows: `CR-AC-01` to `CR-AC-22`, then `BASE-AC-01` to `BASE-AC-19` as individual re-run rows (Notes: "re-run against the changed application, CR-AC-21"). Every row starts as Not run. Add a short header: environment, plan reference, and "baseline results are in `06-test-results.md`".
- [X] T002 Confirm the starting point is green before any change: run `mvn -version` (expect Java 22.0.2, vendor Amazon.com Inc.) and `mvn -B clean verify` on the unchanged tree. Expected: BUILD SUCCESS and 81 tests, 0 failures. Save output to `evidence/logs/cr-t002-start-verify.txt`. Record `git rev-parse HEAD` and `git status --short` in the same file. If the count is not 81 or a test fails, stop and tell the owner.
- [X] T003 [P] Create a list of the 81 baseline tests (class and method names) from the Surefire report in `target/surefire-reports/` and save it to `evidence/logs/cr-t003-baseline-test-names.txt`. It is used at T040 to show that no baseline test was removed or edited.

**Checkpoint**: Results file exists, starting build is green.

---

## Phase 2: Foundational (Blocking Prerequisites)

**Purpose**: The new types that every story needs: three `Operation` constants with a unary flag, the new error and its message. Source: data-model.md, research C-04, C-05, C-08.

**CRITICAL**: No user story work starts until this phase is complete. Adding the constants breaks the compile of `Calculator.compute`, `ErrorMessages` and the test helper. Two existing baseline tests (`startsInTheInitialState`, `selectingAnOperationLeavesExactlyThatButtonSelected`) loop over every `Operation` and look up its button, so the three buttons must exist as soon as the constants do. These edits are therefore made together in T007 (analysis finding I1).

- [X] T004 [P] Write `src/test/java/calculator/core/OperationTest.java` (new file): `SQUARE`, `SQUARE_ROOT` and `RECIPROCAL` exist with symbols `x²`, `√x` and `1/x` (literal characters from CR-01 §2.1); `isUnary()` is true for those three and false for `ADD`, `SUBTRACT`, `MULTIPLY`, `DIVIDE`; the four baseline symbols are unchanged (`+`, `−` U+2212, `×`, `÷`). Covers `CR-FR-01`, FR-001.
- [X] T005 [P] Extend the `buttonName` helper in `src/test/java/calculator/ui/CalculatorPanelTest.java` with `SQUARE -> "squareButton"`, `SQUARE_ROOT -> "squareRootButton"`, `RECIPROCAL -> "reciprocalButton"` (this is the one allowed change to existing test code). Do not touch any other existing method.
- [X] T006 Run `mvn -B test` and confirm the new tests fail or do not compile (red). Save output to `evidence/logs/cr-t006-red-before-foundation.txt`.
- [X] T007 Edit `src/main/java/calculator/core/Operation.java`: add `SQUARE("x²")`, `SQUARE_ROOT("√x")`, `RECIPROCAL("1/x")` after `DIVIDE`, using the literal characters (same practice as baseline DL-27), and an `isUnary()` method: true for these three only. Edit `src/main/java/calculator/core/CalculationError.java`: add `NEGATIVE_SQUARE_ROOT` as the last constant (priority 5; the enum is declared in priority order). Edit `src/main/java/calculator/ui/ErrorMessages.java`: add the case for `NEGATIVE_SQUARE_ROOT` with the exact text `Cannot take square root of a negative number` (CR-01 §5); leave the other four messages as they are. In `src/main/java/calculator/ui/CalculatorPanel.java` add the three toggle buttons to the existing `operationGroup`, after `÷`, labelled with the operation's symbol, named `squareButton`, `squareRootButton` and `reciprocalButton` (use the existing `addOperationButton` helper, so they join the group and the `EnumMap`); do not hide or disable the Second number field; leave `calculate()` and `clear()` unchanged. In `src/main/java/calculator/core/Calculator.java` make the `compute` path compile again with a temporary placeholder: when the operation is `SQUARE`, `SQUARE_ROOT` or `RECIPROCAL`, `calculate` returns `new CalculationOutcome.Failure(CalculationError.INVALID_NUMBER)` and does not throw (analysis finding I3). This is a placeholder only: it shows an error and no result, it is not a behaviour of the product, it is not tested and it must not be recorded as a result. T016 to T018 replace it.
- [X] T008 Run `mvn -B test`. Expected: the 81 baseline tests plus the new `OperationTest` tests pass. Save output to `evidence/logs/cr-t008-foundation-tests.txt`.
- [X] T009 Checkpoint: update `evidence/00-STATUS.md`, `evidence/05-effort-intervention-log.md` and `evidence/03-decisions-log.md` (the temporary placeholder that returns a failure for the three new operations, the three buttons added in T007 instead of T019, and the `isUnary()` name are task-level choices; record them).

**Checkpoint**: New types compile; baseline tests still pass.

---

## Phase 3: User Story 1 - Calculate square, square root and reciprocal of one number (Priority: P1) MVP

**Goal**: Select `x²`, `√x` or `1/x`, enter one number, calculate by button or Enter, and read the correct result; the Second number never matters.

**Independent Test**: Launch the application, enter `-3`, leave Second number empty, select `x²`, press Calculate, and read `9`.

**Covers**: `CR-FR-01` to `CR-FR-05`, `CR-FR-07`; `CR-AC-01` to `CR-AC-07`, `CR-AC-09` to `CR-AC-11`, `CR-AC-16`, `CR-AC-17`; LOG-17, LOG-20. Error cases are US2.

### Tests for User Story 1 (write first, run red)

All core tests call `Calculator.calculate(first, second, operation)` and add new methods to `src/test/java/calculator/core/CalculatorTest.java`. Expected values come from `contracts/calculation-contract.md` and the boundary list in research.md.

- [X] T010 [US1] Add square tests to `CalculatorTest.java`: `-3` gives `9` (`CR-AC-02`); `1.2` gives `1.44` (`CR-AC-03`); `0` gives `0` (`CR-AC-04`); `-0.0` gives `0` (never `-0`); `12345678901234567890` gives its exact square (compute the expected string independently and write it in the test); `0.00000000001` gives `0.0000000000000000000001` (exact, plain notation); `2.5` gives `6.25`; `10` gives `100`. Second number empty in all.
- [X] T011 [US1] Add square-root tests to `CalculatorTest.java`: `9` gives `3`, `2` gives `1.4142135624`, `0` gives `0` (`CR-AC-05` to `07`); `0.0` gives `0`; `3` gives `1.7320508076`; `5` gives `2.2360679775`; `0.5` gives `0.7071067812`; `1.44` gives `1.2`; `0.25` gives `0.5`; `4` gives `2`; the exact tie `0.0000000000000000000025` gives `0.0000000001`; just below the tie `0.0000000000000000000024999999999999999999` gives `0`; `0.000000000000000000001` gives `0`; `123456789012345678901234567890` gives `351364182882014.4253111222`. Do not add any test for `-0.0` or `-0`.
- [X] T012 [US1] Add reciprocal tests to `CalculatorTest.java`: `4` gives `0.25`, `3` gives `0.3333333333`, `-4` gives `-0.25` (`CR-AC-09` to `11`); `1` gives `1`; `0.5` gives `2`; `-1` gives `-1`; `2048` gives `0.0004882813` and `-2048` gives `-0.0004882813` (ties away from zero); `30000000000` and `-30000000000` give `0` (not `-0`), commented `// LOG-19 (agreed)`.
- [X] T013 [US1] Add Second-number tests to `CalculatorTest.java`, for each of the three unary operations with First `4`: Second empty, Second `7`, Second `abc` and Second `1,5` all give the same outcome as with Second empty and never an error (`CR-AC-16`, `CR-AC-17`), commented `// LOG-17 (agreed)`. Also confirm a binary operation is unchanged: `4`, empty, ADD still gives `EMPTY_OPERAND`.
- [X] T014 [P] [US1] Add panel tests to `src/test/java/calculator/ui/CalculatorPanelTest.java` (new methods only; the button-existence, label and single-selection tests are expected to pass at once because T007 already added the buttons, so record them as green and not red): the buttons `squareButton`, `squareRootButton` and `reciprocalButton` exist with labels `x²`, `√x`, `1/x` (`CR-AC-01`, automated part); choosing any of the seven buttons leaves exactly that button selected; the Second number field stays enabled with a unary operation selected (`// LOG-20 (agreed)`); square of `-3` with Second empty gives `9` in `resultLabel`; square root of `2` gives `1.4142135624`; reciprocal of `3` gives `0.3333333333`; with a unary operation selected and a valid First number, pressing Enter in the First field and in the Second field gives the same result as Calculate (`CR-FR-03`); a different value in Second does not change the result (`CR-AC-17`); a shown result stays when the operation is changed until the next Calculate (`// LOG-13 (agreed)`).
- [X] T015 [US1] Run `mvn -B test` and confirm the new US1 calculation tests fail (red); the button tests from T014 are expected to be green already. Save output to `evidence/logs/cr-t015-red-before-us1.txt`.

### Implementation for User Story 1

- [X] T016 [US1] Edit `src/main/java/calculator/core/Calculator.java`: choose the operands to examine from the operation. Binary operation: exactly the baseline behaviour. Unary operation: parse only the First number; the Second text is not parsed, not checked and cannot produce an error, even if malformed (LOG-17; research C-04). No operation (`null`): keep the baseline behaviour (both fields examined). Keep the order: empty operand, then no operation, then invalid number, then domain error (LOG-18). Replace the temporary placeholder (the `INVALID_NUMBER` failure for the three new operations) from T007.
- [X] T017 [US1] In `Calculator.java` implement `SQUARE` as `x.multiply(x)` (exact, no scale) and `RECIPROCAL` as `BigDecimal.ONE.divide(x, 10, RoundingMode.HALF_UP)` (the same scale and rounding as baseline division, research C-02), both followed by the existing `ResultFormatter`. The zero check is added in US2.
- [X] T018 [US1] Implement `SQUARE_ROOT` per research C-01, rounded once and without `double`: `T = x × 4 × 10^20` as an exact `BigDecimal`; `N = floor(T)` as a `BigInteger` (`setScale(0, RoundingMode.FLOOR).toBigInteger()`); `q = N.sqrt()`; `R = (q + 1) >> 1`; result `new BigDecimal(R, 10)` passed to `ResultFormatter`. Put it in a small `private static` method in `Calculator.java`, or in a separate package-private class `SquareRoot.java` in `src/main/java/calculator/core/` if that keeps `Calculator` readable (record the choice in the decisions log). The negative-input check is added in US2; this task handles non-negative input.
- [X] T019 [US1] Confirm `src/main/java/calculator/ui/CalculatorPanel.java` against `contracts/ui-contract.md`: the three buttons added in T007 are in the group after `÷`, the Second number field is neither hidden nor disabled, and `calculate()` and `clear()` are unchanged. Make a code change here only if a US1 panel test shows a need, and record it. (Button creation moved to T007 after analysis finding I1; the ID is kept so later references stay valid.)
- [X] T020 [US1] Run `mvn -B test`. Expected: all baseline tests and all US1 tests pass. Save output to `evidence/logs/cr-t020-us1.txt`. If a baseline test fails, stop, do not edit it, record the defect in `evidence/08-cr01-test-results.md` and tell the owner.
- [X] T021 [US1] Build the jar with `mvn -B clean verify` (pinned `JAVA_HOME`), save output to `evidence/logs/cr-t021-build-for-owner-launch.txt`, and give the owner the exact run command from quickstart.md. The agent does not use its own launch as evidence.
- [X] T022 [US1] OWNER: launch the jar and confirm in the real window: the buttons `x²`, `√x`, `1/x` are visible next to `+ − × ÷` (`CR-AC-01`); `-3`, Second empty, `x²`, Calculate gives `9` (`CR-AC-02`); `4`, `abc` in Second, `1/x`, Enter gives `0.25` (`CR-AC-17`, Enter); `2`, `√x` gives `1.4142135624` (`CR-AC-06`). Save screenshots as `evidence/screenshots/cr01-01-*.png` and so on, or give notes. The agent records them as the owner's report.
- [X] T023 [US1] Checkpoint: in `evidence/08-cr01-test-results.md` set `CR-AC-02` to `07` (except as noted), `CR-AC-09` to `11`, `CR-AC-16` and `CR-AC-17` to Pass where the automated test was observed passing (name the test and log); set `CR-AC-01` to Pass only after T022 (otherwise Not run). Then update `evidence/00-STATUS.md`, `evidence/05-effort-intervention-log.md` and `evidence/03-decisions-log.md`. MVP note: the application is not safe against `√x` of a negative number or `1/x` of zero until US2.

**Checkpoint**: The three operations work for valid input, by button and by Enter, and the Second number is ignored.

---

## Phase 4: User Story 2 - Clear feedback for impossible or invalid unary input (Priority: P2)

**Goal**: Negative square root, reciprocal of zero, empty and malformed First number each give an inline error, no result and no crash.

**Independent Test**: Select `√x`, enter `-1`, calculate, and read `Cannot take square root of a negative number` with no result.

**Covers**: `CR-FR-06`, `CR-FR-08`, `CR-FR-09`, FR-013; `CR-AC-08`, `CR-AC-12` to `CR-AC-15`; LOG-18, LOG-21.

### Tests for User Story 2 (write first, run red)

- [X] T024 [US2] Add error tests to `src/test/java/calculator/core/CalculatorTest.java`: square root of `-1`, `-0.5` and `-1000` give `NEGATIVE_SQUARE_ROOT` (`CR-AC-08`); reciprocal of `0`, `0.0`, `-0.0` and `-0` gives `DIVISION_BY_ZERO` (`CR-AC-12`, `CR-AC-13`); for each unary operation an empty First number gives `EMPTY_OPERAND` whatever the Second is (`CR-AC-14`, `// LOG-21 (agreed)`) and `abc` as First gives `INVALID_NUMBER` (`CR-AC-15`); First `abc` with `√x` gives `INVALID_NUMBER`, not a domain error; empty First with no operation gives `EMPTY_OPERAND`; valid First with no operation gives `NO_OPERATION`; spaces-only First gives `EMPTY_OPERAND` (`// LOG-18 (agreed)` on the priority cases). Do not add a test for the square root of `-0.0` or `-0`.
- [X] T025 [P] [US2] Add panel tests to `src/test/java/calculator/ui/CalculatorPanelTest.java` (new methods only): `√x` with `-1` shows exactly `Cannot take square root of a negative number` in `errorLabel` and nothing in `resultLabel` (`CR-AC-08`); `1/x` with `0`, `0.0` and `-0.0` shows exactly `Cannot divide by zero` and no result (`CR-AC-12`, `CR-AC-13`); each unary operation with empty First shows `Enter both numbers` (`CR-AC-14`); each with `abc` shows `Invalid number` (`CR-AC-15`); no dialog is opened (no new top-level window appears) and no exception is thrown in any of these cases.
- [X] T026 [US2] Run `mvn -B test` and confirm the new US2 tests fail (red). Save output to `evidence/logs/cr-t026-red-before-us2.txt`.

### Implementation for User Story 2

- [X] T027 [US2] Edit `src/main/java/calculator/core/Calculator.java`: for `RECIPROCAL`, return `DIVISION_BY_ZERO` when the parsed First number has `signum() == 0` (this covers `0`, `0.0`, `-0.0`, `-0`) before dividing; for `SQUARE_ROOT`, return `NEGATIVE_SQUARE_ROOT` when the parsed First number is negative (`signum() < 0`, the same test used for any input; the result for a zero-signed input is not a requirement and is not documented or tested, DL-46). Domain checks come after the invalid-number check (LOG-18). Remove nothing else.
- [X] T028 [US2] Run `mvn -B test`. Expected: all baseline tests and all US1 and US2 tests pass. Save output to `evidence/logs/cr-t028-us2.txt`.
- [X] T029 [US2] OWNER: in the real window confirm: `-1` with `√x` shows the negative-root message and no result (`CR-AC-08`); `0`, `0.0` and `-0.0` with `1/x` each show `Cannot divide by zero` (`CR-AC-12`, `CR-AC-13`); empty First with each of the three shows `Enter both numbers` and `abc` shows `Invalid number` (`CR-AC-14`, `CR-AC-15`); no pop-up appears. Save screenshots or notes under `evidence/screenshots/` (prefix `cr01-`). Recorded as the owner's report.
- [X] T030 [US2] Update `evidence/08-cr01-test-results.md`: `CR-AC-08`, `CR-AC-12` to `CR-AC-15` Pass from the observed automated tests (name them); mark the real-window part from T029 separately in Notes (Pass only if the owner confirmed).
- [X] T031 [US2] Checkpoint: update `evidence/00-STATUS.md`, `evidence/05-effort-intervention-log.md` and `evidence/03-decisions-log.md`.

**Checkpoint**: Every impossible or invalid unary request gives an inline error; the application keeps running.

---

## Phase 5: User Story 3 - Recover from a unary error and reset (Priority: P3)

**Goal**: After a unary error the user can correct the input and calculate, or press Clear and start over.

**Independent Test**: Trigger the negative square-root error, enter `16`, calculate, and read `4`.

**Covers**: `CR-FR-10`, FR-015; `CR-AC-18` to `CR-AC-20`.

- [X] T032 [US3] Add panel tests to `src/test/java/calculator/ui/CalculatorPanelTest.java` (new methods only): `-1` with `√x` shows the error, then First changed to `16` and Calculate shows `4` with the error gone (`CR-AC-18`); `0` with `1/x` shows the error, then First changed to `4` and Calculate shows `0.25` (`CR-AC-19`); after a unary calculation Clear leaves both fields empty, none of the seven buttons selected, result and error empty (`CR-AC-20`), repeated for each unary operation; a sequence of calculations that mixes `+`, `x²`, `√x`, `1/x` and `÷` stays usable and each result matches the current fields (extends the idea of `BASE-AC-16` for the changed application).
- [X] T033 [US3] Run `mvn -B test`. These tests rely on behaviour that existing code should already provide, so they may pass at once. Record in the log whether they were red or green when first run; if one fails, fix the code (not the test) and record the defect. Save output to `evidence/logs/cr-t033-us3.txt`.
- [X] T034 [US3] OWNER: in the real window do `-1` with `√x`, then change to `16` and Calculate (expect `4`); `0` with `1/x`, then change to `4` and Calculate (expect `0.25`); then click Clear after a unary calculation (expect the initial state, no button selected). Save screenshots or notes (prefix `cr01-`). Recorded as the owner's report.
- [X] T035 [US3] Update `evidence/08-cr01-test-results.md`: `CR-AC-18`, `CR-AC-19`, `CR-AC-20` (automated Pass, real-window result from T034 noted separately).
- [X] T036 [US3] Checkpoint: update `evidence/00-STATUS.md`, `evidence/05-effort-intervention-log.md` and `evidence/03-decisions-log.md`.

**Checkpoint**: Recovery and Clear work for the unary operations.

---

## Phase 6: User Story 4 - Baseline still works and the changed application still builds (Priority: P3)

**Goal**: Every baseline check is re-run against the changed application and has its own recorded result; the documented build and test commands work from a clean checkout.

**Independent Test**: Run `mvn -B clean verify`, then compare the 19 baseline rows in `evidence/08-cr01-test-results.md` with the baseline results in `evidence/06-test-results.md`.

**Covers**: `CR-FR-11`, NFR-001; `CR-AC-21`, `CR-AC-22`.

- [X] T037 [US4] Run the full build `mvn -B clean verify` (pinned `JAVA_HOME`), save output to `evidence/logs/cr-t037-final-verify.txt`, and record the test count (81 baseline plus the new tests), failures and the jar name. Checkpoint update of `evidence/00-STATUS.md`, `evidence/05-effort-intervention-log.md` and `evidence/03-decisions-log.md`.
- [X] T038 [US4] Re-run `BASE-AC-04` to `BASE-AC-14` and `BASE-AC-16` to `BASE-AC-19` one by one: for each, name the existing automated tests that observe it (from the T037 Surefire report), and write one row in `evidence/08-cr01-test-results.md` with Status Pass only if those tests were seen passing in this run. Use the plan's re-run table (`plan.md`, "Baseline checks to re-run"). No aggregate row.
- [X] T039 [US4] OWNER: in the real window of the changed jar, repeat the baseline checks that need eyes or hands: `BASE-AC-01` (networking off, launch, screenshot or note), `BASE-AC-02` and `BASE-AC-03` (new screenshots, the window is wider), `BASE-AC-10` (Clear after `7 + 5`), `BASE-AC-11` (empty First, empty Second and both empty with `+`), `BASE-AC-17` (no operation selected), `BASE-AC-19` (Enter in both fields with `+`). Save screenshots or notes (prefix `cr01-`). Recorded as the owner's report. Results stay Not run or Blocked until the owner confirms.
- [X] T040 [US4] Compare the baseline test list from T003 with the Surefire report of T037: every baseline test name is still present and passed; record any difference. List `git diff --stat 88f56d0 -- src/test` and confirm that the only edited baseline test code is the `buttonName` helper. Save to `evidence/logs/cr-t040-baseline-test-integrity.txt`.
- [X] T041 [US4] Regression review (CR-01 §7.6, protocol §5 step 6): for each of `BASE-AC-01` to `BASE-AC-19`, compare the baseline status in `evidence/06-test-results.md` with the new status. Count a regression only where the baseline Pass was observed and the same check now fails. Write the table and any regression or unintended behaviour change (for example the wider window) into `evidence/08-cr01-test-results.md`.
- [X] T042 [US4] OWNER: first ask the owner whether to remove the Sync Impact Report comment at the top of `.specify/memory/constitution.md`; on a yes, remove it (as in baseline DL-32; the version stays 1.1.0) and record it (analysis finding I2). Then ask for an interim commit of the changed code and evidence so the clean-checkout check can run (not the freeze). The agent shows the exact file list and waits for the owner's yes; `git add -f` for new files in `evidence/logs/`; no `Co-Authored-By`; no push.
- [X] T043 [US4] After the commit in T042: clone it to a new short path (for example `C:\Temp\cr01-clean`; a long path failed in the baseline), set `JAVA_HOME` as above, run `mvn -B clean verify` and then `java -jar` as documented in quickstart.md, unchanged. Save output to `evidence/logs/cr-t043-clean-checkout-build.txt` and `cr-t043-clean-checkout-launch.txt`. Record `BASE-AC-15` and `CR-AC-22` as Pass only if the build, the tests and the launch were observed to work; record prerequisites and any failure. Delete the temporary clone only when the owner says so.
- [X] T044 [US4] Update `evidence/08-cr01-test-results.md` with `CR-AC-21` (a summary that points to the 19 individual rows, not a replacement for them) and `CR-AC-22`.
- [X] T045 [US4] Checkpoint: update `evidence/00-STATUS.md`, `evidence/05-effort-intervention-log.md` and `evidence/03-decisions-log.md`.

**Checkpoint**: All 22 change checks and all 19 baseline re-runs have a recorded status.

---

## Phase 7: Polish & Cross-Cutting Concerns

- [X] T046 [P] Code review of the change, saved to `evidence/logs/cr-t046-review.txt`: no dialog classes, no network, file or persistence classes, no GUI imports in `calculator.core`, no `double` or `Math.sqrt` in the calculation code, only the three unary operations were added, and there is no code, test or text that decides the square root of `-0.0`.
- [X] T047 [P] Update `specs/002-scientific-operations/quickstart.md` line 3 and the build section: replace "have not been run" with the date the commands were run and the real test count; do not change other content. Do not edit `specs/001-baseline-calculator/`.
- [X] T048 Add a dated addendum (do not edit in place) at the end of `evidence/07-cr01-impact-analysis.md`: the files actually changed (`git diff --stat 88f56d0`) next to the predicted list, with each prediction marked correct, partly correct or wrong, and any unpredicted change. State which predicted regressions happened and which did not.
- [X] T049 Final update of `evidence/08-cr01-test-results.md` (counts of Pass, Fail, Blocked and Not run for the 22 change checks and the 19 re-runs, listed separately), `evidence/00-STATUS.md`, `evidence/05-effort-intervention-log.md` and `evidence/03-decisions-log.md`. Report to the owner. Do not freeze, tag or push.

---

## Dependencies & Execution Order

- Phase 1 (T001 to T003) first. T003 needs T002's build output.
- Phase 2 (T004 to T009) blocks all stories. T004 and T005 are in different files (parallel); T006 runs after both; T007 after T006 (T007 now also adds the three buttons to `CalculatorPanel.java`); T008 and T009 after T007.
- US1 (T010 to T023) after Phase 2. Tests T010 to T013 share `CalculatorTest.java` and run in order; T014 is in a different file and can be written in parallel. T015 after all tests. T016 to T018 share `Calculator.java` and run in order; T019 is only a confirmation of the panel (the buttons were added in T007) and follows T015; T020 after T016 to T019; T021 after T020; T022 needs the owner.
- US2 (T024 to T031) after US1 (it edits the same `Calculator.java`). T024 and T025 are different files; T026 after both; T027 after T026.
- US3 (T032 to T036) after US2. It adds tests only; code changes happen only if a test fails.
- US4 (T037 to T045) after US1 to US3. T038 and T040 can run once T037 is done. T039 and T042 need the owner. T043 needs T042.
- Polish (T046 to T049) last. T046 and T047 are different files and parallel.

### Parallel opportunities

- T003 with T001 and T002 (read-only list).
- T004 with T005.
- T014 with T010 to T013; T025 with T024.
- T046 with T047.

## Implementation Strategy

- **MVP**: Phase 1, Phase 2 and US1 (through T023). The three operations work for valid input and the window can be shown to the owner (T022). Not crash-safe for `√x` of a negative number and `1/x` of zero until US2.
- **Then**: US2 (errors), US3 (recovery and Clear), US4 (baseline re-run, build, clean checkout), Polish.
- **Stops that need the owner**: T022, T029, T034, T039 (real window), T042 (commit approval) and T043 (clean checkout after the commit). Until the owner confirms them, those results are Not run or Blocked.
- **Gate**: this task list was generated after the plan was approved (AP-03). No `/speckit-implement` and no code until the owner says so.
