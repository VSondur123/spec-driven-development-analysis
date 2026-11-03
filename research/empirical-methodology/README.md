# My Instructions — Amin / Empirical Methodology and Validity

**Updated:** 2026-11-03. **Package revision:** draft-5. **Status:** Proposals for team review; no implementation evaluation performed.

## My goal

I will prepare the empirical methodology for our GQM-informed comparative case study of BMAD/Varun and GitHub Spec Kit/George. Thanh defines the goals, questions and metrics; I define how evidence is collected and evaluated consistently, including quantitative/qualitative integration, reproducibility and validity. We jointly review the crosswalk and evaluation rules before team adoption.

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
- `evidence-checklist.md`
- `evaluation-rubric-proposal.md`
- `evaluation-templates.md`
- `validity-threats.md`
- `protocol-recommendations.md`
- `references.md`

## My expected methodological position

I will treat our comparison as small and exploratory. I will recommend identical shared inputs and objective tests wherever possible, retention of raw evidence, scoring anchors defined before evaluation, and explicit reporting of uncertainty and limitations. If we cannot randomise or balance operators and agents, I will recommend framing our conclusions as observations about the two complete workflows rather than proof that one tool is inherently superior.

## My evaluation preparation — created 2026-10-25, revised 2026-11-03

**Status: Draft for team review.** These documents propose a procedure; they are not an adopted shared protocol or results. No implementation artefacts were inspected for comparative findings to prepare this revision. The shared requirements and acceptance criteria remain authoritative.

| Document | Purpose |
|---|---|
| [Methodology review](methodology-review.md) | Educational comparative design, proposed GQM crosswalk, mixed-methods integration and source rationale |
| [Protocol recommendations](protocol-recommendations.md) | Phase procedure, snapshots, primary runs, review and team-decision register |
| [Rubric proposal](evaluation-rubric-proposal.md) | Measurement rules and retained J1–J7 anchors with criterion-specific evidence guidance |
| [Evidence checklist](evidence-checklist.md) | EC-01–EC-21 collectors, timing, locations, missingness and M01–M16 record mapping |
| [Evaluation templates](evaluation-templates.md) | Empty 19-check instruments, separate operator/evaluator records, metric summaries and proposed CSV field crosswalk |
| [Validity threats](validity-threats.md) | Stable threat IDs, responsible roles, evidence needed, residual limitations and assessment records |
| [References](references.md) | M1–M6 metadata, reading/verification scope, limits and decision-to-source links |
| [Evaluation skill](skills/evaluate-sdd-workflows/SKILL.md) | Reusable agent instructions for preparation and later evaluation |

The skill is kept here with my research contribution for review. It has not been installed into an agent's skill directory. An evaluator can be given its `SKILL.md` path explicitly together with the evaluation request. Keep the skill and these companion documents together.

The current division of work follows `project.md`, confirmed by experiment-log LOG-09: Amin leads procedure, evidence rules and validity; Thanh leads frameworks, metrics and presentation. The top-level README describes the initial plan. Thanh's [metric catalogue](../comparison-frameworks/metric-catalogue.md) and [visualisation proposal](../comparison-frameworks/visualisation-proposal.md) at draft-5 supply proposed metric IDs, tiers and export vocabulary, not adopted shared rules.

At preparation basis `1e39815`, the baseline inputs declare a 2026-10-23 freeze and contain 13 requirement IDs (8 FR, 5 NFR) and 19 parent acceptance checks. The experiment log contains decisions and later clarifications, including work without an adopted evaluation procedure (LOG-10). The shared protocol and rubric remain empty. A merged proposal or frozen requirement set is not protocol adoption or evidence that both evaluation snapshots are ready.

The package is now **draft-5**. Historically, draft-3 used `cb1a051` and a 16-check draft; the 2026-10-30 template draft-4 corrected the inventory to 19. This revision aligns the companion documents, retains J1–J7 and maps all proposed M01–M16 without changing Thanh's IDs or adopting optional measures. Recheck counts against the actual evaluation input revision.

CR-01 is present in the repository, but release and frozen change checks are not established by the inspected shared record. Its contents were not needed for this preparation. LOG-12–15 treatment also needs an explicit common evaluation decision; their agreement does not silently add subcases to the frozen acceptance conditions.

### How to use the package

1. Read the methodology's GQM crosswalk and the protocol's team-decision register together with Thanh's catalogue.
2. Review collection obligations with Varun and George: preserve native artefacts and historical gaps; start prospective logs without inventing earlier records. This update itself does not send requests to teammates.
3. Agree revisions, metric applicability, required subcases, exact snapshots and reviewers through the existing shared decision process. Record actual dates and any work predating adoption.
4. At authorised evaluation, copy the master templates into a separate research output and record operator reports, evaluator observations, qualitative judgements and integrated findings separately.

Four NFR IDs lack explicit shared AC associations, and AC-08/09/16 have no requirement ID. Report these shared-input limits for both tracks; acceptance success does not prove every requirement is satisfied. Missing evidence is not an automatic Fail, unknown time is not zero, and NR is not a rubric score of 0.

### Team review decisions

- [ ] Amin and Thanh reconcile the proposed metric definitions, anchors, and presentation with the comparison-framework work.
- [ ] The team records the adopted protocol/rubric revision, actual adoption date, effective phase, and any deviations from the original study plan.
- [ ] Both operators identify frozen phase snapshots and provide the evidence checklist records, including known gaps.
- [ ] The team identifies reviewers, confirms existing evaluation access, and agrees on the common execution sheet before scoring.
- [ ] The team records LOG-12–15 treatment, optional metric/checkpoint decisions, phase boundaries and applicable evidence obligations using the protocol's D-01–D-09 register.
- [ ] Reviewers retain independent initial judgements and connect quantitative results to qualitative observations and validity threat IDs in the findings table.

After agreement, the team can incorporate the adopted procedure into `shared/experiment-protocol.md` and `shared/evaluation-rubric.md` through its normal decision process. This research PR does not amend those shared files.
