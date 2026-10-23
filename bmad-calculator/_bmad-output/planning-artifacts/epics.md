---
status: final
stepsCompleted: [1, 2, 3, 4]
inputDocuments:
  - _bmad-output/planning-artifacts/prds/prd-bmad-calculator-2026-10-23/prd.md
  - _bmad-output/planning-artifacts/prds/prd-bmad-calculator-2026-10-23/addendum.md
  - _bmad-output/planning-artifacts/architecture/architecture-bmad-calculator-2026-10-23/ARCHITECTURE-SPINE.md
  - ../shared/baseline-requirements.md
  - ../shared/acceptance-criteria.md
---

# bmad-calculator - Epic Breakdown

## Overview

Decomposes the PRD (FR-1…FR-10, NFR-1…NFR-5) and Architecture spine (AD-1…AD-10) of the baseline desktop calculator into epics and stories. Each story cites the shared `BASE-FR/NFR/AC` IDs it satisfies. No scientific features are included; CR-01 has not been consulted.

## Requirements Inventory

### Functional Requirements

- FR-1 Enter operands (BASE-FR-02)
- FR-2 Launch offline (BASE-FR-01)
- FR-3 Select an operation (BASE-FR-03)
- FR-4 Calculate via button or Enter (BASE-FR-04)
- FR-5 Consecutive calculations (BASE-AC-16)
- FR-6 Clear (BASE-FR-05)
- FR-7 Invalid and incomplete input, incl. no operation selected (BASE-FR-06, D-07)
- FR-8 Display Policy (BASE-FR-07 numeric aspects)
- FR-9 Division by zero (BASE-FR-07)
- FR-10 Error recovery (BASE-FR-08)

### NonFunctional Requirements

- NFR-1 Runs locally on agreed Java/OS (BASE-NFR-01)
- NFR-2 Interactive responsiveness (BASE-NFR-02)
- NFR-3 No crash on invalid input/arithmetic errors (BASE-NFR-03)
- NFR-4 Reviewable, extensible code/test organisation (BASE-NFR-04)
- NFR-5 Documented, reproducible build/test commands (BASE-NFR-05)

### Additional Requirements

From the Architecture spine: AD-1 dependency direction; AD-2 total core API; AD-3 BigDecimal only; AD-4 single formatter and message source; AD-5 validation order/grammar; AD-6 presenter-owned immutable ViewState; AD-7 Operation enum extension point; AD-8 EDT threading; AD-9 named controls and Enter key; AD-10 Maven layout, headless-safe tests, pinned `JAVA_HOME`. No starter template is specified; Story 1.1 creates the Maven project from scratch.

### UX Design Requirements

No UX document (step skipped; layout fixed by PRD D-01 and AD-9).

### FR Coverage Map

| Requirement | Stories |
| --- | --- |
| FR-1 | 1.3 |
| FR-2 | 1.1 |
| FR-3 | 1.3 |
| FR-4 | 1.2, 1.3 |
| FR-5 | 1.3 |
| FR-6 | 3.1 |
| FR-7 | 2.1 |
| FR-8 | 1.2 |
| FR-9 | 2.2 |
| FR-10 | 2.3 |
| NFR-1 | 1.1 |
| NFR-2 | 1.3 |
| NFR-3 | 2.1, 2.2 |
| NFR-4 | 3.2 |
| NFR-5 | 3.2 |

## Epic List

1. **Epic 1: Basic Calculations** — a user can open the calculator, enter two numbers, choose an operation and see a correct result.
2. **Epic 2: Safe Input Handling** — invalid, incomplete and divide-by-zero cases give clear errors, and the user can recover.
3. **Epic 3: Reset, Reproducibility and Verification** — the user can clear the calculator, and the team can build, run and verify the whole baseline from a clean checkout.

## Epic 1: Basic Calculations

A user can launch the calculator offline, enter operands, select an operation and get a correct, cleanly displayed result.

### Story 1.1: Project scaffold and launchable window

As a user,
I want to launch the calculator as a desktop window with the documented command,
So that I can start using it without a network.

**Acceptance Criteria:**

**Given** a clean checkout and JDK 22 with `JAVA_HOME` set
**When** I run the documented run command (`mvn` with pinned `JAVA_HOME`)
**Then** a window titled "Calculator" opens without network access (BASE-AC-01, FR-2)
**And** the Maven project uses `sourceDirectory=src`, `testSourceDirectory=tests`, Java release 22, JUnit Jupiter 6.1.3 (AD-10)
**And** the window is created on the Event Dispatch Thread (AD-8).

**Given** the headless test environment
**When** `mvn test` runs
**Then** the build succeeds and GUI tests are skipped via `assumeFalse(isHeadless())`.

### Story 1.2: Core calculation engine with display policy

As a user,
I want addition, subtraction, multiplication and division of integer and decimal values to be exact,
So that I can trust the displayed result.

**Acceptance Criteria:**

**Given** `Calculator.evaluate(a, op, b)` with valid operands
**When** I evaluate `7 + 5`, `7 - 5`, `7 * 5`, `7 / 2`
**Then** the outcomes are `Success("12")`, `Success("2")`, `Success("35")`, `Success("3.5")` (BASE-AC-04…07, FR-4)

**Given** `-7 + 2` and `1.2 + 2.3`
**When** evaluated
**Then** the outcomes are `Success("-5")` and `Success("3.5")` with no float artefacts (BASE-AC-08, 09, FR-8)

**Given** `1 / 3`, `2 / 2`, `0 / 5`, `0 * -1`
**When** evaluated
**Then** the outcomes are `0.3333333333`, `1`, `0`, `0` (Display Policy: scale 10 HALF_UP, no trailing zeros, no `-0`/exponent; BASE-AC-18)

**And** `core` uses only `BigDecimal` (AD-3), `ResultFormatter` is the only formatter (AD-4), and `Operation` is an enum with a binary function (AD-7).

### Story 1.3: Enter operands, select operation and calculate in the GUI

As a user,
I want to type two numbers, pick an operation and press Calculate or Enter,
So that I see the result in the window.

**Acceptance Criteria:**

**Given** the window is open
**When** I type `7` and `-7` in turn into an operand field
**Then** the text is accepted and shown (BASE-AC-02, FR-1)
**And** typing `1.2` is accepted and shown (BASE-AC-03).

**Given** operands `7` and `5`
**When** I click `+` then Calculate (or press Enter in either operand field)
**Then** the result area shows `12` (BASE-AC-04, FR-4)
**And** the selected operation is visibly indicated and selecting another replaces it (FR-3).

**Given** operands `7` and `5` and `+` selected
**When** I press Enter in either operand field
**Then** the result is `12`, the same as using the Calculate button (BASE-AC-19, D-02).

**Given** I have calculated `7 + 5`
**When** I change to `*` and Calculate again
**Then** the result is `35`, not influenced by the previous result (BASE-AC-16, FR-5).

**Given** any valid calculation
**When** I Calculate
**Then** the result appears with no perceptible delay (under 100 ms on the evaluation machine, NFR-2)

**And** every control has a label and a stable `setName` (AD-9), the presenter owns state via immutable `ViewState` (AD-6), and the view contains no calculation logic (AD-1).

## Epic 2: Safe Input Handling

Invalid, incomplete or impossible input never crashes the app or produces a misleading result, and the user can recover.

### Story 2.1: Reject empty, operation-less and malformed input

As a user,
I want clear feedback when my input is incomplete or invalid,
So that I never mistake an error for a result.

**Acceptance Criteria:**

**Given** operand A or B is empty or blank
**When** I Calculate
**Then** the error "Enter both numbers" appears inline and no result is shown (BASE-AC-11, FR-7)

**Given** both operands are valid and no operation is selected
**When** I Calculate
**Then** the error "Select an operation" appears and no result is shown (D-07, BASE-AC-17)

**Given** operand `abc`, `1.2.3`, `1,5`, `1e3`, `--1` or `.`
**When** I Calculate with an operation selected
**Then** the error "Invalid number" appears and no result is shown (BASE-AC-12)

**And** with several faults the order is empty, then no operation, then invalid (AD-5), the app does not throw or exit (NFR-3, AD-2), and errors are inline, never dialogs (AD-9).

### Story 2.2: Safe division by zero

As a user,
I want division by zero to be reported as an error,
So that I never see Infinity or NaN as a result.

**Acceptance Criteria:**

**Given** `7 / 0` and `7 / 0.0`
**When** I Calculate
**Then** "Cannot divide by zero" appears, no result is shown, and the app does not crash (BASE-AC-13, FR-9)

**Given** `0 / 5`
**When** I Calculate
**Then** the result is `0`.

### Story 2.3: Recover after an error

As a user,
I want to correct my input and calculate again after an error,
So that I can continue without restarting.

**Acceptance Criteria:**

**Given** an error is displayed (any of the four messages)
**When** I correct the operands (or select an operation) and Calculate
**Then** the correct result appears and the error is cleared (BASE-AC-14, FR-10)

**Given** a result is displayed
**When** a following Calculate fails
**Then** the result area is empty and only the error is shown.

## Epic 3: Reset, Reproducibility and Verification

The user can reset the calculator, and the team can build, run and verify the baseline reproducibly.

### Story 3.1: Clear

As a user,
I want a Clear action,
So that the calculator returns to its initial state.

**Acceptance Criteria:**

**Given** operands, a selected operation, a result and/or an error are present
**When** I click Clear
**Then** both operand fields are empty, no operation is selected, and the result and error areas are empty (BASE-AC-10, FR-6)

**And** I can then enter new values and calculate normally.

### Story 3.2: Documented, reproducible build and architecture rules

As a team member,
I want documented build, run and test commands and automated architecture checks,
So that the baseline is reproducible and reviewable before the change phase.

**Acceptance Criteria:**

**Given** a clean checkout
**When** I follow the build/run/test instructions in `bmad-calculator/README` (or a documented build doc), with `JAVA_HOME` pinned to Corretto 22
**Then** the project builds, tests pass and the app launches, with prerequisites documented (BASE-AC-15, NFR-5)

**Given** the source tree
**When** `ArchitectureRulesTest` runs
**Then** it fails if `core` or `presenter` import `java.awt`/`javax.swing`, or if a lower layer imports a higher one (AD-1, NFR-4).

### Story 3.3: Acceptance verification and evidence

As a team member,
I want every `BASE-AC` check executed and recorded,
So that the baseline can be frozen with evidence.

**Acceptance Criteria:**

**Given** the finished application
**When** I run all automated tests and the manual GUI checklist
**Then** each of BASE-AC-01…16 is recorded as Pass / Fail / Blocked / Not run with commit hash, environment, command and evidence location in `evidence/` (SM-1)

**And** a requirement-to-story-to-test traceability table (FR/NFR → story → test method) is saved in `evidence/` (SM-2)
**And** unresolved defects and untested criteria (e.g. BASE-AC-01 offline, if only manual) are listed explicitly.
