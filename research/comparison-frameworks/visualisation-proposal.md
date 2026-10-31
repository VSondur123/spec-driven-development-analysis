
# Visualisation Proposal and Record Formats

**Author:** Pham Thanh (Thanh)  
**Date:** 2026-10-30  
**Revision:** draft-5 (schema-key and cross-track alignment revision)  
**Status:** Proposal for joint review with `metric-catalogue.md` and Amin's proposed rubric/protocol. No data are represented here; all figures are specifications, not results.

## Authority and scope

This file does **not** define evaluation rules. The adopted rubric and protocol are authoritative. Until adoption, Amin's `evaluation-rubric-proposal.md` and `protocol-recommendations.md` are drafts. The metric catalogue supplies proposed measure IDs M01–M16 and OD-1–OD-12. RQ1–RQ5 refer to `project.md` §3. If this file conflicts with an adopted shared rule, the shared rule wins.

The purpose is to provide a common evidence-record structure for both tracks. It does not require Varun or George to change their internal BMAD or Spec Kit workflows. Preserve native records, then map them to the common shared IDs and stages.

## 1. Principles

- **Tables before charts.** With 13 requirements, 19 parent ACs and two tracks, tables expose every value.
- **Show each unit.** Plot individual requirements or parent ACs; do not use means or error bars as if two workflows were statistical replications.
- **Keep status systems separate:** test execution, evidence availability and rubric judgement.
- **Show denominators:** display counts as `x / D`; use Not calculable for zero or unfrozen denominators.
- **No composite score or radar chart.** Ordinal ratings are not summed or averaged.
- **Do not rely on colour alone.** Use letters, labels or patterns suitable for monochrome printing.
- **Use the same row order and scale** for both tracks.
- Every figure names its measure IDs, source table and snapshot/phase.
- Show missing data explicitly; never plot missing or unlogged values as zero.

## 2. Legends

| Symbol | Meaning | Status system |
|---|---|---|
| P / F / B / N in an AC cell | Pass / Fail / Blocked / Not run parent AC | Test execution |
| P, F, B, N in a count table | Number of parent ACs with each status | Count |
| D | Frozen applicable parent AC denominator, P+F+B+N | Count |
| C | Parents for which every required subcase has Pass or Fail observation | Execution completeness |
| F• | Parent is Fail but at least one required sibling subcase is not executed | Fail + incomplete |
| A / X / U / ? | Available / Confirmed absent / Unavailable / Unassessed | Evidence availability |
| 0–3 / NR / N/A | Rubric rating | Rubric judgement |

A Pass cannot be incomplete under the proposed aggregation. Blocked and Not run are incomplete. F• is a display annotation, not a fifth execution status.

For traceability grids, use two explicitly labelled origin markers: `N` = native explicit and `R` = evaluator-reconstructed. A cell may contain more than one record/state. Do not collapse conflicting states into one letter.

## 3. Canonical record tables (proposed CSV schemas)

The tables below are a proposed common data layer. Amin's adopted templates remain authoritative for required fields. Additional columns may be added when they preserve provenance and do not change the adopted evaluation rules.

### 3.1 Common metadata

Every observation row should carry:

| Column | Rule |
|---|---|
| `track` | `bmad` or `speckit` |
| `phase` | `baseline` or `change` (change is CR-01 only if CR-01 is approved/released) |
| `snapshot` | Immutable commit observed; `n/a` only for a time-based record before a first commit, with reason |
| `snapshot_state` | `clean`, `dirty` or `unknown` |
| `snapshot_alias` | Optional superseded hash mentioned in native records |
| `shared_input_revision` | Commit/revision of shared requirements and AC inventory |
| `evidence_id` | Optional/conditional evidence-index ID; required where the adopted template requires it |
| `collector` | Person who recorded the observation |
| `observed_on` | Date/time with timezone |

**Nullability:** `track`, `phase`, `collector` and `observed_on` are mandatory for observation rows. `snapshot` is mandatory except for the stated pre-first-commit time record case. `snapshot_state` must be recorded as `unknown` if not established. `snapshot_alias` and `evidence_id` may be blank when not applicable; explain missing evidence references in the evidence index or deviations log. Do not require a fabricated evidence ID.

### 3.2 `operator_results.csv` — operator-reported outcomes

One row per operator-reported test result in a specific report/run. Suggested columns:

`track, phase, snapshot, snapshot_state, snapshot_alias, shared_input_revision, evidence_id, collector, observed_on, report_id, run_id, test_id, ac_id, subcase_id, commit_hash, environment, status_reported, status_mapped, expected, actual, evidence_location, defect_id, notes, report_source`

- `status_reported` preserves the operator's exact wording; never rewrite it.
- `status_mapped` is an evaluator mapping to Pass/Fail/Blocked/Not run, or blank with a reason if it cannot be mapped.
- `ac_id` and `subcase_id` are populated only where a defensible mapping exists. Otherwise leave blank and record the unresolved mapping; do not guess.
- `report_id` identifies the source report/version. `run_id` identifies the operator's run if known.
- This table describes what the operator reported; it is not the evaluator's acceptance result and cannot by itself populate an acceptance Pass.

**Suggested unique key:** `track, phase, snapshot, report_id, test_id`. If the same test ID can appear more than once in one report, add `run_id` or a native row identifier. Do not key only by `track, phase, test_id`.

### 3.3 `ac_results.csv` — evaluator-observed subcase executions

One row per observed execution of one shared AC subcase in one run/environment:

`track, phase, snapshot, snapshot_state, snapshot_alias, shared_input_revision, evidence_id, collector, observed_on, run_id, ac_id, subcase_id, required, test_plan_revision, status, expected, actual, layer, environment, native_test, provenance, run_kind, exclusion_reason, blocker, defect_id, repair_snapshot`

**Unique key:** `track, phase, snapshot, run_id, ac_id, subcase_id, observation_id`.

`observation_id` distinguishes multiple observations of the same subcase within a run (for example, a rerun or separate environment). If the team instead guarantees one row per subcase per run, it may be omitted and the key becomes `track, phase, snapshot, run_id, ac_id, subcase_id`. The key must never stop at `run_id`, because one run contains many AC/subcase rows.

Rules:
- `status` is Pass/Fail/Blocked/Not run.
- `required` comes from the agreed test-plan revision, not from an individual operator's preference.
- `layer` is engine, GUI, build or document review (extend only by team agreement).
- `environment` records relevant OS, JDK, Maven, display/headless mode and other material conditions.
- `provenance` identifies evaluator-observed execution, raw run record or dated manual observation by a named operator.
- `run_kind` is primary or rerun. Reruns are retained but excluded from primary outcome counts.
- A skipped native test is Not run, never Pass.
- A missing required primary subcase is derived as Not run and recorded in a missing-observation/deviation note; do not invent a successful row.
- Pass/Fail require expected and actual evidence. Blocked requires a blocker. Excluded/rerun rows require a reason.
- One primary observation per required subcase/environment is expected. If there are multiple primary candidates, resolve under the adopted protocol before analysis.

### 3.4 `trace_links.csv` — requirement-to-artefact evidence

One row per shared requirement × stage × origin × evidence record:

`track, phase, snapshot, snapshot_state, shared_input_revision, evidence_id, collector, observed_on, req_id, native_id, id_mapping_origin, stage, origin, availability, authorship, verified, search_scope, rationale, artefact_ref`

- `req_id` is a shared FR/NFR ID.
- `native_id` preserves the track's own requirement label.
- `id_mapping_origin` is `operator-supplied`, `evaluator-inferred` or `identical`.
- `stage` is specification, design-decision, task, implementation or verification. A track-specific stage mapping is recorded in the evaluation manifest before counting.
- `origin` is `native explicit` or `reconstructed`.
- `availability` is Available, Confirmed absent, Unavailable or Unassessed.
- `authorship` is tool, operator, unknown or n.a.
- `verified` is Y, N, pending or blank.
- `artefact_ref` identifies the actual artefact/section/line or evidence item.

**Unique key:** `track, phase, snapshot, req_id, stage, origin, artefact_ref`. If a record is an assessment summary rather than one artefact, use a stable `record_id`. This allows multiple native artefacts for the same requirement/stage/origin without overwriting them. M03 counts distinct requirement IDs, not rows.

Validation:
- `availability` is always recorded.
- Confirmed absent requires a sufficiently complete search and `search_scope`.
- Unavailable requires a rationale; it is not evidence of absence.
- `verified` is populated only for Available evidence after review; `pending` means assessment is incomplete.
- Native explicit links require authorship tool/operator/unknown. Reconstructed links use authorship n.a. and are never counted as native.
- A native link counts toward a shared ID only through the recorded mapping.
- Confirmed absent, Unavailable and Unassessed describe the native evidence search; do not present a reconstructed link as proof that a native link existed.

### 3.5 `events.csv` — interventions, checkpoints and rework

One row per event/episode:

`track, phase, snapshot, snapshot_state, shared_input_revision, evidence_id, collector, observed_on, event_id, type, purpose, trigger_and_action, interaction_ids, artefact_before, artefact_after, is_rework, linked_defect_id, start, end, time_precision, provenance`

`type` proposal: CLARIFY, CORRECT, MANUAL_EDIT, RETRY, ENV, CHECKPOINT or UNCLASSIFIED. CHECKPOINT remains optional until team agreement. One episode and one primary type per row; additional types can be explained in notes. `is_rework=Y` requires a defect/inconsistency link or an explicit inconsistency reference. End must not precede start. Do not derive active minutes from approximate timestamps.

### 3.6 `sessions.csv` — active-work intervals

One row per person/activity interval:

`track, phase, snapshot, snapshot_state, shared_input_revision, evidence_id, collector, observed_on, session_id, person, activity, start, end, time_precision, pauses_min, active_min, interaction_count, routine_prompt_count, provenance, missing_intervals`

- `active_min` is blank if not measured.
- If `active_min` is blank, record why; M09 is Not calculable for that interval.
- `active_min` cannot exceed elapsed interval minus pauses.
- `routine_prompt_count` cannot exceed `interaction_count`.
- Do not overlap sessions for the same person unless the overlap is explained and resolved.
- Approximate/reconstructed intervals must not be mixed into measured totals.

### 3.7 `defects.csv`

One row per distinct defect:

`track, phase, snapshot, snapshot_state, shared_input_revision, evidence_id, collector, observed_on, defect_id, req_id, ac_id, expected, actual, reproduction, discovered_phase, discovered_by, fix_snapshot, rerun_status, regression_evidence`

`discovered_by` categories must be agreed (for example operator-test, operator-review, independent-eval, change-phase). Near-duplicates with the same reproduction and shared requirement/AC should be reviewed for merging.

### 3.8 `impact_register.csv`

One row per reference element or listed original-analysis element:

`track, phase, snapshot, snapshot_state, shared_input_revision, evidence_id, collector, observed_on, element_id, category, granularity_note, req_ids, reason_needed, reviewed_final_evidence, source, match_status, original_analysis_ref, reviewer_a, reviewer_b, disagreement, reference_set_revision, saw_original_before_reference`

`source` is reference or analysis-only. `match_status` is Identified, Omitted, Unknown or proposed Extra. Extra requires team agreement. Record the reference-set revision and whether reviewers saw the original analysis before freezing the reference set.

**Unique key:** `track, phase, reference_set_revision, element_id, source`. If one element is represented by multiple evidence records, add a stable row/evidence ID rather than overwriting it.

### 3.9 `ratings.csv`

One row per criterion × reviewer × rating kind × evaluation snapshot:

`track, phase, snapshot, shared_input_revision, evidence_id, collector, observed_on, criterion, reviewer, rating_kind, rating, rationale, uncertainty, status`

`rating_kind` is initial, reconciled or unresolved. Preserve both initial reviewer rows. Reconciled/unresolved rows must reference the relevant initial assessments. `rating` is 0, 1, 2, 3, NR or N/A. Agent-provisional ratings are not human reviewer ratings.

**Unique key:** `track, phase, snapshot, criterion, reviewer, rating_kind`.

### 3.10 `deviations.csv`

One row per deviation or missing-data decision:

`track, phase, snapshot, shared_input_revision, evidence_id, collector, observed_on, deviation_id, planned_rule, actual_event, affected_tracks, reason, uncertainty, decision_status, effect_on_claims`

A deviation may affect both tracks; use `affected_tracks=both` or list the affected tracks. Do not silently edit source records to make them fit a rule.

### 3.11 Validation before analysis

| Table | Key/validation |
|---|---|
| All | Mandatory common metadata present; phase and track valid; missing optional metadata explained where material |
| `operator_results` | Key includes report/test identity; raw status retained; mapping is evaluator-owned |
| `ac_results` | Key includes run and AC/subcase identity; Pass/Fail evidence present; Blocked reason present; required subcases derive from agreed plan |
| `trace_links` | Key permits multiple artefacts; mappings and stage vocabulary recorded; native/reconstructed origins not conflated |
| `events` | Unique event ID; end ≥ start; rework linked to defect/inconsistency |
| `sessions` | Unique session ID; measured time internally consistent; no unexplained overlap |
| `defects` | Unique defect ID; duplicate root causes reviewed |
| `impact_register` | Unique element/source/revision; reference set frozen before matching |
| `ratings` | Unique criterion/reviewer/kind/snapshot; initial ratings preserved |
| `deviations` | Unique deviation ID; impact on claims recorded |

A failed validation blocks the affected calculation until corrected or transparently recorded in `deviations.csv`.

## 4. Derivation rules

### 4.1 Parent AC status (F2/M01)

For each track, phase, snapshot and parent AC, use required primary subcase observations only:

1. Fail if any required subcase is Fail.
2. Else Blocked if any is Blocked.
3. Else Not run if any required subcase is Not run or has no primary observation.
4. Else Pass.

Complete = Y when every required subcase has Pass or Fail; otherwise N. Count P, F, B, N; D=P+F+B+N; C=complete parents. Show P/D, C/D, (P+F)/D and P/(P+F). Denominator zero or unfrozen inventory = Not calculable. Keep reruns visible but outside primary counts. A conflicting valid rerun is reported as non-repeatable observation and does not overwrite the primary result.

### 4.2 Change outcomes (F3/M06)

| Baseline primary | Change primary | Category |
|---|---|---|
| Pass | Pass | No regression observed |
| Pass | Fail | Regression |
| Fail | Fail | Persisting failure |
| Fail | Pass | Resolved since baseline |
| Blocked/Not run/missing | Any | Undetermined |
| Any | Blocked/Not run/missing | Undetermined |

A newly introduced change AC is reported separately as a change AC outcome, never a regression.

### 4.3 Impact (F4/M07)

For reference set R, Identified I, Omitted O, Unknown U and proposed Extra X:
- Recall = I/R; show U and label recall a lower bound when U>0.
- Not calculable when R=0 or original analysis is unavailable.
- Proposed precision = I/(I+X); Not calculable if original analysis lists no elements.
- M05 late-discovery share is calculated from distinct defect records only, never from this register.

### 4.4 Traceability (F1/M03)

M03a and M03b count distinct shared requirement IDs with at least one native-explicit, Available and verified=Y link at implementation and verification respectively, divided by the frozen requirement count. M03c counts distinct IDs whose stage assessment is complete: Available and judged (verified Y/N), or Confirmed absent with search scope. Unavailable, Unassessed and pending are not complete assessments. Reconstructed links feed M13 only.

## 5. Figure catalogue

| Figure | Priority | RQ | Measures | Source | Specification |
|---|---|---|---|---|---|
| F1 | A | RQ1 | M02, M03, M13 | `trace_links` | Two track grids: 13 shared requirements × five stages; display every relevant state and origin |
| F2 | A | RQ1/RQ2 | M01 | `ac_results` | Parent AC × track grid with P/F/B/N and F•; adjacent P/F/B/N/C/D and ratios |
| F3 | A | RQ3 | M06 | `ac_results` | Category counts and optional per-AC dumbbell plot |
| F4 | A | RQ2/RQ3 | M07 | `impact_register` | Counts of Identified/Omitted/Unknown and proposed Extra; show R and Not calculable |
| F5 | B | RQ4 | M08/M09/M12 | `events`, `sessions` | Swimlane timeline by track/person; logged sessions and events only; gaps labelled no record |
| F6 | B | RQ1/RQ2/RQ5 | M11/M14 | `ratings` | Dot plot for 0–3; NR/N/A as labelled cells; show initial and reconciled ratings separately |
| F7 | C | RQ5 | M15 | evidence index | Applicable checklist status counts by track and phase |

Per-figure rules:
- **F1:** FR rows before NFR rows. Show NFR-01..04 with no linked AC as shared input limitation C1, not as a track failure. If a cell has multiple records, show multiple markers or a detail table. Example: `N:A; R:A` means native Available and reconstructed Available. `N:X; R:A` must not be collapsed to a single A or X.
- **F2:** show failing-subcase detail below the grid. The count table must be derived from the same primary rows as the grid.
- **F3:** only compare ACs with comparable baseline/change status; list others as Undetermined.
- **F4:** never draw a zero-height bar when the reference set or original analysis is unavailable; label Not calculable.
- **F5:** show only logged periods and report coverage next to effort values. Unlogged time is not zero effort.
- **F6:** preserve both reviewers' initial ratings, even if reconciled later.
- **F7:** state the applicable checklist denominator and show Unassessed separately.

## 6. Report mapping

| Report section | Figures |
|---|---|
| Results RQ1: baseline translation and traceability | F1, F2, F6 (J1–J4) |
| Results RQ2: change analysis and implementation | F2 (change ACs), F4, F6 (J5/J6) |
| Results RQ3: missed and late items | F3, F4, defect table |
| Results RQ4: human work, time and rework | F5 and intervention/effort tables |
| Results RQ5: reproducibility | F7, F6 (J7) |
| Discussion/validity | Missing data and confounders beside each result |

Confirm this mapping against the final report outline and the adopted RQ wording.

## 7. What to avoid

- One overall winner, weighted total, composite score or ranking.
- Percentages without denominators.
- Bars for small counts without printed values.
- Treating unlogged time as zero effort.
- Treating Unassessed or Unavailable as Confirmed absent.
- Calling a failing new change AC a regression.
- Mixing baseline and change snapshots in an unlabelled panel.
- Treating operator-reported “all tests pass” as evaluator-confirmed acceptance.
- Drawing result figures before rules are adopted and evidence is collected.

## 8. Feasibility and implementation order

The tables can be prepared in a spreadsheet or generated by a short script. F1, F2 and F6 can be formatted tables; F4 can be a small horizontal bar chart; F5 benefits most from a script.

Recommended order:
1. Agree the shared inventory, required subcases, stage mapping and primary-run rules.
2. Align Amin's templates with all 19 parent ACs and the agreed schema.
3. Collect operator reports and evaluator observations separately.
4. Validate CSV keys, missing rows and provenance.
5. Produce F1–F4 first; add F5–F7 only when their underlying evidence is sufficiently complete.

The existing logs may not support a measured change-phase effort comparison. If M09/M10 are wanted, give both operators the same prospective logging instruction from the CR release point. Do not reconstruct missing measured time and present it as measured.

## 9. Provenance

Section 1 principles are pragmatic design choices; no dedicated visualisation source has been verified for this proposal. State vocabularies and aggregation must follow the adopted rubric/protocol. M01–M16 and OD-1–OD-12 come from the metric catalogue. This file proposes a data layout and visual presentation; it does not claim that the layout or measures are validated by the literature.
