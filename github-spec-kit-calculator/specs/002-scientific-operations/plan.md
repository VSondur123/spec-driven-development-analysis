# Implementation Plan: Scientific Operations (CR-01)

**Branch**: none created (work is on `george/spec-kit-cr01`) | **Date**: 2026-11-04 | **Spec**: [spec.md](spec.md)

**Input**: Feature specification from `/specs/002-scientific-operations/spec.md`

## Summary

Extend the frozen baseline calculator (commit `88f56d0`) with three unary operations on the First number: square (`x²`), square root (`√x`) and reciprocal (`1/x`). The existing structure is kept: calculation rules stay in the GUI-free `calculator.core` package and the Swing layer only adds three buttons to the existing selection group. `Operation` learns which operations are unary; `Calculator.calculate` then ignores the Second number for them (including malformed text, LOG-17) and keeps the baseline error priority on the First number (LOG-18). Square is an exact `BigDecimal` product. Reciprocal uses the baseline division policy (10 decimals, `HALF_UP`). Square root uses an exact integer method that rounds only once (research C-01). One error is added, `NEGATIVE_SQUARE_ROOT`; reciprocal of zero reuses the baseline division-by-zero error. The square root of `-0.0` is left undefined by owner decision (research C-06). All 19 baseline checks must be re-run individually (`CR-AC-21`). Details and rejected alternatives are in [research.md](research.md).

## Technical Context

**Language/Version**: Java 22 (Amazon Corretto 22.0.2.9.1), `--release 22`. Unchanged.

**Primary Dependencies**: none at runtime. Swing (JDK). `BigInteger.sqrt()` from the JDK is used for the square root (research C-01).

**Storage**: N/A

**Testing**: JUnit Jupiter 6.1.3 through Surefire 3.6.1, headless, as in the baseline. Existing tests stay; new ones are added (research C-09). Registry versions re-checked 2026-11-04, no change (research C-10).

**Target Platform**: Windows 11 desktop. Unchanged.

**Project Type**: desktop-app

**Performance Goals**: none numeric (`BASE-NFR-02`, observation). The square-root step works on integers of at most a few hundred digits for normal input.

**Constraints**: no network; inline errors only, never dialogs; only the three unary operations are added (CR-01 §2.2 exclusions stay out); no dependency other than the test framework.

**Scale/Scope**: 15 functional requirements (FR-001 to FR-015 in the spec, from `CR-FR-01` to `CR-FR-11` and CR-01 §4 and §5), 2 non-functional requirements, 22 change checks (`CR-AC-01` to `CR-AC-22`) and 19 baseline checks to re-run.

## Constitution Check

*GATE: Must pass before Phase 0 research. Re-check after Phase 1 design.* Checked against `.specify/memory/constitution.md` **v1.1.0** (last amended 2026-11-04).

| Principle | Gate | Status before research | Status after design |
|---|---|---|---|
| I. Shared requirements are the source of truth (now includes CR-01 v0.2 and LOG-17 to LOG-21; `CR-FR-*`, `CR-AC-*` traceable) | The plan adds no requirement; every `CR-FR` and `CR-AC` maps to a design element and a test; gaps go to the log | Pass | **Pass with one disclosed exception.** The coverage table below maps all 11 `CR-FR` and 22 `CR-AC`. Button test handles and window width are design choices, listed as such. The one gap found (square root of `-0.0`) is not logged in `shared/experiment-log.md`, although this principle says gaps are logged; the owner told the agent to ignore it (DL-46). The plan neither decides nor tests it. |
| II. Baseline and released change scope only | Only the three unary operations are added; other exclusions stay out | Pass | Pass. No trigonometry, logarithm, power, history, memory or styling is designed. |
| III. Observable correctness without crashes | Exact decimals, agreed rounding, grammar, inline errors, priority, recovery | Pass | Pass. Square is exact, square root and reciprocal round once to 10 decimals `HALF_UP`, the grammar and formatter are reused, the core returns an outcome and never throws. |
| IV. Test evidence is honest and reproducible (covers `CR-AC-*`; each `BASE-AC-01` to `-19` re-run and recorded individually) | Each `CR-AC` has a test or a manual procedure; the 19 baseline checks are re-run one by one; commands work from a clean checkout; pinned `JAVA_HOME` | Pass | Pass. See the coverage table and the re-run list. The new quickstart commands are planned and **not yet run**. |
| V. Reviewable, extensible structure | Logic separable from the GUI; no unjustified abstraction or dependency | Pass | Pass. The change extends the enum, the core and the panel; no new class beyond what the tasks may add, no dependency. This is the first real use of the extensibility intended by `BASE-NFR-04`. |
| Technology and environment constraints | Java, desktop GUI, Corretto 22, Maven, `JAVA_HOME` pinned, versions checked | Pass | Pass. Toolkit unchanged; versions re-checked on 2026-11-04. |
| Workflow and quality gates | Owner approves the plan before code; the frozen baseline spec is not edited; the change lives in `specs/002-scientific-operations` | Pending | **Pending the owner's approval of this plan.** No code has been written. `specs/001-baseline-calculator/` is untouched. |

No unjustified violation, so Complexity Tracking is empty.

## Requirement Coverage

Maps each requirement to the design element and to the verification. Test names are planned, not yet written. "Core" tests call `Calculator.calculate` directly; "panel" tests drive the Swing components headless; "manual" means the owner observes the real window.

| Requirement | Design element | Planned verification |
|---|---|---|
| `CR-FR-01` / FR-001, `CR-AC-01` | Three toggle buttons `x²`, `√x`, `1/x` in the existing group (C-07) | Panel test: three buttons exist with those labels and test handles; manual: owner sees them in the real window |
| `CR-FR-02` / FR-002, `CR-AC-16`, `CR-AC-17`, LOG-17 | `Operation` unary flag; `Calculator` does not parse the Second text for unary operations (C-04) | Core tests: Second empty, different value, malformed `abc`; panel test: same through the GUI |
| `CR-FR-03` / FR-003 | Calculate button and Enter handlers already call the same `calculate()`; no change (C-07) | Panel tests: unary result by button and by Enter in each field |
| `CR-FR-04` / FR-004, `CR-AC-02` to `CR-AC-04` | Exact `x.multiply(x)` (C-03) | Core and panel tests for `-3`, `1.2`, `0`; boundary cases in research.md |
| `CR-FR-05` / FR-005, `CR-AC-05` to `CR-AC-07` | Integer square root, rounded once (C-01) | Core and panel tests for `9`, `2`, `0`; boundary and tie cases in research.md |
| `CR-FR-06` / FR-006, `CR-AC-08` | New `NEGATIVE_SQUARE_ROOT`; message fixed by CR-01 §5 (C-05, C-08) | Core test (`-1`), panel test: message in the error area, no result |
| `CR-FR-07` / FR-007, `CR-AC-09` to `CR-AC-11` | `ONE.divide(x, 10, HALF_UP)` (C-02) | Core and panel tests for `4`, `3`, `-4`; tie and large-input cases |
| `CR-FR-08` / FR-008, `CR-AC-12`, `CR-AC-13` | Zero check by `signum`; reuses `DIVISION_BY_ZERO` (C-02) | Core and panel tests for `0`, `0.0`, `-0.0` (also `-0`) |
| `CR-FR-09` / FR-009, `CR-AC-14`, `CR-AC-15` | First number parsed with the baseline parser; priority empty, no operation, invalid, domain (C-05) | Core and panel tests for each unary operation with empty and `abc` input; priority combinations |
| `CR-FR-10` / FR-010, `CR-AC-18`, `CR-AC-19` | Result and error replaced only on Calculate or Clear (baseline behaviour, unchanged) | Panel tests: negative square root then correct; reciprocal of zero then correct |
| `CR-FR-11` / FR-011, `CR-AC-21`, `CR-AC-22` | Baseline tests kept unmodified except one helper (C-09); no baseline code path removed | Full re-run of all 19 baseline checks, one result each (list below); `mvn -B clean verify` |
| CR-01 §4 / FR-012 | Baseline `OperandParser` and `ResultFormatter` reused unchanged | Existing parser and formatter tests stay green; new cases for plain notation of tiny squares |
| CR-01 §5 / FR-013 | Inline error area only, no dialogs; core never throws | Panel tests; code review that no dialog class is used |
| LOG-20, LOG-13 / FR-014 | Second field stays visible and enabled; no change to when result and error clear | Panel test: Second field enabled while a unary operation is selected; shown result stays until Calculate or Clear |
| `CR-AC-20` / FR-015 | `clear()` already deselects the whole group (C-07) | Panel test: Clear after each unary operation returns to the initial state |
| `NFR-001` (`CR-AC-22`) | Same Maven commands, pinned `JAVA_HOME` | Quickstart run from a clean checkout of the changed code (after an owner-requested commit) |
| `NFR-002` (`BASE-NFR-04`) | Extension by enum, core and panel; baseline spec untouched | Review at the end of implementation (affected-file list vs the impact analysis) |
| LOG-18, LOG-21 | Priority applied to the First number; message "Enter both numbers" unchanged | Covered by the `CR-FR-09` tests |
| LOG-19 | Accepted policy: reciprocal of a very large value shows `0` | Core test: `30000000000` and `-30000000000` give `0` |
| Square root of `-0.0` | **Not decided** (owner, DL-46) | **Not tested.** Not asserted anywhere. |

## Baseline checks to re-run (`CR-AC-21`)

All 19, each recorded on its own in a new results file for the changed application (the baseline results file `evidence/06-test-results.md` stays as the baseline record). A check counts as Pass only if observed.

| Check | How it is re-run | Reason it is exposed to this change |
|---|---|---|
| `BASE-AC-01` | Manual: owner launches the changed jar with networking off; screenshot or note | Window changed |
| `BASE-AC-02`, `BASE-AC-03` | Manual: owner re-takes the real-window screenshots; also panel test | Seven buttons make the window wider |
| `BASE-AC-04` to `BASE-AC-09` | Existing core and panel tests, plus owner spot-check in the real window | The calculation function was rewritten around the operation type |
| `BASE-AC-10` | Existing panel test; owner check | `clear()` must also deselect the new buttons |
| `BASE-AC-11` | Existing core and panel tests (both-empty, first-empty, second-empty with a binary operation) | Highest regression risk: the empty-operand rule now depends on the operation |
| `BASE-AC-12` | Existing tests for all listed malformed inputs | Parser reused; the Second operand is no longer parsed for unary operations |
| `BASE-AC-13` | Existing tests for `7 ÷ 0` and `7 ÷ 0.0` | Division by zero now has a second source (reciprocal) |
| `BASE-AC-14` | Existing recovery test | Error handling shared with the new error |
| `BASE-AC-15` | Clean checkout of the changed commit, follow the documented commands (needs a commit the owner asks for) | Build changed |
| `BASE-AC-16` | Existing sequential-calculation test, extended with a unary calculation in between | State handling |
| `BASE-AC-17` | Existing no-operation test | Priority code changed |
| `BASE-AC-18` | Existing `0 ÷ 5` and `1 ÷ 3` tests | Division code shared with reciprocal |
| `BASE-AC-19` | Existing Enter tests; owner check in the real window | Enter also runs unary operations |

Regression is counted only where the baseline Pass was observed and the same check now fails (protocol section 5 step 6). Baseline results for all 19 were recorded as Pass, so each can be compared.

## Project Structure

### Documentation (this feature)

```text
specs/002-scientific-operations/
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

Files that change are marked `*`. Nothing else is touched.

```text
pom.xml                              # unchanged (no new dependency)
src/
├── main/java/calculator/
│   ├── core/
│   │   ├── Operation.java           * three constants, unary flag
│   │   ├── CalculationError.java    * NEGATIVE_SQUARE_ROOT (last, lowest priority)
│   │   ├── CalculationOutcome.java
│   │   ├── OperandParser.java
│   │   ├── ResultFormatter.java
│   │   └── Calculator.java          * operand handling by arity; three computations
│   └── ui/
│       ├── CalculatorPanel.java     * three buttons in the existing group
│       ├── ErrorMessages.java       * message for the new error
│       └── CalculatorApp.java
└── test/java/calculator/
    ├── core/
    │   ├── OperandParserTest.java
    │   ├── ResultFormatterTest.java
    │   └── CalculatorTest.java      * new unary tests
    └── ui/
        └── CalculatorPanelTest.java * helper switch extended, new unary tests
```

A separate small class for the square-root step is allowed if it keeps `Calculator` readable; that is a task-level choice, to be listed in `tasks.md` and justified under constitution V.

**Structure Decision**: Keep the single Maven module and the two packages from the baseline. CR-01 is the change the structure was built for; no restructuring.

## Complexity Tracking

No constitution violations that need a justification. The one disclosed exception (the unlogged `-0.0` gap) is an owner decision, recorded in the Constitution Check above, in the spec and in `evidence/03-decisions-log.md` (DL-46).
