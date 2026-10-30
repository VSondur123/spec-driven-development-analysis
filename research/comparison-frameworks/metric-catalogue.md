# Metric Catalogue and Operational Definitions

**Author:** Pham Thanh (Thanh)  
**Date:** 2026-10-30  
**Revision:** draft-5 (schema and cross-track compatibility revision)  
**Status:** Proposal for team review. Nothing in this file is adopted unless agreed and entered into the authoritative shared documents. No track has been scored.

## 0. Authority and relationship to team work

This catalogue is supplementary. It must not become a second rubric or protocol.

| Material | Role and authority |
|---|---|
| `shared/baseline-requirements.md`, `shared/acceptance-criteria.md` | Frozen shared requirement and AC inventory; source of truth for IDs and wording |
| `shared/evaluation-rubric.md`, `shared/experiment-protocol.md` | Intended authoritative evaluation rules once adopted |
| Amin's `evaluation-rubric-proposal.md`, `protocol-recommendations.md` | Draft proposals (PR #2 merged them into the repository; merge is not adoption) |
| Amin's `evaluation-templates.md`, `evidence-checklist.md` | Proposed collection instruments |
| This catalogue | Supplementary measures, counting units, operational definitions and proposals |
| `visualisation-proposal.md` | Supplementary CSV schema and figure specifications |
| `literature-review.md`, `references.md` | Research context; neither sets rules |

At the recorded `main` state `200ce28`, the shared rubric and protocol files were empty. The proposed rubric/protocol are therefore not to be described as adopted. If this catalogue conflicts with an adopted rubric or protocol, the adopted document wins. Both tracks should be evaluated using the same adopted shared rules; neither operator is required to imitate the other track's internal workflow.

## 1. Shared input inventory and limits

The inventory below reflects the stated frozen shared inputs at commit `258a6d0`, unchanged at `main` `200ce28`:

- 13 requirements: 8 functional requirements (FR) and 5 non-functional requirements (NFR).
- 19 parent acceptance criteria (ACs).
- 9 requirement IDs have at least one linked AC (FR-01 to FR-08 and NFR-05).
- 4 NFR IDs (NFR-01 to NFR-04) have no linked AC.
- 3 ACs (AC-08, AC-09 and AC-16) have no requirement ID.

These are **input-inventory facts**, not performance results. Report the same shared-input limitation for both tracks. Do not penalise either workflow for an absent shared check unless the team adds and freezes one through the agreed change-control process. Recheck the inventory if the shared-input revision changes.

CR-01 is not part of the frozen `main` inventory described above unless and until it is approved and added to the shared project inputs. A branch draft is not an adopted change request.

## 2. Counting units

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

## 3. Keep three status systems separate

| System | Values | What it describes |
|---|---|---|
| Test execution | Pass, Fail, Blocked, Not run | Outcome of an observed required check |
| Evidence availability | Available, Confirmed absent, Unavailable, Unassessed | Whether evidence can be located and assessed |
| Rubric judgement | 0, 1, 2, 3, NR, N/A | Human judgement under the adopted anchors |

A missing evidence file is not automatically a test failure. An unassessed link is not confirmed absent. A rubric NR is not a zero. Do not substitute one status system for another.

## 4. Operational definitions

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

## 5. Measure catalogue and proposed tiers

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

## 6. Detailed counting rules

### 6.1 Traceability (M03 and M13)

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

### 6.2 Parent AC aggregation (M01)

Use only required subcases from the agreed test plan and primary observations. Proposed aggregation:

1. Fail if any required subcase is Fail.
2. Otherwise Blocked if any required subcase is Blocked.
3. Otherwise Not run if any required subcase is Not run or has no primary observation.
4. Otherwise Pass.

A parent is **complete** when every required subcase has a Pass or Fail observation. A parent can therefore be Fail but incomplete if one subcase failed and another required sibling was not run. A Pass cannot be incomplete.

Let P, F, B and N be parent counts; D = P + F + B + N; C = number of parents with every required subcase observed as Pass or Fail. Show P/D, C/D, (P+F)/D and P/(P+F), with counts. If D or a denominator is zero, or the applicable set is not frozen, show Not calculable. Do not silently add malformed-input subcases; the adopted test plan determines required cases.

Reruns remain in the data but do not replace the primary run. A conflicting valid rerun is reported as non-repeatable evidence. Excluded runs require a reason.

### 6.3 Change outcomes (M06)

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

### 6.4 Change impact (M07, proposed M07b)

Build the reference register from the released change request, requirements, design/interface dependencies and reviewed final artefacts. Agree granularity and reviewers, then freeze the register before matching it to the saved pre-coding analysis. Record whether reviewers saw the original analysis before building the reference set.

For reference-set size R, Identified I, Omitted O and Unknown U:

- Recall = I / R. Show I, O, U and R. If U > 0, identify recall as a lower bound.
- Not calculable if R = 0 or the original analysis is unavailable.
- If the original analysis exists but lists no elements, recall is 0/R.
- Proposed precision = I / (I + X), where X is `Extra` (listed in original analysis but not in reference set). If there are no listed elements, precision is Not calculable, not zero or 100%.
- M05 counts defects discovered late; M07 counts impact elements. They are different units and must never be merged.

### 6.5 Evidence completeness (M15)

Agree the applicable checklist items before scoring. Separate operator-preserved evidence, baseline-freeze evidence, change-phase owner/evaluator items and the evaluator's own package. Do not count evaluator-only items against a track. Empty preparation checkboxes are not evidence gaps. Use Available, Confirmed absent, Unavailable and Unassessed consistently.

### 6.6 Ratings and reviewer agreement (M11/M14)

Use Amin's adopted J1–J7 names, anchors and applicability rules unchanged. Preserve each reviewer's independent initial rating, any reconciled rating, unresolved state, rationale and uncertainty. Agent-generated ratings are provisional until human-reviewed. Do not sum or average ordinal ratings. M14 is descriptive exact agreement only; show counts and disagreements.

## 7. Compatibility with BMAD and Spec Kit

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

## 8. Reconciliation with Amin's proposed measures

The following is a discussion map, not an adoption record.

| Amin proposal | Catalogue mapping | Suggested treatment |
|---|---|---|
| Acceptance coverage/completion | M01 | Keep concept; confirm 19 parent AC inventory and required subcases |
| Native implementation traceability | M03a | Clarify shared-ID mapping and distinct-ID counting |
| Native verification traceability | M03b | Same |
| Reconstructed traceability | M13 | Keep outside native counts |
| Traceability assessment completion | M03c | Clarify assessment-complete definition |
| Human interventions | M08 | Confirm event categories and interaction-count convention |
| Rework | M12 | Clarify episode and defect/inconsistency linkage |
| Active effort | M09 | Same measured-time rule for both operators |
| Elapsed duration | M10 | Agree phase boundary events |
| Defects | M04 | Agree distinct-defect and discovery-source rules |
| Regressions | M06 | Agree paired outcome categories |
| Change-impact omissions | M07 | Agree reference-set procedure and reviewers |
| Reviewer agreement | M14 | Descriptive exact agreement only |
| J1–J7 anchors | M11 | Use adopted anchors without modification |

## 9. Questions for team decision

1. Are the proposed Tier 1/Tier 2/Tier 3 groupings useful?
2. Should CHECKPOINT be a separate event type from interventions?
3. Should M02, M05 and M07b remain optional until agreed?
4. Who will update Amin's templates/rubric inventory for all 19 parent ACs?
5. Who are the two independent reviewers, and what prior exposure must they declare?
6. Confirm RQ numbering against `project.md` §3.
7. Report C1 as a shared input limitation?
8. Who declares and records each freeze?
9. Which subcases are required for each parent AC, including any proposed additional cases?
10. Is one traceability record per shared requirement × stage × origin sufficient, with multiple artefacts listed in evidence references?
11. Will both operators record measured start/end and active minutes for the change phase?
12. Should raw operator reports be retained alongside evaluator-observed AC/subcase records?

No answer is assumed. Record decisions in the authoritative shared documents or experiment log.

## 10. Validity and reporting cautions

- One workflow run per track is not statistical replication. Requirements and ACs are nested observations, not independent workflow replications.
- Confounders include operator experience, agent/model, tool version, OS, prompts, environment and troubleshooting.
- Preserve chronology: baseline freeze dates, commit snapshots, rubric/protocol adoption date, CR release and evaluator exposure.
- If BMAD and Spec Kit ran on different operating systems, report this as a confounder; do not imply environment equivalence.
- Missing data remain explicit: Unassessed, Unavailable, Not run or Not calculable as appropriate.
- No composite score, overall winner chart, ranking by LOC/test/file/commit counts or significance test on two workflow runs.
- Do not replace a primary Fail with a later rerun Pass.

## 11. Guidance for the evaluation skill

If the team builds an evaluation skill, it should read the **adopted** rubric and protocol and the agreed shared inventory. It may draft mappings and populate proposed record tables, but humans verify mappings, statuses, evidence and ratings. The skill must preserve source references and raw operator wording. It must not infer missing evidence, convert missing records to Pass, or treat its own ratings as human-reviewed.
