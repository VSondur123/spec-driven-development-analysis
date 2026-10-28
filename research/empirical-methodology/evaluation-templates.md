# Evaluation Templates — Unpopulated

**Date:** 2026-10-25. **Revision:** draft-3. **Status:** Draft instruments for team review; no evaluation performed.

The inventory reflects `main` at `cb1a051`: 13 requirement IDs and 16 draft acceptance criteria. Reconcile it with the eventual frozen shared inputs before scoring; do not treat counts or unresolved behaviours as already agreed.

Use with the [protocol](protocol-recommendations.md) and [rubric](evaluation-rubric-proposal.md). Copy the relevant tables into an authorised research output for each track/phase; do not fill this master with results. Keep the other track's evidence away from operators still implementing. `Pending`, `Unassessed`, and `NR` below are template markers, not findings.

## 1. Evaluation manifest

| Field | Value |
|---|---|
| Evaluation ID / date / timezone / evaluator | Pending |
| Phase | Pending |
| Protocol and rubric revision / actual adoption date | Pending |
| Test-plan revision / primary-run selection rule | Pending |
| Team readiness/access decision reference | Pending |
| Shared requirements / acceptance snapshot | Pending |
| Released CR revision and release record, if applicable | Not applicable until change release |
| BMAD root / frozen commit / clean state | Pending |
| Spec Kit root / frozen commit / clean state | Pending |
| Operator and agent/model per track | Pending |
| Environment/toolchain/configuration per track | Pending |
| Reviewers / prior exposure / independence limits | Pending |
| Evaluator tools/model / skill revision / human verification | Pending |
| Historical evidence gaps / amendments / deviations | Pending |

Attach exact native commands and observed outcomes later. The two roots may be folders in one repository or separate checkouts. Record each root and repository revision; do not assume two distinct repositories.

## 2. Requirement coverage matrix

Create one copy per track and frozen phase. The baseline inventory below is taken from the shared requirements; labels are navigation aids, not replacements for their text. Each artefact cell later contains availability, native/reconstructed origin where relevant, and evidence IDs/paths. For native links, record tool-generated, operator-added, or unknown authorship where supported. Implementation evidence can include source, configuration, or required documentation, as appropriate to the requirement. Start with Unassessed, not a cross implying inspected absence.

| Requirement | Scope label | Specification | Design/decision | Task | Implementation evidence | Verification and AC links | Outcome evidence / gap |
|---|---|---|---|---|---|---|---|
| BASE-FR-01 | Offline GUI launch | Unassessed | Unassessed | Unassessed | Unassessed | Unassessed | Unassessed |
| BASE-FR-02 | Numeric entry | Unassessed | Unassessed | Unassessed | Unassessed | Unassessed | Unassessed |
| BASE-FR-03 | Four operation selections | Unassessed | Unassessed | Unassessed | Unassessed | Unassessed | Unassessed |
| BASE-FR-04 | Request calculation and see result | Unassessed | Unassessed | Unassessed | Unassessed | Unassessed | Unassessed |
| BASE-FR-05 | Clear/reset | Unassessed | Unassessed | Unassessed | Unassessed | Unassessed | Unassessed |
| BASE-FR-06 | Invalid/missing-input feedback | Unassessed | Unassessed | Unassessed | Unassessed | Unassessed | Unassessed |
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
| baseline-requirements.md §5 | Clear controls/labels, identifiable input/operation/result/error state, simple interface | Unassessed | Unassessed |

The exact GUI pattern, numeric display precision/rounding, calculation trigger, and error presentation remain open in baseline-requirements.md §9. Populate observations only against the agreed revision; the §5 audit concerns the general usability expectations already stated, not a particular set of controls or inline-error policy.

Copy the frozen requirement-to-AC associations from the shared acceptance file when evaluating. If associations do not fully verify a requirement, document the gap; do not silently change shared checks. “No defect found on evaluated checks” is narrower than proving a requirement completely satisfied.

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

Do not compute zeros or percentages from this unused template. Populate real run records first. For CR-01, generate the change inventory only after release, and keep baseline regression outcomes at the change snapshot separate from original baseline outcomes.

### Detailed execution record — one per subcase/run

| Field | Value |
|---|---|
| Run ID / parent criterion / subcase | Pending |
| Track / phase / frozen commit / shared-input revision | Pending |
| Test-plan revision / identical steps and inputs | Pending |
| Initial state / reset steps / execution order | Pending |
| Expected result / authoritative clause | Pending |
| Actual result / status | Pending / Not run |
| Method/layer / observer / date / environment | Pending |
| Raw output or manual observation / evidence ID | Pending |
| Provenance: own run, raw record, or operator observation | Pending |
| Blocker / defect ID / repair revision / rerun link | Pending |
| Primary run or rerun / exclusion reason / conflicting observations | Pending |

Use the adopted criteria to define required subcases and agree on the same execution steps/inputs for both tracks. The current draft does not enumerate malformed-input variants or a keyboard-trigger check; do not silently add them as mandatory criteria. Parent aggregation follows the rubric; it never hides subcase rows. Record whether every required subcase has a Pass/Fail observation: a parent Fail can still be incompletely executed.

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

| Descriptive measure, phase and snapshot required | BMAD | Spec Kit | Definition, denominator, provenance, or caveat |
|---|---|---|---|
| Acceptance P / F / B / N, fully executed C, and total D | Unassessed | Unassessed | Current draft has 16 ACs; derive D from the adopted revision and count only populated run records |
| Verified pass coverage / execution completion | Unassessed | Unassessed | P/D and C/D; show fractions and percentages |
| Decisive parent outcomes / pass share of decisive outcomes | Unassessed | Unassessed | (P+F)/D and P/(P+F); not substitutes for completion |
| Native implementation links / verification links | Unassessed | Unassessed | Baseline denominator 13; report assessment completion |
| Reconstructed implementation/verification links | Unassessed | Unassessed | Separate from native links |
| Interventions by category / rework episodes | Unassessed | Unassessed | Missing logs do not imply zero |
| Active effort / recorded-period coverage | Unassessed | Unassessed | Person-minutes, phase/activity, measured vs estimated |
| Elapsed duration | Unassessed | Unassessed | Exact start/end/timezone required |
| Defects / regressions | Unassessed | Unassessed | Distinct IDs; regressions require paired evidence |
| Change-impact omissions | Unassessed | Unassessed | Change only; agreed affected-element set required |

## 5. Evidence index

Use evaluator evidence IDs such as `E-001` without renaming native artefacts.

| Evidence ID | Track / phase / snapshot | Path and section/lines/test name | Observation date / observer / method | Provenance / availability / claim supported |
|---|---|---|---|---|
| To populate | Pending | Pending | Pending | Pending |

Prefer immutable commit-and-path references. For evidence outside the code snapshot, record its own revision plus the code snapshot it observed. Line numbers alone can drift. Original reports that cannot be verified remain explicitly unverified.

## 6. Intervention, effort, defects, and deviations

| Event ID / track / phase | Timestamp/timezone | Category / purpose | Human action and trigger | Interaction IDs / artefact before-after / evidence | Rework or defect link |
|---|---|---|---|---|---|
| To populate | Pending | Pending | Pending | Pending | Pending |

| Session ID / person / phase/activity | Start / end / timezone | Pauses | Active person-minutes | Source / measured or retrospective / missing intervals |
|---|---|---|---|---|
| To populate | Pending | Pending | Pending | Pending |

| Defect ID / track / snapshot | Requirement / AC | Reproduction / expected / actual | Evidence / discovered phase | Fix snapshot / rerun / regression evidence |
|---|---|---|---|---|
| To populate | Pending | Pending | Pending | Pending |

| Deviation or missing-data ID / date | Planned rule / actual event | Track(s), phase, reason | Evidence / uncertainty | Agreement or pending decision / effect on claims |
|---|---|---|---|---|
| To populate | Pending | Pending | Pending | Pending |

## 7. Independent ratings and findings

| Track / phase / criterion | Reviewer A original rating / evidence | Reviewer B original rating / evidence | Reconciled rating or unresolved | Reason / date / uncertainty |
|---|---|---|---|---|
| To populate | Pending | Pending | Pending | Pending |

| Finding ID / RQ | Observation and evidence IDs | Interpretation | Contradictory/missing evidence | Validity limitation / reviewer |
|---|---|---|---|---|
| To populate | Pending | Pending | Pending | Pending |

Report exact initial agreement with its denominator, unresolved ratings, and any single-reviewer limitation. If neither reviewer has supplied an independent rating, agreement is Not calculable. These empty rows are record structures, not synthetic results.

## 8. Change-impact reference and matching — after release only

Do not populate this register or read CR-01 during preparation. At authorised change evaluation, record the released input revision, baseline/change snapshots, reviewers, agreed element granularity, and whether reviewers saw the original impact analysis before defining the reference set. Use one register per track for architecture-specific elements; compare common requirement-level items directly and keep other categories separate.

| Required affected element / category | Requirement and reason it needs change | Reviewed final evidence or still-missing work | Original pre-coding analysis reference | Identified / Omitted / Unknown | Reviewers / disagreement / reference-set revision |
|---|---|---|---|---|---|
| To populate | Pending | Pending | Pending | Unknown | Pending |

Record the original analysis revision and its evidence of preceding coding separately. An omission count is Not calculable until both the agreed reference set and the original analysis are available. Changed files alone neither define the reference set nor prove a complete impact analysis.
