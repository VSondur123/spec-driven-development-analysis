# Proposed Measurement Rules and Evaluation Rubric

**Date:** 2026-10-25. **Revision:** draft-3. **Status:** Amin's proposal for joint review with Thanh; not adopted or scored.

The measures and anchors below are local proposals. [Kitchenham et al., §4.2](https://www.cin.ufpe.br/~in1002/leituras/preliminaryGuidelinesEmpiricalResearchSE.pdf) supports defining measures and checking collection quality; it does not prescribe this scale. Adopt and version the rules before scoring either track.

## 1. Keep three kinds of information separate

| Information | Allowed states | Rule |
|---|---|---|
| Test execution | Pass / Fail / Blocked / Not run | Pass/Fail requires observed behaviour in a run or a verifiable record of that observation. Blocked needs a concrete obstacle; a pending template is Not run. |
| Evidence availability | Available / Confirmed absent / Unavailable / Unassessed | Confirmed absent requires an inspected, sufficiently complete snapshot or record set and a search scope. Unavailable means access/records are insufficient to decide. |
| Rubric judgment | 0 / 1 / 2 / 3 / NR / N/A | NR means not rated or insufficient evidence. N/A needs phase-specific justification and reviewer agreement. Zero requires observed deficiency, not missing access. |

For artefact links, also label origin: **Native explicit** (the workflow/operator supplied the link) or **Reconstructed** (the evaluator inferred the relationship). Within native links, distinguish tool-generated from operator-added where the interaction history supports that distinction; otherwise record authorship as unknown. Record unresolved candidate links separately. An evaluator mapping does not become a native capability by being written into our matrix, and an operator-added link does not prove automatic tool support.

Keep execution provenance: evaluator-observed run, inspected raw run record, or a dated manual observation by a named operator. Preserve the original report separately when it cannot be verified. A bare “all tests pass” assertion without criterion-level support cannot populate acceptance passes. Record the layer observed: engine, GUI, build, or document review.

An unmet environmental prerequisite or unavailable display is Blocked when it actually prevents an attempted check. A check with no attempt or usable observation is Not run. Once prerequisites are met, an observed departure from the expected product/build behaviour is Fail. A nonzero native-suite exit alone does not establish which shared AC failed: inspect the output and map the actual observation.

## 2. Counting and aggregation

For each track, snapshot, and phase, let `P`, `F`, `B`, and `N` count Pass, Fail, Blocked, and Not run parent acceptance criteria. Let `D = P + F + B + N`, the total frozen applicable criteria. The current `main` draft contains 16 baseline criteria, but it is not frozen: derive `D` from the adopted input revision before scoring. There are no baseline exclusions in this proposal. Missing or blocked checks stay in `D`.

- **Verified pass coverage:** `P / D`. Report it as a fraction and percentage alongside `F`, `B`, and `N`. It is coverage of the frozen checks, not proof of all requirements.
- **Execution completion:** `C / D`, where `C` counts parents for which every required subcase has a Pass or Fail observation. A failure with unexecuted sibling subcases is decisive but incomplete.
- **Decisive parent outcomes:** `(P + F) / D`. This is not execution completion.
- **Pass share of decisive outcomes:** `P / (P + F)`, if there are decisive outcomes. Always show it with completion and total coverage so a small executed subset is visible.
- If a denominator is zero or the applicable set is not frozen, show `Not calculable`, not zero or 100%.

Keep subcases under their parent AC. A parent is Fail if any required subcase fails; otherwise Blocked if any is blocked; otherwise Not run if any is unexecuted; otherwise Pass when all required subcases pass. Retain every subcase outcome so this precedence cannot hide mixed evidence. Subcases do not increase `D`.

For illustration only, suppose three fictional parent checks have subcases `(Pass, Pass)`, `(Fail, Not run)`, and `(Not run)`. Then `P=1`, `F=1`, `B=0`, `N=1`, `D=3`, and `C=1`. Pass coverage and execution completion are both `1/3`; decisive outcomes are `2/3`. This is an example of the counting rule, not calculator evidence.

Select the initial planned run at each frozen snapshot as the primary evaluation record. Preserve reruns and their reasons. If a run used an invalid environment/procedure, retain it with the exclusion rationale and apply the same rule to both tracks. Conflicting valid reruns are reported as non-repeatable observations; do not silently replace the initial result with a later pass. Repairs require a new snapshot and separate results.

Report any approved change-phase exclusions with reasons and the excluded count. Never drop a failing check because it is inconvenient. Requirements, native test counts, and acceptance-check counts have different denominators.

## 3. Proposed metric definitions

| Measure | Definition and unit | Collection/evidence | Interpretation and bias |
|---|---|---|---|
| Acceptance coverage and completion | Fractions above, per phase/snapshot | Criterion/subcase results and raw observations | Higher coverage supports correctness on those checks only; limited by their scope |
| Native requirement-to-implementation traceability | Number of frozen requirement IDs with a verified native explicit link to relevant implementation evidence, divided by all frozen phase requirement IDs | Native artefacts and content verification | Baseline denominator is 13; missing/unassessed entries stay visible. Appropriate evidence can be source, configuration, build instructions, or other required documentation; do not require a code target for a documentation-only NFR |
| Native requirement-to-verification traceability | Number of frozen requirement IDs with a verified native explicit link to an appropriate test/manual check, divided by the same requirement denominator | Native artefacts, actual test/check content | A linked test need not pass; quality and execution are separate. Label manual checks |
| Reconstructed traceability | Separate counts of evaluator-inferred implementation and verification links | Reviewer-created mapping with rationale | Useful for analysis, but not credited as native links |
| Traceability assessment completion | Requirement IDs assessed for each link type / all frozen phase requirement IDs | Evidence availability and searched scope | A native-link fraction with incomplete assessment is only a verified lower bound, not established absence |
| Human interventions | Count distinct human clarification, correction, manual edit, retry, or configuration/troubleshooting episodes, by category and phase | Timestamped interaction/event log | One continuous episode for the same purpose counts once; link multiple messages. Routine workflow prompts are separately counted, not automatic interventions. More interventions may mean useful oversight |
| Rework | Count episodes correcting previously produced artefacts/code/tests because of defects, omissions, or inconsistency | Event log with before/after evidence | Excludes intended CR scope additions and normal first-time task progression; fewer commits does not imply less rework |
| Active effort | Sum recorded person-minutes in disjoint active work intervals, by operator/reviewer and phase | Session start/end, pauses, activity, provenance | Include prompting, review, editing, and attended testing. Separate setup/training and evaluation from implementation. Overlapping categories cannot double-count one person's time |
| Elapsed duration | Wall-clock phase-end minus phase-start, with timezone; hours | Timestamped phase boundaries | Includes waits/breaks; report alongside active effort. If either boundary is unknown, duration is unavailable |
| Defects | Distinct reproducible departures from frozen requirements | Defect IDs, expected/actual, reproduction and evidence | Several failed checks can share one defect; one failed check may reveal several defects. Do not equate the counts |
| Regressions | Previously passing baseline checks that fail at the change snapshot under comparable conditions | Paired baseline/change observations | Count at parent AC level and list subcases. Unobserved baseline behaviour gives unknown regression status |
| Change-impact omissions | Reviewer-agreed required affected elements absent from the initial saved pre-coding analysis | Original analysis, released CR, reviewed diffs/specs/tests, affected-element register | Agree on categories and granularity before matching the original analysis; include design/interface elements where relevant. Count within categories, without duplicate elements; do not compare raw file totals across different architectures. If either the reference set or original analysis is unavailable, omission counts are Not calculable |
| Reviewer agreement | Equal initial numeric ratings / items independently numerically rated by both reviewers | Preserved independent rating sheets | Report numerator/denominator, NR/N/A counts, differences and reconciliation; agreement is not correctness |

Numeric/UI clauses without requirement IDs are retained in a separate scope audit. They are not silently added to the 13-ID denominator. If later inputs create new requirement IDs or checks, record the new frozen inventories and denominators before evaluating them.

Unknown time/intervention records do not become zeros. Provide logged-period coverage, missing periods, and provenance. Retrospective estimates are separate from measured totals; do not compute a comparative effort ratio when the collection periods are not comparable.

## 4. Proposed judgment anchors

Rate each criterion independently for each track/phase. Apply only the strongest anchor fully supported by inspected evidence. These are ordinal descriptions, not equal numerical intervals. Do not total or average them into an overall score.

For the anchors, a **material gap** is an evidenced omission, ambiguity, contradiction, or missing link that prevents implementing or verifying a frozen requirement, misrepresents required behaviour, or prevents reproducing a reported result. A **minor gap** affects navigation or documentation detail while the required behaviour, relationship, and supporting evidence remain unambiguous. Record the affected requirement, why the gap matters, and both reviewers' judgment. Do not invent percentage cutoffs after inspecting a track. If evidence cannot distinguish adjacent anchors, retain NR or a provisional rating with that uncertainty until review resolves it.

| ID / criterion | 0 | 1 | 2 | 3 |
|---|---|---|---|---|
| J1 Specification structure and clarity | Inspected artefacts provide no usable representation of the evaluated scope | Representation exists with at least one material scope/clarity gap | Scope and acceptance expectations are unambiguous, with only minor documentation gaps | Evaluated scope, verifiable expectations, and clarification decisions are explicit and consistent, with no identified gap |
| J2 Traceability across native artefacts | Inspection establishes no usable native links for the evaluated scope | Some native links are usable, but at least one material relationship is missing or incorrect | Required native relationships are verifiable, with only minor navigation/documentation gaps | Every evaluated requirement has verified native relationships through relevant stages to implementation and verification, with no identified gap |
| J3 Requirements-informed verification | Inspected tests/checks establish no meaningful connection to the evaluated requirements | Representation has at least one material omission or an inappropriate verification layer | Required behaviours/subcases are represented at suitable layers, with only minor documentation gaps | All frozen acceptance behaviours and required subcases, including their specified negative/boundary cases, are explicitly mapped to appropriate verification layers |
| J4 Non-functional requirement treatment | Inspected artefacts do not address the frozen NFRs | NFR treatment contains at least one material decision or verification gap | Each NFR has a decision and suitable verification treatment, with only minor documentation gaps | Each NFR is explicitly traced to decisions and suitable evidence; qualitative limits are recorded, with no invented thresholds or identified gap |
| J5 Pre-coding change-impact analysis | Sufficient chronology establishes coding began without an impact analysis | Pre-coding analysis has at least one material omission from the agreed affected-element set | Pre-coding analysis identifies all material affected elements, with only minor explanation/linking gaps | Saved pre-coding analysis explicitly covers the agreed affected-element set and its relevant risks/dependencies, with no identified gap |
| J6 Change propagation and consistency | Inspection establishes the required change has not propagated to relevant implementation or native artefacts | Change reaches some relevant stages but at least one material inconsistency remains | Change reaches relevant native stages and verification, with only minor documentation gaps | Relevant native artefacts, implementation, and verification consistently reflect the released change, with no identified gap in the evaluated scope |
| J7 Evidence and reproducibility | Inspected submission lacks an identifiable snapshot or usable build/test procedure | Submission is identifiable, but at least one material evidence/reproduction gap prevents following a result | Required records support the reported findings and reproduction steps, with only minor documentation gaps | Applicable records are complete and a documented independent reproduction attempt confirms the reported build/test outcomes; failures and limitations are retained |

Baseline uses J1–J4 and J7; J5–J6 are N/A because change has not been released/evaluated. This is the phase applicability rule, not a score. Change uses all seven. Do not score a missing chronology as J5=0; use NR unless absence before coding is established. Different filenames or the absence of a separate HLD/LLD are not deficiencies by themselves: assess equivalent native stages. A demonstrably missing capability is still a finding; do not credit a reconstructed link as native merely to avoid a low J2 rating.

For J2–J4 and J7, record the concrete evaluated scope and gaps beside the rating. J3 measures verification representation, not successful execution, and does not require new tests outside the agreed scope. J7=3 requires a documented independent reproduction attempt confirming reported outcomes, including any reported failures; successful functionality is measured separately. Without such an attempt, J7 is at most 2 if the record otherwise supports that anchor. A setup blockage or conflicting reproduction is recorded as a limitation and assessed against the material-gap rule. Preserve all Pass/Fail results even where a high documentation rating is justified.

## 5. Rating review

Use two independent reviewers where possible. Each rating needs evidence, rationale, confidence/uncertainty, reviewer, and date. Agent suggestions remain provisional and are not counted as independent human ratings. Resolve differences by referring to anchors and evidence, recording both original ratings and the final decision. Where evidence conflicts or reviewers cannot agree, retain NR/unresolved rather than fabricating consensus. Amendments to anchors require a new revision and consistent reassessment of affected rows in both tracks.
