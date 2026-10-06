# Exploring and Comparing Tools for Spec-Driven Development

This repository contains the work for the Requirements Engineering group project **“Exploring and comparing tools for spec-driven development”** at Tampere University.

## Project Overview

The project investigates how **Spec-Driven Development (SDD)** tools support the transition from requirements to implementation and how well the resulting implementation remains aligned with the original requirements.

Our focus is on the **Requirements Engineering (RE) perspective**, rather than simply comparing which tool produces better code.

The study compares two SDD approaches:

- **GitHub Spec Kit**
- **BMAD (Breakthrough Method for Agile AI-Driven Development)**

Both tools will be evaluated using the same software case and, where possible, the same requirements, AI model, and requirement changes.

## Research Question

### Main Research Question

> **How do SDD tools support the transition from requirements to implementation, and how well does the result match the original requirements?**

### Sub-Research Questions

1. **RQ1:** How does each tool create and structure specifications, including formats, levels of detail, templates, and the role of the human?
2. **RQ2:** How are requirements turned into design, tasks, code, and tests, and is the link between these artifacts traceable?
3. **RQ3:** How does each tool handle changes to the specification, and does the specification remain aligned with the code?
4. **RQ4:** How closely does the generated implementation satisfy the original requirements?

## Key Concepts

The project focuses on the following concepts:

- Spec-Driven Development
- Spec-first, spec-anchored, and spec-as-source approaches
- Requirements-to-implementation transition
- Requirements traceability
- Specification change handling
- Acceptance criteria and test generation
- Non-functional requirements
- Human control and review
- Vibe coding as a contrasting development approach

## Methodology

The study uses a **comparative multi-case study approach with one shared software case**.

### Experimental Setup

1. Define a small common software case.
2. Specify approximately **8–10 functional requirements** and **3–4 non-functional requirements**.
3. Provide the same initial requirements to GitHub Spec Kit and BMAD.
4. Run the development workflow with each tool.
5. Collect specifications, plans, tasks, code, tests, and other generated artifacts.
6. Apply the same requirement change(s) to both workflows.
7. Compare how the tools propagate and handle the changes.
8. Evaluate the final implementation against the original requirements.

### Comparison Criteria

The tools will be compared using predefined criteria:

| Criterion | What we examine |
|---|---|
| Specification structure | How requirements are represented and organized |
| Requirement-to-task traceability | Whether requirements can be followed into plans and tasks |
| Requirement-to-code/test traceability | Whether implementation and tests can be connected back to requirements |
| Ambiguity and clarification | How the workflow identifies or resolves unclear requirements |
| Acceptance criteria | Whether requirements are translated into verifiable criteria |
| Test generation | How requirements influence generated tests |
| Non-functional requirements | How quality attributes are represented and implemented |
| Change handling | What happens when requirements change |
| Human control/review | Where human decisions, review, and intervention are required |

## Requirements Coverage Matrix

A requirements coverage matrix will be used to evaluate traceability and implementation coverage.

| Requirement | Specification | Plan | Task | Code | Test | Result |
|---|---|---|---|---|---|---|
| R1 | ✓ | ✓ | ✓ | ✓ | ✓ | Fully implemented |
| R2 | ✓ | ✓ | ✓ | ✓ | — | Partially implemented / not verifiable |
| R3 | ✓ | — | — | — | — | Not implemented |

The final matrix will be populated using evidence collected during the experiment rather than assumed coverage.

## Requirement Change Experiment

After the initial implementation, the same requirement change will be introduced to both tools. The change scenario will include:

- At least one **new requirement**
- At least one **modified requirement**

We will examine:

- How the specification changes
- Whether plans and tasks are updated
- Whether affected code is identified and modified
- Whether tests are updated or added
- Whether inconsistencies or specification-code drift appear
- How much human intervention is required

## Evidence Collection

For each tool, we will record:

- Tool and version information
- AI model and configuration, where applicable
- Prompts and commands used
- Generated specifications
- Plans and tasks
- Generated or modified source code
- Tests
- Requirement changes
- Before/after artifacts
- Git commits and repository history
- Screenshots where useful
- Observations during the workflow

This evidence will support the comparison and help make the experiment reproducible.

## Tools and Resources

### SDD Tools

- [GitHub Spec Kit](https://github.com/github/spec-kit)
- [BMAD](https://github.com/bmad-code-org/BMAD-METHOD)

### Supporting Tools

- GitHub repository for source code, artifacts, and version history
- Spreadsheet for the requirements coverage and comparison matrix
- AI coding agents supported by the selected workflows
- Scopus
- Google Scholar
- arXiv

No external dataset is required. A small self-defined software case will be used for the experiment.

## Expected Contribution

The project aims to provide a practical Requirements Engineering comparison of SDD tools by examining how they:

- structure requirements,
- transform requirements into actionable development artifacts,
- maintain traceability,
- generate or support acceptance criteria and tests,
- handle requirement changes, and
- maintain alignment between specifications and implementation.

The goal is not to claim that one tool is universally better, but to identify **how the tools support RE activities, where they differ, and where human involvement remains important**.

## Project Team

| Member | Main Responsibility |
|---|---|
| **Varun Ganesh Sondur** | Literature review, background, project coordination, final editing |
| **Mohammadamin Lotfiourimi** | GitHub Spec Kit setup, experiment, documentation, and evidence collection |
| **Pham Thanh** | BMAD setup, experiment, documentation, and evidence collection |
| **Georgios Malezoglou** | Comparison framework, requirements coverage matrix, evaluation, and integration |

All four members participate in the project activities and contribute to the final report.

## Project Timeline

| Period | Activity |
|---|---|
| 2–4 Oct | Lock project scope and research direction |
| 5–8 Oct | Mid-term discussion and refinement |
| 9–16 Oct | Learn tools, finalize requirements, prepare evidence log and report structure |
| 17–24 Oct | Conduct the comparative experiment |
| 25–29 Oct | Analyze results and complete the comparison |
| 30 Oct–3 Nov | Write the final report |
| 4–6 Nov | Review, edit, and finalize |
| **7 Nov 2026** | **Final submission** |

## Repository Structure

The repository is expected to contain material similar to the following:

```text
.
├── README.md
├── requirements/
│   ├── initial-requirements.md
│   └── changed-requirements.md
├── spec-kit/
│   ├── specifications/
│   ├── plans/
│   ├── tasks/
│   └── evidence/
├── bmad/
│   ├── specifications/
│   ├── plans/
│   ├── tasks/
│   └── evidence/
├── evaluation/
│   ├── coverage-matrix.xlsx
│   └── comparison.md
└── report/
    └── final-report.pdf
```

The exact structure may be adjusted as the experiment develops.

## Academic Deliverables

The final report will follow the required IEEE format and is planned to contain:

1. Introduction
2. Background
3. Method
4. Tool Descriptions
5. Results
6. Discussion
7. Conclusion
8. References / Appendices as required

The project will also include the required AI-use declaration.

## Research Sources

Initial literature includes research on Spec-Driven Development, SDD artifacts, and the impact of generative AI on Requirements Engineering. Relevant sources will be expanded through searches in **Scopus, Google Scholar, and arXiv**.

Key starting sources include:

- Piskala, *Spec-Driven Development: From Code to Contract in the Age of AI Coding Assistants* (2026).
- *SpecMine: A Large-Scale Corpus of Spec-Driven Development Artifacts* (2026).
- *The Impact of GenAI on the Future of Requirements Engineering* (2026).
- *Practical Implementation Report on Introducing Spec-Driven Development Using AI Agents in Software Development PBL* (2026).
- *One Developer Is All You Need: A Case Study of an AI-Augmented One-Person Squad in a Brownfield Enterprise* (2026).

The final report will document the complete bibliography and distinguish academic sources from tool documentation and other grey literature.

## Notes on Validity

The study has several anticipated limitations:

- The experiment uses one shared software case, which limits generalizability.
- AI-generated results may vary because of LLM non-determinism.
- SDD tools and their workflows are evolving rapidly.
- Results may depend on the selected AI model, configuration, prompts, and tool versions.

To reduce these threats, the team will record tool versions, prompts, model settings, generated artifacts, and experiment observations as consistently as possible.
