# Literature Review — Comparison Frameworks, Metrics and Presentation

**Author:** Pham Thanh (Thanh)  
**Date:** 2026-11-02  
**Revision:** draft-5 (GQM integration and cross-document alignment)  
**Status:** Targeted literature review for team verification. This is **not** a systematic literature review. It supports `metric-catalogue.md`; it sets no evaluation rules and does not replace Amin's rubric or protocol. No track has been scored.

## 1. Scope and method

**Question.** Which existing frameworks, measures and findings can inform a comparison of two spec-driven development (SDD) workflows used to evolve the same Java desktop GUI calculator, for the five working research questions in `project.md` §3?

**Work completed (2026-10-25 to 2026-10-30).** General web searches surfaced arXiv records, publisher and proceedings pages, institutional records and project documentation. The reading scope is stated for each source in `references.md`:

- **Opened:** Piskala (abstract and opening sections); EPIC (full text); Agarwal et al. (partial text); Hill (proceedings abstract, metadata and reference list); Musco et al. (excerpt on evaluation metrics).
- **Record:** bibliographic record and/or abstract confirmed, paper not read in full.
- **Ref-only:** citation seen only in another paper's reference list.
- **Unverified:** carried over from an earlier list and not checked in this pass.

An abstract-only or excerpt-only reading supports only claims visible in that material. Do not imply that the full paper was reviewed.

**Completeness.** This review does not claim systematic coverage. Scopus and Google Scholar were not searched directly; there was no systematic screening and no structured snowballing beyond the named reference lists. Section 7 is a proposed search plan, not evidence that the searches have been completed.

**Relationship to Amin's work.** Amin's empirical-methodology sources M1–M5 remain in `research/empirical-methodology/references.md` and are not duplicated here. This review focuses on what to measure and how related research evaluates it.

## 2. GQM as the measurement structure

The project uses the Goal–Question–Metric (GQM) approach to organise the relationship between research aims, evaluation questions and measures. The working GQM structure is documented in `metric-catalogue.md` §1.

The overall study is a comparative case study using a mixed-methods evaluation framework. GQM is the measurement-organising approach within that design; it is not a replacement for the case-study design or the qualitative/quantitative distinction.

The GQM structure is used to:
- define what the comparison is intended to evaluate;
- derive answerable questions from each evaluation goal;
- select measures that provide evidence for those questions;
- make the connection between evidence and conclusions explicit;
- identify questions that cannot be answered because evidence is missing or shared inputs are incomplete.

The five proposed goals are:

| Goal | Focus | Related working RQ |
|---|---|---|
| G1 | Specification and traceability | RQ1 |
| G2 | Change-impact handling | RQ2 |
| G3 | Implementation and verification outcomes | RQ3 |
| G4 | Human work and effort | RQ4 |
| G5 | Evidence and reproducibility | RQ5 |

This mapping is based on the current working research-question structure in `project.md` §3. The team must confirm the exact wording and numbering before the final report.

**Source limitation:** L5, Basili, Caldiera and Rombach's “The Goal Question Metric Approach,” is currently a bibliographic record only in this review. It is the intended foundational reference for GQM, but the exact bibliographic details and source text must be verified before making detailed claims about the original method or citing it as fully reviewed. The project's GQM mapping is a proposed application to this study, not a claim that the original source validates the project's metrics.

## 3. Relevant sources

### 3.1 SDD context

**L1. Piskala (2026), _Spec-Driven Development: From Code to Contract in the Age of AI Coding Assistants_, arXiv:2602.00180.** Opened: abstract and opening sections. Preprint.
- The paper describes levels of specification rigour: spec-first, spec-anchored and spec-as-source.
- It is useful vocabulary for describing the two workflows, not an evaluation instrument.
- It does not provide a validated comparison metric for BMAD and Spec Kit.
- **Use in this project:** descriptive terminology in the background/tool descriptions only. Do not rank the tools from these categories.

**L2. Claudino et al. (2026), _The EPIC Framework for Spec-Driven Development_, arXiv:2610.07534.** Opened: full text. Preprint; peer-review status not established.
- EPIC presents 40 practices across 10 dimensions, including seven requirement-quality characteristics retained from a standard: unambiguous, singular, verifiable, complete, consistent, conforming and comprehensible.
- It treats the set of specification, plan and task artefacts as a quality unit and reports a 0–3 assessment approach with researcher calibration and agreement results.
- The authors state that reliability of their agent skill's scoring was not measured.
- **Limitations:** repository/practitioner evidence, not a controlled comparison of development workflows; its rubric is not our rubric.
- **Use:** optional evidence prompts for human reviewers considering J1/J3; supports examining related artefacts together; supports labelling automated ratings provisional. It does not change Amin's rating anchors.

**L3. Hill (2026), _Specification Artifacts in Open-Source Pull Request Workflows Do Not Reduce Defects_, ESEM 2026, LIPIcs 394, art. 84.** Opened: abstract, metadata and reference list; full PDF not read.
- The abstract reports a study of 88,052 pull requests in 119 repositories and says the five hypotheses derived from SDD claims were not supported. Specification artefacts appeared to track task complexity rather than quality.
- The study measures defects and rework at repository scale; the sample does not test adoption of SDD tools as such.
- **Limitations:** abstract-level reading for this review; specification-like artefacts are not necessarily tool-generated SDD outputs.
- **Use:** caution against claiming that specifications automatically improve quality. Its large-scale rework method should not be transferred uncritically to two small project tracks.

**L4. Agarwal et al. (2026), _Spec-Driven Development as Prescribed and as Practiced_, arXiv:2608.25202.** Opened: partial text from the version fetched on 2026-10-25. Preprint. Bibliographic identity/version must be confirmed; see `references.md` L4.
- The partially read version discusses implementation drift, spec decay and verification debt, drawing on practitioner and repository evidence.
- **Limitations:** partial reading; mixed qualitative/repository study; not a controlled comparison of BMAD and Spec Kit; metadata/version discrepancy remains to be resolved.
- **Use:** background for examining whether implementation and verification artefacts remain aligned after a change. It supplies no project metric and does not establish causality.

### 3.2 Measurement design and GQM

**L5. Basili, Caldiera and Rombach (1994), _The Goal Question Metric Approach_.** Record only.
- The work is the intended foundational reference for the Goal–Question–Metric measurement approach.
- The project's proposed GQM structure starts from evaluation goals, derives questions from those goals and maps measures to the questions.
- **Limitations:** only a bibliographic record was checked for this review. Exact pages, edition and bibliographic details require confirmation. Do not claim the original text has been read or that it validates the specific metrics in this project.
- **Use:** cite only after bibliographic verification and review of the relevant source text. The current G1–G5 mapping is a project-specific proposal.

### 3.3 Traceability and change impact

**L6. Cleland-Huang et al. (2014), _Software traceability: Trends and future directions_.** Record/abstract only.
- The source discusses the value and practical difficulty of traceability, including ad hoc and retrospective links.
- **Use:** rationale for keeping links created during work separate from links reconstructed by evaluators. It does not validate our counting formula.

**L7. Li et al. (2013), _A survey of code-based change impact analysis techniques_.** Record/abstract only.
- Surveys code-based impact analysis techniques.
- **Use:** background terminology only. Code-based prediction is not the same as a person or agent listing requirement-level impacts before implementation.

**L8. Gentili, Çarka and Falessi (2024), _A Systematic Mapping Study on Impact Analysis_.** Record/abstract only.
- The abstract reports that requirements are among the least mentioned target artefacts in the mapped impact-analysis literature.
- **Use:** explains why this project may need a reviewer-built reference set. Do not claim the full mapping study was read.

**L14. Musco, Monperrus and Preux (2017), _A large-scale study of call graph-based impact prediction using mutation testing_.** Opened: excerpt on evaluation metrics; full paper not read.
- The excerpt uses precision and recall to evaluate impact prediction.
- **Limitations:** code-level call-graph prediction, not requirement-level impact analysis by an agent or operator.
- **Use:** supports precision/recall as a general evaluation device only. The project's reference set, granularity and proposed `Extra` category are project-specific procedures.

### 3.4 Requirements quality and testing

**L9. Femmer et al. (2017), _Rapid quality assurance with requirements smells_.** Record/abstract.
- Describes detection of requirements text smells and reports limitations in distinguishing some smells.
- **Use:** possible optional check of shared requirement wording; not a core workflow outcome.

**L10. ISO/IEC/IEEE 29148:2018, _Systems and software engineering — Life cycle processes — Requirements engineering_.** Ref-only; standard not opened.
- EPIC uses requirement-quality characteristics associated with the standard.
- **Use:** do not cite this standard as personally reviewed until a team member opens the relevant clauses and confirms the citation.

**L11. Inozemtseva and Holmes (2014), _Coverage is not strongly correlated with test suite effectiveness_.** Record/abstract and authors' summaries.
- Reports that coverage is not a strong substitute for test-suite effectiveness.
- **Use:** supports keeping test counts and coverage as descriptive context rather than treating them as quality scores. The complete paper has not been read for this review.

**L12. Frattini et al. (2023), _Requirements quality research: a harmonized theory, evaluation, and roadmap_.** Ref-only.
- Seen through EPIC's reference list, not independently read.
- **Use:** no substantive claim should rely on this source until it is opened and checked.

### 3.5 AI non-determinism

**L13. Ouyang et al. (2025), _An Empirical Study of the Non-Determinism of ChatGPT in Code Generation_.** Record/abstract and Crossref metadata.
- The abstract reports non-determinism in ChatGPT code generation and notes that temperature zero does not guarantee deterministic output.
- **Limitations:** benchmark code generation, not agentic SDD workflows or this calculator.
- **Use:** caution to record model/tool settings and preserve primary runs and reruns separately. A single run per workflow cannot estimate run-to-run variance.

### 3.6 Grey literature

GitHub Spec Kit documentation, BMAD documentation, Böckeler's practitioner comparison and Eberhardt's practitioner critique have not been opened in this pass. Treat vendor and practitioner statements as claims, not independent empirical findings. Verify current versions before describing exact tool behaviour.

## 4. Synthesis: support, limits and GQM goals

| GQM goal / area | What the reviewed material supports | What it does not establish | Project-specific proposal |
|---|---|---|---|
| G1 — Specification and traceability | Artefact-set view; requirement-quality vocabulary; an example 0–3 rubric approach (L2; L10 not opened); traceability challenges (L6) | Validity of this project's anchors or a causal effect on outcomes | Use Amin's J1–J7 anchors; keep native links separate from reconstructed links |
| G2 — Change-impact handling | Precision/recall as evaluation concepts (L14); requirement-level impact is underrepresented in the mapped literature (L8) | A ready-made requirement-level benchmark | Build and freeze a project reference set before matching |
| G3 — Implementation and verification outcomes | Repository-scale defect/rework research (L3); coverage is not equivalent to effectiveness (L11) | That test count or coverage alone measures quality, or that SDD automatically reduces defects | Use shared AC outcomes and distinct defects as primary evidence; report counts as context |
| G4 — Human work and effort | No direct SDD norm established by the sources reviewed here | Expected intervention or effort levels | Record interventions and measured time consistently for both operators |
| G5 — Evidence and reproducibility | The reviewed sources provide some measurement and evaluation examples; Amin's M1–M5 provide additional empirical-methodology context | A validated evidence-completeness ratio for this project | Treat M15 as a project proposal pending agreement; preserve snapshots and provenance |
| GQM measurement structure | GQM is the intended goal-to-question-to-measure structure (L5 record only) | Validation of this project's goals, questions, metric catalogue or thresholds | Use GQM to keep each measure connected to a project question; verify L5 before final citation |
| Non-determinism | Code-generation non-determinism is reported in L13 | Variance of the two workflows in this project | Preserve primary runs, settings and reruns; do not infer variance from one run |

**Overall interpretation.** The literature provides concepts and measurement precedents, not validation of this project's complete measurement system. The GQM mapping, measures, operational definitions, thresholds, data structures and visualisations remain project-specific proposals unless the team adopts them.

## 5. Implications for the comparison

The literature suggests several cautions for interpreting the two workflows:

1. **Specification presence is not proof of specification quality.** The existence or volume of specification artefacts should not be treated as a quality result by itself.
2. **Traceability origin matters.** Links created by a workflow and links reconstructed later by evaluators answer different questions and should be reported separately.
3. **Impact analysis needs an explicit reference set.** Precision and recall are meaningful only when the reference elements and their granularity are agreed and frozen before matching.
4. **Testing needs shared outcomes.** Native test counts and coverage can describe the workflow, but shared acceptance criteria provide a more comparable evaluation basis.
5. **Human effort needs direct measurement.** Intervention counts, active minutes and elapsed duration are different quantities; one should not be used as a proxy for another.
6. **Reproducibility depends on preserved evidence.** Snapshots, environment settings, original reports and evaluator observations should remain identifiable.
7. **Causal claims must remain limited.** Two workflows with different operators, agents, operating systems and configurations do not isolate the effect of the tool alone.

These points inform the proposed G1–G5 structure and the metric definitions in `metric-catalogue.md`. They do not independently establish that the proposed measures are valid or reliable.

## 6. Limitations

- This is a small targeted review, not a systematic review.
- Scopus and Google Scholar searches have not been completed.
- Several sources are abstracts, records, reference-list entries or partial excerpts.
- Recent SDD work may change quickly; preprint status and bibliographic metadata require checking.
- No reviewed source validates the exact measures proposed for this calculator comparison.
- The GQM source L5 has not yet been read in full or bibliographically verified.
- Human intervention and effort in agentic SDD workflows remain an identified search gap.
- Tool documentation and practitioner comparisons have not been independently checked in this review.

## 7. Carried-over sources not yet verified

The following were carried over from an earlier plan and are not evidence in this review: arXiv:2609.05667, arXiv:2608.30572 and arXiv:2605.18461. Open each record and the paper, confirm title/authors/version and relevance, then retain or remove it. Do not cite these as read.

## 8. Search plan (not yet completed)

Run the searches and record date, database, exact query, number of results, screening decision and reason for inclusion/exclusion.

| Database | Proposed query | Purpose |
|---|---|---|
| Scopus | `TITLE-ABS-KEY("spec-driven development" OR "specification-driven development") AND TITLE-ABS-KEY(AI OR LLM OR agent)` | SDD studies and tool comparisons |
| Scopus | `TITLE-ABS-KEY("requirements traceability") AND TITLE-ABS-KEY(LLM OR generative OR "AI-assisted")` | AI-assisted traceability |
| Scopus | `TITLE-ABS-KEY("change impact analysis") AND TITLE-ABS-KEY(requirement* AND (LLM OR generative))` | Requirement-level impact analysis |
| Google Scholar | `"spec-driven development" empirical OR "case study" OR comparison` | Early empirical work |
| Google Scholar | `"requirements quality" "coding agents"` | Specification quality for agents |
| Google Scholar | `"Goal Question Metric" software measurement` | GQM method and original bibliographic details |
| arXiv (cs.SE) | `spec-driven development`; `agentic software engineering specification quality` | Preprints |
| Any | `("human intervention" OR "human-in-the-loop") AND "coding agent" AND (effort OR rework)` | Human-work measurement gap |

Record the exact syntax accepted by each database; the table is a search plan, not a completed search log.