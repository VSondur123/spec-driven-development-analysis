# Baseline vs Change Analysis

**Study:** Spec-Driven Development Analysis — BMAD and GitHub Spec Kit Java Swing Calculator  
**Phase:** Cross-phase synthesis (Experiment 1 Baseline → Experiment 2 CR-01)  
**Prepared:** 2026-11-06  
**Method:** Team-confirmed shared protocol/rubric, repository `evaluate-sdd-workflows/SKILL.md` (draft-5), GQM metric catalogue (draft-6)  
**Status:** Evidence-grounded comparative analysis; not a new test execution or an independent human rubric assessment  
**Intended repository location:** `final-analysing/Baseline_vs_Change_Analysis_Report.md`

## Abstract

This analysis compares **each workflow with itself across two phases**: the initial calculator implementation and the later CR-01 scientific-operations extension. It then contrasts the two phase transitions. Experiment 1 reported **19/19 baseline parent acceptance checks Pass** for both BMAD and GitHub Spec Kit. Experiment 2 reported **22/22 CR-01 parent checks Pass** and **19/19 baseline regression checks Pass** for both. Consequently, no baseline regressions were *reported* in the change-phase evidence. These are distinct acceptance populations: **19 baseline parents and 22 CR parents**. Their raw counts must not be treated as comparable measures of productivity or change difficulty.

Both workflows preserved the reported baseline functionality while adding square, square root and reciprocal operations. BMAD extended a story/architecture-driven process; Spec Kit extended its constitution/specification/plan/task process. Both recorded change-related surprises and corrections. Spec Kit's CR-01 independent evaluator package includes richer frozen-snapshot GUI/build evidence; BMAD's independent evaluator records were supplied locally and have weaker repository-level raw provenance. Neither workflow can be ranked for effort, native traceability coverage, impact recall or rework rate without additional standardized data.

## 1. Research purpose and comparison design

### 1.1 Questions

- **Q3.1:** How did BMAD's application quality, workflow, traceability, documentation and rework change between baseline and CR-01?
- **Q3.2:** How did Spec Kit's corresponding properties change?
- **Q3.3:** What does the *difference between the two transitions* support about change management, regression control and evidence quality?

This is a **within-workflow, cross-phase** comparison followed by a cautious **between-workflow synthesis**. The unit is the complete tool–agent–operator workflow run, not an abstract tool in isolation. Different operators, AI assistance, environments, suite designs and documentation conventions limit causal attribution.

### 1.2 Source hierarchy and procedure

The shared requirements, frozen acceptance criteria, CR-01 specification and experiment log determine the evaluated scope. The team has confirmed the shared protocol and rubric as its adopted working methodology despite outdated “Proposal” document headers; the precise original adoption date remains unspecified. The research SKILL and its companion templates, checklist, methodology review and validity-threat register supply the evaluation sequence: establish snapshots; inventory native artifacts; verify links and execution provenance; calculate supported measures; distinguish original and rerun outcomes; review J1–J7; integrate qualitative and quantitative findings. This report integrates those steps rather than creating a separate SKILL compliance chapter.

**Source records used:** Experiment 1's final comparison report/workbook, Experiment 2's integrated comparison report/final workbook, and repository track-specific acceptance, impact and evaluation records. This document does **not** assert that its author independently reran either application or reviewed every source-code-level native traceability link. “Reported Pass” is retained as reported unless a specific independently inspectable observation is identified.

### 1.3 Frozen scope and snapshots

| Dimension | Baseline | CR-01 change |
|---|---|---|
| Common functionality | Four binary operations, validation, GUI and documented build | Adds `x²`, `√x`, `1/x` and preserves baseline |
| Shared requirements | 13 IDs: BASE-FR-01–08 and BASE-NFR-01–05 | 11 change functional requirements: CR-FR-01–11 |
| Parent acceptance checks | BASE-AC-01–19 (19) | CR-AC-01–22 (22) |
| Baseline preservation after change | Not applicable to initial phase | BASE-AC-01–19 rerun (19) |
| BMAD frozen reference | `740c78de` (reported baseline-frozen) | `72f2783` (reported local `bmad-cr01-frozen`; tag not pushed) |
| Spec Kit frozen reference | `88f56d0979e2fae1fb5d59e8681975e3a1fc2a27` | `9e523775e31c42bb9f164ddf84e9ff736b2c2fdc` |

The **BMAD change snapshot and local evaluator execution** need an independently documented clean checkout/immutable-reference check before claiming the same degree of reproducibility as the Spec Kit CR snapshot. The Spec Kit CR evaluator used a clean detached clone; its supplementary offline check was human-reported separately.

## 2. Quantitative phase comparison

### 2.1 Acceptance and regression (M01, M06)

| Workflow | Baseline: BASE-AC Pass | Change: CR-AC Pass | Baseline recheck after change | Reported observed regressions |
|---|---:|---:|---:|---:|
| BMAD | 19/19 (100%) | 22/22 (100%) | 19/19 (100%) | 0 |
| GitHub Spec Kit | 19/19 (100%) | 22/22 (100%) | 19/19 (100%) | 0 |

**Interpretation:** Both workflows reported complete pass coverage on the frozen parent inventories in each phase. The relevant within-workflow regression comparison is **BASE-AC-01–19 at baseline versus those same 19 checks after CR-01**. The CR-AC 22/22 result measures the new feature, not a 3-check improvement over baseline. Neither 100% outcome establishes exhaustive correctness, complete subcase execution under the common rubric, or an overall quality score.

**Provenance:** BMAD baseline evidence includes a clean-build caveat: the baseline BASE-AC-15 check used a copied file set rather than a true clean Git clone; the change-phase independent BMAD sheets were supplied locally, while the repository `main` evaluator sheet was blank when previously inspected. Spec Kit's original independent change evaluation had an **offline check Blocked**, later supplemented by Amin's manually reported offline Pass. The combined final reported 19/19 and 22/22 should not erase that chronology. Subcase-level fully executed parent counts **C** are not assumed from aggregate reported P.

### 2.2 Native tests (M16 context, not an outcome ranking)

| Workflow | Baseline native suite | CR-01 native suite | Observed increase | Qualifications |
|---|---:|---:|---:|---|
| BMAD | 172 | 316 | +144 (+83.7%) | Baseline separate later 174-test LOG-15 run; CR headless run had 80 skipped; display run 316 with 0 failures |
| GitHub Spec Kit | 81 | 116 | +35 (+43.2%) | Clean CR verification: 116 tests, 0 failures/errors/skipped; baseline 81 |

These are **within-track changes in reported test counts**, not numbers of independent shared acceptance checks. The BMAD and Spec Kit suites differ in design, scope, runner and GUI conditions; a larger count or percentage increase is **not** evidence of greater quality, coverage or efficiency. The BMAD 174-case supplementary baseline should not be silently substituted for the selected 172-case baseline run.

### 2.3 Metric-by-metric cross-phase readiness (M01–M16)

| Metric | Cross-phase use | Evidence status and interpretation |
|---|---|---|
| M01 | Baseline acceptance vs CR acceptance; preserve denominators | **Reported results available**: 19/19 and 22/22 per track; execution provenance differs |
| M02 | Requirement status, where adopted/applicable | **Partial**: native requirements differ by phase; no verified comparable requirement-level rating |
| M03 | Native explicit traceability of shared IDs | **Partial**: baseline 13-ID candidate chains; CR 11-ID inventory; no semantically verified comparable numeric coverage |
| M04 | Defect discovery across phases | **Descriptive only**: known cases, no exhaustive harmonized defect census |
| M05 | Late defect share (if adopted) | **Not calculable** without common discovery-stage classifications and complete counts |
| M06 | Baseline-to-change regression | **Reported**: 0/19 observed regressions per track, subject to evidence caveats |
| M07 | Change-impact omissions | **Not calculable** without agreed affected-element reference set and matching rules |
| M08 | Human interventions | **Partial** event records; incomplete comparable observation windows |
| M09 | Active effort | **Not calculable**: no comparable measured person-minute sessions |
| M10 | Elapsed duration | **Not calculable**: consistent phase boundary timestamps absent |
| M11 | J1–J7 evidence-linked rubric | **Not rated**: independent human review set incomplete |
| M12 | Rework episodes | **Partial**: documented corrections, not a harmonized exhaustive event count |
| M13 | Reconstructed traceability | **Partial**; evaluator inference kept separate from native M03 |
| M14 | Reviewer agreement | **Not calculable** without two independent human rating sets |
| M15 | Evidence completeness | **Not calculable** as a rate until applicable EC obligations and collectors are assessed |
| M16 | Contextual counts and environments | **Available in part**: native test counts and some environments; not a score |

The two experiments use the same *definitions* but not necessarily the same *applicability*. For example, change-impact M07 is meaningful for the released change phase, not a fictitious baseline impact event. **NR / unavailable / not calculable is not zero**.

## 3. BMAD: Baseline → CR-01

### 3.1 Application quality and preservation

BMAD's baseline report records 19/19 parent criteria Pass, with a documented limitation on clean-checkout reproducibility. The CR-01 operator record reports 22/22 change criteria Pass and 19/19 baseline rechecks Pass. The local evaluator's manual sheets also reported those totals, but the evidence has not been established as a complete frozen-commit, timestamped, raw independent run. The available evidence therefore supports **reported functional preservation and successful feature extension**, not proof of an independently reproduced 41-check comprehensive run.

The change required new unary operation selection, single-operand behavior, domain and zero handling, recovery and UI states. Its 316-case display test run had no reported failures; the headless run had 80 skipped cases. Baseline and change counts show test-suite growth but not a validated coverage gain.

### 3.2 Requirements, architecture and change impact

At baseline, BMAD used a PRD, an Architecture Spine, three epics and nine stories, with story-specific verification and review. CR-01 was handled through an impact analysis and follow-on story/test updates. The CR-01 impact review records unexpected work involving **two GUI operation-button tests** and an **`assertInitialState` helper**, demonstrating that some consequences emerged during implementation/review rather than being fully captured in the original prediction. These observations are informative, but they do not establish an M07 omission rate without a shared reference register.

### 3.3 Defects, interventions and rework

BMAD's CR record includes a **test-input expectation issue** (`1e41` versus `1e40`), test-only review corrections over three rounds, and a documentation/Javadoc correction. The cited numerical issue is **not a confirmed product defect**. The episodes show iterative review and correction; without a common unit of work and exhaustive intervention/session logs, their count cannot be compared as a rate with Spec Kit's corrections or used to infer slower development.

### 3.4 Traceability and documentation continuity

The baseline's 13 shared requirements have candidate native document–code–test chains, while the CR report inventories 11 change functional requirements and 22 acceptance checks. The two populations are different, and link origin/semantic correctness remain only partially verified. The evidence supports a **qualitative continuity** from BMAD planning and story records into change impact and tests, but **not** a numerical M03 improvement or deterioration. The independently retained CR evaluator evidence is weaker than Spec Kit's in the inspected record set; that is a **reproducibility/evidence observation**, not a proof of worse BMAD code.

## 4. GitHub Spec Kit: Baseline → CR-01

### 4.1 Application quality and preservation

Spec Kit's baseline operator record reports 19/19 parent criteria Pass, with an 81-test native suite and an interim-commit clean-checkout qualification. The CR-01 operator record reports 22/22 change checks Pass, and the frozen-snapshot independent evaluation eventually reports 22/22 after its offline follow-up. The regression evaluation reports 19/19 after the same manual offline follow-up; its earlier automated phase had 18/19 comparable Pass outcomes and an offline Blocked check. Thus the current reported result is **preservation with no observed regressions**, while the initial Blocked status remains historically visible.

The clean CR snapshot ran 116 native tests with no reported failures/errors/skips. Its evaluator retained build output, real-window GUI observations, screenshots, environment details and a manual offline follow-up. These records make the observed CR transition **more reproducible in the available independent evidence** than the BMAD change evaluation, subject to offline-procedure and evaluator-exposure limitations.

### 4.2 Specifications, tasks and change propagation

At baseline, Spec Kit used a constitution, specification, plan, research/data-model records, contracts, a requirements checklist and **34 tasks**. The change phase added its scientific-operation specification/plan/tasks and required updates to the calculator implementation, GUI and tests. Its original impact analysis predicted five source files to change (all changed) and five to remain unchanged (all remained unchanged), but also encountered an **unanticipated constitution update**, an unpredicted `OperationTest.java` file and an `errorAreaSize` GUI helper. These observations show useful initial impact anticipation **and incomplete scope prediction**. Without a jointly approved affected-element oracle, neither perfect recall nor a precise omission percentage is justified.

### 4.3 Defects, interventions and rework

Spec Kit's CR operator record describes **D-CR-01 / DL-57**, a genuine UI defect: the negative-square-root error message was clipped because the display area was too narrow. A screenshot exposed the defect; the panel was fixed and a test added, with a subsequent screenshot showing the full message. A separate incorrect test expectation was also corrected. This is direct evidence of **visual review detecting a product defect**, not proof that Spec Kit produced generally more defects than BMAD. The correction history demonstrates the role of native verification and feedback but lacks a standardized effort denominator.

### 4.4 Traceability and documentation continuity

Spec Kit's baseline specification/plan/tasks and change-phase spec/plan/tasks form a navigable document chain, supplemented by test and impact records. Its baseline internal requirements/task count is **not** interchangeable with the 13 shared baseline IDs; CR internal identifiers must similarly be mapped to the 11 shared CR-FR IDs. M03 requires native, semantically verified links; M13 records evaluator-reconstructed links separately. The current material supports a qualitative assessment of structured change documentation but not a comparable numeric M03 phase delta.

## 5. Direct comparison of the two transitions

| Dimension | BMAD Baseline → Change | Spec Kit Baseline → Change | Supported interpretation |
|---|---|---|---|
| Common acceptance | 19/19 baseline; 22/22 CR | 19/19 baseline; 22/22 CR | **Tie in reported parent Pass coverage**, not necessarily equal evidence quality |
| Baseline preservation | 19/19 rechecks reported Pass | 19/19 after manual offline follow-up | **No observed/reported regressions** in either; preserve provenance |
| Change planning | Architecture/story-driven impact and review | Constitution/specification/plan/task-driven impact | Different native workflows; no universal process winner |
| Impact surprises | GUI button tests, initial-state helper | Constitution update, extra test, GUI helper | Both had missed/unexpected elements; **M07 not calculable** |
| Defect correction | Test-input issue and review/test/document changes | UI clipping product defect plus test expectation correction | Different defect types; **do not rank by raw counts** |
| Native test growth | 172 → 316 (+144) | 81 → 116 (+35) | Context only; not a quality or productivity comparison |
| Native traceability | Candidate baseline and CR links | Candidate baseline and CR links | **M03 phase changes not numerically verified** |
| Independent CR evidence | Local manual evaluator reports, limited retained raw detail | Frozen-clone build, GUI screenshots/TSV, manual offline follow-up | **Spec Kit stronger independent reproducibility in inspected evidence** |
| Effort / elapsed time | Not comparable | Not comparable | **No defensible faster-workflow conclusion** |
| J1–J7 / reviewer agreement | No complete paired ratings | No complete paired ratings | **No approved overall rubric ranking** |

### 5.1 Regressions

The most direct longitudinal measure is M06: a baseline check that previously passed but now fails under comparable conditions. Neither track reports such a transition among BASE-AC-01–19. However, differences in execution layer, clean-checkout procedure and the manually resolved offline check limit the strength of a blanket claim of “regression-free.” The precise supported conclusion is **no regression observed in the reported/reviewed check sets**.

### 5.2 Change-impact analysis

Both teams documented predicted and observed affected elements. BMAD identified review-discovered GUI/test-helper consequences; Spec Kit identified a constitutional scope correction, a test file and a helper not fully anticipated. These examples demonstrate that CR-01 propagated beyond a single mathematical operation implementation. They **cannot** be converted into M07 precision/recall without an agreed reference set defined independently of the final diff, a fixed element granularity and adjudication of uncertain elements. A retrospective narrative is not equivalent to the pre-coding prediction.

### 5.3 Traceability and rework

The correct cross-phase traceability comparison would compute native explicit link coverage separately for **13 baseline shared IDs** and **11 CR shared IDs**, using identical semantic-verification rules, and then interpret the percentages with different denominators. Current inventories are candidate-based; therefore no defensible traceability delta is presented. Likewise, BMAD's review patches and Spec Kit's UI/test corrections are concrete examples, but event/session coverage is incomplete. Rework episodes should be defined consistently and deduplicated before computing M12.

### 5.4 Documentation usefulness

BMAD provides a clear story-to-architecture/change-review narrative. Spec Kit provides a structured specification-to-plan/tasks/impact narrative and, for the CR evaluation, richer independent execution artifacts. The latter supports a **narrower conclusion about inspectability of the submitted CR evidence**, not a universal claim that Spec Kit documentation is more complete or useful. A formal comparison requires the adopted EC checklist (M15), native link checks (M03), reviewer judgments (M11) and human agreement (M14).

## 6. GQM integrated findings

| Finding | Quantitative strand | Qualitative strand | Contradictory / missing evidence | Integration |
|---|---|---|---|---|
| F3-01 — Functional preservation (RQ1; M01/M06) | 19/19 baseline and 19/19 rechecks per track; 22/22 CR reported | Both extended existing GUI calculator behavior | Different raw evidence depth; offline Blocked→manual Pass history | **Corroborates reported preservation**, not exhaustive correctness |
| F3-02 — Change propagation (RQ2; M07) | No justified impact percentage | Both impact narratives identify unexpected consequences | No agreed affected-element oracle | **Complementary qualitative evidence; numeric comparison insufficient** |
| F3-03 — Defect/rework (RQ2/RQ4; M04/M08/M12) | No comparable defect/rework rates | BMAD test/review corrections; Spec Kit GUI defect and test correction | Incomplete observation windows and heterogeneous defect classes | **Complementary process observations, no winner** |
| F3-04 — Traceability (RQ3; M03/M13) | No verified native phase delta | Both retain structured document-to-implementation candidates | Native-vs-reconstructed origin and semantic verification incomplete | **Insufficient for coverage ranking** |
| F3-05 — Documentation/reproducibility (RQ3/RQ5; M15/M16) | Native suite growth only as context | Spec Kit CR independent GUI/build package more inspectable | M15 checklist not scored; BMAD local evaluator records not fully archived | **Evidence-strength difference, not intrinsic tool quality** |
| F3-06 — Efficiency and judgments (RQ4/RQ5; M09–M11/M14) | Not calculable/not rated | Different workflow patterns and operator contexts | No active sessions, comparable boundaries, paired reviewer scores | **Insufficient** |

The GQM RQ labels follow the study's metric catalogue. The findings integrate observations; they do not create extra independent samples from individual acceptance checks.

## 7. Validity threats and mitigations

| Threat | Mitigation applied | Residual limitation |
|---|---|---|
| **VT-C01 / VT-C07:** native vs inferred traceability | Keep M03 and M13 separate; do not score candidate links as verified | Full semantic audit absent |
| **VT-C02 / VT-C03:** engine tests or parent summary mistaken for GUI/subcase execution | Preserve layer and subcase distinctions; retain original Blocked outcome | BMAD independent raw GUI/subcase record incomplete |
| **VT-C04:** test counts interpreted as quality/effort | Present counts only as M16 context | Different suite definitions and runner conditions |
| **VT-C06:** missing data converted to zero or failure | Use Not calculable, NR and provenance qualifications | Some measurements unrecoverable retrospectively |
| **Internal validity:** operator/agent/environment differences | Compare complete workflow runs, not tool-only causation | No randomized or controlled replication |
| **Conclusion validity:** small case study and incomplete ratings | No statistical superiority, time ranking or overall winner | No paired J1–J7 human assessments |
| **Reliability:** frozen snapshot and raw-record completeness | Cite commits, original operator sheets, independent Spec Kit evaluation and follow-up | BMAD local evaluator evidence needs archival verification |

## 8. Conclusions — Baseline vs Change Analysis

1. **Both workflows handled the stated CR-01 functionality successfully according to their reported parent acceptance results.** Both also reported preservation of the 19 baseline checks, with no observed baseline regressions in the available records.
2. **Neither workflow is demonstrated to have handled CR-01 more efficiently.** Active effort, comparable elapsed boundaries and harmonized intervention/rework rates are missing.
3. **Both encountered change-impact surprises**, which supports the importance of pre-coding impact analysis followed by independent verification; it does not establish comparative M07 recall.
4. **BMAD's strength in the available evidence is continuity through architecture, stories and iterative review.** Its limitation for this cross-phase assessment is weaker independent CR raw-evidence archival and incomplete standardized effort/traceability measurements.
5. **Spec Kit's strength in the available evidence is structured specifications/plans/tasks and a more reproducible independent CR GUI/build evaluation package.** Its CR change also exposed a genuine GUI defect and a previously unanticipated constitution/document scope change; the offline follow-up has a human-reporting limitation.
6. **The most defensible current outcome is a tie on reported functional acceptance and regression preservation, with Spec Kit stronger on inspectability of independent CR evaluation evidence.** There is no evidence-based overall tool winner or causal productivity claim.

**Part 4 should reuse these findings, not overstate them.** In particular, answer “which workflow handled change better?” separately for functional acceptance, impact anticipation, evidence reproducibility, documentation and effort, rather than collapsing them into one unsupported score.

## 9. Remaining verification work

To finalize numerical cross-phase measures under the adopted method, the team should: (a) verify the BMAD evaluator's frozen checkout and archive row/subcase raw records; (b) record the adopted protocol/rubric revision and historical date if available; (c) complete native link-by-link M03 and reconstructed M13 audits for both phases; (d) agree the predeclared impact reference register for M07; (e) harmonize defect/intervention/rework event records and recover actual time sessions; (f) assess each applicable evidence-checklist obligation by collector for M15; and (g) obtain independent evidence-linked J1–J7 human ratings and M14 agreement. These are **evidence gaps**, not additional functionality to implement.

## 10. Sources and reproducibility pointers

**Local comparison inputs (not GitHub-pushed by this report):**

- `Experiment_1_Baseline_Comparison_Report_FINAL.md` and the associated Experiment 1 workbook.
- `Experiment_2_CR01_Comparison_Report_INTEGRATED.md` and `Experiment_2_CR01_Comparison_FINAL.xlsx`.

**Repository methodology and common scope:**

- [Study repository](https://github.com/VSondur123/spec-driven-development-analysis)
- [Research SKILL.md](https://github.com/VSondur123/spec-driven-development-analysis/blob/main/research/empirical-methodology/skills/evaluate-sdd-workflows/SKILL.md)
- [Metric catalogue / GQM](https://github.com/VSondur123/spec-driven-development-analysis/blob/main/research/comparison-frameworks/metric-catalogue.md)
- [Shared protocol](https://github.com/VSondur123/spec-driven-development-analysis/blob/main/shared/experiment-protocol.md) and [rubric](https://github.com/VSondur123/spec-driven-development-analysis/blob/main/shared/evaluation-rubric.md)
- [Experiment log](https://github.com/VSondur123/spec-driven-development-analysis/blob/main/shared/experiment-log.md)
- [Baseline acceptance criteria](https://github.com/VSondur123/spec-driven-development-analysis/blob/main/shared/acceptance-criteria.md) and [CR-01](https://github.com/VSondur123/spec-driven-development-analysis/blob/main/shared/change-request-CR-01.md)
- [Evaluation templates](https://github.com/VSondur123/spec-driven-development-analysis/blob/main/research/empirical-methodology/evaluation-templates.md), [evidence checklist](https://github.com/VSondur123/spec-driven-development-analysis/blob/main/research/empirical-methodology/evidence-checklist.md), [validity threats](https://github.com/VSondur123/spec-driven-development-analysis/blob/main/research/empirical-methodology/validity-threats.md)

**Native operator and independent evaluator evidence:**

- [BMAD baseline acceptance/traceability](https://github.com/VSondur123/spec-driven-development-analysis/blob/main/bmad-calculator/evidence/04-acceptance-results-and-traceability.md)
- [BMAD CR acceptance/traceability](https://github.com/VSondur123/spec-driven-development-analysis/blob/main/bmad-calculator/evidence/06-cr01-acceptance-results-and-traceability.md) and [CR impact](https://github.com/VSondur123/spec-driven-development-analysis/blob/main/bmad-calculator/evidence/05-cr01-impact-analysis.md)
- [Spec Kit baseline results](https://github.com/VSondur123/spec-driven-development-analysis/blob/main/github-spec-kit-calculator/evidence/06-test-results.md)
- [Spec Kit CR results](https://github.com/VSondur123/spec-driven-development-analysis/blob/main/github-spec-kit-calculator/evidence/08-cr01-test-results.md) and [CR impact](https://github.com/VSondur123/spec-driven-development-analysis/blob/main/github-spec-kit-calculator/evidence/07-cr01-impact-analysis.md)
- [Spec Kit independent CR evaluator sheet](https://github.com/VSondur123/spec-driven-development-analysis/blob/main/github-spec-kit-calculator/evidence/08-cr01-evaluator-execution-sheet.md) and [regression sheet](https://github.com/VSondur123/spec-driven-development-analysis/blob/main/github-spec-kit-calculator/evidence/09-cr01-baseline-regression-sheet.md)
- [Spec Kit independent raw evaluation package](https://github.com/VSondur123/spec-driven-development-analysis/tree/main/research/empirical-methodology/evaluations/2026-11-05-spec-kit-cr01)

---

**Handoff:** This file is intended for the repository's `final-analysing/` folder. It is a descriptive research synthesis and should be reviewed alongside the two experiment workbooks before the team drafts Part 4. No source artifacts, shared inputs, implementation files, tags or GitHub branches were changed.
