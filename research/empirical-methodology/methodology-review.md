# Methodology Review — Calculator Workflow Comparison

**Author:** Mohammadamin Lotfiourimi (Amin), with AI assistance in research and drafting; team verification pending.
**Date:** 2026-10-25.
**Status:** Draft for team review. No implementation evaluation or scores.

## 1. Recommended study framing

We propose a small exploratory comparative study of two tool–agent–operator workflows on one shared Java GUI calculator, with baseline and change phases. The primary unit of comparison is a complete workflow run, not an individual generated file, acceptance check, or AI response. Requirements and checks are observations nested within each run, not independent replications of a tool treatment.

This follows the comparison boundary in [project.md](../../project.md). We cannot isolate a tool-only effect when BMAD and Spec Kit have different operators and possibly different agents. We can report what happened under each recorded configuration and identify possible explanations.

Use case-study guidance to organise context, evidence, and reporting. Do not claim this calculator exercise meets every definition of an industrial case study: the SIGSOFT Case Study standard expects a real site rather than a toy example. The broader label above is more defensible for this educational task. See the [SIGSOFT standards, General and Case Study sections](https://www2.sigsoft.org/EmpiricalStandards/docs/standards).

## 2. Research approach and source selection

This is a targeted methodological review, not a systematic literature review. On 2026-10-25, web searches used combinations of:

- `Runeson Host Guidelines conducting reporting case study research software engineering 2009`
- `Kitchenham Pfleeger Preliminary guidelines empirical research software engineering 2002`
- `Gotel Finkelstein analysis requirements traceability problem 1994`
- `ACM SIGSOFT empirical standards case study data analysis software engineering`
- `site.arxiv.org 2302.06590 Peng Kalliamvakou Cihon Demirer impact AI developer productivity`

We selected primary methodological publications, the professional society's own standards, an author-institution repository record, and a primary AI-development study. Sources needed verifiable authorship/title information and a direct connection to study design, measurement, traceability, or AI-assisted development. Search snippets and secondary summaries were used for discovery, not as the evidential basis for recommendations. No Scopus or Google Scholar search or exhaustive screening is claimed.

The [reference register](references.md) records what was actually read. Abstract-only reading supports only abstract-level claims. The small selection may miss relevant work and should be extended if needed for the final report.

## 3. Guidance and application

| Source | Verified guidance or context | Our proposed application and boundary |
|---|---|---|
| [M1: Runeson and Höst](https://link.springer.com/article/10.1007/s10664-008-9102-8), especially §§3.2 and 5.2.3 | A versioned protocol supports data collection and review; validity needs attention throughout the study. | Keep an explicit procedure and amendments. Apply the guidance without relabelling this small exercise as a fully controlled causal experiment. |
| [M2: Kitchenham et al.](https://www.cin.ufpe.br/~in1002/leituras/preliminaryGuidelinesEmpiricalResearchSE.pdf), §4.2, DC1–DC3 | Measures need entities, units, and counting rules; subjective measurement and collection quality need scrutiny. | Define effort and intervention counts, retain evidence, and preserve independent reviewer judgments. Our exact rubric is a local proposal. |
| [M3: SIGSOFT Empirical Standards](https://www2.sigsoft.org/EmpiricalStandards/docs/standards), General Standard | Reporting should explain methods, data collection, analysis, limitations, and evidence supporting conclusions. | Give every result a snapshot and evidence reference, then distinguish observations from interpretations. Do not claim certification or full standards compliance. |
| [M4: Gotel and Finkelstein](https://discovery.ucl.ac.uk/id/eprint/749/), repository abstract | Requirements traceability includes both pre-specification and post-specification concerns. | Trace shared requirements forward and preserve the clarification decisions behind them. A keyword hit alone does not verify a semantic link. |
| [M5: Peng et al.](https://arxiv.org/abs/2302.06590), abstract | A controlled Copilot study measures completion time for a JavaScript HTTP-server task. | It provides contrasting AI-study context. Its task, treatment, and design do not establish expected time savings for our SDD workflows. |

## 4. Questions and evidence

Use the working questions in `project.md` rather than silently replacing them:

| Project question | Proposed evidence |
|---|---|
| RQ1: Baseline translation and traceability | Shared requirement inventory, native specs/design/tasks, code/test links, clarification records |
| RQ2: Change analysis and implementation | Released CR revision, impact analysis saved before coding, baseline/change snapshots, updated artefacts |
| RQ3: Missed requirements, dependencies, and regressions | Failed checks, defects, omissions from initial impact analysis, regression runs |
| RQ4: Intervention, effort, and rework | Timestamped interaction/events log, active sessions, elapsed phase boundaries, fixes |
| RQ5: Reproducibility and reviewability | Environment manifest, commands/logs, evidence index, reproduction attempt, review disagreements |

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
