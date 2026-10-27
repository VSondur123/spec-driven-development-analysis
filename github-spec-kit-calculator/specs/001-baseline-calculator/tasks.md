---

description: "Task list for the baseline calculator"
---

# Tasks: Baseline Calculator

**Input**: Design documents from `/specs/001-baseline-calculator/`

**Prerequisites**: plan.md (approved 2026-10-27), spec.md, research.md, data-model.md, contracts/

**Tests**: Included. The baseline requires "Tests for our shared acceptance criteria" (`shared/baseline-requirements.md` §2), and the approved plan maps each `BASE-AC-*` check to a test (plan.md, Requirement Coverage). Within each story, tests come before the code they check.

**Organization**: Tasks are grouped by user story. The Swing GUI and the `calculator.core` package are shared by all stories, so the stories build on each other in priority order (US1, then US2, then US3). US4 only needs the packaged application.

## Format: `[ID] [P?] [Story] Description`

- **[P]**: Can run in parallel (different files, no dependencies on unfinished tasks)
- **[Story]**: User story the task belongs to (US1 to US4)
- Paths are relative to the track folder `github-spec-kit-calculator/`

## Path Conventions

- Source: `src/main/java/calculator/core/` and `src/main/java/calculator/ui/`
- Tests: `src/test/java/calculator/core/` and `src/test/java/calculator/ui/`
- Evidence: `evidence/`

## Rules that apply to every task

- Run every Maven or Java command with `JAVA_HOME` pinned: `$env:JAVA_HOME = 'C:\Program Files\Amazon Corretto\jdk22.0.2_9'` and `$env:Path = "$env:JAVA_HOME\bin;$env:Path"` (PowerShell). Save command output under `evidence/logs/`.
- Do not add behaviour that is not in spec.md or the contracts. Do not read `shared/change-request-CR-01.md`.
- Tests that depend on a team decision (LOG-12 to LOG-15 in `shared/experiment-log.md`) carry a comment `// LOG-nn (agreed)` so they can be found if a decision is ever reopened. The comment read `(proposed)` until the team agreed on 2026-10-27.
- Record a result in `evidence/06-test-results.md` as Pass, Fail, Blocked or Not run. Pass only if the behaviour was observed.
- After each checkpoint task (T011, T017, T023, T026, T029), also update `evidence/00-STATUS.md` and `evidence/05-effort-intervention-log.md`.
- The agent cannot see the window. A step that needs someone to look at the window is performed or confirmed by the owner. Until the owner confirms, its result is Not run or Blocked, never Pass.
- Do not commit, tag or push. The owner asks for each commit separately. An interim commit that is not the baseline freeze is allowed so `BASE-AC-15` can be checked (owner decision, 2026-10-27). The freeze (commit, tag, hash) comes last, after every `BASE-AC-*` result is recorded.

---

## Phase 1: Setup (Shared Infrastructure)

**Purpose**: Build file, pinned versions and evidence files.

- [X] T001 Create `pom.xml` for artifact `baseline-calculator`, version `1.0.0`, `groupId` `study.speckit`, packaging `jar`. Properties: `maven.compiler.release` 22 and `project.build.sourceEncoding` UTF-8. Dependency: `org.junit.jupiter:junit-jupiter` 6.1.3, scope test. Plugins pinned exactly as in research.md: `maven-compiler-plugin` 3.16.0, `maven-resources-plugin` 3.5.0, `maven-surefire-plugin` 3.6.1 with system property `java.awt.headless=true`, `maven-jar-plugin` 3.5.1 with manifest `Main-Class` `calculator.ui.CalculatorApp`. No other dependency.
- [X] T002 [P] Create `evidence/05-effort-intervention-log.md` (date, activity, active time, human interventions, retries and tool failures, per entry) and `evidence/06-test-results.md` (one row per test per track using the template fields in `shared/acceptance-criteria.md` §4: Test ID, Track = George/Spec Kit, Phase = Baseline, Commit hash, Environment / Java version, Status, Expected, Actual, Evidence location, Defect / issue ID, Notes). Every row starts as Not run.
- [X] T003 Verify the build skeleton with pinned `JAVA_HOME`: run `mvn -version` (expect Java 22.0.2, vendor Amazon.com Inc.), then `mvn -B clean verify`. Expected: the build succeeds with no tests yet and all pinned plugin versions resolve. Save output to `evidence/logs/t003-build-skeleton.txt`. If a pinned version fails, record the failure and the replacement in `evidence/03-decisions-log.md` before changing `pom.xml`.

**Checkpoint**: The empty project builds on Corretto 22 from the command line.

---

## Phase 2: Foundational (Blocking Prerequisites)

**Purpose**: The value types and input rules used by every story. Source: data-model.md, calculation-contract.md.

**CRITICAL**: No user story work starts until this phase is complete.

- [X] T004 [P] Create `Operation` enum in `src/main/java/calculator/core/Operation.java` with `ADD`, `SUBTRACT`, `MULTIPLY`, `DIVIDE`. Each carries its button symbol: `+`, `−` (U+2212), `×`, `÷`.
- [X] T005 [P] Create `CalculationError` enum in `src/main/java/calculator/core/CalculationError.java` with `EMPTY_OPERAND`, `NO_OPERATION`, `INVALID_NUMBER`, `DIVISION_BY_ZERO` (priority 1 to 4 in this order).
- [X] T006 [P] Create `CalculationOutcome` in `src/main/java/calculator/core/CalculationOutcome.java`: exactly one of a success carrying the result text, or a failure carrying one `CalculationError`. A failure carries no result text.
- [X] T007 [P] Write `OperandParserTest` in `src/test/java/calculator/core/OperandParserTest.java`. After removing leading and trailing space characters (U+0020 only): empty text and spaces-only text are EMPTY; `0`, `7`, `-7`, `1.2`, `-0.5`, `  7 ` are VALID with the expected `BigDecimal` value; `abc`, `1.2.3`, `1,5`, `1e3`, `--1`, `.`, `+1`, `.5`, `5.`, `-` are INVALID. Mark `.5`, `5.`, `-` and spaces-only with `// LOG-12 (agreed)`. Covers FR-009, FR-012, `BASE-AC-02`, `BASE-AC-03`, `BASE-AC-12`.
- [X] T008 [P] Write `ResultFormatterTest` in `src/test/java/calculator/core/ResultFormatterTest.java`: `3.50` becomes `3.5`; `12` stays `12`; `0.0000` and `0` become `0`; `1E+3` is written `1000`; `-5` stays `-5`; no output contains an exponent or a trailing zero after the decimal point. Covers FR-011.
- [X] T009 Implement `OperandParser` in `src/main/java/calculator/core/OperandParser.java` (after T007): strip only space characters (U+0020) at both ends; empty text gives EMPTY; text matching `-?[0-9]+(\.[0-9]+)?` gives VALID with a `BigDecimal`; anything else gives INVALID. Return the three outcomes as a small nested type inside this class (no extra file). Write `[0-9]`, not `\d`.
- [X] T010 Implement `ResultFormatter` in `src/main/java/calculator/core/ResultFormatter.java` (after T008): remove trailing zeros, write plain notation (no exponent), zero as `0`.
- [X] T011 Run `mvn -B test`; the parser and formatter tests must pass. Save output to `evidence/logs/t011-foundation-tests.txt`. Then update `evidence/00-STATUS.md` and `evidence/05-effort-intervention-log.md`.

**Checkpoint**: Input rules and result formatting are built and tested. User stories can start.

---

## Phase 3: User Story 1 - Calculate with the four basic operations (Priority: P1) MVP

**Goal**: Enter two numbers, pick an operation, calculate by button or Enter, and read the correct result.

**Independent Test**: Launch the application, enter `7` and `5`, select `+`, press Calculate, and read `12`.

**Covers**: FR-002, FR-003, FR-004, FR-009 to FR-011, FR-015, NFR-003 to NFR-004; `BASE-AC-02` to `-09`, `-16`, `-18`, `-19`.

### Tests for User Story 1 (write first, confirm they fail)

- [X] T012 [P] [US1] Write the success cases in `src/test/java/calculator/core/CalculatorTest.java`: `7+5=12`, `7-5=2`, `7*5=35`, `7/2=3.5`, `-7+2=-5`, `1.2+2.3=3.5`, `0/5=0`, `1/3=0.3333333333`, `2/2=1`, `  7 + 5 = 12` (spaces), `0 - 7 = -7`, `0 + -0 = 0` (never `-0`), and `-1/2048=-0.0004882813` and `1/2048=0.0004882813` (mark both `// LOG-15 (agreed)`). Use the exact inputs and results from `contracts/calculation-contract.md`.
- [X] T013 [P] [US1] Write `src/test/java/calculator/ui/CalculatorPanelTest.java` using the component names in `contracts/ui-contract.md` and no top-level window: the panel starts with empty fields and no operation selected; typed text is kept (`BASE-AC-02`, `-03`); selecting one operation button leaves exactly that button selected; Calculate with `7`, `5`, `+` shows `12` in `resultLabel` (`BASE-AC-04`); one test per `BASE-AC-05` to `-09`; `0 ÷ 5` and `1 ÷ 3` (`BASE-AC-18`); pressing Enter in `firstOperandField` and in `secondOperandField` (use `postActionEvent()`) gives the same result as Calculate (`BASE-AC-19`); five calculations in a row on one panel each show the result for the current fields (`BASE-AC-16`).

### Implementation for User Story 1

- [X] T014 [US1] Implement `Calculator` in `src/main/java/calculator/core/Calculator.java`: `calculate(String first, String second, Operation operation)` returning `CalculationOutcome`. For valid operands and a selected operation: parse with `OperandParser`, add/subtract/multiply exactly, divide with scale 10 and `RoundingMode.HALF_UP`, format with `ResultFormatter`. Error branches are added in T021, so this step handles valid input only.
- [X] T015 [US1] Implement `CalculatorPanel` in `src/main/java/calculator/ui/CalculatorPanel.java` (a `JPanel`, not a window): labelled `firstOperandField` ("First number") and `secondOperandField` ("Second number"); `addButton`, `subtractButton`, `multiplyButton`, `divideButton` as toggle buttons in one `ButtonGroup`, labelled `+`, `−`, `×`, `÷`; `calculateButton` ("Calculate"); `resultLabel` ("Result:" plus value). Set each component's name exactly as in `contracts/ui-contract.md`. The Calculate button and Enter in either field call the same method, which calls `Calculator.calculate` and shows the result. Standard toolkit look, no custom styling.
- [X] T016 [US1] Implement `CalculatorApp` in `src/main/java/calculator/ui/CalculatorApp.java`: `main` builds a window on the Swing event thread, adds one `CalculatorPanel`, sizes it to fit, closes the program on window close, shows it. No dialogs, no network code.
- [X] T017 [US1] (2026-10-27: automated part done; the owner launched the jar and took screenshots 01 to 03, which show the window open and `7 + 5` giving `12`) Run `mvn -B test`, then `mvn -B package`. Save output to `evidence/logs/t017-us1.txt`. Ask the owner to launch `java -jar target\baseline-calculator-1.0.0.jar` and confirm the window opens and `7 + 5` shows `12`; the agent starts the build but cannot see the window. Record `BASE-AC-02` to `-09`, `-16`, `-18`, `-19` in `evidence/06-test-results.md` as the automated tests show them. Record the manual launch as Not run until the owner confirms. At this stage invalid input can still throw inside the window (the error handling comes in T021), so do not use this manual run as evidence for any error check or `BASE-NFR-03`. Then update `evidence/00-STATUS.md` and `evidence/05-effort-intervention-log.md`.

**Checkpoint**: User Story 1 is fully functional and testable on its own (MVP; not crash-safe on invalid input until US2).

---

## Phase 4: User Story 2 - Clear feedback for invalid input and division by zero (Priority: P2)

**Goal**: Every bad request shows a distinct inline message, no result, and no crash.

**Independent Test**: Request a calculation with an empty operand; an inline error appears, no result is shown, the application keeps running.

**Covers**: FR-006, FR-007, FR-012 to FR-014, NFR-003; `BASE-AC-11`, `-12`, `-13`, `-17`.

### Tests for User Story 2 (write first, confirm they fail)

- [X] T018 [P] [US2] Add to `src/test/java/calculator/core/CalculatorTest.java`, using `contracts/calculation-contract.md`: `EMPTY_OPERAND` for empty first, empty second and both empty, and for spaces-only (`// LOG-12 (agreed)`); `NO_OPERATION` for `7`, `5`, no operation; `INVALID_NUMBER` for each of `abc`, `1.2.3`, `1,5`, `1e3`, `--1`, `.`, `+1` in either operand position, and for `.5`, `5.`, `-` (`// LOG-12 (agreed)`); `DIVISION_BY_ZERO` for `7 ÷ 0`, `7 ÷ 0.0` and `7 ÷ -0`; priority cases: empty with no operation gives `EMPTY_OPERAND`, `abc` with no operation gives `NO_OPERATION`, `abc ÷ 0` gives `INVALID_NUMBER`. No case may throw.
- [X] T019 [P] [US2] Add to `src/test/java/calculator/ui/CalculatorPanelTest.java`: for each case above, Calculate puts exactly the contract message in `errorLabel` and leaves `resultLabel` empty (`BASE-AC-11`, `-12`, `-13`, `-17`). Messages: "Enter both numbers", "Select an operation", "Invalid number", "Cannot divide by zero". A new calculation after an error clears the error; a new error after a result clears the result.

### Implementation for User Story 2

- [X] T020 [P] [US2] Create `ErrorMessages` in `src/main/java/calculator/ui/ErrorMessages.java`, mapping each `CalculationError` to its message text from `contracts/ui-contract.md`. The four messages must differ from each other.
- [X] T021 [US2] Extend `Calculator.calculate` in `src/main/java/calculator/core/Calculator.java`: check in this order and return the first fault found: empty operand (first or second), no operation, invalid number (first or second), division by zero (divisor equal to zero after parsing, so `0`, `0.0` and `-0`). Never throw for any text input or a null operation.
- [X] T022 [US2] Extend `CalculatorPanel` in `src/main/java/calculator/ui/CalculatorPanel.java`: add `errorLabel` ("Error:" plus message text, name `errorLabel`). On Calculate, first empty both `resultLabel` and `errorLabel`, then show the result or the message from `ErrorMessages`. Errors appear only in `errorLabel`; use no dialog, message box or pop-up. Design choice DL-13 (not a requirement): wrap the calculation in a handler that catches `RuntimeException`, writes it to `System.err`, and keeps the window open without introducing a new message text.
- [X] T023 [US2] Run `mvn -B test`; save output to `evidence/logs/t023-us2.txt`. Record `BASE-AC-11`, `-12`, `-13`, `-17` in `evidence/06-test-results.md`. Then update `evidence/00-STATUS.md` and `evidence/05-effort-intervention-log.md`.

**Checkpoint**: User Stories 1 and 2 both work independently.

---

## Phase 5: User Story 3 - Recover from errors and reset (Priority: P3)

**Goal**: After an error the user can correct input and calculate again, or press Clear to reach the initial state.

**Independent Test**: Trigger "Invalid number", correct the operand, calculate and read the correct result; press Clear and see everything reset.

**Covers**: FR-005, FR-008, FR-016; `BASE-AC-10`, `-14`.

### Tests for User Story 3 (write first, confirm they fail)

- [X] T024 [P] [US3] Add to `src/test/java/calculator/ui/CalculatorPanelTest.java`: recovery (enter `1,5` and `2`, `+`, Calculate gives "Invalid number"; change `1,5` to `1.5`, Calculate gives `3.5` and no error; `BASE-AC-14`); Clear after a result and after an error resets both fields to empty, deselects the operation, empties `resultLabel` and `errorLabel` (`BASE-AC-10`); editing a field or selecting another operation leaves a shown result or error unchanged (`// LOG-13 (agreed)`). Focus after Clear (DL-12) is not tested in headless mode; it is checked manually in T031.

### Implementation for User Story 3

- [X] T025 [US3] Extend `CalculatorPanel` in `src/main/java/calculator/ui/CalculatorPanel.java`: add `clearButton` ("Clear") whose action empties both fields, clears the `ButtonGroup` selection so no operation is selected, and empties `resultLabel` and `errorLabel`. Design choice DL-12 (not a requirement): then move keyboard focus to `firstOperandField`. Do not clear a shown result or error on editing a field or selecting an operation.
- [X] T026 [US3] Run `mvn -B test`; save output to `evidence/logs/t026-us3.txt`. Record `BASE-AC-10` and `-14` in `evidence/06-test-results.md`. Then update `evidence/00-STATUS.md` and `evidence/05-effort-intervention-log.md`.

**Checkpoint**: User Stories 1 to 3 all work independently.

---

## Phase 6: User Story 4 - Launch offline and reproduce the build (Priority: P3)

**Goal**: A reviewer can build, run and test from a clean checkout with the documented commands, and the application starts without a network.

**Independent Test**: Follow `quickstart.md` on a clean checkout with networking off and see the window open.

**Covers**: FR-001, NFR-001, NFR-005; `BASE-AC-01`, `-15`.

- [X] T027 [US4] Check that `quickstart.md` matches the real build: the jar name `target/baseline-calculator-1.0.0.jar`, the build command and the run command. If anything differs, correct `quickstart.md` and record the change in `evidence/03-decisions-log.md`. Note that the quickstart commands were written before the build existed.
- [X] T028 [US4] `BASE-AC-01` (manual, needs the owner): ask the owner to turn networking off and launch with the documented command; the owner or the agent saves a screenshot or short note to `evidence/screenshots/ac-01-manual-check.md`. Until the owner confirms, record `BASE-AC-01` as **Blocked** in `evidence/06-test-results.md`, not Pass.
- [ ] T029 [US4] `BASE-AC-15` (needs an interim commit that the owner asks for; this is not the baseline freeze): once the owner has committed, clone the repository into a new folder, pin `JAVA_HOME`, and follow `quickstart.md` unchanged. Record whether it built and launched, and any prerequisite that had to be added. Note the interim commit hash. Until that commit exists, record `BASE-AC-15` as **Blocked**. Do not commit on the owner's behalf. Then update `evidence/00-STATUS.md` and `evidence/05-effort-intervention-log.md`.

**Checkpoint**: The build and launch are reproducible, or the gap is recorded.

---

## Phase 7: Polish & Cross-Cutting Concerns

**Purpose**: Checks that span stories and the evidence for the baseline freeze.

- [X] T030 [P] Scope and structure review: confirm by search that none of `JOptionPane`, `JDialog`, `java.awt.Dialog`, `JPopupMenu`, `showMessageDialog`, `showConfirmDialog`, `showInputDialog`, `java.net`, or any file or persistence class is used (`BASE-FR-01`, FR-014, NFR-001); that `calculator.core` imports nothing from `javax.swing` or `calculator.ui` (NFR-004, constitution V); that only the four operations exist (spec out-of-scope list). Save the search commands and output to `evidence/logs/t030-review.txt`.
- [X] T031 [P] Owner-observed manual run of the real window, with the agent recording the owner's notes: `quickstart.md` scenarios 2 to 8, plus: controls are labelled, the selected operation is visibly indicated, result and error areas are identifiable (FR-015), no dialog appears, calculations show without a perceptible wait (`BASE-NFR-02`, observed only), and keyboard focus moves to the first field after Clear (DL-12). Save notes or screenshots under `evidence/screenshots/` and record in `evidence/06-test-results.md`. Each item stays Not run until the owner confirms it. SC-006 and `BASE-NFR-02` are the owner's observation.
- [X] T032 Add a traceability section to `evidence/06-test-results.md` mapping each `BASE-FR-*`, `BASE-NFR-*` and `BASE-AC-*` to the test class and method names (or manual evidence file) that cover it, as `plan.md` Requirement Coverage promises.
- [X] T033 Run the full `mvn -B clean verify`; save output to `evidence/logs/t033-final-verify.txt`. Record the test counts (run, passed, failed, skipped) and the interim commit hash (record "uncommitted" if none exists). Rows recorded before the freeze carry the interim hash; the freeze hash is recorded after the freeze, which only happens when the owner asks.
- [X] T034 Update `evidence/00-STATUS.md`, `evidence/05-effort-intervention-log.md` and `evidence/03-decisions-log.md` with what was done, any defect or rework, human interventions and effort. Report to the owner that the baseline is ready for review against `BASE-AC-01` to `BASE-AC-19`. Commit, tag and the freeze hash happen only if the owner asks.

---

## Dependencies & Execution Order

### Phase Dependencies

- **Setup (Phase 1)**: starts immediately. T003 needs T001.
- **Foundational (Phase 2)**: needs Setup. Blocks all stories. T009 needs T007 and T004 to T006; T010 needs T008; T011 needs T009 and T010.
- **US1 (Phase 3)**: needs Foundational. T014 needs T012; T015 needs T013 and T014; T016 needs T015; T017 needs T014 to T016.
- **US2 (Phase 4)**: needs US1 (it extends `Calculator` and `CalculatorPanel`). T021 needs T018; T022 needs T019, T020 and T021.
- **US3 (Phase 5)**: needs US2 (it extends `CalculatorPanel`, which has the result and error areas).
- **US4 (Phase 6)**: needs the packaged application from US1 (T027); T029 also needs an owner commit.
- **Polish (Phase 7)**: needs US1 to US3. T030 and T031 can start after US3.

### User Story Dependencies

- **US1 (P1)**: after Foundational; no dependency on other stories.
- **US2 (P2)**: after US1 (same files); tests can be run on their own once the story is built.
- **US3 (P3)**: after US2 (same panel file).
- **US4 (P3)**: after US1; can run in parallel with US2 and US3 except for final confirmation after the full build.

### Within Each Story

- Tests are written first and must fail before the code they check exists.
- Core code before UI code.
- Each story ends with a test run and evidence entry before the next starts.

### Parallel Opportunities

- Phase 1: T002 alongside T001.
- Phase 2: T004 to T008 are all in different files and can run together; T009 and T010 can run together once their tests exist.
- US1: T012 and T013 together.
- US2: T018, T019 and T020 together.
- Polish: T030 and T031 together.
- Tasks that edit `Calculator.java`, `CalculatorPanel.java` or `CalculatorTest.java` or `CalculatorPanelTest.java` are not marked [P] with each other, since they share files.

---

## Parallel Example: User Story 1

```text
# Write the two test files together:
Task: "Write the success cases in src/test/java/calculator/core/CalculatorTest.java"
Task: "Write src/test/java/calculator/ui/CalculatorPanelTest.java"
```

---

## Implementation Strategy

### MVP First (User Story 1 Only)

1. Phase 1: Setup
2. Phase 2: Foundational
3. Phase 3: User Story 1
4. Stop and check: `7 + 5 = 12` works through the button and Enter, in the real window and in tests

### Incremental Delivery

1. Setup and Foundational give a building, tested base.
2. US1 gives a working calculator; US2 adds error handling; US3 adds recovery and Clear; US4 proves the build and launch.
3. Each step ends with a test run and an evidence entry.

---

## Notes

- Each task names its file; the Maven layout is the approved D-08.
- `// LOG-nn (agreed)` tests change only if the team reopens LOG-12 to LOG-15 (agreed 2026-10-27).
- DL-12 (focus after Clear) and DL-13 (exception safeguard) are design choices, not requirements, and no `BASE-AC-*` result depends on them.
- Commit, tag and push only when the owner asks.
