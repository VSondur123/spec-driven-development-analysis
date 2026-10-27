# Test results — George / GitHub Spec Kit track, Baseline

One row per test. Fields follow `shared/acceptance-criteria.md` §4. Status is Pass, Fail, Blocked or Not run. A test counts as Pass only if the behaviour was observed. Track = George/Spec Kit, Phase = Baseline.

Environment for all rows unless stated: Windows 11 Home 10.0.26200, Amazon Corretto 22.0.2.9.1, Maven 3.9.16, `JAVA_HOME` pinned (see `01-environment.md`).

Commit hash: none yet (uncommitted working tree). An interim commit may be made later at the owner's request (AP-02); the freeze hash is recorded after the freeze.

| Test ID | Commit hash | Status | Expected | Actual | Evidence location | Defect | Notes |
|---|---|---|---|---|---|---|---|
| BASE-AC-01 | uncommitted | Pass | GUI opens with networking off (manual) | The GUI opened with networking off (owner statement) and is shown open in the screenshot; the tray shows a globe icon where the Wi-Fi icon normally is | evidence/screenshots/08-offline.png; evidence/screenshots/ac-01-manual-check.md |  | Manual check. Pass rests on the owner confirmation plus the screenshot; the icon alone does not prove offline |
| BASE-AC-02 | uncommitted | Pass | Valid integer operand accepted and displayed | In the real window the integer operands `7` and `5` are shown in the two labelled fields | evidence/screenshots/02-add.png (viewed by the agent); also 01-initial.png |  | Observed in screenshots taken by the owner on 2026-10-27. Automated test CalculatorPanelTest.integerAndDecimalTextIsKept also passes |
| BASE-AC-03 | uncommitted | Pass | Valid decimal operand accepted and displayed | In the real window the decimal operands `1.2` and `2.3` are shown in the two labelled fields | evidence/screenshots/03-decimal.png (viewed by the agent) |  | Observed in a screenshot taken by the owner on 2026-10-27. Automated test also passes |
| BASE-AC-04 | uncommitted | Pass | `7 + 5` = `12` | Result `12` shown in resultLabel | evidence/logs/t017-us1.txt (54 tests run, 0 failures) |  | Automated, headless. CalculatorTest.addition; CalculatorPanelTest.addition |
| BASE-AC-05 | uncommitted | Pass | `7 - 5` = `2` | Result `2` shown in resultLabel | evidence/logs/t017-us1.txt (54 tests run, 0 failures) |  | Automated, headless. CalculatorTest.subtraction; CalculatorPanelTest.subtraction |
| BASE-AC-06 | uncommitted | Pass | `7 * 5` = `35` | Result `35` shown in resultLabel | evidence/logs/t017-us1.txt (54 tests run, 0 failures) |  | Automated, headless. CalculatorTest.multiplication; CalculatorPanelTest.multiplication |
| BASE-AC-07 | uncommitted | Pass | `7 / 2` = `3.5` | Result `3.5` shown in resultLabel | evidence/logs/t017-us1.txt (54 tests run, 0 failures) |  | Automated, headless. CalculatorTest.divisionWithDecimalResult; CalculatorPanelTest.division |
| BASE-AC-08 | uncommitted | Pass | `-7 + 2` = `-5` | Result `-5` shown in resultLabel | evidence/logs/t017-us1.txt (54 tests run, 0 failures) |  | Automated, headless. CalculatorTest.negativeOperand; CalculatorPanelTest.negativeOperand |
| BASE-AC-09 | uncommitted | Pass | `1.2 + 2.3` = `3.5` | Result exactly `3.5` shown in resultLabel | evidence/logs/t017-us1.txt (54 tests run, 0 failures) |  | Automated, headless. CalculatorTest.decimalOperandsGiveNoFloatingPointArtefact; CalculatorPanelTest.decimalOperands |
| BASE-AC-10 | uncommitted | Pass | Clear resets inputs, operation, result, error | After a result and after an error, Clear left both fields empty, no operation selected, resultLabel and errorLabel empty | evidence/logs/t026-us3.txt (81 tests run, 0 failures) |  | Automated, headless. CalculatorPanelTest.clearAfterAResultReturnsToTheInitialState, clearAfterAnErrorReturnsToTheInitialState, calculatorIsUsableAfterClear. Focus after Clear (DL-12, design choice) is not tested headless |
| BASE-AC-11 | uncommitted | Pass | Empty operand: "Enter both numbers", no result | Empty first, empty second and both empty each showed "Enter both numbers" in errorLabel; resultLabel stayed empty; no exception | evidence/logs/t023-us2.txt (73 tests run, 0 failures) |  | Automated, headless. CalculatorTest.emptyOperandIsReportedWhicheverOperandIsEmpty; CalculatorPanelTest.emptyOperandShowsEnterBothNumbers. Spaces-only case (LOG-12, agreed) also tested |
| BASE-AC-12 | uncommitted | Pass | Malformed input: "Invalid number", no crash | `abc`, `1.2.3`, `1,5`, `1e3`, `--1`, `.` each showed "Invalid number" in errorLabel, no result, no exception (also `+1` in the core; `.5`, `5.`, `-` per LOG-12, agreed) | evidence/logs/t023-us2.txt (73 tests run, 0 failures) |  | Automated, headless. CalculatorTest.malformedOperandsAreInvalidInEitherPosition, incompleteDecimalFormsAreInvalid; CalculatorPanelTest.malformedOperandsShowInvalidNumber, incompleteDecimalFormsShowInvalidNumber |
| BASE-AC-13 | uncommitted | Pass | `7 / 0` and `7 / 0.0`: "Cannot divide by zero" | `7 / 0` and `7 / 0.0` showed "Cannot divide by zero", no result, no exception | evidence/logs/t023-us2.txt (73 tests, 0 failures); evidence/screenshots/05-error-divzero.png (`7 / 0`) |  | Automated, headless. Real window: `7 / 0` seen by the agent in 05-error-divzero.png; `7 / 0.0` reported by the owner (not seen by the agent). No pop-up appeared (owner) |
| BASE-AC-14 | uncommitted | Pass | Recover after an input error | `1,5` + `2` showed "Invalid number"; after changing `1,5` to `1.5`, Calculate showed `3.5` and no error | evidence/logs/t026-us3.txt (81 tests run, 0 failures) |  | Automated, headless. CalculatorPanelTest.recoversAfterAnInputError |
| BASE-AC-15 | uncommitted | Blocked | Builds and launches from a clean checkout | Not run. The documented build command was run in the working tree and succeeded (`mvn -B clean verify`, 81 tests), but a clean checkout is not possible without a commit | evidence/logs/t033-final-verify.txt |  | Blocked until the owner asks for an interim commit (AP-02); then T029 (clone to a new folder and follow quickstart.md unchanged) |
| BASE-AC-16 | uncommitted | Pass | Several calculations in sequence | Five calculations in a row each showed the result for the current fields | evidence/logs/t017-us1.txt (54 tests run, 0 failures) |  | Automated, headless. CalculatorPanelTest.severalCalculationsInARowEachUseTheCurrentFields |
| BASE-AC-17 | uncommitted | Pass | No operation selected: "Select an operation" | Two valid numbers with no operation selected showed "Select an operation", no result, no exception | evidence/logs/t023-us2.txt (73 tests run, 0 failures) |  | Automated, headless. CalculatorTest.noOperationSelected; CalculatorPanelTest.noOperationShowsSelectAnOperation |
| BASE-AC-18 | uncommitted | Pass | `0 / 5` = `0`; `1 / 3` = `0.3333333333` | `0 / 5` showed `0`; `1 / 3` showed `0.3333333333` | evidence/logs/t017-us1.txt (54 tests run, 0 failures) |  | Automated, headless. CalculatorTest.zeroDividedByFive, oneThirdIsRoundedToTenPlaces; CalculatorPanelTest.zeroDividedByFiveAndOneThird |
| BASE-AC-19 | uncommitted | Pass | Enter key gives the same result as Calculate | Enter in the first and in the second field gave the same result as Calculate | evidence/logs/t017-us1.txt (automated, Enter simulated with postActionEvent()); owner report for the real window |  | Automated test plus the owner reporting on 2026-10-27 that Enter works in both fields in the real window (not seen by the agent) |

## Traceability (task T032)

Maps each requirement to the automated tests or evidence that cover it. Status here follows the rows above; "Not run" means a part still needs the owner. Test classes are in `src/test/java/calculator/`.

| Requirement | Covered by | Status |
|---|---|---|
| `BASE-FR-01` launch as a Java desktop GUI, no network | `BASE-AC-01` (manual, needs the owner, T028); code search for network classes in `evidence/logs/t030-review.txt` (no matches) | Pass (manual check T028: screenshot 08 and the owner confirmation) |
| `BASE-FR-02` enter numeric values | `CalculatorPanelTest.integerAndDecimalTextIsKept` (text kept); display in the window needs the owner (T031) | Pass (screenshots 01 to 03) |
| `BASE-FR-03` select one of four operations | `CalculatorPanelTest.selectingAnOperationLeavesExactlyThatButtonSelected`, `operationButtonsShowTheirSymbols`; visible indication needs the owner (T031) | Pass (screenshots 02 and 05 show the selected button highlighted) |
| `BASE-FR-04` calculate by button and Enter, see result | `CalculatorTest` (all success cases); `CalculatorPanelTest.addition`, `subtraction`, `multiplication`, `division`, `negativeOperand`, `decimalOperands`, `zeroDividedByFiveAndOneThird`, `enterInTheFirstFieldCalculatesLikeTheButton`, `enterInTheSecondFieldCalculatesLikeTheButton` | Pass (automated) |
| `BASE-FR-05` clear/reset to initial state | `CalculatorPanelTest.clearAfterAResultReturnsToTheInitialState`, `clearAfterAnErrorReturnsToTheInitialState`, `startsInTheInitialState` | Pass (automated) |
| `BASE-FR-06` no crash or misleading result on invalid input, feedback given | `CalculatorTest.emptyOperandIsReportedWhicheverOperandIsEmpty`, `spacesOnlyOperandCountsAsEmpty`, `noOperationSelected`, `malformedOperandsAreInvalidInEitherPosition`, `incompleteDecimalFormsAreInvalid`, `errorPriorityEmptyThenNoOperationThenInvalidThenArithmetic`; `CalculatorPanelTest` error tests | Pass (automated) |
| `BASE-FR-07` division by zero safe and visible | `CalculatorTest.divisionByZero`, `zeroDividendIsNotAnError`; `CalculatorPanelTest.divisionByZeroShowsCannotDivideByZero` | Pass (automated) |
| `BASE-FR-08` correct input or clear, then continue | `CalculatorPanelTest.recoversAfterAnInputError`, `calculatorIsUsableAfterClear` | Pass (automated) |
| `BASE-NFR-01` run locally on the agreed runtime and OS | `evidence/01-environment.md`; `mvn -version` in `evidence/logs/t003-build-skeleton.txt` (Corretto 22.0.2, Windows 11); tests run on it | Pass (build and tests, and the window runs: screenshots) |
| `BASE-NFR-02` prompt calculation | Owner's observation only, no measurement. The owner reported on 2026-10-27 that calculations feel instant | Pass (owner observation; LOG-14 agreed 2026-10-27: observation only, no numeric limit) |
| `BASE-NFR-03` no crash on invalid input or arithmetic errors | `CalculatorTest.neverThrowsForAnyCombination` (20 x 20 x 5 input combinations, none throws); error tests above | Pass (automated) |
| `BASE-NFR-04` reviewable, extensible code and tests | `evidence/logs/t030-review.txt`: `calculator.core` imports nothing from `javax.swing`, `java.awt` or `calculator.ui`; tests mirror the packages | Pass (code search, review) |
| `BASE-NFR-05` documented, reproducible build and test commands | `quickstart.md`; build command and jar name verified in `evidence/logs/t027-quickstart-check.txt`; clean-checkout run is `BASE-AC-15` | Not run (clean checkout, T029) |

Foundational tests: `OperandParserTest` (input grammar, FR-012, `BASE-AC-02`, `-03`, `-12`) and `ResultFormatterTest` (result format, FR-011).

Tests that depend on a team decision carry `// LOG-nn (agreed)` (the comment read `(proposed)` until 2026-10-27): LOG-12 (`.5`, `5.`, `-`, spaces-only), LOG-13 (result and error stay after an edit), LOG-15 (negative tie rounding). LOG-14 (observation only for `BASE-NFR-02`) has no test.

## Real-window observations (screenshots, 2026-10-27)

Screenshots taken by the owner from the jar built by `mvn -B clean verify`; each was opened and viewed by the agent. They are manual evidence for the rows named; the automated results above are unchanged. Commit: uncommitted.

| Screenshot | What it shows | Supports |
|---|---|---|
| `evidence/screenshots/01-initial.png` | Window "Calculator": labelled "First number" and "Second number" fields (empty), "Operation" row with four buttons `+`, `−`, `×`, `÷` (none highlighted), "Calculate" and "Clear" buttons, "Result:" and "Error:" captions with nothing beside them | `BASE-AC-02` (empty start), initial state, FR-015 labels |
| `evidence/screenshots/02-add.png` | `7` and `5`, `+` button highlighted, Result `12`, Error empty | `BASE-AC-02`, `BASE-AC-04`, FR-003 visible selection |
| `evidence/screenshots/03-decimal.png` | `1.2` and `2.3`, `+` highlighted, Result `3.5`, Error empty | `BASE-AC-03`, `BASE-AC-09` |
| `evidence/screenshots/04-error-invalid.png` | `1,5` and `2`, `+` highlighted, Error "Invalid number", Result empty | `BASE-AC-12` (one of the six inputs) |
| `evidence/screenshots/05-error-divzero.png` | `7` and `0`, `÷` highlighted, Error "Cannot divide by zero", Result empty | `BASE-AC-13` (`7 / 0` only; `7 / 0.0` not shown) |
| `evidence/screenshots/06-error-no-operation.png` | `7` and `5`, no button highlighted, Error "Select an operation", Result empty | `BASE-AC-17` |
| `evidence/screenshots/07-after-clear.png` | Both fields empty, no button highlighted, Result and Error empty, text cursor in the first field | `BASE-AC-10` (the state after Clear; the state before it was not captured), DL-12 (focus, design choice) |
| `evidence/screenshots/08-offline.png` | The calculator open, and the taskbar tray at 2:19 PM 10/27/2026 with a globe icon where the Wi-Fi icon normally is | `BASE-AC-01`: window opens; the offline state is only suggested by the icon. See `BASE-AC-01` row (still Blocked) |

No error screenshot shows a dialog or pop-up. Not covered by any screenshot: Enter pressed in a real window, calculation speed, `7 / 0.0`, and the other malformed inputs in the real window.

## Owner-reported manual observations (2026-10-27)

Reported by the owner in the chat after using the real window. The agent did not see these; they are recorded as the owner's observations.

| Item | Owner's report | Supports |
|---|---|---|
| Enter pressed in a field | Works in both fields | `BASE-AC-19` |
| Pop-ups or dialogs | None ever appeared | FR-014, `BASE-AC-11`, `BASE-AC-12`, `BASE-AC-13`, `BASE-AC-17`, SC-003 |
| `7 / 0.0` | Shows "Cannot divide by zero" | `BASE-AC-13` |
| Speed of calculations | Feel instant | `BASE-NFR-02`, SC-006 (observation only, no numeric limit, LOG-14 agreed 2026-10-27) |
| Network | Turned off before launching | `BASE-AC-01` |