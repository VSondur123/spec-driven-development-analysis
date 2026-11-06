# Our Experiment Log

**Purpose:** We record study-level decisions, ambiguities and deviations here, so both tracks (Varun/BMAD and George/Spec Kit) and the research members work from the same record. Track-specific evidence stays in each track's own `evidence/` folder.

**Rules for this log**
- Append new entries; do not rewrite or delete another member's entry. Respond in the *Responses* column or add a new dated entry.
- An entry marked **Proposed** is not binding on any track until the team marks it **Agreed**.
- Once agreed, the amendment is applied to the shared documents for both tracks at the same time, as `project.md` §2 and §7 require. Until then neither track should treat it as a requirement.
- Do not copy generated artefacts (specs, designs, code) between tracks. This log holds only requirement-level decisions.

---

## Section A — Open decisions required before freezing (from `baseline-requirements.md` §9)

Raised by: Varun (BMAD track), 2026-10-23, while planning the baseline. Status: **Agreed (Varun and George, confirmed by Varun on 2026-11-03); applied to `acceptance-criteria.md` on that date.** These are Varun's proposals; George and others should answer or counter-propose.

| ID | Open decision (§9) | Proposal | Documents affected if agreed | Responses |
|---|---|---|---|---|
| LOG-01 | GUI interaction pattern | Two labelled operand text fields; four operation buttons (+, −, ×, ÷) with the selected one visibly indicated; a Calculate button; a Clear button; a result area; an inline error area. | `baseline-requirements.md` §5; AC-02, AC-03, AC-11, AC-12 (these assume text entry is possible) | George: Agree |
| LOG-02 | Calculation trigger | Calculate button **and** Enter key (Enter pressed in an operand field). | `baseline-requirements.md` BASE-FR-04 | George: Agree |
| LOG-03 | Numeric precision and rounding | Exact decimal arithmetic. Division rounded half-up to 10 decimal places. Results shown as plain decimal text: no trailing zeros, no exponent notation, zero shown as `0`. Examples: `7/2 → 3.5`; `1.2+2.3 → 3.5`; `1/3 → 0.3333333333`; `2/2 → 1`. | `baseline-requirements.md` §4; AC-07, AC-09 | George: Agree |
| LOG-04 | Error presentation and wording | Errors are shown inline (no dialogs). Messages: "Enter both numbers", "Invalid number", "Cannot divide by zero" (plus LOG-05). Any wording is acceptable to the acceptance checks as long as each case is clearly distinguishable; exact strings are only needed if we want identical wording across tracks. | `baseline-requirements.md` §9; AC-11, AC-12, AC-13 | George: Agree |
| LOG-05 | **Gap found:** behaviour when Calculate is requested and no operation has been selected | The shared documents do not define it. Proposal: show inline error "Select an operation" and no result. Error priority when several faults exist: empty operands, then no operation selected, then invalid number. Alternatives considered: default the initial operation to `+` (changes the initial state), or disable Calculate until an operation is chosen. Varun chose the error-message option for the BMAD track. | `baseline-requirements.md` BASE-FR-06 and the initial state in BASE-FR-05; AC-10, AC-11 (suggest a new check, e.g. `BASE-AC-17`) | George: Agree |
| LOG-06 | Accepted input grammar | Accepted: optional leading `-`, digits, optionally one `.` followed by digits; surrounding spaces ignored. Rejected as "Invalid number": leading `+`, exponent notation (`1e3`), comma decimals (`1,5`), thousands separators, `.` alone, `1.2.3`. Suggested examples for AC-12: `abc`, `1.2.3`, `1,5`, `1e3`, `--1`, `.`. | `baseline-requirements.md` §4 item 6; AC-12 | George: Agree |
| LOG-07 | Java version, build tool and evaluation environment | Each track records its own toolchain (allowed by `baseline-requirements.md` §7). Suggested common evaluation environment so results are comparable: macOS 15.x, Amazon Corretto 22, Maven 3.9.x, with `JAVA_HOME` set explicitly. Note: on Varun's machine plain `mvn` picked a different JDK (Homebrew OpenJDK 26) than `java` (Corretto 22) because `JAVA_HOME` was unset, so a pinned `JAVA_HOME` is recommended in both tracks' documented commands. | `baseline-requirements.md` §7, BASE-NFR-01; AC-15 | George: Agree, except OS. I use Windows 11 (Corretto 22, Maven 3.9.16, `JAVA_HOME` pinned). |
| LOG-08 | How to evidence AC-01 ("GUI opens without network access") | This can only be observed manually on a machine with a display. Proposal: record a screenshot or short manual-test note taken with networking disabled, and mark the test as manual in the result template. | `acceptance-criteria.md` §1 and AC-01 | George: Agree |

### Where the proposals have been applied

The proposals are written into the shared documents themselves, tagged `[PROPOSED LOG-nn]` until the baseline freeze on 2026-10-23, when the tags were removed (see Section D):

- `shared/baseline-requirements.md`: BASE-FR-04 (LOG-02), BASE-FR-05 and BASE-FR-06 (LOG-05), §4 items 8–10 (LOG-03, LOG-05, LOG-06), §5 interaction pattern (LOG-01, LOG-04), §9 status note.
- `shared/acceptance-criteria.md`: reworded BASE-AC-01 (LOG-08), AC-07, AC-09, AC-11, AC-12, AC-13; new checks `BASE-AC-17` (no operation selected, LOG-05), `BASE-AC-18` (`0 / 5` and `1 / 3`, LOG-03), `BASE-AC-19` (Enter key, LOG-02).
- LOG-07 (Java/build environment) is not written into the documents; it stays here as a recommendation.

To reject a proposal, revert the tagged text and mark the LOG entry *Rejected*. To accept, delete the tag and mark it *Agreed*. Section D records what happened to each one.

---

## Section B — Inconsistencies in the project documents (for the report)

| ID | Observation | Raised by / date | Responses |
|---|---|---|---|
| LOG-09 | Team roles differ between `project.md` (Varun = BMAD, George = Spec Kit, Thanh = frameworks/metrics, Amin = methodology) and the top-level `ReadMe.md` (Pham Thanh = BMAD, Amin = Spec Kit, Georgios = comparison framework, Varun = literature review). Please confirm which is current so the report describes roles correctly. | Varun, 2026-10-23 | George: Agree. `project.md` is current; `ReadMe.md` is the initial plan. |
| LOG-10 | `experiment-protocol.md` and `evaluation-rubric.md` are empty, so neither track's README-referenced "once frozen" inputs exist yet. Both tracks proceeded using only the baseline requirements and acceptance criteria. | Varun, 2026-10-23 | George: Confirmed. The Spec Kit track also works without them, using only the baseline requirements and acceptance criteria. |
| LOG-11 | The shared documents are still marked "Draft ... we will freeze it before either implementation begins". The BMAD track started **planning** (not coding) before the freeze, to surface the ambiguities above. Varun's coding is held until the plan is approved; the team should decide whether the baseline documents are frozen before coding starts on either track. | Varun, 2026-10-23 | George: Noted. The baseline was already frozen when the Spec Kit track started. |

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

---

## Section D — Baseline freeze and implementation record (2026-10-23, Varun)

The owner froze `baseline-requirements.md`, `acceptance-criteria.md` (baseline checks) and this log together with the BMAD baseline implementation, and removed the `[PROPOSED LOG-nn]` tags. The "George" column in Section A was still empty at that time; the agreement recorded below was the owner's decision. George agreed to LOG-01 to LOG-08 on 2026-10-27 (see Section A).

| ID | Before (shared docs, original draft) | Proposed and now frozen | Implemented in BMAD track (decision, story, evidence) |
|---|---|---|---|
| LOG-01 | GUI pattern left open | Two operand fields, four operation buttons, Calculate, Clear, result area, inline error area | D-01, Story 1.3; AC-02/03 Pass; screenshots `bmad-calculator/evidence/screenshot/` |
| LOG-02 | Calculate trigger left open | Calculate button and Enter key | D-02, Story 1.3; AC-19 Pass |
| LOG-03 | Precision and rounding left open | Exact decimals, division half-up to 10 places, plain text, no trailing zeros | D-03, Story 1.2; AC-07, 09, 18 Pass |
| LOG-04 | Error wording and place left open | Inline errors, suggested messages | D-04, Stories 1.2, 1.3, 2.x; AC-11, 12, 13 Pass |
| LOG-05 | No operation selected: undefined (gap) | Inline "Select an operation"; error priority; new BASE-AC-17 | D-07, Story 1.2; AC-17 Pass |
| LOG-06 | Input grammar left open | Optional `-`, digits, one `.`; reject `+1`, `1e3`, `1,5`, `.`, `1.2.3` | Story 1.2; AC-12 Pass |
| LOG-07 | Toolchain per track | Recommendation only, not written into shared docs | Corretto 22, Maven 3.9.16, `JAVA_HOME` pinned (D-05) |
| LOG-08 | AC-01 evidence method open | Manual check, screenshot or note, networking off | `ac-01-manual-check.md`; screenshots add/substract/error, owner-reported Wi-Fi off, commit `943c73f` |

LOG-09 to LOG-11 stay open (roles, empty protocol and rubric files, pre-freeze planning). F-09 (whether a shown result or error should clear on edit) was not part of the freeze; it is now covered by LOG-13 (Section E). Update 2026-10-27: George answered LOG-09 to LOG-11 (Section B).

---

## Section E — Gaps found by the Spec Kit track while writing the specification (2026-10-27, George)

Raised by: George (Spec Kit track), 2026-10-27, during `/speckit-specify`. Status: **Proposed — awaiting team agreement.** The owner chose these answers for the Spec Kit track only so that work could continue. Neither `baseline-requirements.md` nor `acceptance-criteria.md` has been changed. Until the team agrees, they apply to no other track. If the team picks differently, the Spec Kit spec will be revised and the change logged.

| ID | Gap | Proposal | Documents affected if agreed | Responses |
|---|---|---|---|---|
| LOG-12 | The accepted-input grammar (§4 items 6 and 9, LOG-06) does not say how `.5`, `5.`, `-` alone, or a spaces-only field are classified. | Apply the grammar literally. `.5`, `5.` and `-` alone are "Invalid number". A field with only spaces counts as empty ("Enter both numbers"). Suggest adding these cases to `BASE-AC-12` and `BASE-AC-11`. | `baseline-requirements.md` §4 items 6 and 9; `BASE-AC-11`, `BASE-AC-12` | George: proposed; **Agreed 2026-11-03**. Varun: Agree. BMAD already behaves this way (`.5`, `5.`, `-`, `-.5`, `-1.` rejected; spaces-only is empty); tests in `CalculatorTest` and the interaction tests. |
| LOG-13 | The documents do not say when a result or error already shown is removed (the open item F-09 in Section D). | A shown result or error stays until the next Calculate or Clear; editing an operand or changing the operation does not remove it. | `baseline-requirements.md` `BASE-FR-08`; `BASE-AC-14`, `BASE-AC-16` | George: proposed; **Agreed 2026-11-03**. Varun: Agree. BMAD already behaves this way (output stays until Calculate or Clear); this closes F-09. |
| LOG-14 | `BASE-NFR-02` ("promptly enough for interactive use") has no measurable limit. | Keep the wording and judge it by observation; no numeric limit. | `baseline-requirements.md` `BASE-NFR-02` | George: proposed; **Agreed 2026-11-03**. Varun: Agree. BMAD judges NFR-2 by observation only (not measured). |
| LOG-15 | `BASE-FR-04`, §4 item 8 and LOG-03 say division is "rounded half-up to 10 decimal places", but do not say how an exact tie rounds when the result is negative. Example: `-1 ÷ 2048 = -0.00048828125` has an exact tie at the 11th decimal. "Up" could mean away from zero (`-0.0004882813`) or toward the larger value (`-0.0004882812`). | Round ties away from zero, so the sign is kept and the size is rounded half-up. `-1 ÷ 2048` shows `-0.0004882813`, and `1 ÷ 2048` shows `0.0004882813`. This is what Java's `RoundingMode.HALF_UP` does. Suggest adding this case to `BASE-AC-18`. The BMAD track should confirm what its app does, so both tracks match. | `baseline-requirements.md` §4 item 8; `BASE-AC-18` | George: proposed; **Agreed 2026-11-03**. Varun: Agree. Verified 2026-10-27: BMAD shows `-1 ÷ 2048` as `-0.0004882813` and `1 ÷ 2048` as `0.0004882813` (`RoundingMode.HALF_UP`); two cases added to `CalculatorTest.baseAc18_display_policy`, 174 run, 0 failed. |

**Status update 2026-10-27 (Varun):** Varun agreed to LOG-12 to LOG-15 (see the Responses column). Both owners now agree, but `baseline-requirements.md` and `acceptance-criteria.md` are frozen and have not been changed; whether to add the suggested cases to BASE-AC-11, 12 and 18 (and the matching wording) is still to be decided, and must be applied to both tracks at once. The BMAD implementation needed no code change; the only change is two test cases for LOG-15.

**Agreement 2026-11-03 (Varun):** LOG-12 to LOG-15 are agreed by both tracks' owners. The suggested test cases were added to `BASE-AC-11`, `BASE-AC-12` and `BASE-AC-18` in `shared/acceptance-criteria.md`. The wording of `baseline-requirements.md` was not changed; the AC rows carry the clarification. Please apply the same `acceptance-criteria.md` change on the Spec Kit side (it is one shared file, so merging this branch is enough).

---

## Section F — CR-01 release and gaps found by the BMAD track (2026-11-03, Varun)

Varun released CR-01 to both tracks on 2026-11-03 and started the BMAD change phase on branch `cr-01`. The BMAD track read `shared/change-request-CR-01.md` (v0.2) for the first time on that date. Gaps below were raised as **Proposed**. Status 2026-11-07: LOG-16 to LOG-21 are all **Agreed** by Varun and George (see Responses; George's LOG-16 response was recorded on his behalf by Varun's instruction).

| ID | Gap | Proposal | Documents affected if agreed | Responses |
|---|---|---|---|---|
| LOG-16 | The CR-01 freeze checklist (§9) ticks "CR-01 acceptance criteria are added to `shared/acceptance-criteria.md`" and "approval and frozen version recorded in the log", but `acceptance-criteria.md` contains only `BASE-AC-01..19`, and the header of `change-request-CR-01.md` still says "Proposed — pending team approval". | Treat §6 of CR-01 as the frozen CR acceptance criteria until they are copied into `acceptance-criteria.md`; update the CR-01 status line. | `change-request-CR-01.md` header; `acceptance-criteria.md` | Varun: Agree (2026-11-03): CR-AC-01..22 copied unchanged from CR-01 §6 into `acceptance-criteria.md` §3 by the BMAD track. The CR-01 header status line is not changed by us; to be updated by the owner. George: Agree (recorded on George's behalf by Varun's instruction, 2026-11-07, not entered by George himself). Outcome: CR-01 header status updated to Approved on 2026-11-07. |
| LOG-17 | Unary operations: the Second number field may contain a value that "shall not affect the result" (§4 item 4), but it is not said what happens if it contains malformed text (for example `abc`). | Ignore the Second number field completely for unary operations, malformed text included. | `change-request-CR-01.md` §4 items 3 and 4; `CR-AC-17` | Varun: Agree (2026-11-03, owner decision for the BMAD track). George: Agree (2026-11-04, owner decision for the Spec Kit track). |
| LOG-18 | Error priority for unary operations is not stated (empty First number vs no operation selected vs invalid vs domain error), and an empty Second number must not count as an empty operand. | Keep the baseline priority, applied to the First number only: empty operand, no operation, invalid number, domain error. | `change-request-CR-01.md` §5; `CR-AC-14`, `CR-AC-15` | Varun: Agree (2026-11-03, owner decision for the BMAD track). George: Agree (2026-11-04, owner decision for the Spec Kit track). |
| LOG-19 | The reciprocal of a very large non-zero value rounds to `0` under the 10-decimal HALF_UP policy (for example `1/30000000000`), which shows `0` for a non-zero input. The baseline has the same effect for division (`-1 ÷ 30000000000`). | Accept it as the stated policy; no new error. | `change-request-CR-01.md` §4 items 6 and 7 | Varun: Agree (2026-11-03, owner decision for the BMAD track). George: Agree (2026-11-04, owner decision for the Spec Kit track). |
| LOG-20 | The GUI layout for the three controls is not specified (for example whether the Second number field is disabled or hidden while a unary operation is selected, and whether selecting a unary operation clears a shown result). | Keep the Second number field visible and enabled; a shown result or error stays until the next Calculate or Clear (LOG-13). | `change-request-CR-01.md` §3 `CR-FR-01..03` | Varun: Agree (2026-11-03, owner decision for the BMAD track). George: Agree (2026-11-04, owner decision for the Spec Kit track). |
| LOG-21 | CR-01 §5 says empty input uses the baseline message "Enter both numbers", which reads oddly when a unary operation needs only one number. | Keep the mandated wording for both tracks; judge by observation. | `change-request-CR-01.md` §5; `CR-AC-14` | Varun: Agree (2026-11-03, owner decision for the BMAD track). George: Agree (2026-11-04, owner decision for the Spec Kit track). |

---

## Section G — Evaluator execution sheets and CR-01 snapshot (2026-11-05, Varun)

Status: **Proposed — awaiting George's response.** Nothing here changes a requirement, acceptance criterion or rubric.

| ID | Item | Proposal | Responses |
|---|---|---|---|
| LOG-22 | Independent evaluation needs the same procedure on both tracks. | Two blank, identical execution sheets were added to **both** tracks' `evidence/` folders: `07-evaluator-execution-sheet.md` (BASE-AC-01 to 19) and `08-cr01-evaluator-execution-sheet.md` (CR-AC-01 to 22). All outcomes are **Not run**; no operator result was copied into them. They restate `shared/acceptance-criteria.md`, which stays the authority. This is a cross-track write, done at the owner's request. | Varun: added 2026-11-05. George: |
| LOG-23 | Evaluator. | Thanh or Amin runs the sheets, because neither built a calculator. Name, role and prior exposure are recorded in each sheet's run record. Not yet confirmed by them (open protocol item D-05). | Thanh: Confirmed — BMAD Baseline evaluation completed (19/19 Pass, 2026-11-05). / Amin: Confirmed — Spec Kit baseline at `88f56d0` evaluated with Codex assistance on 2026-11-05: 18 Pass, 0 Fail, 1 Blocked (offline launch omitted after unsuccessful network setup, at Amin’s request); denominator 19. Native tests: 81 Pass. GUI evidence and limitations: `github-spec-kit-calculator/evidence/07-evaluator-execution-sheet.md`. |
| LOG-24 | BMAD CR-01 snapshot. | BMAD's CR-01 work is frozen at commit `72f2783` as local tag `bmad-cr01-frozen` (not pushed). `bmad-calculator/` is unchanged between `72f2783` and `main`. Baseline snapshot remains tag `baseline-frozen`. | Varun: tagged 2026-11-05 |
| LOG-25 | Spec Kit snapshots. | George to declare, with commit hash or tag: (a) the baseline snapshot, since results were recorded at `419f92f` and the branch tip was `88f56d0`; (b) the CR-01 snapshot once finished. Then update the Spec Kit track's sheets (run record, snapshot) after CR-01. Evaluation runs use the declared snapshots, not branch tips. Both tracks' sheets must stay identical in content. | George: |
| LOG-26 | Spec Kit baseline snapshot for evaluation. | Varun, as study owner, designates commit `88f56d0979e2fae1fb5d59e8681975e3a1fc2a27` (branch `george/spec-kit-baseline`) as the Spec Kit baseline snapshot for the evaluator run, so the evaluation can proceed. BMAD baseline stays tag `baseline-frozen` (`740c78de`). This is not a freeze declared by George: his own records call earlier commits interim and give no baseline freeze, and his operator results were recorded at `419f92f` and in an uncommitted tree, so differences between `419f92f` and `88f56d0` may exist. Recorded here as a limitation. Sheet `07-evaluator-execution-sheet.md` in both tracks names these two snapshots. | Varun: designated 2026-11-05. George: |
| LOG-27 | Independent BMAD CR-01 evaluation and baseline regression. | Thanh completed the independent BMAD CR-01 evaluation on 2026-11-06. All 22 CR-01 acceptance criteria were reported Pass (22 Pass, 0 Fail, 0 Blocked, 0 Not run). The 19 original baseline acceptance criteria were also reported Pass when rechecked against the updated CR-01 application, with no regressions reported. Environment: JDK 22, Maven 3.9.11. The calculator GUI launched successfully without crashing. Detailed automated test counts were not provided. Evidence: `bmad-calculator/evidence/08-cr01-evaluator-execution-sheet.md` and `bmad-calculator/evidence/09-baseline-regression-cr01.md`. | Thanh: Completed independent BMAD CR-01 evaluation and regression checks on 2026-11-06. |

## Section H — Independent Spec Kit CR-01 evaluation (2026-11-05, Amin with Codex assistance)

Frozen snapshot: `speckit-cr01-frozen` = `9e523775e31c42bb9f164ddf84e9ff736b2c2fdc`.

- CR-01: 21 Pass, 0 Fail, 1 Blocked, 0 Not run; denominator 22. CR-AC-21 is blocked because its offline baseline subcheck remains unverified under Amin’s instruction to skip offline launch.
- Separate baseline regression run on the changed app: 18 Pass, 0 Fail, 1 Blocked; denominator 19. Zero observed regressions among the 18 previously passing comparable criteria; offline regression status is undetermined. Original baseline record unchanged.
- Native clean build: 116 tests, 0 failures, 0 errors, 0 skipped. Separate real-window GUI automation supplied the acceptance observations, screenshots and actual outputs. AI assistance and prior baseline exposure are disclosed; this does not assert every scenario was manually executed by Amin.
- Results: `github-spec-kit-calculator/evidence/08-cr01-evaluator-execution-sheet.md` and `github-spec-kit-calculator/evidence/09-cr01-baseline-regression-sheet.md`. Raw evidence and reproduction: `research/empirical-methodology/evaluations/2026-11-05-spec-kit-cr01/`.
- BMAD CR-01 was not evaluated by Amin in this run. No requirements, rubric, native implementation or original baseline outcomes were changed. Protocol/rubric draft-5 adoption date/reference remains unavailable; no adoption history is invented.


## Section I — Amin’s manual offline-launch follow-up (reported 2026-11-05)

Amin subsequently reported successful Spec Kit offline launch and confirmed **both baseline and CR-01 passed**. Evidence/provenance: `research/empirical-methodology/evaluations/2026-11-05-spec-kit-cr01/offline-manual-follow-up.md` (MANUAL-OFFLINE-01). Exact observation times and procedural details were not supplied; no automated offline execution is asserted.

Updated combined outcomes: baseline **19 Pass / 0 Fail / 0 Blocked / 0 Not run**; CR-01 baseline regression **19 Pass / 0 Fail / 0 Blocked / 0 Not run**; CR-AC-21 **Pass**; CR-01 **22 Pass / 0 Fail / 0 Blocked / 0 Not run**. No observed regressions across the 19 paired baseline criteria, with offline observations human-reported. Earlier blocked attempts, initial totals, automated logs and screenshots are preserved. No acceptance requirements changed.
