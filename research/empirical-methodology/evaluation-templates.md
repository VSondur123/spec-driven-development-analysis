# Evaluation Templates — Unpopulated

**Created:** 2026-10-25. **Updated:** 2026-11-03. **Revision:** draft-5. **Status:** Draft instruments for team review; no evaluation performed.

The inventory reflects the [baseline requirements](../../shared/baseline-requirements.md) and [acceptance criteria](../../shared/acceptance-criteria.md) at repository commit `1e39815`: 13 requirement IDs (eight functional and five non-functional) and 19 baseline acceptance criteria. Both shared documents declare a baseline freeze on 2026-10-23; the CR-01 criteria are not part of that freeze. This reference identifies the shared inputs, not either track's evaluation snapshot or an adopted evaluation procedure.

Draft-4 replaced the earlier 16-criterion inventory and added BASE-AC-17–19. Draft-5 retains it and adds GQM recording/field mappings. Later clarifications in [experiment-log.md §E](../../shared/experiment-log.md#section-e--gaps-found-by-the-spec-kit-track-while-writing-the-specification-2026-10-27-george) are recorded separately below; they do not silently amend the frozen acceptance checks.

Use with the [protocol](protocol-recommendations.md) and [rubric](evaluation-rubric-proposal.md). Copy the relevant tables into an authorised research output for each track/phase; do not fill this master with results. Keep the other track's evidence away from operators still implementing. `Pending`, `Unassessed`, and `NR` below are template markers, not findings.

The companion protocol and rubric are draft-5 proposals aligned with Thanh's draft-5 catalogue and proposed CSV tables. Updating these instruments does not adopt the proposals or establish that both track snapshots are frozen. Record the actual adoption date and effective phase; if implementation predates adoption, disclose it as a deviation rather than backdating the procedure.

## 1. Evaluation manifest

| Field | Value |
|---|---|
| Evaluation ID / date / timezone / evaluator | Pending |
| Phase | Pending |
| Protocol and rubric revision / actual adoption date | Pending |
| Effective phase / work completed before adoption / deviation reference | Pending |
| Test-plan revision / primary-run selection rule | Pending |
| Agreed GQM crosswalk / metric tiers / enabled optional measures | Pending |
| Native artefact-to-common-stage mapping / shared-ID mapping rules | Pending |
| Applicable evidence-checklist revision / obligation set / collection boundaries | Pending |
| Team readiness/access decision reference | Pending |
| Shared requirements / acceptance snapshot | Pending |
| Later clarification revisions / agreed evaluation treatment | Pending |
| Released CR revision and release record, if applicable | Not applicable until change release |
| BMAD root / frozen commit / clean state | Pending |
| Spec Kit root / frozen commit / clean state | Pending |
| Operator and agent/model per track | Pending |
| Environment/toolchain/configuration per track | Pending |
| Reviewers / prior exposure / independence limits | Pending |
| Evaluator tools/model / skill revision / human verification | Pending |
| Historical evidence gaps / amendments / deviations | Pending |

Attach exact native commands and observed outcomes later. The two roots may be folders in one repository or separate checkouts. Record each root and repository revision; do not assume two distinct repositories.

### Common metadata for detailed records

Each detailed observation/assessment carries `track` (bmad/speckit), `phase` (baseline/change), `snapshot`, `snapshot_state` (clean/dirty/unknown), `shared_input_revision`, `collector`, and `observed_on` (timestamp/timezone). For time records before a first commit, record snapshot n/a with a reason. Common owner records use affected tracks and a shared revision rather than a fabricated code snapshot. Preserve superseded hashes as `snapshot_alias` if needed. Attach evidence IDs for supported claims; if evidence is missing, record availability/reason without inventing a reference.

Record these fields per row or through an explicit manifest reference that fixes them for the table. Do not inherit metadata silently across different snapshots, collectors or dates. Table-specific IDs below are local evaluation identifiers.

## 2. Requirement coverage matrix

Create one copy per track and frozen phase. The baseline inventory below is taken from the shared requirements; labels are navigation aids, not replacements for their text. Each artefact cell later contains availability, native/reconstructed origin where relevant, and evidence IDs/paths. For native links, record tool-generated, operator-added, or unknown authorship where supported. Implementation evidence can include source, configuration, or required documentation, as appropriate to the requirement. Start with Unassessed, not a cross implying inspected absence.

| Requirement | Scope label | Specification | Design/decision | Task | Implementation evidence | Verification and AC links | Outcome evidence / gap |
|---|---|---|---|---|---|---|---|
| BASE-FR-01 | Offline GUI launch | Unassessed | Unassessed | Unassessed | Unassessed | Unassessed | Unassessed |
| BASE-FR-02 | Numeric entry | Unassessed | Unassessed | Unassessed | Unassessed | Unassessed | Unassessed |
| BASE-FR-03 | Four operation selections | Unassessed | Unassessed | Unassessed | Unassessed | Unassessed | Unassessed |
| BASE-FR-04 | Calculate button / Enter and visible result | Unassessed | Unassessed | Unassessed | Unassessed | Unassessed | Unassessed |
| BASE-FR-05 | Clear/reset | Unassessed | Unassessed | Unassessed | Unassessed | Unassessed | Unassessed |
| BASE-FR-06 | Invalid/missing input and no-operation feedback | Unassessed | Unassessed | Unassessed | Unassessed | Unassessed | Unassessed |
| BASE-FR-07 | Safe division by zero | Unassessed | Unassessed | Unassessed | Unassessed | Unassessed | Unassessed |
| BASE-FR-08 | Error recovery | Unassessed | Unassessed | Unassessed | Unassessed | Unassessed | Unassessed |
| BASE-NFR-01 | Local agreed runtime/OS | Unassessed | Unassessed | Unassessed | Unassessed | Unassessed | Unassessed |
| BASE-NFR-02 | Interactive responsiveness | Unassessed | Unassessed | Unassessed | Unassessed | Unassessed | Unassessed |
| BASE-NFR-03 | No crash on invalid/arithmetic errors | Unassessed | Unassessed | Unassessed | Unassessed | Unassessed | Unassessed |
| BASE-NFR-04 | Reviewable, extensible organisation | Unassessed | Unassessed | Unassessed | Unassessed | Unassessed | Unassessed |
| BASE-NFR-05 | Reproducible build/test instructions | Unassessed | Unassessed | Unassessed | Unassessed | Unassessed | Unassessed |

Shared numeric/UI clauses do not all have requirement IDs. Audit them too, without adding invented IDs to the shared inventory or to the 13-ID traceability denominator:

| Authoritative clause reference | Existing scope to check | Evidence / related ACs | Observation / unresolved verification |
|---|---|---|---|
| baseline-requirements.md §4, items 1–7 | Finite decimals, identifiable operands/operation, consistent display, invalid input, negatives | Unassessed | Unassessed |
| baseline-requirements.md §4, item 8 | Exact decimals; division half-up to 10 decimal places; plain decimal output, no trailing zeros or exponent notation, zero as `0` | Unassessed | Unassessed |
| baseline-requirements.md §4, item 9 | Optional leading `-`, digits, optional decimal point followed by digits; surrounding spaces ignored; other input rejected | Unassessed | Unassessed |
| baseline-requirements.md §4, item 10 | Error priority: empty operand, no operation, invalid number, arithmetic error | Unassessed | Unassessed |
| baseline-requirements.md §5 | Two labelled operand fields; four operation buttons with visible selection; Calculate, Clear, result and inline-error areas; clear state and labels | Unassessed | Unassessed |

The freeze status note in baseline-requirements.md §9 records the GUI pattern, numeric/display policy, calculation trigger and error presentation as resolved. Audit the frozen clauses above. Error-message strings are suggested wording, not exact-string pass conditions. Java/build versions and the evaluation OS remain per-track records plus the LOG-07 recommendation; do not assume both operators used the same OS.

Copy the frozen requirement-to-AC associations from the shared acceptance file when evaluating. If associations do not fully verify a requirement, document the gap; do not silently change shared checks. “No defect found on evaluated checks” is narrower than proving a requirement completely satisfied.

### Detailed traceability record — M03a/M03b/M03c and supportive M13

Use one record per requirement × stage × origin × artefact, or a stable record ID for an assessment summary. Multiple native artefacts may support one shared requirement; count distinct requirement IDs rather than these rows. Common metadata from §1 applies.

| Field | Value |
|---|---|
| Record ID / shared requirement ID / native requirement ID | Pending |
| ID mapping origin: operator-supplied, evaluator-inferred or identical | Pending |
| Stage: specification, design-decision, task, implementation or verification | Pending |
| Link origin: native explicit or reconstructed | Pending |
| Authorship: tool, operator, unknown or n.a. for reconstruction | Pending |
| Availability / verified: Y, N, pending or blank | Unassessed / blank |
| Artefact reference / evidence ID / semantic verification rationale | Pending |
| Search scope / gap / assessment-completion rationale | Pending |

M03c completion requires Available evidence judged Y/N or Confirmed absent with adequate search scope. Available-but-pending, Unavailable and Unassessed are incomplete. Reconstructed links contribute only to M13. Record native-ID-to-shared-ID mappings even where an evaluator supplied that mapping; the underlying link's origin remains a separate question.

### Requirement status view — supportive M02, only if adopted

| Requirement ID | Adopted shared AC associations / input revision | M01 observation references | Derived status / adopted derivation rule | Coverage limitation / evidence |
|---|---|---|---|---|
| To populate | Pending | Pending | Unassessed | Pending |

Use only the agreed labels Missed, Unverified, Verified on evaluated checks, and Not covered by shared checks under the team's adopted derivation rule (D-01). Four NFR IDs have no explicit AC associations; AC-08/09/16 have no requirement ID. Keep those shared-input limits visible without inventing links or treating the absence of a check as a failed requirement.

## 3. Baseline acceptance matrix

Phase: Baseline. Both track snapshot fields: Pending. Execution method, expected/actual, and evidence go into the detailed records below. Every initial status is Not run because this is an unused template.

| Criterion | BMAD status | BMAD evidence | Spec Kit status | Spec Kit evidence |
|---|---|---|---|---|
| BASE-AC-01 | Not run | Pending | Not run | Pending |
| BASE-AC-02 | Not run | Pending | Not run | Pending |
| BASE-AC-03 | Not run | Pending | Not run | Pending |
| BASE-AC-04 | Not run | Pending | Not run | Pending |
| BASE-AC-05 | Not run | Pending | Not run | Pending |
| BASE-AC-06 | Not run | Pending | Not run | Pending |
| BASE-AC-07 | Not run | Pending | Not run | Pending |
| BASE-AC-08 | Not run | Pending | Not run | Pending |
| BASE-AC-09 | Not run | Pending | Not run | Pending |
| BASE-AC-10 | Not run | Pending | Not run | Pending |
| BASE-AC-11 | Not run | Pending | Not run | Pending |
| BASE-AC-12 | Not run | Pending | Not run | Pending |
| BASE-AC-13 | Not run | Pending | Not run | Pending |
| BASE-AC-14 | Not run | Pending | Not run | Pending |
| BASE-AC-15 | Not run | Pending | Not run | Pending |
| BASE-AC-16 | Not run | Pending | Not run | Pending |
| BASE-AC-17 | Not run | Pending | Not run | Pending |
| BASE-AC-18 | Not run | Pending | Not run | Pending |
| BASE-AC-19 | Not run | Pending | Not run | Pending |

Do not compute zeros or percentages from this unused template. Populate real run records first. For CR-01, generate the change inventory only after release, and keep baseline regression outcomes at the change snapshot separate from original baseline outcomes.

### Execution preparation — frozen cases needing explicit records

The full [shared acceptance table, §2](../../shared/acceptance-criteria.md#2-our-baseline-acceptance-criteria) remains authoritative for all 19 criteria. The reminders below identify manual checks and required subcases that must not disappear into a single native-suite result. Agree on a common execution sheet before evaluating either track.

| Parent criterion | Cases / observations to retain |
|---|---|
| BASE-AC-01 | Launch the GUI with networking disabled; record the disabling procedure, environment, observer, date and screenshot or manual note. A screenshot alone does not establish network state. |
| BASE-AC-11 | Empty first operand, empty second operand, and both empty; retain each validation/no-crash observation. |
| BASE-AC-12 | At least `abc`, `1.2.3`, `1,5`, `1e3`, `--1`, and `.`; retain a rejection/no-crash record for each input. |
| BASE-AC-13 | `7 / 0` and `7 / 0.0`; retain each visible-error/no-crash observation, with no successful infinity/NaN result. |
| BASE-AC-14 / BASE-AC-16 | Preserve state within error-recovery and sequential-calculation scenarios. Agree on identical inputs and steps; record each parent separately. |
| BASE-AC-15 | Follow documented build/run instructions from a clean checkout; record commands, prerequisites, environment, build and launch observations. |
| BASE-AC-17 | From the reset state, enter two valid operands and select no operation, then request calculation; expect a clear error, no result and no crash. |
| BASE-AC-18 | Record both `0 / 5 → 0` and `1 / 3 → 0.3333333333`, using the frozen display policy. |
| BASE-AC-19 | With valid operands and an operation selected, press Enter in an operand field; compare with Calculate. Record the field used and agree on the same field coverage for both tracks. |

### Later clarifications — keep separate from the frozen inventory

Experiment-log §E records both owners' agreement to LOG-12–15 on 2026-10-27, while explicitly leaving incorporation of the suggested cases and wording into the frozen shared documents undecided. Before evaluation, record whether each clarification is used as an interpretation of existing scope, an adopted amendment, or a supplementary observation. Apply the same documented treatment to both tracks; do not silently expand a parent AC's pass condition or the 19-criterion denominator.

| Log reference | Clarification recorded in the log | Related frozen scope | Evaluation treatment / decision reference |
|---|---|---|---|
| LOG-12 | `.5`, `5.` and `-` alone are invalid; spaces-only input counts as empty | §4 input grammar; BASE-AC-11 / BASE-AC-12 | Pending |
| LOG-13 | A shown result/error stays until Calculate or Clear, including during operand/operation edits | BASE-FR-08; BASE-AC-14 / BASE-AC-16 | Pending |
| LOG-14 | Judge responsiveness by observation, without a numeric threshold | BASE-NFR-02 | Pending |
| LOG-15 | Half-up ties round away from zero: `-1 / 2048 → -0.0004882813`, `1 / 2048 → 0.0004882813` | §4 item 8; BASE-AC-18 | Pending |

Retain supplementary observations separately from frozen acceptance totals unless an adopted amendment explicitly changes the common execution plan. Record the amendment's revision, actual date and effect on comparability. Do not invent a latency cutoff for BASE-NFR-02.

### Detailed execution record — one per subcase/run

| Field | Value |
|---|---|
| Run ID / parent criterion / subcase | Pending |
| Observation ID / required subcase Y or N | Pending |
| Scope: frozen case, adopted clarification/amendment, or supplementary / decision reference | Pending |
| Track / phase / frozen commit / shared-input revision | Pending |
| Test-plan revision / identical steps and inputs | Pending |
| Initial state / reset steps / execution order | Pending |
| Expected result / authoritative clause | Pending |
| Actual result / status | Pending / Not run |
| Method/layer / native test name, if any / observer / date / environment | Pending |
| Raw output or manual observation / evidence ID | Pending |
| Provenance: own run, raw record, or operator observation | Pending |
| Blocker / defect ID / repair revision / rerun link | Pending |
| Primary run or rerun / exclusion reason / conflicting observations | Pending |

Use the adopted criteria to define required subcases and agree on the same execution steps/inputs for both tracks. The frozen baseline explicitly enumerates malformed-input cases and an Enter-key check; include them. Record the observed layer: an engine-only test cannot establish GUI interaction or Enter-key behaviour. Parent aggregation follows the rubric; it never hides subcase rows. Record whether every required subcase has a Pass/Fail observation: a parent Fail can still be incompletely executed. Required subcases remain under their parent and do not increase the acceptance denominator.

### Operator-reported results — separate from acceptance observations

Use one record per report/run/native test row, preserving the operator's original wording. An evaluator-owned mapping identifies possible shared coverage; the report is not itself an evaluator acceptance Pass. A usable raw record or dated named observation may support a separate detailed acceptance record after verification, with that provenance retained.

| Report ID / source / native row ID | Operator run / native test ID | Shared AC / subcase mapping, if defensible | Raw status wording | Mapped status / mapping rationale | Expected / actual / environment | Evidence location / defect / notes |
|---|---|---|---|---|---|---|
| To populate | Pending | Unmapped | Pending | Pending | Pending | Pending |

Common metadata applies. Keep unmappable raw results with a reason; do not invent an AC ID. Keys include track, phase, snapshot, report ID and test/row identity, plus run ID where needed.

### Paired baseline/change outcomes — M06, change assessment only

| Parent baseline AC | Baseline snapshot / primary result/evidence | Change snapshot / primary result/evidence | Comparable conditions / limitation | Regression classification / rationale |
|---|---|---|---|---|
| To populate | Pending | Pending | Pending | Undetermined |

Classify Pass→Fail as Regression, Pass→Pass as No regression observed, Fail→Fail as Persisting failure, and Fail→Pass as Resolved since baseline. Blocked, Not run, missing or incomparable paired observations are Undetermined. Change-only ACs are recorded separately and are not regressions.

## 4. Workflow comparison table

| Criterion | BMAD rating | BMAD evidence/rationale | Spec Kit rating | Spec Kit evidence/rationale |
|---|---|---|---|---|
| J1 Specification structure and clarity | NR | Pending | NR | Pending |
| J2 Native traceability | NR | Pending | NR | Pending |
| J3 Requirements-informed verification | NR | Pending | NR | Pending |
| J4 NFR treatment | NR | Pending | NR | Pending |
| J5 Pre-coding impact analysis | N/A for baseline | Change phase only | N/A for baseline | Change phase only |
| J6 Change propagation | N/A for baseline | Change phase only | N/A for baseline | Change phase only |
| J7 Evidence and reproducibility | NR | Pending | NR | Pending |

Label each populated rating Agent-provisional, Human-reviewed, or Unresolved, and identify the responsible reviewer. Keep independent human ratings in section 7; an agent-generated table is not a completed reviewer agreement exercise.

| Metric / descriptive measure, phase and snapshot required | BMAD | Spec Kit | Definition, denominator, provenance, or caveat |
|---|---|---|---|
| M01: P / F / B / N, fully executed C, total D | Unassessed | Unassessed | D = 19 baseline parents; recheck input revision. Blocked/unexecuted remain in D |
| M01: pass coverage / execution completion | Unassessed | Unassessed | P/D and C/D, fractions and percentages |
| M01: decisive outcomes / pass share of decisive outcomes | Unassessed | Unassessed | (P+F)/D and P/(P+F), separate from completion |
| M02: requirement status view (supportive) | Unassessed | Unassessed | Adopt derivation rules before use; §2 shared associations only |
| M03a/M03b/M03c: native implementation / verification / completion | Unassessed | Unassessed | Distinct shared IDs; baseline denominator 13; per-stage completion |
| M04: defects | Unassessed | Unassessed | Distinct underlying defects, discovery phase/source, deduplication rationale |
| M05: late-discovery share (supportive) | Unassessed | Unassessed | Independently discovered / all phase defects, with counts; incomplete history or zero denominator: Not calculable |
| M06: regression classifications | Unassessed | Unassessed | Paired comparable results in §3; change only |
| M07: omissions / recall | Unassessed | Unassessed | Change only; I/O/U/R and I/R; unknown matches give a lower bound |
| M07b: precision (supportive) | Unassessed | Unassessed | Only if Extra adopted; I/(I+X); unavailable analysis or no listed elements: Not calculable |
| M08: interventions / interactions | Unassessed | Unassessed | Episode types and session counts; missing logs are not zero |
| M09: active effort / coverage | Unassessed | Unassessed | Measured person-minutes; estimated intervals separate |
| M10: elapsed duration | Unassessed | Unassessed | Adopted start/end event definitions and observed timestamps/timezone |
| M11: J1–J7 | Unassessed | Unassessed | Individual ordinal ratings above and independent sheets in §7; no total |
| M12: rework episodes | Unassessed | Unassessed | Event linked to defect/inconsistency, not commit count |
| M13: reconstructed links (supportive) | Unassessed | Unassessed | Distinct IDs, separate from native M03 |
| M14: reviewer agreement (supportive) | Unassessed | Unassessed | Exact initial numeric agreement count / paired numeric items; NR/N/A shown separately |
| M15: evidence completeness (supportive) | Unassessed | Unassessed | Agreed applicable set in §5; separate collector groups |
| M16: contextual counts | Unassessed | Unassessed | Tokens/files/LOC/native tests/commits/coverage with unit, method, scope and period; never quality scores |

For each populated summary, cite the source records/calculation, metric-definition revision, applicability, exclusions, uncertainty and comparison limits. If input scope is not frozen, evidence is insufficient or a denominator is zero, use Not calculable with a reason. No result is calculated from these preparation blanks.

### Context record — M16 only

| Context record ID / track / phase / snapshot | Item / value / unit | Scope / collection period | Method / command / version / evidence | Interpretation limit / missingness |
|---|---|---|---|---|
| To populate | Unassessed | Pending | Pending | Context only |

## 5. Evidence index

Use evaluator evidence IDs such as `E-001` without renaming native artefacts.

| Evidence ID | Track / phase / snapshot | Path and section/lines/test name | Observation date / observer / method | Provenance / availability / claim supported |
|---|---|---|---|---|
| To populate | Pending | Pending | Pending | Pending |

Prefer immutable commit-and-path references. For evidence outside the code snapshot, record its own revision plus the code snapshot it observed. Line numbers alone can drift. Original reports that cannot be verified remain explicitly unverified.

### Checklist assessment — supportive M15

| Assessment ID / EC item / checklist revision | Responsible collector / group: operator, owner or evaluator | Track(s) / phase / snapshot or common revision | Applicability / reason / decision reference | Availability / search scope / reason | Evidence IDs / gap / observed by and date |
|---|---|---|---|---|---|
| To populate | Pending | Pending | Pending | Unassessed | Pending |

Freeze the applicable obligation set and justified item splits under D-06 before assessing availability. An operator's missing raw evidence and an evaluator's unfinished assessment are different obligations. M15 = Available/(Available + Confirmed absent + Unavailable), with counts and Unassessed shown separately. Pending applicability prevents a completeness calculation for the intended set. A ratio with Unassessed items describes assessed obligations only. Not applicable requires a reason and decision reference; it is not an availability status. Empty preparation rows are not missing-submission findings.

## 6. Intervention, effort, defects, and deviations

| Event ID / track / phase | Start/end/timezone / time precision | Category / purpose | Human action and trigger / provenance | Interaction IDs / artefact before-after / evidence | Is rework Y/N / defect or inconsistency link |
|---|---|---|---|---|---|
| To populate | Pending | Pending | Pending | Pending | Pending |

| Session ID / person / track / phase/activity | Start / end / timezone / time precision | Pauses | Active person-minutes | Interaction / routine prompt counts | Source / measured or retrospective / missing intervals |
|---|---|---|---|---|---|
| To populate | Pending | Pending | Pending | Pending | Pending |

Apply common metadata to both tables. Proposed event categories are CLARIFY, CORRECT, MANUAL_EDIT, RETRY, ENV and UNCLASSIFIED; CHECKPOINT is optional until adopted. Count one continuous episode for one purpose, retaining multiple interaction references. Routine workflow prompts are counted separately from interventions. Rework requires a defect/inconsistency link. Active minutes cannot exceed elapsed minus pauses, and routine prompts cannot exceed all interactions. Resolve overlaps for one person before summing measured time; estimated/unmeasured intervals are never silently included.

### Phase boundaries — M10

| Boundary ID / track / phase | Adopted start/end event definitions / decision | Actual start / end / timezone | Event evidence / source precision | Elapsed duration / unit / limitation |
|---|---|---|---|---|
| To populate | Pending | Pending | Pending | Pending |

Record setup/training separately. If either boundary or its event definition is unknown, elapsed duration is Not calculable; commit timestamps alone do not establish active effort.

| Defect ID / track / snapshot | Requirement / AC | Reproduction / expected / actual | Evidence / discovered phase / discovered by | Related IDs / deduplication rationale | Fix snapshot / rerun / regression evidence |
|---|---|---|---|---|---|
| To populate | Pending | Pending | Pending | Pending | Pending |

Keep discovery source (proposed: operator-test, operator-review, independent-eval, unknown; D-08) separate from discovery phase. Several failed subcases may share one defect. Retain source records when merging duplicates. M05 requires a sufficiently established defect inventory and discovery history; a defect first found in the change phase is not automatically an independent-evaluation discovery.

| Deviation or missing-data ID / date | Planned rule / actual event | Track(s), phase, reason | Evidence / uncertainty | Agreement or pending decision / effect on claims |
|---|---|---|---|---|
| To populate | Pending | Pending | Pending | Pending |

## 7. Independent ratings and findings

| Track / phase / snapshot / criterion | Reviewer A original rating / evidence | Reviewer B original rating / evidence | Reconciled rating or unresolved / linked initial records | Reason / date / uncertainty / human or agent provenance |
|---|---|---|---|---|
| To populate | Pending | Pending | Pending | Pending |

| Finding ID / RQ / track / phase / snapshot | Metric IDs / quantitative results and denominators / record refs | Qualitative observation / J criterion / evidence IDs | Corroborates, complements, contradicts or insufficient / rationale | Interpretation / contradictory or missing evidence | Threat IDs / claim limitation / reviewer and date |
|---|---|---|---|---|---|
| To populate | Pending | Pending | Pending | Pending | Pending |

Report exact initial agreement with its denominator, unresolved ratings, and any single-reviewer limitation. If neither reviewer has supplied an independent rating, agreement is Not calculable. These empty rows are record structures, not synthetic results.

For export, keep one rating per reviewer × criterion × snapshot × rating kind (initial, reconciled or unresolved), with its own evidence/rationale/uncertainty. Record Agent-provisional, Human-reviewed or Unresolved assessment status separately; an agent suggestion is not an independent human rating. Integrate findings under the [methodology procedure](methodology-review.md#mixed-methods-collection-analysis-and-integration); shared-source agreement does not establish independent corroboration.

## 8. Change-impact reference and matching — after release only

Do not populate this register or read CR-01 during preparation. At authorised change evaluation, record the released input revision, baseline/change snapshots, reviewers, agreed element granularity, and whether reviewers saw the original impact analysis before defining the reference set. Use one register per track for architecture-specific elements; compare common requirement-level items directly and keep other categories separate.

| Element ID / category / source: reference or analysis-only | Requirement / reason / granularity | Reviewed final evidence or still-missing work | Original pre-coding analysis reference | Identified / Omitted / Unknown / Extra only if adopted | Reviewers / disagreement / reference-set revision |
|---|---|---|---|---|---|
| To populate | Pending | Pending | Pending | Unknown | Pending |

Record the original analysis revision and its evidence of preceding coding separately. An omission count is Not calculable until both the agreed reference set and the original analysis are available. Changed files alone neither define the reference set nor prove a complete impact analysis.

Use R = I + O + U for reference elements, retaining I, O and U counts. M07 recall is I/R, a lower bound if U > 0. An available empty analysis gives 0/R; unavailable analysis or R = 0 gives Not calculable. If adopted, retain analysis-only Extra elements X separately for M07b = I/(I+X); no listed elements gives Not calculable precision. Keep `saw_original_before_reference` and source identity so analysis-only elements cannot inflate R. Key elements by track, phase, reference-set revision, element ID and source.

## 9. Field mapping to Thanh's proposed CSV tables

These are Markdown recording instruments; this revision creates no CSV exports. Use Thanh's [visualisation proposal §3](../comparison-frameworks/visualisation-proposal.md#3-canonical-record-tables-proposed-csv-schemas) as the proposed export vocabulary, subject to adoption. Grouped Markdown cells must be split into the named columns when exported, with common metadata from §1. Do not infer missing values to satisfy a schema.

| Markdown record | Proposed table / field mapping and identity |
|---|---|
| §1 manifest/common metadata | All observation tables: track, phase, snapshot, snapshot_state, optional snapshot_alias, shared_input_revision, evidence_id, collector, observed_on; retain manifest ID for shared context |
| §2 detailed traceability | trace_links: record_id for summaries; req_id, native_id, id_mapping_origin, stage, origin, availability, authorship, verified, search_scope, rationale, artefact_ref. Key includes snapshot/req/stage/origin/artefact so multiple artefacts survive |
| §3 operator reports | operator_results: report_id, run_id, test_id/native row, ac_id, subcase_id, commit_hash as reported, environment, status_reported, status_mapped, expected, actual, evidence_location, defect_id, notes, report_source |
| §3 detailed execution | ac_results: run_id, observation_id, ac_id, subcase_id, required, test_plan_revision, status, expected, actual, layer, environment, native_test, provenance, run_kind, exclusion_reason, blocker, defect_id, repair_snapshot. Retain initial/reset/order steps with the test-plan reference/notes |
| §3 paired outcomes / §2 status view / §4 summaries | Derived views from primary ac_results and shared associations, trace_links and source registers; keep metric ID, formula, counts, source references and limitations; no new raw-observation table implied |
| §5 evidence/checklist records | Evidence index and M15 assessment supplement; retain assessment_id, EC item, obligation-set revision, collector group, applicability/decision, availability, search_scope and evidence refs. Thanh proposes no separate canonical CSV for these records |
| §6 events | events: event_id, type, purpose, trigger_and_action, interaction_ids, artefact_before, artefact_after, is_rework, linked_defect_id or explicit inconsistency ref, start, end, time_precision, provenance |
| §6 sessions | sessions: session_id, person, activity, start, end, time_precision, pauses_min, active_min, interaction_count, routine_prompt_count, provenance, missing_intervals |
| §6 boundaries | Phase-event supplement for M10: boundary_id, adopted event definitions/decision, start/end/timezone and evidence refs; do not invent a sessions row for wall-clock boundaries |
| §6 defects | defects: defect_id, req_id, ac_id, expected, actual, reproduction, discovered_phase, discovered_by, fix_snapshot, rerun_status, regression_evidence; retain deduplication/related-ID notes |
| §6 deviations | deviations: deviation_id, planned_rule, actual_event, affected_tracks, reason, uncertainty, decision_status, effect_on_claims |
| §7 independent ratings | ratings: criterion, reviewer, rating_kind, rating, rationale, uncertainty, status; snapshot/criterion/reviewer/kind distinguish original and reconciled records |
| §7 integrated findings | Narrative/Markdown findings supplement: finding_id, RQ, metric result/denominator, qualitative observation, integration relationship, evidence and threat IDs; no canonical CSV proposed |
| §8 impact register | impact_register: element_id, category, granularity_note, req_ids, reason_needed, reviewed_final_evidence, source, match_status, original_analysis_ref, reviewer_a/reviewer_b, disagreement, reference_set_revision, saw_original_before_reference |

The proposed ac_results key mentions `observation_id` although its suggested column list omits it; this instrument retains the ID explicitly. Common owner/deviation records affecting both tracks also need the proposed schema's affected-tracks treatment rather than a fictitious single-track observation. Record these export-alignment points for joint review before adopting CSV headers. Keep raw reports, evaluator observations, independent ratings and derived summaries distinguishable throughout.
