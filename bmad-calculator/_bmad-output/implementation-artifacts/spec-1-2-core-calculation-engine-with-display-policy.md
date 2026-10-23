---
title: 'Story 1.2: Core calculation engine with display policy'
type: 'feature'
created: '2026-10-23'
status: 'done'
baseline_commit: 'f5ae2f03eb03fcf8e7e8253425b21840acca8337'
route: 'dispatch'
review_loop_iteration: 0
story_key: '1-2-core-calculation-engine-with-display-policy'
context:
  - '{project-root}/_bmad-output/implementation-artifacts/epic-1-context.md'
---

<frozen-after-approval reason="human-owned intent — do not modify unless human renegotiates">

## Intent

**Problem:** The calculator has a window but no arithmetic. BASE-AC-04 to 09 and the display policy (LOG-03) cannot be checked, and Story 1.3 has nothing to call.

**Approach:** Add the pure `calculator.core` package: `Operation`, `ErrorKind`, `Outcome`, `ResultFormatter`, and `Calculator.evaluate(String, Operation, String)`, following architecture AD-2 to AD-5 and AD-7, with unit tests that name the acceptance check they verify.

## Boundaries & Constraints

**Always:** `BigDecimal` only, no `double`/`float`; `evaluate` never throws for any input including `null`; validation order is blank operand, then null operation, then Valid Number grammar `-?\d+(\.\d+)?` on the stripped text, then divide-by-zero; division is `divide(b, 10, RoundingMode.HALF_UP)`; result text is `stripTrailingZeros().toPlainString()` with zero shown as `0`; error text comes only from `ErrorKind.message()`; no `java.awt`/`javax.swing` imports.

**Never:** Presenter, GUI, `ViewState` or any control (Story 1.3); modifying `Main` or `CalculatorFrame`; scientific operations; any other public entry point into the core than `Calculator.evaluate`; editing `shared/` or `README.md`.

## I/O & Edge-Case Matrix

| Scenario | Input / State | Expected Output / Behavior | Error Handling |
|----------|--------------|---------------------------|----------------|
| Add | `7`, ADD, `5` | `Success("12")` | N/A |
| Subtract | `7`, SUBTRACT, `5` | `Success("2")` | N/A |
| Multiply | `7`, MULTIPLY, `5` | `Success("35")` | N/A |
| Divide | `7`, DIVIDE, `2` | `Success("3.5")` | N/A |
| Negative | `-7`, ADD, `2` | `Success("-5")` | N/A |
| Decimal | `1.2`, ADD, `2.3` | `Success("3.5")` | N/A |
| Repeating | `1`, DIVIDE, `3` | `Success("0.3333333333")` | N/A |
| Whole result of division | `2`, DIVIDE, `2` | `Success("1")` | N/A |
| Large whole result | `100`, DIVIDE, `1` | `Success("100")` (no exponent) | N/A |
| Zero results | `0`, DIVIDE, `5`; `0`, MULTIPLY, `-1`; `-0`, ADD, `0` | `Success("0")` each | N/A |
| Spaces | ` 7 `, ADD, ` 5 ` | `Success("12")` | N/A |
| Blank operand | ``, ADD, `5`; `7`, ADD, `   ` | `Failure(EMPTY_OPERAND)` | No exception |
| No operation | `7`, `null`, `5` | `Failure(NO_OPERATION)` | No exception |
| Malformed | `abc`, `1.2.3`, `1,5`, `1e3`, `--1`, `.`, `+1` (ADD with `5`) | `Failure(INVALID_NUMBER)` | No exception |
| Precedence | `""`, `null`, `abc` | `Failure(EMPTY_OPERAND)`; `abc`, `null`, `5` gives `NO_OPERATION` | No exception |
| Divide by zero | `7`, DIVIDE, `0`; `7`, DIVIDE, `0.0`; `7`, DIVIDE, `-0` | `Failure(DIVIDE_BY_ZERO)` | No exception |
| Null operand | `null`, ADD, `5` | `Failure(EMPTY_OPERAND)` | No exception |

</frozen-after-approval>

## Code Map

- `bmad-calculator/src/calculator/core/ErrorKind.java` -- new enum `EMPTY_OPERAND`, `NO_OPERATION`, `INVALID_NUMBER`, `DIVIDE_BY_ZERO` with `message()` ("Enter both numbers", "Select an operation", "Invalid number", "Cannot divide by zero")
- `bmad-calculator/src/calculator/core/Outcome.java` -- new sealed interface with records `Success(String text)` and `Failure(ErrorKind kind)`
- `bmad-calculator/src/calculator/core/Operation.java` -- new enum ADD, SUBTRACT, MULTIPLY, DIVIDE with `symbol()` (`+`, `−`, `×`, `÷`), `label()`, and `apply(BigDecimal, BigDecimal)`
- `bmad-calculator/src/calculator/core/ResultFormatter.java` -- new `format(BigDecimal)`
- `bmad-calculator/src/calculator/core/Calculator.java` -- new `evaluate(String, Operation, String)`
- `bmad-calculator/tests/calculator/core/` -- new `CalculatorTest`, `ResultFormatterTest`, `OperationTest`
- Reuse: `core/package-info.java` already states the no-AWT/Swing rule.

## Tasks & Acceptance

**Execution:**
- [x] `bmad-calculator/src/calculator/core/ErrorKind.java` -- create enum with messages -- AD-4 single message source
- [x] `bmad-calculator/src/calculator/core/Outcome.java` -- create sealed `Success`/`Failure` -- AD-2
- [x] `bmad-calculator/src/calculator/core/Operation.java` -- create enum with symbol, label, function; DIVIDE uses scale 10 HALF_UP -- AD-7, FR-8
- [x] `bmad-calculator/src/calculator/core/ResultFormatter.java` -- create formatter -- AD-4
- [x] `bmad-calculator/src/calculator/core/Calculator.java` -- create `evaluate` with the validation order -- AD-2, AD-5
- [x] `bmad-calculator/tests/calculator/core/*Test.java` -- test every matrix row; method names state the `BASE-AC-nn` -- AD-10

**Acceptance Criteria:**
- Given the matrix above, when `mvn test` runs, then every row has a passing test and no test is skipped in the `core` package.
- Given any string or `null` inputs, when `evaluate` runs, then no exception escapes.
- Given the source tree, when searched, then `core` contains no `double`, `float`, `java.awt` or `javax.swing`.

## Implementation Notes

- Implemented directly by the main session: `ErrorKind`, `Outcome`, `Operation`, `ResultFormatter`, `Calculator` and three test classes (`CalculatorTest` 41 cases incl. parameterised, `ResultFormatterTest` 4, `OperationTest` 2).
- Verified 2026-10-23, `JAVA_HOME` Corretto 22: `mvn test` -> 51 run, 0 failed, 0 skipped, BUILD SUCCESS.
- The spec's grep check for forbidden tokens in `core` has one hit: a Javadoc comment in `package-info.java` that names the rule. No imports or types are affected.
- After review (2026-10-23): visibility narrowed, regex made explicit ASCII, tests extended to 62 total, `core` grep now clean; log in `evidence/story-1-2-test-log.txt`.
- Extra cases beyond the matrix (added by the implementer): `.5`, `1.`, `1 2`, Arabic-Indic digit `٣` as malformed; 5000-digit input and `1e9999`; a never-throws sweep over operation x operand combinations.

## Spec Change Log

## Review Triage Log

Layers: Blind Hunter (BH, 8 findings), Edge Case Hunter (EC, 9 edge cases + 3 claim checks), Verification Gap (VG, none: "No verification gaps found"). Reviewed 2026-10-23 at the same model capability as the session.

| # | Source | Finding | Verdict / route | Evidence |
|---|---|---|---|---|
| 1 | EC claim (high), BH-7 | `Operation.apply`, `DIVISION_SCALE`, `ResultFormatter.format` are public, so the core has more than one entry point; Story 1.3 could bypass validation and the zero-divisor guard | medium / patch | Direct deviation from the spec's Never ("any other public entry point ... than `Calculator.evaluate`"). Fixed: `apply`, `DIVISION_SCALE` and `ResultFormatter` are now package-private; tests share the package and still pass. Also resolves BH-5 and EC-4 (`apply` zero-divisor contract exposed to outside callers) |
| 2 | BH-3, EC-7, EC-9 | Missing tests for negatives and boundaries | medium / patch | Real gaps in coverage. Added `-7*-5`, `-7/2`, `7-10`, `-2/3` (HALF_UP away from zero), `-1/3`, `-1/30000000000` (rounds to `0`), `-0.5+0.5`, `-0.0+5`, and malformed `-`, `-.5`, `-1.`; all pass |
| 3 | BH-2 | `\d` relies on default ASCII-only matching | low / patch | Behaviour unchanged but intent was implicit. Regex now `[0-9]` with updated Javadoc; the Arabic-Indic digit test stays |
| 4 | BH-8 (traceability) | Test names cited AC-04 to 09, 12, 13, 17, 18 only; message test cited no source | low / patch | Renamed `emptyOperandIsReported` and the null-operand test to `baseAc11_...`; message test now cites LOG-04/LOG-05 and D-07. AC-10, 14, 15, 16 belong to other stories |
| 5 | EC claim (low) | Spec's grep for forbidden tokens matches a Javadoc comment, so "no matches" fails literally | low / patch | True at review time. Fixed by rewording the `core` package Javadoc to avoid the tokens; grep now reports no matches. The spec text itself was not edited |
| 6 | BH-1, EC-1, EC-2, EC-8, EC claim (medium) | "Never throws" overstated: no length cap, no catch for OOM/ArithmeticException; sweep lacked large-by-large operands | low / patch + rejected | `divide` with an explicit scale and rounding mode cannot throw ArithmeticException for grammar-valid input (no exponent form exists). Patched: Javadoc now states the memory/time limit; the sweep gained 5000-digit integer, decimal and negative operands against every operation. A length cap or catch-all was rejected: it needs a new error kind or requirement decision and adds branches for input no user types |
| 7 | EC-3, BH-2 (second part) | NBSP/zero-width space is not trimmed by `strip()`, so pasted numbers are "Invalid number" | low / rejected | `Character.isWhitespace` excludes NBSP, so the claim is true, but "surrounding spaces ignored" in the shared documents does not say whether NBSP counts. Choosing is a requirement decision; not silently extended. Candidate for the shared log if it matters |
| 8 | EC-5, EC-6, BH-6 | `format(null)`, `Success(null)`, `Failure(null)` unchecked | low / rejected | After finding 1 `format` is package-private and only called with a non-null value from `Calculator`. Records are constructed only inside the core; no reachable null |
| 9 | BH-4 | Display policy authorisation and `DIVISION_SCALE` placement | false | Policy is LOG-03 in `shared/experiment-log.md`, `baseline-requirements.md` section 4 item 8, and `BASE-AC-18`. Keeping the division scale beside the division is a design choice, no harm named |
| 10 | BH-8 (record-keeping), BH-8 (UTF-8) | No evidence update; non-ASCII operator symbols risk encoding | false | Evidence is saved in `evidence/story-1-2-test-log.txt` and the status file; `pom.xml` sets `project.build.sourceEncoding` to UTF-8 |

No `intent_gap` or `bad_spec` findings, so no loopback (`review_loop_iteration` stays 0). No `defer` entries.

## Design Notes

Slicing decision: `evaluate` is total from this story because AD-2 forbids escaping exceptions and parsing needs the grammar check anyway. Stories 2.1 and 2.2 therefore concentrate on user-visible error behaviour in the presenter and GUI plus exhaustive tests; this overlap is logged as a planning finding.

Golden examples: `new Calculator().evaluate("7", Operation.DIVIDE, "2")` returns `new Outcome.Success("3.5")`; `evaluate("7", Operation.DIVIDE, "0")` returns `new Outcome.Failure(ErrorKind.DIVIDE_BY_ZERO)`. `Calculator` is stateless; a static factory or plain instance are both fine, but pick one and keep the signature stable for Story 1.3.

## Verification

**Commands:**
- `export JAVA_HOME=$(/usr/libexec/java_home -v 22) && cd bmad-calculator && mvn test` -- expected: BUILD SUCCESS, new `core` tests run, none failed
- `grep -rnE "double|float|java\.awt|javax\.swing" bmad-calculator/src/calculator/core` -- expected: no matches
