# Research: Baseline Calculator

Date: 2026-10-27. Resolves every open technical choice in [plan.md](plan.md). The Technical Context had no `NEEDS CLARIFICATION` markers; the only unmade choice was the GUI toolkit (left open by the constitution), and the decisions below record how that and the other technical choices were made.

## Decisions

### D-01 GUI toolkit: Swing

- **Decision**: Use Swing (`javax.swing`), which is part of the JDK.
- **Rationale**: It needs no dependency, so a clean checkout builds with only the JDK and Maven (`BASE-AC-15`, `BASE-NFR-05`). Its components can be created and operated without a display (only top-level windows need one), so button, field and Enter-key behaviour can be tested automatically. Corretto 22 on Windows runs it without extra setup.
- **Alternatives considered**:
  - JavaFX: needs the OpenJFX libraries as a dependency with platform-specific artefacts, adds module-path setup to run and test, and offers nothing the baseline requires.
  - AWT only: lacks toggle buttons and the component set needed for the labelled interface.
- **Owner approval**: The constitution requires the toolkit to be chosen and justified in the plan; the owner approves it with the plan.

### D-02 Numeric representation: `BigDecimal`

- **Decision**: Parse operands into `java.math.BigDecimal`. Add, subtract and multiply exactly. Divide with scale 10 and `RoundingMode.HALF_UP`. Format by removing trailing zeros and writing plain text (`toPlainString`), so zero prints as `0` and no exponent appears.
- **Rationale**: Gives exact decimal arithmetic (`1.2 + 2.3 = 3.5`) and the agreed division policy (`1 ÷ 3 = 0.3333333333`, `2 ÷ 2 = 1`).
- **Alternatives considered**: `double` (rejected: floating-point artefacts, and `BASE-AC-09` forbids them); a hand-written decimal type (rejected: unnecessary).

### D-03 Input parsing: strict grammar before any number conversion

- **Decision**: Strip leading and trailing space characters (U+0020 only). A result of length zero is an empty operand. Otherwise accept only text matching `-?[0-9]+(\.[0-9]+)?`; everything else is "invalid number". This follows the owner decisions recorded as LOG-12 (agreed): `.5`, `5.` and `-` are invalid, and a spaces-only field is empty.
- **Rationale**: The grammar in baseline §4 item 9 is applied literally, and checking it before conversion stops `BigDecimal` from accepting forms the grammar rejects (`+1`, `1e3`, `.5`, `5.`).
- **Notes**: `[0-9]` is written explicitly because `\d` can be widened to other scripts' digits in some regex modes. Only the space character is stripped because the requirement says "surrounding spaces"; tabs and other whitespace are therefore invalid input. This is an interpretation of "spaces" and is flagged in the open points below.

### D-04 Error handling: outcome value, not exceptions

- **Decision**: `Calculator.calculate(first, second, operation)` returns a `CalculationOutcome` that is either a result text or one `CalculationError`. Bad input never raises an exception to the GUI.
- **Rationale**: Makes `BASE-NFR-03` (no crash) a property of the return type, and makes the error priority a single, testable sequence of checks.

### D-05 Error priority

- **Decision**: Check in this fixed order: an empty operand (first or second), then no operation selected, then an invalid first or second operand, then division by zero (divisor equal to zero after parsing, so `0`, `0.0` and `-0` are all zero).
- **Rationale**: Baseline §4 item 10. The source documents do not order the first operand against the second within a category; the plan reports the one that is found first and shows a single message for the category. This does not change any observable acceptance check.

### D-06 UI state: results and errors persist until Calculate or Clear

- **Decision**: Calculate first clears the previous result and error, then shows the new one. Editing a field or selecting an operation changes nothing else on screen. Clear resets fields, selection, result and error.
- **Rationale**: LOG-13 (agreed), spec FR-016.

### D-07 Build and test tooling: Maven, JUnit Jupiter, jar run

- **Decision**: Maven 3.9.16 (already installed, LOG-07), JUnit Jupiter 6.1.3, Surefire 3.6.1. Surefire runs with `java.awt.headless=true`. The application is packaged as a jar with a `Main-Class` entry and started with `java -jar`.
- **Rationale**: Matches the environment in LOG-07 and the constitution. Running a jar avoids an extra exec plugin. A headless test run proves the window logic without a display, while the real window is checked manually.
- **Alternatives considered**: `exec-maven-plugin` for `mvn exec:java` (adds a plugin for no gain); TestFX or AssertJ-Swing (adds a dependency for window-level tests that the manual `BASE-AC-01` check already covers).

### D-08 Project layout

- **Decision**: Maven standard layout in the track folder root; packages `calculator.core` and `calculator.ui`. See plan.md.
- **Rationale**: Keeps calculation rules free of GUI code (constitution V) and lets CR-01 add operations by extending `Operation` and the core without touching the GUI structure.

## Dependency versions checked against the registry

Checked on 2026-10-27 from `https://repo.maven.apache.org/maven2/<group>/<artifact>/maven-metadata.xml`.

| Artifact | Registry latest release tag | Pinned | Note |
|---|---|---|---|
| `org.junit.jupiter:junit-jupiter` | 6.1.3 | 6.1.3 | |
| `org.apache.maven.plugins:maven-surefire-plugin` | 3.6.1 | 3.6.1 | |
| `org.apache.maven.plugins:maven-compiler-plugin` | 4.0.0-beta-5 | 3.16.0 | Release tag is a beta; latest stable 3.x pinned |
| `org.apache.maven.plugins:maven-jar-plugin` | 4.0.0-beta-2 | 3.5.1 | Release tag is a beta; latest stable 3.x pinned |
| `org.apache.maven.plugins:maven-resources-plugin` | 4.0.0-beta-2 | 3.5.0 | Release tag is a beta; latest stable 3.x pinned |

Not yet verified: that this set of versions builds and runs together on Maven 3.9.16 with Corretto 22. That is checked at the first build and any change is recorded.

## Open points to confirm (not decided silently)

1. **Rounding of negative ties**. "Half-up" is not defined for negative values. `RoundingMode.HALF_UP` rounds ties away from zero (for example, a tie at the 11th decimal for `-1 ÷ 2048` gives `-0.0004882813`), while "toward positive infinity" would give `-0.0004882812`. The plan uses `HALF_UP`, the library mode of that name, as the closest reading. Logged as LOG-15 in `shared/experiment-log.md`; agreed by the team on 2026-10-27. It affects no frozen check.
2. **Which whitespace is "surrounding spaces"**. The plan strips only the space character (D-03). Not part of LOG-12 as agreed; it can be added only if the owner wants it stated. No acceptance check uses tabs.
3. **GUI toolkit** (D-01) needs the owner's approval together with the plan.
