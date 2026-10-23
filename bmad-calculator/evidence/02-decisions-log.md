# Decisions Log — Varun / BMAD Track (Baseline)

These resolve the open decisions in `shared/baseline-requirements.md` §9. They are **PROPOSED** on behalf of Varun and must be confirmed by the team (and aligned with George's track) before the shared docs are frozen. No shared file has been edited.

| ID | Decision | Status | Affects | Rationale |
|---|---|---|---|---|
| D-01 | GUI pattern: two operand text fields, four operation buttons (+, −, ×, ÷), a "Calculate" button, a "Clear" button, a result label and an inline error label | Proposed | BASE-FR-02/03/04/05, AC-02/03/11/12 | Text entry allows testing of empty/malformed input |
| D-02 | Calculation is triggered by the Calculate button **and** the Enter key | Proposed | BASE-FR-04 | Supports both keyboard and mouse use |
| D-03 | Numeric type `BigDecimal`; division scale 10, `RoundingMode.HALF_UP`; trailing zeros stripped; no scientific notation in display | Proposed | AC-07, AC-08, AC-09 | `7/2 → 3.5`, `1.2+2.3 → 3.5`, no binary-float artefacts |
| D-04 | Errors shown in an inline label (no dialogs). Messages: "Enter both numbers", "Select an operation" (D-07), "Invalid number", "Cannot divide by zero" | Proposed | BASE-FR-06/07/08, AC-10–14 | Observable, testable, non-blocking |
| D-05 | Java 22 (Corretto), Maven 3.9.16, Swing, JUnit Jupiter 6.1.3 (D-05 amended: 6.x is current; was written as JUnit 5); `JAVA_HOME` pinned explicitly | Proposed | BASE-NFR-01/05, AC-15 | Locally available; no extra GUI dependency |
| D-06 | Process: stop for human approval after planning artefacts, before writing code | Confirmed (user, 2026-10-23) | Master prompt | Matches README master prompt |

## Open ambiguities noticed (not resolved here)

- A-01: `project.md` assigns George = Spec Kit, Thanh = research. Top-level `ReadMe.md` assigns Pham Thanh = BMAD, Amin = Spec Kit. Inconsistent team-role description; relevant for the report.
- A-02: AC-01 requires "GUI opens without network access" — verifiable manually only (headless CI cannot open a Swing window). Needs a team decision on how to evidence it (screenshot/manual note).
- A-03: AC-12 "if the UI permits text entry" — D-01 permits text entry, so AC-12 applies to this track; George's track may differ unless the pattern is shared.
- A-04: Whether the input accepts a leading `+`, exponent notation (`1e3`), or locale comma decimals (`1,5`) is unspecified. Proposed: only optional leading `-`, digits, optional single `.`; comma and exponent are "Invalid number".

## D-07 (2026-10-23, Varun)

| ID | Decision | Status | Affects | Rationale |
|---|---|---|---|---|
| D-07 | Calculate with no operation selected shows inline error "Select an operation" and no result. Error precedence: empty operands → no operation → invalid number | Proposed amendment to shared docs; Varun approved for the BMAD track; **must be agreed with George** | BASE-FR-06, AC-11 | Shared docs were silent (found during BMAD PRD step) |
