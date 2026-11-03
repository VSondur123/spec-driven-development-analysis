# Proposed Evidence Checklist and Metric Collection Map

**Created:** 2026-10-25. **Updated:** 2026-11-03. **Revision:** draft-5.
**Status:** Collection proposal for team review; no submission has been assessed.

The preparation basis is repository commit `1e39815`: 13 requirement IDs and 19 frozen baseline parent acceptance checks. Use the [shared inputs](../../shared/acceptance-criteria.md) as authority and recheck their revision before evaluation. The checklist maps Thanh's [proposed metric catalogue](../comparison-frameworks/metric-catalogue.md), draft-5, without adopting its tiers or optional measures.

## 1. Collection and reference conventions

Varun collects BMAD records; George collects Spec Kit records. Preserve evidence inside each operator's own track, including native artefacts at their existing paths. The study owner records common freezes/releases; evaluators maintain assessment records under an authorised research output directory. Do not require identical internal documents or create missing native artefacts retrospectively.

`EC-01` etc. identify checklist obligations, not evidence files. Use an evidence index ID such as `E-001` for each retained item; include track, phase and code snapshot in every reference, and give external records their own revision. A reference contains repository/commit, path and section/test/lines, observation date/timezone, collector, provenance and claim supported. Keep native filenames and IDs. Assign event/session/report/run IDs in the recording templates, without pretending those are native workflow IDs.

The location column below describes where to retain evidence, not a requirement to create a particular filename. `<track>` means `bmad-calculator` or `github-spec-kit-calculator`; `<evaluation>` means the authorised research output. Raw stdout/stderr may be ignored by `*.log` or `logs/` rules: use retained text evidence or an approved persistent artefact and verify retention. Redact credentials/private data and record the redaction's analytical effect.

## 2. Operational collection checklist

Assess each obligation separately for each applicable track/phase. The last column identifies the main missing-record consequence; availability states are defined in §4. Evaluator-only rows never count against an operator's M15 submission completeness.

| Item | Evidence to preserve | Collector | Collection event | Storage / identifier | RQ / metric support | If missing |
|---|---|---|---|---|---|---|
| EC-01 | Shared requirements/AC revisions, clarification decisions and adopted procedure/rubric revision/date | Study owner | Freeze, adoption or amendment | Existing shared files/decision record; immutable revisions | RQ1–RQ5; M01–M15 applicability | No invented scope, denominator or adoption date |
| EC-02 | Operator experience/familiarity; tool, model/settings, OS, JDK, build tool, toolkit, configuration and timezone | Respective operator | Start and environment change | `<track>/evidence/`; environment record | RQ4/RQ5; M09/M10/M11, M16 context | Unknown settings/background remain unknown; report confounding |
| EC-03 | Initial/subsequent prompts and commands, interaction IDs/order and available timestamps | Respective operator | During work | Native transcript or track evidence; interaction/session IDs | RQ1/RQ4; M08/M12, M16 context | Show logged-period gaps; no inferred zero interactions |
| EC-04 | Native specifications, decisions/design, tasks, source/configuration/docs and tests, with revisions | Respective operator | During work and every freeze | Original native paths; evidence-index references | RQ1/RQ2; M03/M11/M13 | Inspect scope before deciding absence versus unavailability |
| EC-05 | Existing native requirement IDs, links, shared-ID mappings and recorded authorship | Respective operator | During work and freeze | Original native mapping/link evidence | RQ1; M03/M13, supportive M02 | Evaluator mappings stay reconstructed; unknown authorship stays unknown |
| EC-06 | Freeze declaration, root, commit, clean/dirty state, known failures and timestamped phase boundaries | Respective operator | Phase start and frozen submission | Track evidence; snapshot and boundary IDs | RQ2/RQ4/RQ5; M06/M10/M11 | Readiness unresolved or M10 Not calculable |
| EC-07 | Raw native-suite and operator manual results, expected/actual, commands, exits, environment and report/run/test IDs; GUI/offline procedure where observed | Respective operator | Every run, especially freeze | Track evidence; raw reports, screenshots and dated notes | RQ1/RQ3/RQ5; M01/M04/M06/M11, M16 context | Unverified assertions remain separate; no inferred acceptance Pass |
| EC-08 | Common evaluator execution sheet, required subcases, primary/rerun observations, layer, blockers and raw output | Evaluator | Authorised phase assessment | `<evaluation>`; run/subcase and evidence IDs | RQ1/RQ2/RQ3; M01/M02/M06 | No observation: Not run; attempted obstacle: Blocked |
| EC-09 | Distinct defects, reproduction, expected/actual, discovery source/phase, fixes and reruns | Respective operator | Discovery and repair | Track evidence; defect IDs linked to results | RQ3; M04/M05/M06/M12 | Incomplete discovery history limits counts and M05 |
| EC-10 | Intervention/rework episodes, triggers/actions, before/after evidence, defect/inconsistency links and native-workflow deviations | Respective operator | During each episode | Track evidence; event IDs linked to interactions | RQ4; M08/M12 | Show missing periods; commits are not a substitute |
| EC-11 | Measured sessions, start/end, pauses, person/activity, active minutes and logged-period coverage | Respective operator | During each active interval | Track evidence; session IDs | RQ4; M08/M09 | Unmeasured minutes unknown; retrospective estimates separate |
| EC-12 | Identical released CR/check revisions, delivery times, baseline readiness and prior exposure | Study owner | Change release after both baseline freezes | Common release/decision record and revision | RQ2/RQ3; M06/M07/M11 | Change readiness unresolved; do not read unreleased CR to fill gaps |
| EC-13 | First impact analysis saved before coding: requirements, interfaces, dependencies, artefacts, tests, risks and chronology | Respective operator | After release, before change coding | Original analysis revision and timestamp/order evidence | RQ2/RQ3; M07/M07b/M11 (J5) | M07 Not calculable; J5 NR unless chronology proves absence |
| EC-14 | Updated native artefacts, baseline/change diffs, changed code/tests and change freeze declaration | Respective operator | Change implementation and freeze | Native paths and retained diff references | RQ2/RQ3; M06/M07/M11 (J6) | Record gaps without reconstructing native history |
| EC-15 | Frozen affected-element reference set, granularity, original-analysis matching, Unknown/Extra decisions and reviewer exposure | Evaluator/reviewers | Change assessment; freeze set before matching | Research evidence; element IDs/reference-set revision | RQ2/RQ3; M07/M07b | No set or original analysis: Not calculable |
| EC-16 | Independent initial J1–J7 ratings, evidence/rationale, uncertainty, reconciliation and unresolved differences | Human reviewers; evaluator preserves records | Calibration and authorised assessment | Research evidence; reviewer/criterion/rating-kind records | RQ1/RQ2/RQ5; M11/M14 | NR for insufficient support; M14 needs paired human ratings |
| EC-17 | Semantic link verification, search scope, stage/shared-ID mappings and separately reconstructed links | Evaluator | Authorised artefact review | Research evidence; traceability record IDs | RQ1; M03/M13, supportive M02 | Unassessed/Unavailable is not confirmed absence |
| EC-18 | Agreed applicable obligations, availability, search scope and evidence references per checklist assessment | Evaluator | Applicability agreement, then review | Research evidence; item/collector/track/phase records | RQ5; M15 | No calculation from unused checklist blanks |
| EC-19 | Optional tokens, files/LOC/tests/commits or coverage, with scope, command/version and collection period | Respective operator | Context capture or freeze | Native report or track evidence; context record ID | RQ4/RQ5; M16 only | Unknown context remains unknown; never a quality score |
| EC-20 | Deviations, gaps/redactions, decisions, prior exposure and retrospective-account provenance | Responsible operator, owner or evaluator, named per record | Event or discovery of gap | Track/common/research record by responsibility; deviation ID | RQ1–RQ5; M01–M16 interpretation | Disclose uncertainty and affected claims; no history rewriting |
| EC-21 | Independent reproduction, commands/environment, outcomes, raw evidence and evaluator tool/model/instructions with human verification | Evaluator | Authorised reproduction/review | Research evidence; run and evidence IDs | RQ5; M11 (J7), M15 evaluator package | No attempt: J7 at most 2; attempted blockage recorded separately |

Evaluator-discovered defects go in the evaluator's own register with discovery source and links to operator records, retaining deduplication rationale. Do not modify operator history or count the same underlying defect twice.

## 3. Metric-to-record crosswalk

The tiers reproduce Thanh's proposal. Required choices are in the [protocol decision register](protocol-recommendations.md#team-decision-register--recommendations-not-adoption). Record formats and CSV field mappings are in the [templates](evaluation-templates.md).

| Metric / proposed tier | Collection items | Recording location / applicability |
|---|---|---|
| M01 / Tier 1 | EC-01, EC-06–08 | Templates §3 acceptance/subcase records, §4 summary; both phases |
| M02 / Tier 2 | EC-01, EC-08, EC-17 | §2 requirement status view + §3; optional, evaluated shared associations only |
| M03a/M03b/M03c / Tier 1 | EC-04–05, EC-17 | §2 native links, §4 distinct-ID/completion summary; both phases |
| M04 / Tier 1 | EC-07, EC-09–10 | §6 distinct defects; both phases |
| M05 / Tier 2 | EC-09 plus evaluator defects | §6 discovery source/phase, §4 share; optional, unknown history explicit |
| M06 / Tier 1 | EC-06–09, EC-14 | §3 paired regression records; change only, comparable baseline observations required |
| M07 / Tier 1 | EC-12–15 | §8 reference/matching register, §4 counts/recall; change only |
| M07b / Tier 2 | EC-13, EC-15 | §8 analysis-only/Extra elements, §4 precision; optional change measure |
| M08 / Tier 1 | EC-03, EC-10–11 | §6 events/session interaction counts; both phases |
| M09 / Tier 1 | EC-02, EC-11 | §6 measured sessions/coverage; no unmeasured totals |
| M10 / Tier 1 | EC-06 | §6 phase boundaries; agreed start/end events |
| M11 / Tier 1 | EC-04, EC-07, EC-13–16, EC-21 | §4/§7 J1–J7 evidence/ratings; J5/J6 change only |
| M12 / Tier 1 | EC-03, EC-09–10 | §6 linked rework episodes; both phases |
| M13 / Tier 2 | EC-04–05, EC-17 | §2 reconstructed links, separate from M03; supportive |
| M14 / Tier 2 | EC-16 | §7 initial human ratings/agreement; paired numeric ratings only |
| M15 / Tier 2 | EC-18 plus applicable evidence | §5 checklist assessments; separate operator/owner/evaluator sets |
| M16 / Tier 3 | EC-02–03, EC-07, EC-19 | §4 context records with unit/method/scope; never quality scores |

EC-20 supplies provenance/limitations across all measures. Evidence availability and chronology determine calculability, not which workflow appears better.

## 4. Missing evidence, applicability and completeness

| Availability | Meaning / required record |
|---|---|
| Available | Located record can be inspected; cite revision and scope. Availability alone does not verify its claims. |
| Confirmed absent | Sufficiently complete inspected snapshot/record set and documented search establish absence. |
| Unavailable | Access or retained records are insufficient to decide; record reason and impact. |
| Unassessed | Not yet inspected. Empty preparation rows start here. |

Applicability is separate: Applicable, Not applicable (reason/decision reference), or Pending. Agree applicable obligations and collection boundaries under D-06 before counting M15. Use one obligation per item × responsible collector × track/phase (or a common owner record); freeze the set and justified splits before assessing availability. Do not split an inconvenient gap after inspection. Optional EC-19 applies only where context collection was agreed.

For the agreed applicable set, M15 = Available / (Available + Confirmed absent + Unavailable); report all counts, total applicable items and Unassessed separately. If the set is not agreed or the denominator is zero, report Not calculable. With Unassessed items, label the ratio as coverage of assessed obligations, not a complete-submission result. Report operator sets separately per track/phase and owner/evaluator sets separately; evaluator obligations cannot reduce operator completeness.

Missing evidence does not establish an acceptance Fail. An unattempted/unobserved check is Not run; an attempted check prevented by a concrete obstacle is Blocked. Observed Pass/Fail needs supporting observation. Missing timing is not zero minutes; missing qualitative support is NR rather than 0. Dated recollections stay retrospective, with uncertainty, and never replace contemporaneous raw evidence.

At each freeze retain initial outcomes, required sibling subcases, repair snapshots, rerun reasons and valid conflicts. Update existing operator resume files where applicable. Preparation checklists and template placeholders are not findings about a submission.
