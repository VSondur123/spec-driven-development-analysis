# Plan — Requirements Engineering Final Report (COMP.SE.130)

## Document
- **Type:** ieee-report (IEEEtran conference format, two columns, numeric IEEE citations)
- **Working title:** Comparing BMAD and GitHub Spec Kit for Spec-Driven Development: A Requirements Engineering Case Study on a Java Swing Calculator
- **Course / audience:** COMP.SE.130-2026-2027 Requirements Engineering, Tampere University; course staff and peer reviewers.
- **Authors (four), confirmed by the owner:** Varun Ganesh Sondur, Mohammadamin Lotfiourimi, Pham Thanh, Georgios Malezoglou. Affiliation: Tampere University. Names as in `ReadMe.md`; no other details are added.
- **Objective:** a descriptive comparative case study of how two complete tool–agent–operator workflows support the transition from requirements to implementation and the handling of one change request, and how well the result matches the requirements.
- **Scope:** baseline four-operation calculator; CR-01 (square, square root, reciprocal). Out of scope: other scientific functions, graphing, history, memory, persistence, networking; any "overall winner" claim.
- **Research questions:** OPEN (Q2). Recommended default: the `ReadMe.md` set RQ1–RQ4 (specification structure; requirement→design/task/code/test traceability; change handling and alignment; match to original requirements), with the results from `research/final-analysis/` mapped onto them. Not assumed until the owner decides.
- **Constraints:** at most 10 pages main text plus at most 2 pages references (config `limits.max_pages = 12`, checked after the PDF build); formal academic English; critical analysis; evidence-backed conclusions only; required AI-use declaration (drafted from recorded tools, to be checked by the owner against the course wording); no per-member hours, contribution statements or timeline section (owner decision); no appendix (owner decision); no invented references or results; `project-context.md` tags ([verified]/[reported]/[draft]/[planned]/[TODO]) respected, and any statement that depends on a [reported] item says so in the text.
- **Citation style:** numeric IEEE. `final-submission/references.bib` has no entries. Each source is verified before it is entered. Whether web search/fetch may be used for that is OPEN (Q6). Tool documentation and other grey literature are marked separately from academic sources (`ReadMe.md`).
- **Write scope:** only inside `manuscript/final-report/`. No commits. Nothing outside this folder is edited, moved or created.

## Approved structure
- Introduction
- Background
  - Spec-Driven Development
  - Requirements Engineering Concepts Used
  - Related Work
- Method
  - Research Questions
  - Case and Change Request
  - Experimental Procedure
  - Evaluation Approach and Measures
- Tool Descriptions
  - BMAD
  - GitHub Spec Kit
  - Capability Comparison
- Results
  - Specification Structure and Traceability
  - Baseline Implementation
  - Change Request CR-01
  - Process Observations
- Discussion
  - Answers to the Research Questions
  - Implications
  - Threats to Validity
- Conclusion
- AI-Use Declaration
- References

## Section notes
The Abstract is a front-matter element (required by the doctype) and is not listed above. Page allocation totals 10.0 pages of main text (the first plan listed 10.0 but its rows summed to 10.5; corrected here after removing the work-division section).

| Section | Purpose | Main evidence | Pages |
|---|---|---|---|
| Abstract | Context, method, main findings, limits (about 150 words) | all sections | – |
| Introduction | Problem, why RE perspective, RQs preview, contribution, paper outline | `ReadMe.md`; `project-context.md` §A | 0.75 |
| Background | SDD and the spec-first / spec-anchored / spec-as-source spectrum; traceability, change-impact analysis, acceptance criteria, non-functional requirements; vibe coding as contrast (not a condition); related work | `research/comparison-frameworks/literature-review.md`, `references.md`; `research/empirical-methodology/references.md`; mid-term plan. Citations only after verification | 1.25 |
| Method | RQs; shared case (8 FR + 5 NFR, 19 BASE-AC) and CR-01 (11 CR-FR, 22 CR-AC); phases and fairness rules; freeze snapshots; independent evaluators; measure definitions and rules (Pass/Fail/Blocked/Not run; Blocked is not Fail; test counts are context only) | `shared/*`; `project.md`; `shared/experiment-protocol.md`, `shared/evaluation-rubric.md`; `research/comparison-frameworks/metric-catalogue.md` | 1.25 |
| Tool Descriptions | What each tool is, its native workflow and artefacts as installed (BMAD 6.12.4, Spec Kit specify-cli 1.2.3), what it was used for here, and a capability comparison on the `ReadMe.md` criteria | `bmad-calculator/evidence/00-STATUS.md`, `01-environment.md`; `github-spec-kit-calculator/evidence/00-STATUS.md`, `01-environment.md`; installed skill directories; `.specify/` | 1.5 |
| Results | Observed outcomes by phase, with provenance layer shown for every number | `research/final-analysis/results.md`; Experiment 1 and 2 reports and workbooks; both tracks' acceptance, traceability and impact-analysis files; `shared/experiment-log.md` | 3.0 |
| Discussion | Answer RQ1–RQ4 critically; practical implications; threats to validity (confounding, single run, evidence asymmetry, operator = owner, shared-input gaps) | `research/final-analysis/final-report.md`; `research/empirical-methodology/validity-threats.md`; `project-context.md` §J | 1.5 |
| Conclusion | Qualified, dimension-specific conclusions and future work | as above | 0.5 |
| AI-Use Declaration | Which AI tools were used for the experiment, the research and the report, for what, and how output was checked. Drafted from recorded tools only; the owner checks it against the course wording | `project-context.md` §K (Claude Code with `claude-sonnet-5-5` on both tracks, BMAD 6.12.4 and Spec Kit 1.2.3 skills, AI-assisted source discovery, `evaluate-sdd-workflows` skill, Codex assistance in the Spec Kit baseline evaluation per LOG-23, this report workflow). Statements about human checking are limited to what the repo records | 0.25 |
| References | Verified IEEE entries only | `references.bib` after verification | up to 2 |

## Proposed figures and tables
Each item states its data source. Nothing is added to the draft without a traceable source. Edit this list freely; it is capped to fit the page budget. Charts are built from CSV files that I would create under `manuscript/final-report/data/`, because the workbooks are XLSX and the figure tool does not read XLSX. Every CSV row would be transcribed from a cited document, with a provenance column.

| ID | Section | Type | Content | Data source |
|---|---|---|---|---|
| Fig. 1 | Method | Workflow diagram (blocks, two lanes) | Study design: shared baseline requirements → freeze → independent baseline build per track → freeze tags → CR-01 → impact analysis → implementation → independent evaluation (baseline, CR-01, regression) | `project.md` §6; `shared/experiment-log.md` (LOG-22 to LOG-27); git tags `baseline-frozen`, `speckit-baseline-frozen`, `bmad-cr01-frozen`, `speckit-cr01-frozen` |
| Fig. 2 | Tool Descriptions | Block diagram | What BMAD is: phases and the agents/skills used, artefacts produced, human gates, change path (`bmad-correct-course`) | BMAD `00-STATUS.md`, `01-environment.md`; `_bmad-output/`; `.agents/skills/` names (`bmad-product-brief`, `bmad-prd`, `bmad-architecture`, `bmad-create-epics-and-stories`, `bmad-sprint-planning`, `bmad-build`, `bmad-code-review`, `bmad-correct-course`) |
| Fig. 3 | Tool Descriptions | Block diagram | What GitHub Spec Kit is: constitution → specify → clarify → plan → tasks → analyze → implement, with the artefact each command writes and the human gates | Spec Kit `00-STATUS.md`, `02-approvals.md`; `.specify/`; `specs/001-baseline-calculator/`; `.claude/skills/speckit-*` |
| Tab. 1 | Method | Table | Shared case: counts of requirements and acceptance criteria per phase; error priority and message rules in one line | `shared/baseline-requirements.md`, `shared/acceptance-criteria.md`, `shared/change-request-CR-01.md` |
| Tab. 2 | Method | Table | Measures used (M01, M03, M06, M07, M08, M12, M16) with what each means and its aggregation rule; the rest listed as not calculable | `research/comparison-frameworks/metric-catalogue.md`; `results.md` §5 |
| Tab. 3 | Tool Descriptions | Table | Environment and versions per track (OS, JDK, Maven, JUnit, tool version, model) | both tracks' `evidence/01-environment.md` |
| Tab. 4 | Tool Descriptions | Capability matrix | BMAD vs Spec Kit on the nine `ReadMe.md` criteria (specification structure, traceability, ambiguity handling, acceptance criteria, test generation, NFRs, change handling, human control) | native artefacts of both tracks; analyze findings; `bmad-correct-course` outputs |
| Fig. 4 | Results | Bar chart (CSV) | Parent acceptance outcomes per track by phase: baseline independent (Pass/Blocked), CR-01 (Pass), baseline regression (Pass). Operator-reported and independent layers shown separately | new `data/acceptance-outcomes.csv` from `results.md` §2.1, §3.1; LOG-23, LOG-27 |
| Tab. 5 | Results | Table | Baseline: operator-reported vs independent outcome, offline qualification | `results.md` §2.1 |
| Tab. 6 | Results | Table | CR-01: acceptance, regression, impact, defects and corrections by dimension | `results.md` §3.1, §3.2 |
| Tab. 7 | Results | Table | Native test counts, baseline → CR-01 (context only, no quality inference) | `results.md` §4.2; `data/native-tests.csv` |
| Tab. 8 | Results / Discussion | Table | Metric disposition: calculable vs not calculable, with the reason | `results.md` §5 (condensed) |
| Tab. 9 | Discussion | Table | Strengths and constraints per workflow, with evidence boundary | `final-report.md` §7 |

That is 4 figures and 9 tables, which is probably too many for 10 pages in two columns. Open item Q4 below states the recommended cut.
A traceability or requirements-coverage chart (`ReadMe.md` coverage matrix) is not proposed because no verified semantic traceability percentage exists (M03/M13 unscored). It is a described gap instead.

## Citation and evidence rules for drafting
- A number is stated with its layer: operator-reported, independent evaluator, or native automated test.
- Missing measures are written as "not calculable", never as zero or a favourable default.
- `[reported]` items in `project-context.md` are marked as reported in the text.
- Anything with no source is marked `[[EVIDENCE NEEDED: ...]]` and listed to you.

## Open items (owner decisions pending; nothing below is assumed)
- **Q2 Research-question set.** Three sets exist (`ReadMe.md` RQ1–4; `project.md` §3 five working questions; `research/final-analysis` RQ1–5 "which performed better"). Recommended default: `ReadMe.md` RQ1–4, with `final-analysis` results mapped onto them.
- **Q3 Framing of conclusions.** Recommended default: lead with the qualified findings of `research/final-analysis` (both workflows report success on the common checks; differences in evidence depth and process; no overall winner or efficiency claim), because effort, impact recall, rework and rubric ratings were not measured.
- **Q4 Figure/table cut.** Recommended default: keep the 4 figures; merge Tab. 5 and Tab. 6 into Fig. 4 plus one table; turn Tab. 7 into a sentence. That leaves 4 figures and 6 tables.
- **Q6 Reference verification.** Recommended default: allow web search/fetch to confirm only the sources already listed in the team registers (L1–L14, M1–M5), and enter only those I actually read, with the arXiv/preprint status shown. If not allowed, the reference list contains only sources the owner confirms, and the Background is shortened accordingly.

## Resolved by the owner
- Q1: authors are the four full names above, affiliation Tampere University.
- Q5: no appendix.
- Q7, Q8: no per-member hours, contribution statements, timeline or protocol-adoption-date item. The "work division and timelines" mark criterion (1 point, from `project-context.md` §L) is therefore not addressed by a section; this follows the owner's decision.
- Q9: the AI-use declaration is drafted from recorded tools.
