# Our Baseline Requirements — Java GUI Calculator

**Status:** Draft for our team review; we will freeze it before either implementation begins.
**Applies to:** Both our BMAD and GitHub Spec Kit tracks.
**Source of truth:** This document defines our baseline product scope. We will implement the same externally observable behaviour in both tracks.

## 1. Our purpose

We will build a small Java desktop GUI calculator that performs basic arithmetic through a graphical interface. This is our baseline application. We will introduce scientific-calculator functionality only later through our separately frozen change request.

## 2. Our scope

### In scope
- A Java desktop application with a graphical user interface.
- Numeric input and a visible way to select basic arithmetic operations.
- Basic addition, subtraction, multiplication, and division.
- A visible result.
- Clear/reset behaviour.
- Defined handling for invalid input and division by zero.
- Tests for our shared acceptance criteria.

### Out of scope
- Scientific operations such as trigonometry, logarithms, roots, or exponentiation.
- Graphing, history, memory buttons, variables, unit conversion, or symbolic algebra.
- User accounts, networking, persistence, databases, and external services.
- Unrequested styling or features that change the interaction model.
- Features not stated here or in our shared acceptance criteria.

## 3. Our functional requirements

| ID | Requirement |
|---|---|
| `BASE-FR-01` | Our application shall launch as a Java desktop GUI without requiring a network connection. |
| `BASE-FR-02` | We shall be able to enter numeric values using the GUI. |
| `BASE-FR-03` | We shall be able to select addition, subtraction, multiplication, or division. |
| `BASE-FR-04` | We shall be able to request a calculation and see the resulting value in the GUI. |
| `BASE-FR-05` | We shall provide a clear/reset action that returns the calculator to a usable initial state. |
| `BASE-FR-06` | Our application shall not crash or silently display a misleading numeric result when input is invalid. It shall provide understandable feedback. |
| `BASE-FR-07` | Our application shall handle division by zero safely and visibly; it shall not crash or present infinity/NaN as an ordinary successful result. |
| `BASE-FR-08` | After an error, we shall be able to correct the input or clear the calculator and continue using it. |

## 4. Our input and numeric behaviour

1. We will support finite decimal numeric input.
2. Our GUI will make the active operands and selected operation understandable.
3. We will use a consistent numeric representation and rounding/display policy.
4. We will agree on precise display precision and rounding in `shared/acceptance-criteria.md` before freezing our documents.
5. Unless our acceptance criteria specify otherwise, we will use a suitable numeric representation and avoid avoidable floating-point display artefacts.
6. We will not treat empty, malformed, or incomplete input as a successful calculation.
7. We will cover negative values and decimal values in our acceptance criteria.

## 5. Our user interface requirements

- We will provide a desktop GUI rather than a command-line-only interface.
- We will give controls and output clear labels or otherwise recognisable meanings.
- We will make the current input, operation, result, and error state identifiable.
- We will provide an obvious clear/reset action.
- We will keep the baseline interface simple; visual polish will not be a comparison objective unless it affects usability or our acceptance criteria.
- We will use the same required interaction behaviour in both tracks while allowing implementation-level design choices that do not change our requirements.

## 6. Our non-functional requirements

| ID | Requirement |
|---|---|
| `BASE-NFR-01` | We will run the application locally on the agreed Java runtime and operating system used for evaluation. |
| `BASE-NFR-02` | Our basic calculations will complete promptly enough for interactive use. |
| `BASE-NFR-03` | We will handle invalid inputs and arithmetic errors without an application crash. |
| `BASE-NFR-04` | We will organise code and tests so we can review and extend the baseline during the later change request. |
| `BASE-NFR-05` | We will document build and test commands so we can reproduce them. |

## 7. Our constraints

- We will use Java.
- We will build a desktop GUI application.
- We will record the Java version, build tooling, GUI toolkit, operating system, and relevant dependencies for each track.
- We may allow implementation-level differences between tracks, but both must meet the same observable requirements and acceptance criteria.
- We will not add scientific-calculator behaviour before the change phase.

## 8. Our traceability expectations

Where each native workflow supports it, we will map every requirement ID to:
- A user story/specification item or equivalent
- Acceptance criteria
- Architecture/HLD decisions
- Detailed design/LLD elements, where appropriate
- Implementation tasks and relevant code
- Tests and test outcomes

We will not force identical artefact names across BMAD and Spec Kit. We will preserve each tool's native workflow while keeping traceability reviewable.

## 9. Decisions we must resolve before freezing

- Our exact GUI interaction pattern and how we enter operands and operations.
- Our display precision and rounding policy.
- Whether we trigger calculation using an equals button, Enter key, or both.
- Our exact invalid/incomplete input messages and whether they appear inline or in a dialog.
- Our agreed Java version, build commands, and evaluation environment.

We will record our decisions in the experiment log and reflect testable behaviour in `shared/acceptance-criteria.md`. Once we freeze this document, we will not change it for only one track.
