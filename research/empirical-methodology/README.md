# My Instructions — Amin / Empirical Methodology and Validity

## My goal

I will research and recommend a defensible empirical-study design for our calculator comparison. I will focus on procedure, evidence collection, analysis, reproducibility, and validity threats.

## What I will read first

- `project.md`
- `shared/baseline-requirements.md`
- `shared/acceptance-criteria.md`
- `shared/evaluation-rubric.md` once we draft it
- `shared/experiment-protocol.md` once we draft it

## My research tasks

1. I will find credible empirical software engineering guidance on comparative studies, case studies, experiments, and AI-assisted software development.
2. I will recommend how we can standardise requirements, the change request, tests, evidence collection, and scoring.
3. I will identify internal, construct, external, and conclusion validity threats.
4. I will recommend mitigations that are feasible for our two-track student project.
5. I will define how we should record deviations, missing evidence, human interventions, and disagreements between reviewers.
6. I will provide verifiable references and explain their relevance.

## My copy-ready research prompt

```text
I am researching empirical methodology for our small requirements-engineering comparison study.

We will build a Java desktop GUI calculator using two workflows: BMAD operated by Varun and GitHub Spec Kit operated by George. Both receive the same shared baseline requirements and acceptance criteria. Once each baseline is frozen, both receive the same change request and are evaluated using common tests and a shared rubric. We compare tool–agent–operator workflows; because operators and potentially coding agents differ, we must not claim a clean causal estimate of tool-only effects.

Research empirical software engineering methods relevant to this design. Use credible, verifiable sources. For each source, provide bibliographic details, link/identifier, the relevant method or finding, and its application to our project. Do not invent sources or overstate what a source establishes.

Recommend a practical protocol covering:
- Study objectives and research questions
- Unit of analysis and comparison boundaries
- Baseline setup and freeze procedure
- Timing and delivery of the identical change request
- Required pre-coding impact analysis
- Common acceptance/regression tests
- Evidence and effort collection
- Rubric scoring and reviewer calibration
- Handling of missing data, deviations, and disagreements
- Reproducibility and reporting

Explicitly assess:
- Construct validity: whether our metrics really measure specification quality, impact analysis, correctness, effort, or workflow effectiveness
- Internal validity: operator skill, agent/model differences, environment, prompt differences, order effects, prior familiarity, and unequal troubleshooting
- External validity: one small calculator case, two operators, limited change type, and limited generalisability
- Conclusion validity: small sample size, subjective scoring, measurement noise, and multiple comparisons

For each threat, propose a realistic mitigation and state any residual limitation. Distinguish established methodological guidance from recommendations tailored to our project. Do not score the tracks before we freeze the common protocol and rubric.
```

## My suggested deliverables

I will save my work under `research/empirical-methodology/` or the location we agree on:

- `methodology-review.md`
- `validity-threats.md`
- `protocol-recommendations.md`
- `references.md`

## My expected methodological position

I will treat our comparison as small and exploratory. I will recommend identical shared inputs and objective tests wherever possible, retention of raw evidence, scoring anchors defined before evaluation, and explicit reporting of uncertainty and limitations. If we cannot randomise or balance operators and agents, I will recommend framing our conclusions as observations about the two complete workflows rather than proof that one tool is inherently superior.

## My evaluation preparation — 2026-10-25

**Status: Draft for team review.** These documents propose a procedure; they are not an agreed shared protocol or results. No implementation artefacts were inspected to prepare them. The shared requirements and acceptance criteria remain authoritative.

| Document | Purpose |
|---|---|
| [Methodology review](methodology-review.md) | Study design, source selection, and methodological rationale |
| [Protocol recommendations](protocol-recommendations.md) | Preparation, evaluation gates, collection, review, and reporting |
| [Rubric proposal](evaluation-rubric-proposal.md) | Measurement definitions and criterion-specific scoring anchors |
| [Evidence checklist](evidence-checklist.md) | Records operators should preserve now and at each freeze |
| [Evaluation templates](evaluation-templates.md) | Unpopulated coverage, acceptance, comparison, and review tables |
| [Validity threats](validity-threats.md) | Threats, feasible mitigations, and remaining limitations |
| [References](references.md) | Verified sources, reading scope, and limitations |
| [Evaluation skill](skills/evaluate-sdd-workflows/SKILL.md) | Reusable agent instructions for preparation and later evaluation |

The skill is kept here with my research contribution for review. It has not been installed into an agent's skill directory. An evaluator can be given its `SKILL.md` path explicitly together with the evaluation request. Keep the skill and these companion documents together.

My proposed division of work follows `project.md`: Amin leads procedure, evidence rules, and validity; Thanh reviews metric definitions, scoring, and presentation. The role mismatch with the top-level README needs team confirmation; this draft does not resolve it on the team's behalf.

Before using this package to score either track, the team should agree on the protocol/rubric revision, phase readiness, exact snapshots, and reviewers. The shared requirements and acceptance criteria on `main` are still drafts, and the experiment log is empty at preparation time. Record the actual adoption date and which activities it precedes; do not describe this proposal as an already agreed protocol.

The protocol, rubric, and instruments are at **draft-3**, prepared for team review against `main` at `cb1a051`. The templates cover its 13 requirement IDs and 16 draft acceptance criteria. Counts and clauses must be reconciled with the eventual frozen inputs before evaluation. No unmerged implementation-track requirements or decisions are adopted by this proposal.

### Team review decisions

- [ ] Amin and Thanh reconcile the proposed metric definitions, anchors, and presentation with the comparison-framework work.
- [ ] The team records the adopted protocol/rubric revision, actual adoption date, effective phase, and any deviations from the original study plan.
- [ ] Both operators identify frozen phase snapshots and provide the evidence checklist records, including known gaps.
- [ ] The team identifies reviewers, confirms existing evaluation access, and agrees on the common execution sheet before scoring.

After agreement, the team can incorporate the adopted procedure into `shared/experiment-protocol.md` and `shared/evaluation-rubric.md` through its normal decision process. This research PR does not amend those shared files.
