# Research: Scientific Operations (CR-01)

Date: 2026-11-04. Resolves the open technical choices in [plan.md](plan.md). The Technical Context has no `NEEDS CLARIFICATION` markers. The baseline decisions D-01 to D-08 in [../001-baseline-calculator/research.md](../001-baseline-calculator/research.md) (frozen) still apply; this file only records what CR-01 adds or changes. Numbering restarts at C-01 to avoid clashing with the baseline's D-nn.

## Decisions

### C-01 Square root: one exact integer step, rounded once

- **Decision**: Compute the rounded square root with integer arithmetic only, in four steps. Let `x` be the accepted input as a `BigDecimal` with `x >= 0`.
  1. `T = x × 4 × 10^20` as an exact `BigDecimal` (multiplication never rounds).
  2. `N = floor(T)` as a `BigInteger` (`setScale(0, RoundingMode.FLOOR).toBigInteger()`). This is an exact truncation of a non-negative number, not a rounding of the result.
  3. `q = N.sqrt()` (`BigInteger.sqrt()`, Java 9 and later, returns the exact floor of the square root).
  4. `R = (q + 1) >> 1` (integer division by two). The result is `new BigDecimal(R, 10)`, i.e. `R × 10^-10`, then the baseline `ResultFormatter`.
- **Why it is correct** (rounded once, HALF_UP): the wanted value is `R = floor(√x × 10^10 + ½)`, which is "round half up" of `√x × 10^10` to an integer. Since `√x × 10^10 = √(x × 10^20)` and `2√z = √(4z)`, `R = floor((1 + √T) / 2)`. For any real `a`, `floor(floor(a) / 2) = floor(a / 2)`, and `floor(1 + √T) = 1 + floor(√T) = 1 + floor(√N)` because `floor(√T) = floor(√floor(T))` for `T >= 0`. So `R = (1 + q) >> 1`. No intermediate value is rounded: the only operations are an exact multiplication, two floors that provably do not change the final integer, and an integer shift.
- **Ties**: an exact tie happens when `√T` is an odd integer, `q = 2r − 1`, and then `R = r` (rounds up). This is `HALF_UP` for a non-negative value. An example: `x = 0.0000000000000000000025` (22 decimals) has `√x = 5E-11`, exactly half of the last place; the result is `0.0000000001`. An irrational root (such as `√2`) is never a tie.
- **Worked checks** (by hand, to be confirmed by tests): `x = 2` gives `T = 8E20`, `q = 28284271247`, `R = 14142135624`, result `1.4142135624` (`CR-AC-06`). `x = 9` gives `q = 60000000000`, `R = 30000000000`, result `3.0000000000`, shown `3` (`CR-AC-05`).
- **Rationale**: meets CR-01 §4 items 6 and 9: deterministic, one rounding, `HALF_UP`, no `double`, no dependency, works for any size or number of decimals the grammar accepts.
- **Alternatives considered**:
  - `BigDecimal.sqrt(MathContext)` then `setScale(10, HALF_UP)`: rejected because it rounds twice (first to the `MathContext` precision, then to 10 decimals), which can give a wrong last digit when the first rounding creates or removes a tie (for example a root a hair below a tie at digit 11 that rounds up to the tie at the working precision). `BigDecimal.sqrt` with `RoundingMode` is also limited to the `MathContext` precision, not to a fixed number of decimals.
  - `Math.sqrt(double)`: rejected, because of binary floating-point error and limited precision (the baseline also rejects `double`, D-02).
  - Newton iteration on `BigDecimal` to a fixed scale: rejected, more code and a stopping rule to justify, for no gain over `BigInteger.sqrt()`.
  - Scaling by `10^10` and using `MathContext` with a few extra digits ("guard digits"): rejected, still double rounding in rare cases.

### C-02 Reciprocal: baseline division policy

- **Decision**: Reject a zero input before dividing (`signum() == 0`; this covers `0`, `0.0`, `-0.0` and `-0`, which the grammar accepts and `BigDecimal` represents as zero). Otherwise `BigDecimal.ONE.divide(x, 10, RoundingMode.HALF_UP)`, the same call and rounding as baseline division (baseline D-02, LOG-15 agreed). The formatter then removes trailing zeros.
- **Rationale**: CR-01 §4 item 7 says reciprocal uses the baseline division policy. Detecting zero by sign of the parsed value (not by the text) is why `-0.0` needs no special case.
- **Notes**: A very large input such as `30000000000` gives scale-10 zero, shown `0` (LOG-19 agreed, accepted policy). A scale-10 zero from a negative input is shown as `0`, never `-0` (formatter: `signum() == 0` gives `0`).

### C-03 Square: exact multiplication

- **Decision**: `x.multiply(x)`, then the baseline formatter. No scale or rounding step (CR-01 §4 item 5).
- **Rationale**: `BigDecimal.multiply` is exact. `1.2 × 1.2 = 1.44`; `-3 × -3 = 9`; the formatter removes trailing zeros and prints plain notation, so very small or very large squares show no exponent.

### C-04 Unary operations ignore the Second number entirely

- **Decision**: `Operation` gets a way to tell unary from binary (a boolean on each constant). `Calculator.calculate` decides what to look at from the operation:
  - Binary operation: unchanged baseline behaviour (parse both fields; either empty gives `EMPTY_OPERAND`; either invalid gives `INVALID_NUMBER`).
  - Unary operation: parse only the First number. The Second text is not parsed, not checked and cannot produce an error, even when it is malformed (LOG-17).
  - No operation selected (`null`): arity is unknown, so the baseline behaviour is kept (both fields are examined; see C-05).
- **Rationale**: CR-01 §4 items 3 and 4, `CR-FR-02`, LOG-17. Keeping the rule in the core means the GUI needs no special case; the GUI passes both field texts as before.
- **Alternative considered**: the GUI blanks or ignores the Second field before calling the core. Rejected: it spreads a numeric rule into the GUI (constitution V) and could not be tested without a display.

### C-05 Error priority with unary operations

- **Decision**: The order stays: empty operand, then no operation selected, then invalid number, then domain error. For a unary operation "operand" means the First number only (LOG-18). Domain errors: `DIVISION_BY_ZERO` for divide by zero and for reciprocal of zero; a new `NEGATIVE_SQUARE_ROOT` for square root of a negative number.
- **Consequence for `null`**: with no operation selected and an empty Second number but a valid First number, the baseline answer is kept: `EMPTY_OPERAND`. CR-01 does not change the no-operation case. This follows from LOG-18 ("keep the baseline priority") and is not a new rule.
- **Rationale**: `CR-FR-09`, `CR-AC-14`, `CR-AC-15`, LOG-18. `CalculationError` is declared in priority order, so the new constant goes last.

### C-06 Negative zero under square root: not decided

- **Decision**: None. The owner told the agent to leave this gap undefined (2026-11-04, recorded as DL-46). The plan does not state a result for the square root of `-0.0` or `-0`, and the tests will not assert one.
- **What the code will do anyway**: the square-root domain check is the same sign test used for any input (`signum() < 0`). The outcome for a zero-signed input is therefore a side effect of that general test, not a requirement; it is not documented as behaviour and not tested. This stays visible in the spec's Edge Cases.

### C-07 GUI: three more buttons in the same group

- **Decision**: Add three toggle buttons to the existing button group, in the enum order after `÷`: `x²`, `√x`, `1/x`. The labels are the literal characters from CR-01 §2.1 (the same practice as the baseline symbols, baseline DL-27). Test handles: `squareButton`, `squareRootButton`, `reciprocalButton` (names chosen by this track; CR-01 gives labels only). The Second number field is neither hidden nor disabled (LOG-20). `calculate()` and `clear()` keep their logic: Calculate passes both texts and the selected operation to the core, and Clear already deselects every button in the group.
- **Rationale**: `CR-FR-01`, `CR-FR-03`, LOG-20, constitution V (extend by adding to the enum and the panel, not by restructuring). The operations row grows from four to seven buttons, so the window becomes wider; the owner must re-observe the screenshots for `BASE-AC-02` and `BASE-AC-03`.
- **Alternatives considered**: a second row or a separate group for the unary buttons. Rejected: a second group would allow a binary and a unary operation to look selected together, which CR-01 does not describe.

### C-08 Error message for the new error

- **Decision**: `NEGATIVE_SQUARE_ROOT` shows exactly `Cannot take square root of a negative number`, mandated by CR-01 §5. The reciprocal-of-zero case reuses the existing `Cannot divide by zero`. The other baseline messages are unchanged (including "Enter both numbers" for an empty First number, LOG-21).

### C-09 Tests: extend, do not replace

- **Decision**: Keep the 81 baseline tests unchanged except for the one helper that must grow (`CalculatorPanelTest.buttonName`, an exhaustive `switch` that will not compile once three constants exist). Add new core tests and panel tests for each `CR-AC` (see the coverage table in plan.md and the boundary list below). Surefire stays headless. The real window is observed by the owner for `CR-AC-01` and the checks marked manual in plan.md.
- **Rationale**: `CR-FR-11`, `CR-AC-21`. Not deleting or editing baseline tests is what lets the re-run show a regression.

### C-10 Dependencies and build

- **Decision**: No new dependency; no `pom.xml` change expected. `BigInteger.sqrt()` is part of the JDK since Java 9; the build already targets `--release 22`.
- **Registry check** (re-done 2026-11-04 from `https://repo.maven.apache.org/maven2/<group>/<artifact>/maven-metadata.xml`): JUnit Jupiter 6.1.3, Surefire 3.6.1 (both latest release, unchanged). Compiler plugin 3.16.0, jar plugin 3.5.1, resources plugin 3.5.0 are still the latest stable 3.x; the registry release tag is a 4.0.0 beta for each (compiler 4.0.0-beta-5, jar 4.0.0-beta-2, resources 4.0.0-beta-2). All pins from the baseline stay. They are already proven to build together (baseline T033, clean checkout `BASE-AC-15`).

## Boundary cases the tests must cover

Core layer unless stated. Expected values are from CR-01 §4.1 and from the arithmetic rules; all are to be confirmed by running the tests, not assumed.

**Square**
- `-3` gives `9`; `1.2` gives `1.44`; `0` gives `0` (`CR-AC-02` to `04`).
- `-0.0` gives `0` (never `-0`).
- A long exact result: `12345678901234567890` squared is exact with no exponent; `0.00000000001` (1E-11) squared gives `0.0000000000000000000001` (1E-22, plain notation, 22 decimals, not rounded to 10).
- Trailing zeros removed: `2.5` gives `6.25`; `10` gives `100`.

**Square root**
- `9` gives `3`; `2` gives `1.4142135624`; `0` gives `0` (`CR-AC-05` to `07`); `0.0` gives `0`.
- `-1` gives `NEGATIVE_SQUARE_ROOT`; `-0.5` and `-1000` too.
- Rounding up vs down: `3` gives `1.7320508076` (digits `1.73205080756…`); `5` gives `2.2360679775` (digits `2.23606797749979…`); `0.5` gives `0.7071067812` (digits `0.70710678118…`).
- Exact tie rounds up: `0.0000000000000000000025` gives `0.0000000001`.
- Just below a tie rounds down (this is the double-rounding trap): `0.0000000000000000000024999999999999999999` gives `0`.
- Very small non-zero: `0.000000000000000000001` (1E-21, root about 3.16E-11, below half of the last place at 5E-11) gives `0`.
- Exact roots with decimals: `1.44` gives `1.2`; `0.25` gives `0.5`; `4` gives `2`.
- A large input: `123456789012345678901234567890` gives `351364182882014.4253111222` (checked against an independent exact calculation on 2026-11-04).
- NOT tested: `-0.0` and `-0` (C-06).

**Reciprocal**
- `4` gives `0.25`; `3` gives `0.3333333333`; `-4` gives `-0.25` (`CR-AC-09` to `11`).
- `0`, `0.0`, `-0.0` and `-0` give `DIVISION_BY_ZERO` (`CR-AC-12`, `13`).
- `1` gives `1`; `0.5` gives `2`; `-1` gives `-1`.
- Tie rounds away from zero: `2048` gives `0.0004882813` (exact `0.00048828125`); `-2048` gives `-0.0004882813`.
- Very large input shows `0` (LOG-19): `30000000000` gives `0`; `-30000000000` gives `0`, not `-0`.

**Unary interface rules**
- Second number empty, filled with a different value, or malformed (`abc`): same result, no error (`CR-AC-16`, `17`, LOG-17).
- Empty First number with each unary operation gives `EMPTY_OPERAND` (`CR-AC-14`); malformed First gives `INVALID_NUMBER` (`CR-AC-15`); empty First and no operation gives `EMPTY_OPERAND`.
- Priority with unary: malformed First number and negative square-root cannot occur together (invalid wins); no operation selected with valid First gives `NO_OPERATION`.
- GUI layer: the seven buttons form one group (selecting one deselects the others); Enter in either field runs a unary calculation; error then correction then calculate recovers (`CR-AC-18`, `19`); Clear after a unary calculation returns to the initial state, including the three new buttons (`CR-AC-20`).

## Open points (not decided silently)

1. **Square root of `-0.0`**: left undefined by the owner (C-06, DL-46).
2. **Button names** (`squareButton`, `squareRootButton`, `reciprocalButton`) and the choice to extend the existing group are this track's design, not part of CR-01.
3. **Window width** grows with seven buttons; the owner is asked to re-observe `BASE-AC-02` and `BASE-AC-03`.
