# Epic 1 Context: Basic Calculations

<!-- Compiled from planning artifacts. Edit freely. Regenerate with compile-epic-context if planning docs change. -->

## Goal

A user can launch a Java Swing calculator offline, type two numbers, choose one of four operations (+, −, ×, ÷), and see a correct, cleanly formatted result. This epic builds the walking skeleton: the Maven project, the pure calculation core, and the GUI that drives it. Error handling beyond the happy path comes in Epic 2; Clear and the verification pass come in Epic 3.

## Stories

- Story 1.1: Project scaffold and launchable window
- Story 1.2: Core calculation engine with display policy
- Story 1.3: Enter operands, select operation and calculate in the GUI

## Requirements & Constraints

- The app is a desktop GUI that runs with no network access; the build and run commands must be documented and reproducible.
- Operands are typed into two labelled text fields; one of four operations is selected with visibly indicated buttons; the result is shown after the Calculate button or Enter in an operand field.
- Integers, decimals and negatives are supported. Arithmetic is exact. Division rounds half-up to 10 decimal places; results are plain decimal text with no trailing zeros, no exponent notation, and zero shown as `0` (never `-0`).
- Reference results: `7+5=12`, `7-5=2`, `7*5=35`, `7/2=3.5`, `-7+2=-5`, `1.2+2.3=3.5`, `1/3=0.3333333333`, `2/2=1`, `0/5=0`.
- Each result must reflect only the current operands and selected operation, over repeated calculations.
- Calculations feel instantaneous (under 100 ms).
- No scientific or other features beyond the four operations.

## Technical Decisions

- Layered design with dependency direction `ui → presenter → core`; `core` and `presenter` must not import `java.awt` or `javax.swing`.
- Package root `calculator`: `calculator.core` (`Calculator`, `Operation`, `Outcome`, `ErrorKind`, `ResultFormatter`), `calculator.presenter` (`CalculatorPresenter`, `ViewState`), `calculator.ui` (`CalculatorFrame`), and `calculator.Main`.
- `Calculator.evaluate(String a, Operation op, String b)` is the only entry into the core, never throws on user input, and returns a sealed `Outcome` of `Success(String)` or `Failure(ErrorKind)`.
- Numeric values are `BigDecimal` only (no `double`). One formatter produces result text. One mapping produces error text.
- `Operation` is an enum holding its symbol, label and arithmetic function; it is the only extension point for operations.
- The presenter owns all interaction state and publishes an immutable `ViewState`; the view renders it and holds no logic.
- The window is built and updated on the Event Dispatch Thread; calculation is synchronous.
- Every control has a visible label and a stable component name: `operandA`, `operandB`, `op-add`, `op-subtract`, `op-multiply`, `op-divide`, `calculate`, `clear`, `result`, `error`. Errors appear inline, never in dialogs.
- Build: Maven 3.9.16, Java 22 (Amazon Corretto) with `maven.compiler.release` 22, `sourceDirectory=src`, `testSourceDirectory=tests`, JUnit Jupiter 6.1.3, maven-compiler-plugin 3.16.0, maven-surefire-plugin 3.6.0, exec-maven-plugin 3.6.4. `JAVA_HOME` must be pinned to the Corretto 22 home in every documented command. GUI tests are skipped when the environment is headless.
- Test method names state the acceptance check they verify (for example `baseAc07_divide_7_by_2_gives_3_5`).

## UX & Interaction Patterns

- Two operand fields, four operation buttons, a Calculate button, a Clear button, a result area and an inline error area.
- Enter in either operand field is equivalent to pressing Calculate.
- Selecting an operation replaces any previous selection and does not clear the result or error.

## Cross-Story Dependencies

- Story 1.2 (core) must exist before Story 1.3 (presenter and GUI) can calculate anything.
- Story 1.1 provides the Maven project and `Main` that Stories 1.2 and 1.3 build on.
- Epic 2 extends the same `Calculator`, `ErrorKind` and presenter; Epic 3 adds Clear and the architecture-rules test.
