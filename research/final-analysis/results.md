# Results — BMAD versus GitHub Spec Kit

**Study:** Spec-Driven Development Analysis — Java Swing calculator  
**Coverage:** Experiment 1 (Baseline), Experiment 2 (CR-01), and Part 3 (Baseline vs Change)  
**Prepared:** 2026-11-06  
**Method:** Team-confirmed shared evaluation protocol and rubric, applied using the repository research SKILL and GQM metric catalogue  
**Status:** Evidence-qualified consolidated results; not a new test run or human rubric assessment  
**Companion:** `final-report.md`

## Executive results summary

Both complete tool–agent–operator workflows reported success against the common Baseline and CR-01 functionality. The independent Baseline evidence is **not** an unqualified 19/19 tie: BMAD's evaluator reported 19 Pass (with no explicit offline network-state proof), while Spec Kit's evaluator recorded **18 Pass and 1 Blocked**, because the offline prerequisite could not be established. That Blocked case is not a product failure. In Experiment 2, both workflows report **22/22 CR-01 parent criteria Pass** and **19/19 baseline regression parents Pass**. Spec Kit's CR independent evidence is more directly inspectable in the supplied record set; this does not prove the intrinsic superiority of the Spec Kit workflow.

## 1. Evidence basis, design, and comparison rules

This file consolidates the **five user-provided artifacts**, without overwriting the underlying phase reports or workbooks:

1. `Experiment_1_Baseline_Comparison(1).xlsx` — row-level baseline metrics, independent M01 audit, subcases, provenance and evidence ledgers.
2. `Experiment_1_Baseline_Comparison_Report(1).md` — baseline evidence and retrospective plus independent evaluator amendments.
3. `Experiment_2_CR01_Comparison_FINAL(1).xlsx` — CR parent outcomes, regression records, impact audit, event/defect and evidence inventories.
4. `Experiment_2_CR01_Comparison_Report_FINAL(1).md` — CR evidence and integrated methodological analysis.
5. `Baseline_vs_Change_Analysis_Report(2).md` — within-workflow and cross-workflow phase comparison.

The shared baseline scope has **8 functional and 5 non-functional requirement IDs** and **19 parent acceptance criteria**. CR-01 adds **11 functional requirement IDs** and **22 change parent acceptance criteria**; **19 baseline parents** are rechecked after the change. These are distinct populations. The 19 regression parents are **not added** to the CR M01 denominator of 22; CR-AC-21 itself summarizes baseline preservation.

The team confirms the shared protocol/rubric as adopted despite outdated *Proposal* labels in some source headers. The original adoption date/decision-log reference was not provided. The SKILL's evidence and scoring requirements are integrated into the collection and interpretation below, not treated as an independent scored experiment. The unit of comparison is the **whole tool–agent–operator workflow**; operators, agents, operating systems and evaluation layers differ. An operator report, a human evaluator statement, an independently captured GUI observation, and a native automated test result are not interchangeable.

## 2. Experiment 1 — Baseline results

### 2.1 Baseline M01: original reports versus independent evaluation

| Baseline parent measure | BMAD | GitHub Spec Kit | Interpretation |
|---|---:|---:|---|
| Frozen denominator | 19 | 19 | Same shared parents BASE-AC-01–19 |
| Operator-reported Pass | 19 | 19 | Historical operator layer only |
| Independent evaluator Pass | **19** | **18** | Different evidence modalities |
| Independent evaluator Fail | 0 | 0 | No product failure observed in valid runs |
| Independent evaluator Blocked | 0 | **1** | Spec Kit BASE-AC-01 offline prerequisite not completed |
| Independent evaluator Not run | 0 | 0 | Based on submitted parent status sheets |
| Independent reported pass coverage | **100%** | **94.74%** | Difference reflects one Blocked procedure, not demonstrated software inferiority |
| Offline evidence qualification | Network-disabled state not explicitly demonstrated | No offline launch executed in original independent run | Neither proves a verified offline superiority claim |

The BMAD independent sheet was completed manually by Thanh on a reported clean checkout; its recorded offline Pass lacks explicit proof that networking was disabled, and criterion-level raw screenshots/logs were not attached. Spec Kit's Amin/Codex-assisted independent evaluation retained real-window automation, screenshots and observation logs, but did not complete the offline launch after the network prerequisite was blocked. **Do not replace the Spec Kit 18/19 independent baseline result with the 19/19 operator result.**

### 2.2 Baseline application quality

The common evidence supports correct reported examples for addition, subtraction, multiplication and division; decimal precision/rounding; negative inputs; division by zero; invalid and missing operands; Clear, recovery, sequential calculations and Enter-key execution. Both implementations use Java Swing and decimal-oriented arithmetic. These examples do not establish exhaustive correctness, universal accessibility or every non-functional requirement. Four baseline NFRs have no directly linked parent acceptance criterion in the shared mapping; three ACs lack requirement-ID links. This is a **shared-input traceability limitation**, not a defect uniquely attributable to one workflow.

### 2.3 Baseline workflow and documentation

**BMAD** uses a PRD, architecture spine, epics, stories, reviews and tests to organize implementation and verification. **Spec Kit** uses a constitution, specification, plan, research/data-model/contract artifacts, checklist and tasks. Both provide candidate native traceability chains, but a complete **semantically verified M03 numerator** has not been established. Spec Kit's independent baseline evaluation is richer in retained GUI raw evidence; BMAD's independent outcome is more reliant on manually reported observations. This is an evidence-provenance comparison, not a formal M11 documentation score.

## 3. Experiment 2 — CR-01 results

### 3.1 CR acceptance and regression

| CR-01 measure | BMAD | GitHub Spec Kit | Meaning |
|---|---:|---:|---|
| CR functional requirement IDs | 11 | 11 | Same common change specification |
| CR parent denominator | 22 | 22 | CR-AC-01–22 |
| Final combined reported Pass | **22** | **22** | **100% reported parent pass coverage** |
| Baseline regression parent denominator | 19 | 19 | BASE-AC-01–19 on changed implementation |
| Final reported regression Pass | **19** | **19** | No reported regression in checked set |
| Reported regression failures | 0 | 0 | Does not prove no untested regression |
| Native CR test suite (cited run) | 316 | 116 | Different test populations; context only |

CR-01 adds **square, square root and reciprocal**, including single-operand GUI behavior, zero and negative-domain handling, error recovery and preservation of existing operations. Both tracks report meeting the shared parent criteria. The BMAD CR independent sheets were supplied locally, but their exact frozen checkout and raw per-subcase observations were not established to the same standard as the Spec Kit retained evaluation package. Spec Kit's offline check was initially **Blocked**, later updated through a human-reported follow-up; the original status must remain visible. The final 22/22 and 19/19 are **combined reported statuses**, not 22 fully evidenced subcase bundles independently reproduced under identical conditions.

### 3.2 CR-01 change impact, defects and corrections

| Dimension | BMAD | GitHub Spec Kit |
|---|---|---|
| Change planning | Architecture/story-driven impact and implementation review | Specification/plan/tasks and impact forecast |
| Unexpected impact | GUI operation-button tests and `assertInitialState` helper | Constitution update, `OperationTest.java`, `errorAreaSize` helper |
| Confirmed example of product defect | No comparable CR product defect established in supplied case descriptions | Negative square-root error text clipped in GUI, discovered by visual review, then fixed |
| Test or documentation correction | `1e41` vs `1e40` test-input issue, test-only review patches, Javadoc correction | Separate incorrect test expectation corrected |
| Impact M07 | Not numerically calculable | Not numerically calculable |
| Rework M12 | Candidate episodes only | Candidate episodes only |

A product defect, a test-data defect, a documentation edit and a reviewer intervention are different units. Neither track has a complete harmonized event census, so comparing the number of examples as if they were equivalent defect/rework rates would be invalid. The available Spec Kit impact forecast identified five expected-to-change source files that did change and five expected-to-remain files that remained unchanged, but later unpredicted work prevents an unqualified perfect-impact conclusion. A defensible M07 calculation needs a separately fixed pre-coding prediction and adjudicated affected-element reference set.

## 4. Part 3 — Baseline versus Change results

### 4.1 Within-workflow outcome transitions

| Workflow | Independent baseline parent statuses | CR parent reported statuses | Baseline recheck after CR | Supported longitudinal conclusion |
|---|---|---|---|---|
| BMAD | 19 Pass / 0 Blocked; offline-state proof limited | 22/22 Pass | 19/19 Pass reported | Functional extension and preservation **reported**; independent CR raw provenance incomplete |
| Spec Kit | 18 Pass / 1 Blocked (offline procedure) | 22/22 Pass after follow-up | 19/19 Pass after follow-up | Functional extension and preservation **reported**; later offline completion is not a matched repeat of original blocked baseline run |

The 22 CR checks are **new tests of new behavior**, not an increase from 19 to 22 in a common coverage denominator. The appropriate regression comparison holds BASE-AC-01–19 constant across phases. In the independent baseline, Spec Kit's offline case was Blocked; therefore its later reported Pass must not be described as a measured correction of a baseline product defect. No observed product regression was reported for either track, but identical execution conditions and full subcase-level evidence are not established.

### 4.2 Native test-suite evolution (M16 context only)

| Workflow | Baseline cited suite | CR cited suite | Change | Interpretation |
|---|---:|---:|---:|---|
| BMAD | 172 | 316 | +144 (+83.7%) | Supplementary 174-test baseline run exists; CR headless run skipped 80; display run reported zero failures |
| Spec Kit | 81 | 116 | +35 (+43.2%) | Different native suite and environment; clean CR verify recorded 116 tests without failures |

These increases show **native test inventory growth**, not directly comparable verification quality, development effort, coverage, or productivity.

### 4.3 Longitudinal findings by dimension

- **Regression:** Both tracks report preservation of all 19 baseline parent behaviors after CR-01. Observation strength differs, and offline execution history must remain visible.
- **Impact:** Both tracks anticipated many changes but encountered unexpected scope. Neither has an adjudicated M07 reference oracle for numeric recall/precision.
- **Traceability:** Both maintain structured document-to-code/test candidates across phases; native explicit M03 and evaluator-reconstructed M13 require separate origin/semantic verification. Baseline 13 IDs and CR 11 IDs cannot be compared as raw numerators.
- **Rework:** BMAD's test/review/document changes and Spec Kit's GUI/test corrections show iteration; standardized episode and time denominators are unavailable.
- **Documentation:** BMAD's story/architecture continuity and Spec Kit's specification/plan/task continuity are both useful. The independent CR Spec Kit evidence is more inspectable **within the currently available archive**; this is not a scored documentation-quality victory.

## 5. M01–M16 consolidated metric disposition

| Metric | Construct | Evidence across phases | Disposition |
|---|---|---|---|
| M01 | Parent acceptance outcomes | Baseline BMAD evaluator 19/19 reported; Spec Kit evaluator 18/19, one Blocked; CR 22/22 reported both | Partially calculable: parent pass ratios; C requires subcase evidence |
| M02 | Requirement-level status | 13 baseline shared requirement IDs; 11 CR functional requirement IDs | Partial; no equivalent verified requirement-level numeric status |
| M03 | Native explicit traceability | Native artifact candidates in both workflows | Unscored pending semantic verification of origin and links |
| M04 | Distinct product defects | Spec Kit CR GUI clipping defect; BMAD CR test-input error not product defect | Descriptive only; no exhaustive standardized defect census |
| M05 | Late defect share | Discovery stage of selected incidents known | Not calculable without complete stage-coded census |
| M06 | Baseline regressions after CR | Both report 19/19 regression parents Pass; Spec Kit offline check initially Blocked then manual Pass | Zero reported regressions, with unequal observation provenance |
| M07 | Change-impact omission/recall | BMAD unanticipated GUI tests/helper; Spec Kit constitution/test/helper surprises | Not calculable without frozen affected-element reference set |
| M08 | Human interventions | Some operator/reviewer corrections documented | Partial, no comparable intervention census |
| M09 | Active human effort | No comparable measured person-minute sessions | Not calculable |
| M10 | Elapsed phase duration | No harmonized start/end boundaries | Not calculable |
| M11 | J1–J7 rubric | No complete evidence-linked independent human ratings | NR; do not assign composite score |
| M12 | Rework episodes | BMAD test/review/Javadoc corrections; Spec Kit GUI/test corrections | Illustrative only; deduplicated event count unavailable |
| M13 | Evaluator-reconstructed traceability | Candidate crosswalks documented | Keep separate from native M03; unscored |
| M14 | Reviewer agreement | No two independent paired rating sets | Not calculable |
| M15 | Evidence completeness | Evidence inventories exist; ownership and applicable EC decisions incomplete | Not calculable as a percentage |
| M16 | Native test context | BMAD 172 baseline →316 CR; Spec Kit 81→116 | Context only; no cross-track quality inference |

**Aggregation rules:** `P/D` uses frozen parent denominators; `C/D` requires all required subcases decisively observed. **Blocked is not Fail**, **Not run is not zero**, native test counts are not shared AC counts, and a missing ordinal rating is **NR**, not 0. No M11 composite or M14 reviewer-agreement statistic is calculated without actual independent ratings. No active effort (M09) is inferred from commit times or prose logs.

## 6. Integrated GQM evidence results

| Study question | Quantitative evidence | Qualitative evidence | Supported result |
|---|---|---|---|
| G1: specification and traceability | M01 reported; M03/M13 unscored | Both maintain native document chains; shared NFR–AC gaps | Functional examples supported; traceability ranking unresolved |
| G2: change-impact handling | M07 unavailable | Both impact analyses identify unexpected elements | Neither demonstrated perfect impact prediction or a numeric winner |
| G3: implementation and verification | Baseline independent 19/19 vs 18/19+Blocked; CR reported 22/22; M06 0 reported | GUI/product and test issues differentiated | Similar reported functional outcomes; different observation provenance |
| G4: human work and effort | M08/M09/M10/M12 incomplete | Iterative corrections and reviews recorded | No credible efficiency ranking |
| G5: evidence and reproducibility | M15/M11/M14 unscored; M16 contextual | Spec Kit CR retained richer raw independent evidence | Evidence-depth advantage for inspected Spec Kit CR package only |

## 7. Evidence availability, validity, and unresolved data

The strongest limitations are (1) operator/model/OS/evaluator confounding; (2) BMAD CR independent evaluator records not archived at equivalent frozen-SHA/raw-observation depth; (3) baseline offline-state and CR follow-up asymmetry; (4) inconsistent subcase execution detail; (5) heterogeneous native suites and defect categories; (6) no common M07 impact oracle or measured effort sessions; (7) incomplete native link audit, EC obligation decisions and paired J1–J7 ratings; and (8) a single small case study without randomized replication. Evidence availability is not equivalent to a failed requirement. Missing values are not assigned zeros or favorable defaults.

The team-confirmed protocol/rubric are **adopted**; their outdated proposal headers do not make the method unapproved. Nonetheless, the exact adoption chronology and complete evaluator manifest are not recoverable from the supplied reports alone.

## 8. Result-level conclusions

1. Both workflows report CR-01 acceptance success and baseline preservation on the specified parent checks.
2. The independent baseline evaluation is **BMAD 19 Pass** versus **Spec Kit 18 Pass, 1 Blocked**, with offline evidence limitations on both sides; this is not proof of different product correctness.
3. Spec Kit's inspected CR independent GUI/build evidence is more directly reproducible; BMAD's native planning and reported acceptance remain substantial but its independent CR raw record is less complete.
4. Neither workflow can be ranked on measured effort, numeric impact recall, formal native traceability coverage, rework rate, documentation rubric, or overall quality from these files.

## 9. Sources and audit trail

**Primary local sources:** the five uploaded experiment reports/workbooks and Part 3 report listed in Section 1. **Repository context:**

- **method:** https://github.com/VSondur123/spec-driven-development-analysis/blob/main/research/empirical-methodology/skills/evaluate-sdd-workflows/SKILL.md
- **protocol:** https://github.com/VSondur123/spec-driven-development-analysis/blob/main/shared/experiment-protocol.md
- **rubric:** https://github.com/VSondur123/spec-driven-development-analysis/blob/main/shared/evaluation-rubric.md
- **metric:** https://github.com/VSondur123/spec-driven-development-analysis/blob/main/research/comparison-frameworks/metric-catalogue.md
- **baseline:** https://github.com/VSondur123/spec-driven-development-analysis/blob/main/shared/acceptance-criteria.md
- **cr:** https://github.com/VSondur123/spec-driven-development-analysis/blob/main/shared/change-request-CR-01.md
- **bmadbase:** https://github.com/VSondur123/spec-driven-development-analysis/blob/main/bmad-calculator/evidence/07-evaluator-execution-sheet.md
- **specbase:** https://github.com/VSondur123/spec-driven-development-analysis/blob/main/github-spec-kit-calculator/evidence/07-evaluator-execution-sheet.md
- **bmadcr:** https://github.com/VSondur123/spec-driven-development-analysis/blob/main/bmad-calculator/evidence/06-cr01-acceptance-results-and-traceability.md
- **speccr:** https://github.com/VSondur123/spec-driven-development-analysis/blob/main/github-spec-kit-calculator/evidence/08-cr01-evaluator-execution-sheet.md
- **specgui:** https://github.com/VSondur123/spec-driven-development-analysis/blob/main/research/empirical-methodology/evaluations/2026-11-05-spec-kit-cr01/
- **bmadimpact:** https://github.com/VSondur123/spec-driven-development-analysis/blob/main/bmad-calculator/evidence/05-cr01-impact-analysis.md
- **specimpact:** https://github.com/VSondur123/spec-driven-development-analysis/blob/main/github-spec-kit-calculator/evidence/07-cr01-impact-analysis.md
- **log:** https://github.com/VSondur123/spec-driven-development-analysis/blob/main/shared/experiment-log.md
- **check:** https://github.com/VSondur123/spec-driven-development-analysis/blob/main/research/empirical-methodology/evidence-checklist.md
- **valid:** https://github.com/VSondur123/spec-driven-development-analysis/blob/main/research/empirical-methodology/validity-threats.md

**Interpretation note:** Repository links identify the native source paths cited by the uploaded documents. This consolidated file did not independently re-run applications or verify that every linked repository file is currently unchanged. Results are frozen to the supplied evidence state as of 2026-11-06.
