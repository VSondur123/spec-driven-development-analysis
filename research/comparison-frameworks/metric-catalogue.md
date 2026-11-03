# Metric Catalogue and Operational Definitions

**Author:** Pham Thanh (Thanh)  
**Date:** 2026-11-02  
**Revision:** draft-6 (GQM integration, CR-01 status and cross-document alignment)  
**Status:** Proposal for team review. Nothing in this file is adopted unless agreed and entered into the authoritative shared documents. No track has been scored.

## 0. Authority and relationship to team work

This catalogue is supplementary. It must not become a second rubric or protocol.

| Material | Role and authority |
|---|---|
| `shared/baseline-requirements.md`, `shared/acceptance-criteria.md` | Shared requirement and AC inventory; source of truth for IDs and wording |
| `shared/evaluation-rubric.md`, `shared/experiment-protocol.md` | Intended authoritative evaluation rules once adopted |
| Amin's `evaluation-rubric-proposal.md`, `protocol-recommendations.md` | Draft proposals; a GitHub merge does not mean the team has adopted them |
| Amin's `evaluation-templates.md`, `evidence-checklist.md` | Proposed collection instruments |
| This catalogue | Supplementary GQM mapping, measures, counting units and operational definitions |
| `visualisation-proposal.md` | Supplementary CSV schemas and figure specifications |
| `literature-review.md`, `references.md` | Research context; neither sets evaluation rules |

If this catalogue conflicts with an adopted rubric or protocol, the adopted document wins. Both tracks should be evaluated using the same adopted shared rules; neither operator is required to imitate the other track's internal workflow.

The comparison unit is the workflow run under its recorded conditions, not an abstract tool in isolation. The study compares two tool–agent–operator workflows and should not be described as a fully controlled causal experiment.

## 1. GQM framework: goals, questions and metrics

### 1.1 Why GQM is used

This study uses the Goal–Question–Metric (GQM) approach as the organising structure for measurement. GQM starts with the goals of the evaluation, derives questions that make each goal answerable, and then selects measures that provide evidence for those questions.

The overall research design is a comparative case study using a mixed-methods evaluation framework. GQM structures the quantitative and qualitative evidence; it is not a separate research design and does not make the comparison a controlled experiment.

The GQM structure is intended to:
- connect each measure to a clear evaluation purpose;
- prevent collecting metrics simply because they are easy to count;
- make the relationship between research questions, evidence and conclusions traceable;
- distinguish quantitative outcomes from qualitative reviewer judgements;
- expose questions that cannot be answered because evidence is missing or the shared inputs are incomplete.

The GQM mapping below is based on the five working research questions in `project.md` §3. The wording and numbering must be checked against that file before the final report is submitted. If the team changes the research questions, update this mapping and the visualisation proposal together.

### 1.2 Overall measurement goal

**Overall goal:** Evaluate and compare how BMAD and GitHub Spec Kit support two recorded development workflows that use the same Java desktop GUI calculator requirements and evolve the same application, considering specification and traceability quality, change-impact handling, implementation and verification outcomes, human effort, and evidence reproducibility.

The goal is comparative and descriptive. It does not assume that either tool is superior and does not isolate the causal effect of the tool from operator, agent/model, operating system, configuration or other workflow differences.

### 1.3 GQM goals

| Goal | Goal statement | Main perspective | Related RQ |
|---|---|---|---|
| G1 — Specification and traceability | Evaluate how clearly each workflow represents the shared requirements in its native specification and development artefacts, and how well those requirements can be traced through the workflow | Evaluator reviewing artefacts against shared requirements | RQ1 |
| G2 — Change-impact handling | Evaluate how each workflow identifies and handles the requirements, components and dependencies affected by the planned application change | Evaluator comparing pre-implementation impact analysis with a frozen reference set and final evidence | RQ2 |
| G3 — Implementation and verification outcomes | Compare the observed implementation and verification outcomes, including acceptance results, defects and regressions | Evaluator observing the application and test evidence | RQ3 |
| G4 — Human work and effort | Describe the human interventions, interactions, active effort, elapsed duration and rework required in each recorded workflow | Operator records and evaluator-reviewed event/time evidence | RQ4 |
| G5 — Evidence and reproducibility | Assess whether the records, artefacts, environment details and evaluation evidence are sufficiently complete to support checking and interpretation of the comparison | Evaluator reviewing the evidence package and reproducibility information | RQ5 |

### 1.4 GQM questions and metric mapping

#### G1 — Specification and traceability

**Question 1.1:** To what extent are the shared requirements represented in each workflow's native specification and related artefacts?

- M03 — Native traceability.
- M13 — Reconstructed traceability, reported separately.
- M02 — Requirement status view, if adopted.
- M11 — Relevant rubric criteria J1–J4.

**Question 1.2:** To what extent can requirements be followed through the workflow stages from specification to verification?

- M03a — Native implementation traceability.
- M03b — Native verification traceability.
- M03c — Traceability assessment completion.
- M13 — Reconstructed links, as supplementary evidence.

**Question 1.3:** Are there shared requirements or acceptance checks that cannot be evaluated because the shared input inventory does not define a corresponding check?

- C1 — Shared input limitations.
- M02 — Requirement status view, if adopted.
- M01 — Acceptance outcomes, interpreted against the available shared AC inventory.

**Question 1.4:** How do reviewers judge the quality of the specification and related artefacts?

- M11 — J1–J4 ratings using the adopted rubric.
- M14 — Initial reviewer agreement, if adopted.

#### G2 — Change-impact handling

**Question 2.1:** Which elements from the agreed change-impact reference set were identified in each workflow's original impact analysis?

- M07 — Identified, omitted and unknown reference elements.
- M07b — Proposed impact precision, if adopted.

**Question 2.2:** Which relevant elements were omitted or discovered only after implementation or independent evaluation?

- M07 — Impact omissions and recall.
- M04 — Distinct defects.
- M05 — Late-discovery share, if adopted.

**Question 2.3:** How well do the change-related specifications, plans and implementation artefacts address the intended change?

- M11 — Relevant J5–J6 ratings.
- M01 — Change acceptance outcomes, only after the change inventory is approved and frozen.
- M04 — Change-phase defects.

**Question 2.4:** What evidence supports the conclusion that an identified impact was actually addressed?

- M03 — Native implementation and verification traceability.
- M13 — Reconstructed traceability, reported separately.
- M01 — Evaluator-observed acceptance results.

#### G3 — Implementation and verification outcomes

**Question 3.1:** How many shared parent acceptance criteria pass, fail, are blocked or are not run in each comparable phase?

- M01 — Acceptance outcomes.

**Question 3.2:** Which baseline acceptance criteria regress after the change, persist as failures, become resolved or show no observed regression?

- M06 — Paired baseline/change outcomes.
- M01 — Parent AC results used to derive the paired classification.

**Question 3.3:** What distinct defects are observed, and when and by whom are they discovered?

- M04 — Defects.
- M05 — Late-discovery share, if adopted.
- M12 — Rework episodes, where linked to defects or inconsistencies.

**Question 3.4:** How do reviewers judge the implementation and verification evidence?

- M11 — Relevant J1–J6 ratings.
- M14 — Reviewer agreement, if adopted.

#### G4 — Human work and effort

**Question 4.1:** What kinds and numbers of human intervention episodes occur in each workflow?

- M08 — Human interventions and interaction counts.

**Question 4.2:** How much active human effort is recorded for each workflow and phase?

- M09 — Measured active effort.

**Question 4.3:** What is the elapsed duration between the agreed workflow phase boundaries?

- M10 — Elapsed duration.

**Question 4.4:** How many documented rework episodes occur, and what defects or inconsistencies do they address?

- M12 — Rework episodes.
- M04 — Defects.

#### G5 — Evidence and reproducibility

**Question 5.1:** Is the evidence required to check each workflow's decisions and outcomes available and assessable?

- M15 — Evidence completeness, if adopted.
- Evidence checklist and evidence index.

**Question 5.2:** Can the evaluated application state and workflow environment be identified and reconstructed from the records?

- Snapshot and environment metadata in the proposed record tables.
- M15 — Evidence completeness, if adopted.
- M16 — Descriptive context only.

**Question 5.3:** Are the evaluation judgements supported by preserved source evidence and independent review?

- M11 — Initial and reconciled reviewer ratings.
- M14 — Exact initial reviewer agreement, if adopted.
- Evidence references and provenance fields.

**Question 5.4:** Which missing records, deviations or limitations weaken the interpretation of the comparison?

- M15 — Evidence completeness, if adopted.
- `deviations.csv`.
- C1 — Shared input limitations.
- Explicit Not run, Unavailable, Unassessed and Not calculable states.

### 1.5 GQM traceability table

The following table provides a compact mapping from goals and questions to measures. It is a planning map, not evidence that the measures have already been collected.

| Goal | Question | Main measures | Evidence |
|---|---|---|---|
| G1 | Q1.1–Q1.2 | M03, M13, optional M02 | Native artefacts, traceability records |
| G1 | Q1.3 | C1, M01, optional M02 | Shared requirements and AC inventory |
| G1 | Q1.4 | M11, optional M14 | Reviewer rating records |
| G2 | Q2.1–Q2.2 | M07, optional M07b and M05 | Frozen impact reference set, original analysis, defect records |
| G2 | Q2.3–Q2.4 | M11, M01, M03, M13, M04 | Change artefacts, acceptance observations, traceability records |
| G3 | Q3.1 | M01 | Evaluator-observed AC/subcase results |
| G3 | Q3.2 | M06 | Comparable baseline and change AC results |
| G3 | Q3.3 | M04, optional M05, M12 | Defect and event records |
| G3 | Q3.4 | M11, optional M14 | Reviewer ratings and rationale |
| G4 | Q4.1 | M08 | Event and session records |
| G4 | Q4.2 | M09 | Measured active-work intervals |
| G4 | Q4.3 | M10 | Agreed phase boundary timestamps |
| G4 | Q4.4 | M12, M04 | Rework events and linked defects |
| G5 | Q5.1 | Optional M15 | Evidence checklist and index |
| G5 | Q5.2 | Snapshot/environment metadata, optional M15/M16 | Commit hashes, environment and tool records |
| G5 | Q5.3 | M11, optional M14 | Reviewer records and source references |
| G5 | Q5.4 | Optional M15, deviations, C1 | Missing-data and deviation records |

### 1.6 How GQM is applied

1. Confirm the five research questions and their wording in `project.md` §3.
2. Agree the G1–G5 goal statements and question wording with the team.
3. Confirm which proposed measures are required, optional or excluded.
4. Agree operational definitions, data sources, denominators and status rules in the shared protocol.
5. Freeze the applicable shared requirements, ACs, required subcases and change reference set at the agreed points.
6. Collect operator-reported records separately from evaluator-observed outcomes.
7. Validate the records and preserve missing values and deviations.
8. Analyse each question using its mapped evidence; do not create an overall score from unrelated measures.
9. State when a question cannot be answered because the evidence or denominator is incomplete.

GQM does not itself prescribe the status precedence, rubric anchors, test plan, reviewer procedure or data-collection schedule. Those must be agreed in the authoritative shared rubric and protocol.

## 2. Shared input inventory and limits

The baseline inventory documented in the shared project files is:

- 13 requirements: 8 functional requirements (FR) and 5 non-functional requirements (NFR).
- 19 parent acceptance criteria (ACs).
- 9 requirement IDs have at least one linked AC (FR-01 to FR-08 and NFR-05).
- 4 NFR IDs (NFR-01 to NFR-04) have no linked AC.
- 3 ACs (AC-08, AC-09 and AC-16) have no requirement ID.

These are input-inventory facts, not performance results. Report the same shared-input limitation for both tracks. Do not penalise either workflow for an absent shared check unless the team adds and freezes one through the agreed change-control process. Recheck the inventory if the shared-input revision changes.

### CR-01 status and proposed change inventory

CR-01 was merged into `main` in commit `1e39815` on 2026-11-02. However, the CR-01 document header still describes it as **Proposed — pending team approval**, while its freeze checklist appears checked. The shared acceptance-criteria document also still indicates that the CR-01 criteria are pending incorporation.

Therefore, the merge must not be treated by itself as proof that CR-01 has been formally approved, released or adopted as the evaluation baseline. The team must resolve this status inconsistency and record the authoritative approval/freeze state.

The proposed CR-01 scope describes three scientific operations:

- square;
- square root;
- reciprocal.

The proposal contains 11 change requirements (`CR-FR-01` to `CR-FR-11`) and 22 proposed change acceptance criteria (`CR-AC-01` to `CR-AC-22`). `CR-AC-21` requires rerunning the 19 baseline checks against the changed application. Those rerun checks are not 19 additional unique change acceptance criteria.

Until CR-01 is formally approved and the shared acceptance criteria are updated:
- keep the baseline denominator at 19 parent ACs;
- label the 22 CR-01 criteria as proposed change ACs;
- do not combine proposed change ACs with baseline ACs in one unexplained percentage;
- do not treat CR-AC-21 as 19 additional unique change ACs;
- do not calculate change-phase regressions as if the CR-01 inventory were already frozen.

If CR-01 is approved, record the approval, release/freeze commit and shared-input revision. Use that frozen revision for both tracks.

## 3. Counting units

Do not use the word “count” without naming its unit.

| Unit | Meaning |
|---|---|
| Requirement | One shared FR/NFR ID |
| Parent AC | One shared parent acceptance criterion |
| Subcase | One required test variant under a parent AC |
| Execution observation | One observed execution of one subcase in one run/environment |
| Workflow run | One track's execution under its recorded operator, agent, tool and environment |
| Traceability record | One shared requirement × stage × origin assessment |
| Native link | A link present in a track artefact, with its native ID mapping recorded |
| Defect | One distinct underlying failure/inconsistency, not one failed test row |
| Intervention episode | One bounded human intervention episode |
| Tool interaction | One prompt, command or other logged interaction, under the agreed counting convention |
| Checkpoint | A review/decision point, only if adopted separately from interventions |
| Rework episode | One documented correction/revision episode linked to a defect or inconsistency |
| Impact element | One item in the frozen change-impact reference set or original analysis |
| Active-work interval | One measured interval of active human work |

## 4. Keep three status systems separate

| System | Values | What it describes |
|---|---|---|
| Test execution | Pass, Fail, Blocked, Not run | Outcome of an observed required check |
| Evidence availability | Available, Confirmed absent, Unavailable, Unassessed | Whether evidence can be located and assessed |
| Rubric judgement | 0, 1, 2, 3, NR, N/A | Human judgement under the adopted anchors |

A missing evidence file is not automatically a test failure. An unassessed link is not confirmed absent. A rubric NR is not a zero. Do not substitute one status system for another.

## 5. Operational definitions

The following definitions are proposals for consistent collection. Where Amin's adopted protocol defines a term differently, use the adopted definition and update this catalogue.

- **OD-1 Requirement:** a shared FR/NFR ID from the frozen requirements file.
- **OD-2 Parent AC outcome:** the aggregate status of all required subcases for one parent AC at one track/phase/snapshot, using the adopted rubric's precedence. Proposed pipeline precedence: Fail if any required subcase fails; otherwise Blocked if any is blocked; otherwise Not run if any required subcase is not run or missing; otherwise Pass.
- **OD-3 Defect:** one distinct underlying deviation from expected behaviour or requirement. Multiple failed subcases caused by the same root cause may map to one defect; record the rationale. Do not equate failed test rows with defect count.
- **OD-4 Regression:** a baseline parent AC that was Pass and becomes Fail at the comparable change snapshot. Other paired outcomes must be reported separately.
- **OD-5 Intervention episode:** a bounded human action to clarify, correct, manually edit, retry, resolve an environment issue or perform another recorded intervention. Record trigger, action and before/after artefacts where possible.
- **OD-6 Checkpoint:** a planned or performed review/decision point. This is a proposed separate event type; use it only if the team agrees. Do not silently count every intervention as a checkpoint.
- **OD-7 Rework episode:** a documented episode in which an existing artefact or implementation is revised to correct a defect or inconsistency. Link it to a defect/inconsistency; do not use commit count as a proxy.
- **OD-8 Active effort:** person-minutes of active work, excluding pauses/waits. Only measured intervals belong in measured totals. Approximate or reconstructed intervals must be shown separately.
- **OD-9 Elapsed duration:** wall-clock time between agreed phase boundaries, including waits and breaks. State the exact boundary events.
- **OD-10 Native traceability:** a link explicitly present in a track's own artefacts, mapped to a shared requirement ID and assessed by a reviewer.
- **OD-11 Late discovery:** a distinct defect first identified during independent evaluation, using the agreed `discovered_by` categories. It is not an impact omission.
- **OD-12 Impact reference element:** one requirement, interface, design dependency or other element included in the reviewer-agreed reference set for CR-01. Freeze granularity and membership before matching against the original impact analysis.

## 6. Measure catalogue and proposed tiers

The tiers are proposals for team discussion, not adopted priorities. “Direction” is contextual, not a universal ranking: a lower number is not always better.

### Tier 1 — proposed core measures

| ID | Measure | Unit and proposed definition | Source | Interpretation and limitation |
|---|---|---|---|---|
| M01 | Acceptance outcomes | Parent AC statuses and counts, with denominators and subcase detail | `ac_results.csv` | Primary shared outcome. Required subcases and primary-run selection must be agreed for both tracks |
| M03 | Native traceability | M03a implementation links, M03b verification links, M03c assessment completion; distinct shared requirement IDs / frozen requirement count | `trace_links.csv` | Native and reconstructed links must remain separate; denominator 13 only while inventory is unchanged |
| M04 | Defects | Distinct defects by phase and discovery source | `defects.csv` | Several failed ACs can share one defect |
| M06 | Regressions | Paired baseline/change parent AC outcomes; classify regression, persistent failure, resolved, no regression and undetermined | `ac_results.csv` | Requires comparable snapshots and primary results |
| M07 | Change-impact omissions/recall | Omitted reference elements and recall = Identified / reference-set size, with Unknown shown | `impact_register.csv` | Requires a frozen reference set and saved original analysis; precision/Extra is a separate proposal |
| M08 | Human interventions/interactions | Intervention episodes by type; interaction counts per session as descriptive context | `events.csv`, `sessions.csv` | More interventions do not automatically mean worse quality |
| M09 | Active effort | Measured person-minutes by person, phase and activity; show recorded-period coverage | `sessions.csv` | Not calculable where measured intervals are missing or incomparable |
| M10 | Elapsed duration | Wall-clock duration between agreed phase boundaries | Phase event records / protocol | Includes waits; report boundaries and environment |
| M11 | Rubric ratings J1–J7 | Ordinal ratings 0–3, NR or N/A, exactly as adopted rubric defines | `ratings.csv` | Never sum or average ordinal ratings; preserve independent initial ratings |
| M12 | Rework episodes | Count documented OD-7 episodes linked to defect/inconsistency | `events.csv` | Not equal to intervention count or commit count |

### Tier 2 — supportive proposals requiring agreement

| ID | Measure | Proposed definition | Source | Decision needed |
|---|---|---|---|---|
| M02 | Requirement status view | Derived view: Missed, Unverified, Verified on evaluated checks, Not covered by shared checks | M01 + shared req-to-AC map | Agree labels and whether useful; “verified” does not mean universally satisfied |
| M05 | Late-discovery share | Distinct defects first found by independent evaluation / all distinct defects in phase | `defects.csv` | Agree discovery categories and applicability |
| M07b | Impact precision | Identified / (Identified + Extra); `Extra` means listed in original analysis but absent from reference set | `impact_register.csv` | Agree `Extra` category and reviewer effort; conservative extra items may be reasonable |
| M13 | Reconstructed traceability | Evaluator-inferred implementation and verification links | `trace_links.csv` | Keep separate from native M03 |
| M14 | Reviewer agreement | Exact agreement on initial numeric ratings / items both reviewers rated numerically | `ratings.csv` | Report numerator/denominator and differences; do not calculate kappa/alpha on this small sample |
| M15 | Evidence completeness | Available / (Available + Confirmed absent + Unavailable), with Unassessed shown separately, over a pre-agreed applicable checklist | Amin's evidence checklist | Agree applicable items before counting; checklist preparation blanks are not missing evidence |

### Tier 3 — context only, never quality scores

| ID | Item | Use |
|---|---|---|
| M16 | Tokens, generated files, LOC, native test count, commits, coverage | Describe size, activity or cost only; never use as a stand-alone quality score |
| C1 | Shared input limits | Report the four NFRs without linked ACs and three ACs without requirement IDs once for both tracks |

## 7. Detailed counting rules

### 7.1 Traceability (M03 and M13)

Stages: specification, design/decision, task, implementation and verification. Use the same shared stage vocabulary for both tracks, but preserve each track's native artefact names in the manifest. The stage mapping must be agreed before counting.

- Native explicit links are links present in the track's own artefacts.
- Reconstructed links are inferred by evaluators after the fact.
- A native link counts toward a shared ID only through a recorded mapping from the track's native requirement label to the shared requirement ID.
- Multiple native IDs may map to one shared ID. Native IDs without a shared mapping go to a scope audit, not into the 13 shared requirements.
- Count distinct shared requirement IDs, never rows.
- **M03a:** distinct shared IDs with at least one native-explicit, Available, verified implementation link / frozen requirement count.
- **M03b:** same for verification.
- **M03c:** distinct shared IDs assessed for the relevant stage / frozen requirement count. Assessment is complete when evidence is Available and judged (verified Y or N), or absence is Confirmed absent within a stated search scope. Unavailable, Unassessed and Available-but-pending are not completed assessments.
- When M03c is below 1, M03a/M03b are verified lower bounds; do not interpret unassessed items as absent.
- Reconstructed links feed M13 only, never M03.

### 7.2 Parent AC aggregation (M01)

Use only required subcases from the agreed test plan and primary observations. Proposed aggregation:

1. Fail if any required subcase is Fail.
2. Otherwise Blocked if any required subcase is Blocked.
3. Otherwise Not run if any required subcase is Not run or has no primary observation.
4. Otherwise Pass.

A parent is **complete** when every required subcase has a Pass or Fail observation. A parent can therefore be Fail but incomplete if one subcase failed and another required sibling was not run. A Pass cannot be incomplete.

Let P, F, B and N be parent counts; D = P + F + B + N; C = number of parents with every required subcase observed as Pass or Fail. Show P/D, C/D, (P+F)/D and P/(P+F), with counts. If D or a denominator is zero, or the applicable set is not frozen, show Not calculable. Do not silently add malformed-input subcases; the adopted test plan determines required cases.

Reruns remain in the data but do not replace the primary run. A conflicting valid rerun is reported as non-repeatable evidence. Excluded runs require a reason.

### 7.3 Change outcomes (M06)

For each baseline parent AC with comparable primary observations:

| Baseline | Change | Classification |
|---|---|---|
| Pass | Pass | No regression observed |
| Pass | Fail | Regression |
| Fail | Fail | Persisting failure |
| Fail | Pass | Resolved since baseline |
| Blocked, Not run or missing | Any | Undetermined |
| Any | Blocked, Not run or missing | Undetermined |

An AC introduced only for the change is a **change AC outcome**, not a regression. Count by parent AC and show subcase detail.

### 7.4 Change impact (M07, proposed M07b)

Build the reference register from the approved and released change request, requirements, design/interface dependencies and reviewed final artefacts. Agree granularity and reviewers, then freeze the register before matching it to the saved pre-coding analysis. Record whether reviewers saw the original analysis before building the reference set.

For reference-set size R, Identified I, Omitted O and Unknown U:

- Recall = I / R. Show I, O, U and R. If U > 0, identify recall as a lower bound.
- Not calculable if R = 0 or the original analysis is unavailable.
- If the original analysis exists but lists no elements, recall is 0/R.
- Proposed precision = I / (I + X), where X is `Extra` (listed in original analysis but not in reference set). If there are no listed elements, precision is Not calculable, not zero or 100%.
- M05 counts defects discovered late; M07 counts impact elements. They are different units and must never be merged.

If CR-01 remains pending approval, do not present its reference set or proposed change ACs as a frozen evaluation denominator.

### 7.5 Evidence completeness (M15)

Agree the applicable checklist items before scoring. Separate operator-preserved evidence, baseline-freeze evidence, change-phase owner/evaluator items and the evaluator's own package. Do not count evaluator-only items against a track. Empty preparation checkboxes are not evidence gaps. Use Available, Confirmed absent, Unavailable and Unassessed consistently.

### 7.6 Ratings and reviewer agreement (M11/M14)

Use Amin's adopted J1–J7 names, anchors and applicability rules unchanged. Preserve each reviewer's independent initial rating, any reconciled rating, unresolved state, rationale and uncertainty. Agent-generated ratings are provisional until human-reviewed. Do not sum or average ordinal ratings. M14 is descriptive exact agreement only; show counts and disagreements.

## 8. Compatibility with BMAD and Spec Kit

The comparison unit is the **workflow run under its recorded conditions**, not an abstract tool in isolation. Both tracks are assessed against the same frozen shared requirements, ACs, agreed subcases, rubric anchors and evidence rules.

Do not require Varun and George to use identical internal artefacts, command sequences, prompts, agent roles or development methods. Instead:

1. Preserve native artefact names and IDs.
2. Map native requirement IDs to shared IDs with a recorded mapping and origin.
3. Map native artefact stages to the common stage vocabulary in a team-approved manifest.
4. Preserve raw operator result wording.
5. Have evaluators record shared AC/subcase observations independently.
6. Record actual OS, JDK, Maven, display/headless mode, tool/agent/model versions and other material environment details per run.
7. Apply the same rules for primary runs, reruns, exclusions and evidence sufficiency.

This is a structured comparison of two tool–agent–operator workflows under recorded conditions. Because operator, agent/model, tool version, operating system, prompts and troubleshooting may differ, do not describe the result as a fully controlled causal experiment or claim that the tool alone caused an outcome.

## 9. Validity and reporting cautions

- One workflow run per track is not statistical replication. Requirements and ACs are nested observations, not independent workflow replications.
- Confounders include operator experience, agent/model, tool version, OS, prompts, environment and troubleshooting.
- Preserve chronology: baseline freeze dates, commit snapshots, rubric/protocol adoption date, CR approval/release and evaluator exposure.
- If BMAD and Spec Kit ran on different operating systems, report this as a confounder; do not imply environment equivalence.
- Missing data remain explicit: Unassessed, Unavailable, Not run or Not calculable as appropriate.
- No composite score, overall winner chart, ranking by LOC/test/file/commit counts or significance test on two workflow runs.
- Do not replace a primary Fail with a later rerun Pass.
- Do not treat a GitHub merge as proof that a proposal has been approved or adopted.
- Do not report proposed measures as collected results.
- Conclusions must be limited to the two observed workflows and the evidence actually available.
