# Experiment 1 – Baseline Comparison: BMAD vs GitHub Spec Kit

**Research role:** Comparison Frameworks and Metrics  
**Prepared:** 2026-11-04  
**Updated with independent Baseline evidence:** 2026-11-05  
**Case study:** Java desktop GUI calculator  
**Study repository:** [VSondur123/spec-driven-development-analysis](https://github.com/VSondur123/spec-driven-development-analysis)  
**Companion dataset:** `Experiment_1_Baseline_Comparison.xlsx`  
**Phase:** Baseline only; CR-01 excluded

> **Evidence status (updated 2026-11-05).** This report preserves the earlier retrospective operator-evidence analysis and now incorporates **two additional Baseline evaluator execution records**: Thanh's BMAD manual reports (**19 Pass / 0 Fail / 0 Blocked / 0 Not run**) and Amin's Codex-assisted real-window Spec Kit evaluation (**18 Pass / 0 Fail / 1 Blocked / 0 Not run**). Spec Kit's single Blocked criterion is offline launch (BASE-AC-01), not an observed software defect. These records differ in evidence provenance. BMAD's offline network state was not documented, despite its reported Pass. Independent J1–J7 reviewer ratings and several workflow metrics remain unavailable; no missing observation or decision date is invented. Dated 2026-11-04 audit sections remain as historical preparatory analysis and are superseded where these new evaluator findings conflict.

## Abstract

This exploratory case study compares two specification-driven development workflows, **BMAD** (operated by Varun) and **GitHub Spec Kit** (operated by George), applied to the same frozen Baseline requirements for a Java Swing calculator. The shared inputs specify eight functional requirements, five non-functional requirements (13 IDs in total), and 19 parent acceptance criteria. Evidence was extracted from the operators' planning artefacts, architecture/design documents, implementation sources, tests, build and review logs, decisions, prompts, screenshots, and manual observations. The analysis is organised under **(A) application quality**, **(B) development workflow quality**, and **(C) development results and documentation**.

Both operators initially reported **19/19 parent criteria as Pass**, but the subsequent independent evaluator records differ: **BMAD 19/19 Pass as reported by Thanh**, and **Spec Kit 18/19 Pass with one Blocked** in Amin's Codex-assisted real-window execution. The Blocked Spec Kit check is BASE-AC-01: networking was not reliably disabled and an offline launch was not performed; it is not a Fail. BMAD also lacks a network-state record for its reported offline Pass, so its 100% coverage has a specified evidence limitation. Spec Kit's frozen evaluation contains 35 timestamped real-GUI observations and a native suite of 81 passing tests; BMAD's current independent sheet contains written evaluator observations without attached raw GUI logs. Both tracks have substantive planning and traceability artefacts, but M03 native-link scores, M11 independent rubric ratings, comparable active effort and some evidence-completeness metrics remain unresolved. This descriptive result **does not establish that BMAD produces a higher-quality calculator**.

## 1. Study question, scope and evidence rules

### 1.1 Purpose

The study asks how two complete **tool–agent–operator workflows** turn shared requirements into specifications, architecture, tasks, code, tests and reproducible evidence. Comparing the calculator outputs is one component of this wider evaluation. The causal impact of BMAD versus Spec Kit alone cannot be isolated because operators, operating systems and model histories also differ. This follows the scope in [`project.md`](https://github.com/VSondur123/spec-driven-development-analysis/blob/main/project.md).

### 1.2 Fixed shared inputs

The Baseline scope was frozen on **23 October 2026**. It contains 13 requirement IDs—`BASE-FR-01` through `BASE-FR-08` and `BASE-NFR-01` through `BASE-NFR-05`—and 19 parent ACs—`BASE-AC-01` through `BASE-AC-19`. The baseline includes a two-operand GUI, four arithmetic operations, exact decimal arithmetic, defined rounding and display rules, validation, error handling, recovery, Clear, Enter-key calculation and reproducible launch/build behaviour. The [baseline requirements](https://github.com/VSondur123/spec-driven-development-analysis/blob/main/shared/baseline-requirements.md) and [acceptance criteria](https://github.com/VSondur123/spec-driven-development-analysis/blob/main/shared/acceptance-criteria.md) remain authoritative. **CR-01 is not analysed here.**

The shared inventory has one common limitation: **four NFRs (`BASE-NFR-01`…`04`) are not associated with a parent AC, and three ACs (`BASE-AC-08`, `09`, `16`) have no requirement ID.** This is a limitation of the shared input mapping, not a failure belonging to one track. Later LOG-12…LOG-15 clarifications are recorded in the [experiment log](https://github.com/VSondur123/spec-driven-development-analysis/blob/main/shared/experiment-log.md); they do not silently enlarge the frozen 19-parent denominator.

### 1.3 Method and distinctions

The study remains an exploratory comparative case study of complete tool–agent–operator workflows. The 2026-11-04 desk review extracted the operators' historical outcomes and artefacts; the **2026-11-05 amendment incorporates independent evaluation records** at two identified immutable Baseline snapshots. This report does not assert that its author launched either program when updating the report itself.

| Information layer | BMAD | Spec Kit | Analytical rule |
|---|---|---|---|
| Original operator report | 19/19 Pass | 19/19 Pass | Historical developer-reported, not pooled with independent outcomes |
| Independent Baseline evaluator | Thanh: 19 Pass, 0 Fail, 0 Blocked, 0 Not run | Amin/Codex: 18 Pass, 0 Fail, 1 Blocked, 0 Not run | Separate frozen-snapshot test records; D=19 per track |
| Observation method | Manual GUI outcomes entered by Thanh; raw screenshots/logs not supplied | Java Robot real-display keyboard/mouse GUI actions, live Swing state readback, screenshots, raw TSV; Amin directed run | Different evidence levels and human/AI participation must be explicit |
| Native test suite | BMAD Maven test command reportedly exited 0; raw independent output absent | 81 tests passed, 0 failure/error/skip, from successful clean verify retry | Native test counts are context, not acceptance parent counts or common quality units |
| Ordinal rubric J1–J7 | NR / N/A where justified | NR / N/A where justified | No paired independent 0–3 human scoring; do not create numerical ratings |
| Native traceability (M03) | Desk-reviewed candidates; formal provenance sign-off pending | Desk-reviewed candidates; formal provenance sign-off pending | No unsupported M03 fraction from the new acceptance run |

Sources: [BMAD independent sheet](https://github.com/VSondur123/spec-driven-development-analysis/blob/main/bmad-calculator/evidence/07-evaluator-execution-sheet.md), [Spec Kit independent sheet](https://github.com/VSondur123/spec-driven-development-analysis/blob/main/github-spec-kit-calculator/evidence/07-evaluator-execution-sheet.md), [Spec Kit reproducibility README](https://github.com/VSondur123/spec-driven-development-analysis/blob/main/research/empirical-methodology/evaluations/2026-11-05-spec-kit-baseline/README.md), and [experiment log](https://github.com/VSondur123/spec-driven-development-analysis/blob/main/shared/experiment-log.md).

Parent pass coverage is **P/D**, D=19 per track; **Blocked** stays in the denominator. Execution completeness C/D requires decisive observations of every required subcase of each parent. BASE-AC-11 (four), AC-12 (nine), AC-13 (two), and AC-18 (four) retain their subcase evidence without increasing D. Reporting 19 BMAD Pass preserves Thanh's submitted statuses, **not** a claim that the missing offline network-state record is independently proven.

### 1.4 Conditions and snapshot integrity

| Condition | Varun – BMAD | George – Spec Kit |
|---|---|---|
| Workflow version | BMAD 6.12.4 | specify-cli 1.2.3 |
| Agent/model | Claude Code, Sonnet 5.5 | Claude Code (VS Code); Opus 5.5 during initial setup, Sonnet 5.5 thereafter |
| OS | macOS 15.7.1, Apple Silicon | Windows 11 Home 10.0.26200, amd64 |
| Runtime | Amazon Corretto 22.0.2 | Amazon Corretto 22.0.2.9.1 |
| Build/test | Maven 3.9.16; JUnit Jupiter 6.1.3; Swing | Maven 3.9.16; JUnit Jupiter 6.1.3; Swing |
| Snapshot record | `baseline-frozen` tag declared; acceptance file cites `ebac834`, final manual AC-01 cites `943c73f` | AC-15 on interim `419f92f`; `6bdf927` another interim commit; no final freeze/tag declared in status |
| Evidence limitation | Tag target verified as 740c78de; alignment of historical result trees remains to be checked | Most AC reports refer to an uncommitted working tree; later edits remain outside an immutable final freeze |

**Evaluator runs (2026-11-05, separate from the operator environments above):** Thanh used Windows 11, Eclipse Temurin Java 22 and Maven 3.9.11, testing BMAD `740c78de062bb1edd8076e4a49dde768b0fe1e49` (`baseline-frozen`) by reported manual GUI checks. Amin's Codex-assisted Spec Kit run used macOS 27.0.1 (aarch64), Amazon Corretto 22.0.2 and Maven 3.9.16 against `88f56d0979e2fae1fb5d59e8681975e3a1fc2a27`. The latter SHA was designated in LOG-26; the original operator's own final freeze declaration remained pending in the checked log. This newer evaluation evidence supersedes the former *lack-of-frozen-evaluator-run* limitation but does not retroactively change the historical operator record.

Sources: [BMAD environment](https://github.com/VSondur123/spec-driven-development-analysis/blob/main/bmad-calculator/evidence/01-environment.md), [BMAD status](https://github.com/VSondur123/spec-driven-development-analysis/blob/main/bmad-calculator/evidence/00-STATUS.md), [BMAD acceptance record](https://github.com/VSondur123/spec-driven-development-analysis/blob/main/bmad-calculator/evidence/04-acceptance-results-and-traceability.md), [Spec Kit environment](https://github.com/VSondur123/spec-driven-development-analysis/blob/main/github-spec-kit-calculator/evidence/01-environment.md), [Spec Kit status](https://github.com/VSondur123/spec-driven-development-analysis/blob/main/github-spec-kit-calculator/evidence/00-STATUS.md).

The different operating systems, model histories, operators and run modes are threats to an isolated tool comparison. The comparison unit is explicitly **the whole workflow as implemented in this study**, not a controlled software-vendor benchmark.

---

## 1.5 GQM measurement framework (updated 2026-11-04)

This Experiment 1 comparison follows the **Goal–Question–Metric (GQM)** organisation in Thanh's `metric-catalogue.md` **draft-6 (2026-11-02)**, supported by `visualisation-proposal.md` draft-6, `literature-review.md` draft-5 and `references.md` draft-5. The overall design remains a **mixed-methods comparative case study**; GQM is the measurement organisation, not a new experiment or a claim of causal isolation. The catalogue is a proposal subordinate to an adopted shared protocol/rubric.

| Goal | Baseline question focus | Metrics | Baseline status |
|---|---|---|---|
| **G1 – Specification and traceability** | Q1.1 native requirement representation; Q1.2 stage links; Q1.3 shared-check gaps; Q1.4 reviewer quality | M03a/b/c, M13, C1, M01, optional M02, M11 J1–J4 | Native mapping tables found for both tracks; formal link verification and reviewer ratings pending |
| **G2 – Change-impact handling** | Q2.1–Q2.4 impact, omissions, propagation and evidence | M07, M07b, M04/M05, M11 J5–J6, M01/M03 | **Not applicable to Baseline**; CR-01 remains outside Experiment 1 |
| **G3 – Implementation and verification outcomes** | Q3.1 parent AC outcomes; Q3.2 regressions; Q3.3 distinct defects; Q3.4 reviewer judgement | M01, M06, M04, M05, M12, M11 | Operator reports 19/19 each; evaluator BMAD 19 Pass reported, Spec Kit 18 Pass + 1 Blocked; rubric ratings pending |
| **G4 – Human work and effort** | Q4.1–Q4.4 interventions, active effort, duration, rework | M08–M10, M12 | Retrospective event candidates; measured effort and comparable phase boundaries unavailable |
| **G5 – Evidence and reproducibility** | Q5.1–Q5.4 evidence availability, snapshots, review, limitations | M15, M16, M11, M14, C1 | Evidence inventory available; applicable checklist and independent reviews not fully adopted/completed |

The workbook sheet **GQM goal question map** records the question-level crosswalk. The three descriptive report aspects below remain useful *presentation lenses*, not competing GQM goals. No composite score is calculated.

**CR-01 authority caveat:** `metric-catalogue.md` draft-6 records CR-01 merged into `main` on 2026-11-02, but approval/freeze wording is inconsistent. A merge alone does not make its proposed 11 change requirements or 22 change ACs adopted. Experiment 1 continues to use 13 baseline requirements and 19 baseline parent ACs.

## 2. Aspect A – Application Quality

**Evaluation question:** When the two calculators are assessed against the same Baseline requirements, which observable behaviours are supported by the existing evidence and where are the evidence limits?

### 2.1 M01 – Acceptance outcomes (updated 2026-11-05)

The **same 19 frozen Baseline parent ACs** were used for both tracks. The evaluator-status matrix in the updated workbook is in **Independent M01 audit**; all individual required subcases are in **Independent subcases**. Operator results are retained, without overwriting them, in **M01 AC evidence**.

| Baseline measure | BMAD | Spec Kit |
|---|---:|---:|
| Denominator D | 19 | 19 |
| Historical operator-reported Pass | 19 | 19 |
| **Independent evaluator Pass (P)** | **19** | **18** |
| Independent Fail (F) | 0 | 0 |
| Independent Blocked (B) | 0 | **1** (`BASE-AC-01`) |
| Independent Not run (N) | 0 | 0 |
| **Reported independent pass coverage P/D** | **19/19 = 100%** | **18/19 = 94.74%** |
| Decisive parent share (P+F)/D | 19/19 = 100% | 18/19 = 94.74% |
| Complete parent execution C/D | 19/19 = 100% *as recorded* | 18/19 = 94.74% |
| Distinct observed product failures | None reported | None observed in valid run |
| Important limitation | Reported AC-01 Pass without explicit offline network-state evidence; raw GUI screenshot/log not attached | Offline run omitted after unsuccessful network setup and evaluator direction; Codex-assisted, not all scenarios manually executed by Amin |

**Interpretation.** The 5.26 percentage-point difference in *recorded pass coverage* is entirely attributable to Spec Kit's one **environment/procedure Blocked** check, not to a demonstrated failing calculator behaviour. On the 18 checks that were actually completed for Spec Kit, all passed. BMAD reported Pass for its offline launch, but the independent sheet does not record proof that the network was disabled. It is inappropriate to conclude that BMAD's software is more correct, that Spec Kit cannot work offline, or that one workflow wins a controlled benchmark.

**Status provenance:** Thanh manually supplied a complete Pass checklist and noted a clean BMAD checkout with Maven test exit 0, but no raw GUI observations or network state were archived in the current evaluator sheet. Amin's Codex-assisted evaluation produced 35 timestamped real-window observations using Java Robot, GUI state readback, screenshots and saved logs, with BASE-AC-01 excluded from any Pass. A preceding invalid GUI activation attempt is retained separately and **not counted** as a product failure. The primary Maven build initially failed due to sandbox network restrictions; a documented network-enabled retry succeeded, with all 81 native tests passing.

**Sources:** [BMAD evaluator sheet](https://github.com/VSondur123/spec-driven-development-analysis/blob/main/bmad-calculator/evidence/07-evaluator-execution-sheet.md); [Spec Kit evaluator sheet](https://github.com/VSondur123/spec-driven-development-analysis/blob/main/github-spec-kit-calculator/evidence/07-evaluator-execution-sheet.md); [Spec Kit run README](https://github.com/VSondur123/spec-driven-development-analysis/blob/main/research/empirical-methodology/evaluations/2026-11-05-spec-kit-baseline/README.md); [GUI TSV](https://github.com/VSondur123/spec-driven-development-analysis/blob/main/research/empirical-methodology/evaluations/2026-11-05-spec-kit-baseline/gui-observations.tsv); [network record](https://github.com/VSondur123/spec-driven-development-analysis/blob/main/research/empirical-methodology/evaluations/2026-11-05-spec-kit-baseline/offline-network-state.txt); [successful native build](https://github.com/VSondur123/spec-driven-development-analysis/blob/main/research/empirical-methodology/evaluations/2026-11-05-spec-kit-baseline/build-network-retry.txt).

### 2.2 Functional correctness and numeric representation

The operator reports cover addition (`7+5=12`), subtraction (`7−5=2`), multiplication (`7×5=35`), division (`7/2=3.5`), negative numbers (`−7+2=−5`), decimal arithmetic (`1.2+2.3=3.5`), zero division numerator (`0/5=0`), one-third rounding (`1/3=0.3333333333`) and keyboard-triggered calculation. Both implementations use `BigDecimal`, avoiding the ordinary binary floating-point display artefacts prohibited by the shared Baseline. BMAD routes the calculation through a total core API and `ResultFormatter`; Spec Kit exposes `Calculator.calculate` with explicit parsed values and success/failure outcomes.

The recorded results support the narrow finding that **both operators observed the required arithmetic examples behaving as expected**. They do not establish correctness for all possible decimal magnitudes, all event sequences or all Java/OS environments.

### 2.3 Input validation and error handling

The shared ACs require handling of missing operands (`AC-11`), malformed strings (`AC-12`), division by zero (`AC-13`), recovery (`AC-14`) and no selected operation (`AC-17`). Both records report Pass and list automated checks. BMAD's acceptance record links `CalculatorTest`, `CalculatorPresenterTest` and `CalculatorFrameInteractionTest`. Spec Kit's record links core `CalculatorTest`, GUI component `CalculatorPanelTest` and selected screenshots/manual observations.

Spec Kit includes a test named `neverThrowsForAnyCombination`, described as covering **20 × 20 × 5** input/operation combinations; this is a context-specific test case matrix, **not** 2,000 additional individual Maven tests to add to the final test count. The two programs have different internal designs: BMAD has a core/presenter/view layering; Spec Kit has a `calculator.core` and `calculator.ui` split. These distinctions can be examined for NFR-04, but no failure should be inferred just from a difference in classes or packages.

### 2.4 Reset, recovery, sequential use and GUI input

`AC-10`, `AC-14`, `AC-16` and `AC-19` address user-visible state transitions and interaction. Both tracks have automation supporting the cases and report Pass. Spec Kit also records owner-reported real-window checks of recovery, several sequential calculations, and Enter in both operand fields. Its screenshot after Clear shows the initial state but not the preceding state; automated tests supplement that limitation. BMAD's GUI interaction tests cover the relevant behaviours on the display run.

These results support a comparison of **observed shared ACs**, not a full usability experiment with measured task time, user satisfaction or accessibility. Visual polish was deliberately not a Baseline comparison objective.

### 2.5 Offline launch and reproducibility — evaluator update

**BASE-AC-01 (offline launch).** BMAD evaluator Thanh marked **Pass**, reporting that the program opened and a supplementary `1−1=0` calculation worked. However, the submitted observation **does not explicitly state or demonstrate that networking was disabled**. This remains an evidentiary qualification and should be resolved with a dated network-state note, not silently treated as proven. Spec Kit evaluator Amin/Codex marked **Blocked**: the network-off prerequisite was not reliably achieved, no offline GUI launch took place and Amin instructed the assistant to skip the case. This is **not an observed product defect** and does not contradict George's older operator-reported offline Pass; the records describe different runs.

**BASE-AC-15 (clean build and GUI launch).** Thanh reported a fresh BMAD repository clone and checkout of `740c78de`, `mvn -q test` exit code 0, then `mvn -q compile exec:java` opening the GUI. No terminal capture was provided with this evaluator report. Amin/Codex used a clean tracked Spec Kit clone detached at `88f56d0`; the first `clean verify` was environment-blocked because dependencies could not be downloaded in the sandbox; the identical build retried with network access exited 0, producing 81/81 passing tests. A real GUI window was opened/confirmed; the valid OS-automated GUI run separately used the frozen JAR. These new observations should **not** be confused with operator-era AC-15 evidence tied to older commits.

The conditions are not identical: Thanh tested on Windows 11 with Temurin 22 and Maven 3.9.11; Amin's Codex run used macOS 27.0.1 with Corretto 22 and Maven 3.9.16. Both evaluated the documented Baseline behaviour, but platform and evidence modality remain comparison limitations.

Sources: [BMAD evaluator record](https://github.com/VSondur123/spec-driven-development-analysis/blob/main/bmad-calculator/evidence/07-evaluator-execution-sheet.md); [Spec Kit evaluator record](https://github.com/VSondur123/spec-driven-development-analysis/blob/main/github-spec-kit-calculator/evidence/07-evaluator-execution-sheet.md); [Spec Kit run README](https://github.com/VSondur123/spec-driven-development-analysis/blob/main/research/empirical-methodology/evaluations/2026-11-05-spec-kit-baseline/README.md); [network setup log](https://github.com/VSondur123/spec-driven-development-analysis/blob/main/research/empirical-methodology/evaluations/2026-11-05-spec-kit-baseline/offline-network-state.txt); [build attempt log](https://github.com/VSondur123/spec-driven-development-analysis/blob/main/research/empirical-methodology/evaluations/2026-11-05-spec-kit-baseline/build-primary.txt); [successful build log](https://github.com/VSondur123/spec-driven-development-analysis/blob/main/research/empirical-methodology/evaluations/2026-11-05-spec-kit-baseline/build-network-retry.txt).

### 2.6 Native test suites and reliability

| Test context | BMAD | Spec Kit |
|---|---|---|
| Final reported display/native run | 172 tests, 0 failures, 0 skipped | Not applicable as same type of display run |
| Final reported headless/verification run | 172 tests, 0 failures, **57 skipped** | 81 tests, 0 failures, 0 skipped |
| Later supplementary case | LOG-15 tie-rounding run: 174 tests, 0 failures | LOG-15 cases recorded within normal test suite |
| What this means | GUI window tests must be separated from headless results | Headless component tests and manual GUI observation are combined in the evidence package |

The BMAD 174-test LOG-15 run is **later and separate** from the 172-test final Baseline display run. Neither 172 vs 81 nor 174 vs 81 is a defensible quality score because the suites, levels, test definitions and GUI execution modes differ. An AC-level common evaluation is a more valid basis for functional comparison.

Sources: [BMAD final log](https://github.com/VSondur123/spec-driven-development-analysis/blob/main/bmad-calculator/evidence/story-3-3-final-test-log.txt), [BMAD LOG-15 later run](https://github.com/VSondur123/spec-driven-development-analysis/blob/main/bmad-calculator/evidence/log-15-tie-rounding-test-log.txt), [Spec Kit results](https://github.com/VSondur123/spec-driven-development-analysis/blob/main/github-spec-kit-calculator/evidence/06-test-results.md).

### 2.7 Non-functional requirement audit

| Shared NFR | BMAD evidence | Spec Kit evidence | Defensible judgement |
|---|---|---|---|
| NFR-01 Local runtime | macOS, Corretto 22, Maven and launched Swing GUI | Windows, Corretto 22, Maven and launched Swing GUI | Each ran locally in its own environment; no controlled common-OS comparison |
| NFR-02 Prompt response | No numeric latency measurement; architecture proposes synchronous calculation | Owner reports the response “feels instant”; no numeric latency measurement | **No defensible application-speed ranking** |
| NFR-03 No crash on invalid/error input | Automated validation and GUI checks | Automated outcome/no-throw checks and manual observations | Supporting evidence for tested cases in both |
| NFR-04 Reviewable/extensible structure | Separate core/presenter/UI; architecture rule test | Separate core/UI; review log checks no GUI imports in core | Both have architectural support; quality judgement needs rubric |
| NFR-05 Document build/test commands | `BUILD.md`; clean file copy build/test | `quickstart.md`; clean-clone build/launch at interim commit | Spec Kit provides stronger clean-clone evidence; snapshot caveat |

NFR-02 does **not** have a frozen numeric response-time limit, so introducing a threshold retrospectively would be an unfair alteration of the experiment. A benchmark comparing speed would be a separate controlled supplementary experiment rather than an automatic M01 result.

### Aspect A findings

The existing operator evidence shows **no difference in the reported 19 parent AC results**. The main observed difference concerns the completeness of the clean-checkout evidence and the coverage/presentation of supporting records. The current data do **not** establish which calculator is faster, more usable in general or more reliable under untested conditions. A common independent run at equivalent immutable snapshots is still required for formal M01 scoring.

---

## 3. Aspect B – Development Workflow Quality

**Evaluation question:** How did each workflow turn the same initial requirements into planning, architecture, tasks, implementation, testing and human interaction, and what workflow differences can actually be observed?

### 3.1 Requirements engineering and planning

**BMAD** used a PRD, PRD addendum, architecture workflow, epics and stories. The PRD carries shared `BASE-*` IDs inline; it is marked final and includes user journeys, feature requirements, non-goals, NFRs and an assumptions index. BMAD followed a **fast path** and skipped the optional UX step. The evidence status records **3 epics and 9 stories**, with all nine reportedly implemented.

**Spec Kit** used `/speckit-constitution`, `/speckit-specify`, `/speckit-plan`, `/speckit-tasks`, `/speckit-analyze` and `/speckit-implement`. Its generated documents include a constitution, feature specification, plan, technical research, data model, calculation/UI contracts, quickstart, requirements checklist and **34 tasks**. Its plan contains a **Requirement Coverage** table and the status records an `/speckit-analyze` run with **11 findings** and **21/21 internal requirements having at least one task**. This 21-ID internal plan count must not be confused with the **13 shared requirement IDs** used as the M03 denominator.

**Comparison:** Both workflows generated reviewable specification and planning structures. BMAD is more PRD-and-story centred; Spec Kit is specification-and-task centred. Spec Kit's task list is more granular; neither the number of files nor the raw task count measures quality in isolation.

Sources: [BMAD PRD](https://github.com/VSondur123/spec-driven-development-analysis/blob/main/bmad-calculator/_bmad-output/planning-artifacts/prds/prd-bmad-calculator-2026-10-23/prd.md), [BMAD status](https://github.com/VSondur123/spec-driven-development-analysis/blob/main/bmad-calculator/evidence/00-STATUS.md), [Spec Kit specification](https://github.com/VSondur123/spec-driven-development-analysis/blob/main/github-spec-kit-calculator/specs/001-baseline-calculator/spec.md), [Spec Kit plan](https://github.com/VSondur123/spec-driven-development-analysis/blob/main/github-spec-kit-calculator/specs/001-baseline-calculator/plan.md), [Spec Kit tasks](https://github.com/VSondur123/spec-driven-development-analysis/blob/main/github-spec-kit-calculator/specs/001-baseline-calculator/tasks.md).

### 3.2 Architecture and design decisions

BMAD's **Architecture Spine** explicitly defines `calculator.core`, `calculator.presenter`, `calculator.ui` and an application entry point. Its decisions AD-1 through AD-10 cover dependency direction, total core API, `BigDecimal`, error/result formatting, validation order, state ownership, extension points, Swing threading, UI controls and testability/build setup. This is a concentrated native architecture artefact.

Spec Kit distributes the design over `plan.md`, `research.md`, `data-model.md`, `contracts/calculation-contract.md` and `contracts/ui-contract.md`. It defines a GUI-free calculation core, a Swing interface, precise input grammar, error-priority rules, state transitions, and a calculation outcome that represents success or failure. Both implementations appear designed for separated calculation and interface logic, but the shapes of their artefacts differ.

**Comparison:** A dedicated single architecture document may make BMAD's design decisions easier to locate; Spec Kit's multiple contracts/data-model records provide narrower, more operational detail. Both are legitimate native forms. Formal rubric assessment should evaluate relevance, accuracy, clarity, consistency and link verification—not impose the other workflow's document names.

Sources: [BMAD Architecture Spine](https://github.com/VSondur123/spec-driven-development-analysis/blob/main/bmad-calculator/_bmad-output/planning-artifacts/architecture/architecture-bmad-calculator-2026-10-23/ARCHITECTURE-SPINE.md), [Spec Kit research](https://github.com/VSondur123/spec-driven-development-analysis/blob/main/github-spec-kit-calculator/specs/001-baseline-calculator/research.md), [Spec Kit calculation contract](https://github.com/VSondur123/spec-driven-development-analysis/blob/main/github-spec-kit-calculator/specs/001-baseline-calculator/contracts/calculation-contract.md), [Spec Kit UI contract](https://github.com/VSondur123/spec-driven-development-analysis/blob/main/github-spec-kit-calculator/specs/001-baseline-calculator/contracts/ui-contract.md).

### 3.3 Task breakdown and source organisation

BMAD's 3 epics and 9 stories provide larger work units. The status describes story implementation and per-story logs, with some stories becoming primarily end-to-end verification because earlier architectural layers already implemented error handling. Spec Kit's 34 tasks specify setup, foundational code, user stories and polish, frequently naming exact source/test paths and checkpoint artefacts. This supports a descriptive distinction in **work-item granularity**, not a rank in project-management quality.

BMAD uses `src/` and `tests/` with Maven configuration overrides; Spec Kit uses Maven's conventional `src/main/java` and `src/test/java`. Both use Java 22, Swing, `BigDecimal`, Maven 3.9.16 and JUnit Jupiter 6.1.3. Since the directory layouts are allowed implementation choices, a different layout is not a Baseline requirement failure.

### 3.4 Verification strategy, review gates and process deviations

BMAD used per-story validation logs, GUI tests when a display was available and a separate headless run. It reports an architecture linter, human approval gates and some review-subagent activity. Its status explicitly records deviations: PRD/architecture fast path, optional UX omitted, several reviewer gates/subagents omitted, and a self-judged readiness gate.

Spec Kit records test-first red-before-implementation runs, progressive verification, an `/speckit-analyze` result, plan and implementation approvals, owner-dependent GUI checks, and a clean-clone test from an interim commit. Its status/decisions report smaller corrections, including an omitted constitution-template-resolution step, an initially incorrect LOG-15 chronology statement, a UTF-8 BOM correction and retries. Expected red tests before their implementations are **not automatically product defects**.

**Comparison:** The native review practices are different and their evidence is not directly convertible into a single quality number. Their transparency about deviations is valuable for analysing reproducibility and validity. Counting agent corrections, PR reviews or native test counts without common operational definitions would bias the comparison.

Sources: [BMAD status/deviations](https://github.com/VSondur123/spec-driven-development-analysis/blob/main/bmad-calculator/evidence/00-STATUS.md), [Spec Kit status/deviations](https://github.com/VSondur123/spec-driven-development-analysis/blob/main/github-spec-kit-calculator/evidence/00-STATUS.md), [Spec Kit decisions](https://github.com/VSondur123/spec-driven-development-analysis/blob/main/github-spec-kit-calculator/evidence/03-decisions-log.md).

### 3.5 AI interactions and M08 human intervention evidence

BMAD's status records an **approximate chronology labelled intervention 1–9**, including planning recommendations, instruction to maintain a persistent status file, no-commit instruction, selection of no-operation error, plan approval and approvals for stories. The same status explicitly says prompts/responses were not preserved verbatim. Its dates/times are only partially measured.

Spec Kit has a **prompt log P01–P24**. The first eight were reconstructed from the session transcript; full time-of-day stamps were not recorded. Its effort/intervention log describes Q1–Q3 clarification responses, approvals, corrections to LOG-15 and `/speckit-analyze` outputs, human checklist edits, manual launch/screenshots and retries. Active work time was **not measured**.

**M08 caution:** The BMAD chronology of nine approximate numbered interventions and Spec Kit's 24 prompt entries are **different units**. One prompt may be a routine command rather than a bounded intervention episode. Therefore, the result **is not “BMAD 9 versus Spec Kit 24 interventions.”** A defensible M08 requires categorising and bounding episodes under OD-5, including triggers, actions, outcomes and evidence. No final M08 comparative count is reported here.

**M09/M10 caution:** Neither track measured comparable active person-minutes. The existence of approximate times or short wall-clock spans does not provide equivalent phase start/end boundaries; **M09 and M10 are Not calculable** on the historical records as inspected.

Sources: [BMAD intervention chronology](https://github.com/VSondur123/spec-driven-development-analysis/blob/main/bmad-calculator/evidence/00-STATUS.md), [Spec Kit prompt log](https://github.com/VSondur123/spec-driven-development-analysis/blob/main/github-spec-kit-calculator/evidence/04-prompt-log.md), [Spec Kit effort log](https://github.com/VSondur123/spec-driven-development-analysis/blob/main/github-spec-kit-calculator/evidence/05-effort-intervention-log.md).

### Aspect B findings

BMAD's strengths in the available record include a dedicated and explicit architecture spine and an identifiable PRD→architecture→stories process. Spec Kit's strengths include a granular task list, contract/data-model artefacts, a documented analysis step and a more detailed interaction/decision chronology. Both follow significant subsets of their native workflows while recording deviations. **These are descriptive strengths of the observed artefacts**, not proof that a tool is inherently superior or more efficient. The evidence does not support a quantified effort or intervention winner.

---

## 4. Aspect C – Development Results and Documentation

**Evaluation question:** How complete, traceable and reproducible are the planning, implementation, test and decision artefacts produced by each workflow?

### 4.1 Evidence inventory

| Type | BMAD records | Spec Kit records |
|---|---|---|
| Shared requirement interpretation | PRD, addendum, decisions log, shared IDs | Constitution, spec, plan, decisions, shared IDs |
| Architecture/design | Architecture Spine, AD-1…AD-10 | Research, data model, calculation/UI contracts |
| Work tracking | 3 epics/9 stories, sprint-status | 34 tasks, checklist, analyse findings |
| Implementation | Java source under `src/` | Java source under `src/main/java/` |
| Verification | JUnit tests under `tests/`, per-story logs, final/supplementary logs | JUnit tests under `src/test/java/`, incremental/red-green/verification logs |
| Manual checks | AC-01 manual note, three final screenshots | AC-01 note, eight GUI screenshots, further owner reports |
| Interaction/decisions | Approximate intervention chronology, D-series log | P01–P24 prompt log, DL-series decisions, AP-series approvals, effort log |
| Reproduction guidance | `BUILD.md` and clean file-copy evidence | `quickstart.md`, interim clean-clone build/launch logs |

The complete **C Documentation** sheet in the companion workbook links the individual paths. The number of generated documents or screenshots is not a measure of completeness until the applicable evidence checklist, evidence search scope and status criteria are fixed.

### 4.2 M03 – Native traceability candidates

M03 examines explicit native requirement links from 13 **shared requirement IDs** to **implementation** (M03a) and **verification** (M03b). M03c measures whether the link assessment has been completed. The numerator counts **distinct shared IDs**, never the number of files, trace rows, or internal Spec Kit requirement IDs. The workbook's **M03 trace candidates** sheet records each individual shared ID with candidate document, implementation and test chains.

| Shared requirement | BMAD candidate chain | Spec Kit candidate chain |
|---|---|---|
| FR-01 Offline GUI | PRD FR-2 → AD-8 → Story 1.1 → Main/UI → smoke/manual check | Spec US4 → Plan CalculatorApp → T027–T029 → app → manual/build check |
| FR-02 Numeric input | PRD FR-1 → AD-9 → Story 1.3 → fields → GUI tests | Spec US1 → Plan CalculatorPanel → UI tasks → panel test/screenshots |
| FR-03 Operation select | PRD FR-3 → AD-7/9 → Story 1.3 → Operation/buttons → tests | Spec US1 → Operation/toggle group → T004/UI tasks → tests |
| FR-04 Calculation | PRD FR-4 → AD-2/3/4/9 → Stories 1.2/1.3 → Calculator/Presenter/UI → AC tests | Spec US1 → Plan Calculator.calculate → tasks → core/panel AC tests |
| FR-05 Clear | PRD FR-6 → AD-6 → Story 3.1 → Presenter/UI → AC-10 tests | Spec US3 → Plan clear state → T024–26 → Panel → AC-10 tests |
| FR-06 Invalid input | PRD FR-7 → AD-2/5 → Story 2.1 → parser/core → error tests | Spec US2 → parser/error priority → foundational/US2 → AC-11/12/17 tests |
| FR-07 Divide zero | PRD FR-9 → AD-2/4/5 → Story 2.2 → Calculator → AC-13 tests | Spec US2 → calculation contract → US2 → Calculator → AC-13 tests |
| FR-08 Recovery | PRD FR-10 → AD-6 → Story 2.3 → Presenter/UI → AC-14 tests | Spec US3 → state design → US3 tasks → panel recovery test |
| NFR-01 Runtime | PRD NFR-1 → architecture stack/Story 1.1 → runtime evidence | Plan context → T003/build → environment record |
| NFR-02 Response | PRD NFR-2 → AD-8; **no timed test** | Plan observation only; owner subjective report; **no timed test** |
| NFR-03 No crash | PRD NFR-3 → AD-2/5 → 2.1/2.2 → error/no-dialog tests | Plan contract → core/outcome → no-throw tests |
| NFR-04 Extensibility | PRD NFR-4 → AD-1/7/10 → Story 3.2 → ArchitectureRulesTest | Plan package separation → T030 review → review log |
| NFR-05 Build reproducibility | PRD NFR-5 → AD-10 → Story 3.2 → BUILD/clean copy | Plan/quickstart → T029/33 → clean-clone logs |

**Status:** There are candidate native chains **for all 13 IDs in the collected records**, but this statement is **not** a formal `M03a=13/13` or `M03b=13/13`. Some paths are summaries in native traceability tables and some relationships are architectural/semantic candidates. The reviewer still needs to inspect the actual implementation/test content at the selected frozen snapshot, resolve native-to-shared ID mapping, distinguish **native explicit vs reconstructed**, record origin/authorship, and mark Y/N/pending with evidence availability. A candidate is not a verified link merely because an ID occurs in a file.

The audit should also avoid incorrectly penalising documentation-only or qualitative NFRs for not pointing to a Java class. Verified links must be **appropriate to the actual requirement**. `NFR-02` has no timed verification, so no numerical response-performance link should be asserted.

Sources: [BMAD traceability table](https://github.com/VSondur123/spec-driven-development-analysis/blob/main/bmad-calculator/evidence/04-acceptance-results-and-traceability.md), [BMAD Architecture Spine](https://github.com/VSondur123/spec-driven-development-analysis/blob/main/bmad-calculator/_bmad-output/planning-artifacts/architecture/architecture-bmad-calculator-2026-10-23/ARCHITECTURE-SPINE.md), [Spec Kit requirement coverage](https://github.com/VSondur123/spec-driven-development-analysis/blob/main/github-spec-kit-calculator/specs/001-baseline-calculator/plan.md), [Spec Kit requirement-to-test table](https://github.com/VSondur123/spec-driven-development-analysis/blob/main/github-spec-kit-calculator/evidence/06-test-results.md).

### 4.3 M04 – Defects

BMAD's acceptance record says that the final display run had zero test failures and **no defect IDs were raised**. Spec Kit's inspected evidence does not include a complete, distinct Baseline product-defect register. Some Spec Kit log entries document intentional test-first red runs before the implementation existed; these are not automatically defects in a completed product. Minor agent/process slips (such as a BOM fix) are not by themselves evidence of a calculator requirement failure.

**M04 conclusion: Not calculable as a defensible cross-track defect comparison.** “No defect IDs raised” and “no defect register found” are different observation types; neither proves all product defects absent. A reviewer must define the inspection scope, identify distinct departures from requirements, deduplicate underlying causes and preserve discovery sources. M05 late-discovery share also remains not calculable.

### 4.4 M08 – Human interventions; M12 – Rework

The available BMAD and Spec Kit interaction evidence is described in §3.5. For the metric table, **do not convert approximate intervention chronology to precise episode counts** or count every Spec Kit prompt as an intervention. Rework episodes require documented correction of previously produced content due to a defect or inconsistency, linked to before/after evidence. A commit, routine checkpoint or normal test-first workflow is not an OD-7 rework episode by itself. M08 and M12 remain preliminary until a common event register is populated.

### 4.5 M15 – Evidence completeness

Both tracks have extensive evidence packages, but they are not equally organised. BMAD's records include a status file, environment, decisions, acceptance/traceability summary, per-story logs, screenshots, manual check, planning artefacts, source and tests. Spec Kit includes analogous files plus AP approvals, P01–P24, a per-activity effort/intervention log, eight screenshots and a variety of build/red-green/review logs.

A valid M15 fraction is **Available / (Available + Confirmed absent + Unavailable)** over an **agreed applicable checklist**, while **Unassessed** items are shown separately. The baseline-applicable checklist has not yet been fully agreed and scored. Therefore **M15 is Not calculable**; the table above is an inventory, not a disguised percentage or winner declaration. An unchecked preparation checkbox does not mean the actual evidence is absent.

### 4.6 M11 – J1–J7 rubric ratings and M14 agreement

No independent initial rubric ratings were found for either Baseline. Under the catalogue's phase applicability, **J1–J4 and J7** are Baseline-relevant and **J5/J6** concern the change phase; their Baseline N/A state should be documented by the reviewers. Ratings must remain independent until each reviewer has submitted the initial judgement with supporting artefact references. **Do not assign arbitrary 0–3 values or average ordinal scores.** Since paired initial ratings do not exist, M14 reviewer agreement is also Not calculable.

### Aspect C findings

Both workflows produced substantial native documentation and test evidence. BMAD demonstrates a dedicated architecture record and explicit requirement→story→test references. Spec Kit demonstrates extensive task/contract records and richer prompts/decisions. However, this observation only supports a **descriptive documentation difference**, not an independently measured M03, M11 or M15 superiority claim. Missing formal assessments must be reported as such.

---

## 5. Metric status: what Experiment 1 actually supports

| Metric | Baseline status | Explanation |
|---|---|---|
| **M01** | **Operator-reported 19/19 each; formal score pending** | Both recorded Pass, but missing common frozen-snapshot independent execution |
| M02 | Not calculated | Derive from evaluator M01 and shared association mapping; four NFRs uncovered |
| **M03** | **Native trace tables located; 13 candidate ID chains per track; M03a/b/c pending** | Every native semantic link and its origin must be assessed |
| **M04** | **Not calculable** | No complete verified distinct defect register |
| M05 | Not calculable | Needs complete defects + independent discovery-source data |
| M06 | Not applicable | Baseline/change paired regression metric |
| M07, M07b | Not applicable | CR-01 change-impact metrics |
| **M08** | **Evidence exists; not normalised** | BMAD approximate episodes and Spec Kit prompts are not the same units |
| M09 | Not calculable | Active work intervals never measured comparably |
| M10 | Not calculable | Comparable agreed phase boundary timestamps absent |
| **M11** | **Not rated** | No independent J1–J4/J7 initial ratings; J5/J6 baseline N/A with reviewer confirmation |
| M12 | Not calculable | No consistent rework episode register |
| M13 | Not assessed | Reconstructed mappings must be kept separate from native M03 |
| M14 | Not calculable | No paired independent ratings |
| **M15** | **Inventory available; ratio not calculable** | Applicable checklist not frozen/assessed |
| M16 | Descriptive context only | Test counts, commits and file counts not quality measures |

No six metrics are silently replaced by generic application-performance measurements: the above retain the project's own operational definitions. The primary six Baseline metrics being developed are M01, M03, M04, M08, M11 and M15; supportive and contextual metrics are documented at their real readiness level.

## 6. Validity, fairness and limitations

**Construct validity.** Pass coverage measures checked acceptance behaviour, not all aspects of application quality. A source link is not automatically valid traceability. A prompt is not automatically an intervention. A native test count is not a quality or coverage score. A human-observed “instant” calculation is not measured latency.

**Internal validity.** The case compares two complete workflows involving different operators and operating systems. Spec Kit's model changed during the session. There is one case and one workflow execution per tool. A causal claim that any difference was caused solely by BMAD or Spec Kit would be unjustified.

**Conclusion validity.** The small sample cannot support statistical significance, broad effectiveness estimates or universal “winner” claims. Rubric ratings must be supported by independent reviewers. Numeric M03/M15 values without completed assessments would be misleading.

**Reliability and reproducibility.** BMAD and Spec Kit have recorded operator checks, but their frozen-snapshot status is not equally established. Evidence provenance varies (automated tests, owner reports, screenshots, copied-file build, interim Git clone). The report preserves these differences rather than flattening them into equivalent proofs.

**Shared input limits.** Four NFR IDs have no direct linked AC and three AC IDs no linked requirement. These are reported once as study-wide input characteristics, not as track failures. LOG-12…LOG-15 agreed clarifications must not retrospectively create extra parent ACs unless the shared document is formally revised for both.

**Timing and missing evidence.** Historical active effort was not measured and comparable elapsed boundaries were not defined. No retroactive estimates are used. Missing access, unassessed data and verified absence are separate states.

## 7. Actions needed for a fully closed formal Experiment 1

1. **Confirm exact snapshots.** Verify BMAD's `baseline-frozen` tag target and the relationship between `ebac834`, `943c73f` and the later LOG-15 run. George should record the final clean immutable Spec Kit Baseline commit/tag and preserve the final evidence changes.
2. **Run a common M01 evaluation.** Use the same 19 parents and required subcases, step order and expected outcomes on both exact snapshots; record evaluator, date, platform, JDK, commands, observed layer, expected/actual, screenshot/log and rerun metadata. Preserve the operator runs separately.
3. **Complete the M03 semantic audit.** For each of the 13 shared IDs, inspect native implementation and verification artefacts, record native→shared mapping, origin/authorship and Y/N assessment. Calculate M03a, M03b and M03c only after that process.
4. **Review distinct M04 defects.** Deduplicate defects across failed ACs, logs, review issues and final behaviour, and record first discovery source.
5. **Normalise M08/M12 events.** Distinguish interventions, prompts, approvals, checkpoints and rework under the agreed units; leave unmeasured time as unknown.
6. **Collect independent M11 ratings.** Two reviewers should record initial J1–J4/J7 ratings, evidence and rationale; register J5/J6 phase applicability and any disagreements.
7. **Assess M15.** Fix the Baseline-applicable evidence checklist before scoring, then assign Available/Confirmed absent/Unavailable/Unassessed for both tracks, with source references.
8. **Report final interpretation with limitations.** Keep M09/M10 Not calculable where no measured/bounded data exist; do not invent a total, composite, radar score or performance winner.

## 8. Conclusion

The extracted Baseline evidence shows **the same recorded functional acceptance outcomes for the two applications (19/19 parent AC Pass per operator)** and substantial artefacts from both development workflows. BMAD generated a clearly delineated PRD/Architecture Spine/epics-and-stories chain; Spec Kit generated a constitution/specification/plan/task/contract chain and a more detailed prompt and decision record. The clearest **evidence-strength difference** is that Spec Kit documents a real clean Git clone for AC-15 (albeit at an interim commit), whereas BMAD's clean-build record used a file copy without repeating launch from that copy. No controlled benchmark establishes that either calculator executes more quickly.

This is a useful and auditable **preliminary comparison of the existing Baseline outputs**, but **not a completed independent metric evaluation**. Formal claims about verified M01 coverage, M03 native traceability fractions, distinct defect totals, M08 intervention counts, M11 rubric judgements or M15 evidence-completeness ratios would currently exceed the evidence. Explicitly distinguishing established findings from assessment gaps makes the study more reproducible, more academically defensible and ready for the subsequent CR-01 comparison once both Baselines have been frozen and evaluated.

---

## Source register

- [Study project and research questions](https://github.com/VSondur123/spec-driven-development-analysis/blob/main/project.md)
- [Shared Baseline requirements](https://github.com/VSondur123/spec-driven-development-analysis/blob/main/shared/baseline-requirements.md)
- [Shared Baseline acceptance criteria](https://github.com/VSondur123/spec-driven-development-analysis/blob/main/shared/acceptance-criteria.md)
- [Shared experiment decisions and LOG-01…15](https://github.com/VSondur123/spec-driven-development-analysis/blob/main/shared/experiment-log.md)
- [Metric catalogue and operational definitions](https://github.com/VSondur123/spec-driven-development-analysis/blob/main/research/comparison-frameworks/metric-catalogue.md)
- [Proposed evaluation rubric](https://github.com/VSondur123/spec-driven-development-analysis/blob/main/shared/evaluation-rubric.md)
- [Proposed experiment protocol](https://github.com/VSondur123/spec-driven-development-analysis/blob/main/shared/experiment-protocol.md)
- [BMAD PRD](https://github.com/VSondur123/spec-driven-development-analysis/blob/main/bmad-calculator/_bmad-output/planning-artifacts/prds/prd-bmad-calculator-2026-10-23/prd.md)
- [BMAD Architecture Spine](https://github.com/VSondur123/spec-driven-development-analysis/blob/main/bmad-calculator/_bmad-output/planning-artifacts/architecture/architecture-bmad-calculator-2026-10-23/ARCHITECTURE-SPINE.md)
- [BMAD Baseline status](https://github.com/VSondur123/spec-driven-development-analysis/blob/main/bmad-calculator/evidence/00-STATUS.md)
- [BMAD environment](https://github.com/VSondur123/spec-driven-development-analysis/blob/main/bmad-calculator/evidence/01-environment.md)
- [BMAD decisions](https://github.com/VSondur123/spec-driven-development-analysis/blob/main/bmad-calculator/evidence/02-decisions-log.md)
- [BMAD AC results and traceability](https://github.com/VSondur123/spec-driven-development-analysis/blob/main/bmad-calculator/evidence/04-acceptance-results-and-traceability.md)
- [BMAD final test log](https://github.com/VSondur123/spec-driven-development-analysis/blob/main/bmad-calculator/evidence/story-3-3-final-test-log.txt)
- [BMAD AC-01 manual check](https://github.com/VSondur123/spec-driven-development-analysis/blob/main/bmad-calculator/evidence/ac-01-manual-check.md)
- [Spec Kit specification](https://github.com/VSondur123/spec-driven-development-analysis/blob/main/github-spec-kit-calculator/specs/001-baseline-calculator/spec.md)
- [Spec Kit plan](https://github.com/VSondur123/spec-driven-development-analysis/blob/main/github-spec-kit-calculator/specs/001-baseline-calculator/plan.md)
- [Spec Kit tasks](https://github.com/VSondur123/spec-driven-development-analysis/blob/main/github-spec-kit-calculator/specs/001-baseline-calculator/tasks.md)
- [Spec Kit Baseline status](https://github.com/VSondur123/spec-driven-development-analysis/blob/main/github-spec-kit-calculator/evidence/00-STATUS.md)
- [Spec Kit environment](https://github.com/VSondur123/spec-driven-development-analysis/blob/main/github-spec-kit-calculator/evidence/01-environment.md)
- [Spec Kit approvals](https://github.com/VSondur123/spec-driven-development-analysis/blob/main/github-spec-kit-calculator/evidence/02-approvals.md)
- [Spec Kit decisions](https://github.com/VSondur123/spec-driven-development-analysis/blob/main/github-spec-kit-calculator/evidence/03-decisions-log.md)
- [Spec Kit prompt log](https://github.com/VSondur123/spec-driven-development-analysis/blob/main/github-spec-kit-calculator/evidence/04-prompt-log.md)
- [Spec Kit effort/intervention log](https://github.com/VSondur123/spec-driven-development-analysis/blob/main/github-spec-kit-calculator/evidence/05-effort-intervention-log.md)
- [Spec Kit AC results/traceability](https://github.com/VSondur123/spec-driven-development-analysis/blob/main/github-spec-kit-calculator/evidence/06-test-results.md)

*The GitHub links identify the evidence paths inspected. Some operator records refer to earlier, uncommitted or interim versions. For formal reproducibility, replace branch-head references with the approved immutable commit SHA once the evaluation snapshots are confirmed.*

---

# Supplementary audit update — 4 November 2026

> **Historical evidence checkpoint:** This appendix records the 2026-11-04 preparatory state. Where it says no independent evaluation had been performed, read that as **true at this earlier checkpoint**; the 2026-11-05 evaluator result in §2.1 supersedes it.

This section updates the initial retrospective comparison with direct read-only GitHub commit/branch verification, additional Java source/test inspection, a structured intervention episode screen and a complete provisional **EC-01–EC-21** checklist audit. The original three aspects—**A. Application Quality, B. Development Workflow Quality, C. Development Results and Documentation**—remain the organising framework. The companion workbook supplies row-level evidence; this section specifies what the added review can and cannot establish.

## D1. Baseline snapshot verification — completed as far as the repository permits

| Track | Verified object | Exact SHA / finding | Status for formal evaluation |
|---|---|---|---|
| BMAD | [`baseline-frozen` tag](https://github.com/VSondur123/spec-driven-development-analysis/commit/740c78de062bb1edd8076e4a49dde768b0fe1e49) | `740c78de062bb1edd8076e4a49dde768b0fe1e49` | **Identified**: tag resolves and `bmad-baseline` branch is identical to tag. |
| BMAD | Historical acceptance-record commit | `ebac834b1902bef07585197537ce8e4f2c5d3c68` | **Different**: frozen tag is three commits ahead; the acceptance record and manual check have earlier provenance. |
| BMAD | Final GUI operator offline check | `943c73f` in operator note | **Owner-reported**, not an independent network-off observation. |
| Spec Kit | [`george/spec-kit-baseline` branch](https://github.com/VSondur123/spec-driven-development-analysis/commit/88f56d0979e2fae1fb5d59e8681975e3a1fc2a27) | `88f56d0979e2fae1fb5d59e8681975e3a1fc2a27` | **Identified branch tip, not a confirmed final freeze**. Branch tip is two commits ahead of `419f92f`, while track status still says a final freeze/tag was not declared. |
| Spec Kit | Interim clean-clone result | [`419f92ffde832371a21ff3e8eccea66d54a80e71`](https://github.com/VSondur123/spec-driven-development-analysis/commit/419f92ffde832371a21ff3e8eccea66d54a80e71) | **AC-15 verified as an existing operator report**, but the recorded test does not automatically represent a later branch tip. |

**Finding:** The BMAD Baseline is identifiable as a tagged commit; George's branch now exists at a stable, readable Git SHA. That is a useful improvement over the earlier comparison, which only mentioned interim commits. However, a branch head is not synonymous with an owner-declared frozen evaluation submission. The same rule must apply to both tracks. The following actions remain external: confirm George's formal freeze; reconcile the BMAD test/manual observations with its tag; and establish exact primary evaluation snapshots under a team-adopted protocol.

Sources: [BMAD status](https://github.com/VSondur123/spec-driven-development-analysis/blob/baseline-frozen/bmad-calculator/evidence/00-STATUS.md), [BMAD operator AC results](https://github.com/VSondur123/spec-driven-development-analysis/blob/baseline-frozen/bmad-calculator/evidence/04-acceptance-results-and-traceability.md), [Spec Kit status on branch](https://github.com/VSondur123/spec-driven-development-analysis/blob/george/spec-kit-baseline/github-spec-kit-calculator/evidence/00-STATUS.md), [Spec Kit AC results](https://github.com/VSondur123/spec-driven-development-analysis/blob/george/spec-kit-baseline/github-spec-kit-calculator/evidence/06-test-results.md).

## D2. M01 – deeper acceptance evidence audit

The recorded **19/19 Pass on each track** is corroborated by the operators' criterion-by-criterion logs. Direct source inspection also confirmed: BMAD test methods for AC-04/05/06/07/08/09/11/12/13/17/18, BMAD Swing interaction methods for AC-02/03/04–09/19 and selection; Spec Kit core tests and GUI panel tests for the equivalent arithmetic, input and selection cases. The retained builds report BMAD 172 display tests (0 failures, 0 skips) and Spec Kit 81 tests (0 failures, 0 skips).

Three qualifications remain. **First**, the offline AC-01 state relies on each owner's statement; BMAD's screenshot does not depict the network indicator and Spec Kit's globe icon alone is not conclusive. **Second**, BMAD AC-15 is a clean-copy test without launch from that copy, while Spec Kit AC-15 is a clean Git clone/launch at an interim commit. **Third**, the 19 Pass values were recorded in operator-specific environments and cannot be promoted to a common independent evaluator run. Therefore **M01 formal evaluator pass coverage = Not assessed** for both tracks. This is not the same as 0/19.

Directly inspected sources include [BMAD CalculatorTest](https://github.com/VSondur123/spec-driven-development-analysis/blob/baseline-frozen/bmad-calculator/tests/calculator/core/CalculatorTest.java), [BMAD GUI interaction tests](https://github.com/VSondur123/spec-driven-development-analysis/blob/baseline-frozen/bmad-calculator/tests/calculator/ui/CalculatorFrameInteractionTest.java), [Spec Kit CalculatorTest](https://github.com/VSondur123/spec-driven-development-analysis/blob/george/spec-kit-baseline/github-spec-kit-calculator/src/test/java/calculator/core/CalculatorTest.java) and [Spec Kit CalculatorPanelTest](https://github.com/VSondur123/spec-driven-development-analysis/blob/george/spec-kit-baseline/github-spec-kit-calculator/src/test/java/calculator/ui/CalculatorPanelTest.java).

## D3. M03 – requirement-by-requirement source inspection

The workbook's **M03 assessed links** sheet now identifies all **13 shared requirements** for both tracks, with native mapping, implementation path, test/manual verification and a source-specific caveat. The review checked actual BMAD `Calculator.java`, `CalculatorFrame.java`, `CalculatorPresenter.java`, `CalculatorTest.java`, `CalculatorFrameInteractionTest.java` and `ArchitectureRulesTest.java`. For Spec Kit, it checked `Calculator.java`, `CalculatorPanel.java`, `CalculatorPanelTest.java`, `OperandParserTest.java` and the `t030-review` log. The earlier native PRD, architecture, plan, contract and operator traceability tables were also consulted.

| Scope | BMAD direct evidence | Spec Kit direct evidence | What the evidence supports |
|---|---|---|---|
| Numeric operations and error validation (FR-04, FR-06, FR-07) | `Calculator.evaluate` validates inputs and returns an `Outcome`; tests name shared ACs | `Calculator.calculate` validates using `OperandParser`, returns `CalculationOutcome`; `CalculatorTest` tests outcomes | Strong source/test corroboration of candidate implementation and verification links. |
| GUI input, operation choice, Enter and Clear (FR-02/03/04/05) | `CalculatorFrame` names controls and wires Calculate, Enter and Clear handlers; GUI tests inspect fields/operations | `CalculatorPanel` names controls and wires button/Enter/Clear; panel tests inspect fields/operations | Strong support for native chains to GUI implementation and tests, but no new independent GUI run. |
| Architecture/reviewability (NFR-04) | `ArchitectureRulesTest` guards core/presenter independence from Swing | `t030-review` reports no GUI imports in `calculator.core` | Supporting architecture evidence exists in both, with differing verification methods. |
| Offline launch, build, responsiveness (FR-01, NFR-01/02/05) | Manual AC-01; runtime/build records; no timed speed test | Manual AC-01; interim clean clone; no timed speed test | Documentation exists; offline and NFR-02 evidence remains qualified. |

**Why M03 is not simply 13/13:** the catalogue requires separate M03a implementation, M03b verification and M03c assessment-completion denominators for **distinct shared requirement IDs**, with explicit native link origin and a semantic review of every relevant stage. A code file mentioned by an evaluator is not automatically a native explicit link. Some links were verified by direct source inspection, while others are supported by existing native operator mapping and still need full stage-by-stage provenance verification. The 13 rows are *reviewed candidate chains*, not automatically 13 native-credit successes. No unsupported numerator is written.

## D4. M04 – defects and corrections screened, but no invented zero

| Observation type | BMAD | Spec Kit | Interpretation |
|---|---|---|---|
| Final native suite | 172 display tests, no failed tests | 81 final tests, no failed tests | This is not a distinct-defect count. |
| Defect register | Acceptance record says no defect IDs were raised | No standalone distinct product-defect register identified in inspected evidence | Neither proves zero defects outside tested scope. |
| Process/rework evidence | Planning story-slicing overlap and review false positives documented | Early LOG-15 wording correction, BOM cleanup, clone-path retry and later control-character correction | These are process findings/corrections, not automatically application defects. |
| TDD red runs | Not a basis for defect count | Red-before-code runs are intentional test-first evidence | Expected red tests do not represent post-implementation product defects without a defined failure. |

The workbook's **M04 defect screening** separates confirmed product defect findings from agent/process corrections and environmental retries. No complete, deduplicated defect register or independent defect-discovery history exists, so M04 and M05 formal numeric values remain **Not calculable**. Treat this as a data limitation, not a finding of perfect reliability.

## D5. M08 – evidence-grounded candidate intervention episodes

The workbook's **M08 candidate episodes** records **eight BMAD retrospective candidate episodes** from the approximate status chronology and **nine grouped Spec Kit candidate episodes** from P01–P24, approvals, decisions and effort notes. These are *audit groupings*, not adopted metric results. In particular, Spec Kit P01–P24 represent prompts; they must not be counted automatically as 24 human intervention episodes. BMAD's 1–9 sequence records decision points with approximate times, and some events share a timestamp and could be one episode under an adopted boundary rule.

| Evidence characteristic | BMAD | Spec Kit |
|---|---|---|
| Prompt/interaction history | Approximate dated decision/intervention chronology; original prompts not saved verbatim | 24 numbered prompts, P01–P08 reconstructed; later logged as they occurred |
| Candidate intervention episodes in this worksheet | 8 retrospective groups; some may merge/split | 9 prompt/decision clusters; some may merge/split |
| Measured active minutes | Not available | Not measured |
| Comparability | **Not yet a reliable intervention-rate comparison** | **Not yet a reliable intervention-rate comparison** |

This is materially more useful than comparing 9 to 24, but the numerical M08 result still requires agreement on event boundaries, exclusion of routine prompting and a documented retrospective coverage period. M09 active effort remains **Not calculable**.

## D6. M11 – reviewer-ready rubric audit

The workbook's **M11 reviewer preparation** contains a criterion-by-criterion evidence pack using the actual [J1–J7 rubric anchors](https://github.com/VSondur123/spec-driven-development-analysis/blob/main/shared/evaluation-rubric.md): J1 specification clarity; J2 native traceability; J3 requirements-informed verification; J4 NFR treatment; J5 pre-coding change-impact analysis; J6 change propagation; J7 evidence and reproducibility. It includes **AI-assisted preliminary anchor discussions**, not independent reviewer scores. They are suggestions for human inspection, not official M11 outcomes.

For Baseline, J5 and J6 concern change-phase activities and should be marked **N/A subject to reviewer agreement**. For J1–J4 and J7, the current formal entries are **NR (not independently rated)**. No reviewer identity, blind review, independent initial rating or reconciliation is invented. A zero would be inaccurate when a rating simply was not conducted.

## D7. M15 – full EC-01 through EC-21 provisional evidence checklist

The workbook now includes **42 item-by-track records**: EC-01–EC-21 for BMAD and Spec Kit. Every row contains the collection obligation, phase applicability, an evidence-availability state and an explanation of the existing evidence or gap. **EC-12–EC-15 are change-only and Not applicable to Baseline.** EC-19 is optional and its applicability depends on the adopted collection scope. The checklist preserves three separate ideas: evidence exists, the obligation is fully met, and the checklist has been adopted. One available document does not necessarily satisfy every subfield of a collection obligation.

Key observations:

- **BMAD:** native artefacts, AC logs, environment and decision records are Available; the prompt chronology is approximate rather than verbatim; the frozen tag exists; a comparable defect register, active-minute intervals, independent GUI run, reviewer ratings and independent reproduction records are unavailable.
- **Spec Kit:** native artefacts, P01–P24 prompt log, tests, eight screenshots and interim clean-clone logs are Available; early prompts are reconstructed and working-time intervals are unmeasured; the branch head is identified but a final freeze declaration is not established; independent evaluator runs/ratings are unavailable.
- **Common:** no effective-date adoption record for a shared protocol/rubric or pre-approved item-by-collector applicability set has been established from the inspected material.

**M15 formula proposed by the catalogue:** `Available / (Available + Confirmed absent + Unavailable)`, calculated **only over a pre-agreed applicable obligation set**, with Unassessed reported separately. Because applicability and collector-level splits are not yet adopted, calculating a fraction from these preliminary 42 checklist rows would be methodologically misleading. **M15 official result = Not calculable**. This is a completed *provisional checklist audit*, not a formally adopted completeness score.

Checklist source: [Amin's evidence-checklist.md](https://github.com/VSondur123/spec-driven-development-analysis/blob/main/research/empirical-methodology/evidence-checklist.md).

## D8. Updated metric completion and responsibility boundary

| Work item | Done here | Still requires evidence or external action |
|---|---|---|
| Baseline snapshot verification | BMAD tag SHA confirmed; Spec Kit branch SHA confirmed; interim/freeze distinction recorded | George formally declares freeze; team reconciles which exact tree each acceptance run represents. |
| M01 | 19 parent ACs per track extracted, test/source spot-checks | A genuine common independent GUI/acceptance run on both agreed frozen trees. |
| M03 | 13 requirement-level native mapping chains per track documented, direct implementation/test spot-checks | Full semantic native-origin and stage-by-stage audit before scoring M03a/b/c. |
| M04 | Operator results, review notes and process corrections screened | Completeness-bounded defect register and discovery-source evidence to justify any numeric count. |
| M08 | Retrospective grouped episode candidates produced for both operators | Agree counting convention and ensure equivalent observation coverage. |
| M11 | J1–J7 evidence pack with preliminary anchor discussions | Appointed independent human reviewers and initial ratings; team-approved effective rubric. |
| M15 | EC-01–EC-21 evidence availability assessed provisionally for each track | Agree item applicability and collector splits before reporting ratio. |
| M09/M10 | Missing measured time/boundaries documented | Not calculable unless genuine contemporaneous records are found. |

The study's [project.md](https://github.com/VSondur123/spec-driven-development-analysis/blob/main/project.md) explicitly compares **complete tool–agent–operator workflows**, not the causal contribution of BMAD versus Spec Kit in isolation. The original comparison along the three aspects remains the primary analysis. The enhanced audit supplies a more transparent evidence chain while preserving limitations instead of inflating scores.


---

# Final evidence-disposition addendum (4 November 2026)

> **Historical evidence checkpoint:** These are prior desk-study dispositions. New baseline evaluator results are integrated in the revised main sections and the final 2026-11-05 update below.

This section supersedes earlier statements that the M03 and M15 desk review itself had not been performed. The revised workbook contains a row-level **evidence-based desk determination** for each of the 13 shared requirements on both tracks, and a state for each of the 42 EC track-item checklist entries. These are **not** independently executed test results or independent human ratings.

## M03 – Requirement-by-requirement desk assessment

All 13 shared requirement chains for each tool were evaluated using the documented native specification/design/task/test mapping and the source/test evidence available in the repository. The revised `M03 assessed links` worksheet gives an explicit finding for each requirement and tool instead of a blank or generic `Unassessed` value.

- **BASE-FR-01:** Native launch/GUI chains are supported by source and test records; the offline state remains operator-reported rather than independently observed.
- **BASE-FR-02 to BASE-FR-08:** Native planning-to-implementation-to-test chains are documented and corroborated by the available code/test evidence. This supports a positive desk-review finding but not an independently executed acceptance result.
- **BASE-NFR-01:** Runtime/build documentation and operator environment records support the documented chains; platforms differ.
- **BASE-NFR-02:** A prompt response design is described, but no controlled timed test or numeric performance result exists for either track. Verification of latency is **not demonstrated**.
- **BASE-NFR-03 and BASE-NFR-04:** Source organisation, no-throw/error tests, architecture rules and review evidence support the documented chains.
- **BASE-NFR-05:** Build/reproduction evidence exists in both tracks, with different limits: BMAD used a clean file copy; Spec Kit used a clean clone of an interim commit.

**Interpretation:** The desk assessment is complete for the identified repository evidence. It would be inaccurate to report a formal `M03a=13/13` or `M03b=13/13` from this alone: those official ratios require applying the team's agreed explicit-native-link verification and assessment-completion rules to the exact final snapshots. The workbook therefore records concrete evidence verdicts instead of inventing a formal ratio.

## M04 – Defect review disposition

The existing test/acceptance/decision records were screened. BMAD reports no final failing tests or defect IDs. A complete distinct product-defect register was not found for Spec Kit. Expected red-phase TDD failures are not automatically product defects. The supported conclusion is **no documented comparative defect count**, not a fabricated 0-versus-0 result.

## M08 – Human-intervention review disposition

The recorded BMAD approximate chronology and Spec Kit P01–P24 prompt/intervention sources were extracted and classified in the workbook. Their counting units and provenance differ: a prompt is not necessarily a human intervention, and an approximate retrospective event may not be a bounded episode. A numerical comparison is therefore **not defensible from the supplied historical logs**.

## M11 – Rubric disposition

The workbook records evidence-based qualitative discussion for J1–J7. J5 and J6 are **not applicable** to Baseline because they concern change impact and implementation. The remaining formal 0–3 ratings cannot be attributed to independent human reviewers when no such ratings were supplied. This is a documented absence of required external evidence, not an empty cell or an AI-created substitute score.

## M15 – Evidence checklist disposition

Every one of the 42 EC track-item entries has a retrospective evidence state in the revised workbook. EC-12 through EC-15 are marked **N/A – Change phase only**, rather than `Unassessed`. The remaining entries are classified by availability or unavailability in the inspected records. This is a completed descriptive checklist audit. A formal M15 percentage requires the research team to adopt the applicable checklist denominator and collection/assessment boundaries; none is invented here.

## Final submission interpretation

The three-aspect **retrospective Baseline evidence comparison is complete as a desk study**. The available sources do not support claiming that the *independent experimental evaluation* is complete. No new GUI test run, missing timing measurements, human reviewer ratings, or final Spec Kit freeze has been fabricated. This distinction should remain explicit in any submitted academic report.


---

# Appendix E — Alignment with the research packages (submission edition)

> **Dating note:** The source integration below was prepared before the 2026-11-05 evaluator runs and is retained as historical methodology context, not the latest M01 status.

**Document control.** This appendix reconciles the Stage 1 retrospective comparison with the 2026-10-30 draft-5 comparison catalogue/visualisation proposal and the 2026-11-03 draft-5 empirical methodology package. The corresponding expanded workbook is `Experiment_1_Baseline_Comparison_FINAL.xlsx`. This appendix is a methodological crosswalk, not a claim of new independent observations or of formal team adoption. The earlier body and Appendix D retain the original source-by-source analysis; where a candidate mapping is described, the stricter verification limitations in this appendix apply.

## E1. Source hierarchy and scope

1. **Authoritative shared input:** `shared/baseline-requirements.md` and `shared/acceptance-criteria.md` as actually frozen, with subsequent LOG-12–15 clarifications explicitly distinguished. Baseline denominators: 13 requirements and 19 parent acceptance criteria; additional required subcases do not enlarge the parent denominator.
2. **Proposed comparison instruments:** `research/comparison-frameworks/metric-catalogue.md` (draft-5), `visualisation-proposal.md` (draft-5), `README.md`, `literature-review.md`, and `references.md`. The latter two support research rationale and source provenance, not operator outcomes.
3. **Proposed empirical instruments:** `research/empirical-methodology/README.md`, `methodology-review.md`, `protocol-recommendations.md`, `evaluation-rubric-proposal.md`, `evaluation-templates.md`, `evidence-checklist.md`, `validity-threats.md`, and `references.md` (2026-11-03 draft-5 package). The study's source verification and AI-use requirements remain in force.
4. **Future authority:** The shared adopted rubric and experiment protocol, if approved, override proposals. An approved PR or merged draft is not an adopted study procedure. The retrospective Baseline cannot be backdated as if the protocol was adopted before implementation.

## E2. Workbook-to-template crosswalk

| Empirical template | Submission workbook record(s) | Interpretation / remaining gap |
|---|---|---|
| §1 Manifest | `Submission manifest`, `Verified snapshots` | Tracks actual known commits and preparation state; reviewer appointment, adoption and common-run decisions are pending. |
| §2 Requirement coverage and M03 | `M03 assessed links`, `M03 trace candidates`, `M03 verification ledger` | 13 shared IDs per track, candidate native evidence and explicit semantic-verification work. No unverified link is counted as verified M03a/b. |
| §3 Baseline acceptance | `M01 AC evidence`, `M01 calculated summary`, `Evaluator subcase register` | Operator reports are separated from evaluator subcase records. The latter correctly show no independent execution; operator Pass is not converted to evaluator Pass. |
| §4 Workflow / M01–M16 | `A Application quality`, `B Workflow quality`, `C Documentation`, `Metric readiness`, `Final dispositions`, `M11 rating register` | Objective observations and NR/Not calculable dispositions are separated. No overall weighted score. |
| §5 Evidence index and EC-01–21 | `M15 EC-01 to EC-21`, `Verified snapshots`, `Submission manifest` | Preliminary inventory; agreed applicability, collector-specific obligations, observation dates and comprehensive immutable evidence IDs remain incomplete. |
| §6 Events/effort/defects | `M08 candidate episodes`, `M04 defect screening`, `M04-M08 audit`, `Schema crosswalk` | Retrospective candidates; no invented person-minutes, duration, distinct defect count or rework total. |
| §7 Independent ratings | `M11 reviewer preparation`, `M11 rating register`, `Submission readiness` | Human initial J1–J7 scores not provided; M11 NR and M14 Not calculable. |
| §8 Change impact | Not applicable to Experiment 1 | M06, M07 and M07b are change-phase measures, not missing Baseline failures. |
| §9 CSV crosswalk | `Schema crosswalk` | Names the proposed record types and workbook equivalents; does not falsely claim that a complete canonical CSV export or complete keys already exist. |

**Record integrity note:** The `Evaluator subcase register` is a *planned common execution inventory*, not a fabricated set of 56 observations. It has 28 case/subcase rows per track and explicitly states that no independent execution took place. A later evaluator must agree which subcases are required and preserve any additional scenarios from the authoritative shared criteria. The `M03 verification ledger` similarly contains 13 IDs × two stages × two tracks as *assessment obligations*, not 52 confirmed native links. Existing candidate-link sheets are retained for provenance, but must not be interpreted as completed formal M03 assessments.

## E3. Reconciliation of reported outcomes and formal M01

Both operator evidence sets report all 19 parent Baseline criteria as passing. This is a legitimate **operator-reported** result, qualified by the snapshot and procedure issues discussed in §2 and Appendix D. However, the formal independent evaluator matrix remains **Not run** until the same adopted subcase plan is executed or adequately evidenced for each track. In particular, AC-01 requires a recorded offline procedure, AC-11 has three empty-input variants, AC-12 has six listed malformed strings, AC-13 has two zero divisors, AC-15 requires a genuine clean checkout, AC-18 requires two exact display cases, and AC-19 requires a documented Enter-key field. AC-14/16 require comparable ordered stateful scenarios. Therefore an independently verified 19/19, an independently measured full-execution count C, and a definitive independent M01 pass percentage are **not reported** here. The operator 19/19 claim remains visible with its correct provenance.

## E4. M03 and M15 — non-negotiable counting boundaries

For **M03a** and **M03b**, count only *distinct* frozen requirement IDs with at least one explicitly native link whose target is available and semantically verified, respectively at implementation and verification stages. An evaluator-reconstructed path contributes only to **M13**. **M03c** is the stage-specific fraction whose assessment is completed with available evidence judged Y/N or absence confirmed after a stated adequate search scope. Candidate links, `Unassessed`, `Unavailable`, and available-but-pending records cannot be counted as completed assessment. The Stage 1 report therefore does not give a final M03a/M03b/M03c percentage.

For **M15**, applicability must first be agreed for EC-01–21, including appropriate track/phase/collector boundaries and justified splits. The proposed denominator uses *Available + Confirmed absent + Unavailable* among applicable assessed obligations, with `Unassessed` shown separately. An empty template is not a missing submission. Operator and evaluator obligations must not be combined to make one operator appear less complete. No final M15 percentage is defensible from the current retrospective inventory.

## E5. M01–M16 disposition by phase

| Measure | Stage 1 defensible disposition |
|---|---|
| M01 | Operator-reported parent outcomes available (19/19 each); independent subcase-derived outcome/completion not established. |
| M02 | Optional supportive status view; not adopted and shared requirement-to-AC mapping incomplete. |
| M03a/b/c | Native candidates and per-requirement desk review available; semantic verification and completion pending; **no final ratio**. |
| M04 | Screened recorded corrections/defects; no complete distinct-product-defect register; **no verified zero**. |
| M05 | Late-discovery share not calculable without a defensible distinct defect set and discovery history. |
| M06 | Not applicable to Baseline-only analysis (requires paired change outcomes). |
| M07 / M07b | Not applicable to Baseline-only analysis (requires released CR and reference set). |
| M08 | Candidate operator episodes and prompt logs available; no audited final comparable intervention total. |
| M09 | Active person-minutes **Not calculable** from unmeasured intervals. |
| M10 | Comparable elapsed boundaries **Not calculable** without adopted start/end and reliable timestamps. |
| M11 | J1–J4 and J7 reviewer preparation exists; no independent ratings; **NR**. J5/J6 are change-only. |
| M12 | Rework candidates screened, but no final deduplicated, defect-linked episode total. |
| M13 | Reconstructed link evidence useful for interpretation; separate from native M03 and not fully quantified. |
| M14 | Independent paired numeric ratings missing; **Not calculable**. |
| M15 | Preliminary EC inventory exists; applicable obligation set not adopted; **Not calculable**. |
| M16 | Test counts and other contextual quantities reported with their scopes; **not quality scores**. |

## E6. Threats and mitigations explicitly tied to Amin's register

The accompanying `Validity threat crosswalk` sheet maps Stage 1 observations to Amin's `validity-threats.md`. Key risks are **VT-C01/C07** (ID mentions and inferred links wrongly counted as native traceability), **VT-C02/C03** (core tests or parent-level results overstating GUI/subcase coverage), **VT-C06/C09** (missingness and checklist applicability), **VT-I01/I02/I05/I07** (operator/model/environment confounds and post-hoc rubric selection), **VT-E01/E02** (one small calculator and two operators), **VT-Q01/Q03/Q04/Q05** (nonindependent criteria, missing time, selective metrics and snapshot drift), and **VT-R01/R02** (AI-assisted assessment and mutable references). The mitigation throughout is explicit provenance, versioned snapshots, separation of claims, and no causal or universal-winner inference.

## E7. Responsible next actions and submission status

**Completed for this deliverable:** retrospective collection and comparative synthesis from accessible Baseline records; 13-ID/19-AC inventory; three quality dimensions; source-specific limitations; proposed metric dispositions; provisional evidence and intervention/defect screens; evaluation-manifest and schema crosswalk; explicit unperformed evaluator obligations.

**Not claimed completed:** adoption of a protocol/rubric, independent AC execution, fully verified native traceability, human J1–J7 rating and agreement, measured historical time, final evidence completeness, or a formal Spec Kit freeze declaration. These are responsibilities of the study owner, operators and/or designated evaluators, not data the comparison author can invent. If the course requires formal independent results, the research team must perform and append those records before describing the experiment as formally closed.

**Submission conclusion:** This is a **submission-ready, clearly qualified retrospective Baseline comparison and evidence package** for the Comparison Frameworks and Metrics role. It is **not** a completed formal independent evaluation of both calculators. The difference must be retained in any cover note, presentation, or final study synthesis. Verify source URLs, team decisions and the institution's AI-use disclosure requirements before handing it in.


## F. GQM-aligned reassessment of M03, M04, M05 and M11 (2026-11-04)

This supplement uses the four updated comparison-framework files dated **2026-11-02** and the existing source-specific desk audit retained in Appendices D–E. It does **not** replace the earlier source audit or imply new independent executions. See the workbook's **M03 GQM reassessment** and **M03 M04 M05 M11 decision** sheets.

### F1. M03 – native traceability: evidence found, final numeric score still pending

Both operator-authored traceability tables contain explicit shared requirement IDs and references to native stories, tasks, tests or checks: BMAD `evidence/04-acceptance-results-and-traceability.md` §2 and Spec Kit `evidence/06-test-results.md` Traceability (T032). BMAD's PRD and Spec Kit's specification independently show native-to-shared ID mappings. The existing detailed audit in D3 and the workbook **M03 assessed links** records candidate implementation and verification artefacts for all **13/13 shared IDs in each track**. This is a positive finding about **located native evidence**, not a formally validated traceability percentage.

Under the updated draft-6 counting rules, M03a and M03b count *distinct shared IDs* with native-explicit, Available **and verified** implementation or verification links. M03c counts completed assessments, including scoped confirmed absence. The new ledger therefore records **Available (native trace table)** and **pending reviewer sign-off**, rather than incorrectly treating evidence as unavailable. An explicit ID in a traceability table is not automatically proof that its referenced target semantically implements or verifies the requirement. For NFR-02, BMAD expressly has no timed/automated responsiveness test; Spec Kit has an owner observation without numerical measurement. That distinction must survive link assessment.

**Disposition:** native link *discovery* and candidate extraction can be reported now; a full independent link-by-link native-origin/target audit and final reviewer Y/N decisions remain necessary before publishing M03a/M03b/M03c. `Unassessed` is no longer an appropriate shorthand for *evidence not located* across all rows. The earlier blanket uncertainty is narrowed, not replaced with invented 13/13 verified scores.

### F2. M04 – distinct product defects

The reviewed final test evidence reports **BMAD 172 display tests with no failures** and **Spec Kit 81 tests with no failures**. The earlier D4 screening additionally examined corrections, intentional test-first red runs, review false positives and environment retries. These are not automatically distinct application defects. No confirmed distinct product defect was established in the inspected records, **but the inspection is not a complete independently verified defect register**. A claim of M04 = 0 would require a defined exhaustive discovery scope, deduplication and provenance.

**Disposition:** descriptive screening is complete for the named sources; formal cross-track M04 distinct-defect totals remain **Not calculable**, not zero.

### F3. M05 – late-discovery share

M05 requires distinct defects and reliable first-discovery categories, especially whether a defect was first found in independent evaluation. Neither the final zero-failure native suites nor the process correction logs supply that numerator and denominator. It would be incorrect to infer 0% late discovery from an absence of confirmed final failures.

**Disposition:** **Not calculable** with currently recorded discovery provenance.

### F4. M11 – reviewer rubric judgements

The existing **M11 reviewer preparation** sheet already assembles source-specific evidence for J1 specification clarity, J2 native traceability, J3 requirements-informed verification, J4 NFR treatment and J7 evidence/reproducibility. Thus **preparatory qualitative assessment is possible now**; lack of formal ratings does not mean the underlying artefacts were unread. However, preliminary AI-assisted anchor discussions must not be converted into official human ratings. Independent initial reviewer identity, ratings and rationale are absent. For Baseline, J5 change-impact analysis and J6 change propagation are **N/A subject to confirmation against the adopted rubric**.

**Disposition:** J1–J4/J7 = **NR (formal independent ratings not performed)**, not 0; J5/J6 = **provisionally N/A**. M14 reviewer agreement is likewise not calculable.

### F5. Updated interpretation and responsibilities

The evidence now supports a stronger G1 statement: **both workflows preserved native shared-ID traceability tables and concrete candidate implementation/test chains**, while the final metric verification boundary remains open. For G3, passing native tests support operator-reported acceptance results but do not establish a zero-defect population. For G5, the existing reviewer preparation and evidence inventory enable later formal judgement, not a fabricated rating. The study team should confirm adoption of the common rubric/protocol and assign independent reviewer sign-off; operators may supply missing native provenance and defect discovery logs. The historical records must not be silently rewritten as independently observed data.


---

# SKILL.md-aligned evaluation procedure and governance addendum

**Date:** 2026-11-04  
**Procedure:** [`research/empirical-methodology/skills/evaluate-sdd-workflows/SKILL.md`](https://github.com/VSondur123/spec-driven-development-analysis/blob/main/research/empirical-methodology/skills/evaluate-sdd-workflows/SKILL.md), updated 2026-11-03; companion package **draft-5**.  
**Status:** Completed retrospective desk evidence comparison and preparation; **not** a newly authorised, independently executed formal evaluation. This addendum clarifies the procedural status of the preceding report and takes precedence if earlier narrative uses *pending* to refer ambiguously to desk review and formal sign-off.

## S1. Procedure selection and source authority

The skill requires evaluators to distinguish **Prepare mode** from **Evaluate mode**. Formal Evaluate mode requires both ready, frozen submissions, identified immutable snapshots, and an adopted common protocol/rubric. A completed implementation can contain failures, but a branch head, PR approval or draft companion document does not by itself establish adoption or phase readiness. On the evidence currently recorded, the necessary authorisation/adoption gates have not all been established. Accordingly, this deliverable is a **source-based retrospective desk review and evaluation preparation package**. No new build, GUI acceptance run, network-off observation, human reviewer rating or repository mutation is claimed.

The authority order is: the team's adopted shared protocol and rubric (if recorded); the frozen shared baseline inputs; the agreed metric definitions; and supporting skill/companion guidance. The skill's draft-5 companion files are preparation guidance rather than proof of team adoption. Thanh's metric catalogue defines M01–M16 and GQM; it does not override the shared rubric or adopt optional metrics. The skill also directs use of the protocol recommendations, rubric proposal, evaluation templates, evidence checklist, methodology review and validity-threat register.

## S2. Evaluation manifest and readiness decisions

| Manifest element | BMAD | GitHub Spec Kit | Interpretation |
|---|---|---|---|
| Phase | Baseline | Baseline | CR-01 excluded |
| Shared scope | 13 requirements; 19 parent ACs | Same frozen shared scope | Do not expand denominators with LOG clarifications |
| Identified source snapshot | `740c78de062bb1edd8076e4a49dde768b0fe1e49` (`baseline-frozen`) | `88f56d0979e2fae1fb5d59e8681975e3a1fc2a27` (branch tip) | BMAD tag resolved; Spec Kit final owner freeze not established |
| Historical result provenance | Acceptance references `ebac834`; offline check references `943c73f` | Interim clean clone at `419f92f` | Operator observations do not automatically describe the later identified heads |
| Operator/environment | Varun; macOS, Corretto 22, Maven 3.9.16 | George; Windows 11, Corretto 22, Maven 3.9.16 | Whole tool–agent–operator workflows compared |
| Common protocol/rubric adoption | Not confirmed | Not confirmed | Draft guidance is not an adoption decision |
| Independent acceptance run | Not performed | Not performed | Operator 19/19 Pass records remain separate from evaluator M01 |
| Independent J1–J7 rating | Not recorded | Not recorded | J1–J4/J7 `NR`; J5/J6 baseline change-only `N/A` |

The workbook's **Skill evaluation manifest** and **Skill gates and provenance** sheets retain these facts as separate reviewable fields. The original **Verified snapshots** sheet remains the detailed snapshot source.

## S3. Completed M03 desk review versus formal M03 scoring

**Desk review completed.** The existing 13 requirement-level source/test chains for both tracks have retrospective desk findings, and the workbook now explicitly marks the 52 track × requirement × stage records in **M03 GQM reassessment** as `Completed — retrospective desk review`. This records work actually done; it does not mean the evaluation has not started. The **M03 assessed links** sheet preserves requirement-specific observations and caveats.

**Formal M03a/M03b/M03c remains unreported.** A source/test path observed by the evaluator is not automatically an explicitly authored native traceability link. Formal numerators require the team's adopted rules for native origin, semantic stage verification, exact snapshot provenance and sign-off. The workbook therefore uses `Not independently signed off; no formal Y/N assigned` rather than an unsupported `Y` or a misleading zero. This distinction is especially relevant for NFR-02, for which neither track has a timed responsiveness test.

## S4. Evidence provenance and measurement rules

| Evidence category | Recorded outcome | Formal use |
|---|---|---|
| Operator acceptance reports | 19/19 parent ACs reported Pass per track, with documented AC-01/AC-15 qualifications | Descriptive operator result, not independently executed M01 |
| Desk-reviewed implementation and test links | Requirement-level mapping and source/test corroboration completed | Supports qualitative M03 finding; no formal native-link fraction yet |
| M04/M05 defect history | Screened existing records; no complete bounded distinct-product-defect register | Not calculable; missing register is not proof of zero defects |
| M08 intervention events | Retrospective episode candidates classified; unequal source logging | No adopted comparable episode count |
| M09/M10 effort and duration | No equivalent active-minute records or agreed phase boundaries | Not calculable; do not reconstruct estimates |
| M11 ratings | Evidence and anchor discussion exists; no independent initial human ratings | `NR` for J1–J4/J7; `N/A` for J5/J6 in Baseline |
| M14 reviewer agreement | No paired independent initial ratings | Not calculable |
| M15 evidence inventory | EC-01–EC-21 screened for both tracks; change-only items identified | Descriptive audit complete; formal percentage needs adopted applicability and collectors |

A test method, code inspection, operator screenshot and independently executed GUI test are different observation layers. GUI behaviour requires a recorded GUI observation; a headless pass or source inspection alone is insufficient. Reported `Pass`, evaluator-observed `Pass`, evidence `Available`, and rubric rating 0–3 must not be conflated. No composite or radar score is derived.

## S5. GQM-integrated findings and validity threats

| Goal / question focus | Quantitative status and denominator | Qualitative finding | Contradiction, gap or threat | Integration |
|---|---|---|---|---|
| G1 specification and traceability | 13 shared IDs per track; formal M03 fractions not reported | Both have substantial native planning-to-code/test candidates | Explicit-native origin and final snapshot sign-off unresolved; NFR-02 untimed | **Complementary, not formal corroboration** |
| G3 application outcomes | Operator M01 19/19 each; evaluator M01 not assessed | Both report expected arithmetic, validation and GUI behaviours | AC-15 clean-copy vs interim clone; no common GUI run | **Insufficient for comparative superiority** |
| G3 defects | M04/M05 not calculable | Screened test/review and correction records | No complete distinct-defect provenance | **Insufficient** |
| G4 human work | M08 candidate groups only; M09/M10 not calculable | BMAD approximate chronology versus Spec Kit detailed prompt log | Prompt and bounded intervention are different units; time unmeasured | **Descriptive only** |
| G5 evidence/reproducibility | 42 EC track-item records; M15 formal ratio not calculable | Both have extensive, differently structured evidence | Applicable checklist and adoption unresolved; snapshot mismatch | **Complementary evidence with limitations** |
| G5 reviewer quality | M11 `NR`; M14 not calculable | Reviewer-ready qualitative anchor discussion | AI output cannot stand in for independent human ratings | **Pending external review** |

These findings are threatened by **construct validity** (a trace candidate is not a verified native link; prompt count is not intervention count), **internal validity** (different operators, OS and model histories), **reliability** (historical evidence tied to different revisions), and **conclusion validity** (one implementation per workflow, no statistical superiority inference). The comparison unit remains the complete workflow, not a causal tool-only effect.

## S6. Handoff and next formal evaluation steps

The workbook retains all pre-existing sheets and adds **Skill evaluation manifest**, **Skill gates and provenance**, and **Skill findings crosswalk**. Its M11 human-reviewer columns now explicitly show `NR` rather than retrospective numerical scores, and M03 records completion of the desk review without inventing formal link verification.

To move from this completed desk study to a formal Evaluate-mode result, the team must record the effective adopted protocol/rubric, confirm both immutable Baseline submissions and align primary evidence with them, conduct a common evaluator acceptance procedure where required, apply agreed native-link sign-off rules, collect independent reviewer ratings, and settle applicable M15 collection obligations. None of these external decisions is represented as already completed here.

**Conclusion:** The previous three-aspect Experiment 1 comparison remains the main research analysis. The skill adds explicit procedural governance, provenance, readiness gates and GQM-to-validity traceability. The desk review is **complete within the stated retrospective evidence scope**; the formal independent evaluation is **not claimed complete**.


---

# Experiment 1 — Independent Baseline Evaluation Integration (2026-11-05)

This update is the operative M01 finding and supersedes older statements elsewhere in this report that *no independent evaluation has yet been performed*. Earlier operator evidence and desk-review records are preserved as prior checkpoints, not deleted.

## U1. Evidence register and observed conditions

| Record | Frozen version / date | Observation type | Primary record |
|---|---|---|---|
| BMAD — independent evaluation | `baseline-frozen` → `740c78de062bb1edd8076e4a49dde768b0fe1e49`; 2026-11-05 | Thanh's reported manual GUI observations, 19 Pass; no separate screenshots or raw native-test log attached | [BMAD 07 sheet](https://github.com/VSondur123/spec-driven-development-analysis/blob/main/bmad-calculator/evidence/07-evaluator-execution-sheet.md) |
| Spec Kit — independent evaluation | `88f56d0979e2fae1fb5d59e8681975e3a1fc2a27`; 2026-11-05 | Amin-directed Codex real-window mouse/keyboard automation, live state-readback and screenshots; 18 Pass, 1 Blocked | [Spec Kit 07 sheet](https://github.com/VSondur123/spec-driven-development-analysis/blob/main/github-spec-kit-calculator/evidence/07-evaluator-execution-sheet.md) |
| Spec Kit — verification artefacts | Same 88f56d0 snapshot; successful build and GUI run, 2026-11-05 | 35 timestamped GUI observations; Maven 81/81 passing on second attempt; initial environment-blocked build retained | [Run README](https://github.com/VSondur123/spec-driven-development-analysis/blob/main/research/empirical-methodology/evaluations/2026-11-05-spec-kit-baseline/README.md), [raw observations](https://github.com/VSondur123/spec-driven-development-analysis/blob/main/research/empirical-methodology/evaluations/2026-11-05-spec-kit-baseline/gui-observations.tsv), [build retry](https://github.com/VSondur123/spec-driven-development-analysis/blob/main/research/empirical-methodology/evaluations/2026-11-05-spec-kit-baseline/build-network-retry.txt) |
| Spec Kit — offline limitation | 2026-11-05 | Network disabling attempt inconclusive; no offline launch performed; AC-01 Blocked | [Network-state record](https://github.com/VSondur123/spec-driven-development-analysis/blob/main/research/empirical-methodology/evaluations/2026-11-05-spec-kit-baseline/offline-network-state.txt) |
| Governance and freeze | LOG-23 and LOG-26 | Evaluator confirmations recorded; designated Spec Kit snapshot not necessarily George-declared freeze; rubric/protocol revision draft-5 identified as adopted by Thanh, formal decision date absent | [Experiment log](https://github.com/VSondur123/spec-driven-development-analysis/blob/main/shared/experiment-log.md), [protocol](https://github.com/VSondur123/spec-driven-development-analysis/blob/main/shared/experiment-protocol.md), [rubric](https://github.com/VSondur123/spec-driven-development-analysis/blob/main/shared/evaluation-rubric.md) |

The evidence levels must not be conflated: an evaluator's written manual test record is valid reported observation evidence but is less readily reproducible than retained screenshots plus logs. GUI automation provides observable execution records but **does not constitute a second human reviewer rating**. All acceptance results are bound to specific snapshots, not later branch tips.

## U2. Individual parent AC comparison (D=19)

| Shared parent | BMAD independent | Spec Kit independent | Interpretation |
|---|---|---|---|
| `BASE-AC-01` | Pass (Thanh report) | Blocked (offline not executed) | Offline-state evidence differs: BMAD undocumented, Spec Kit not attempted |
| `BASE-AC-02` | Pass (Thanh report) | Pass (Codex-assisted GUI) | GUI outcomes concordant on performed case; source/evidence modalities differ |
| `BASE-AC-03` | Pass (Thanh report) | Pass (Codex-assisted GUI) | GUI outcomes concordant on performed case; source/evidence modalities differ |
| `BASE-AC-04` | Pass (Thanh report) | Pass (Codex-assisted GUI) | GUI outcomes concordant on performed case; source/evidence modalities differ |
| `BASE-AC-05` | Pass (Thanh report) | Pass (Codex-assisted GUI) | GUI outcomes concordant on performed case; source/evidence modalities differ |
| `BASE-AC-06` | Pass (Thanh report) | Pass (Codex-assisted GUI) | GUI outcomes concordant on performed case; source/evidence modalities differ |
| `BASE-AC-07` | Pass (Thanh report) | Pass (Codex-assisted GUI) | GUI outcomes concordant on performed case; source/evidence modalities differ |
| `BASE-AC-08` | Pass (Thanh report) | Pass (Codex-assisted GUI) | GUI outcomes concordant on performed case; source/evidence modalities differ |
| `BASE-AC-09` | Pass (Thanh report) | Pass (Codex-assisted GUI) | GUI outcomes concordant on performed case; source/evidence modalities differ |
| `BASE-AC-10` | Pass (Thanh report) | Pass (Codex-assisted GUI) | GUI outcomes concordant on performed case; source/evidence modalities differ |
| `BASE-AC-11` | Pass (Thanh report) | Pass (Codex-assisted GUI) | GUI outcomes concordant on performed case; source/evidence modalities differ |
| `BASE-AC-12` | Pass (Thanh report) | Pass (Codex-assisted GUI) | GUI outcomes concordant on performed case; source/evidence modalities differ |
| `BASE-AC-13` | Pass (Thanh report) | Pass (Codex-assisted GUI) | GUI outcomes concordant on performed case; source/evidence modalities differ |
| `BASE-AC-14` | Pass (Thanh report) | Pass (Codex-assisted GUI) | GUI outcomes concordant on performed case; source/evidence modalities differ |
| `BASE-AC-15` | Pass (Thanh report) | Pass (Codex-assisted GUI) | GUI outcomes concordant on performed case; source/evidence modalities differ |
| `BASE-AC-16` | Pass (Thanh report) | Pass (Codex-assisted GUI) | GUI outcomes concordant on performed case; source/evidence modalities differ |
| `BASE-AC-17` | Pass (Thanh report) | Pass (Codex-assisted GUI) | GUI outcomes concordant on performed case; source/evidence modalities differ |
| `BASE-AC-18` | Pass (Thanh report) | Pass (Codex-assisted GUI) | GUI outcomes concordant on performed case; source/evidence modalities differ |
| `BASE-AC-19` | Pass (Thanh report) | Pass (Codex-assisted GUI) | GUI outcomes concordant on performed case; source/evidence modalities differ |

*Subcases:* AC-11 (four empty-field configurations), AC-12 (nine malformed inputs), AC-13 (two zero divisors) and AC-18 (four precision cases) are explicitly preserved in the workbook. They do not increase the parent denominator. The Spec Kit screenshots and timestamped TSV cover these performed scenarios. BMAD subcase outcomes are based on Thanh's submitted checklist, not an inferred test run.

## U3. Recalculated M01

Let P/F/B/N count parent Pass/Fail/Blocked/Not run, and D = P+F+B+N = 19. `C` counts parents with every required subcase completed decisively. On the reported evaluations:

- **BMAD:** P=19, F=0, B=0, N=0; P/D=19/19 (100%); (P+F)/D=19/19 (100%); C/D=19/19 (100%) according to submitted outcomes. **Qualifier:** AC-01's offline-network condition was not explicitly evidenced; do not describe offline operation as independently corroborated.
- **Spec Kit:** P=18, F=0, B=1, N=0; P/D=18/19 (94.74%); (P+F)/D=18/19 (94.74%); C/D=18/19 (94.74%). BASE-AC-01 is Blocked, **not** Fail. All 18 executed parent checks passed.
- **Original operator results:** 19/19 Pass for each workflow, measured at the operator-report layer and prior snapshots; these remain historical observations and do not overwrite the independent results.

The M01 pass-fraction difference is 1/19 ≈ 5.26 percentage points, explained by the **unexecuted offline scenario in Spec Kit**, not an observed wrong answer, crash or failed calculator functionality. Because BMAD's network-off evidence is missing and the execution approaches differ, the data do not justify a ranked software-quality claim or a causal advantage of BMAD over Spec Kit.

## U4. Three comparison aspects after the evaluation

**A. Application Quality:** Both completed sets of ordinary GUI arithmetic, validation, reset, precision and error cases returned expected results. Spec Kit's 18 performed checks have reproducible real-window automation records; BMAD has evaluator-reported Pass outcomes. The online behaviour coverage overlaps; offline launch remains a specific evidence gap for both tracks in different ways. No confirmed functional defect was discovered in either independent evaluation record. An environmental Blocked check is not a product failure.

**B. Development Workflow Quality:** The original distinctions (BMAD PRD, Architecture Spine, three epics and nine stories; Spec Kit constitution, spec, plan, contracts and task breakdown) remain descriptive. The new evaluator runs do not measure operator active minutes, intervention episodes or development rework, so **M08/M09/M10/M12 cannot be filled using the 19 AC results alone**. Native 81/81 Spec Kit tests show that the observed checkout built and passed its suite, but test counts are not cross-track productivity metrics.

**C. Development Results and Documentation:** The Spec Kit evaluation strengthens reproducibility evidence with frozen SHA, raw environment, successful and unsuccessful build attempts, 35 time-stamped GUI observations, screenshots and a method note for invalid input-delivery attempts. BMAD's 07 sheet preserves independent manual results, exact frozen SHA, date, reported commands and some observed error messages, but no attached raw GUI logs/screen captures; AC-01 network status and AC-19 operand field remain gaps. The M03 desk-review conclusions, missing NFR-to-AC associations and qualitative documentation comparison are unchanged; M15 can inventory these new artefacts but its percentage still needs the agreed applicable checklist and formal assessment.

## U5. Metrics M01–M16: what actually changed

| Metric | 2026-11-05 consequence | Status / boundary |
|---|---|---|
| **M01** | Calculate independent evaluator parent pass fractions for both tracks (19/19 vs 18/19; Spec Blocked=1), maintain subcases and provenance | **Computed with explicit limitations** |
| M02 | Acceptance outcomes permit an update to requirements with associated ACs, but four NFRs still have no linked AC | **Partial; mapping/definitions pending** |
| M03a/b/c | New manual verification pointers do not retroactively establish all native explicit implementation/test links | **Desk-reviewed; formal sign-off and denominators unresolved** |
| M04 | Neither independent sheet reports a product defect; environmental Blocked is not a defect | **Zero defects observed in evaluator-run scope only; broader distinct defect register incomplete** |
| M05 | Cannot compute late defect discovery share from no bounded discovery history | **Not calculable** |
| M06/M07/M07b | Compare changed app and predicted impact during CR-01, outside this Baseline | **Not applicable in Experiment 1** |
| M08/M09/M10/M12 | No new comparable intervention log, active effort measurement, matched phase boundary or normalised rework register | **Not calculable or preliminary** |
| M11 | No independent 0–3 J1–J7 human ratings obtained by either evaluated calculator run | **NR / phase-specific N/A** |
| M13 | Reconstructed link candidates remain separate from native M03 | **Descriptive only** |
| M14 | No pair of initial independent human ratings | **Not calculable** |
| M15 | Add two completed execution sheets, Spec GUI capture package and Maven logs to evidence inventory | **Evidence expanded; formal completeness percentage still conditional** |
| M16 | Record 81 native tests passing for the Spec Kit frozen independent build; do not compare totals as intrinsic quality | **Descriptive context only** |

## U6. Limitations and decisions for final review

1. **Different observation modes:** Thanh manually tested and documented results; Amin directed Codex to execute OS-driven GUI checks. There is no evidence of Amin personally executing each case or two independent human reviewers grading them.
2. **Offline check:** BMAD AC-01 is a reported Pass with missing explicit offline network-state observation. Spec Kit AC-01 is Blocked because no offline launch occurred. Neither status establishes a comparative difference in intrinsic offline capability.
3. **Snapshot governance:** BMAD's immutable baseline tag is `740c78de`; Spec Kit's `88f56d0` was study-owner-designated in LOG-26 pending George's own formal freeze confirmation. Historical operator results referred to different working trees/commits.
4. **Environment / automation:** Windows vs macOS, JDK vendors, Maven versions, model-assisted evaluation and initial GUI focus/build network restrictions are potential threats to comparability and repeatability.
5. **Protocol/rubric:** Thanh reports draft-5 was officially adopted; the repository's shared document headers still describe proposal status and an adoption decision date/reference is not present in the checked material. Do not retroactively infer when adoption became effective.
6. **Other metrics:** AC execution cannot replace the missing efforts, event episode classifications, reviewer ratings, complete defect histories, or applicability decisions.

**Conclusion, updated 2026-11-05.** Both workflows' calculators performed correctly on the acceptance cases executed and recorded in the independent Baseline evaluations. The recorded M01 outcomes are **BMAD 19/19 Pass with documented evidence qualification**, and **Spec Kit 18/19 Pass with one environment/procedure Blocked**; no functional Fail was reported. The outcome does not justify a product-quality ranking. Experiment 1 is substantially strengthened by adding independent evidence, but its complete multi-metric scientific assessment remains unfinished until the specific outstanding measures are collected or transparently designated unavailable.
