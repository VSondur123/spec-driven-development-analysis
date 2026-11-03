# Proposed Evaluation Protocol

**Author:** Mohammadamin Lotfiourimi (Amin), with AI assistance; team review pending.
**Created:** 2026-10-25. **Updated:** 2026-11-03. **Revision:** draft-5.
**Status:** Proposal in Amin's research folder. Not the frozen shared protocol.

## 1. Scope and authority

Compare the two complete tool–agent–operator workflows against the same phase-specific shared inputs. Use [project.md](../../project.md), [baseline requirements](../../shared/baseline-requirements.md), [acceptance criteria](../../shared/acceptance-criteria.md), and [experiment log](../../shared/experiment-log.md) as authority. LOG-09 confirms the current roles: Varun/BMAD, George/Spec Kit, Thanh/metrics and Amin/methodology. This proposal uses repository commit `1e39815` as its preparation basis and maps Thanh's draft-5 catalogue; it does not adopt shared amendments or declare implementation readiness.

Keep research preparation separate from evaluation. Preparation uses shared baseline inputs only. It does not require reading either implementation or the unreleased CR-01. During implementation, operators must not receive the other track's generated artefacts or evaluation hints derived from them.

Our proposed design is exploratory. The comparison units and questions are explained in [methodology-review.md](methodology-review.md). A versioned protocol is supported by [Runeson and Höst, §3.2](https://link.springer.com/article/10.1007/s10664-008-9102-8); the gates and records below are our project-specific procedure.

## 2. Adoption and readiness

The team should record the following in its normal decision process before formal scoring:

| Decision | Required record |
|---|---|
| Adopt procedure and rubric | Exact document revision/commit, approving people, actual date/time, effective phase |
| Reconcile with Thanh's framework | Agreed measures, anchors, denominator rules, and any amendments |
| Identify snapshots | Both track roots, immutable commits, phase, shared-input revision, and frozen/complete declarations |
| Appoint reviewers | Names, roles, prior exposure to either track, and independent review plan |
| Agree on test execution | Common inputs/order/expected outcomes, GUI/manual checks, environment and prerequisites |
| Permit evaluation access | Owner confirms independent assessment of the two ready phase snapshots; results stay away from operators still implementing that phase |

The shared requirements and baseline acceptance checks declare a 2026-10-23 freeze: 13 requirement IDs and 19 parent ACs. The experiment log records decisions and chronology, including work without an adopted protocol/rubric (LOG-10). The shared protocol and rubric files remain empty at `1e39815`. A requirements freeze is not protocol adoption or proof that both implementation snapshots are ready. Inventory evidence already available, identify records that cannot be recovered, and distinguish retrospective assessment from prospective collection if work predates adoption. Do not backdate decisions. A team-approved exception must be dated and applied consistently.

Readiness means both phase submissions are declared complete and frozen for evaluation; it does not mean they must pass every check. A frozen implementation with failures is still evaluable. If only one is ready, prepare common templates and evidence requests, but do not score or inspect the ready track for comparative findings.

Existing session authorisation or a recorded team decision can satisfy the access decision; do not request duplicate approval. This proposal does not turn PR approval into protocol adoption: record which procedural decisions the team actually agreed on, their revision, and their effective date.

## 3. What operators can collect now

Use the [evidence checklist](evidence-checklist.md). Preserve raw prompts, tool/model versions, native artefacts, test output, decisions, defects, rework, and timestamped effort records inside each operator's own track. Existing missing records stay missing or explicitly retrospective.

Do not create artificial native specs/designs to make either track look more traceable. Any evaluator-created mapping is labelled reconstructed and kept outside the implementation folders.

## 4. Baseline assessment

1. Record the approved protocol/rubric and shared-input revisions, both track snapshot hashes, clean/dirty state, evaluator, and date. Uncommitted edits are not covered by a commit hash; use a clean frozen snapshot or preserve the exact patch as an explicitly approved deviation.
2. Inventory the adopted shared scope. The preparation basis contains eight `BASE-FR-*`, five `BASE-NFR-*`, 19 parent `BASE-AC-*`, and numeric/UI clauses without their own IDs. Recheck the [templates](evaluation-templates.md) against the evaluation input revision. Four NFR IDs have no explicit AC association; AC-08, AC-09 and AC-16 have no requirement ID. Report these shared-input limits separately from track performance and retain the 13-ID/19-parent denominators.
3. Verify each native traceability link. Record whether the tool/operator supplied it or the evaluator inferred it. Inspect actual content and tests, not just filenames or ID occurrences.
4. Prepare a common execution sheet from the frozen acceptance checks before running either implementation. Fix the same operands, steps, expected outcomes, required subcases, reset/isolation steps, and execution order for both. Retain the original criteria as authority rather than maintaining a competing requirements copy. Start independent checks from the defined initial state; preserve state within recovery and sequential-calculation scenarios where the criterion requires it.
5. Run each track's documented native build/tests in separate clean checkouts or otherwise isolated snapshots. Record each actual JDK/build-tool version, `JAVA_HOME`, OS, command, prerequisite, exit status, and full output. Use each track's declared dependencies; do not silently upgrade or force identical internals.
6. Execute or inspect the common acceptance checks at the proper layer. An engine-only unit test cannot establish that a GUI control works. A screenshot alone cannot establish a network state; retain a dated manual procedure and observation to support AC-01's offline-launch expectation.
7. Populate test and coverage records. Retain the initial planned run as the primary record, including partially executed failed criteria. Record reruns, invalid-procedure exclusions, and inconsistent observations separately. Any repair becomes a separately identified revision and rerun; it cannot replace the frozen result.
8. Apply the common rubric with evidence citations and independent reviewer notes. Keep objective counts separate from ordinal judgments.

Retain required subcase outcomes. The frozen inputs specify malformed-input examples, Calculate and Enter, exact decimals and division half-up to 10 places, and inline errors. Message strings are suggestions, not exact-string pass conditions. The [template execution reminders](evaluation-templates.md#execution-preparation--frozen-cases-needing-explicit-records) identify cases requiring separate records, including offline launch and GUI interaction. Complete a common execution sheet for all 19 parents before either assessment. Record the treatment of later LOG-12–15 clarifications under D-03 below; do not silently expand frozen pass conditions.

Some non-functional requirements are qualitative. For example, “promptly enough” has no frozen latency threshold. Record observations or measured latency and its method, but do not invent a numerical pass threshold. Treat any newly proposed threshold as a shared decision for a future revision.

## 5. Change-phase procedure, once released

CR-01 is present on `main`; repository presence is not a release record. The shared change-acceptance section remains pending at the preparation basis. Do not open `shared/change-request-CR-01.md` during preparation. This section specifies sequence only, without assuming operations or expected results.

1. After both baseline freezes, the owner records release of the same frozen CR and change acceptance checks to both operators. Record delivery times and any prior exposure or exception honestly.
2. Each operator preserves the first impact analysis before coding: affected requirements, specs/stories, decisions, interfaces, tasks, tests, risks, and predicted regressions. Keep its timestamp and content revision. A later reconstruction is not a pre-coding analysis.
3. Operators update their native artefacts, implement, and test independently, retaining the same kinds of evidence as for baseline.
4. Once both change submissions are ready and access is authorised, assess each frozen change snapshot against the released change checks and rerun the same baseline checks.
5. Reviewers build an affected-element register from the released CR, requirements, design/interface dependencies, and reviewed final artefact/test changes. Agree on meaningful element granularity and required changes independently of the initial impact analysis where feasible; record prior exposure and disagreements. The final diff is one source, not a perfect impact oracle: it may contain unrelated edits or omit needed work. Only then match the original pre-coding analysis against that register and record identified, omitted, or unknown elements. If the reference set or original analysis is unavailable, report the metric as Not calculable. Keep architecture-dependent categories separate rather than ranking by omitted file counts.
6. Count a regression only where baseline behaviour was observed to pass and is now observed to fail on the same comparable check. Missing baseline evidence means regression status is unknown; a current failure can still be reported.

If a CR or expected outcome changes after one operator receives it, retain both versions, date the amendment, and give the same revision to both. Describe the resulting comparison limitation.

## 6. Collection and analysis rules

Use [evaluation-rubric-proposal.md](evaluation-rubric-proposal.md) for statuses, units, counts, and scoring. Every nonempty result needs a track, phase, snapshot, shared-input revision, evidence reference, collector, and observation date. Distinguish direct observation, an inspected operator report, and retrospective recollection.

For missing/conflicting data, record what is missing, its effect on the claim, and the request or decision needed. Never infer zero effort from no time log or a test pass from plausible code. Preserve contradictory evidence and mark the affected judgment unresolved.

For subjective scoring, two reviewers first calibrate using a synthetic example or shared requirement rather than an unfinished implementation. They then rate independently. Preserve original ratings, exact agreement numerator/denominator, reconciliation, final rating or unresolved status, and reasons. Agreement is descriptive for this small study; consensus does not erase initial disagreement. If only one reviewer is available, disclose that limitation.

Report each track and phase separately: counts, applicable denominators, fully executed parent criteria, evidence gaps, individual rubric ratings, defects/regressions, and narrative findings. A parent Fail with unexecuted subcases contributes to decisive outcomes but not full execution completion. Do not combine ordinal ratings, pass rates, and time into a single overall score or treat individual acceptance checks as independent workflow runs.

Use the [GQM crosswalk and integration procedure](methodology-review.md#proposed-gqm-crosswalk) to connect results to RQ1–RQ5. For every proposed metric, use the checklist's collection mapping and the templates' recording location. Preserve Thanh's IDs, units and proposed tiers; optional measures remain pending adoption. At synthesis, place quantitative results alongside qualitative observations and retain corroborating, complementary and contradictory evidence. Unsupported calculations remain Not calculable rather than becoming zeros.

## 7. Tools and their purpose

| Tool or record | Proposed use | Limit |
|---|---|---|
| Git and immutable commit references | Identify snapshots, inspect history/diffs, retain input and artefact versions | Commit times and counts do not measure active work; shared commits may contain both tracks |
| `rg` and file inspection | Locate IDs, candidate links, tasks, and test names within an authorised snapshot | Search results are candidate evidence, not semantic verification |
| Track-native Java/build/test tooling | Reproduce documented commands and inspect test reports | Record actual versions; a green suite may omit shared behaviours |
| Manual GUI observation and screenshots/notes | Check GUI interaction, recovery, launch, and offline behaviour | Record steps, environment, observer, and date; headless execution cannot prove GUI behaviour |
| Markdown tables; optional spreadsheet copy | Maintain versioned matrices and compute explicit counts | Keep evidence links and formulas visible; document exported spreadsheet revision |
| Timestamped operator session/event records | Measure effort, interventions, failures, and rework | Retrospective estimates and unlogged periods remain separately marked |
| [Evaluation skill](skills/evaluate-sdd-workflows/SKILL.md) | Apply the agreed procedure consistently and draft evidence-linked tables | Agent output needs human verification; it cannot invent observations or settle disputed ratings |

No new dependency, paid service, token collection tool, or external upload is needed for preparation. Record the actual evaluator tools/model and versions later. Automated assistance is part of the analysis method and should be disclosed alongside its human verification.

## 8. Review and handoff

The proposed package is ready for team review when templates cover the frozen baseline inventory, statuses/denominators are unambiguous, source claims are verifiable, and change-specific fields stay gated. Amin and Thanh should reconcile it with the framework work before the team adopts a shared protocol/rubric.

The final report should preserve observations, interpretations, deviations, missing data, and the [validity register](validity-threats.md). Relevant statements and evidence can then be incorporated into the final analysis; this preparation does not populate findings or modify shared decisions.

### Team-decision register — recommendations, not adoption

Every row below starts Pending. The responsible role coordinates a decision; the team records the actual decision, approving people, date/time, effective phase and immutable revision in the authoritative shared rules or experiment log, then links that record here. These local D-IDs are unrelated to native track decision IDs. Existing session authorisation and recorded decisions satisfy applicable access requirements; do not request duplicate approval.

| Local ID | Decision / recommended treatment | Responsible role | Adoption record / status |
|---|---|---|---|
| D-01 | Reconcile the GQM crosswalk, M01–M16 and catalogue tiers. Retain Tier 1 as proposed core, Tier 2 as supportive pending agreement, and M16 as context only. M02, M05 and M07b remain optional; CHECKPOINT remains separate and optional. | Amin + Thanh, then team | Pending |
| D-02 | Adopt the common protocol/rubric revision with actual adoption date and effective phase; disclose baseline work predating adoption. | Team; Amin prepares rules, Thanh checks metric alignment | Pending |
| D-03 | Freeze one execution sheet for all 19 baseline parents and their required subcases. Record a treatment for each LOG-12–15 item. Recommended default: retain added cases as supplementary until expressly adopted; report observations on existing scope without inventing a latency threshold. | Amin + Thanh + both operators | Pending |
| D-04 | Record each operator's baseline and change freeze declaration, root, immutable commit, input revision and known gaps. Study owner records phase readiness/release; no requirement to erase failures before freeze. | Varun and George provide declarations; study owner records readiness | Pending |
| D-05 | Identify two independent human reviewers and prior exposure; calibrate on a synthetic example. If only one is available, disclose the limitation and leave M14 Not calculable. | Team appoints reviewers | Pending |
| D-06 | Agree native-ID/stage mappings, traceability assessment unit and applicable evidence items before counting. Keep multiple artefacts but count distinct shared requirement IDs; separate operator, owner and evaluator obligations. | Amin + Thanh + reviewers | Pending |
| D-07 | Agree phase boundaries for M10. Proposed implementation start: first workflow action on supplied phase inputs; end: operator's frozen-submission declaration. Record setup separately, timestamps/timezones and prospective measured sessions; unavailable historical boundaries remain unknown. | Both operators + Amin + Thanh | Pending |
| D-08 | Retain raw operator results separately from evaluator acceptance observations. Agree interaction counting and defect discovery categories (proposed: operator-test, operator-review, independent-eval, unknown); keep discovery phase separate from discovery source. | Amin + Thanh + both operators | Pending |
| D-09 | After both baseline freezes, record identical CR/check revisions, release/delivery times and prior exposure. For impact assessment, freeze element granularity/reference sets before matching saved pre-coding analyses; enable Extra/precision only if adopted. | Study owner + operators + reviewers | Pending |

This package can be ready for review while these adoption rows remain Pending. Formal evaluation uses recorded adopted decisions, not preparation placeholders. The handoff consists of the collection checklist for Varun/George, the crosswalk and field mapping for Thanh, and this decision register for the team; no teammate messages are sent by this document update.
