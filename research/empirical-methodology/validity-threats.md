# Validity Threats and Mitigations

**Created:** 2026-10-25. **Updated:** 2026-11-03. **Revision:** draft-5.
**Status:** Proposed threat register; no implementation assessment or applied mitigations claimed.

The register below is our project-specific assessment. Runeson and Höst discuss construct, internal, external validity and reliability in [§5.2.3](https://link.springer.com/article/10.1007/s10664-008-9102-8). We additionally use the conclusion-validity category requested in our research brief; we do not attribute that exact four-category scheme to their paper.

## 1. Construct validity — are we measuring the intended property?

| Threat ID / threat | Affected metrics or claim | Mitigation / responsible role | Evidence needed to establish mitigation | Remaining limitation |
|---|---|---|---|---|
| VT-C01: ID mentions or many documents are treated as traceability/quality | M03/M13; traceability quality | Verify semantic links and distinguish native links from evaluator reconstruction — Evaluator | Native links, semantic checks and search scope | A correct mapping may still omit rationale or be expensive to follow |
| VT-C02: Engine tests are treated as full GUI correctness | M01; GUI correctness | Map checks to the observed layer; retain GUI/manual evidence for interaction checks — Evaluator + operators | Layer-specific run/manual records, including offline procedure | Finite scenarios cannot prove all behaviour correct |
| VT-C03: A parent failure with unexecuted subcases is counted as fully executed | M01; execution completion | Report decisive parent outcomes separately from fully executed parent criteria — Evaluator | Required subcase outcomes and separate C versus P+F counts | A definitive failure may still leave much of a criterion unobserved |
| VT-C04: Number of tests or lines changed is treated as test quality or effort | M01/M09/M16; quality/effort | Use shared criterion outcomes and recorded active sessions; report structural counts only as context — Thanh + evaluator | Shared outcomes, measured sessions and contextual-count method | Logs may be incomplete; acceptance checks cover limited behaviour |
| VT-C05: “Promptly,” maintainability, or specification clarity is scored without a definition | M11 (J1/J4); NFR/clarity claims | Use frozen anchors where available; report qualitative observations and unresolved thresholds explicitly — Amin + reviewers | Anchors, evaluated NFR scope and observation limits | Broad NFRs may remain only partially assessable |
| VT-C06: Missing evidence is treated as failure, no intervention, or zero time | M01/M08/M09/M11/M15; missingness | Separate evidence status, execution status, and rubric rating — Collectors + evaluator | Separate status fields, missing-data notes and provenance | Unknown performance cannot be recovered by relabelling it |
| VT-C07: Inferred mappings are credited as tool-produced traceability | M03/M13; native capability | Label origin and preserve native artefacts untouched — Evaluator | Origin/authorship/shared-ID mapping and retained native artefacts | Native IDs may be absent even when a workflow supports useful human tracing |
| VT-C08: Shared NFR/AC associations incompletely represent the requirement scope | M01/M02/M03/M11; coverage claims | Report four NFR IDs without explicit AC links and three ACs without requirement IDs; preserve qualitative NFR observations without invented pass thresholds — Amin + Thanh | Frozen shared inventory, clause audit and per-NFR review notes | Shared acceptance coverage cannot establish every requirement is satisfied |
| VT-C09: Checklist size or evaluator work changes operator evidence completeness | M15; evidence comparisons | Agree applicable obligations before counting; separate operator, owner and evaluator sets — Amin + Thanh + evaluator | Frozen obligation-set revision, collection boundaries, applicability decisions and availability assessments | A complete record can still document poor outcomes; incomplete assessment limits the ratio |

## 2. Internal validity — what else could explain a difference?

| Threat ID / threat | Affected metrics or claim | Mitigation / responsible role | Evidence needed to establish mitigation | Remaining limitation |
|---|---|---|---|---|
| VT-I01: Different operator skill, tool familiarity, or prior Java experience | All comparative claims | Record relevant experience and roles; avoid tool-only causal claims — Operators + Amin | Recorded relevant experience/familiarity; claim restrictions | With one operator per tool, tool and operator effects remain confounded |
| VT-I02: Different agents/models, settings, contexts, or tool versions | All comparative claims | Record exact available identifiers, settings and dates; report differences — Operators | Tool/model/configuration/date manifest and unknown settings | Matching names does not guarantee identical hidden service behaviour |
| VT-I03: Different prompts, troubleshooting help, retry budgets, or optional native steps | M08–M12; workflow differences | Preserve interactions and interventions; retain each native workflow and explain deviations — Operators + evaluator | Interaction/event records, help sources and native-process deviations | Full process equivalence is neither achieved nor necessarily desirable |
| VT-I04: Cross-track artefacts, fixes, or early CR knowledge influence an operator | M06/M07/M11; independence | Keep implementation tracks isolated; log actual exposures and CR release times — Study owner + operators | Exposure/release records and separation procedure | Prior informal discussions and knowledge may be impossible to remove |
| VT-I05: Different environments or evaluator setup errors affect outcomes | M01/M06/M10/M11; environment | Record JDK, build tool, OS, `JAVA_HOME`, prerequisites, and the same test cases — Operators + evaluator | Actual OS/JDK/build/display/JAVA_HOME, commands and blocker records | Some platform differences may persist; setup blockage is not automatically a product defect |
| VT-I06: Time/order effects and LLM nondeterminism | All comparative claims | Record chronology and retries; retain initial outcomes and exact snapshots — Operators + evaluator | Chronology, initial outcomes, snapshot/rerun records | One run per workflow gives no stable estimate of run-to-run variability |
| VT-I07: Rubric or criteria chosen after seeing desirable results | All metrics; selection bias | Agree on anchors before scoring; record adoption date and prior exposure — Team + Amin | Actual adoption dates, prior exposure and work predating adoption | Any work or exposure predating adoption remains a limitation and must be recorded |

## 3. External validity — where might the findings apply?

| Threat ID / threat | Affected metrics or claim | Mitigation / responsible role | Evidence needed to establish mitigation | Remaining limitation |
|---|---|---|---|---|
| VT-E01: One small calculator with limited requirements | All generalisations | Describe scope and complexity; relate interpretations only to similar tasks — Amin + report authors | Shared task/requirement inventory and explicit scope boundaries | Findings do not establish performance on large, distributed, or industrial systems |
| VT-E02: Two student operators and one team | All generalisations | Report context and relevant experience — Operators + report authors | Team context/experience records | Other teams and expertise levels may use the workflows differently |
| VT-E03: One change request and short duration | M06/M07/M11; maintenance claims | State exactly which released change was studied and what was excluded — Study owner + report authors | Released change revision, duration and exclusions | Long-term maintenance and different change types remain untested |
| VT-E04: Rapidly changing tools and model services | All generalisations | Pin artefact/input revisions; record versions and execution dates — Operators + evaluator | Dated versions/configurations and immutable artefacts | Future versions and identical prompts may produce different results |

## 4. Conclusion validity — do the observations support the claims?

| Threat ID / threat | Affected metrics or claim | Mitigation / responsible role | Evidence needed to establish mitigation | Remaining limitation |
|---|---|---|---|---|
| VT-Q01: Correlated acceptance checks are treated as independent experiments | M01–M07; statistical claims | Report check counts as within-run observations; keep the workflow run as comparison unit — Thanh + report authors | Analysis identifies two workflow runs and nested observations | Two runs do not support population-level statistical superiority |
| VT-Q02: Subjective ratings, researcher expectations, and ambiguous anchors | M11/M14; qualitative claims | Use explicit anchors, independent ratings, and a disagreement log; mask labels if practical — Reviewers + Amin | Initial independent ratings, calibration, exposure and reconciliation | Native artefacts often reveal tool identity; a small rating set limits agreement analysis |
| VT-Q03: Missing timing, selective logs, and retrospective recall | M08–M10/M12; effort claims | Retain gaps and provenance; separate measured and recalled effort — Operators + evaluator | Measured versus recalled sessions, coverage/gap notes | Comparable total effort may be unavailable |
| VT-Q04: Many metrics allow selective emphasis or an arbitrary overall winner | M01–M16; selective reporting | Freeze a small metric set; report all applicable results and denominators; no weighted total — Amin + Thanh | Adopted metric set/tiers and all applicable results, including missingness | Trade-offs still require interpretation |
| VT-Q05: Repairs replace failures or latest artifacts are attributed to a baseline | M01/M04/M06; snapshot attribution | Retain the initial frozen result, repair revision, and each rerun separately — Operators + evaluator | Original frozen result, repair revision and separate reruns | The defect discovery process may differ across workflows |
| VT-Q06: Rerunning until success hides inconsistent behaviour | M01/M06; repeatability | Identify the primary planned run; preserve valid conflicting observations and exclusion reasons — Evaluator | Primary selection, valid conflicts and exclusion reasons | A small number of reruns cannot characterise the frequency of intermittent failures |
| VT-Q07: Agreement between dependent sources is treated as corroboration, or qualitative findings are reduced to ratings | Mixed-methods interpretation; M01/M03/M11 | Trace each strand's provenance and integrate narrative observations with counts, retaining contradictory findings — Evaluator + reviewers | RQ-level findings table, source dependencies, counterexamples and review rationale | One raw record may support both strands; they need not provide independent confirmation |

## 5. Reliability and reproducibility

Keep immutable input/snapshot references, commands, raw output, manual steps, rubric revisions, reviewer decisions, and source links. Reproducibility has two boundaries: another reviewer should be able to follow our analysis of the saved evidence, while a new AI generation run may differ even with the same prompt.

| Threat ID / threat | Affected metrics or claim | Mitigation / responsible role | Evidence needed to establish mitigation | Remaining limitation |
|---|---|---|---|---|
| VT-R01: Evaluator assistance invents links/statuses or verifies its own output | M01/M03/M11/M13/M14; evidence trust | Preserve evaluator model/tool, instructions and document revision; humans verify source links, observations and ratings — Evaluator + reviewers | Assisted outputs, source references and dated human verification | Human reviewers can also miss errors; two agent outputs are not two independent human ratings |
| VT-R02: Mutable paths or incomplete snapshots prevent reproduction | M01/M06/M10/M11/M15; reproducibility | Retain immutable revisions, actual commands/environment and independent reproduction records — Operators + evaluator | Manifest, exact snapshot/patch, raw output, manual steps and reproduction attempt | Saved-evidence analysis can be reproducible while a new generative run differs |

The existing [evaluation skill](skills/evaluate-sdd-workflows/SKILL.md) supports consistent record preparation; its output remains subject to the same source verification. Source-reading limits in [references.md](references.md) also constrain methodological claims.

## 6. How to use this register

At baseline and change evaluation, append a dated assessment for each relevant threat: evidence observed, mitigation actually used, deviation, remaining uncertainty, and effect on the reported claim. Do not write a planned mitigation as if it happened. Highlight the limitations most relevant to each conclusion rather than copying this prospective list as a completed validity assessment.

Use stable threat IDs to connect the [findings table](evaluation-templates.md#7-independent-ratings-and-findings) to this register. The ownership/evidence columns describe proposed responsibilities and proof needed, not completed mitigation. The preparation basis is `1e39815`; baseline freeze and later protocol/rubric adoption must be reported as distinct events.

| Assessment ID / threat ID / track(s) / phase | Snapshot / date / reviewer | Observed evidence / deviation reference | Mitigation status: planned, applied, partial or not feasible | Actual action / supporting evidence | Residual influence / restriction on the finding |
|---|---|---|---|---|---|
| To populate | Pending | Unassessed | Planned | Pending | Pending |

Append dated assessments rather than replacing earlier ones. Before claiming a mitigation was applied, cite its action evidence. If unsupported, retain planned/unknown status and narrow the affected conclusion. Different operator/model/environment conditions remain confounders even when fully documented; logging makes them visible without removing them.
