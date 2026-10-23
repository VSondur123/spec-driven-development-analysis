# Our Shared Acceptance Criteria — Baseline and Change

**Status:** Baseline checks frozen on 2026-10-23 (owner decision, see `shared/experiment-log.md`, Section D). The CR-01 section is not part of this freeze.
**Purpose:** We will use these observable, repeatable pass/fail checks for both tracks. We will apply the same cases and expected outcomes to our BMAD and Spec Kit implementations.

> Important: This file currently covers our baseline acceptance checks. We will complete the CR-01 section only after we finalise the scientific operations and exact expected behaviour in `shared/change-request-CR-01.md`. We will freeze the completed version before implementation.

## 1. Our test execution rules

- We will run the same cases against both applications.
- We will record the operating system, Java version, build/run command, commit hash, and test date.
- We will record each result as **Pass**, **Fail**, **Blocked**, or **Not run**.
- For every failure, we will include reproduction steps, expected result, actual result, and evidence (test log or screenshot where useful).
- We will not count a test as passed unless we actually observe the behaviour.
- We will agree on numeric precision and rounding before we freeze this file.

## 2. Our baseline acceptance criteria

| Test ID | Requirement | Our test procedure | Expected result |
|---|---|---|---|
| `BASE-AC-01` | `BASE-FR-01` | We launch the application using the documented command. | The GUI opens successfully without network access. This is a manual check: we disable networking, then record a screenshot or short note as evidence. |
| `BASE-AC-02` | `BASE-FR-02` | We enter a valid integer operand using the GUI. | The input is accepted and displayed clearly. |
| `BASE-AC-03` | `BASE-FR-02` | We enter a valid decimal operand. | The decimal input is accepted and displayed clearly. |
| `BASE-AC-04` | `BASE-FR-03`, `BASE-FR-04` | We calculate `7 + 5`. | The result is `12`. |
| `BASE-AC-05` | `BASE-FR-03`, `BASE-FR-04` | We calculate `7 - 5`. | The result is `2`. |
| `BASE-AC-06` | `BASE-FR-03`, `BASE-FR-04` | We calculate `7 * 5`. | The result is `35`. |
| `BASE-AC-07` | `BASE-FR-03`, `BASE-FR-04`, `BASE-FR-07` | We calculate `7 / 2`. | The result is `3.5`, subject to our agreed display policy. Policy: exact decimals, division rounded half-up to 10 decimal places, no trailing zeros. |
| `BASE-AC-08` | Numeric behaviour | We calculate a negative-value case, such as `-7 + 2`. | The result is `-5`. |
| `BASE-AC-09` | Numeric behaviour | We calculate a decimal case, such as `1.2 + 2.3`. | The result matches our agreed numeric/display policy, without avoidable artefacts. `1.2 + 2.3` shows exactly `3.5`. |
| `BASE-AC-10` | `BASE-FR-05` | We enter values, then activate clear/reset. | Inputs, operation, result, and error state reset to the defined initial state. |
| `BASE-AC-11` | `BASE-FR-06` | We attempt to calculate with an empty operand. | We see a clear error/validation state; the app does not crash or report a normal result. Suggested message: "Enter both numbers". Applies to an empty first operand, an empty second operand, and both. |
| `BASE-AC-12` | `BASE-FR-06` | We enter malformed input if the UI permits text entry, then calculate. | The app rejects the input with understandable feedback and does not crash. Test at least `abc`, `1.2.3`, `1,5`, `1e3`, `--1`, `.`; suggested message: "Invalid number". |
| `BASE-AC-13` | `BASE-FR-07` | We calculate `7 / 0`. | We see an error; the app does not crash or display infinity/NaN as an ordinary successful result. Suggested message: "Cannot divide by zero". Also test `7 / 0.0`. |
| `BASE-AC-14` | `BASE-FR-08` | We trigger an input error, correct the input, and calculate again. | We can recover and obtain a correct result. |
| `BASE-AC-15` | `BASE-NFR-05` | We follow the documented build and run instructions from a clean checkout. | The application builds and launches, or we clearly document environment prerequisites. |
| `BASE-AC-16` | Regression/usability | We complete several calculations in sequence without restarting the app. | The app remains usable and each result corresponds to the current operands and operation. |
| `BASE-AC-17` | `BASE-FR-06` | We enter two valid numbers, select no operation, and request the calculation. | We see a clear error (suggested: "Select an operation"); no result is shown; the app does not crash. |
| `BASE-AC-18` | `BASE-FR-04`, `BASE-FR-07` | We calculate `0 / 5`, then `1 / 3`. | `0 / 5` gives `0`; `1 / 3` gives `0.3333333333`. |
| `BASE-AC-19` | `BASE-FR-04` | We enter two valid numbers, select an operation, and press Enter in an operand field instead of using the Calculate button. | The result is the same as using the Calculate button. |

## 3. Our change acceptance criteria — CR-01

**Pending our final decision.** We will not treat this section as complete until we agree on the exact scientific operations, input domain, result precision, domain-error behaviour, and UI interaction.

When we finalise CR-01, we will add uniquely identified checks for:
- Each newly supported operation and representative valid inputs
- Boundary values and invalid/domain inputs for each operation
- Correct result display under our agreed precision/rounding policy
- Invalid or incomplete scientific-operation input
- Error recovery and clear/reset behaviour
- Discoverability of the interaction and correct operation selection
- Regression of all `BASE-AC-*` checks
- Any specified compatibility or build requirements

We will make every change-specific criterion observable and give it a clear expected outcome. We will avoid subjective wording such as “works well” unless we operationalise it.

## 4. Our test result template

We will use one row per test per track.

| Field | Value |
|---|---|
| Test ID | |
| Track | Varun/BMAD or George/Spec Kit |
| Phase | Baseline or CR-01 |
| Commit hash | |
| Environment / Java version | |
| Status | Pass / Fail / Blocked / Not run |
| Expected result | |
| Actual result | |
| Evidence location | |
| Defect / issue ID | |
| Notes | |

## 5. Our freeze checklist

Before we start implementation:
- [ ] We will resolve all open decisions in `shared/baseline-requirements.md`.
- [ ] We will agree on numeric precision and rounding.
- [ ] We will confirm our GUI interaction expectations.
- [ ] We will confirm invalid-input and division-by-zero expectations.
- [ ] We will check that each criterion is observable and repeatable.
- [ ] We will freeze/version this file and our baseline requirements.
- [ ] We will finalise and freeze CR-01 acceptance checks before the change phase.
