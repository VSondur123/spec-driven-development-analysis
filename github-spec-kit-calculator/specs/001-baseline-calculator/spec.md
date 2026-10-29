# Feature Specification: Baseline Calculator

**Feature Branch**: none created (no git extension hook is configured; no git actions taken)

**Created**: 2026-10-27

**Status**: Draft

**Input**: User description: "Build the baseline Java desktop GUI calculator exactly as defined in shared/baseline-requirements.md and shared/acceptance-criteria.md (baseline checks BASE-AC-01 to BASE-AC-19 only). Use only those two documents as the source of requirements. Do not add, change, reinterpret or extend any requirement. Carry the requirement IDs and acceptance check IDs into the spec for traceability. If anything in the two documents is unclear or leaves a gap, do not decide it: stop and ask the owner before proceeding."

Source IDs in parentheses (for example `BASE-FR-04`, `BASE-AC-07`) point to `shared/baseline-requirements.md` and `shared/acceptance-criteria.md`. Those documents are the source of truth; this spec restates them for the Spec Kit workflow and does not extend them.

## User Scenarios & Testing *(mandatory)*

### User Story 1 - Calculate with the four basic operations (Priority: P1)

A user opens the calculator, types two numbers into labelled fields, picks addition, subtraction, multiplication or division, requests the calculation and reads the result. Negative and decimal numbers work. The calculation can be requested with the Calculate button or with Enter pressed in an operand field.

**Why this priority**: This is the whole purpose of the baseline application. Without it nothing else has value.

**Independent Test**: Launch the application, enter `7` and `5`, select `+`, press Calculate, and read `12` in the result area.

**Acceptance Scenarios**:

1. **Given** the application is running, **When** the user enters a valid integer operand, **Then** the input is accepted and displayed clearly. (`BASE-AC-02`)
2. **Given** the application is running, **When** the user enters a valid decimal operand, **Then** the input is accepted and displayed clearly. (`BASE-AC-03`)
3. **Given** operands `7` and `5` with `+` selected, **When** the user calculates, **Then** the result is `12`. (`BASE-AC-04`)
4. **Given** operands `7` and `5` with `-` selected, **When** the user calculates, **Then** the result is `2`. (`BASE-AC-05`)
5. **Given** operands `7` and `5` with `×` selected, **When** the user calculates, **Then** the result is `35`. (`BASE-AC-06`)
6. **Given** operands `7` and `2` with `÷` selected, **When** the user calculates, **Then** the result is `3.5`. (`BASE-AC-07`)
7. **Given** operands `-7` and `2` with `+` selected, **When** the user calculates, **Then** the result is `-5`. (`BASE-AC-08`)
8. **Given** operands `1.2` and `2.3` with `+` selected, **When** the user calculates, **Then** the result is exactly `3.5`. (`BASE-AC-09`)
9. **Given** operands `0` and `5` with `÷` selected, **When** the user calculates, **Then** the result is `0`; **and given** `1` and `3` with `÷`, **Then** the result is `0.3333333333`. (`BASE-AC-18`)
10. **Given** two valid operands and an operation selected, **When** the user presses Enter in an operand field instead of using the Calculate button, **Then** the result is the same as with the Calculate button. (`BASE-AC-19`)
11. **Given** several calculations are completed in sequence without restarting, **When** each is requested, **Then** the application stays usable and each result corresponds to the current operands and operation. (`BASE-AC-16`)

---

### User Story 2 - Clear feedback for invalid input and division by zero (Priority: P2)

When the user requests a calculation with missing, malformed or arithmetically impossible input, the application tells them what is wrong, inline, and shows no result. It never crashes and never shows infinity, NaN or any other misleading value as a result.

**Why this priority**: The baseline explicitly requires safe, visible error handling, but it only matters once calculating works.

**Independent Test**: Request a calculation with an empty operand and confirm an inline error appears, no result is shown and the application keeps running.

**Acceptance Scenarios**:

1. **Given** the first operand, the second operand, or both are empty, **When** the user requests a calculation, **Then** a clear inline error is shown (suggested: "Enter both numbers"), no result is shown and the application does not crash. (`BASE-AC-11`)
2. **Given** an operand is one of `abc`, `1.2.3`, `1,5`, `1e3`, `--1`, `.`, **When** the user requests a calculation, **Then** the input is rejected with understandable inline feedback (suggested: "Invalid number") and the application does not crash. (`BASE-AC-12`)
3. **Given** `7` and `0` with `÷` selected, **When** the user calculates, **Then** an inline error is shown (suggested: "Cannot divide by zero"), and infinity or NaN is not shown as an ordinary result. The same holds for `7` and `0.0`. (`BASE-AC-13`)
4. **Given** two valid operands and no operation selected, **When** the user requests a calculation, **Then** a clear inline error is shown (suggested: "Select an operation"), no result is shown and the application does not crash. (`BASE-AC-17`)

---

### User Story 3 - Recover from errors and reset (Priority: P3)

After an error the user can correct the input and calculate again, or press Clear to return the calculator to its initial state.

**Why this priority**: Recovery builds on the error handling of Story 2.

**Independent Test**: Trigger an "Invalid number" error, correct the operand, calculate, and read the correct result; then press Clear and confirm everything is reset.

**Acceptance Scenarios**:

1. **Given** an input error has been shown, **When** the user corrects the input and calculates again, **Then** the correct result is obtained. (`BASE-AC-14`)
2. **Given** operands entered, an operation selected and a result or error shown, **When** the user activates Clear, **Then** both operands are empty, no operation is selected, and no result or error is shown. (`BASE-AC-10`)

---

### User Story 4 - Launch offline and reproduce the build (Priority: P3)

A reviewer can build, run and test the application from a clean checkout using documented commands, and the application starts without any network connection.

**Why this priority**: Required for reproducible evaluation, but it does not add calculator behaviour.

**Independent Test**: Follow the documented instructions on a clean checkout with networking disabled and observe the GUI open.

**Acceptance Scenarios**:

1. **Given** the documented launch command and networking disabled, **When** the application is launched, **Then** the GUI opens successfully. This is a manual check; a screenshot or short note is kept as evidence. (`BASE-AC-01`)
2. **Given** a clean checkout and the documented build and run instructions, **When** they are followed, **Then** the application builds and launches, or the environment prerequisites are clearly documented. (`BASE-AC-15`)

---

### Edge Cases

Taken from the two source documents:

- Both operands empty, or one empty: reported as an empty-operand error, whichever operand is empty (`BASE-AC-11`).
- Several faults at once: the error reported follows the priority empty operand, then no operation selected, then invalid number, then arithmetic error such as division by zero (baseline §4 item 10). For example, an empty operand with no operation selected reports "Enter both numbers"; an invalid operand with `÷` and a zero second operand reports the invalid number, not the division error.
- Surrounding spaces around an operand are ignored.
- Rejected input includes `+1`, `1e3`, `1,5`, `.`, `1.2.3`, `--1` and `abc`. By owner decision (LOG-12, agreed) it also includes `.5`, `5.` and `-` alone; a spaces-only field is treated as empty.
- A result or error stays shown after the user edits an operand or the operation, until the next Calculate or Clear (LOG-13, agreed).
- Division that does not terminate (for example `1 ÷ 3`) is rounded half-up to 10 decimal places (`0.3333333333`). A division that gives a whole number (for example `2 ÷ 2`) shows `1`.
- A zero result shows as `0`; results never show trailing zeros or exponent notation.
- After an error, the application remains fully usable (`BASE-FR-08`).

## Requirements *(mandatory)*

### Functional Requirements

- **FR-001** (`BASE-FR-01`): The application MUST launch as a Java desktop GUI without requiring a network connection.
- **FR-002** (`BASE-FR-02`): Users MUST be able to enter numeric values using two labelled operand text fields.
- **FR-003** (`BASE-FR-03`): Users MUST be able to select addition, subtraction, multiplication or division using four operation buttons (+, −, ×, ÷). The selected operation MUST be visibly indicated.
- **FR-004** (`BASE-FR-04`): Users MUST be able to request a calculation with a Calculate button and with the Enter key pressed in an operand field, and MUST see the resulting value in a result area of the GUI.
- **FR-005** (`BASE-FR-05`): The application MUST provide a Clear action that returns the calculator to its initial state: both operands empty, no operation selected, and no result or error shown.
- **FR-006** (`BASE-FR-06`): The application MUST NOT crash or silently display a misleading numeric result when input is invalid, and MUST give understandable feedback. This includes a calculation requested when no operation has been selected.
- **FR-007** (`BASE-FR-07`): The application MUST handle division by zero safely and visibly, and MUST NOT present infinity or NaN as an ordinary successful result.
- **FR-008** (`BASE-FR-08`): After an error, users MUST be able to correct the input or clear the calculator and continue using it.
- **FR-009** (baseline §4 items 1, 3, 5, 8): The application MUST support finite decimal numeric input, including negative and decimal values, and MUST use exact decimal arithmetic so that avoidable floating-point display artefacts do not occur.
- **FR-010** (baseline §4 item 8): Division results MUST be rounded half-up to 10 decimal places.
- **FR-011** (baseline §4 item 8): Results MUST be displayed as plain decimal text with no trailing zeros, no exponent notation, and zero shown as `0`.
- **FR-012** (baseline §4 items 6, 9): An operand MUST be accepted if it is an optional leading `-`, digits, and optionally one `.` followed by digits, ignoring surrounding spaces. Everything else MUST be rejected as invalid input, and empty, malformed or incomplete input MUST NOT be treated as a successful calculation. Owner decision 2026-10-27 (agreed by the team as LOG-12 on 2026-10-27): the grammar is applied literally, so `.5`, `5.` and `-` alone are rejected as "Invalid number", and a field containing only spaces counts as empty ("Enter both numbers").
- **FR-013** (baseline §4 item 10): When several faults exist, the application MUST report them in this priority: empty operand, then no operation selected, then invalid number, then arithmetic error such as division by zero.
- **FR-014** (baseline §5; `BASE-AC-11`, `BASE-AC-12`, `BASE-AC-13`, `BASE-AC-17`): Errors MUST be shown inline in a dedicated error area and never in dialogs. Each error case MUST be clearly distinguishable. Suggested messages: "Enter both numbers", "Select an operation", "Invalid number", "Cannot divide by zero".
- **FR-015** (baseline §5): Controls and output MUST have clear labels or otherwise recognisable meanings, and the current input, operation, result and error state MUST be identifiable. The interface MUST be a desktop GUI, kept simple, with no unrequested styling.
- **FR-016** (`BASE-FR-08`, `BASE-AC-14`): A result or error that is already shown MUST stay visible until the next Calculate or Clear; editing an operand or changing the operation does not remove it. Owner decision 2026-10-27 (agreed by the team as LOG-13 on 2026-10-27).

### Non-Functional Requirements

- **NFR-001** (`BASE-NFR-01`): The application MUST run locally on the agreed Java runtime and operating system used for evaluation.
- **NFR-002** (`BASE-NFR-02`): Basic calculations MUST complete promptly enough for interactive use. No numeric limit is set; this is judged by observation. Owner decision 2026-10-27 (agreed by the team as LOG-14 on 2026-10-27).
- **NFR-003** (`BASE-NFR-03`): Invalid inputs and arithmetic errors MUST be handled without an application crash.
- **NFR-004** (`BASE-NFR-04`): Code and tests MUST be organised so the baseline can be reviewed and extended during the later change request.
- **NFR-005** (`BASE-NFR-05`): Build and test commands MUST be documented so they can be reproduced.

### Key Entities *(include if feature involves data)*

- **Operand**: Text entered in one of the two operand fields. Either empty, valid per FR-012, or invalid.
- **Operation**: One of addition, subtraction, multiplication, division, or none selected (the initial state).
- **Result**: The calculated value in plain decimal text, or absent when an error is shown.
- **Error state**: One of "empty operand", "no operation selected", "invalid number", "division by zero", or none.

## Success Criteria *(mandatory)*

### Measurable Outcomes

- **SC-001**: All baseline checks `BASE-AC-01` to `BASE-AC-19` have a recorded result, and each Pass is based on observed behaviour.
- **SC-002**: The eight calculation cases `7 + 5`, `7 - 5`, `7 × 5`, `7 ÷ 2`, `-7 + 2`, `1.2 + 2.3`, `0 ÷ 5` and `1 ÷ 3` give exactly `12`, `2`, `35`, `3.5`, `-5`, `3.5`, `0` and `0.3333333333`.
- **SC-003**: In every listed error case (empty operand, no operation selected, `abc`, `1.2.3`, `1,5`, `1e3`, `--1`, `.`, `7 ÷ 0`, `7 ÷ 0.0`) the application shows an inline error, shows no result and keeps running: 0 crashes and 0 dialogs.
- **SC-004**: After any error case, a corrected calculation gives the correct result, and Clear returns the calculator to the defined initial state.
- **SC-005**: A reviewer can build, run and test the application from a clean checkout using only the documented commands.
- **SC-006**: In normal interactive use, each calculation shows its result without a perceptible wait (observed, no numeric limit; `BASE-NFR-02`).

## Assumptions

- Out of scope (baseline §2): scientific operations (trigonometry, logarithms, roots, exponentiation), graphing, history, memory buttons, variables, unit conversion, symbolic algebra, user accounts, networking, persistence, databases, external services, unrequested styling, and any feature not stated in the two source documents. The change request is not used.
- Java is a requirement of the source documents (`BASE-FR-01`, baseline §7), not a design choice. The GUI toolkit, Java version, build tool, operating system and dependency versions are recorded in the plan and in `evidence/01-environment.md`.
- The evaluation environment for this track is the one recorded in `shared/experiment-log.md` (LOG-07) and `evidence/01-environment.md`.
- The error messages are suggestions in the source documents. Any wording is acceptable as long as each error case is clearly distinguishable.
- `BASE-AC-01` is a manual check; all other checks are expected to be repeatable.
