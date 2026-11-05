# Feature Specification: Scientific Operations (CR-01)

**Feature Branch**: none created (no git extension hook is configured; work is on the existing branch `george/spec-kit-cr01`)

**Created**: 2026-11-04

**Status**: Draft

**Input**: User description: "Extend the existing baseline calculator with change request CR-01 exactly as defined in shared/change-request-CR-01.md v0.2 (CR-FR-01 to CR-FR-11, input and result policy section 4, error handling section 5) and the CR-AC-01 to CR-AC-22 checks in shared/acceptance-criteria.md, together with the agreed decisions LOG-17 to LOG-21 in shared/experiment-log.md. Use only those documents, the baseline documents, and the existing baseline spec in specs/001-baseline-calculator/ (read-only, frozen) as sources. Do not add, change, reinterpret or extend any requirement. Carry the CR-FR and CR-AC IDs into the spec for traceability, and state explicitly which baseline statements (for example the four operation buttons, the empty-operand rule and the error priority) this change affects. If anything is unclear or leaves a gap, stop and ask the owner. Do not read the BMAD track."

Source IDs in parentheses (for example `CR-FR-04`, `CR-AC-06`, `BASE-AC-11`) point to `shared/change-request-CR-01.md`, `shared/acceptance-criteria.md` and `shared/baseline-requirements.md`. Those documents and the agreed decisions `LOG-17` to `LOG-21` in `shared/experiment-log.md` are the source of truth; this spec restates them for the Spec Kit workflow and does not extend them. The baseline spec `specs/001-baseline-calculator/spec.md` is frozen and is not edited; this spec builds on it.

## Effect on the baseline

CR-01 adds to the baseline; it removes or weakens nothing (`CR-FR-11`, CR-01 §7). The table lists the baseline statements that read differently once the three unary operations exist. The "Effect" column follows from the CR-01 text; it is not a new rule.

| Baseline statement | Effect of CR-01 |
|---|---|
| `BASE-FR-03` / baseline spec FR-003: four operation buttons (+, −, ×, ÷) | The four buttons remain and work as before. Three more selectable operations are added: square, square root, reciprocal (`CR-FR-01`). |
| `BASE-FR-02`: two labelled operand fields | Both fields remain. For the three new operations only the First number is used; the Second number is not required (`CR-FR-02`, CR-01 §4 items 2 to 4). |
| `BASE-AC-11` / baseline spec FR-013: "first operand, second operand, or both empty" is an error | Unchanged for the four basic operations. For a unary operation an empty Second number is not an error; only an empty First number is (`CR-AC-14`, `CR-AC-16`, LOG-18). |
| Baseline error priority (empty, no operation, invalid, arithmetic) | Same order, applied to the First number only for unary operations (LOG-18). |
| Baseline spec FR-014: the four error messages | One new message is added for a negative square-root input. Reciprocal of zero uses the existing "Cannot divide by zero" message (CR-01 §5). |
| `BASE-FR-05`: Clear returns to the initial state | Unchanged. The initial state still has no operation selected, including none of the three new ones (`CR-AC-20`). |
| Baseline spec Assumptions: "scientific operations (… roots …) out of scope"; constitution principle II "Baseline Scope Only" | Square, square root and reciprocal are in scope for CR-01. All other exclusions in CR-01 §2.2 stay out of scope. See the notes on the constitution in the checklist. |
| Result formatting, input grammar, `BASE-FR-04` (Calculate button and Enter), Clear, inline errors, no dialogs | Unchanged and reused (CR-01 §4 items 1, 8, 10; §5; `CR-FR-03`). |

## User Scenarios & Testing *(mandatory)*

### User Story 1 - Calculate square, square root and reciprocal of one number (Priority: P1)

A user opens the calculator and sees controls for square (`x²`), square root (`√x`) and reciprocal (`1/x`) next to the four basic operations. They type one number in First number, leave Second number empty, select one of the three controls and press Calculate (or Enter in a field). The result appears in the result area. A value in Second number does not change the result.

**Why this priority**: These three operations are the whole point of CR-01. Without them nothing else in this change has value.

**Independent Test**: Launch the application, enter `-3` in First number, leave Second number empty, select square, press Calculate, and read `9`.

**Acceptance Scenarios**:

1. **Given** the application is launched, **When** the user inspects the window, **Then** square, square-root and reciprocal controls are visible and identifiable. (`CR-AC-01`, `CR-FR-01`)
2. **Given** `-3` in First number and an empty Second number, **When** the user selects square and calculates, **Then** the result is `9`. (`CR-AC-02`)
3. **Given** `1.2`, square selected, **When** the user calculates, **Then** the result is `1.44`. (`CR-AC-03`)
4. **Given** `0`, square selected, **When** the user calculates, **Then** the result is `0`. (`CR-AC-04`)
5. **Given** `9`, square root selected, **When** the user calculates, **Then** the result is `3`. (`CR-AC-05`)
6. **Given** `2`, square root selected, **When** the user calculates, **Then** the result is `1.4142135624`. (`CR-AC-06`)
7. **Given** `0`, square root selected, **When** the user calculates, **Then** the result is `0`. (`CR-AC-07`)
8. **Given** `4`, reciprocal selected, **When** the user calculates, **Then** the result is `0.25`. (`CR-AC-09`)
9. **Given** `3`, reciprocal selected, **When** the user calculates, **Then** the result is `0.3333333333`. (`CR-AC-10`)
10. **Given** `-4`, reciprocal selected, **When** the user calculates, **Then** the result is `-0.25`. (`CR-AC-11`)
11. **Given** a valid First number, an empty Second number and a unary operation selected, **When** the user calculates, **Then** the calculation succeeds without a second operand. (`CR-AC-16`, `CR-FR-02`)
12. **Given** a valid First number and a different value in Second number, **When** the user calculates with a unary operation, **Then** the result depends only on First number. (`CR-AC-17`, LOG-17)
13. **Given** a unary operation selected and a valid First number, **When** the user presses Enter in an operand field instead of using Calculate, **Then** the result is the same as with the Calculate button. (`CR-FR-03`)

---

### User Story 2 - Clear feedback for impossible or invalid unary input (Priority: P2)

When the user asks for the square root of a negative number, the reciprocal of zero, or gives empty or malformed input, an inline error explains the problem. No successful-looking result is shown and the application keeps running.

**Why this priority**: The operations are only safe to use if mathematically impossible requests are handled visibly.

**Independent Test**: Select square root, enter `-1`, calculate, and read the specified error with no result shown.

**Acceptance Scenarios**:

1. **Given** `-1`, square root selected, **When** the user calculates, **Then** the inline error `Cannot take square root of a negative number` appears and no successful result is shown. (`CR-AC-08`, `CR-FR-06`)
2. **Given** `0`, reciprocal selected, **When** the user calculates, **Then** the baseline division-by-zero error (`Cannot divide by zero`) appears and no successful result is shown. (`CR-AC-12`, `CR-FR-08`)
3. **Given** reciprocal selected, **When** the user calculates with `0.0` and then with `-0.0`, **Then** each is treated as zero and rejected safely with the same error. (`CR-AC-13`)
4. **Given** each unary operation selected in turn and an empty First number, **When** the user calculates, **Then** the baseline empty-input feedback appears and the application does not crash. (`CR-AC-14`, `CR-FR-09`, LOG-21)
5. **Given** each unary operation selected in turn and malformed input such as `abc` in First number, **When** the user calculates, **Then** the baseline invalid-number feedback appears and the application does not crash. (`CR-AC-15`, `CR-FR-09`)

---

### User Story 3 - Recover from a unary error and reset (Priority: P3)

After a unary-operation error the user can fix the input and calculate again, or press Clear and start over.

**Why this priority**: Recovery keeps the application usable after the error cases in User Story 2.

**Independent Test**: Trigger the negative square-root error, change the input to a valid number, calculate, and read the correct result.

**Acceptance Scenarios**:

1. **Given** a negative square-root error is shown, **When** the user corrects the input and calculates again, **Then** the application recovers and shows the correct result. (`CR-AC-18`, `CR-FR-10`)
2. **Given** a reciprocal division-by-zero error is shown, **When** the user corrects the input and calculates again, **Then** the application recovers and shows the correct result. (`CR-AC-19`, `CR-FR-10`)
3. **Given** a unary calculation has been made, **When** the user activates Clear, **Then** the calculator returns to the baseline initial state: both fields empty, no operation selected, no result or error shown. (`CR-AC-20`)

---

### User Story 4 - Baseline still works, and the changed application still builds (Priority: P3)

All baseline behaviour still holds with the three new controls present, and the documented build and test instructions still work.

**Why this priority**: CR-01 must not remove or weaken any baseline requirement (`CR-FR-11`, CR-01 §7).

**Independent Test**: Run the documented build and test commands, then run `BASE-AC-01` to `BASE-AC-19` against the changed application and record each result separately.

**Acceptance Scenarios**:

1. **Given** the changed application, **When** `BASE-AC-01` to `BASE-AC-19` are executed, **Then** each baseline criterion keeps its required behaviour, and each result is recorded individually. (`CR-AC-21`, `CR-FR-11`)
2. **Given** a checkout of the changed application and the documented build and test instructions, **When** they are followed, **Then** the application builds and the tests run; prerequisites, test outcomes and failures are documented. (`CR-AC-22`)

---

### Edge Cases

Taken from the source documents:

- Second number empty: not an error for a unary operation (`CR-AC-16`). Second number holding a value: ignored (`CR-AC-17`). Second number holding malformed text such as `abc`: also ignored, with no error (LOG-17).
- Several faults at once for a unary operation: the error reported follows the priority empty First number, then no operation selected, then invalid First number, then the domain error (negative square root or zero reciprocal) (LOG-18).
- Zero reciprocal input in the forms `0`, `0.0` and `-0.0` is rejected; negative zero counts as zero (CR-01 §5).
- Square of any accepted input is exact, with no unnecessary rounding (CR-01 §4 item 5). The square of a negative number is positive (`CR-AC-02`).
- Square root and reciprocal results are rounded half-up to 10 decimal places. The reciprocal of a very large non-zero value, for example `1/30000000000`, shows `0`; this is the stated policy and not an error (LOG-19).
- Results are plain decimal text, trailing zeros removed, zero shown as `0`.
- A shown result or error stays until the next Calculate or Clear; the Second number field stays visible and enabled while a unary operation is selected (LOG-20, LOG-13).
- Empty First number uses the baseline wording ("Enter both numbers"), even though only one number is needed (LOG-21).
- Known gap, left unspecified by owner decision (2026-11-04): CR-01 treats negative zero as zero only "for reciprocal calculations" (§5). It does not say what the square root of `-0.0` (or `-0`) gives, and neither `CR-AC-07` (`0`) nor `CR-AC-08` (`-1`) covers it. This spec does not define that case, the plan will not decide it, and no test will assert it. It is not logged in `shared/experiment-log.md`.

## Requirements *(mandatory)*

### Functional Requirements

- **FR-001** (`CR-FR-01`): The application MUST expose identifiable GUI controls for square (`x²`), square root (`√x`) and reciprocal (`1/x`).
- **FR-002** (`CR-FR-02`; CR-01 §4 items 2 to 4; LOG-17): Each unary operation MUST use the value in First number. A second operand MUST NOT be required. Any content of Second number, including malformed text, MUST NOT affect the result or cause an error.
- **FR-003** (`CR-FR-03`): The user MUST be able to select a unary operation and run it with the existing Calculate button and with Enter pressed in an operand field.
- **FR-004** (`CR-FR-04`; CR-01 §4 item 5): Square MUST calculate the square of a valid input using exact decimal arithmetic, without unnecessary rounding.
- **FR-005** (`CR-FR-05`; CR-01 §4 items 6, 9): Square root MUST calculate the principal non-negative square root of a non-negative input, rounded to 10 decimal places half-up, by a deterministic calculation that produces the specified rounded result.
- **FR-006** (`CR-FR-06`; CR-01 §5): Square root of a negative input MUST be rejected with the inline error `Cannot take square root of a negative number`, and no successful result MUST be shown.
- **FR-007** (`CR-FR-07`; CR-01 §4 item 7): Reciprocal MUST calculate 1 divided by a non-zero input, rounded to 10 decimal places half-up, the same division policy as baseline division.
- **FR-008** (`CR-FR-08`; CR-01 §5): Reciprocal of zero MUST be rejected with the baseline inline division-by-zero error (`Cannot divide by zero`). Zero detection MUST include `0`, `0.0` and `-0.0`; negative zero is zero for reciprocal. No successful result MUST be shown.
- **FR-009** (`CR-FR-09`; CR-01 §5; LOG-18, LOG-21): Empty or malformed First number MUST be rejected without a crash and without a misleading result, using the baseline empty-input and invalid-number feedback. The error priority is: empty First number, then no operation selected, then invalid First number, then domain error.
- **FR-010** (`CR-FR-10`; CR-01 §5): After a unary-operation error the user MUST be able to correct the input or press Clear and continue.
- **FR-011** (`CR-FR-11`; CR-01 §7): All baseline requirements and acceptance criteria (`BASE-FR-*`, `BASE-NFR-*`, `BASE-AC-01` to `BASE-AC-19`) MUST remain applicable and MUST be re-run against the changed application, each result recorded individually (`CR-AC-21`).
- **FR-012** (CR-01 §4 items 1, 8, 10): The unary operations MUST use the baseline input grammar and numeric representation. Results MUST be plain decimal text, with trailing zeros removed and zero shown as `0`. CR-01 introduces no separate numeric-input format.
- **FR-013** (CR-01 §5; baseline §5): Errors MUST be shown inline in the dedicated error area, never in dialogs, and MUST follow the baseline error-presentation policy. An error MUST NOT be shown as a successful numeric result, and the application MUST NOT crash when an invalid or mathematically unsupported unary operation is requested.
- **FR-014** (LOG-20; LOG-13): The Second number field MUST stay visible and enabled while a unary operation is selected. A result or error that is shown MUST stay until the next Calculate or Clear.
- **FR-015** (`CR-AC-20`; `BASE-FR-05`): Clear MUST return the calculator to the baseline initial state after a unary calculation.

### Non-Functional Requirements

- **NFR-001** (`CR-AC-22`; `BASE-NFR-05`): The documented build and test commands MUST work for the changed application so that they can be reproduced, with prerequisites, outcomes and failures documented.
- **NFR-002** (`BASE-NFR-04`, CR-01 §1): Code and tests are organised so that the baseline stays reviewable alongside the change.

### Key Entities *(include if feature involves data)*

- **Unary operation**: One of square, square root, reciprocal. Needs only the First number.
- **Binary operation**: One of the four baseline operations (addition, subtraction, multiplication, division). Needs both numbers.
- **Operand**: Text entered in an operand field. Either empty, valid per the baseline grammar, or invalid. For a unary operation only the First number is examined.
- **Error state**: The baseline cases (empty operand, no operation selected, invalid number, division by zero) plus "negative square root input".

## Success Criteria *(mandatory)*

### Measurable Outcomes

- **SC-001**: All change checks `CR-AC-01` to `CR-AC-22` have a recorded result, and each Pass is based on observed behaviour.
- **SC-002**: The nine numeric cases in CR-01 §4.1 give exactly `9`, `1.44`, `0`, `3`, `1.4142135624`, `0`, `0.25`, `0.3333333333` and `-0.25`.
- **SC-003**: In every listed unary error case (square root of `-1`; reciprocal of `0`, `0.0` and `-0.0`; empty First number; `abc` in First number) the application shows an inline error, shows no result and keeps running: 0 crashes and 0 dialogs.
- **SC-004**: After a negative square-root error or a reciprocal division-by-zero error, a corrected calculation gives the correct result, and Clear returns the calculator to the baseline initial state.
- **SC-005**: Each of `BASE-AC-01` to `BASE-AC-19` is re-run against the changed application and has its own recorded result; any regression or unintended change is documented.
- **SC-006**: A reviewer can build, run and test the changed application using only the documented commands.

## Assumptions

- Source of requirements: `shared/change-request-CR-01.md` v0.2, the `CR-AC-01` to `CR-AC-22` rows copied into `shared/acceptance-criteria.md` (LOG-16), and the agreed decisions `LOG-17` to `LOG-21`. The baseline documents and the frozen baseline spec supply everything not changed by CR-01.
- Out of scope (CR-01 §2.2): trigonometric functions, logarithms and exponentials, arbitrary powers and factorials, graphing and history, memory buttons, variables and symbolic algebra, degree/radian settings, and unrequested redesigns. Other baseline exclusions (networking, persistence, accounts) also remain.
- The control labels `x²`, `√x` and `1/x` are given in CR-01 §2.1. Component names, layout and toolkit details are plan decisions, not requirements.
- The Java version, build tool, operating system and GUI toolkit stay as recorded for the baseline (`evidence/01-environment.md`).
- The error wording for the existing cases stays as in the baseline. The new wording `Cannot take square root of a negative number` is mandated by CR-01 §5.
- The status line of `shared/change-request-CR-01.md` still reads "Proposed — pending team approval" (LOG-16). The owner handles that; this spec treats the freeze checklist in CR-01 §9 and the agreed decisions as the released version.
