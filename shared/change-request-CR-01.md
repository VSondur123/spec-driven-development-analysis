# Change Request CR-01 — Scientific Operations

**Status:** Proposed — pending team approval
**Version:** 0.2
**Applies to:** BMAD and GitHub Spec Kit tracks
**Baseline:** `shared/baseline-requirements.md`
**Baseline acceptance criteria:** `shared/acceptance-criteria.md`

## 1. Purpose

CR-01 extends the existing Java GUI calculator with three scientific-style unary operations.

The purpose is to observe how each spec-driven development workflow handles a requirements change after completing the same baseline application.

Both tracks shall implement the same externally observable behaviour. Each track may use its tool's native workflow and implementation structure, provided it satisfies the shared requirements and acceptance criteria.

The change shall be introduced to both tracks only after the baseline phase has been completed and its results have been recorded.

## 2. Scope

### 2.1 Included operations

| Operation   | Control label | Definition                                |
| ----------- | ------------- | ----------------------------------------- |
| Square      | `x²`          | (x \times x)                              |
| Square root | `√x`          | Principal non-negative square root of (x) |
| Reciprocal  | `1/x`         | (1 \div x)                                |

These are unary operations: each operation requires only one numeric operand.

### 2.2 Excluded functionality

CR-01 does not include:

- Trigonometric functions such as sine, cosine, and tangent.
- Logarithms or exponential functions.
- Arbitrary powers or factorials.
- Graphing or calculation history.
- Memory buttons, variables, or symbolic algebra.
- Degree/radian settings.
- Unrequested redesigns or features unrelated to these operations.

All existing baseline operations and behaviours remain in scope and must continue to work.

## 3. Functional requirements

| ID         | Requirement                                                                                                                                                |
| ---------- | ---------------------------------------------------------------------------------------------------------------------------------------------------------- |
| `CR-FR-01` | The application shall expose identifiable GUI controls for square, square root, and reciprocal.                                                            |
| `CR-FR-02` | Each unary operation shall use the value in the First number field. A second operand shall not be required.                                                |
| `CR-FR-03` | The user shall be able to select a unary operation and execute it using the existing Calculate interaction, including the supported Enter-key interaction. |
| `CR-FR-04` | Square shall calculate the square of a valid input.                                                                                                        |
| `CR-FR-05` | Square root shall calculate the principal square root of a non-negative input.                                                                             |
| `CR-FR-06` | Square root shall reject negative inputs with understandable error feedback.                                                                               |
| `CR-FR-07` | Reciprocal shall calculate the reciprocal of a non-zero input.                                                                                             |
| `CR-FR-08` | Reciprocal shall reject zero with understandable error feedback.                                                                                           |
| `CR-FR-09` | Empty or malformed input shall be rejected without crashing or presenting a misleading result.                                                             |
| `CR-FR-10` | After a unary-operation error, the user shall be able to correct the input or clear the calculator and continue.                                           |
| `CR-FR-11` | All baseline requirements and acceptance criteria shall remain applicable.                                                                                 |

## 4. Input and result policy

1. The new operations shall use the baseline input grammar and numeric representation.
2. The unary operation shall use the First number field.
3. The Second number field may remain empty and shall not be required for unary calculations.
4. If the Second number field contains a value, that value shall not affect the result of a unary calculation.
5. Square shall use exact decimal arithmetic for the square of the accepted input, without unnecessary rounding.
6. Square root shall produce a result rounded to 10 decimal places using HALF_UP.
7. Reciprocal shall use the same division policy as baseline division: 10 decimal places using HALF_UP.
8. Results shall use plain decimal notation, with trailing zeros removed and zero displayed as `0`.
9. The implementation shall use a deterministic square-root calculation that produces the specified rounded result.
10. The existing baseline input grammar remains applicable. CR-01 does not introduce a separate numeric-input format.

### 4.1 Expected numeric behaviour

| Operation   | Input | Expected result |
| ----------- | ----: | --------------: |
| Square      |  `-3` |             `9` |
| Square      | `1.2` |          `1.44` |
| Square      |   `0` |             `0` |
| Square root |   `9` |             `3` |
| Square root |   `2` |  `1.4142135624` |
| Square root |   `0` |             `0` |
| Reciprocal  |   `4` |          `0.25` |
| Reciprocal  |   `3` |  `0.3333333333` |
| Reciprocal  |  `-4` |         `-0.25` |

## 5. Error handling

- Empty input shall use the agreed baseline empty-input feedback.

- Malformed input shall use the agreed baseline invalid-number feedback.

- A negative square-root input shall produce the inline error:

  `Cannot take square root of a negative number`

- A zero reciprocal input shall produce the inline error:

  `Cannot divide by zero`

- Zero detection shall include `0`, `0.0`, and `-0.0`.

- Negative zero shall be treated as zero for reciprocal calculations.

- Errors shall not be displayed as successful numeric results.

- Error feedback shall follow the baseline error-presentation policy.

- After an error, the user shall be able to correct the input and calculate again.

- The application shall not crash when an invalid or mathematically unsupported unary operation is requested.

## 6. CR-01 acceptance criteria

These criteria are proposed additions to `shared/acceptance-criteria.md`. The team shall review and freeze them together with this change request.

| Test ID    | Test procedure                                                                                              | Expected result                                                                                                      |
| ---------- | ----------------------------------------------------------------------------------------------------------- | -------------------------------------------------------------------------------------------------------------------- |
| `CR-AC-01` | Inspect the GUI after launching the application.                                                            | Square, square-root, and reciprocal controls are visible and identifiable.                                           |
| `CR-AC-02` | Select square, enter `-3` in First number, leave Second number empty, and calculate.                        | Result is `9`.                                                                                                       |
| `CR-AC-03` | Select square, enter `1.2`, and calculate.                                                                  | Result is `1.44`.                                                                                                    |
| `CR-AC-04` | Select square, enter `0`, and calculate.                                                                    | Result is `0`.                                                                                                       |
| `CR-AC-05` | Select square root, enter `9`, and calculate.                                                               | Result is `3`.                                                                                                       |
| `CR-AC-06` | Select square root, enter `2`, and calculate.                                                               | Result is `1.4142135624`.                                                                                            |
| `CR-AC-07` | Select square root, enter `0`, and calculate.                                                               | Result is `0`.                                                                                                       |
| `CR-AC-08` | Select square root, enter `-1`, and calculate.                                                              | The specified negative-input error appears; no successful result is shown.                                           |
| `CR-AC-09` | Select reciprocal, enter `4`, and calculate.                                                                | Result is `0.25`.                                                                                                    |
| `CR-AC-10` | Select reciprocal, enter `3`, and calculate.                                                                | Result is `0.3333333333`.                                                                                            |
| `CR-AC-11` | Select reciprocal, enter `-4`, and calculate.                                                               | Result is `-0.25`.                                                                                                   |
| `CR-AC-12` | Select reciprocal, enter `0`, and calculate.                                                                | The baseline division-by-zero error appears; no successful result is shown.                                          |
| `CR-AC-13` | Repeat CR-AC-12 with `0.0` and `-0.0`.                                                                      | Each input is treated as zero and rejected safely.                                                                   |
| `CR-AC-14` | Select each unary operation with an empty First number field and calculate.                                 | Clear validation feedback appears; the application does not crash.                                                   |
| `CR-AC-15` | Select each unary operation and enter malformed input, such as `abc`.                                       | The baseline invalid-number feedback appears; the application does not crash.                                        |
| `CR-AC-16` | Select a unary operation, enter a valid First number, and leave Second number empty.                        | The calculation succeeds without requiring a second operand.                                                         |
| `CR-AC-17` | Select a unary operation, enter a valid First number and a different value in Second number, and calculate. | The result depends only on First number.                                                                             |
| `CR-AC-18` | Trigger a negative square-root error, correct the input, and calculate again.                               | The application recovers and displays the correct result.                                                            |
| `CR-AC-19` | Trigger a reciprocal division-by-zero error, correct the input, and calculate again.                        | The application recovers and displays the correct result.                                                            |
| `CR-AC-20` | Use Clear after a unary calculation.                                                                        | The calculator returns to the agreed baseline initial state.                                                         |
| `CR-AC-21` | Execute all `BASE-AC-01` through `BASE-AC-19` checks against the changed application.                       | All baseline criteria retain their required behaviour. Each result is recorded individually.                         |
| `CR-AC-22` | Follow the documented build and test instructions for the changed application.                              | The application builds and the tests execute. Environment prerequisites, test outcomes, and failures are documented. |

## 7. Compatibility and regression

1. CR-01 shall not remove or weaken any baseline requirement.
2. All existing baseline operations shall remain available.
3. Existing baseline validation, error handling, result formatting, and Clear behaviour shall remain applicable.
4. The team shall execute the baseline acceptance criteria against the changed application.
5. Baseline regression results shall be recorded individually rather than reported as a single aggregate pass.
6. Any regression or unintended behaviour change shall be documented and addressed before the track is considered complete.

## 8. Change-control rules

- This change request applies equally to both tracks.
- Neither track shall implement CR-01 before the team approves and freezes this document and the corresponding acceptance criteria.
- Existing baseline acceptance criteria shall not be removed or weakened to accommodate CR-01.
- The team shall record any approved amendment in `shared/experiment-log.md`.
- Any amendment shall be applied consistently to both tracks before implementation.
- Track-specific implementation artefacts shall remain separate. Generated specifications, designs, code, and tests shall not be copied between tracks.
- Each track shall preserve its own evidence of requirements refinement, planning, design, implementation, testing, and review.

## 9. Freeze checklist

- [x] The team approves the three operations.
- [x] The team approves the unary interaction and First number field behaviour.
- [x] The team approves the numeric precision and rounding policy.
- [x] The team approves error messages and domain-error behaviour.
- [x] The team approves `CR-AC-01` through `CR-AC-22`.
- [x] The CR-01 acceptance criteria are added to `shared/acceptance-criteria.md`.
- [x] The team records approval and the frozen version in `shared/experiment-log.md`.
- [x] Both tracks receive the same frozen change request and acceptance criteria.
- [x] Baseline implementation and verification evidence have been recorded for both tracks.
- [x] Implementation begins only after the team releases CR-01.

## 10. Document history

| Version | Status   | Description                                                                                                  |
| ------- | -------- | ------------------------------------------------------------------------------------------------------------ |
| 0.1     | Proposed | Initial CR-01 scope and acceptance criteria.                                                                 |
| 0.2     | Proposed | Clarified unary interaction, numeric behaviour, boundary cases, error recovery, and regression verification. |
