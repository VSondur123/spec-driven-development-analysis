# Speaker notes: BMAD vs GitHub Spec Kit

Generated together with the deck. Target total: 15:00. Word counts assume roughly 140 words per minute.

## Slide 1. Title and introduction

**Presenter:** Varun Ganesh Sondur  
**Target:** 0:45  
**Script length:** 101 words (about 0.7 min at 140 wpm)

Good day, everyone. We are four students from Tampere University, and this is our Requirements Engineering case study. Coding agents can now produce working software from short instructions, but the intent behind that software is easily lost when the instructions live only in a chat. Spec-driven development tries to fix that by making the written specification the input to the agent. We investigated how two AI-assisted spec-driven workflows, BMAD and GitHub Spec Kit, support the step from requirements to implementation, and how they handle one common change to those requirements. Both were used to build the same small Java Swing calculator.

## Slide 2. Research team

**Presenter:** Varun Ganesh Sondur  
**Target:** 0:45  
**Script length:** 87 words (about 0.6 min at 140 wpm)

Let me introduce the team. I am Varun Ganesh Sondur. With me are Mohammadamin Lotfiourimi, Pham Thanh and Georgios Malezoglou. All four of us study at Tampere University. In the study, two members each operated one tool to build the same calculator, and two other members evaluated the results independently with shared checklists. Please note that today's speaking order is only a presentation split; it is not a statement of who did which part of the research. Next, I will give you the background on spec-driven development.

## Slide 3. What is spec-driven development?

**Presenter:** Varun Ganesh Sondur  
**Target:** 1:30  
**Script length:** 209 words (about 1.5 min at 140 wpm)

So what is spec-driven development? The idea is that the specification is the source of truth, and the code is a generated or verified secondary product. Piskala distinguishes three levels of rigour: spec-first, where the specification is written before the code; spec-anchored, where it is kept alongside the code; and spec-as-source, where the specification is the main artefact. We use this vocabulary only to describe the workflows, not to rank the tools. At the bottom you see the contrast. In prompt-driven development, often called vibe coding, you prompt an agent and receive code. The course assignment names this as a contrasting approach, but it was not an experimental condition in our study. In spec-driven development, a person writes or approves a specification, a plan and a task list, and the agent generates code and tests against them. The exact sequence differs between tools, as you will see shortly. Why does this matter for requirements engineering? Because the interesting question is not which tool generates better code. It is how each workflow carries requirements through design, tasks, code and tests, and what happens when a requirement changes. Now that we understand what spec-driven development means, Mohammadamin will explain how we designed our experiment and the questions we wanted to investigate.

## Slide 4. Research questions and evaluation goals

**Presenter:** Mohammadamin Lotfiourimi  
**Target:** 1:15  
**Script length:** 146 words (about 1.0 min at 140 wpm)

Our main question is: how do spec-driven development tools support the transition from requirements to implementation, and how well does the result match the original requirements? The course assignment splits this into four research questions. RQ1 asks how each tool creates and structures specifications. RQ2 asks how requirements become design, tasks, code and tests, and whether those links can be followed. RQ3 asks how each tool handles a change, and whether the specification stays aligned with the code. RQ4 asks how closely the implementation satisfies the original requirements. To organise the measurement we used the Goal Question Metric approach, with five goals. G1 to G3 map directly onto the research questions. G4, human work, and G5, evidence and reproducibility, have no matching course question, so we treat them as supporting goals. In short: the questions define what we investigate, and the goals structure how we evaluate.

## Slide 5. Experimental design: one case, two workflows

**Presenter:** Mohammadamin Lotfiourimi  
**Target:** 1:30  
**Script length:** 183 words (about 1.3 min at 140 wpm)

This is the design of the experiment, shown in Figure 1 of our report. We start from one shared, frozen set of requirements and acceptance criteria: 13 requirements, of which 8 are functional and 5 non-functional, and 19 acceptance criteria. Two team members each operated one tool, BMAD on one track and GitHub Spec Kit on the other, and each built the same calculator. Each baseline was frozen with a git tag before the change request was released. Then both tracks received the same change request, CR-01, with 11 functional requirements and 22 acceptance criteria. Each track wrote an impact analysis before coding, implemented the change, and froze the result with a second tag. Finally, two other members, who had built neither calculator, evaluated both tracks with identical execution sheets. To keep this fair, both tracks used the same inputs, did not share generated artefacts, kept their tool's native workflow, recorded every human intervention and documented every deviation. One important caveat: we compare the whole workflow of tool, coding agent and operator. We do not claim to isolate the effect of either framework.

## Slide 6. The case: a Java Swing calculator

**Presenter:** Mohammadamin Lotfiourimi  
**Target:** 1:15  
**Script length:** 160 words (about 1.1 min at 140 wpm)

Both workflows were asked to build the same small desktop application: a Java Swing calculator. It has two operand fields, four operation buttons, a Calculate button, a Clear button and inline error messages. Arithmetic is exact in decimals, division rounds half-up to ten places, and the error messages and their priority are fixed: empty input first, then no operation, then an invalid number, then an arithmetic error such as division by zero. The change request, CR-01, adds three unary operations: square, square root and reciprocal. They use the first number field and ignore the second. A negative square root and a reciprocal of zero must give errors, and all 19 baseline checks must be re-run. The drawing here is a schematic of the specified interface, not a screenshot from either track. The calculator is deliberately small, and the change request was identical for both tracks. With the common case and experiment established, Varun will walk us through the BMAD workflow.

## Slide 7. BMAD: workflow and change handling

**Presenter:** Varun Ganesh Sondur  
**Target:** 1:30  
**Script length:** 185 words (about 1.3 min at 140 wpm)

BMAD stands for Breakthrough Method for Agile AI-Driven Development. We used version 6.12.4, run through Claude Code. The diagram is Figure 2 from our report. The path we actually followed starts with a product requirements document, then an architecture with ten numbered decisions, then epics and stories, then sprint planning, then building each story and checking it with review layers. We used a fast path for the PRD and architecture, skipped the optional UX step, and did not run the reviewer subagents for the PRD and architecture. Work was split into three epics and nine stories, and two stories used a minimal path without the review layers. For CR-01, BMAD's correct-course step produced a sprint change proposal and an impact analysis before any code was written. After the owner approved, a new Epic 4 with four stories was added, the PRD, architecture and epics were amended, and the stories were built. So BMAD treated the change as a change to an existing plan. That was the BMAD approach. George will now show how GitHub Spec Kit handled the same requirements and the same change request.

## Slide 8. GitHub Spec Kit: workflow and change handling

**Presenter:** Georgios Malezoglou  
**Target:** 1:30  
**Script length:** 208 words (about 1.5 min at 140 wpm)

GitHub Spec Kit is an open-source toolkit that gives coding agents structured processes. We used specify-cli version 1.2.3, again through Claude Code. The workflow in Figure 3 starts with a constitution that records the project principles once. Then specify writes the feature specification and raises clarification questions, plan produces the technical approach together with research, a data model, contracts and a quickstart, tasks derives the task list, analyze checks consistency without changing anything, and implement writes the code and tests. The owner approved the plan before the tasks were generated, which gave 34 baseline tasks. The analysis found 11 findings before any code was written: none critical, two high, two medium and seven low. They were fixed after the owner's approval. For CR-01, the track wrote an impact analysis first, amended the constitution from version 1.0.0 to 1.1.0, and then ran the same chain in a new feature folder. That gave 49 tasks and a second analysis with 10 findings. So Spec Kit treated the change as a new feature. Compare this with BMAD's PRD, architecture, epics and stories. They are different structures, and we do not claim that one is better. Now that we have seen both workflows, I will explain how we observed and evaluated them.

## Slide 9. How we evaluated the results

**Presenter:** Georgios Malezoglou  
**Target:** 1:30  
**Script length:** 209 words (about 1.5 min at 140 wpm)

Now, how did we evaluate? Both baselines and both changes were frozen with git tags. Each track wrote an impact analysis before coding and implemented CR-01. Then all 19 baseline criteria were re-checked and the 22 CR-01 criteria were evaluated, using independent execution sheets, with the evidence preserved. Every criterion receives one of four statuses: Pass, Fail, Blocked or Not run. A criterion counts as Pass only if the behaviour was observed. Blocked is not a Fail, and Not run is not a zero. We kept three evidence layers apart: the operators' reports, the independent evaluators' observations, and the native automated tests. They cannot be merged into one number, because the tools use different test designs and the evaluators saw different amounts of evidence. We started from a catalogue of sixteen measures derived from the five goals, and several could not be calculated: verified native traceability, impact recall, comparable effort and rework, rubric ratings, and evidence completeness as a percentage. We say so openly instead of reporting zero. In writing the report we did not re-run any test or launch either application; we relied on the recorded evidence. Now that we've seen both workflows and how we evaluated them, Thanh will present the results and what we can conclude.

## Slide 10. Results: what did we observe?

**Presenter:** Pham Thanh  
**Target:** 2:00  
**Script length:** 240 words (about 1.7 min at 140 wpm)

Here are the results; the chart reproduces Figure 4 of our report and shows parent acceptance criteria passed. First the baseline. The operators of both tracks reported all 19 criteria as passed. The independent evaluation differs. For BMAD, the evaluator reported 19 passes. For Spec Kit, the evaluator recorded 18 passes, no failures and one Blocked criterion: the offline launch was not executed because the network prerequisite could not be established. That is not a product failure. The report also records a later human-reported follow-up that marked it Pass, but the original Blocked result stays visible. For the change request, both tracks reported all 22 criteria as passed, and all 19 baseline criteria as passed again after the change, with no regression found in the checked set. Please note that these CR-01 numbers are reported statuses, and the evidence behind them is not equally strong. We draw three findings. First, both workflows produced structured artefacts with candidate links from requirements to tests, but we did not verify native traceability coverage. Second, both impact analyses were useful but incomplete: each track met effects the analysis had not listed. Third, Spec Kit's independent change evaluation kept more inspectable raw evidence: a frozen-snapshot build, GUI observations, screenshots and a dated follow-up for the offline check. BMAD's change results are recorded in evaluator sheets, with less complete criterion-level evidence. This difference concerns the evidence package. It does not show that one implementation is better.

## Slide 11. Discussion, limitations and lessons

**Presenter:** Pham Thanh  
**Target:** 1:30  
**Script length:** 179 words (about 1.3 min at 140 wpm)

What do the results mean? The observed differences are about these two runs only. BMAD's documents are organised around product and design decisions: a PRD, an architecture, epics and stories, with review layers and a change proposal. Spec Kit's are organised around the specified feature: constitution, specification, plan and tasks, with an impact forecast listing changed and unchanged files, and more inspectable independent evidence. The middle column limits what we can claim. We have one case, one run per workflow, one operator per tool, two operating systems, and a model change at the start of the Spec Kit track. Effort and duration were not measured, impact recall could not be calculated, native traceability was not verified, and evidence depth differed. These observations are descriptive, not statistically established. Finally, five practical lessons. Judge a change by new and preserved behaviour together. Check the real window, because a clipped error message passed automated tests. Keep a Blocked attempt next to a later completion to avoid hindsight bias. Freeze commits, commands, timestamps and environment. And fix an impact reference set before coding.

## Slide 12. Conclusion and Q&A

**Presenter:** Pham Thanh  
**Target:** 0:30  
**Script length:** 91 words (about 0.7 min at 140 wpm)

To conclude: within the limits of one case, both workflows carried the same requirements through specification, planning, tasks, code and tests, handled the change with a pre-coding impact analysis, and reported all 22 change criteria and all 19 baseline re-checks as passed. BMAD gave a continuous account of architecture and story decisions; Spec Kit gave a more inspectable evidence package for the independent change evaluation. Both needed human decisions, both met effects their impact analyses missed, and effort and rework were not measured. We found no basis for an overall ranking.

## Slide 13. Thank you

**Presenter:** All four presenters  
**Target:** 0:00  
**Script length:** 46 words (about 0.3 min at 140 wpm)

Thank you for listening. We are happy to take your questions. Questions about a specific workflow are best answered by the team member who operated or evaluated that track; questions about the study design and results by whoever is closest to that part of the talk.

---

Total target 15:30; total script 2044 words, about 14.6 min at 140 wpm.
