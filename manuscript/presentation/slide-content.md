# Slide content: BMAD vs GitHub Spec Kit

Independent record of what is on each slide. Source of truth: `manuscript/final-report/final-report.pdf`.

| Slide | Presenter | Time |
|---:|---|---:|
| 1 | Varun Ganesh Sondur | 0:45 |
| 2 | Varun Ganesh Sondur | 0:45 |
| 3 | Varun Ganesh Sondur | 1:30 |
| 4 | Mohammadamin Lotfiourimi | 1:15 |
| 5 | Mohammadamin Lotfiourimi | 1:30 |
| 6 | Mohammadamin Lotfiourimi | 1:15 |
| 7 | Varun Ganesh Sondur | 1:30 |
| 8 | Georgios Malezoglou | 1:30 |
| 9 | Georgios Malezoglou | 1:30 |
| 10 | Pham Thanh | 2:00 |
| 11 | Pham Thanh | 1:30 |
| 12 | Pham Thanh | 0:30 |
| 13 | All four presenters | 0:00 |

## Slide 1. Title and introduction

**Message:** Introduce the study and the team.

- Comparing BMAD and GitHub Spec Kit for Spec-Driven Development
- A Requirements Engineering Case Study on a Java Swing Calculator
- Tampere University, Requirements Engineering
- Four authors listed
- Visual: chain Requirements, Specification, Plan, Tasks, Code and tests; BMAD and Spec Kit tags

## Slide 2. Research team

**Message:** Introduce the team and the scope of the work.

- Four names with Tampere University
- Course and one-line study scope

## Slide 3. What is spec-driven development?

**Message:** SDD makes the specification the input to the agent; we study how requirements are carried through.

- Definition: specification as source of truth, code as secondary product
- Three levels: spec-first, spec-anchored, spec-as-source
- Prompt, AI agent, Code (vibe coding; not an experimental condition)
- Requirements, Specification, Plan, Tasks, Code, Tests (illustrative; tools differ)

## Slide 4. Research questions and evaluation goals

**Message:** The questions define what we investigate; the GQM goals organise how we evaluate.

- Main research question
- RQ1 to RQ4 cards
- G1 to G5 strip; G4 and G5 marked supporting

## Slide 5. Experimental design: one case, two workflows

**Message:** One frozen baseline, one change request, two native workflows, independent evaluation.

- Original report Fig. 1
- Baseline: 13 requirements (8 functional, 5 non-functional), 19 criteria
- CR-01: 11 requirements, 22 criteria, 19 baseline checks re-run
- Six fairness rules
- Caveat: whole tool-agent-operator workflow is compared

## Slide 6. The case: a Java Swing calculator

**Message:** Both workflows built the same small application and received the same change.

- Schematic calculator (not a screenshot)
- Baseline summary
- CR-01: square, square root, reciprocal with the two error rules; baseline re-run

## Slide 7. BMAD: workflow and change handling

**Message:** BMAD handled CR-01 as a change to an existing plan.

- Original report Fig. 2
- BMAD 6.12.4; flow PRD, Architecture, Epics and stories, Sprint plan, Build, Review
- Fast path, UX skipped, 3 epics, 9 stories
- Change path: CR-01, change proposal, impact analysis, Epic 4 (4 stories), amended PRD/architecture/epics, build

## Slide 8. GitHub Spec Kit: workflow and change handling

**Message:** Spec Kit handled CR-01 as a new feature.

- Original report Fig. 3
- specify-cli 1.2.3; flow Constitution, Specify, Plan, Tasks, Analyze, Implement
- 34 baseline tasks; first analysis 11 findings
- Change path: impact analysis, constitution 1.0.0 to 1.1.0, new feature spec and plan, 49 tasks, analysis with 10 findings

## Slide 9. How we evaluated the results

**Message:** Independent evaluation and preserved evidence, with strict outcome statuses and honest gaps.

- Six-step evaluation pipeline
- Three evidence layers kept separate
- Statuses: Pass, Fail, Blocked, Not run
- 16 measures; native traceability, impact recall, effort and rework, rubric ratings, evidence completeness % not calculable

## Slide 10. Results: what did we observe?

**Message:** Equal reported outcomes; the difference is in the evidence, not demonstrated correctness.

- Native chart reproducing report Fig. 4: BMAD 19, 19, 22, 19; Spec Kit 19, 18, 22, 19
- Table: independent baseline BMAD 19 Pass; Spec Kit 18 Pass, 0 Fail, 1 Blocked
- Note: Blocked is not Fail; later follow-up kept separate
- Three findings: structured artefacts and candidate links, impact analyses incomplete, evidence depth differs

## Slide 11. Discussion, limitations and lessons

**Message:** The differences belong to these two runs and must not be generalised.

- Observed differences for BMAD and Spec Kit
- Eight threats to validity
- Five practical lessons

## Slide 12. Conclusion and Q&A

**Message:** No overall ranking; the workflows differ in documentation, change handling and inspectable evidence.

- Report conclusion statement
- BMAD, Spec Kit, Both, Not measured cards
- We found no basis for an overall ranking

## Slide 13. Thank you

**Message:** Close and open the floor for questions (time beyond the 15-minute talk).

- Thank you; Questions?
- Four names, Tampere University, Requirements Engineering
- Study repository address (report reference [10]) and BMAD and Spec Kit tags

