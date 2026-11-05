# Experiment 2 — CR-01 Change Comparison Report

**Study:** Spec-Driven Development Analysis — BMAD versus GitHub Spec Kit  
**Phase:** Experiment 2, Change Request CR-01 (Scientific Operations)  
**Prepared:** 2026-11-06  
**Status:** Research comparison using the team-confirmed evaluation methodology; evidence-qualified results and remaining measurement limitations documented  
**Companion dataset:** `Experiment_2_CR01_Comparison_FINAL.xlsx`  
**Repository:** https://github.com/VSondur123/spec-driven-development-analysis

## Abstract

This report compares two specification-driven workflows, BMAD and GitHub Spec Kit, while extending a Java Swing calculator with square, square-root and reciprocal operations. The frozen change specification contains **11 change functional requirements** and **22 parent acceptance criteria**. Both tracks report **22/22 CR-01 criteria passed**, and both report **19/19 baseline criteria passed** on the changed application, implying **zero reported baseline regressions**. These equal reported outcomes do not imply equal evidential strength or overall workflow quality. The Spec Kit evaluation retains frozen-snapshot build output, real-window GUI observations, screenshots and a supplementary human-reported offline-launch check. The BMAD independent evaluation is represented by the evaluator's locally supplied completed sheets; those sheets were not found completed on the inspected repository `main` branch and contain limited criterion-specific raw observations. The study therefore draws descriptive, evidence-qualified conclusions rather than causal claims of tool superiority.

## 1. Research objectives and design

Experiment 2 addresses three dimensions: **Application Quality**, **Development Workflow Quality**, and **Development Results and Documentation**. The common comparison unit is the same released CR-01 specification and the same set of parent acceptance criteria. Workflow-specific architecture, automated test counts and artifact layouts are **not** interchangeable measurement units. The protocol requires separate native execution, explicit source provenance, paired regression assessment and clear identification of missing measurements.

The shared protocol and rubric are the team-confirmed evaluation rules, although their document headers have not yet been updated; the exact original adoption date is not recorded in the material available for this report. The relevant sources include the [shared change request](https://github.com/VSondur123/spec-driven-development-analysis/blob/main/shared/change-request-CR-01.md), [shared acceptance criteria](https://github.com/VSondur123/spec-driven-development-analysis/blob/main/shared/acceptance-criteria.md), [experiment protocol](https://github.com/VSondur123/spec-driven-development-analysis/blob/main/shared/experiment-protocol.md), [evaluation rubric](https://github.com/VSondur123/spec-driven-development-analysis/blob/main/shared/evaluation-rubric.md), and [metric catalogue](https://github.com/VSondur123/spec-driven-development-analysis/blob/main/research/comparison-frameworks/metric-catalogue.md). The workbook contains the row-level comparison, candidate traceability associations and evidence manifest.


### 1.1 Evaluation methodology and authority

The team has confirmed that the existing evaluation protocol and rubric are the **adopted working methodology for both experiments**, notwithstanding the outdated *Proposal* labels in their headers. The date of original adoption and its decision-log reference have not been supplied; this report does not invent either. The study applies the repository-specific [`evaluate-sdd-workflows/SKILL.md`](https://github.com/VSondur123/spec-driven-development-analysis/blob/main/research/empirical-methodology/skills/evaluate-sdd-workflows/SKILL.md) (updated 2026-11-03, companion package draft-5) **throughout the evaluation**, not as a separate object of study. Its companion [protocol](https://github.com/VSondur123/spec-driven-development-analysis/blob/main/research/empirical-methodology/protocol-recommendations.md), [rubric](https://github.com/VSondur123/spec-driven-development-analysis/blob/main/research/empirical-methodology/evaluation-rubric-proposal.md), [templates](https://github.com/VSondur123/spec-driven-development-analysis/blob/main/research/empirical-methodology/evaluation-templates.md), [evidence checklist](https://github.com/VSondur123/spec-driven-development-analysis/blob/main/research/empirical-methodology/evidence-checklist.md), [methodology review](https://github.com/VSondur123/spec-driven-development-analysis/blob/main/research/empirical-methodology/methodology-review.md) and [validity-threat register](https://github.com/VSondur123/spec-driven-development-analysis/blob/main/research/empirical-methodology/validity-threats.md) inform collection, scoring boundaries and interpretation. The shared inputs and recorded team decisions take precedence over supplementary metric/visualisation proposals.

The study is a **GQM-informed comparative case study of complete tool–agent–operator workflows**, not a controlled experiment isolating tool effects. Observations are classified separately as operator-reported, independently observed, or inferred/reconstructed. Acceptance execution status (Pass/Fail/Blocked/Not run), evidence availability, and ordinal rubric judgments (0–3/NR/N/A) are different fields. The denominator for parent acceptance checks is the frozen applicable set; required subcases must be verified separately before claiming a fully executed-parent count. Unknown measurements are never replaced with zero. This report audits existing records; **no new build, GUI test or human rating was performed for this revision**.

### 1.2 Study procedure and evaluation sequence

The adopted evaluation method is operationalized in the same sequence specified by the research SKILL, while preserving the three Experiment 1 comparison dimensions:

1. **Authority and phase readiness:** establish the common frozen baseline and released CR-01 scope, identify both frozen implementation snapshots, and preserve the historical adoption/release record. Both tracks are evaluated as complete tool–agent–operator workflows; native implementation artefacts are not transferred between operators.
2. **Manifest and inventory:** record immutable commits, operators, evaluators, agents/tools, environments, observer independence, prior exposure and evidence locations. Inventory each track's native specifications, decisions, source/configuration, tests and reports without modifying them.
3. **Requirement, clause and traceability assessment:** evaluate every applicable CR-FR clause against the common checks. Separate **native explicit** links from **evaluator-reconstructed** links, and verify the meaning of each link rather than relying on ID mentions.
4. **Execution and regression assessment:** retain the original planned run, attempted subcases, expected/actual behaviour, observer, timestamp, execution layer, command/exit code, raw record, blockers and later reruns. Compare all 19 frozen baseline parents after the change.
5. **Metric and evidence assessment:** apply the rubric's denominators, M01–M16 operational definitions, evidence-checklist collector boundaries, event/defect records, and impact reference controls. Keep missing data unavailable or NR rather than converting it into zero.
6. **Independent review and integration:** apply J1–J7 anchors only with evidence-linked reviewer rationales, preserve disagreements, integrate GQM quantitative and qualitative strands, and document threats to validity and deviations.

This report is the **current evidence-supported execution of that reporting procedure**, not a claim that every underlying data-collection step was completed. Where a required input is absent, the corresponding result is explicitly qualified instead of omitted.

## 2. Evidence, provenance and evaluated snapshots

| Attribute | BMAD | GitHub Spec Kit |
| --- | --- | --- |
| Frozen change snapshot | `bmad-cr01-frozen`, identified in the experiment log as `72f2783`; exact independent evaluator checkout not confirmed | `speckit-cr01-frozen`, full SHA `9e523775e31c42bb9f164ddf84e9ff736b2c2fdc`; fresh clean detached clone recorded |
| Independent evaluation | Thanh's user-reported manual evaluation | Amin's evaluation with Codex assistance and Java Robot real-window automation; later manual offline follow-up |
| CR-01 sheet | Locally supplied completed `08-cr01-evaluator-execution-sheet.md`; repository `main` copy inspected as blank | [Completed CR-01 sheet](https://github.com/VSondur123/spec-driven-development-analysis/blob/main/github-spec-kit-calculator/evidence/08-cr01-evaluator-execution-sheet.md) |
| Regression sheet | Locally supplied `09-baseline-regression-cr01.md`, based on an aggregate report; not found on inspected `main` | [Completed regression sheet](https://github.com/VSondur123/spec-driven-development-analysis/blob/main/github-spec-kit-calculator/evidence/09-cr01-baseline-regression-sheet.md) |
| Native implementation evidence | [BMAD change results](https://github.com/VSondur123/spec-driven-development-analysis/blob/main/bmad-calculator/evidence/06-cr01-acceptance-results-and-traceability.md), [impact analysis](https://github.com/VSondur123/spec-driven-development-analysis/blob/main/bmad-calculator/evidence/05-cr01-impact-analysis.md) | [Spec Kit test results](https://github.com/VSondur123/spec-driven-development-analysis/blob/main/github-spec-kit-calculator/evidence/08-cr01-test-results.md), [impact analysis](https://github.com/VSondur123/spec-driven-development-analysis/blob/main/github-spec-kit-calculator/evidence/07-cr01-impact-analysis.md) |
| Native test result | Owner display run: 316 executed, 0 failures, 0 skipped; headless run: 316 counted, 80 skipped | Evaluator clean verify: 116 executed, 0 failures, 0 errors, 0 skipped |

**Evidence distinction.** A native operator's successful test run is not automatically an independent evaluator reproduction. A parent criterion reported Pass is not the same as a criterion with independently inspectable GUI steps and screenshots. The Spec Kit offline-launch criterion was initially blocked and later updated through Amin's supplementary manual statement; the original blocked history remains relevant. BMAD's 19 regression statuses derive from the user's overall confirmation and should not be described as 19 separately supplied raw test logs.


### 2.1 Evaluation manifest and provenance boundaries

| Manifest field | Recorded state |
| --- | --- |
| Change phase and scope | CR-01: 11 change functional requirements, 22 parent CR acceptance checks, 19 baseline regression parents |
| Adopted procedure | Team-confirmed shared evaluation protocol and rubric; SKILL.md companion draft-5 |
| Approval chronology | Team confirmation known; exact original adoption date/log entry not supplied |
| BMAD frozen change commit | `72f2783` (reported tag `bmad-cr01-frozen`); evaluator's exact checkout/clean state unconfirmed |
| Spec Kit frozen change commit | `9e523775e31c42bb9f164ddf84e9ff736b2c2fdc` (tag `speckit-cr01-frozen`); independent clean clone documented |
| Operators | Varun (BMAD); George (Spec Kit) |
| Evaluator provenance | Thanh's locally supplied BMAD manual sheets; Amin's Codex-assisted Spec Kit evaluation and later manual offline follow-up |
| Environment and independence | Not fully harmonised; BMAD evaluator metadata/exposure and raw per-subcase records need verification |
| This revision's execution | Documentary synthesis only; no tests rerun |
| Rubric review | Independent human J1–J7 scores not supplied; no overall quality score assigned |

The frozen snapshots and common criteria permit **descriptive paired comparison**, but the evidence packages differ. A reported pass is not equivalent to a separately retained raw GUI observation, and a native operator run is not an independent evaluator reproduction.

### 2.2 Evidence inventory, ownership and availability

The evidence index is organized by **track, phase, snapshot, source path, collector, observation layer and supported claim**. The companion workbook's Evidence inventory is the reference index; its presence does not by itself establish that each checklist obligation is satisfied. The shared study owner is responsible for frozen inputs, adoption and release decisions; Varun and George for their respective native operator artefacts; and independent evaluators for observation records and ratings. Evaluator-created reconstructions are not retroactively assigned to an operator or tool.

The evidence-checklist obligations are assessed conceptually as follows:

| Evidence group | BMAD CR-01 | Spec Kit CR-01 | Analytical treatment |
| --- | --- | --- | --- |
| Shared requirements, release, procedure and rubric (EC-01) | Shared common sources | Shared common sources | Team-confirmed authority; exact historical adoption date unknown |
| Operator/tool/environment and prompt history (EC-02–03) | Partial contextual records | Partial contextual records | Do not infer equivalent exposure, effort or interventions |
| Native specifications, implementation and tests (EC-04 and related obligations) | Operator records available | Operator records available | Native content requires frozen-snapshot semantic verification |
| Build/test and GUI evidence | Operator run and locally supplied evaluator sheet; independent raw detail limited | Clean frozen build, GUI observations/screenshots, manual offline follow-up | Distinguish reported from inspectable observations |
| Impact, defect and intervention evidence | Impact narrative and test corrections | Impact narrative, UI defect and test correction | Candidate event records; no exhaustive common census |
| Evaluator review and reproducibility | Incomplete independent raw package | Richer independent raw package with offline limitation | No formal M15 ratio without agreed per-obligation statuses |

**Availability vocabulary:** *Available* means a retained and inspectable record; *Confirmed absent* requires a documented sufficiently complete search; *Unavailable* means the available access cannot establish presence; and *Unassessed* means no defensible check was performed. These labels are separate from **Pass/Fail/Blocked/Not run** test execution and **0–3/NR/N/A** rubric ratings. A file that is absent from the inspected `main` branch is not necessarily absent from the evaluator's local working copy.

## 3. Quantitative outcome summary

| Measure | BMAD | GitHub Spec Kit | Interpretation |
| --- | ---: | ---: | --- |
| CR-01 parent checks (D) | 22 | 22 | Common frozen denominator |
| Reported Pass | 22 | 22 | 100% reported pass coverage |
| Reported Fail / Blocked / Not run (current combined record) | 0 / 0 / 0 | 0 / 0 / 0 | Does not erase historical blocked attempts |
| Baseline checks rerun on CR-01 version | 19 | 19 | Common regression denominator |
| Reported baseline Pass | 19 | 19 | No reported regressions |
| Reported baseline regressions | 0 | 0 | Strength of paired observations differs |
| Native tests in cited CR-01 run | 316 | 116 | Different test suites; not a ranking |
| Native failures in cited run | 0 | 0 | Different scopes and execution environments |

The primary outcome M01 is **reported pass coverage**: `P / D = 22 / 22 = 100%` in each track. The primary regression result M06 is **0 reported baseline parent regressions** for each track. Neither percentage establishes untested functional or non-functional requirement satisfaction. The CR-AC-21 regression aggregation is one of the 22 CR parents; the 19 baseline regression parents are reported **separately** and must not be added to the CR denominator.

### 3.1 Individual CR-01 acceptance outcomes (M01)

| ID | Common procedure | Expected | Layer | BMAD status | Spec Kit status |
| --- | --- | --- | --- | --- | --- |
| CR-AC-01 | Launch the application and inspect the GUI. | Square, square-root and reciprocal controls are visible and identifiable. | GUI | Pass | Pass |
| CR-AC-02 | Select square, enter `-3` in First number, leave Second number empty, calculate. | 9 | GUI | Pass | Pass |
| CR-AC-03 | Select square, enter `1.2`, calculate. | 1.44 | GUI | Pass | Pass |
| CR-AC-04 | Select square, enter `0`, calculate. | 0 | GUI | Pass | Pass |
| CR-AC-05 | Select square root, enter `9`, calculate. | 3 | GUI | Pass | Pass |
| CR-AC-06 | Select square root, enter `2`, calculate. | 1.4142135624 | GUI | Pass | Pass |
| CR-AC-07 | Select square root, enter `0`, calculate. | 0 | GUI | Pass | Pass |
| CR-AC-08 | Select square root, enter `-1`, calculate. | The specified negative-input error appears; no successful result is shown. | GUI | Pass | Pass |
| CR-AC-09 | Select reciprocal, enter `4`, calculate. | 0.25 | GUI | Pass | Pass |
| CR-AC-10 | Select reciprocal, enter `3`, calculate. | 0.3333333333 | GUI | Pass | Pass |
| CR-AC-11 | Select reciprocal, enter `-4`, calculate. | -0.25 | GUI | Pass | Pass |
| CR-AC-12 | Select reciprocal, enter `0`, calculate. | The baseline division-by-zero error appears; no successful result is shown. | GUI | Pass | Pass |
| CR-AC-13 | Repeat CR-AC-12 with `0.0` and `-0.0`. | Each input is treated as zero and rejected safely. | GUI; 2 subcases | Pass | Pass |
| CR-AC-14 | Select each unary operation with an empty First number and calculate. | Clear validation feedback; no crash. | GUI; 3 subcases | Pass | Pass |
| CR-AC-15 | Select each unary operation and enter malformed input such as `abc`. | Baseline invalid-number feedback; no crash. | GUI; 3 subcases | Pass | Pass |
| CR-AC-16 | Select a unary operation, enter a valid First number, leave Second number empty. | The calculation succeeds without a second operand. | GUI | Pass | Pass |
| CR-AC-17 | Select a unary operation, enter a valid First number and a different value in Second number, calculate. | The result depends only on First number. | GUI | Pass | Pass |
| CR-AC-18 | Trigger a negative square-root error, correct the input, calculate again. | The application recovers and shows the correct result. | GUI; stateful | Pass | Pass |
| CR-AC-19 | Trigger a reciprocal division-by-zero error, correct the input, calculate again. | The application recovers and shows the correct result. | GUI; stateful | Pass | Pass |
| CR-AC-20 | Use Clear after a unary calculation. | The calculator returns to the agreed baseline initial state. | GUI | Pass | Pass |
| CR-AC-21 | Execute all `BASE-AC-01` to `BASE-AC-19` against the changed application. Record each individually (use a fresh copy of the baseline sheet). | All baseline criteria retain their required behaviour. | Regression; 19 parents | Pass | Pass |
| CR-AC-22 | Follow the documented build and test instructions for the changed application. | Builds and tests execute. Prerequisites, test outcomes and failures are documented. | Build | Pass | Pass |

The workbook additionally records actual observations, evidence tier and Spec Kit source links for each criterion. BMAD observed-actual fields should be read as the evaluator's reported outcome rather than independently recovered raw UI output.

### 3.2 Baseline regression results (M06)

| Baseline AC | Req link | Procedure | Expected | BMAD CR status | Spec Kit CR status |
| --- | --- | --- | --- | --- | --- |
| BASE-AC-01 | BASE-FR-01 | Disable networking. Launch with the documented command. | GUI opens. Record a screenshot or note showing the network state and the window. | Pass | Pass |
| BASE-AC-02 | BASE-FR-02 | Enter integer 7 in First number. | Accepted and displayed clearly. | Pass | Pass |
| BASE-AC-03 | BASE-FR-02 | Enter decimal 1.5 in First number. | Accepted and displayed clearly. | Pass | Pass |
| BASE-AC-04 | BASE-FR-03, BASE-FR-04 | 7 + 5, click Calculate. | 12 | Pass | Pass |
| BASE-AC-05 | BASE-FR-03, BASE-FR-04 | 7 - 5, click Calculate. | 2 | Pass | Pass |
| BASE-AC-06 | BASE-FR-03, BASE-FR-04 | 7 * 5, click Calculate. | 35 | Pass | Pass |
| BASE-AC-07 | BASE-FR-03, BASE-FR-04, BASE-FR-07 | 7 / 2, click Calculate. | 3.5 | Pass | Pass |
| BASE-AC-08 | Numeric behaviour | -7 + 2, click Calculate. | -5 | Pass | Pass |
| BASE-AC-09 | Numeric behaviour | 1.2 + 2.3, click Calculate. | Exactly 3.5 | Pass | Pass |
| BASE-AC-10 | BASE-FR-05 | Enter values, select an operation, calculate, then Clear. | Inputs, operation, result and error reset to the initial state. | Pass | Pass |
| BASE-AC-11 | BASE-FR-06 | Calculate with (a) empty first, (b) empty second, (c) both empty, (d) a spaces-only field. | Clear error for each (suggested "Enter both numbers"); no result; no crash. | Pass | Pass |
| BASE-AC-12 | BASE-FR-06 | Calculate abc, 1.2.3, 1,5, 1e3, --1, ., .5, 5., - as First number with a valid Second number. | "Invalid number" feedback for each; no crash. | Pass | Pass |
| BASE-AC-13 | BASE-FR-07 | (a) 7 / 0, (b) 7 / 0.0. | Error (suggested "Cannot divide by zero"); no infinity or NaN; no crash. | Pass | Pass |
| BASE-AC-14 | BASE-FR-08 | Trigger an input error, correct the input, calculate again. | Correct result is shown. | Pass | Pass |
| BASE-AC-15 | BASE-NFR-05 | Clean checkout of the frozen commit. Follow the documented build and run steps. | Builds and launches, or prerequisites are documented. Record exact commands and exit status. | Pass | Pass |
| BASE-AC-16 | Regression/usability | Run three or more different calculations in sequence without restarting. | Each result matches the current operands and operation. | Pass | Pass |
| BASE-AC-17 | BASE-FR-06 | Two valid numbers, no operation selected, click Calculate. | Error (suggested "Select an operation"); no result; no crash. | Pass | Pass |
| BASE-AC-18 | BASE-FR-04, BASE-FR-07 | (a) 0 / 5, (b) 1 / 3, (c) 1 / 2048, (d) -1 / 2048. | (a) 0; (b) 0.3333333333; (c) 0.0004882813; (d) -0.0004882813 | Pass | Pass |
| BASE-AC-19 | BASE-FR-04 | Two valid numbers, select an operation, press Enter in an operand field. | Same result as the Calculate button. Record which field was used. | Pass | Pass |

All rows are reported Pass in both tracks. **No observed regression** is the supported final classification, subject to the documented BMAD observation limitations and the Spec Kit offline follow-up provenance. The result should not be generalized to all possible untested regressions.


### 3.3 Requirement coverage, subcases and traceability

The **11 CR functional requirements** are inventoried in the workbook, but that inventory is not a completed semantic traceability score. M03 requires verifying each native requirement-to-specification, implementation/configuration and verification link **in context** and recording origin/authorship. Links inferred by an evaluator belong under **M13 reconstructed traceability**, not native M03. Accordingly, no native-link numerator or percentage is claimed.

For M01 and M06, the tabled outcomes use **parent criteria**. Some parents contain multiple required input variants or sequential GUI states. The adopted rubric distinguishes the frozen parent denominator **D**, parent status counts and the number of parents with all required subcases actually executed **C**. Because the underlying BMAD evaluator evidence and the Spec Kit offline follow-up have unequal detail, this report does **not** equate 22 reported parent Pass statuses with 22 independently verified complete subcase bundles. The Spec Kit offline check's initial Blocked result and later human-reported Pass are both retained.

### 3.4 Execution rules and aggregation safeguards

For each track and frozen phase, **D = P + F + B + N**, where P, F, B and N are counts of Pass, Fail, Blocked and Not run *parent* criteria. Here the common parent denominators are **D = 22 for CR-01** and **D = 19 for baseline regression**. Reported Pass coverage is P/D; execution coverage and **fully executed parent count C** must be established from required subcase records rather than inferred from a parent summary. A decisive failed parent can still contain unexecuted subcases, so C is not automatically P+F. A later successful rerun does not erase the initial blocked/failing observation or justify rewriting the primary-run history.

A valid observation should specify the **input, expected result, actual result, execution layer (engine/GUI/build/document), observer, date/timezone, environment, exit status and retained raw evidence**. GUI interaction requirements require GUI evidence, not just engine unit tests or source inspection. In the available evidence, Spec Kit's original offline launch attempt was blocked and later reported Pass through a human follow-up; these are two provenance-distinct observations. BMAD's local evaluator sheet records outcomes but does not independently establish all of these subcase metadata fields. The workbook therefore does not claim a fully verified C value for either track.

## 4. Aspect A — Application Quality

| Dimension | BMAD | GitHub Spec Kit | Conclusion |
| --- | --- | --- | --- |
| New functions | square, square root, reciprocal — reported Pass | same — observed Pass with real-window records | Both meet 22/22 reported CR parents |
| Boundary/error cases | zero variants, negative root, malformed/empty input reported Pass | corresponding cases observed and screenshot-backed | No reported final failures |
| Interaction and recovery | no second operand, malformed second ignored; correct/clear reported Pass | real-window observations for same categories | Both appear functionally complete on declared checks |
| Regression | 19/19 reported Pass via aggregate restatement | 19/19 current Pass; automated records plus manual offline follow-up | No observed regression in either track; evidence tier differs |
| Reproducibility | JDK 22; Maven 3.9.11; GUI launched; native counts absent | fresh clone; Corretto 22.0.2; Maven 3.9.16; 116/116 | Spec Kit has stronger independent build documentation |

Both changed applications reportedly provide the three new unary operations and handle the specified numerical, error, interaction, recovery and reset cases. The change checks include negative square roots, zero reciprocals including `0.0` and `-0.0`, malformed and missing operands, second-operand independence, and recovery after errors. The Spec Kit records contain direct GUI result fields and screenshots; the BMAD independent sheet relies on the evaluator's summarized manual observations. Thus **the reported product outcome is tied, but the auditability of the independent observations is not equivalent**.

### 4.1 Acceptance quality versus rubric judgments

**Application Quality** is assessed primarily through the common CR-01 and baseline parent criteria, with the appropriate layer-specific execution evidence. The reported 22/22 and 19/19 outcomes answer the limited question of observed/reported functional acceptance. They do not prove all non-functional requirements, every possible input, maintainability or overall product quality.

The adopted **J1–J7 rubric** is a separate ordinal judgment instrument. Each criterion requires its anchor, evidence citation and reviewer rationale; **NR** denotes insufficient evidence or an unperformed review, while **0** denotes an observed deficiency. Independent human ratings must remain individually identifiable, and disagreement must be retained rather than silently averaged. Neither track has complete independent J1–J7 reviewer scores in the current evidence package; therefore this report does not invent a weighted total or declare a rubric winner.

## 5. Aspect B — Development Workflow Quality

| Dimension | BMAD | GitHub Spec Kit | Finding |
| --- | --- | --- | --- |
| Change management | Correct-course batch, Epic 4, story checkpoint approvals | /speckit-specify, plan/tasks 002, approvals AP-04/AP-05 | Both maintained explicit change workflow |
| Architecture propagation | Operation enum + Calculator; no frame or presenter source modifications | Calculator, Operation, GUI and spec/constitution propagation | Both needed adjustments beyond simplistic initial expectation |
| Pre-implementation impact | Retrospective missed window test/helper cases | Unpredicted constitution + error width + OperationTest | Formal recall cannot be computed fairly |
| Review/defect discovery | Multiple review layers, test-only changes, one test input issue | Red/green TDD logs, owner screenshot found clipped error | Different workflows revealed different improvement opportunities |
| Interventions/effort | Approximate owner narrative; no complete timing | Event log says time not measured; some build durations | No defensible efficiency winner |

### 5.1 Change-impact prediction (M07)

| Aspect | BMAD | Spec Kit | Comparability / boundary |
| --- | --- | --- | --- |
| Pre-coding analysis | Sprint Change Proposal and impact analysis saved | Named pre-coding impact analysis plus T048 addendum | Both have candidate original predicted sets; no jointly frozen impact reference set |
| Known missed impact: tests | Two GUI button-list tests; initial-state helper for seven buttons | New OperationTest test file not predicted | Elements differ; do not divide by combined raw file totals |
| Known missed impact: design/config | Claim that adding operations only changes enum and buttons invalidated | Constitution changed though predicted unchanged; error width method unanticipated | Different internal architectures; classify before scoring |
| Prediction details | Frame/presenter source modifications not required as expected | 5 predicted source files changed; 5 predicted-unchanged source files stayed unchanged | Spec Kit's self-audit describes limited predictive matches, not formal M07 recall |
| Formal M07 recall / precision | Not calculable | Not calculable | Needs single reviewer-approved reference set and counting units |

The BMAD analysis records missed GUI test/button-list effects and a test helper that initially checked only four of seven operations. The Spec Kit retrospective analysis identifies an unexpected constitution update, a newly added `OperationTest` file, and a UI error-area sizing correction. Spec Kit reports five predicted source files changed and five predicted-unchanged source files remained unchanged, but these figures are **not** a cross-track recall denominator. A valid M07 recall/precision comparison requires a jointly reviewed, frozen reference set of required affected elements, agreed granularity and reconciliation of original pre-coding predictions.

### 5.2 Defects, discovery and rework (M04, M05, M12)

| Track | ID / scope | Finding | Discovery | Outcome | Research classification |
| --- | --- | --- | --- | --- | --- |
| BMAD | Story 4.3 test-input issue | A wrong test value 1e41 rather than 1e40 failed a test; production code correct | Owner/review implementation test run | Test corrected; final 316 display tests zero failures | 1 test defect; no confirmed product defect in operator evidence |
| BMAD | Testing helper gap | assertInitialState originally only checked 4 operations, not all 7 | Story 4.4 review | Test helper extended | Missed-impact/test-coverage issue; do not automatically count as a product defect |
| Spec Kit | D-CR-01 / DL-57 | Negative-root message clipped in fixed-width error UI | Owner real-window screenshot; not caught by headless suite | Fixed via errorAreaSize; final tests pass | 1 documented product/UI defect |
| Spec Kit | DL-56 test expectation issue | A test expected a negative root to pass before domain check was implemented | Implementation test run | Test fixed | Test-defect / test-maintenance episode; separate from product defect |
| Both | Regression defects | No observed baseline regression in final reported records | CR-AC-21 + 19 baseline checks | 0 reported | Do not conflate zero observed regressions with absence of latent defects |

Spec Kit documents product/UI defect **D-CR-01 (DL-57)**: a negative-square-root error message was clipped in the live window. It was discovered in owner screenshots and fixed. Its presence during development is not a final acceptance failure. BMAD documents a wrong test input (`1e41` instead of `1e40`) and an incomplete test helper; these are test-quality findings, **not evidence of an equivalent product defect**. Spec Kit also documents a test expectation correction. These classifications must be kept separate before comparing total product-defect or late-discovery rates.

| Track | Event | Provenance | Count treatment | Formal metric status |
| --- | --- | --- | --- | --- |
| BMAD | CR release and branch; LOG decisions; approvals for 4 stories | Owner 2026-11-03 narrative | Episodes described, no complete event-level timestamped register | M08 incomplete |
| BMAD | Three review subagents for Stories 4.1, 4.2 and 4.3; one test patch round each | Owner review triage logs | 3 candidate test review-patch episodes; exact counting scope unagreed | M12 candidate only |
| BMAD | Wrong test input 1e41/1e40 and assertInitialState helper gap | Owner logs | 2 additional candidate corrections; check overlap with reviews | Do not sum prematurely |
| Spec Kit | Approvals AP-04 and AP-05; screen checks; owner chose UI defect fix | Dated owner effort log | At least named interventions; complete M08 register unverified | M08 incomplete |
| Spec Kit | US2 test expectation correction and D-CR-01 error width fix | Dated bug and review records | Two candidate rework episodes; independent classification needed | M12 candidate only |
| Both | Active minutes | Not measured consistently | Unknown, not 0 | M09 Not calculable |
| Both | Comparable elapsed phase time | Boundary definitions / complete timestamps not paired | Unknown | M10 Not calculable |

BMAD's multi-layer story reviews produced test-patch rounds. Spec Kit records approvals, red/green tests, an owner-discovered UI defect and its fix. Because episode boundaries and overlap have not been consistently classified, candidate rework episodes are **not** presented as formal M12 totals. Human interventions (M08) are documented in different formats; comparable active person-minutes (M09) and elapsed change-phase durations (M10) cannot be calculated from the currently verified records. Missing time is **unknown**, not zero.

### 5.3 Measurement boundaries for change handling

M07 change-impact recall cannot be calculated without a jointly agreed affected-element reference register that is not merely the final diff. The available pre-coding analyses and retrospective narratives should remain distinct. M04, M08 and M12 contain identifiable defects, interventions and rework candidates, but not a demonstrably exhaustive, harmonised event census. M09 active effort requires measured person-time sessions; M10 elapsed duration requires agreed start/end boundaries. Neither may be inferred from Git timestamps or document length.

### 5.0 Change propagation and original-impact preservation

The change-phase procedure requires the **original pre-coding impact analysis** and the **baseline and change snapshots** to remain separately identifiable. The final implementation diff is useful corroboration but is **not a complete impact oracle**: it can omit required but unimplemented changes and can include incidental edits. M07 omissions can only be scored after the evaluators agree a reference register of affected elements, their granularity, match rules, uncertainties and reviewer exposure. Retrospective explanations of an impact are not reclassified as original predictions.

The two tracks' qualitative impact narratives are therefore compared for *observed propagation and surprises* (including unexpected tests, helper methods, documentation or configuration) without calculating unsupported precision/recall or omission percentages. Similarly, interventions (M08), defect discoveries (M04/M05) and rework episodes (M12) require a consistent event boundary and deduplication rule. A test-input correction, a product GUI fix and a review-only documentation change are distinct event types; they are not interchangeable measures of workflow performance.

## 6. Aspect C — Development Results and Documentation

| Dimension | BMAD | GitHub Spec Kit | Inference |
| --- | --- | --- | --- |
| Native evidence | Impact analysis, trace table, per-story tests, screenshots | Specs 002, impact addendum, approvals, logs, screenshots | Both have substantive native records |
| Independent acceptance | Locally supplied evaluator Sheet 08, not committed on main | GitHub main populated Sheet 08 with raw GUI links | Spec Kit evidence more directly reproducible |
| Regression record | Locally supplied Sheet 09, aggregate observations not item-by-item | GitHub main Sheet 09 and per-case screenshot/TSV | Regression auditability differs |
| Snapshot evidence | Tag stated, actual evaluator checkout SHA unconfirmed | Full frozen SHA, clean detached local clone | Not equally controlled evaluation conditions |
| Rubric/metrics | M03, M07, M11, M14, M15 incomplete | M03, M07, M11, M14, M15 incomplete | Do not infer quality scores from filenames or AC coverage |

The two workflows produced substantive implementation and verification evidence. BMAD retains story-oriented change analysis and review records; Spec Kit retains a dedicated scientific-operations specification, plans/tasks, impact addendum and numerous test/GUI artifacts. The Spec Kit independent evaluation has a more directly reproducible frozen-SHA record, while the BMAD evaluation requires confirmation of the actual tested checkout and publication or archival of its completed local sheets. This is an **evidence/reproducibility distinction**, not proof that one underlying implementation is inherently better.

### 6.1 Native tests as context (M16)

| Record | BMAD | GitHub Spec Kit | Interpretation |
| --- | --- | --- | --- |
| Baseline final suite (context) | 172 display tests in earlier BMAD baseline record | 81 native tests at Spec Kit baseline | Not normalized |
| CR-01 native total | 316 test cases in owner display run | 116 test cases in evaluator clean verify | Additional count not evidence of product superiority |
| CR-01 native failures | 0 reported | 0 reported | On different test scopes |
| CR-01 headless skips | 80 of 316 BMAD headless test cases | 0 of 116 native suite | BMAD display 0 skips; different rendering/test setups |
| CR-01 clean checkout | Owner clean-copy test, not clean Git clone | Evaluator detached fresh clone at 9e523775 | Different reproducibility rigor |
| BMAD independent run counts | Not supplied by evaluator | Not applicable | Do not attribute operator counts to evaluator |

The difference between 316 and 116 native tests reflects non-identical suites, design and display/headless coverage. It should not be used as a measure of productivity, correctness superiority, or specification quality. In BMAD's headless run, 80 GUI-dependent tests were skipped; the separate display-enabled run reported none skipped.

### 6.2 Evidence checklist and review quality

The adopted methodology's evidence checklist assigns obligations **EC-01 onward** to the study owner, operators and evaluator. The workbook's **Evidence inventory** is a source index, **not** a completed per-obligation/per-track M15 checklist assessment. A numeric completeness ratio needs agreed applicable obligations, collection boundaries, and evidence availability decisions. Likewise, J1–J7 judgments require evidence-linked independent reviewer ratings; M14 agreement cannot be derived from one reviewer or one automated assessment. Differences in documentation reproducibility are reported as evidence observations, not as unreviewed quality-rubric scores.

### 6.0 Native documentation, traceability and checklist interpretation

The documentation comparison treats **equivalent native structures as acceptable**: BMAD stories and Spec Kit specifications/tasks need not have identical filenames or document counts. Each asserted requirement link must be traced to an actual native artefact at the evaluated revision, checked in context and classified as tool-generated, operator-added or unknown where provenance permits. **M03** measures native explicit links; **M13** records evaluator reconstructions separately. A reconstructed matrix can help a reviewer understand the system but cannot be credited as evidence that the native workflow produced the links.

The evidence checklist also separates **operator submission obligations** from **study-owner/evaluator collection obligations**. Missing evaluator screenshots must not automatically reduce an operator's M15 submission completeness. The applicable checklist revision, each obligation's owner, status and justification are required before an M15 percentage is defensible. Evidence richness and document quantity are not direct measures of implementation quality.

## 7. Metric coverage and calculation readiness (M01–M16)

| ID | Metric | BMAD disposition | Spec Kit disposition | Grounded finding | Further work? |
| --- | --- | --- | --- | --- | --- |
| M01 | Acceptance outcome | Calculated (reported) | Calculated (evaluator+follow-up) | 22/22 each; stronger evidence on Spec Kit | No |
| M02 | Requirement status view | Partial/conditional | Partial/conditional | 11 CR FR known; need adopted mapping | Yes |
| M03 | Native traceability | Not formally scored | Not formally scored | Needs native explicit implementation+verification link audit per 11 CR-FRs | Yes |
| M04 | Distinct defects | Operator evidence screened | Operator evidence screened | BMAD test-input issue; Spec Kit product UI defect | Partial |
| M05 | Late discovery share | Not calculable | Not calculable | Classification + denominator not reviewed | Yes |
| M06 | Baseline regressions | 0 reported | 0 observed/reported | 19 paired parent checks each; offline follow-up provenance caveat | No (classification review) |
| M07 | Change-impact recall | Not calculable | Not calculable | No jointly agreed required-element reference set | Yes |
| M08 | Interventions | Partial narrative | Partial event log | No harmonized complete episode census | Yes |
| M09 | Active effort | Not measured | Not measured | Do not interpret as zero | Yes |
| M10 | Elapsed duration | Not comparable | Not comparable | Phase boundaries/timing incomplete or inconsistent | Yes |
| M11 | J1–J7 rubric | NR | NR | No paired independent ratings; ordinal values not averaged | Yes |
| M12 | Rework | Candidates identified | Candidates identified | Episodes not classified or deduplicated formally | Yes |
| M13 | Reconstructed traceability | Not scored | Not scored | Separate from native M03 | Yes |
| M14 | Reviewer agreement | Not calculable | Not calculable | No two independent numeric rating sets | Yes |
| M15 | Evidence completeness | Not scored | Not scored | No agreed applicable checklist and reviewed statuses | Yes |
| M16 | Native tests/context | 316 owner display | 116 evaluator clean verify | Different suites/conditions, never a quality score | No |

**Interpretation:** Every metric M01–M16 is represented in the companion workbook. This does **not** mean every metric is fully calculated. M01 and M06 have current **reported/combined outcomes** (not independently validated full rubric scores), M16 has contextual native test counts, and several others have descriptive or candidate evidence. M03 native traceability needs semantic link verification; M07 requires an agreed impact reference set; M11 needs independently justified J1–J7 ratings; M14 needs two reviewer rating sets; M15 needs a predefined applicable evidence checklist. No fabricated zeros, durations, agreement percentages or ordinal scores are used.

### 7.1 Metric operationalization and non-calculability rules

The metric catalogue is used as a **GQM crosswalk**, not as permission to assign values unsupported by the shared rubric. M01 and M06 report common parent-check outcomes with explicit provenance and denominators. M03 requires link-by-link semantic verification. M04/M05/M08/M12 require consistently classified, timestamped events with a defined observation window; otherwise only illustrative cases can be reported. M07 requires the agreed affected-element reference register. M09 uses **measured active person-minutes**, not wall-clock estimates. M10 requires a common, documented phase start/end definition. M11 requires evidence-linked J1–J7 human judgments; M14 requires independent paired reviewer ratings. M15 needs the applicable EC checklist, collector boundaries and verified statuses. M16 provides **context**, not a score; test totals, files or lines are not proxies for quality or effort.

Optional measures and checkpoints are applied only where covered by the team's confirmed methodological scope; an empty cell or missing log is **Not calculable/Unavailable**, not zero. The companion workbook's Metric readiness and evidence sheets retain these decisions so that a later reviewer can update a result without silently changing the denominator or observation period.

## 8. Validity threats and limitations

| ID | Issue | Implication |
| --- | --- | --- |
| L01 | BMAD evaluator snapshot unconfirmed | BMAD statuses are reported on unknown actual checkout; frozen tag required |
| L02 | BMAD filled files absent on main | GitHub BMAD 08 is blank and BMAD 09 path returned 404; supplied local copies |
| L03 | BMAD raw evaluator evidence limited | No per-case screenshots, timestamp, Maven output, or test suite count from evaluator |
| L04 | BMAD aggregate regression outcomes | Sheet 09 rows marked Pass based on generic overall statement |
| L05 | Spec Kit offline case follow-up | Initial offline case blocked; Amin later reported pass without timestamp/screenshots |
| L06 | Operator/native suite scope differs | 316 vs 116 suites differ in code, tests, UI/headless execution |
| L07 | Reviewers and automation differ | BMAD manual report vs Spec Kit Codex/Robot-assisted logs |
| L08 | No shared M07 reference set | Cannot rank change impact completeness using anecdotal counts |
| L09 | Effort not measurable | No comparable full active person-minutes or complete timing |
| L10 | Rubric adoption and ratings | Team confirmation supplied; exact adoption date/log entry not provided; no independent J scores |
| L11 | Shared input check coverage | CR 11 functional requirements mapped at candidate level; baseline NFR coverage remains a limitation |
| L12 | Cross-track protocol | Software, OS, operator, agents and frozen snapshot documentation differ |

The most consequential threats are (a) unequal independent-evaluation evidence depth; (b) uncertain BMAD evaluator checkout and locally held completed sheets; (c) different evaluator/automation methods; (d) different native test suites and display conditions; (e) retrospectively assembled impact and effort records; and (f) missing adoption-date metadata and paired reviewer ratings. The comparison is a two-workflow descriptive case study, not a controlled randomized experiment or a basis for causal attribution.

### 8.1 Threat-to-mitigation mapping

| Threat family | Relevant register examples | Application to Experiment 2 | Residual limitation |
| --- | --- | --- | --- |
| Construct validity | VT-C01, VT-C07 (native vs reconstructed links) | M03/M13 are kept separate; candidate links are not counted as native coverage | Semantic verification remains incomplete |
| Construct validity | VT-C02, VT-C03 (GUI and parent/subcase evidence) | Layer-specific observations and original blocked history are distinguished | BMAD independent subcase raw records are limited |
| Construct validity | VT-C04, VT-C06 (test counts and missingness) | Native tests are contextual; unknown values are not scored zero | Different test suites and observation conditions |
| Internal validity | Operator, agent, environment and exposure differences | Comparison unit is the complete workflow, not tool-only treatment | Cannot isolate causal tool effects |
| Conclusion validity | Small number of workflow runs; incomplete ratings and time | No statistical superiority, time ranking or global quality winner | No independent replication or paired J ratings |
| Reliability/reproducibility | Snapshot and raw-record provenance | Exact Spec Kit SHA and raw GUI package retained; BMAD frozen reference recorded | BMAD evaluator checkout and local evidence archive incomplete |

The mitigation column describes **analytical controls used in this report**, not proof that all underlying collection problems were resolved. Any unresolved discrepancy between original and follow-up runs remains visible in the evidence record.

## 9. Findings and conclusions

### 9.1 Integrated GQM findings

| Finding / RQ | Quantitative strand | Qualitative strand and evidence | Missing / conflicting evidence | Integration and validity |
| --- | --- | --- | --- | --- |
| F01 / RQ1, M01 | 22/22 CR parent Pass **reported** for each | Spec Kit has retained GUI and build records; BMAD has operator and local evaluator reports | BMAD raw per-subcase independent records limited; Spec Kit offline history includes Blocked then human-reported Pass | Reported results corroborate; evidential strength differs. VT-C02, VT-C03, VT-C06 |
| F02 / RQ1, M06 | 19/19 baseline parent Pass **reported** for each | Both describe preserved baseline behaviours | BMAD per-case raw independent evidence limited; offline manual follow-up not automated | Descriptive corroboration only. VT-C02, VT-C06 |
| F03 / RQ2–RQ3, M04/M12 | Known defect examples, not exhaustive rates | BMAD test-input correction; Spec Kit GUI error-text clipping fixed after visual review | Incomplete shared classification and episode census | Complementary process observations; no winner. VT-C04, VT-C06 |
| F04 / RQ2, M07 | Not calculable | Both impact narratives identify unexpected work | No jointly agreed affected-element reference register | Insufficient for impact-recall ranking. VT-C06 |
| F05 / RQ4, M08–M10/M14 | Time and agreement not calculable | Partial event histories | No comparable active sessions, phase boundaries or paired human ratings | Insufficient for productivity/consistency claims. VT-C06 |
| F06 / RQ3–RQ5, M03/M11/M13/M15/M16 | 316 vs 116 native tests, contextual only | Spec Kit's independent GUI evidence is more readily reproducible in inspected records | Missing semantic native-link audit, checklist decisions and human ratings | Evidence availability complements outcomes; not proof of intrinsic tool superiority. VT-C01, VT-C04, VT-C07 |

Here **corroboration** means agreement between available observations, not independent replication of the entire workflow. No single raw record is counted as two independent reviewers.

### 9.2 Cross-workflow conclusions

1. **Common acceptance result:** BMAD and GitHub Spec Kit each report 22 of 22 CR-01 parent checks passing. This is a tie on reported common acceptance coverage, not an all-purpose quality tie.
2. **Preserved baseline behaviour:** Both report 19 of 19 baseline regression parents passing, with zero reported regressions. Spec Kit provides richer individual raw evidence, subject to the explicitly documented offline manual follow-up; BMAD requires item-level evaluator observations for equivalent auditability.
3. **Different defects and review paths:** BMAD's documented corrections concern tests and review coverage; Spec Kit had a real UI message-clipping defect corrected after live-window observation. A product defect and a test defect should not be counted as equivalent.
4. **Change-impact forecasting remains unscored:** Both tracks identify missed or unexpected impacts. Without a common frozen impact reference set, numerical M07 ranking would be methodologically weak.
5. **Efficiency and overall workflow superiority remain undetermined:** Neither comparable active effort nor complete paired ordinal ratings are available. The research should not infer that one workflow is faster, cheaper or generally superior.
6. **Evidence completeness is itself a result:** Spec Kit's independent frozen-SHA and raw GUI package is stronger for reproduction in the currently inspected evidence set; BMAD's manual evaluator results still contribute useful reported acceptance evidence but have explicit provenance limitations.

## 10. Evidence gaps and recommended finalization

Before a final research submission, archive BMAD's completed `08-cr01-evaluator-execution-sheet.md` and `09-baseline-regression-cr01.md` alongside the precise evaluated snapshot, add criterion-level observations where possible, agree the impact reference set, review distinct defect/rework episodes, audit native traceability links, and record independently supported rubric ratings. Leave M09, M10 and M14 as **Not calculable** if the underlying comparable time or second-reviewer data cannot be recovered; this is preferable to inventing values.




To complete the remaining data-collection steps under the adopted methodology: (i) confirm exact snapshot and evaluator environment metadata; (ii) retain raw criterion/subcase actuals and the historical offline status; (iii) verify native and reconstructed traceability separately; (iv) agree the affected-element reference for M07; (v) apply the EC checklist per track and collector; (vi) reconcile intervention/rework classifications; and (vii) obtain evidence-linked independent J1–J7 ratings. **The methodology itself does not need reapproval**; incomplete data and human scoring are separate matters. The workbook retains an internal **SKILL audit** sheet for auditability, but methodological compliance is integrated into this report's research sections rather than treated as a fourth comparison dimension.

## References and reproducibility pointers

- [Research evaluation SKILL.md — draft-5](https://github.com/VSondur123/spec-driven-development-analysis/blob/main/research/empirical-methodology/skills/evaluate-sdd-workflows/SKILL.md)
- [Evaluation templates and field crosswalk](https://github.com/VSondur123/spec-driven-development-analysis/blob/main/research/empirical-methodology/evaluation-templates.md)
- [Evidence checklist and EC obligation map](https://github.com/VSondur123/spec-driven-development-analysis/blob/main/research/empirical-methodology/evidence-checklist.md)
- [Methodology review and GQM integration](https://github.com/VSondur123/spec-driven-development-analysis/blob/main/research/empirical-methodology/methodology-review.md)
- [Validity-threat register](https://github.com/VSondur123/spec-driven-development-analysis/blob/main/research/empirical-methodology/validity-threats.md)


- [Experiment protocol](https://github.com/VSondur123/spec-driven-development-analysis/blob/main/shared/experiment-protocol.md)
- [Evaluation rubric](https://github.com/VSondur123/spec-driven-development-analysis/blob/main/shared/evaluation-rubric.md)
- [Metric catalogue](https://github.com/VSondur123/spec-driven-development-analysis/blob/main/research/comparison-frameworks/metric-catalogue.md)
- [Shared CR-01](https://github.com/VSondur123/spec-driven-development-analysis/blob/main/shared/change-request-CR-01.md)
- [Experiment log](https://github.com/VSondur123/spec-driven-development-analysis/blob/main/shared/experiment-log.md)
- [BMAD CR-01 impact analysis](https://github.com/VSondur123/spec-driven-development-analysis/blob/main/bmad-calculator/evidence/05-cr01-impact-analysis.md)
- [BMAD CR-01 acceptance results](https://github.com/VSondur123/spec-driven-development-analysis/blob/main/bmad-calculator/evidence/06-cr01-acceptance-results-and-traceability.md)
- [Spec Kit CR-01 impact analysis](https://github.com/VSondur123/spec-driven-development-analysis/blob/main/github-spec-kit-calculator/evidence/07-cr01-impact-analysis.md)
- [Spec Kit CR-01 test results](https://github.com/VSondur123/spec-driven-development-analysis/blob/main/github-spec-kit-calculator/evidence/08-cr01-test-results.md)
- [Spec Kit independent CR-01 sheet](https://github.com/VSondur123/spec-driven-development-analysis/blob/main/github-spec-kit-calculator/evidence/08-cr01-evaluator-execution-sheet.md)
- [Spec Kit independent regression sheet](https://github.com/VSondur123/spec-driven-development-analysis/blob/main/github-spec-kit-calculator/evidence/09-cr01-baseline-regression-sheet.md)
- [Spec Kit effort/intervention log](https://github.com/VSondur123/spec-driven-development-analysis/blob/main/github-spec-kit-calculator/evidence/05-effort-intervention-log.md)

**Source note:** The BMAD completed independent evaluator Sheets 08/09 were supplied in this conversation and are not represented as completed files on the inspected GitHub `main` branch. The report reflects the workbook and available repository evidence; it does not claim a new independent execution of either application.
