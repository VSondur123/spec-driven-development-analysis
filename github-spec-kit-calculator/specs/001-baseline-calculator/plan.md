# Implementation Plan: Baseline Calculator

**Branch**: none created (see spec) | **Date**: 2026-10-27 | **Spec**: [spec.md](spec.md)

**Input**: Feature specification from `/specs/001-baseline-calculator/spec.md`

## Summary

Build the frozen baseline: a Java desktop GUI calculator with two operand fields, four operation buttons, Calculate and Clear, a result area and an inline error area. Calculation uses exact decimal arithmetic (`BigDecimal`), with division rounded half-up to 10 decimal places. The calculation rules live in a GUI-free `calculator.core` package, so they can be tested without a display; a thin Swing layer (`calculator.ui`) only collects input, calls the core and shows the outcome. Every `BASE-AC-*` check maps to an automated test, except `BASE-AC-01` and `BASE-AC-15`, which are manual or command checks. Details and alternatives are in [research.md](research.md).

## Technical Context

**Language/Version**: Java 22 (Amazon Corretto 22.0.2.9.1), compiled with `--release 22`

**Primary Dependencies**: none at runtime. The GUI toolkit is Swing, which ships with the JDK (decision D-01 in research.md).

**Storage**: N/A (no persistence; out of scope in the baseline)

**Testing**: JUnit Jupiter 6.1.3 through Maven Surefire 3.6.1, run headless. GUI behaviour is tested on the Swing components directly (no window is opened). The window itself is checked manually for `BASE-AC-01`.

**Target Platform**: Desktop, Windows 11 for this track (LOG-07 suggested macOS; the deviation is recorded in the experiment log and `evidence/01-environment.md`)

**Project Type**: desktop-app

**Performance Goals**: none numeric. `BASE-NFR-02` is judged by observation (LOG-14, agreed). Arithmetic on text of normal length is instantaneous.

**Constraints**: no network access; inline errors only, never dialogs; no scientific behaviour; no dependency other than the test framework

**Scale/Scope**: one window, one screen, 16 functional requirements (8 from `BASE-FR-*`) and 5 non-functional requirements, 19 acceptance checks

## Constitution Check

*GATE: Must pass before Phase 0 research. Re-check after Phase 1 design.*

| Principle | Gate | Status before research | Status after design |
|---|---|---|---|
| I. Shared requirements are the source of truth | Plan adds no requirement; every requirement is traceable to design, tasks and tests; gaps go to the log | Pass | Pass. Coverage table below. One new gap (negative rounding ties) is logged as LOG-15, not decided silently. |
| II. Baseline scope only | No scientific features; CR-01 not read | Pass | Pass. No `exp`, `sqrt` or other operation is designed. |
| III. Observable correctness without crashes | Exact decimals, agreed rounding, grammar and error priority, inline errors, recovery | Pass | Pass. Rules are defined once, in `calculator.core`, and referenced from the contracts. |
| IV. Test evidence is honest and reproducible | Each `BASE-AC-*` has a test or manual procedure; commands work from a clean checkout; pinned `JAVA_HOME` | Pass | Pass. See quickstart.md. The commands there are planned and **not yet run**. |
| V. Reviewable, extensible structure | Logic separable from the GUI; no unjustified abstraction or dependency | Pass | Pass. Two packages, no dependency beyond JUnit. |
| Technology and environment constraints | Java, desktop GUI, Corretto 22, Maven, `JAVA_HOME` pinned, toolkit justified, versions checked against the registry | Pass | Pass. Toolkit justified in research.md D-01. Registry check dated 2026-10-27 in research.md. |
| Workflow and quality gates | Owner approves the plan before any code | Pending | Pass for the plan gate: owner approved the plan on 2026-10-27 (`evidence/02-approvals.md`, AP-01). No code has been written; implementation needs its own go-ahead. |

No violations, so Complexity Tracking is empty.

## Requirement Coverage

Maps each requirement to the design element that satisfies it and to the tests that will check it. Test names are planned, not yet written.

| Requirement | Design element | Planned verification |
|---|---|---|
| `BASE-FR-01` / FR-001, `BASE-AC-01` | `CalculatorApp.main` opens the window; no networking code or dependency exists | Manual check with networking off (screenshot or note) |
| `BASE-FR-02` / FR-002, `BASE-AC-02`, `-03` | `CalculatorPanel` operand fields | Panel test: set text, read it back |
| `BASE-FR-03` / FR-003 | `Operation` enum; four toggle buttons in one button group | Panel test: selecting a button marks only that one selected |
| `BASE-FR-04` / FR-004, `BASE-AC-04` to `-07`, `-18`, `-19` | `Calculator.calculate`; Calculate button and Enter handler call the same method | Core tests for each case; panel tests for button and for Enter in each field |
| `BASE-FR-05` / FR-005, `BASE-AC-10` | `CalculatorPanel.clear()` | Panel test: values, operation, result and error reset |
| `BASE-FR-06` / FR-006, `BASE-AC-11`, `-12`, `-17` | `OperandParser`, error priority in `Calculator` | Core tests (all listed inputs); panel tests (inline message, no result) |
| `BASE-FR-07` / FR-007, `BASE-AC-13` | Zero-divisor check in `Calculator` before dividing | Core and panel tests for `7 ÷ 0` and `7 ÷ 0.0` |
| `BASE-FR-08` / FR-008, FR-016, `BASE-AC-14` | Result and error replaced only on Calculate or Clear | Panel test: error, correct input, calculate |
| Baseline §4 / FR-009 to FR-013, `BASE-AC-08`, `-09` | `BigDecimal`; `ResultFormatter`; grammar regex `-?[0-9]+(\.[0-9]+)?` after trimming spaces | Core tests, including `-7 + 2`, `1.2 + 2.3`, `1 ÷ 3`, `2 ÷ 2`, zero shown as `0` |
| Baseline §5 / FR-014, FR-015 | Labelled controls, separate result and error areas, no dialogs | Panel test: error text in the error area; code review that no dialog class is used |
| `BASE-NFR-01` / NFR-001 | Corretto 22 runs the build and the app | Quickstart, environment record |
| `BASE-NFR-02` / NFR-002, SC-006 | Plain synchronous calculation | Observation during the manual run |
| `BASE-NFR-03` / NFR-003 | Core returns an outcome value instead of throwing for bad input | Tests above; no crash in any listed error case |
| `BASE-NFR-04` / NFR-004 | `core` and `ui` packages; tests mirror them | Review at freeze and when CR-01 is released |
| `BASE-NFR-05` / NFR-005, `BASE-AC-15` | Quickstart commands with pinned `JAVA_HOME` | Follow quickstart.md from a clean checkout |
| `BASE-AC-16` | Calculation has no hidden state; each Calculate reads the current fields | Panel test: several calculations in sequence |

## Project Structure

### Documentation (this feature)

```text
specs/001-baseline-calculator/
├── plan.md              # This file (/speckit-plan command output)
├── research.md          # Phase 0 output (/speckit-plan command)
├── data-model.md        # Phase 1 output (/speckit-plan command)
├── quickstart.md        # Phase 1 output (/speckit-plan command)
├── contracts/           # Phase 1 output (/speckit-plan command)
│   ├── calculation-contract.md
│   └── ui-contract.md
├── checklists/
│   └── requirements.md  # /speckit-specify output
└── tasks.md             # Phase 2 output (/speckit-tasks command - NOT created by /speckit-plan)
```

### Source Code (track folder root)

```text
pom.xml
src/
├── main/java/calculator/
│   ├── core/
│   │   ├── Operation.java
│   │   ├── CalculationError.java
│   │   ├── CalculationOutcome.java
│   │   ├── OperandParser.java
│   │   ├── ResultFormatter.java
│   │   └── Calculator.java
│   └── ui/
│       ├── CalculatorPanel.java
│       ├── ErrorMessages.java
│       └── CalculatorApp.java
└── test/java/calculator/
    ├── core/
    │   ├── OperandParserTest.java
    │   ├── ResultFormatterTest.java
    │   └── CalculatorTest.java
    └── ui/
        └── CalculatorPanelTest.java
```

**Structure Decision**: A single Maven module with the standard `src/main/java` and `src/test/java` layout inside this track's folder. `project.md` §4 shows `source/` and `tests/` folders and allows folder names to be adapted; the Maven standard layout is used because it is the build tool's own convention. Everything under `src/` is created only after the owner approves this plan and the tasks.

## Complexity Tracking

No constitution violations. Nothing to justify.
