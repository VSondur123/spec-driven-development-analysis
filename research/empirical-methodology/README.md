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
