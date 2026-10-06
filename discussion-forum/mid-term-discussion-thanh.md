# Mid-Term Discussion Plan
**Course:** COMP.SE.130-2026-2027 Requirements Engineering
**Topic 1:** Exploring and comparing tools for spec-driven development

---

## 1. Research topic and importance

**Topic:** Exploring and comparing tools for spec-driven development (SDD).

**Why it matters:**
- SDD tools let developers capture what to build as Markdown documents that an AI agent then uses to generate and modify code. The practice barely existed before 2025 and now spans dozens of competing tools (SpecMine corpus paper).
- SDD treats specifications as operational inputs to code-generating agents, not only reference documents for human developers (arXiv:2609.05667).
- Tools differ in how they structure specs, move from requirements to tasks and code, and handle spec changes. These differences are an RE question: do the tools preserve the requirements?

## 2. Key concepts and research questions

**Key concepts:**
- Spec-driven development
- Spec-first, spec-anchored and spec-as-source (a spectrum in which specs gain increasing authority over code)
- Requirements-to-implementation transition
- Traceability
- Specification change handling
- Vibe coding (contrast case)

**Main research question:**
How do SDD tools support the transition from requirements to implementation, and how well does the result match the original requirements?

**Sub-questions:**
- **RQ1:** How does each tool create and structure specifications (format, levels, templates, role of the human)?
- **RQ2:** How are requirements turned into design, tasks and code, and is the link traceable?
- **RQ3:** How does each tool handle changes to the specification, and does the spec stay aligned with the code?
- **RQ4:** How closely does the generated implementation satisfy the original requirements?

## 3. Methodology

A **comparative multi-case study** using one shared case project.

1. **Tool selection:** choose 3 tools by explicit criteria (maturity, active use, different workflows). Candidates: GitHub Spec Kit, Kiro Specs, and one of OpenSpec or BMAD.
2. **Common case:** one small, realistic system with about 8-10 functional and 3-4 non-functional requirements. Every tool receives the same input requirements and, where possible, the same underlying LLM, so differences come from the tool.
3. **Comparison framework (defined before running the tools):**
   - Spec structure
   - Requirement-to-task traceability
   - Handling of ambiguity and clarification questions
   - Acceptance criteria and test generation
   - Support for non-functional requirements
   - Change handling
   - Human control and review points
4. **Change scenario:** after the first implementation, apply the same requirement change (one added, one modified) in each tool and observe the effect on spec, plan, tasks and code.
5. **Evaluation of results:** a requirements coverage matrix (requirement -> spec -> task -> code/test), counting requirements fully, partly or not implemented, plus spec-code drift after the change.
6. **Data collection and synthesis:** structured notes and screenshots per tool, the artifacts each tool produces, a comparison table, and a short narrative per RQ.
7. **Threats to validity:** a single case, LLM non-determinism, and fast-changing tools. Mitigation: log tool versions, prompts and model settings.

## 4. Relevant articles

> **TODO before the meeting:** also search **Scopus** and **Google Scholar** and add anything relevant. The checklist requires all three sources (Scopus, Google Scholar, arXiv). The papers below were found via arXiv/web search; open each one to confirm details.

| # | Article | Relevance |
|---|---------|-----------|
| 1 | Piskala, *Spec-Driven Development: From Code to Contract in the Age of AI Coding Assistants* (arXiv:2602.00180, 2026) | Defines spec-first, spec-anchored and spec-as-source, and analyses tools from BDD frameworks to toolkits such as GitHub Spec Kit. Conceptual basis for our comparison framework. |
| 2 | *SpecMine: A Large-Scale Corpus of Spec-Driven Development Artifacts* (arXiv:2608.25202) | Covers artifacts from 18 SDD tools. Helps with tool selection and shows how spec structures differ. |
| 3 | *The Impact of GenAI on the Future of Requirements Engineering* (arXiv:2609.05667), Section 4.3 | Places SDD within RE research and lists current tools; each workflow defines its own spec format, level of detail and project integration. |
| 4 | *Practical Implementation Report on Introducing Spec-Driven Development Using AI Agents in Software Development PBL* (arXiv:2608.30572) | Describes a workflow where requirements.md, design.md and tasks.md are generated and verified by developers at each step. Practical example of the requirements -> design -> tasks chain. |
| 5 | *One Developer Is All You Need: A Case Study of an AI-Augmented One-Person Squad in a Brownfield Enterprise* (arXiv:2605.18461) | Treats natural-language specifications as the primary artifact and shows a spec prompt template. Industrial perspective. |

**Grey literature:** GitHub Spec Kit documentation and Kiro Specs documentation (named in the course topic description).

## 5. Tools, datasets, frameworks and AI platforms

- **SDD tools:** GitHub Spec Kit, Kiro, plus one of OpenSpec or BMAD
- **AI platforms:** an AI coding agent (e.g. Claude Code, Copilot or Cursor, depending on tool support), with the same LLM across tools where possible
- **Case project:** a small self-defined system with requirements written by the group
- **Supporting tools:** GitHub repository for artifacts and version logs; spreadsheet for the coverage matrix and comparison table; Scopus, Google Scholar and arXiv for literature
- **Dataset:** none required. The SpecMine corpus can optionally be used to look at real-world spec examples.

## 6. Final report outline

1. **Introduction:** motivation, problem statement, research questions
2. **Background:** SDD concepts, the spec-first to spec-as-source spectrum, related work
3. **Method:** tool selection, case project, comparison framework, evaluation procedure
4. **Tool descriptions:** workflow and artifacts of each selected tool
5. **Results:** findings per RQ, comparison table, coverage matrix
6. **Discussion:** implications for RE (requirements quality, traceability, human role), threats to validity
7. **Conclusion:** answers to the RQs and future work
8. **References and appendices:** prompts, example specs, matrices

## 7. Tasks, roles and estimated time

*Placeholder names and hours; adjust to your real division of work and course workload.*

| Member | Main responsibility | Other tasks | Est. time |
|--------|--------------------|-------------|-----------|
| **Varun** | Literature review, background section, project management | Final editing | ~28 h |
| **Admin** | Tool 1 (e.g. Spec Kit): setup, running, documentation | Comparison framework | ~28 h |
| **Thanh** | Tool 2 (e.g. Kiro): setup, running, documentation | Change scenario design | ~28 h |
| **George** | Tool 3 and case project requirements | Coverage matrix and evaluation | ~28 h |
| **All** | Discussion, conclusion, peer review of sections | Final presentation | shared |

## 8. Team confirmation

Our team has 4 members: **Varun Ganesh Sondur, Mohammadamin Lotfiourimi, Georgios Malezoglou, Pham Thanh**. All members are actively involved.

---