# Final Report — Comparative Evaluation of BMAD and GitHub Spec Kit

**Study:** Spec-Driven Development Analysis  
**Case:** Java Swing calculator, Baseline implementation and CR-01 scientific operations  
**Prepared:** 2026-11-06  
**Scope:** Part 4 — Overall Findings, grounded in Experiments 1 and 2 and the cross-phase Part 3 analysis  
**Companion evidence synthesis:** [`results.md`](results.md)  
**Methodological authority:** Team-confirmed protocol/rubric; repository evaluation SKILL; GQM catalogue  
**Status:** Evidence-qualified final research synthesis, subject to team review and unresolved formal metric/rubric data

## Abstract

This comparative case study evaluates two specification-driven development workflows—BMAD and GitHub Spec Kit—through implementation of the same baseline Java Swing calculator and a common CR-01 extension adding square, square-root and reciprocal operations. The comparison integrates application quality, workflow quality, change handling, traceability, documentation, rework and reproducibility using the adopted evaluation protocol and GQM-aligned M01–M16 catalogue. Both operators reported passing all 19 baseline parent criteria. Independent baseline evaluation distinguished **BMAD 19 Pass** from **Spec Kit 18 Pass and one procedure-Blocked offline check**; BMAD's offline Pass also lacked explicit network-state proof. For CR-01, both workflows reported **22/22 change criteria Pass** and **19/19 baseline regression criteria Pass**. The Spec Kit CR independent evaluation retained more inspectable frozen-snapshot GUI/build evidence; BMAD retained strong architecture/story/change-review documentation but its independent CR observation archive was less complete in the supplied record set. Both experienced unanticipated change effects and corrections. Because effort, semantic traceability, impact recall, formal rubric ratings and standardized rework rates are not fully measured, the study cannot justify an overall workflow winner or a tool-only causal claim. It can identify **dimension-specific strengths, comparable reported functional outcomes, and concrete evidence gaps**.

## 1. Introduction and research objective

Specification-driven development aims to make requirements, design choices, implementation, verification and change management explicit and inspectable. The study compares two different native approaches to that aim: **BMAD**, with PRD/architecture/epic/story and review-oriented organization, and **GitHub Spec Kit**, with constitution/specification/plan/task and checklist-oriented organization. The comparison asks not only whether the calculator works, but how well the complete workflow records decisions, handles a change request and supports independent assessment.

The central questions are: **(RQ1)** which workflow achieved better application outcomes; **(RQ2)** which handled CR-01 more effectively; **(RQ3)** which produced more complete and useful documentation and traceability; **(RQ4)** what strengths and weaknesses appeared in the workflow and rework; and **(RQ5)** which methodological limitations constrain interpretation. These are answered separately rather than combined into a fabricated overall score.

## 2. Study design, adopted methodology and evidence

### 2.1 Common scope and phases

The baseline comprises **13 requirement IDs** (8 functional, 5 non-functional) and **19 parent acceptance criteria**. CR-01 adds **11 change functional requirement IDs**, **22 change parent acceptance criteria** and a requirement to preserve the same **19 baseline parents**. Each track implements the same externally specified behavior while retaining its native workflow and artifact conventions. The two phases form a longitudinal, within-workflow comparison; comparing BMAD and Spec Kit then forms a descriptive cross-workflow comparison.

### 2.2 Method and source hierarchy

The shared protocol and evaluation rubric are **confirmed by the study team as adopted**, even though some repository headings retain *Proposal*. Their exact original approval date is not provided. The repository's `evaluate-sdd-workflows/SKILL.md` and companion templates, evidence checklist and validity register inform the reporting structure throughout: identify frozen snapshots, inventory native artifacts, distinguish native links from evaluator reconstructions, preserve original attempts and reruns, separate operator reports from independent observation, apply only supported metrics, and integrate quantitative and qualitative evidence. The method is not added as an isolated compliance chapter.

The analysis uses the **five user-supplied files**: two experiment workbooks, two experiment reports, and the Part 3 baseline/change analysis. Their consolidated findings and measurement statuses are preserved in [`results.md`](results.md). No new application run, raw evidence collection or independent human J1–J7 assessment was performed in writing this report. A report or workbook can document an evaluator's statement without establishing an independently reproducible raw observation.

### 2.3 Comparability and decision rules

The experimental unit is the **complete tool–agent–operator workflow**, not the software tool stripped of its human/AI context. Operators, model use, operating systems, evaluators and evidence capture differ. Parent acceptance coverage is measured against the common frozen denominator; a **Blocked** check is not a failed product test, and a later manual follow-up does not erase the original Blocked record. Native test counts, numbers of documents and lines of code are contextual, not directly comparable measures of software quality. Native explicit M03 traceability is not credited from evaluator-reconstructed M13 links. Human rubric judgments are not fabricated from narrative impressions.

## 3. Overall quantitative findings

| Comparison | BMAD | GitHub Spec Kit | What can be concluded |
|---|---|---|---|
| Baseline operator-reported acceptance | 19/19 Pass | 19/19 Pass | Both operators reported common functionality success |
| Baseline independent evaluation | 19 Pass, 0 Blocked | 18 Pass, 1 Blocked | Offline check was procedurally blocked for Spec Kit; BMAD offline-state proof incomplete |
| CR-01 parent acceptance | 22/22 Pass reported | 22/22 Pass reported | Tie in **reported** common CR parent outcomes |
| Baseline rechecks after CR | 19/19 Pass reported | 19/19 Pass after manual offline follow-up | Zero **reported** regressions in assessed set |
| Native test counts (baseline → CR) | 172 → 316 | 81 → 116 | Within-track growth only; no cross-track quality ranking |
| Quantitative impact recall (M07) | Unavailable | Unavailable | Cannot determine superior impact prediction |
| Measured effort and duration (M09/M10) | Unavailable | Unavailable | Cannot claim faster/cheaper workflow |
| Formal J1–J7 / agreement (M11/M14) | Not rated | Not rated | No overall rubric score or statistical winner |

The independent baseline difference must not be described as BMAD's software outperforming Spec Kit by 5.26 percentage points: the single missing Spec Kit check was **Blocked by an unmet test prerequisite**, while BMAD's reported offline Pass lacked a recorded network-disabled state. The distinction is between **recorded coverage and observation provenance**, not demonstrated correctness. Likewise, CR-01's 22 parents cannot be directly compared with baseline's 19 parents as if they were identical tests.

## 4. RQ1 — Which workflow performed better, and in which areas?

### 4.1 Application quality

**Finding: no demonstrated overall application-quality winner.** Both operators reported success on the baseline behaviors, and both CR implementations report passing all common CR parents. The independent baseline checklists are not symmetric: Spec Kit retained one Blocked offline procedure, and BMAD's claimed offline completion lacks explicit network-state evidence. In the change phase, both report preserving all 19 baseline parents. The Spec Kit CR evaluation has more retained raw observations; BMAD's independent CR results are more dependent on local manual records. These observations support a **tie in reported functional outcomes**, but not a controlled tie in all possible software qualities or independent evidence strength.

The CR-01 Spec Kit UI clipping defect shows a real product issue that was discovered and fixed through visual evaluation. BMAD's `1e41` versus `1e40` issue concerns a test input/expectation, not a demonstrated equivalent product failure. Counting these as the same class of defect would bias the comparison. Neither observed defect set is a complete, standardized product-defect census.

### 4.2 Development workflow quality

BMAD's **architecture/story/review** sequence gives an intelligible narrative from requirements through design decisions and implementation increments. Spec Kit's **constitution/specification/plan/tasks** sequence gives a systematic, navigable planning structure. Both have native planning and change artifacts. Both also required correction or discovery after initial planning. Without comparable measured person-minutes, elapsed boundaries, intervention logs and J1–J7 human ratings, neither can be ranked for efficiency, discipline or general process quality. The evidence favors **describing their different mechanisms**, not declaring one more productive.

### 4.3 Evidence and reproducibility

**Finding: Spec Kit's inspected CR-01 independent evaluation package is stronger in raw inspectability.** Its clean frozen-SHA run, build output, GUI observations/screenshots and explicit offline follow-up chronology make more of the recorded CR behavior auditable. BMAD has substantive operator evidence and a locally supplied evaluator checklist, but the exact independent CR checkout and complete criterion-level raw package are not equally established. This is a **specific result about the submitted evidence**, not proof that Spec Kit always creates better documentation or better code.

## 5. RQ2 — Which workflow handled CR-01 better?

### 5.1 Functional extension and regression control

Both workflows successfully **reported** square, square root, reciprocal, unary input handling, domain/zero errors, recovery and unchanged baseline behavior. The 22 CR parent Pass statuses and 19 baseline recheck Pass statuses support successful extension in the reported checked set. Spec Kit's offline check was initially Blocked and subsequently marked Pass by human follow-up; the chronology is part of the result, not an embarrassment to omit. The BMAD evaluator's local checklist and raw evidence depth limit the strength of an equal-independence assertion. There is **no justified functional winner** on the common reported CR parents.

### 5.2 Change-impact anticipation

BMAD's impact and review trail identified implementation work and later exposed additional GUI operation-button test changes and an `assertInitialState` helper. Spec Kit's pre-change analysis correctly anticipated five changed and five unchanged files in its described set, but later encountered a constitution update, `OperationTest.java` and an `errorAreaSize` helper beyond the original scope. These are valuable examples of how changes propagate through UI, tests and governance documents. However, the shared M07 metric needs a **frozen pre-coding prediction and independently adjudicated affected-element reference set**. The reported examples do not justify a numeric precision/recall ranking or a declaration that either workflow anticipated CR-01 more accurately overall.

### 5.3 Verification, corrections and rework

BMAD records test-input correction, test-only review patches and a Javadoc correction. Spec Kit records a visible error-message clipping defect, its fix, and a separate test expectation correction. The Spec Kit example demonstrates the value of real-window GUI verification; BMAD's iterative review demonstrates detection and correction of test/documentation issues. A product defect, a test expectation error and a documentation correction cannot be treated as equal rework units. M12 and M08 remain descriptive because complete, harmonized episodes and intervention windows are not recorded. **No evidence-based claim that one handled the change faster or with less rework is available.**

### 5.4 Answer to RQ2

The defensible answer is **both handled the specified functional change successfully as reported; Spec Kit provided more inspectable independent CR verification; neither has demonstrated superior impact prediction or efficiency**. A single universal “CR-01 winner” would collapse incomparable dimensions into an unsupported judgment.

## 6. RQ3 — Which produced more complete and useful documentation?

### 6.1 Native documentation strengths

BMAD's PRD, architecture spine, epics, stories, impact analysis, review and test records are well suited to explaining **why a design was chosen, how work was decomposed, and how a change passed through architecture and story review**. Spec Kit's constitution, specification, plan, research/model/contract records, checklists and tasks are well suited to explaining **what was specified, what was planned, and which implementation tasks were expected**. These are different native document types; a filename or document count is not a valid measure of quality.

### 6.2 Traceability and evidence completeness

Both workflows have candidate links from shared requirements to native specifications, implementation and tests. The baseline shared-input map itself leaves four NFRs without linked parent ACs and three ACs without requirement-ID links. Native M03 requires semantic verification at frozen commits and origin classification; evaluator-built crosswalks belong to M13. Neither has a fully verified cross-phase M03 percentage in the supplied analyses. Similarly, M15 requires obligation-by-obligation evidence status with the correct collector ownership. A richer evaluator-created screenshot archive must not automatically be credited as more complete *operator-generated* documentation. J1–J7 documentation judgments and independent agreement were not supplied.

### 6.3 Answer to RQ3

**No formally established winner for overall documentation completeness or usefulness.** BMAD has strengths in architecture/story/change-review continuity; Spec Kit in structured spec/plan/task continuity. The **Spec Kit CR independent evidence archive is better inspectable** within the reviewed records, a narrower but meaningful reproducibility finding. Formal comparative completeness would require completed M03/M15 and independent J1–J7 judgments.

## 7. RQ4 — Strengths and weaknesses of each workflow

| Workflow | Supported strengths | Supported weaknesses or constraints | Evidence boundary |
|---|---|---|---|
| **BMAD** | Coherent PRD–architecture–epic–story organization; review-oriented change handling; reported baseline and CR acceptance success; explicit impact narrative | Unanticipated GUI/test-helper work; test-input and documentation corrections; independent CR raw evidence and frozen-checkout provenance less complete | Cannot infer greater effort or poorer code quality from missing evaluator artifacts |
| **GitHub Spec Kit** | Structured constitution–spec–plan–tasks chain; impact forecast with explicit changed/unchanged set; retained CR independent build/GUI evidence; visible defect detection and fix | Unanticipated constitution/test/helper changes; genuine GUI clipping defect before correction; initial offline procedure Blocked and later manual follow-up | Cannot infer more intrinsic defects, higher productivity or universal documentation superiority |

These observations are **dimension-specific findings from one case study**, not stable properties of all BMAD or Spec Kit implementations.

## 8. RQ5 — Limitations and threats to validity

### 8.1 Construct validity

A parent Pass status does not prove all subcases were independently executed. Native automated test counts are not common acceptance coverage. A manually reconstructed requirement chain is not native explicit traceability. A missing screenshot is not a failed calculator function. The study protects these distinctions by reporting the layer, denominator and missingness separately.

### 8.2 Internal validity and confounding

Different operators (Varun and George), AI assistance/model histories, operating systems, reviewer practices and environments prevent isolating the causal effect of BMAD versus Spec Kit. The comparison is a descriptive two-workflow case study, not a randomized benchmark. Changes in documentation and test suites across phases may reflect team decisions as well as workflow characteristics.

### 8.3 Reliability and reproducibility

The baseline frozen references and Spec Kit CR detached-SHA evaluation support some reproducibility. The BMAD CR independent evaluator checkout and full raw GUI/subcase archive are not comparably established. Spec Kit's offline completion history is split between an initially Blocked run and a later human-reported check. These are **limits on evidential certainty**, not automatically defects of the implementation.

### 8.4 Conclusion validity and missing measurements

M03 semantic traceability, M07 change-impact recall, M09 active effort, M10 elapsed duration, M11 evidence-linked rubric scores, M12 standardized rework, M14 independent reviewer agreement and M15 checklist completeness are not fully calculable from the supplied material. Missing values are **not zero** and cannot be filled with impressions. One project and two workflows do not support population-level statistical superiority.

### 8.5 Shared specification limitations

The baseline shared AC mapping leaves four NFRs without direct parent acceptance coverage and three parent ACs without requirement-ID links. This is a common study-design limitation. CR-01 introduces new requirements and a different AC denominator; a 19-to-22 raw comparison is not a meaningful improvement metric.

## 9. Integrated GQM interpretation

| GQM goal | What the evidence supports | What remains unresolved | Implication |
|---|---|---|---|
| G1 — Specification/traceability | Both produced structured native artifacts; shared ACs and candidate links documented | M03/M13 semantic link coverage, J1–J4 ratings | Compare specific document mechanisms, not unverified percentages |
| G2 — Change impact | Both extended CR and recorded unexpected work | M07 affected-element oracle, J5–J6 ratings | No numeric impact-planning winner |
| G3 — Implementation/verification | Reported CR 22/22 each; baseline evaluator statuses distinct; no reported CR regressions | Full subcase provenance and exhaustive defect census | Similar reported functional outcomes, different evidence depth |
| G4 — Human work | Corrections and review episodes visible | M08–M10 and M12 comparable denominators | No speed/effort/rework superiority claim |
| G5 — Evidence/reproducibility | Spec Kit CR package richer in retained independent GUI/build records | M15 and paired human rubric/M14 | Evidence auditability is a bounded advantage, not a global quality score |

The quantitative strand and qualitative strand **complement** one another. Where a numerical comparison is unsupported, a concrete qualitative observation can still be reported, but it must not be promoted into an invented measurement. No composite GQM score is assigned.

## 10. Implications for practice and future evaluation

First, a successful CR-01 change should be judged against **both new behavior and preserved baseline behavior**; the shared 22 CR parents and 19 regression parents serve different purposes. Second, planning documents and impact analyses should be followed by **real-window GUI verification**, because a passing core test may not reveal clipped user-visible error text. Third, retaining the **original Blocked attempt alongside a later manual completion** improves reproducibility and avoids hindsight bias. Fourth, both workflows benefit from explicit native requirement-to-test links, but the study must keep evaluator reconstructions separate. Fifth, independent evaluation should capture a complete frozen-SHA manifest, commands, GUI observations, timestamps and relevant environment state. Finally, standardized effort, rework and paired rubric data should be collected prospectively if the team wishes to compare efficiency or reviewer agreement.

These are **study-grounded methodological implications**, not universal claims about all projects or workflow products.

## 11. Final conclusions

**Which workflow performed better?** On the common reported functional acceptance outcomes, neither has a demonstrated advantage. The independent baseline difference arises from an offline procedure Blocked in Spec Kit and incomplete network-state proof for BMAD; it is not evidence of different product correctness. Spec Kit has stronger inspectability of the retained CR independent evaluation package.

**Which handled CR-01 better?** Both reported all 22 CR parents Pass and all 19 baseline regression parents Pass. Spec Kit documented a real GUI defect and its repair; BMAD documented test/review corrections. Neither has sufficient comparable impact, effort or rework data for a global change-management winner.

**Which produced more complete and useful documentation?** Both produced substantive, differently organized native artifacts. BMAD's architecture/story narrative and Spec Kit's spec/plan/task chain each have demonstrable uses. The supplied evidence does not support a formal overall completeness score; Spec Kit's CR independent evidence is more inspectable in the available archive.

**What were their strengths and weaknesses?** BMAD supports design/story continuity and iterative review but has weaker independent CR raw-evidence archival in the reviewed set. Spec Kit supports structured specification/task continuity and a reproducible CR evaluator package but still encountered unexpected change scope and a GUI defect before correction.

**What limits these conclusions?** Confounded operator/agent/environment conditions, unequal observation layers, a single case study, missing semantic traceability and effort measures, incomplete standardized defect/rework logs, and absent paired human rubric ratings. The study therefore establishes **qualified, dimension-specific findings—not an intrinsic overall winner**.

=> Both BMAD and GitHub Spec Kit successfully implemented the baseline calculator and CR-01 extension according to their reported acceptance results. GitHub Spec Kit demonstrated stronger independently inspectable evaluation evidence, while BMAD provided structured architecture- and story-driven development documentation. Neither workflow demonstrated a conclusive overall advantage in application quality, development efficiency, or change-management effectiveness. Further standardized measurements are required to establish an overall ranking.

## 12. Research deliverables and evidence closure

This final report is intended to be read together with `results.md` and the three source analyses. The study team should review the retained evidence statuses, record the adopted protocol/rubric revision and historical approval date if recoverable, archive the BMAD CR independent evaluator raw records and exact checkout, verify native links for M03 and reconstructed links for M13, adjudicate M07 reference impacts, standardize defect/rework records, assess applicable EC obligations for M15, and obtain independent evidence-linked J1–J7 ratings for M11/M14 **if those quantitative conclusions are required**. If data cannot be recovered, leave the metrics Not calculable rather than backfill estimates.

**Scope of this deliverable:** synthesis of supplied evidence only; it does not claim new application testing, newly obtained reviewer ratings, team sign-off or GitHub push.

## References and reproducibility

**Local primary source files:** `Experiment_1_Baseline_Comparison(1).xlsx`, `Experiment_1_Baseline_Comparison_Report(1).md`, `Experiment_2_CR01_Comparison_FINAL(1).xlsx`, `Experiment_2_CR01_Comparison_Report_FINAL(1).md`, and `Baseline_vs_Change_Analysis_Report(2).md`. The exact uploaded names are retained here for audit provenance; the repository may use cleaner canonical names.

**Study repository references (as cited by the supplied reports):**

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
