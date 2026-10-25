# Addendum — Baseline Desktop Calculator

Technical-how and decisions that belong downstream (architecture), not in the PRD.

## Proposed technology choices (D-05, pending team confirmation)

- Java 22 (Amazon Corretto 22.0.2), Maven 3.9.16, Swing, JUnit 5.
- `JAVA_HOME` pinned to Corretto 22 in all build commands (default `mvn` otherwise uses Homebrew JDK 26).
- Source in `src/`, tests in `tests/` (Maven `sourceDirectory`/`testSourceDirectory` overridden) to fit the track layout.
- Numeric type `java.math.BigDecimal`; division `divide(b, 10, RoundingMode.HALF_UP)`, then `stripTrailingZeros().toPlainString()`; zero normalised to `0`.

## Testing notes

- Logic layer is unit-testable without a display. GUI behaviours (BASE-AC-01, 02, 03, 10, 14, 16) are exercised either via Swing component tests with `GraphicsEnvironment` guard (skipped when headless) or by a manual checklist recorded in `evidence/`.
- Test-input table for BASE-AC-04…09, 13 is shared across tracks and must not be modified per track.

## Sources

`shared/baseline-requirements.md`, `shared/acceptance-criteria.md`, `evidence/02-decisions-log.md`.
