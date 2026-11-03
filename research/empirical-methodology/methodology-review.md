# Methodology Review — Calculator Workflow Comparison

**Author:** Mohammadamin Lotfiourimi (Amin), with AI assistance in research and drafting; team verification pending.
**Created:** 2026-10-25. **Updated:** 2026-11-03. **Revision:** draft-5.
**Status:** Draft for team review. No implementation evaluation or scores.

## 1. Recommended study framing

We propose a small educational, GQM-informed comparative case study of two tool–agent–operator workflows on one shared Java GUI calculator, with baseline and change phases. The cases are BMAD operated by Varun and GitHub Spec Kit operated by George, each under its recorded agent/model and environment. Using the same application, requirements and acceptance checks gives the workflows a common task and observation framework while allowing their native processes to differ. The primary unit of comparison is a complete workflow run, not an individual generated file, acceptance check, or AI response. Requirements and checks are observations nested within each run, not independent replications of a tool treatment.

This follows the comparison boundary in [project.md](../../project.md). We cannot isolate a tool-only effect when BMAD and Spec Kit have different operators and possibly different agents. We can report what happened under each recorded configuration and identify possible explanations.

Use case-study guidance to organise context, evidence, and reporting. Here “comparative case study” describes the educational comparison of two workflow runs; it does not establish industrial realism or full standards compliance. The SIGSOFT Case Study standard concerns a phenomenon in its real-world context at a site, which may be a team or process. The calculator task alone does not establish that context. See the [SIGSOFT standards, General and Case Study sections](https://www2.sigsoft.org/EmpiricalStandards/docs/standards).

### Current preparation basis

This revision was prepared against repository commit `1e39815`. The shared baseline declares a 2026-10-23 freeze and contains 13 requirement IDs and 19 parent acceptance checks. The experiment log records work before adoption of a common protocol/rubric; both shared evaluation-rule files remain empty at this revision. These facts do not identify both evaluation snapshots or establish readiness. CR-01 is present on `main`, but its release and frozen change checks are not established by the inspected shared record. Its contents and both implementations were not inspected for this preparation.

`project.md` roles are current, as confirmed in experiment-log LOG-09: Thanh defines goals/questions/metrics; Amin specifies collection, evaluation procedure and validity. The package proposes their alignment for joint review, rather than independently adopting a new GQM model.

## 2. Research approach and source selection

This is a targeted methodological review, not a systematic literature review. On 2026-10-25, web searches used combinations of:

- `Runeson Host Guidelines conducting reporting case study research software engineering 2009`
- `Kitchenham Pfleeger Preliminary guidelines empirical research software engineering 2002`
- `Gotel Finkelstein analysis requirements traceability problem 1994`
- `ACM SIGSOFT empirical standards case study data analysis software engineering`
- `site.arxiv.org 2302.06590 Peng Kalliamvakou Cihon Demirer impact AI developer productivity`

We selected primary methodological publications, the professional society's own standards, an author-institution repository record, and a primary AI-development study. Sources needed verifiable authorship/title information and a direct connection to study design, measurement, traceability, or AI-assisted development. Search snippets and secondary summaries were used for discovery, not as the evidential basis for recommendations. No Scopus or Google Scholar search or exhaustive screening is claimed.

The [reference register](references.md) records what was actually read. Abstract-only reading supports only abstract-level claims. The small selection may miss relevant work and should be extended if needed for the final report.

On 2026-11-03, AI-assisted verification revisited the primary records for M1–M5 and selected methodological passages, and added the author-hosted GQM source M6, also listed as L5 in Thanh's register. The ACM mixed-methods section was checked for the integration rationale. This extends the targeted review; it is not a new systematic search or a claim of human full-paper review.

## 3. Guidance and application

| Source | Verified guidance or context | Our proposed application and boundary |
|---|---|---|
| [M1: Runeson and Höst](https://link.springer.com/article/10.1007/s10664-008-9102-8), especially §§3.2 and 5.2.3 | A versioned protocol supports data collection and review; validity needs attention throughout the study. | Keep an explicit procedure and amendments. Apply the guidance without relabelling this small exercise as a fully controlled causal experiment. |
| [M2: Kitchenham et al.](https://www.cin.ufpe.br/~in1002/leituras/preliminaryGuidelinesEmpiricalResearchSE.pdf), §4.2, DC1–DC3 | Measures need entities, units, and counting rules; subjective measurement and collection quality need scrutiny. | Define effort and intervention counts, retain evidence, and preserve independent reviewer judgments. Our exact rubric is a local proposal. |
| [M3: SIGSOFT Empirical Standards](https://www2.sigsoft.org/EmpiricalStandards/docs/standards), General and Multi-Methodology and Mixed Methods sections | Methods, evidence and limitations need explicit reporting; findings from multiple methods should be integrated to answer the research questions. | Link quantitative results and qualitative observations in the findings table, retaining discrepancies. Do not claim certification or full standards compliance. |
| [M4: Gotel and Finkelstein](https://discovery.ucl.ac.uk/id/eprint/749/), repository abstract | Requirements traceability includes both pre-specification and post-specification concerns. | Trace shared requirements forward and preserve the clarification decisions behind them. A keyword hit alone does not verify a semantic link. |
| [M5: Peng et al.](https://arxiv.org/abs/2302.06590), abstract | A controlled Copilot study measures completion time for a JavaScript HTTP-server task. | It provides contrasting AI-study context. Its task, treatment, and design do not establish expected time savings for our SDD workflows. |
| [M6 / Thanh L5: Basili, Caldiera and Rombach](https://www.cs.umd.edu/~mvz/handouts/gqm.pdf), §§1–2 | GQM connects measurement goals to questions and measures in a stated context. | Organise the existing RQs and proposed M01–M16 without claiming that GQM validates our local metrics or anchors. |

## 4. Questions and evidence

Use the working questions in `project.md` rather than silently replacing them:

| Project question | Proposed evidence |
|---|---|
| RQ1: Baseline translation and traceability | Shared requirement inventory, native specs/design/tasks, code/test links, clarification records |
| RQ2: Change analysis and implementation | Released CR revision, impact analysis saved before coding, baseline/change snapshots, updated artefacts |
| RQ3: Missed requirements, dependencies, and regressions | Failed checks, defects, omissions from initial impact analysis, regression runs |
| RQ4: Intervention, effort, and rework | Timestamped interaction/events log, active sessions, elapsed phase boundaries, fixes |
| RQ5: Reproducibility and reviewability | Environment manifest, commands/logs, evidence index, reproduction attempt, review disagreements |

### Proposed GQM crosswalk

The goal wording below is proposed for Amin/Thanh review. RQ numbers follow `project.md` §3; metric IDs and tiers follow Thanh's [catalogue](../comparison-frameworks/metric-catalogue.md), draft-5. This table does not replace either source or adopt optional measures.

| Proposed goal / evaluation focus | Existing question | Proposed measures | Collection instruments |
|---|---|---|---|
| Assess translation of shared requirements into traceable, verifiable artefacts and behaviour | RQ1: baseline translation and traceability | M01, M02 (supportive), M03a/M03b/M03c, M11 (J1–J4), M13 (supportive) | Requirement/clause matrix, native/reconstructed links, acceptance observations, ratings |
| Assess analysis and propagation of an identical released change | RQ2: change analysis and implementation | M01 (change), M07, M07b (supportive), M11 (J5/J6) | Released-input and impact registers, paired snapshots, change observations and ratings |
| Identify missed scope, defects and regressions | RQ3: missed requirements, dependencies and regressions | M04, M05 (supportive), M06, M07 | Distinct defects with discovery source, paired baseline/change results, impact matching |
| Describe human work required under recorded conditions | RQ4: interventions, effort and rework | M08, M09, M10, M12; M16 as context only | Events, sessions, phase boundaries and contextual counts |
| Assess reviewability and evidence supporting reproduction | RQ5: reproducibility and reviewability | M11 (J7), M14/M15 (supportive) | Reproduction records, independent ratings and checklist assessments |

M11 ratings require supporting narrative; their numeric labels alone are not a qualitative analysis. M14 describes agreement, not validity. M16 is never a quality score. Shared-input limitation C1 applies across the crosswalk: four NFR IDs lack explicit AC links, and three ACs lack a requirement ID. Preserve those gaps without inventing IDs or counting them as track defects.

### Mixed-methods collection, analysis and integration

The quantitative strand records acceptance statuses and denominators, distinct traceability links, defects, interventions and measured effort. Analyse these descriptively for each frozen track/phase; preserve missingness and comparability limits. The qualitative strand uses structured document review guided by the RQs and J1–J7, with evidence-linked notes about clarification, missing relationships, workflow decisions, change handling and reproduction. Keep observations and interpretations separate; record unexpected issues and counterexamples without changing the rubric retrospectively.

Collect prospective records during development and freeze events, and inspect historical records as explicitly retrospective evidence. At each authorised phase assessment, evaluators analyse both strands separately, then integrate them in the [findings table](evaluation-templates.md#7-independent-ratings-and-findings). Each finding names the RQ, metric result/denominator, qualitative observation, evidence IDs, relationship between the strands (corroborates, complements, contradicts or insufficient), interpretation and validity limit. Reviewers inspect contradictory evidence before reconciling an interpretation. Agreement between sources derived from the same record is not independent corroboration.

This integration can explain, for example, how acceptance success coexists with a traceability gap, without treating either as a substitute for the other. Retain differences rather than forcing agreement or averaging results into one score. The design is a local mixed-methods application; it does not claim interviews, grounded theory, saturation or a validated qualitative instrument. The rationale and integration requirement draw on [ACM's mixed-methods guidance](https://www2.sigsoft.org/EmpiricalStandards/docs/standards); the exact table and review procedure are project choices.

## 5. Practical methodological decisions

These are recommendations tailored to our project, not validated measures prescribed by the sources:

1. Preserve the frozen shared inputs and tool-native artefacts. Map equivalent functions across different document structures.
2. Separate observed acceptance outcomes from the presence of tests and from judgment-based workflow ratings.
3. Capture evidence while working. Mark retrospective accounts as such, with their uncertainty; never reconstruct undocumented timing as fact.
4. Agree on scoring anchors before evaluating either track. Record the actual adoption date; if implementation work precedes it, disclose that chronology rather than presenting the protocol as agreed beforehand.
5. Use two reviewers for judgment-based ratings where feasible. Retain initial ratings, disagreements, reconciliation, and unresolved cases.
6. Report counts with denominators, missing data, and narrative explanations. Avoid significance tests or a universal winner based on two runs of one case.
7. Preserve a reproducible analysis trail even though an identical generative rerun is not guaranteed.

The operational detail is in [protocol recommendations](protocol-recommendations.md), [rubric proposal](evaluation-rubric-proposal.md), and [validity threats](validity-threats.md). These drafts need joint review with Thanh before adoption.
