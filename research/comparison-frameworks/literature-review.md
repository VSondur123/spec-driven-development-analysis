# Literature Review — Comparison Frameworks, Metrics and Presentation

**Author:** Pham Thanh (Thanh)  
**Date:** 2026-10-30  
**Revision:** draft-4 (consistency revision)  
**Status:** Targeted literature review for team verification. This is **not** a systematic literature review. It supports `metric-catalogue.md`; it sets no evaluation rules and does not replace Amin's rubric or protocol. No track has been scored.

## 1. Scope and method

**Question.** Which existing frameworks, measures and findings can inform a comparison of two spec-driven development (SDD) workflows used to evolve the same Java desktop GUI calculator, for RQ1–RQ5 in `project.md` §3?

**Work completed (2026-10-25 to 2026-10-30).** General web searches surfaced arXiv records, publisher and proceedings pages, institutional records and project documentation. The reading scope is stated for each source in `references.md`:

- **Opened:** Piskala (abstract and opening sections); EPIC (full text); Agarwal et al. (partial text); Hill (proceedings abstract, metadata and reference list); Musco et al. (excerpt on evaluation metrics).
- **Record:** bibliographic record and/or abstract confirmed, paper not read in full.
- **Ref-only:** citation seen only in another paper's reference list.
- **Unverified:** carried over from an earlier list and not checked in this pass.

An abstract-only or excerpt-only reading supports only claims visible in that material. Do not imply that the full paper was reviewed.

**Completeness.** This review does not claim systematic coverage. Scopus and Google Scholar were not searched directly; there was no systematic screening and no structured snowballing beyond the named reference lists. Section 6 is a proposed search plan, not evidence that the searches have been completed.

**Relationship to Amin's work.** Amin's empirical-methodology sources M1–M5 remain in `research/empirical-methodology/references.md` and are not duplicated here. This review focuses on what to measure and how related research evaluates it.

## 2. Relevant sources

### 2.1 SDD context

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

### 2.2 Measurement design

**L5. Basili, Caldiera and Rombach (1994), _The Goal Question Metric Approach_.** Record only.
- GQM derives measures from goals through questions.
- **Use:** organising principle for mapping RQ1–RQ5 to proposed measures. Bibliographic details need confirmation before final citation.

### 2.3 Traceability and change impact

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

### 2.4 Requirements quality and testing

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

### 2.5 AI non-determinism

**L13. Ouyang et al. (2025), _An Empirical Study of the Non-Determinism of ChatGPT in Code Generation_.** Record/abstract and Crossref metadata.
- The abstract reports non-determinism in ChatGPT code generation and notes that temperature zero does not guarantee deterministic output.
- **Limitations:** benchmark code generation, not agentic SDD workflows or this calculator.
- **Use:** caution to record model/tool settings and preserve primary runs and reruns separately. A single run per workflow cannot estimate run-to-run variance.

### 2.6 Grey literature

GitHub Spec Kit documentation, BMAD documentation, Böckeler's practitioner comparison and Eberhardt's practitioner critique have not been opened in this pass. Treat vendor and practitioner statements as claims, not independent empirical findings. Verify current versions before describing exact tool behaviour.

## 3. Synthesis: support, limits and project proposals

| Area | What the reviewed material supports | What it does not establish | Project-specific proposal |
|---|---|---|---|
| Specification quality (J1/J3) | Artefact-set view; requirement-quality vocabulary; an example 0–3 rubric approach (L2; L10 not opened) | Validity of this project's anchors or causal effect on outcomes | Use Amin's J1–J7 anchors; optional evidence prompts |
| Traceability (M03/M13) | Traceability concept and practical difficulty (L6) | A validated counting formula for these tracks | Separate native links from evaluator-reconstructed links |
| Change impact (M07) | Precision/recall as evaluation concepts (L14); requirement-level impact is underrepresented in the mapped literature (L8) | A ready-made requirement-level benchmark | Build and freeze a project reference set before matching |
| Defects, regressions and rework | Large-scale approaches exist (L3) | That repository-scale methods transfer to two small tracks | Count distinct defects and documented rework episodes |
| Testing (M01/M16) | Coverage is not equivalent to effectiveness (L11) | That test count or coverage alone measures quality | Use shared AC outcomes as primary evidence; report counts as context |
| Human work and effort (M08–M10) | No direct SDD norm established by the sources reviewed here | Expected intervention or effort levels | Record interventions and measured time consistently for both operators |
| Reproducibility (M15/J7) | General empirical-method guidance is covered by Amin's M1–M5 | A validated evidence-completeness ratio | Treat M15 as a project proposal pending agreement |
| Non-determinism | Code-generation non-determinism is reported in L13 | Variance of the two workflows in this project | Preserve primary runs, settings and reruns; do not infer variance from one run |

**Overall interpretation.** The literature provides concepts and measurement precedents, not validation of this project's complete measurement system. The measures, operational definitions, thresholds, data structures and visualisations remain project-specific proposals unless the team adopts them.

## 4. Limitations

- This is a small targeted review, not a systematic review.
- Scopus and Google Scholar searches have not been completed.
- Several sources are abstracts, records, reference-list entries or partial excerpts.
- Recent SDD work may change quickly; preprint status and bibliographic metadata require checking.
- No reviewed source validates the exact measures proposed for this calculator comparison.
- Human intervention and effort in agentic SDD workflows remain an identified search gap.

## 5. Carried-over sources not yet verified

The following were carried over from an earlier plan and are not evidence in this review: arXiv:2609.05667, arXiv:2608.30572 and arXiv:2605.18461. Open each record and the paper, confirm title/authors/version and relevance, then retain or remove it. Do not cite these as read.

## 6. Search plan (not yet completed)

Run the searches and record date, database, exact query, number of results, screening decision and reason for inclusion/exclusion.

| Database | Proposed query | Purpose |
|---|---|---|
| Scopus | `TITLE-ABS-KEY("spec-driven development" OR "specification-driven development") AND TITLE-ABS-KEY(AI OR LLM OR agent)` | SDD studies and tool comparisons |
| Scopus | `TITLE-ABS-KEY("requirements traceability") AND TITLE-ABS-KEY(LLM OR generative OR "AI-assisted")` | AI-assisted traceability |
| Scopus | `TITLE-ABS-KEY("change impact analysis") AND TITLE-ABS-KEY(requirement* AND (LLM OR generative))` | Requirement-level impact analysis |
| Google Scholar | `"spec-driven development" empirical OR "case study" OR comparison` | Early empirical work |
| Google Scholar | `"requirements quality" "coding agents"` | Specification quality for agents |
| arXiv (cs.SE) | `spec-driven development`; `agentic software engineering specification quality` | Preprints |
| Any | `("human intervention" OR "human-in-the-loop") AND "coding agent" AND (effort OR rework)` | Human-work measurement gap |

Record the exact syntax accepted by each database; the table is a search plan, not a completed search log.
