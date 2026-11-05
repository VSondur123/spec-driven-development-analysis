# Project Context — Living Evidence Document

**Purpose:** persistent information source for the final report (COMP.SE.130-2026-2027 Requirements Engineering). It is not the report. Do not generate the report from it without the owner's explicit instruction (see `report.md`).

**Status tags:** `[verified]` read in a repository file or git history during an inspection; `[reported]` stated by a member in a repository record but not independently re-run; `[draft]` research proposal not adopted by the team; `[planned]` intended, not done; `[TODO]` unknown, must be collected. Evidence IDs (E-nn) point to section M.

**Update rules:** inspect newly completed work first; preserve earlier entries; mark outdated entries `[superseded <date>]` instead of deleting; never invent results, hours, references, contributions or decisions.

## Changelog

| Date | Change | Inspected |
|---|---|---|
| 2026-11-03 | Initial version created. | `project.md`, `ReadMe.md`, `shared/*`, both tracks' `evidence/00-STATUS.md`, BMAD baseline and CR-01 result files, Spec Kit `06-test-results.md`, `research/*` registers, git log and branches. Source code and generated specs were not interpreted. |

**Repository snapshot at creation:** branch `feature/cr-01-bmad`, HEAD `72f2783` (2026-11-03). Tag `baseline-frozen` exists. `[verified]` (E-30)

---

## A. Project Overview

| Item | Value | Status |
|---|---|---|
| Course | COMP.SE.130-2026-2027 Requirements Engineering, Tampere University | `[verified]` E-01 |
| Project title | "Exploring and comparing tools for spec-driven development" | `[verified]` E-01 |
| Group name | `[TODO]` not in the repository | |
| Authors | Varun Ganesh Sondur, Mohammadamin Lotfiourimi (git: Amin Lotfi / Mohammadamin Lotfi), Pham Thanh, Georgios Malezoglou | `[verified]` E-01, E-30 |
| Objective | Understand how each complete tool–agent–operator workflow supports requirements engineering and controlled change. The study does not claim to isolate the effect of BMAD versus Spec Kit alone. | `[verified]` E-02 |
| Case | One Java Swing desktop calculator built twice (BMAD; GitHub Spec Kit), then evolved with the same change request (CR-01). | `[verified]` E-02 |
| Problem statement | How SDD tools support the transition from requirements to implementation and how well the result matches the requirements. | `[verified]` E-01 |
| Scope | Baseline four-operation calculator; CR-01 adds square, square root, reciprocal. Out of scope: other scientific functions, graphing, history, memory, persistence, networking. | `[verified]` E-03, E-05 |
| Method | Comparative multi-case study with one shared software case. | `[verified]` E-01 |
| Planned timeline in `ReadMe.md` | Report writing 30 Oct–3 Nov; review and finalise 4–6 Nov. This was set at project start; actual progress is behind it (Spec Kit CR-01 not started). | `[verified]` E-01, E-21 |

## B. Research Questions

Two sets exist in the repository. Which one is approved is `[TODO]` (also open in metric catalogue §9, item 6, E-27).

**Set 1 — `ReadMe.md` (initial plan; E-01).** Main: *How do SDD tools support the transition from requirements to implementation, and how well does the result match the original requirements?*

| ID | Question | Current evidence |
|---|---|---|
| RQ1 | How does each tool create and structure specifications (formats, detail, templates, role of the human)? | BMAD: PRD, architecture, epics, specs (E-10). Spec Kit: constitution, spec, plan, research, data-model, contracts, tasks (E-18). Comparison not yet done. |
| RQ2 | How are requirements turned into design, tasks, code, tests; is the link traceable? | Each track has a requirement-to-test table (E-11, E-19). Comparison not yet done. |
| RQ3 | How does each tool handle changes; does the spec stay aligned with the code? | BMAD CR-01 done (E-13, E-14). Spec Kit CR-01 not started (E-21). |
| RQ4 | How closely does the implementation satisfy the original requirements? | Both baselines report all 19 BASE-AC as Pass (E-11, E-19), unreviewed by an independent evaluator. |

**Set 2 — `project.md` §3 (working questions; E-02).** Thanh's literature review and metric catalogue cite this set as RQ1–RQ5 (E-26, E-27).

1. How well does each workflow turn the baseline requirements into traceable specifications, design decisions, tasks and tests?
2. How well does each workflow analyse and implement the same change request in an existing codebase?
3. Which requirements, design dependencies, tests or regressions are missed or discovered late?
4. What human interventions, elapsed time, tool interactions and rework are needed?
5. How reproducible and reviewable is the evidence the workflows produce?

Status of all questions: `[planned]` for analysis; no comparative finding has been produced.

## C. Group Members and Responsibilities

Role source: `project.md` §1 and §5, confirmed as current by George in LOG-09 (E-02, E-04). `ReadMe.md` lists different assignments (E-01); `[verified]` conflict, resolved by LOG-09 as "`ReadMe.md` is the initial plan".

| Member | Role (current) | Verified contributions (from repository records) | Hours |
|---|---|---|---|
| Varun | BMAD implementation track; shared-document owner | BMAD baseline (9 stories) and CR-01 (Epic 4); proposed LOG-01 to LOG-08, LOG-16 to LOG-21; authored CR-01 and `project.md` rules; coordination (E-04, E-10 to E-14, git log) | `[TODO]` |
| George | GitHub Spec Kit implementation track | Spec Kit installation, constitution, spec, plan, tasks, analyze, baseline implementation (81 tests); LOG-07 to LOG-09 replies; raised LOG-12 to LOG-15 (E-04, E-17 to E-20) | `[TODO]` |
| Thanh | Comparison frameworks, metrics, visualisations | `research/comparison-frameworks/`: README, literature review, metric catalogue, references, visualisation proposal (E-26 to E-28) | `[TODO]` |
| Amin | Empirical methodology and experiment protocol | `research/empirical-methodology/`: protocol, rubric proposal, templates, evidence checklist, validity threats, references, evaluation skill (E-22 to E-25) | `[TODO]` |

Git authorship check (`git shortlog`, E-30) shows commits by Varun, Georgios Malezoglou, Amin Lotfi / Mohammadamin Lotfi and Pham Thanh. Working hours and per-person effort are `[TODO]`. Do not infer effort from commit counts.

## D. Project Background and Concepts

Only material already in the repository is summarised. No new academic sources are introduced here.

- **Specification-driven development (SDD):** specifications are operational inputs to code-generating agents rather than only reference documents. Source: mid-term discussion plan (`discussion-forum/mid-term-discussion-thanh.md`, E-29) and Thanh's literature review (E-26). Spec-first, spec-anchored and spec-as-source are described as a spectrum of increasing spec authority (E-29). `[reported]` Citations to confirm: L1 Piskala, L2 EPIC, L4 Agarwal et al. (E-26).
- **BMAD Method:** installed version 6.12.4 (modules core, bmm). Native flow recorded by the BMAD track: Product Brief (optional) → PRD → UX (optional) → Architecture → Epics and Stories → Sprint Planning → Build → Code Review/QA (optional). Change handling via `bmad-correct-course`. `[verified]` E-09.
- **GitHub Spec Kit:** specify-cli 1.2.3, `claude` integration. Native flow used: constitution → specify → (clarify) → plan → tasks → analyze → implement. `[verified]` E-17.
- **Concepts used by the study:** requirements traceability, change-impact analysis, acceptance criteria, regression, human intervention and rework, evidence completeness, validity threats (construct, internal, external, conclusion). Definitions are in the metric catalogue and rubric proposal (`[draft]`, E-23, E-27).
- **Contrast case:** vibe coding is named in `ReadMe.md` and the mid-term plan but is not an experimental condition. `[verified]` E-01, E-29.

## E. Requirements and Experimental Design

### E.1 Baseline requirements (frozen 2026-10-23; E-03)

8 functional (`BASE-FR-01`..`08`) and 5 non-functional (`BASE-NFR-01`..`05`) requirements. Behaviour fixed by the experiment log: two operand fields, four operation buttons, Calculate and Clear, inline errors; Calculate button and Enter key; exact decimals, division half-up to 10 places, plain decimal text; messages "Enter both numbers", "Select an operation", "Invalid number", "Cannot divide by zero"; error priority empty → no operation → invalid → arithmetic. `BASE-NFR-02` has no numeric limit (LOG-14, observation only).

### E.2 Baseline acceptance criteria (E-05)

`BASE-AC-01`..`19`. Results are recorded as Pass, Fail, Blocked or Not run; Pass only if behaviour was observed. LOG-12, LOG-13, LOG-14, LOG-15 agreed 2026-11-03; clarifying cases added to `BASE-AC-11`, `12`, `18`. Four NFRs have no linked AC and three ACs have no requirement ID (noted by Thanh as shared-input limit C1; E-27) `[reported]`.

### E.3 Change request CR-01 (E-06)

Version 0.2, header still reads "Proposed — pending team approval" although its freeze checklist is ticked (LOG-16). Adds unary operations `x²`, `√x`, `1/x`: 11 requirements `CR-FR-01`..`11`; 22 acceptance criteria `CR-AC-01`..`22`, copied unchanged into `shared/acceptance-criteria.md` §3 on 2026-11-03. Key rules: First number field is used, Second number ignored; square exact; square root and reciprocal rounded half-up to 10 places; errors "Cannot take square root of a negative number" and "Cannot divide by zero"; `0`, `0.0`, `-0.0` all count as zero; `CR-AC-21` re-runs all baseline checks.
Clarifications (E-04): LOG-17 Second number ignored even if malformed; LOG-18 baseline error priority on First number only; LOG-19 tiny reciprocals round to `0`; LOG-20 Second number stays visible and enabled; LOG-21 keep "Enter both numbers" for empty input. Varun agreed all five for the BMAD track; **George's responses are pending** `[verified]` (log shows none).

### E.4 Experimental procedure (from `project.md` §6; E-02)

Phase 0 freeze protocol → Phase 1 independent baselines → Phase 2 freeze baselines → Phase 3 same CR-01 to both, pre-coding impact analysis, update artefacts, implement, regression → Phase 4 evaluate with a common rubric. Fairness rules: same inputs, no cross-track artefact sharing, native workflows intact, interventions recorded, deviations documented.

Actual chronology so far (git log, E-30): shared baseline frozen 2026-10-23; BMAD baseline 2026-10-23; Spec Kit baseline interim commit 2026-10-27 (freeze commit not recorded); CR-01 file committed 2026-10-30, released 2026-11-03; BMAD CR-01 commit `72f2783` 2026-11-03. The experimental protocol file is empty (E-07).

### E.5 Evaluation rubric and metrics — status `[draft]`, not adopted

- `shared/evaluation-rubric.md` and `shared/experiment-protocol.md`: empty (LOG-10, E-07).
- Amin's proposals: protocol (readiness, snapshots, review), rubric with ordinal judgments J1–J7 on 0–3 anchors (J1 specification clarity, J2 native traceability, J3 requirements-informed verification, J4 NFR treatment, J5 pre-coding impact analysis, J6 change propagation, J7 evidence and reproducibility; baseline uses J1–J4 and J7), templates, evidence checklist (E-22 to E-25).
- Thanh's metric catalogue: Tier 1 M01 acceptance outcomes, M03 native traceability, M04 defects, M06 regressions, M07 change-impact omissions/recall, M08 interventions, M09 active effort, M10 elapsed duration, M11 ratings, M12 rework; Tier 2 M02, M05, M07b, M13, M14, M15; Tier 3 M16 size/tokens/LOC as context only. Twelve team decisions listed in §9 are open (E-27).
- The skill `evaluate-sdd-workflows` supports preparation and, once both submissions are ready, evaluation (E-25). Its templates reference 13 requirement IDs and 16 draft ACs; frozen inputs have 19 baseline + 22 CR checks (to reconcile at freeze). `[verified]`

## F. Implementation Progress

### F.1 BMAD implementation (Varun)

- **Environment `[verified]` (E-09):** macOS 15.7.1 (arm64), Amazon Corretto 22.0.2, Maven 3.9.16 (`JAVA_HOME` pinned), Swing, JUnit Jupiter 6.1.3, BMAD 6.12.4, Claude Code with Sonnet 5.5 (`claude-sonnet-5-5`) acting as operator proxy.
- **Artefacts (E-10):** `bmad-calculator/_bmad-output/planning-artifacts/` (PRD FR-1..10 and NFR-1..5, architecture spine AD-1..10, `epics.md`, `sprint-change-proposal-2026-11-03.md`); `_bmad-output/implementation-artifacts/` (stories 1.1–4.4 specs, epic contexts, `sprint-status.yaml`, `deferred-work.md`); evidence 00–06.
- **Design (E-10):** layered core + presenter + Swing view; exact decimal (`BigDecimal`), HALF_UP; code in `bmad-calculator/src/calculator/`, tests in `bmad-calculator/tests/calculator/`.
- **Baseline `[verified]` (E-11):** 9 stories in 3 epics, tag `baseline-frozen`; last baseline run 172 tests (later 174 after LOG-15 cases), 0 failed on a display; headless run skips 57 window tests. All 19 BASE-AC Pass; AC-15 "Pass with limits" (clean copy, not a git clone).
- **CR-01 `[verified]` (E-12 to E-14):** impact analysis produced before code with `bmad-correct-course` (`bmad-calculator/evidence/05-cr01-impact-analysis.md`, sprint change proposal); Epic 4 (stories 4.1–4.4) approved by the owner, PRD/architecture/epics amended; implementation commit `72f2783`; display run **316 tests, 0 failed, 0 skipped**; headless run 80 skipped (window tests). Results per check in `06-cr01-acceptance-results-and-traceability.md`.
- **Deviations from native process `[verified]` (E-15):** fast-path PRD and architecture; UX skill skipped; reviewer subagents for PRD/architecture not run; readiness gate self-judged; stories 3.2/3.3 without full review layers; JUnit 6.1.3 instead of JUnit 5; standing approval replaces per-story checkpoint.
- **Stale records `[verified]`:** `bmad-calculator/evidence/00-STATUS.md` still says CR-01 changes are "uncommitted" and "Current phase: baseline"; `06-cr01-...md` says "Commit: none; branch `cr-01`" — HEAD is `72f2783` on `feature/cr-01-bmad`. To be corrected by the BMAD track, not by this document.
- **Outstanding `[TODO]`:** BASE-AC-15/CR-AC-22 not re-run from a real git clone; measured (not approximate) effort log; verbatim prompt log (status file says prompts not yet saved verbatim); George's replies to LOG-17..21; Varun's visual check noted in `deferred-work.md` (verify whether closed).

### F.2 GitHub Spec Kit implementation (George)

- **Environment `[verified]` (E-17):** Windows 11 Home, Amazon Corretto 22.0.2.9.1, Maven 3.9.16 (`JAVA_HOME` pinned), Swing, JUnit Jupiter 6.1.3, specify-cli 1.2.3, `claude` integration, `claude-sonnet-5-5`.
- **Artefacts (E-18):** `github-spec-kit-calculator/.specify/` (constitution v1.0.0), `specs/001-baseline-calculator/` (spec, plan, research, data-model, quickstart, contracts, tasks 34, checklists), `.claude/skills/speckit-*`; evidence 00–06 including prompt log P01–P12, approvals AP-01/02, decisions DL-01..DL-37, effort/intervention log.
- **Design `[verified]` (E-18):** Swing; `BigDecimal` HALF_UP; packages `calculator.core` and `calculator.ui`; Maven standard layout (deviates from `project.md` §4 `source/`/`tests/` names, decision D-08 approved).
- **Baseline `[reported]` (E-19):** 81 tests, 0 failures (headless); 19 of 19 BASE-AC Pass, with window screenshots and owner observations for GUI checks; AC-15 Pass from a clean clone of interim commit `419f92f` on 2026-10-27. Last status note says 33 of 34 tasks then 34 of 34 ticked. Freeze hash/tag `[TODO]`.
- **Spec Kit `analyze` findings `[verified]` (E-20):** 11 findings (0 critical, 2 high, 2 medium, 7 low), 21 of 21 requirements with tasks; fixes applied after owner approval.
- **Deviations `[verified]` (E-20):** constitution skill's template-resolve script not run; "Requirement Coverage" table added to the plan (not native); plan text claimed LOG-15 existed before it did (corrected); owner removed a sentence from the constitution; decisions DL-12/13 UI additions flagged "not a requirement".
- **CR-01 `[verified]` (E-21):** not started — no CR artefacts in the repository or on any branch; status file last updated 2026-10-27.
- **Outstanding `[TODO]`:** freeze commit/tag; LOG-16..21 replies; CR-01 impact analysis, implementation, tests; record whether the `checklists/requirements.md` line about LOG-14 stays out of date.

## G. Experimental Evidence

### G.1 Baseline acceptance results (as recorded by each track; not independently re-run)

| Item | BMAD (E-11) | Spec Kit (E-19) |
|---|---|---|
| BASE-AC Pass | 19/19 (AC-15 "Pass with limits") | 19/19 per later status entry; earlier table 18 Pass + 1 Blocked, then AC-15 Pass |
| Automated tests (display) | 172 → 174 (LOG-15) | 81 |
| Headless | 57 skipped window tests | headless tests only (window tests run on screenshots/owner) |
| Manual/owner-observed | AC-01 screenshots, Wi-Fi off reported | AC-01, Enter, `7 / 0.0`, speed owner-reported |
| Freeze | tag `baseline-frozen` | commit hash not recorded |

Test counts are not comparable across tracks (different test design, layers and skipped windows); do not compare them as quality (`[TODO]` state this in the report; metric M16 tier 3).

### G.2 Change-impact analysis

- BMAD: `bmad-calculator/evidence/05-cr01-impact-analysis.md` and the sprint change proposal: change local to `core/Operation`, `core/Calculator`, `core/ErrorKind`, `ui/CalculatorFrame`; presenter expected unchanged; `OperationTest` needs update; PRD, architecture AD-4/5/7/9 and `epics.md` need amendment. Whether it was complete is `[TODO]` until an agreed affected-element reference set exists (M07, E-27). `[verified]` E-12
- Spec Kit: none yet.

### G.3 Post-change results

- BMAD CR-01: results recorded for BASE-AC (CR-AC-21) and CR-AC-01..22 in `06-...md` (316 tests, 0 failed on display). The file header says the commit is "none"; use `72f2783`. I have not re-run the tests; `[reported]` E-14.
- Spec Kit: none.

### G.4 Artefact comparison and metrics

`[TODO]` — no comparative calculation exists. No rubric score has been assigned to either track (Thanh's and Amin's documents explicitly say no scoring yet). Per the rubric proposal, ordinal ratings are not summed. Preliminary descriptive data available: BMAD planning artefacts count (PRD, architecture, epics, 13 stories), Spec Kit tasks 34 and analyze findings 11 (`[reported]` — not a comparison).

## H. Research and Literature

All entries come from team registers and are `[draft]` until the cited source is confirmed. Reading scope as stated by the register author. No entries have been added to `references.bib`.

| Ref | Source (short) | Register label | Supports |
|---|---|---|---|
| L1 | Piskala, "Spec-Driven Development: From Code to Contract...", arXiv:2602.00180 | Opened (abstract, opening) | Background (SDD concept); RQ1 |
| L2 | Claudino et al., "The EPIC Framework for SDD", arXiv:2610.07534 | Opened (full text), peer review not established | Background; RQ1, RQ2 |
| L3 | Hill, "Specification Artifacts in Open-Source Pull Request Workflows Do Not Reduce Defects...", ESEM 2026 (LIPIcs 394, 84) | Opened (abstract, reference list) | Limits of SDD claims; RQ4 |
| L4 | Agarwal et al., "Spec-Driven Development as Prescribed and as Practiced", arXiv:2608.25202 | Opened (partial) | Practice vs prescription; RQ1, RQ3 |
| L5 | Basili et al., Goal Question Metric approach (1994) | Record | Metric design |
| L6 | Cleland-Huang et al., "Software traceability: Trends and future directions" (FOSE 2014) | Record | Traceability; RQ2 |
| L7, L8, L14 | Impact-analysis surveys and mapping studies (Li et al. 2013; Gentili et al. 2024; Musco et al. 2017) | Record / Opened (L14 excerpt) | Change impact; RQ3 |
| L9 | Femmer et al., requirements smells (JSS 2017) | Record | Spec quality; RQ1 |
| L10 | ISO/IEC/IEEE 29148:2018 | Ref-only | Requirements quality (not opened) |
| L11 | Inozemtseva and Holmes, coverage and effectiveness (ICSE 2014) | Record | Why test counts are not quality |
| L12 | Frattini et al., requirements quality research (RE journal 2023) | Ref-only | Quality theory |
| L13 | Ouyang et al., non-determinism of ChatGPT in code generation (TOSEM 2025) | Record | Validity (LLM nondeterminism) |
| M1 | Runeson and Höst, case study guidelines (EMSE 2009) | Opened (parts) | Method; validity |
| M2 | Kitchenham et al., preliminary guidelines (TSE 2002) | Opened (parts) | Measurement |
| M3 | ACM SIGSOFT Empirical Standards | Opened (parts) | Reporting |
| M4, M5 | Traceability conference paper; AI-assisted development evaluation preprint | Record (abstract) | Traceability; context |

Full bibliographic details are in `research/comparison-frameworks/references.md` and `research/empirical-methodology/references.md` (E-26, E-24). The register notes that the course requires IEEE style and that a source must be confirmed before citing. `[TODO]` verify each source before it enters `references.bib`; two of the 2026 entries are preprints.

## I. Findings and Observations

Nothing in this section is a comparative research finding yet.

**Verified findings (from repository records):**
1. The shared baseline had ambiguities that both operators had to resolve through the log: no-operation behaviour (LOG-05), negative-tie rounding (LOG-15), `.5`/`5.` classification (LOG-12), when results clear (LOG-13). Both tracks independently surfaced gaps (BMAD LOG-05; Spec Kit LOG-12..15). (E-04)
2. CR-01 had gaps found only when the BMAD track read it (LOG-16..21); CR-01 status line and the empty rubric/protocol files were out of sync with the freeze checklist. (E-04, E-06, E-07)
3. Both tracks record documentation drift between logs and the actual repo state (BMAD status stale after CR-01 commit; Spec Kit plan claimed a log entry that did not exist yet). (E-10, E-20)

**Preliminary observations (single run, single operator each; `[reported]`):**
- BMAD planning artefacts and review layers produced many items needing triage; roughly half of reviewer findings were false or rejected (F-07 in BMAD status file, E-10).
- BMAD's planning slicing made Epic 2 stories mostly verification (F-06).
- Spec Kit `analyze` found coverage gaps before coding that were fixed (E-20).
- The BMAD change workflow (`bmad-correct-course` → Epic 4 → `bmad-build`) produced an impact analysis before code.

**Interpretations (not yet supported by comparison):** none to be written until Spec Kit CR-01 finishes and the rubric is adopted.

**Unresolved questions:** which RQ set; whether George agrees LOG-17..21; whether rubric/protocol adoption postdates BMAD CR-01 work (disclosure needed); how to count tests across tracks; reference set for M07; independent reviewers.

## J. Limitations and Threats to Validity

Source: Amin's `validity-threats.md` (E-24) and observations above.

- **Tool, agent and operator confounded:** one operator per tool; different OS (macOS vs Windows 11); same model name is accepted as design but hidden service behaviour may differ (E-24).
- **Single run per workflow:** no estimate of LLM run-to-run variability (E-24).
- **Ceiling effect:** both baselines report all checks passing; a small, well-specified calculator limits discriminating power (observation from E-11, E-19).
- **Evidence asymmetry:** BMAD track has the CR-01 phase; Spec Kit has not; Spec Kit freeze hash not recorded; neither prompt log is verbatim for the whole run (BMAD says so, Spec Kit P01–P08 were reconstructed) (E-10, E-20).
- **Approximate effort:** BMAD intervention log is approximate (E-10); no measured active-time record found in either track `[TODO]`.
- **Staged chronology:** rubric and protocol adopted after baseline work; BMAD saw CR-01 before the rubric was frozen (E-07, E-04).
- **Deviation from native workflows:** fast-path and skipped steps in BMAD; added coverage table in Spec Kit (E-15, E-20).
- **Skipped GUI tests in headless runs:** a skipped test is not a pass; results rely on display runs and owner observation (E-11, E-14).
- **Operator is also the study owner:** Varun owns shared documents, writes CR-01 and operates BMAD (potential bias; mitigation via log and independent review `[TODO]`).
- **External validity:** one small application, one change request, two tools.
- **Possible metric problems:** requirement counts used in drafts are 13 vs the frozen 8+5; mixed AC counts in templates (E-25).

## K. Work Division and AI Usage

**Individual contributions:** see section C. Only contributions visible in repository files or git history are listed. `[TODO]` Each member to supply a short statement of contribution and actual hours.

**Working hours:** `[TODO]` not recorded in a comparable form. BMAD status file has an approximate session log for 2026-10-23 (start 16:54 EEST) (E-10). Spec Kit has `05-effort-intervention-log.md` (E-18); not yet analysed here. No hours for Thanh or Amin.

**AI tools used (recorded):**

| Where | Tool / model | Use | Source |
|---|---|---|---|
| BMAD track | Claude Code, Claude Sonnet 5.5 (`claude-sonnet-5-5`) acting as operator proxy; BMAD 6.12.4 skills; subagents for review layers | Planning artefacts, implementation, tests, evidence drafting | E-09, E-10 |
| Spec Kit track | Claude (`claude` integration), `claude-sonnet-5-5`; Spec Kit 1.2.3 skills | Constitution, spec, plan, tasks, analyze, implementation, evidence | E-17, E-18 |
| Research | AI assisted source discovery and drafting (stated in Thanh's references header) | Literature register | E-26 |
| Research | Evaluation skill `evaluate-sdd-workflows` | Evaluation preparation | E-25 |
| This workspace | Claude Code | Creation and maintenance of this document | this file |

**Human verification:** the BMAD and Spec Kit status files record owner approvals and observations (BMAD interventions 1–9; Spec Kit AP-01/02, owner-run screenshots). Any statement that the report text was human-checked is `[TODO]`. The AI usage declaration wording required by the course is `[TODO]`.

## L. Missing Information and TODOs

**Submission inputs**
- [ ] Group name.
- [ ] The official COMP.SE.130 Group Work Review Report checklist file (not in the repository; the requirements in the owner's task text are listed below).
- [ ] Peer-review requirements, deadlines and attendance details; keep separate from the 16-point report mark.
- [ ] Course AI-usage declaration format.
- [ ] Which research-question set is approved.

**Study**
- [ ] Spec Kit CR-01: impact analysis before code, implementation, tests, results.
- [ ] Spec Kit baseline freeze commit/tag.
- [ ] George's replies to LOG-17..21; update CR-01 header status.
- [ ] Freeze and adopt rubric and protocol (record the adoption date); fill `shared/evaluation-rubric.md` and `shared/experiment-protocol.md`.
- [ ] Reconcile templates/skill to 8 FR + 5 NFR and BASE-AC-01..19 + CR-AC-01..22.
- [ ] Agreed affected-element reference set for M07.
- [ ] Decide independent reviewers and exposure declaration.
- [ ] Measured active effort and elapsed time per phase for both tracks.
- [ ] Saved verbatim prompt logs (or a stated limitation).
- [ ] Correct stale BMAD status lines (commit hash and branch).
- [ ] Decide whether extra change requests or repeat runs are in scope (conclusion validity).

**Report**
- [ ] Verify every source in section H before adding to `references.bib` (IEEE style).
- [ ] Decide on 2 to 3 figures (candidate: requirements coverage matrix, timeline, rating table) and store in `figures/` once approved.
- [ ] Individual contribution statements and hours.
- [ ] Summary/abstract and peer-review evidence.

**Official submission requirements (as given in the owner's task text; not checked against the course document)**
- Main report at most 10 pages; references at most 2 pages; formal academic English; critical analysis; evidence-backed conclusions.
- Points: key concepts and background 4; analysis and reflective discussion of research questions 6; findings 3; logical and complete structure 2; work division and timelines 1 (16 in total).
- Attendance and peer review are assessed separately and are not part of the 16 report points; also account for an overall summary and an AI-usage declaration where applicable.

**Suggested report approach (proposal, not decided):** descriptive comparative case study of two workflows; lead with requirements-engineering findings (gap discovery through the log, traceability, pre-coding impact analysis, interventions, deviations) rather than pass counts; disclose chronology and confounds; use 2 to 3 compact tables/figures; allocate roughly 2 pages background, 1.5 method, 3 analysis of RQs, 1.5 findings, 1 for structure, work division, timeline and AI declaration.

## M. Evidence Index

Verification: `Read` = file opened during the 2026-11-03 inspection; `Listed` = existence/size or git metadata only; none = re-run of tests (no test was re-run by this document).

| ID | Path | Description | RQ | Report section | Verification |
|---|---|---|---|---|---|
| E-01 | `ReadMe.md` | Project overview, RQ set 1, method, roles (initial), timeline | All | Intro, method | Read |
| E-02 | `project.md` | Study guide, roles, RQ set 2, workflow, fairness rules | All | Method | Read |
| E-03 | `shared/baseline-requirements.md` | Frozen baseline requirements | RQ1, RQ4 | Method | Read |
| E-04 | `shared/experiment-log.md` | LOG-01 to LOG-21, freeze records | RQ1, RQ3 | Findings, method | Read |
| E-05 | `shared/acceptance-criteria.md` | BASE-AC-01..19, CR-AC-01..22, result template | RQ4 | Method | Read |
| E-06 | `shared/change-request-CR-01.md` | CR-01 v0.2 | RQ2, RQ3 | Method | Read |
| E-07 | `shared/evaluation-rubric.md`, `shared/experiment-protocol.md` | Both empty (0 bytes) | RQ5 | Limitations | Listed |
| E-09 | `bmad-calculator/evidence/01-environment.md` | BMAD environment and versions | RQ5 | Method | Read |
| E-10 | `bmad-calculator/evidence/00-STATUS.md`, `02-decisions-log.md`, `_bmad-output/` | BMAD resume file, deviations, findings F-06..09, decisions D-01..07, artefacts | RQ1, RQ4 | Cases, findings | Read (status); others Listed |
| E-11 | `bmad-calculator/evidence/04-acceptance-results-and-traceability.md` | Baseline AC results, requirement-to-test table | RQ2, RQ4 | Results | Read |
| E-12 | `bmad-calculator/evidence/05-cr01-impact-analysis.md`; `_bmad-output/planning-artifacts/sprint-change-proposal-2026-11-03.md` | Pre-coding CR-01 impact analysis | RQ3 | Results | Read (05); proposal Listed |
| E-13 | `bmad-calculator/_bmad-output/implementation-artifacts/spec-4-*.md`; `epic-4-context.md` | Epic 4 story specs | RQ3 | Cases | Listed |
| E-14 | `bmad-calculator/evidence/06-cr01-acceptance-results-and-traceability.md`; `story-4-*-test-log.txt` | CR-01 AC results, 316 tests | RQ3, RQ4 | Results | Read (06); logs Listed |
| E-15 | `bmad-calculator/evidence/00-STATUS.md` (deviations section) | BMAD deviations from native process | RQ1, RQ5 | Limitations | Read |
| E-17 | `github-spec-kit-calculator/evidence/00-STATUS.md`, `01-environment.md` | Spec Kit resume file and environment | RQ5 | Method | Read (00); 01 Listed |
| E-18 | `github-spec-kit-calculator/specs/001-baseline-calculator/`, `.specify/`, `evidence/02..05` | Spec Kit artefacts, approvals, decisions, prompt and effort logs | RQ1, RQ2 | Cases | Listed |
| E-19 | `github-spec-kit-calculator/evidence/06-test-results.md` | Baseline AC results and traceability table | RQ2, RQ4 | Results | Read |
| E-20 | `github-spec-kit-calculator/evidence/00-STATUS.md` (analyze, deviations) | `/speckit-analyze` findings and deviations | RQ1, RQ5 | Findings | Read |
| E-21 | `github-spec-kit-calculator/evidence/00-STATUS.md`; `git branch -a` | No Spec Kit CR-01 phase; status last updated 2026-10-27 | RQ3 | Limitations | Read; branches Listed |
| E-22 | `research/empirical-methodology/protocol-recommendations.md` | Proposed protocol | RQ5 | Method | Listed (headings read) |
| E-23 | `research/empirical-methodology/evaluation-rubric-proposal.md`, `evaluation-templates.md`, `evidence-checklist.md` | Rubric J1–J7, templates, checklist | RQ1–RQ5 | Method | Read (rubric partly) |
| E-24 | `research/empirical-methodology/validity-threats.md`, `references.md`, `methodology-review.md` | Validity register, method references | RQ5 | Limitations | Read (partly) |
| E-25 | `research/empirical-methodology/skills/evaluate-sdd-workflows/SKILL.md` | Evaluation skill | RQ5 | Method | Read |
| E-26 | `research/comparison-frameworks/literature-review.md`, `references.md` | Literature review and source register | RQ1–RQ5 | Background | Read (partly) |
| E-27 | `research/comparison-frameworks/metric-catalogue.md` | Metrics M01–M16, open decisions | RQ1–RQ5 | Method | Read (tiers, §9) |
| E-28 | `research/comparison-frameworks/visualisation-proposal.md` | Visualisation proposal | — | Figures | Listed |
| E-29 | `discussion-forum/mid-term-discussion-thanh.md` | Mid-term plan with key concepts | RQ1 | Background | Read (partly) |
| E-30 | git history (`git log --all`, `git shortlog`, tags) | Authorship, chronology, HEAD `72f2783`, tag `baseline-frozen` | RQ5 | Timeline | Read |
| E-31 | `research/final-analysis/results.md`, `discussion.md`, `final-report.md` | Empty placeholders | — | — | Listed |
