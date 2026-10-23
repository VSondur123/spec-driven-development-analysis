# Our Experiment Log

**Purpose:** We record study-level decisions, ambiguities and deviations here, so both tracks (Varun/BMAD and George/Spec Kit) and the research members work from the same record. Track-specific evidence stays in each track's own `evidence/` folder.

**Rules for this log**
- Append new entries; do not rewrite or delete another member's entry. Respond in the *Responses* column or add a new dated entry.
- An entry marked **Proposed** is not binding on any track until the team marks it **Agreed**.
- Once agreed, the amendment is applied to the shared documents for both tracks at the same time, as `project.md` §2 and §7 require. Until then neither track should treat it as a requirement.
- Do not copy generated artefacts (specs, designs, code) between tracks. This log holds only requirement-level decisions.

---

## Section A — Open decisions required before freezing (from `baseline-requirements.md` §9)

Raised by: Varun (BMAD track), 2026-10-23, while planning the baseline. Status: **Proposed — awaiting team agreement.** These are Varun's proposals; George and others should answer or counter-propose.

| ID | Open decision (§9) | Proposal | Documents affected if agreed | Responses |
|---|---|---|---|---|
| LOG-01 | GUI interaction pattern | Two labelled operand text fields; four operation buttons (+, −, ×, ÷) with the selected one visibly indicated; a Calculate button; a Clear button; a result area; an inline error area. | `baseline-requirements.md` §5; AC-02, AC-03, AC-11, AC-12 (these assume text entry is possible) | George: _ |
| LOG-02 | Calculation trigger | Calculate button **and** Enter key (Enter pressed in an operand field). | `baseline-requirements.md` BASE-FR-04 | George: _ |
| LOG-03 | Numeric precision and rounding | Exact decimal arithmetic. Division rounded half-up to 10 decimal places. Results shown as plain decimal text: no trailing zeros, no exponent notation, zero shown as `0`. Examples: `7/2 → 3.5`; `1.2+2.3 → 3.5`; `1/3 → 0.3333333333`; `2/2 → 1`. | `baseline-requirements.md` §4; AC-07, AC-09 | George: _ |
| LOG-04 | Error presentation and wording | Errors are shown inline (no dialogs). Messages: "Enter both numbers", "Invalid number", "Cannot divide by zero" (plus LOG-05). Any wording is acceptable to the acceptance checks as long as each case is clearly distinguishable; exact strings are only needed if we want identical wording across tracks. | `baseline-requirements.md` §9; AC-11, AC-12, AC-13 | George: _ |
| LOG-05 | **Gap found:** behaviour when Calculate is requested and no operation has been selected | The shared documents do not define it. Proposal: show inline error "Select an operation" and no result. Error priority when several faults exist: empty operands, then no operation selected, then invalid number. Alternatives considered: default the initial operation to `+` (changes the initial state), or disable Calculate until an operation is chosen. Varun chose the error-message option for the BMAD track. | `baseline-requirements.md` BASE-FR-06 and the initial state in BASE-FR-05; AC-10, AC-11 (suggest a new check, e.g. `BASE-AC-17`) | George: _ |
| LOG-06 | Accepted input grammar | Accepted: optional leading `-`, digits, optionally one `.` followed by digits; surrounding spaces ignored. Rejected as "Invalid number": leading `+`, exponent notation (`1e3`), comma decimals (`1,5`), thousands separators, `.` alone, `1.2.3`. Suggested examples for AC-12: `abc`, `1.2.3`, `1,5`, `1e3`, `--1`, `.`. | `baseline-requirements.md` §4 item 6; AC-12 | George: _ |
| LOG-07 | Java version, build tool and evaluation environment | Each track records its own toolchain (allowed by `baseline-requirements.md` §7). Suggested common evaluation environment so results are comparable: macOS 15.x, Amazon Corretto 22, Maven 3.9.x, with `JAVA_HOME` set explicitly. Note: on Varun's machine plain `mvn` picked a different JDK (Homebrew OpenJDK 26) than `java` (Corretto 22) because `JAVA_HOME` was unset, so a pinned `JAVA_HOME` is recommended in both tracks' documented commands. | `baseline-requirements.md` §7, BASE-NFR-01; AC-15 | George: _ |
| LOG-08 | How to evidence AC-01 ("GUI opens without network access") | This can only be observed manually on a machine with a display. Proposal: record a screenshot or short manual-test note taken with networking disabled, and mark the test as manual in the result template. | `acceptance-criteria.md` §1 and AC-01 | George: _ |

### Where the proposals have been applied

The proposals are written into the shared documents themselves, tagged `[PROPOSED LOG-nn]` (uncommitted until the team agrees; use `git diff shared/`):

- `shared/baseline-requirements.md`: BASE-FR-04 (LOG-02), BASE-FR-05 and BASE-FR-06 (LOG-05), §4 items 8–10 (LOG-03, LOG-05, LOG-06), §5 interaction pattern (LOG-01, LOG-04), §9 status note.
- `shared/acceptance-criteria.md`: reworded BASE-AC-01 (LOG-08), AC-07, AC-09, AC-11, AC-12, AC-13; new checks `BASE-AC-17` (no operation selected, LOG-05), `BASE-AC-18` (`0 / 5` and `1 / 3`, LOG-03), `BASE-AC-19` (Enter key, LOG-02).
- LOG-07 (Java/build environment) is not written into the documents; it stays here as a recommendation.

To reject a proposal, revert the tagged text and mark the LOG entry *Rejected*. To accept, delete the tag and mark it *Agreed*.

---

## Section B — Inconsistencies in the project documents (for the report)

| ID | Observation | Raised by / date | Responses |
|---|---|---|---|
| LOG-09 | Team roles differ between `project.md` (Varun = BMAD, George = Spec Kit, Thanh = frameworks/metrics, Amin = methodology) and the top-level `ReadMe.md` (Pham Thanh = BMAD, Amin = Spec Kit, Georgios = comparison framework, Varun = literature review). Please confirm which is current so the report describes roles correctly. | Varun, 2026-10-23 | _ |
| LOG-10 | `experiment-protocol.md` and `evaluation-rubric.md` are empty, so neither track's README-referenced "once frozen" inputs exist yet. Both tracks proceeded using only the baseline requirements and acceptance criteria. | Varun, 2026-10-23 | _ |
| LOG-11 | The shared documents are still marked "Draft ... we will freeze it before either implementation begins". The BMAD track started **planning** (not coding) before the freeze, to surface the ambiguities above. Varun's coding is held until the plan is approved; the team should decide whether the baseline documents are frozen before coding starts on either track. | Varun, 2026-10-23 | _ |

---

## Section C — Track progress notes (no requirement impact)

| Date | Track | Note |
|---|---|---|
| 2026-10-23 | Varun / BMAD | BMAD 6.12.4 installed. Planning artefacts (PRD, architecture, epics/stories, sprint plan) produced; coding not started; baseline not frozen. Details in `bmad-calculator/evidence/`. Change request CR-01 has **not** been read. |

---

## How the other track's agent should use this log

1. Read Section A and B before planning, and treat Section A items as **proposals, not requirements** until they are marked Agreed.
2. If your track hits the same gaps, record your own response in the Responses column rather than silently choosing differently, so that both tracks end up with identical observable behaviour.
3. Do not read the other track's `evidence/` or generated artefacts; this log is the only intended channel.
