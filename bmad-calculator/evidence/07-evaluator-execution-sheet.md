# Evaluator Execution Sheet — Baseline (BASE-AC-01 to 19)

**Status:** Completed independent evaluator run for the BMAD Baseline track on 2026-11-05.

**Scope:** Baseline only. CR-01 is excluded.

The denominator is 19 parent acceptance criteria (ACs); subcases never add to it.

**Authority:** `shared/acceptance-criteria.md` §2 (frozen 2026-10-23, with LOG-12 to LOG-15 clarifications added 2026-11-03). If this sheet and that file differ, that file wins.

The same acceptance criteria, operands, order, and expected outcomes apply to both BMAD and Spec Kit.

**Baseline snapshots:**
- BMAD: tag `baseline-frozen` = `740c78de062bb1edd8076e4a49dde768b0fe1e49`.
- Spec Kit: `88f56d0979e2fae1fb5d59e8681975e3a1fc2a27` (`george/spec-kit-baseline`), designated in LOG-26; George's confirmation was pending when the template was prepared.

This document records only the BMAD evaluation. It does not assert any Spec Kit results.

---

## Run record (fill in once per track)

| Field | Value |
|---|---|
| Track | BMAD — Baseline |
| Evaluator (name, role, prior exposure to this track) | Thanh — Comparison Frameworks and Metrics; independent manual evaluator. Prior exposure: reviewed research and evaluation materials; did not implement the BMAD calculator. |
| Date / time / timezone | 2026-11-05; exact time not recorded; Finland (EET, UTC+02:00) |
| Snapshot (full commit SHA or tag; clean or dirty) | `740c78de062bb1edd8076e4a49dde768b0fe1e49` (`baseline-frozen`). Clean checkout reported for BASE-AC-15; working-tree status not independently archived. |
| OS / version | Windows 11 |
| JDK version and JAVA_HOME | Java 22 (Eclipse Temurin 22.0.2); `JAVA_HOME` path not recorded |
| Maven version | Apache Maven 3.9.11 |
| Build and run command used | `mvn -q test`; `mvn -q compile exec:java` |
| Display available (GUI run) or headless | GUI available; manual GUI evaluation performed |
| Adopted protocol and rubric revision | Protocol: `shared/experiment-protocol.md`, draft-5 (2026-11-03). Rubric: `shared/evaluation-rubric.md`, draft-5 (2026-11-03). Evaluator confirms official team adoption. Adoption decision date/reference not supplied. |

### Rules

1. Outcomes are **Pass**, **Fail**, **Blocked**, or **Not run**. Pass only if the behaviour was observed in this run.
2. Start each check from the initial state unless the row says stateful.
3. The first planned run is the primary record. Keep reruns separate, with the reason.
4. Parent outcome: Fail if any subcase fails; else Blocked if any is blocked; else Not run if any is unexecuted; else Pass.
5. Suggested message wording is not an exact-string condition; the cases must be clearly distinguishable.
6. Do not invent a latency threshold for BASE-NFR-02. Record observations only.
7. Engine-only unit tests do not establish GUI behaviour. Name the layer observed.

---

## Checks

| Test ID | Requirement | Procedure (same operands for both tracks) | Expected | Layer / subcases | Outcome | Observed / actual | Evidence ref |
|---|---|---|---|---|---|---|---|
| BASE-AC-01 | BASE-FR-01 | Disable networking. Launch with the documented command. | GUI opens. Record a screenshot or note showing the network state and the window. | Manual; GUI | Pass | Evaluator reported that the calculator launched and ran smoothly. An additional `1 - 1` calculation returned `0`. Network-disabled state was not explicitly documented in the submitted observation. | Evaluator-reported manual observation; screenshot/network-state record not provided. |
| BASE-AC-02 | BASE-FR-02 | Enter integer `7` in First number. | Accepted and displayed clearly. | GUI | Pass | The calculator accepted and displayed `7` correctly. | Evaluator-reported GUI observation; no separate artifact. |
| BASE-AC-03 | BASE-FR-02 | Enter decimal `1.5` in First number. | Accepted and displayed clearly. | GUI | Pass | The calculator displayed `1.5` correctly. | Evaluator-reported GUI observation; no separate artifact. |
| BASE-AC-04 | BASE-FR-03, BASE-FR-04 | `7 + 5`, click Calculate. | `12` | GUI | Pass | Evaluator reported the correct result for `7 + 5`. | Evaluator-reported GUI observation; no separate artifact. |
| BASE-AC-05 | BASE-FR-03, BASE-FR-04 | `7 - 5`, click Calculate. | `2` | GUI | Pass | Evaluator reported the correct result for `7 - 5`. | Evaluator-reported GUI observation; no separate artifact. |
| BASE-AC-06 | BASE-FR-03, BASE-FR-04 | `7 * 5`, click Calculate. | `35` | GUI | Pass | Evaluator reported the correct result for `7 * 5`. | Evaluator-reported GUI observation; no separate artifact. |
| BASE-AC-07 | BASE-FR-03, BASE-FR-04, BASE-FR-07 | `7 / 2`, click Calculate. | `3.5` | GUI | Pass | Evaluator reported the correct result for `7 / 2`. | Evaluator-reported GUI observation; no separate artifact. |
| BASE-AC-08 | Numeric behaviour | `-7 + 2`, click Calculate. | `-5` | GUI | Pass | Evaluator reported the correct result for the negative-number calculation. | Evaluator-reported GUI observation; no separate artifact. |
| BASE-AC-09 | Numeric behaviour | `1.2 + 2.3`, click Calculate. | Exactly `3.5` | GUI | Pass | Evaluator reported the correct decimal result. | Evaluator-reported GUI observation; no separate artifact. |
| BASE-AC-10 | BASE-FR-05 | Enter values, select an operation, calculate, then Clear. | Inputs, operation, result and error reset to the initial state. | GUI | Pass | Evaluator reported that Clear reset all relevant fields and states correctly. | Evaluator-reported GUI observation; no separate artifact. |
| BASE-AC-11 | BASE-FR-06 | Calculate with (a) empty first, (b) empty second, (c) both empty, (d) a spaces-only field. | Clear error for each (suggested "Enter both numbers"); no result; no crash. | GUI; 4 subcases | Pass | All four subcases reported Pass. Missing operand input produced the message `enter both num`. The evaluator also observed `select operation` for a missing operation. No crash reported. | Evaluator-reported GUI observations; individual screenshots not provided. |
| BASE-AC-12 | BASE-FR-06 | Calculate `abc`, `1.2.3`, `1,5`, `1e3`, `--1`, `.`, `.5`, `5.`, `-` as First number with a valid Second number. | "Invalid number" feedback for each; no crash. | GUI; 9 subcases | Pass | All nine malformed-input subcases reported Pass. The calculator displayed `invalid numb` for each. No crash reported. | Evaluator-reported GUI observations; individual screenshots not provided. |
| BASE-AC-13 | BASE-FR-07 | (a) `7 / 0`, (b) `7 / 0.0`. | Error (suggested "Cannot divide by zero"); no infinity or NaN; no crash. | GUI; 2 subcases | Pass | Both subcases reported Pass. The calculator displayed `cannot divide for 0`. No successful Infinity/NaN result or crash reported. | Evaluator-reported GUI observations; no separate artifact. |
| BASE-AC-14 | BASE-FR-08 | Trigger an input error, correct the input, calculate again. | Correct result is shown. | GUI; stateful | Pass | Evaluator reported successful recovery after correcting invalid input. | Evaluator-reported GUI observation; exact operands not recorded. |
| BASE-AC-15 | BASE-NFR-05 | Clean checkout of the frozen commit. Follow the documented build and run steps. | Builds and launches, or prerequisites are documented. Record exact commands and exit status. | Build; clean clone | Pass | Evaluator reported cloning into a clean directory, checking out frozen commit `740c78de`, running `mvn -q test` successfully with exit code `0`, then running `mvn -q compile exec:java` and observing the GUI launch. | Evaluator-reported PowerShell execution; terminal log not attached. |
| BASE-AC-16 | Regression/usability | Run three or more different calculations in sequence without restarting. | Each result matches the current operands and operation. | GUI; stateful | Pass | Evaluator reported that sequential calculations returned expected results without restarting the application. | Evaluator-reported GUI observation; exact calculation sequence not recorded. |
| BASE-AC-17 | BASE-FR-06 | Two valid numbers, no operation selected, click Calculate. | Error (suggested "Select an operation"); no result; no crash. | GUI | Pass | The calculator displayed an error requesting operation selection. | Evaluator-reported GUI observation; no separate artifact. |
| BASE-AC-18 | BASE-FR-04, BASE-FR-07 | (a) `0 / 5`, (b) `1 / 3`, (c) `1 / 2048`, (d) `-1 / 2048`. | (a) `0`; (b) `0.3333333333`; (c) `0.0004882813`; (d) `-0.0004882813` | GUI; 4 subcases | Pass | All four division and rounding subcases reported Pass. Evaluator stated that the calculator returned the correct results. | Evaluator-reported GUI observations; exact displayed strings not separately archived. |
| BASE-AC-19 | BASE-FR-04 | Two valid numbers, select an operation, press Enter in an operand field. | Same result as the Calculate button. Record which field was used. | GUI | Pass | Evaluator reported that Enter-key calculation worked correctly. The operand field used was not specified. | Evaluator-reported GUI observation; field detail and screenshot not provided. |

### Required subcase outcomes

The following subcases were individually marked **Pass** by the evaluator.

| Parent AC | Subcase | Input / condition | Outcome |
|---|---|---|---|
| BASE-AC-11 | a | First number empty | Pass |
| BASE-AC-11 | b | Second number empty | Pass |
| BASE-AC-11 | c | Both numbers empty | Pass |
| BASE-AC-11 | d | Spaces-only operand | Pass |
| BASE-AC-12 | 1 | `abc` | Pass |
| BASE-AC-12 | 2 | `1.2.3` | Pass |
| BASE-AC-12 | 3 | `1,5` | Pass |
| BASE-AC-12 | 4 | `1e3` | Pass |
| BASE-AC-12 | 5 | `--1` | Pass |
| BASE-AC-12 | 6 | `.` | Pass |
| BASE-AC-12 | 7 | `.5` | Pass |
| BASE-AC-12 | 8 | `5.` | Pass |
| BASE-AC-12 | 9 | `-` | Pass |
| BASE-AC-13 | a | `7 / 0` | Pass |
| BASE-AC-13 | b | `7 / 0.0` | Pass |
| BASE-AC-18 | a | `0 / 5` | Pass |
| BASE-AC-18 | b | `1 / 3` | Pass |
| BASE-AC-18 | c | `1 / 2048` | Pass |
| BASE-AC-18 | d | `-1 / 2048` | Pass |

---

## Summary (fill in after the run)

| P | F | B | N | D | Verified pass coverage P/D | Decisive (P+F)/D | Complete C/D |
|---|---|---|---|---|---|---|---|
| 19 | 0 | 0 | 0 | 19 | 19/19 (100%) | 19/19 (100%) | 19/19 (100%) |

**Interpretation:**

- **P = 19:** All 19 parent acceptance criteria were reported Pass by the evaluator.
- **F = 0:** No failed criteria were reported.
- **B = 0:** No blocked criteria were reported.
- **N = 0:** No criteria were marked Not run.
- **D = 19:** Total frozen Baseline parent acceptance criteria.
- **C = 19:** All required subcases were marked with decisive outcomes.

The results establish **100% evaluator-reported acceptance pass coverage** for the BMAD Baseline snapshot.

These results are based on the named evaluator's submitted manual observations. They are not independently corroborated by attached screenshots or complete execution logs.

---

## Defects found in this run

| Defect ID | AC / subcase | Expected | Actual | Reproduction | Evidence | Discovery source |
|---|---|---|---|---|---|---|
| None reported | — | — | — | — | — | Independent evaluator's manual GUI testing |

**Defect count: 0 reported.**

No functional defects were reported during the 19 Baseline acceptance checks.

This does not establish that the application is defect-free outside the tested scope.

---

## Deviations and notes

| Date | Note |
|---|---|
| 2026-11-05 | Evaluator: Thanh. Independent manual evaluation of the BMAD Baseline calculator. |
| 2026-11-05 | Frozen snapshot: `740c78de062bb1edd8076e4a49dde768b0fe1e49` (`baseline-frozen`). |
| 2026-11-05 | All 19 parent acceptance criteria were reported Pass, including the required subcases for BASE-AC-11, BASE-AC-12, BASE-AC-13 and BASE-AC-18. |
| 2026-11-05 | Protocol draft-5 and rubric draft-5 were identified as the adopted documents based on evaluator confirmation. The formal adoption decision date/reference was not supplied. |
| 2026-11-05 | BASE-AC-01: GUI launch and an additional arithmetic calculation were reported successful, but the submitted observation did not explicitly document the disabled-network state. The offline condition requires confirmation or supplementary evidence. |
| 2026-11-05 | BASE-AC-15: Evaluator reported a successful clean checkout, `mvn -q test` with exit code `0`, and successful GUI launch using `mvn -q compile exec:java`. Raw terminal logs were not supplied. |
| 2026-11-05 | BASE-AC-19: Enter-key calculation was reported successful; the specific operand field used was not recorded. |
| 2026-11-05 | No screenshots, saved GUI recordings or complete terminal logs were supplied with the evaluator's submitted results. The observations in this sheet are evaluator-reported manual evidence. |
| 2026-11-05 | The evaluation is limited to the BMAD Baseline. No Spec Kit results or CR-01 results are included. |
| 2026-11-05 | No reruns or changes to the frozen implementation were reported. |

---

## Evaluation conclusion

The BMAD Baseline calculator achieved **19/19 reported Pass outcomes (100%)** against the frozen Baseline acceptance criteria during Thanh's manual evaluation on 2026-11-05.

The reported observations cover arithmetic operations, numeric formatting, input validation, division-by-zero handling, error recovery, clearing, sequential use, keyboard interaction and clean-checkout build execution.

No failures, blocked tests or functional defects were reported.

The principal documentation limitations are the absence of attached execution artifacts, incomplete confirmation of the offline network condition for BASE-AC-01, and the missing operand-field detail for BASE-AC-19.

The acceptance outcomes are preserved as reported, while these limitations remain explicit for subsequent review.

**Evaluator:** Thanh

**Evaluation date:** 2026-11-05

**Track:** BMAD Baseline

**Snapshot:** `740c78de062bb1edd8076e4a49dde768b0fe1e49`

**Final reported outcome:** 19 Pass / 0 Fail / 0 Blocked / 0 Not run