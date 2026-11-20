# Presentation Plan — BMAD vs GitHub Spec Kit (COMP.SE.130)

Status: **DRAFT for owner approval. No .pptx has been built yet.**
Source: `manuscript/final-report/final-report.pdf` (all numbers below are taken from it; nothing is added).

## 1. Decisions needed before I build

| # | Question | My default if you do not object |
|---|----------|---------------------------------|
| D1 | Talk length | 12 minutes, 14 main slides (about 50 s per slide) |
| D2 | Speakers | Four authors, one block each (see section 4); change if the split differs |
| D3 | Include the AI-use declaration as a slide | Yes, as a backup slide, not in the main talk |
| D4 | Include the deferred material from memory (BMAD artefact mapping, findings list, repo file guide) | As two backup slides (B2, B3). I have not rebuilt them from the evidence yet; I would do that after approval |
| D5 | Fig. 4 chart | Rebuild as a native, editable PowerPoint chart in the deck palette. Figs. 1 to 3 reuse the report's PNGs |
| D6 | Speaker notes | Yes: 2 to 3 sentences per slide, taken from the report text |

## 2. Style (professional, classic, little text)

- 16:9, white background, dark navy titles, one accent colour (muted blue), one secondary (grey). BMAD and Spec Kit get two fixed colours used consistently: BMAD = navy, Spec Kit = teal-green.
- Serif-free: one sans font (Calibri), titles 30 pt, body 18 to 20 pt, no text below 16 pt.
- Maximum 4 bullets per slide, each under 10 words. Tables limited to 5 rows.
- Footer: short title, slide number, "Tampere University". No animations, no stock images, no icons beyond simple lines.
- Where the report is cautious ("we make no claim..."), the slide keeps the same wording. No ranking and no "winner" anywhere.

## 3. Slide-by-slide content

### Main talk

**1. Title**
- Title: "Comparing BMAD and GitHub Spec Kit for Spec-Driven Development"
- Subtitle: "A Requirements Engineering case study on a Java Swing calculator"
- Four author names, Tampere University, COMP.SE.130 Requirements Engineering, date.
- Image: none.

**2. Why this study**
- Coding agents produce code, but intent gets lost in chat.
- Spec-driven development (SDD): the specification is the input to the agent.
- RE question: how does each workflow carry requirements to code and handle change?
- Image: none (or a one-line flow: Spec → Plan → Tasks → Code).

**3. Research questions and measurement goals**
- RQ1 Specification structure; RQ2 Traceability; RQ3 Change handling; RQ4 Match to requirements.
- Goal Question Metric goals G1 to G5 (G4 effort and G5 evidence are supporting goals).
- Image: small two-column mapping graphic (from Table I): G1 → RQ1, RQ2; G2 → RQ3; G3 → RQ4; G4, G5 → supporting.

**4. The case and the change request**
- Java Swing calculator: 8 functional + 5 non-functional requirements, 19 acceptance criteria.
- CR-01: square, square root, reciprocal; 11 requirements, 22 criteria; all 19 baseline checks re-run.
- Image: simple three-box diagram: Baseline (13 IDs, 19 AC) → CR-01 (11 IDs, 22 AC) → Re-check (19). No calculator screenshot unless you give me one.

**5. Study design**
- Same frozen requirements, native workflow per tool, change request released to both, independent evaluators.
- Image: report Fig. 1 (`.ms/figures/study-workflow.png`).

**6. Two workflows, two environments**
- Table (5 rows): tool and version (BMAD 6.12.4 / specify-cli 1.2.3); OS (macOS / Windows 11); coding agent (Claude Code, both); model (claude-sonnet-5-5; Spec Kit started on claude-opus-5-5); operator (one per tool).
- One-line note: tool, agent, operator and environment are confounded.
- Image: none.

**7. BMAD as used**
- Path: PRD → architecture → epics and stories → sprint plan → build → review layers.
- Change path: correct-course, impact analysis, new Epic 4.
- Image: report Fig. 2 (`.ms/figures/bmad-workflow.png`).

**8. GitHub Spec Kit as used**
- Path: constitution → specify → plan → tasks → analyze → implement.
- Change path: impact analysis, then same chain in a new feature folder.
- Image: report Fig. 3 (`.ms/figures/speckit-workflow.png`).

**9. Capability comparison**
- Condensed from Table IV, 5 rows: specification structure, requirement-to-task link, ambiguity handling, change handling, human control.
- Two columns, one short phrase per cell. Header line: "What the workflows offered, not ratings".
- Image: none.

**10. Result: acceptance outcomes**
- Baseline: BMAD 19 of 19, Spec Kit 18 of 19 plus 1 Blocked (offline launch not executed).
- CR-01: 22 of 22 both; baseline re-check 19 of 19 both; no regression found.
- Takeaway line: "A Blocked criterion is not a Fail. The difference is evidence, not correctness."
- Image: Fig. 4 rebuilt as native grouped bar chart (Base op., Base ind., CR-01, Regression; BMAD vs Spec Kit).

**11. Result: handling the change**
- Both wrote an impact analysis before coding; both met effects it had not listed.
- BMAD: unplanned GUI tests and an initial-state helper.
- Spec Kit: unplanned constitution update, a changed test, a helper; one product defect (clipped negative square-root message) found by visual review, then fixed.
- Image: two-column table (condensed Table V), 3 rows.

**12. Result: evidence and reproducibility**
- Both froze baseline and change with git tags.
- Spec Kit change evaluation kept a frozen build, GUI observations, screenshots, a dated follow-up for the offline check.
- BMAD change evaluation is recorded in sheets and screenshots, with less raw evidence.
- Takeaway: "22 of 22 and 19 of 19 are reported statuses of unequal inspectability."
- Image: none (optional: two small stacked "evidence depth" labels; I would avoid anything that looks like a score).

**13. Observed differences (no ranking)**
- Documentation structure: BMAD around product and design decisions; Spec Kit around the specified feature.
- Change handling: BMAD as change to an existing plan; Spec Kit as a new feature.
- Inspectable evidence: Spec Kit retained more.
- Image: three-column graphic with the three headings.

**14. What we could not measure, and limits**
- Not calculated: native traceability, impact recall, effort and duration, rework, rubric ratings.
- Validity: one case, one run per workflow, one operator per tool, two operating systems, a model change at the start of Spec Kit.
- Image: none.

**15. Conclusion and implications**
- Both workflows carried the same requirements through to tests and reported full success on the shared criteria.
- They differ in how documentation is organised, how change was handled and how much evidence can be inspected. No overall ranking.
- Future work: fix an impact reference set before coding; measure effort and rework prospectively; verify native links.
- Image: none.

**16. Questions**
- "Thank you" and repository reference (github.com/VSondur123/spec-driven-development-analysis).
- Image: none.

That is 16 slides including title and questions (14 content slides). To fit 12 minutes I would merge 6 into 5 if you want it shorter.

### Backup slides (not presented unless asked)

- **B1. AI-use declaration**: five bullets (building the calculators, requirements, evaluation, literature, report), as in report section VIII.
- **B2. BMAD artefact mapping and findings** (from memory, to be rebuilt from `bmad-calculator/evidence/` and `shared/experiment-log.md`): study-term to BMAD-artefact mapping; no dedicated HLD/LLD; no native traceability matrix; gap found before coding; fast path with reviewer subagents skipped; JUnit 5 to 6.1.3 after registry check.
- **B3. Repository guide**: one line per top-level folder (`shared`, `bmad-calculator`, `github-spec-kit-calculator`, `research`, `manuscript`, `discussion-forum`).
- **B4. Measures table**: condensed Table VI (M01 to M16 disposition).
- **B5. References**: the ten references as listed in the report.

## 4. Suggested speaker split (change as needed)

| Slides | Topic | Speaker |
|--------|-------|---------|
| 1 to 4 | Motivation, questions, case | to be named |
| 5 to 9 | Design, tools, capabilities | to be named |
| 10 to 13 | Results and differences | to be named |
| 14 to 16 | Limits, conclusion, questions | to be named |

## 5. Build plan once approved

- Tool: the `pptx` skill (python-pptx or pptxgenjs, whichever is installable here). Output: `manuscript/presentation/presentation.pptx`.
- Images: copied from `manuscript/final-report/.ms/figures/` into `manuscript/presentation/figures/`; originals untouched.
- I will render slides to images and check layout before handing over, if a renderer is available. `libreoffice` is not installed on this machine, so I may not be able to preview; I will say so if that is the case.
- No git commit unless you ask.

## 6. Points I am not sure about

- The report has no screenshot of the calculator. If you want one on slide 4, please give me the file.
- No required slide count, time limit or template was found in `project.md` or `ReadMe.md`. D1 is my assumption.
