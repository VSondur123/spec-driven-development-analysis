# Validity Threats and Mitigations

**Date:** 2026-10-25. **Status:** Prospective draft; no track findings.

The register below is our project-specific assessment. Runeson and Höst discuss construct, internal, external validity and reliability in [§5.2.3](https://link.springer.com/article/10.1007/s10664-008-9102-8). We additionally use the conclusion-validity category requested in our research brief; we do not attribute that exact four-category scheme to their paper.

## 1. Construct validity — are we measuring the intended property?

| Threat | Feasible mitigation | Remaining limitation |
|---|---|---|
| ID mentions or many documents are treated as traceability/quality | Verify semantic links and distinguish native links from evaluator reconstruction | A correct mapping may still omit rationale or be expensive to follow |
| Engine tests are treated as full GUI correctness | Map checks to the observed layer; retain GUI/manual evidence for interaction checks | Finite scenarios cannot prove all behaviour correct |
| A parent failure with unexecuted subcases is counted as fully executed | Report decisive parent outcomes separately from fully executed parent criteria | A definitive failure may still leave much of a criterion unobserved |
| Number of tests or lines changed is treated as test quality or effort | Use shared criterion outcomes and recorded active sessions; report structural counts only as context | Logs may be incomplete; acceptance checks cover limited behaviour |
| “Promptly,” maintainability, or specification clarity is scored without a definition | Use frozen anchors where available; report qualitative observations and unresolved thresholds explicitly | Broad NFRs may remain only partially assessable |
| Missing evidence is treated as failure, no intervention, or zero time | Separate evidence status, execution status, and rubric rating | Unknown performance cannot be recovered by relabelling it |
| Inferred mappings are credited as tool-produced traceability | Label origin and preserve native artefacts untouched | Native IDs may be absent even when a workflow supports useful human tracing |

## 2. Internal validity — what else could explain a difference?

| Threat | Feasible mitigation | Remaining limitation |
|---|---|---|
| Different operator skill, tool familiarity, or prior Java experience | Record relevant experience and roles; avoid tool-only causal claims | With one operator per tool, tool and operator effects remain confounded |
| Different agents/models, settings, contexts, or tool versions | Record exact available identifiers, settings and dates; report differences | Matching names does not guarantee identical hidden service behaviour |
| Different prompts, troubleshooting help, retry budgets, or optional native steps | Preserve interactions and interventions; retain each native workflow and explain deviations | Full process equivalence is neither achieved nor necessarily desirable |
| Cross-track artefacts, fixes, or early CR knowledge influence an operator | Keep implementation tracks isolated; log actual exposures and CR release times | Prior informal discussions and knowledge may be impossible to remove |
| Different environments or evaluator setup errors affect outcomes | Record JDK, build tool, OS, `JAVA_HOME`, prerequisites, and the same test cases | Some platform differences may persist; setup blockage is not automatically a product defect |
| Time/order effects and LLM nondeterminism | Record chronology and retries; retain initial outcomes and exact snapshots | One run per workflow gives no stable estimate of run-to-run variability |
| Rubric or criteria chosen after seeing desirable results | Agree on anchors before scoring; record adoption date and prior exposure | Any work or exposure predating adoption remains a limitation and must be recorded |

## 3. External validity — where might the findings apply?

| Threat | Feasible mitigation | Remaining limitation |
|---|---|---|
| One small calculator with limited requirements | Describe scope and complexity; relate interpretations only to similar tasks | Findings do not establish performance on large, distributed, or industrial systems |
| Two student operators and one team | Report context and relevant experience | Other teams and expertise levels may use the workflows differently |
| One change request and short duration | State exactly which released change was studied and what was excluded | Long-term maintenance and different change types remain untested |
| Rapidly changing tools and model services | Pin artefact/input revisions; record versions and execution dates | Future versions and identical prompts may produce different results |

## 4. Conclusion validity — do the observations support the claims?

| Threat | Feasible mitigation | Remaining limitation |
|---|---|---|
| Correlated acceptance checks are treated as independent experiments | Report check counts as within-run observations; keep the workflow run as comparison unit | Two runs do not support population-level statistical superiority |
| Subjective ratings, researcher expectations, and ambiguous anchors | Use explicit anchors, independent ratings, and a disagreement log; mask labels if practical | Native artefacts often reveal tool identity; a small rating set limits agreement analysis |
| Missing timing, selective logs, and retrospective recall | Retain gaps and provenance; separate measured and recalled effort | Comparable total effort may be unavailable |
| Many metrics allow selective emphasis or an arbitrary overall winner | Freeze a small metric set; report all applicable results and denominators; no weighted total | Trade-offs still require interpretation |
| Repairs replace failures or latest artifacts are attributed to a baseline | Retain the initial frozen result, repair revision, and each rerun separately | The defect discovery process may differ across workflows |
| Rerunning until success hides inconsistent behaviour | Identify the primary planned run; preserve valid conflicting observations and exclusion reasons | A small number of reruns cannot characterise the frequency of intermittent failures |

## 5. Reliability and reproducibility

Keep immutable input/snapshot references, commands, raw output, manual steps, rubric revisions, reviewer decisions, and source links. Reproducibility has two boundaries: another reviewer should be able to follow our analysis of the saved evidence, while a new AI generation run may differ even with the same prompt.

The evaluator skill can make repeated collection more consistent, but its inferences introduce another source of error. Record the evaluator model/tool, prompt, document revision, output, and human verification. Reject invented links and observations. A skill-generated matrix is not independent confirmation of its own claims.

## 6. How to use this register

At baseline and change evaluation, append a dated assessment for each relevant threat: evidence observed, mitigation actually used, deviation, remaining uncertainty, and effect on the reported claim. Do not write a planned mitigation as if it happened. Highlight the limitations most relevant to each conclusion rather than copying this prospective list as a completed validity assessment.
