# Our Specification-Driven Development Study

## 1. Our project

We are comparing two specification-driven development workflows by using them to build and evolve the same Java desktop GUI calculator.

- **I, Varun, will use BMAD** for my implementation track.
- **I, George, will use GitHub Spec Kit** for my implementation track.
- **I, Thanh, will research** comparison frameworks, metrics, and visualisation approaches.
- **I, Amin, will research** empirical-study methodology, study design, and validity considerations.

Our goal is to understand how each complete **tool–agent–operator workflow** supports requirements engineering and controlled change. Since our workflows may use different coding agents and different operators, we will not claim to isolate the effect of BMAD versus Spec Kit alone.

## 2. Our shared source of truth

We will keep our common documents in the `shared/` directory. Both implementation tracks and our research will use them as the common reference.

These two files are **already in place** and we must use them as our source of truth:

- `shared/baseline-requirements.md` — our requirements for the initial/basic calculator.
- `shared/acceptance-criteria.md` — our observable pass/fail criteria for the baseline and, after we finalise it, the change.

We will not silently replace, reinterpret, or independently extend these requirements in one track. If we find ambiguity or a conflict, we will record it and agree on a resolution before proceeding. We will apply any agreed change consistently to both tracks and document it in our experiment log.

We will keep the change request in `shared/change-request-CR-01.md` once we finalise it. We will keep our scoring rules and study procedure in `shared/evaluation-rubric.md` and `shared/experiment-protocol.md` once we agree on and freeze them.

## 3. Our working research questions

1. How well does each workflow turn our shared baseline requirements into traceable specifications, design decisions, implementation tasks, and tests?
2. How well does each workflow analyse and implement the same change request in an existing codebase?
3. Which requirements, design dependencies, tests, or regressions do we miss or discover late?
4. What human interventions, elapsed time, tool interactions, and rework do we need?
5. How reproducible and reviewable is the evidence our workflows produce?

## 4. Our repository layout

We will keep our common documents separate from each implementation track. We may adapt folder names to our actual repository, but we will preserve the separation and make it clear which files are shared versus track-specific.

```text
project-root/
├── project.md
├── shared/
│   ├── baseline-requirements.md
│   ├── acceptance-criteria.md
│   ├── change-request-CR-01.md
│   ├── evaluation-rubric.md
│   ├── experiment-protocol.md
│   └── experiment-log.md
├── varun-bmad/
│   ├── native BMAD artefacts
│   ├── source/
│   ├── tests/
│   └── evidence/
├── george-spec-kit/
│   ├── .specify/ and native Spec Kit artefacts
│   ├── source/
│   ├── tests/
│   └── evidence/
└── research/
    ├── comparison-frameworks/
    ├── empirical-methodology/
    └── final-analysis/
```

We will let each tool use its normal project structure. We will not force both tracks into identical internal artefact structures if that conflicts with their native workflows.

## 5. Our roles

### I, Varun — BMAD implementation track
I will:
- Build the baseline calculator using the installed BMAD workflow.
- Follow BMAD's installed/native process and use its expected artefacts.
- Use the shared baseline requirements and acceptance criteria without changing their meaning.
- Freeze my baseline before receiving CR-01.
- Analyse change impact before implementation, update the relevant artefacts, then implement and test.
- Save prompts, tool/version information, outputs, tests, diffs, and effort notes as evidence.

### I, George — GitHub Spec Kit implementation track
I will:
- Build the baseline calculator using the installed GitHub Spec Kit workflow.
- Follow Spec Kit's native constitution/specification/plan/tasks workflow and verify commands against the installed version.
- Use the same shared baseline requirements and acceptance criteria as Varun.
- Freeze my baseline before receiving CR-01.
- Analyse change impact before implementation, update the relevant artefacts, then implement and test.
- Save prompts, tool/version information, outputs, tests, diffs, and effort notes as evidence.

### I, Thanh — comparison frameworks and metrics
I will:
- Find and summarise relevant comparison frameworks, metrics, and visualisation techniques.
- Record sources and explain which ideas suit our small controlled study.
- Propose operational definitions and a practical comparison table.
- Avoid scoring either implementation before we collect the evidence and freeze our common rubric.

### I, Amin — empirical methodology and validity
I will:
- Research empirical software engineering methods relevant to our small comparative study.
- Recommend a defensible procedure for consistency, evidence collection, and analysis.
- Identify construct, internal, external, and conclusion validity threats, plus mitigations.
- Record sources and distinguish established methods from recommendations for our project.

## 6. Our end-to-end workflow

### Phase 0 — We agree on and freeze the protocol
1. We will read this guide and all files in `shared/`.
2. We will confirm that our baseline requirements and acceptance criteria are clear enough to implement and test.
3. We will agree on the exact operations and behaviour in CR-01 before either of us starts the change phase.
4. We will agree on the evaluation rubric, evidence requirements, and experiment protocol.
5. We will record unresolved questions and decisions, then freeze/version the shared documents before implementation begins.

### Phase 1 — We build our baselines independently
1. I, Varun, and I, George, will each start from a clean, equivalent project setup.
2. We will give our assigned workflows the same baseline requirements and acceptance criteria.
3. We will follow our tools' native workflows and will not copy generated implementation artefacts from one track to the other.
4. We will keep scope limited to the baseline and will not implement the scientific extension early.
5. We will run the same acceptance checks and record failures, fixes, time, interventions, and evidence.

### Phase 2 — We freeze both baselines
1. We will review each baseline against our shared acceptance criteria.
2. We will record the exact commit, tool/agent versions, environment, and test results.
3. We will tag or otherwise freeze each baseline so the change phase starts from a known state.
4. We will not make unlogged baseline edits after the freeze.

### Phase 3 — We introduce the same change request
1. We will provide the exact same `shared/change-request-CR-01.md` to both tracks.
2. Before coding, we will ask each workflow to identify affected requirements, stories/specifications, design decisions, tasks, interfaces, and tests.
3. We will capture each initial impact analysis before implementation changes are made.
4. We will update each workflow's native artefacts to reflect the approved change.
5. We will implement the change, review diffs, run the same regression and change-specific tests, and record results.

### Phase 4 — We evaluate and analyse
1. We will collect evidence from both tracks before assigning rubric scores.
2. We will apply the same scoring anchors and test criteria to both tracks.
3. We will separate objective outcomes (such as test pass/fail) from judgement-based ratings.
4. We will document evidence supporting each score. If feasible, we will review artefacts without track labels before scoring.
5. We will report results, limitations, deviations, and validity threats without overstating causal claims.

## 7. Our fairness rules

- We will use the same baseline requirements, acceptance criteria, change request, and rubric for both tracks.
- We will not change the change request after one track has seen it unless we formally amend the protocol and supply the same revision to both.
- We will not share one track's generated specifications, design, tasks, code, or fixes with the other before both are complete.
- We will keep each tool's native workflow intact; we are comparing workflows, not identical folder layouts.
- We will record meaningful human interventions, including clarification prompts, manual edits, retries, and tool failures.
- We will record tool and model names/versions, dates, configuration, and relevant environment details.
- We will preserve raw evidence and make summaries link back to it.
- We will use the same test inputs and expected outcomes in both tracks.
- We will document deviations rather than quietly correcting the record.

## 8. Our evidence checklist

For each track, we will preserve as applicable:

- Initial prompt and subsequent prompts
- Tool and model names/versions and configuration
- Native requirements/specification artefacts and revisions
- User stories or equivalent work items and acceptance criteria
- HLD/architecture and LLD/design artefacts, where produced
- Traceability links from requirements to design, implementation, and tests
- Pre-coding impact analysis for CR-01
- Task plans and task completion records
- Source-control commits and diffs
- Test commands, logs, outcomes, and manual test notes
- Defects found, missed criteria, rework, and regressions
- Human interventions and elapsed/active effort
- Final rubric scores with supporting evidence
- Deviations and validity notes

## 9. When we will consider the study complete

We will be ready to report when:
- We have evaluated both baseline implementations against the same frozen acceptance criteria.
- We have evaluated both change implementations against the same frozen CR-01 criteria.
- We have retained baseline and change evidence and made it traceable.
- We have applied our common rubric consistently.
- We have incorporated Thanh's framework/metrics findings and Amin's methodology/validity findings.
- We have clearly distinguished observations, interpretations, and limitations in our report.

## 10. Our current status

**Already in place:** `shared/baseline-requirements.md` and `shared/acceptance-criteria.md`.

**Our next actions:**
1. We will review and freeze those two documents.
2. We will finalise `shared/change-request-CR-01.md`, including the exact scientific operations and edge-case behaviour.
3. We will finalise `shared/evaluation-rubric.md` and `shared/experiment-protocol.md`.
4. I, Varun, and I, George, will build our baseline calculators independently.
5. We will freeze both baselines before distributing CR-01.
