# Epic 3 Context: Reset, Reproducibility and Verification

<!-- Compiled from planning artifacts. Edit freely. Regenerate with compile-epic-context if planning docs change. -->

## Goal

The user can reset the calculator to its initial state, and the team can build, run and verify the whole baseline from a clean checkout and record the result. This closes the baseline: Clear is the last user-visible feature, then reproducible build documentation with an automated architecture-rules guard, then a full acceptance pass with traceability evidence.

## Stories

- Story 3.1: Clear
- Story 3.2: Documented, reproducible build and architecture rules
- Story 3.3: Acceptance verification and evidence

## Requirements & Constraints

- Clear returns the calculator to the Initial State: both operand fields empty, no operation selected, result and error empty. After Clear the user can enter values and calculate normally.
- Build, run and test commands are documented and reproducible from a clean checkout, with `JAVA_HOME` pinned to JDK 22 and the prerequisites stated.
- Every shared acceptance check BASE-AC-01 to 19 is run and recorded as Pass, Fail, Blocked or Not run, with commit hash, environment, command and evidence location. A skipped GUI test is not a pass.
- A requirement-to-story-to-test traceability table is kept in `evidence/`. Unresolved defects and untested criteria are listed explicitly.
- No scientific features; CR-01 stays unread.

## Technical Decisions

- `CalculatorPresenter` owns all state; Clear is a presenter event `onClear()` returning `ViewState.initial()`. The view is the only place that sets field text from state, and does so only on Clear.
- The view needs a `clear` button with the stable name `clear` and a visible label, and must render the empty operand fields from the `ViewState`.
- `core` and `presenter` must not import AWT or Swing, and a lower layer must not import a higher one (core must not import presenter or ui; presenter must not import ui). A unit test (`ArchitectureRulesTest`) scans imports to enforce this.
- Maven 3.9.16, Corretto 22.0.2, JUnit Jupiter 6.1.3, `src` and `tests` directories; commands in `BUILD.md`. Evidence file conventions: `evidence/story-N-M-test-log.txt` per story.
- Per-story review by three subagent layers, triaged in each spec's `Review Triage Log`.

## UX & Interaction Patterns

- A Clear button next to Calculate. Clear also resets the selected operation (toggle group cleared).

## Cross-Story Dependencies

- Story 3.1 builds on Epic 1 and 2 behaviour; Story 3.2 needs the final source layout; Story 3.3 runs after 3.1 and 3.2 and needs all earlier stories.
