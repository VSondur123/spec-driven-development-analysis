# My Instructions — Thanh / Comparison Frameworks and Metrics

## My goal

I will research how software-engineering workflows and requirements-driven development can be compared in a small, evidence-based study. I will recommend metrics and presentation methods that we can apply consistently to our BMAD and GitHub Spec Kit calculator tracks.

## What I will read first

- `project.md`
- `shared/baseline-requirements.md`
- `shared/acceptance-criteria.md`
- `shared/evaluation-rubric.md` once we draft it
- `shared/experiment-protocol.md` once we draft it

## My research tasks

1. I will find and summarise relevant frameworks, models, and studies for comparing software-development processes, requirements traceability, specification quality, change-impact analysis, and AI-assisted development workflows.
2. I will identify quantitative and qualitative measures suitable for our small two-track study.
3. I will recommend operational definitions: what counts as a requirement, a missed criterion, a defect, a regression, a human intervention, rework, and effort.
4. I will recommend practical visualisations for our results.
5. I will provide source links and enough bibliographic information for our team to verify each source.
6. I will distinguish measures supported by prior literature from pragmatic measures I propose specifically for our study.

## My copy-ready research prompt

```text
I am researching comparison frameworks, metrics, and visualisation techniques for our small requirements-engineering study.

Our study builds the same Java desktop GUI calculator using two workflows: BMAD operated by Varun and GitHub Spec Kit operated by George. The workflows receive the same shared baseline requirements and acceptance criteria, then the same frozen change request. We compare the complete tool–agent–operator workflows and must not claim to isolate tool effects when other factors differ.

Research existing frameworks and empirical studies relevant to:
- Requirements quality, completeness, ambiguity, and traceability
- Specification/design/task consistency
- Change-impact analysis and change coverage
- Functional correctness, acceptance-test outcomes, and regression defects
- Test quality and coverage
- Human intervention, rework, elapsed time, and active effort
- Evidence quality, reproducibility, and reviewability
- Clear visualisation of comparative results

For every useful source, provide the citation, link, research context, relevant finding or measure, limitation, and how we could adapt it to this study. Do not invent citations or claim a source says something you have not verified.

Propose a concise metric catalogue with: metric name, definition, unit, collection method, evidence source, scoring direction, likely bias, and whether it is objective or judgement-based. Recommend a small set of visualisations that match the data. Keep the proposal feasible for two implementation tracks.

Do not score either track before we collect the evidence and freeze our common evaluation rubric. Flag threats to fairness or construct validity in the proposed metrics.
```

## My suggested deliverables

I will save my research under `research/comparison-frameworks/` or the location we agree on:

- `literature-review.md`
- `metric-catalogue.md`
- `visualisation-proposal.md`
- `references.md`

## Metric categories I will consider

These are starting points, not final decisions. We will agree on definitions before data collection.

| Category | Possible measure | Evidence |
|---|---|---|
| Requirement coverage | Number/proportion of acceptance criteria satisfied | Shared test results |
| Traceability | Requirements linked to design/tasks/tests | Native artefacts |
| Change-impact quality | Correct affected elements identified before coding | Saved impact analysis compared with final diff/tests |
| Defects | Failed criteria and defects found | Test logs and review |
| Regression | Baseline behaviours broken by CR-01 | Regression results |
| Rework | Repeated edits/reverts or reopened tasks | Git history and task log |
| Human intervention | Clarification prompts, manual corrections, retries | Interaction log |
| Effort | Active time and elapsed time | Timestamped log |
| Evidence quality | Completeness and reproducibility of records | Evidence checklist |

## My controls

- I will recommend the same definitions and collection procedure for both tracks.
- I will not treat raw token counts or the number of generated files as quality by themselves.
- I will avoid combining objective test outcomes and subjective ratings into one opaque score.
- I will note that operator skill, model, environment, and tool version may confound results.
- I will record evidence for each metric and make missing data visible rather than assuming a zero.
