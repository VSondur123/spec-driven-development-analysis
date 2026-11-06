# Evidence inventory — final-report

Status date: 2026-11-06. All paths are relative to the repository root unless noted. "Read" means the file was opened in this project; nothing was re-run, no test was executed, and no application was launched.

## Owner decisions recorded
- Plan approved (state engine: `plan_approval: current`).
- Q1: authors are Varun Ganesh Sondur, Mohammadamin Lotfiourimi, Pham Thanh, Georgios Malezoglou; affiliation Tampere University.
- Q2, Q3, Q4, Q6: the owner accepted the recommended defaults, namely RQ1–RQ4 from `ReadMe.md`; qualified findings with no winner claim; 4 figures and 6 tables; web verification of sources from the team registers only.
- Q5: no appendix. Q7, Q8: no work-division, timeline, hours or protocol-adoption-date content. Q9: the AI-use declaration is drafted from recorded tools; the owner checks it against the course wording.
- Exception to `CLAUDE.md` ("do not read the other track's artefacts"): both tracks' evidence is read for this report under the owner's instruction. Read-only; nothing in either track was changed.

## Sources
| ID | Location | Type | Primary/Secondary | Topic / sections | Citation key | Limitations |
|----|----------|------|-------------------|------------------|--------------|-------------|
| S01 | `ReadMe.md` | Project brief | Primary | Intro, Method (RQ1–4, criteria, team) | – | Initial plan; roles superseded by `project.md` (LOG-09) |
| S02 | `final-submission/project-context.md` | Living fact sheet (dated 2026-11-03) | Secondary | Method, Tools, Limits | – | **Stale** for Spec Kit CR-01, rubric/protocol, evaluations; see Conflicts C1 |
| S03 | `shared/baseline-requirements.md`, `shared/acceptance-criteria.md`, `shared/change-request-CR-01.md` | Shared requirements | Primary | Method (case, counts) | – | Counts taken from `results.md` §1 and S02; the files themselves were not re-counted |
| S04 | `shared/experiment-log.md` | Decision log (LOG-01..27) | Primary | Method, Results (LOG-22..27 evaluations, snapshots) | – | LOG-25/26 George reply blank |
| S05 | `shared/experiment-protocol.md`, `shared/evaluation-rubric.md` | Protocol and rubric | Primary | Method | – | Non-empty now (18 KB, 21 KB); only sizes checked, content not read in full |
| S06 | `research/final-analysis/results.md` | Consolidated results (2026-11-06) | Secondary (team synthesis of S08–S10) | Results | – | Synthesis; "not a new test run" |
| S07 | `research/final-analysis/final-report.md`, `Baseline_vs_Change_Analysis_Report.md` | Team analysis | Secondary | Results, Discussion | – | Second file listed, not read |
| S08 | `research/comparison-frameworks/Experiment 1/…Report.md` and `.xlsx` | Baseline comparison | Primary/Secondary | Results | – | Report and workbook not read; reached through S06 only |
| S09 | `research/comparison-frameworks/Experiment 2/…FINAL.md` and `.xlsx` | CR-01 comparison | Primary/Secondary | Results | – | Same as S08 |
| S10 | `research/comparison-frameworks/metric-catalogue.md`, `literature-review.md`, `references.md` | Metric catalogue, literature register | Secondary | Method, Background | – | Draft register; AI-assisted; reading scope per entry |
| S11 | `research/empirical-methodology/references.md`, `validity-threats.md` | Method register, validity register | Secondary | Method, Discussion | – | `validity-threats.md` not read (reached through S02, S07) |
| S12 | `bmad-calculator/evidence/00-STATUS.md`, `01-environment.md` | BMAD resume and environment | Primary | Tool Descriptions, Method | – | `00-STATUS.md` is partly stale (C3) |
| S13 | `github-spec-kit-calculator/evidence/00-STATUS.md`, `01-environment.md` | Spec Kit resume and environment | Primary | Tool Descriptions, Results | – | Environment file lists GUI toolkit "not chosen yet"; later entries record Swing |
| S14 | `bmad-calculator/.agents/skills/` and `github-spec-kit-calculator/.claude/skills/` | Installed skill directory names | Primary | Tool Descriptions | – | Names only; skill bodies not read |
| S15 | git tags and log | Repository history | Primary | Method (snapshots) | – | Tag commits, resolved with `git rev-list -n 1`: `speckit-baseline-frozen` 88f56d0, `speckit-cr01-frozen` 9e52377; `baseline-frozen` 740c78d and `bmad-cr01-frozen` 72f2783 as recorded in LOG-24/LOG-26 (the first `git tag -l` listing showed annotated-tag object ids for the Spec Kit tags, not commits; corrected) |
| S16 | `github.com/github/spec-kit`, `github.com/bmad-code-org/BMAD-METHOD` | Tool documentation (grey literature) | Primary for tool descriptions | Tool Descriptions | `speckit`, `bmad` | Fetched 2026-11-06; a summary of the page was read, not the full repository. Current README may differ from the installed versions |
| S17 | arXiv 2602.00180; 2610.07534; 2608.25202 | Preprints | Secondary | Background | `piskala2026`, `claudino2026`, `agarwal2026` | Abstract pages fetched 2026-11-06; full texts not read; peer review not established |
| S18 | LIPIcs ESEM 2026, art. 84 (DOI 10.4230/LIPIcs.ESEM.2026.84) | Proceedings paper | Secondary | Background | `hill2026` | Abstract and metadata page read, not the PDF |
| S19 | Runeson and Höst, EMSE 14, 2009 | Journal article | Secondary | Method | `runeson2009` | Metadata confirmed by search catalogues; publisher page returned a redirect, article not opened here |
| S20 | Kitchenham et al., TSE 28(8), 2002 | Journal article | Secondary | Method | `kitchenham2002` | Metadata confirmed by search catalogues; IEEE page not opened |
| S21 | Basili, Caldiera, Rombach, Encyclopedia of Software Engineering, 1994 | Reference-work chapter | Secondary | Method (GQM) | `basili1994` | Metadata from the author's publication list; text not read |

## Evidence items
Kind ∈ observed | source-derived | interpretation | recommendation. Status ∈ verified | needs-check | conflict | gap. "verified" here means read in the cited source, not re-run.

| ID | Source | Extract / finding | Kind | Supports claim | Section | Status |
|----|--------|-------------------|------|----------------|---------|--------|
| E01 | S01 | Main RQ and RQ1–RQ4 wording; nine comparison criteria; team roles | source-derived | RQs and criteria | Method | verified |
| E02 | S06 §1, S02 §E | Baseline: 8 FR + 5 NFR = 13 requirement IDs, 19 parent ACs; CR-01: 11 CR-FR, 22 CR-AC, 19 baseline parents re-checked | source-derived | Case size | Method | verified (counts as stated in S06; S02 agrees on 8+5, 19, 11, 22) |
| E03 | S06 §2.1 | Baseline independent: BMAD 19 Pass; Spec Kit 18 Pass, 1 Blocked (offline); operator-reported 19/19 both | source-derived | Baseline outcome | Results | verified in S06; S04 LOG-23 agrees |
| E04 | S06 §3.1; S04 LOG-27 | CR-01: 22/22 Pass reported both; regression 19/19 reported both | source-derived | CR-01 outcome | Results | verified in S06; BMAD from LOG-27 |
| E05 | S06 §4.2 | Native tests: BMAD 172→316 (174-test baseline run also exists; CR headless run skipped 80); Spec Kit 81→116 | source-derived | Context only | Results | verified in S06; S13 confirms 81 and 116 |
| E06 | S06 §3.2 | CR impact: Spec Kit forecast 5 changed + 5 unchanged files matched, later unplanned work (constitution update, `OperationTest.java`, `errorAreaSize` helper); BMAD later unplanned GUI operation-button tests and `assertInitialState` helper | source-derived | Change impact | Results | verified in S06; Spec Kit side consistent with S13 |
| E07 | S06 §3.2; S13 | Spec Kit product defect: negative square-root error text clipped, found by visual review, fixed (D-CR-01, DL-57); BMAD: `1e41`/`1e40` test-input issue, test-only patches, Javadoc correction | source-derived | Defects/rework are different units | Results | verified (S13 for Spec Kit); BMAD items via S06 only |
| E08 | S06 §5 | M01 partly calculable; M03, M07, M08–M10, M11–M15 not calculable or illustrative; M06 zero reported regressions; M16 context only | source-derived | Metric disposition | Results/Method | verified |
| E09 | S12 | BMAD environment: macOS 15.7.1 arm64, Corretto 22.0.2, Maven 3.9.16, Swing, BMAD 6.12.4 (core, bmm), Claude Code, `claude-sonnet-5-5`, `JAVA_HOME` pinned | observed | Environment | Tool Descriptions | verified |
| E10 | S13 | Spec Kit environment: Windows 11 Home, Corretto 22.0.2.9.1, Maven 3.9.16, specify-cli 1.2.3, `claude` integration, model `claude-opus-5-5` at start then `claude-sonnet-5-5` | observed | Environment | Tool Descriptions | verified |
| E11 | S12 | BMAD flow used: PRD (fast path) → architecture spine (AD-1..10) → epics (3 epics, 9 stories) → sprint planning → build with 3 review layers → CR-01 via `bmad-correct-course` → Epic 4 (stories 4.1–4.4) → `bmad-build` | observed | BMAD description | Tool Descriptions | verified |
| E12 | S13 | Spec Kit flow used: constitution → specify (3 clarifications) → plan → tasks (34 baseline tasks) → analyze (11 findings: 0 critical, 2 high, 2 medium, 7 low) → implement; CR-01 as feature `002-scientific-operations`, 49 tasks, analyze 10 findings | observed | Spec Kit description | Tool Descriptions | verified |
| E13 | S12 | BMAD deviations: fast-path PRD/architecture, UX skipped, PRD/architecture reviewer subagents not run, readiness gate self-judged, stories 3.2/3.3 without full review layers, JUnit 6.1.3 | observed | Deviations | Tool Descriptions, Discussion | verified |
| E14 | S13 | Spec Kit deviations: `resolve-template` script not run for constitution/spec, added "Requirement Coverage" table to the plan (non-native), plan claimed LOG-15 before it existed, sentence removed from constitution by the owner | observed | Deviations | Tool Descriptions, Discussion | verified |
| E15 | S12 | Review layers produced findings of which about half were false or rejected (F-07); Epic 2 stories mostly verification (F-06) | observed | Process observation | Results | verified; BMAD-only, single run |
| E16 | S16 | Spec Kit documented commands: `/speckit-constitution`, `-specify`, `-plan`, `-tasks`, `-implement`, `-converge`; bug-fix and assessment extensions. BMAD documented: delivery loop Clarify, Plan, Build and verify, Learn and adjust; `bmad-build`, `bmad-help`; modules; MIT licence | source-derived | What the tools are | Tool Descriptions | verified (summary of page) |
| E17 | S17 | Piskala: spec-first, spec-anchored, spec-as-source (three levels of rigour) | source-derived | SDD vocabulary | Background | verified (abstract) |
| E18 | S17 | EPIC: 40 practices, 10 dimensions, 114 repositories scored against ISO/IEC/IEEE 29148; Agarwal et al.: 21 interviews, 470,795 spec files from 266 repositories; findings on staleness and late spec-code checks | source-derived | Related work | Background | verified (abstracts) |
| E19 | S18 | Hill: 88,052 PRs in 119 repositories; specified vs unspecified PRs no meaningful difference in defects after matching; artefacts were mostly issue/ticket references; effect of adopting SDD tools untested | source-derived | Caution on SDD claims | Background | verified (abstract) |
| E20 | S11, S21, S19, S20 | Case-study guidance, measurement guidance, GQM | source-derived | Method design | Method | metadata verified; texts not read |
| E21 | S02 §K | AI tools recorded: Claude Code + `claude-sonnet-5-5` on both tracks; BMAD 6.12.4 skills with review subagents; AI-assisted source discovery and drafting (registers S10, S11); `evaluate-sdd-workflows` skill; Codex assistance in Spec Kit baseline and CR-01 evaluation (LOG-23, S04); this report workflow | source-derived | AI declaration | AI-Use Declaration | verified (as recorded); human-check statements limited to what logs record |
| E22 | S04 LOG-23, LOG-27 | Independent evaluators: Thanh (BMAD baseline, BMAD CR-01), Amin (Spec Kit baseline, with Codex; Spec Kit CR-01 per commits) | source-derived | Evaluation design | Method | verified for LOG entries; Spec Kit CR-01 evaluator named only in commit messages |

## Conflicts (not reconciled; owner decision requested where marked)
- **C1 — `project-context.md` (S02, 2026-11-03) vs newer repository files.** S02 says Spec Kit CR-01 not started, rubric/protocol files empty, no independent evaluation, no comparison. Newer files show Spec Kit CR-01 complete (tag `speckit-cr01-frozen`, 116 tests, 22/22 reported), non-empty protocol and rubric, independent evaluations (LOG-22..27), and consolidated results (S06, S07). The draft uses the newer files. S02's [reported]/[verified] tags are respected only for facts not superseded.
- **C2 — Research-question sets.** Three sets exist. Owner accepted `ReadMe.md` RQ1–4. The `final-analysis` RQ1–5 ("which performed better…") are mapped onto these in the draft, not adopted.
- **C3 — Stale status lines.** `bmad-calculator/evidence/00-STATUS.md` still says CR-01 is uncommitted and "Current phase: baseline"; LOG-24 and the tag `bmad-cr01-frozen` show commit 72f2783. Spec Kit status file says "Not read: BMAD track", written before this report. The draft uses LOG-24.
- **C4 — Baseline outcome by layer.** Operators: 19/19 both. Independent: BMAD 19; Spec Kit 18 + 1 Blocked. Reported separately; not merged. BMAD's offline Pass lacks proof of disabled networking (S06).
- **C5 — Spec Kit CR-01 offline check.** Initially Blocked, later Pass by a human-reported follow-up (S06 §3.1). Both statuses shown.
- **C6 — Test counts.** BMAD 172 (S12 original) vs 174 (after LOG-15 cases) vs 316 (CR-01 display run); Spec Kit 81 vs 116. Different test designs and environments; context only.
- **C7 — Spec Kit baseline snapshot.** Operator results were recorded at 419f92f; the frozen tag `speckit-baseline-frozen` points to 88f56d0 (checked with git), which LOG-26 also designates for the evaluator run. LOG-26 itself notes that differences between 419f92f and 88f56d0 may exist and that George did not declare the freeze. The draft states this as a limitation. (An earlier suspected mismatch with other hashes was my misreading of annotated-tag object ids and is withdrawn.)
- **C8 — Spec Kit CR-01 snapshot.** Tag `speckit-cr01-frozen` points to 9e52377 (checked with git), consistent with S13. The tag is local and not pushed per S13.
- **C9 — Model on Spec Kit track.** S13 records `claude-opus-5-5` at session start, then `claude-sonnet-5-5`; S02 lists only Sonnet 5.5. The draft states both, as recorded in S13.
- **C10 — Metric counts.** Baseline "13 requirement IDs" vs earlier "13 + 16 AC" template counts in S02 §E.5; the draft uses 8+5 and 19, 11 and 22.

## Gaps (marked `[[EVIDENCE NEEDED]]` in the draft only where a claim depends on them)
- G1 — Full text of the three preprints, Hill's PDF and the three method sources was not read; claims are limited to abstracts and metadata.
- G2 — Semantic traceability coverage (M03/M13) is not calculated by either track, so no coverage matrix can be drawn.
- G3 — Active effort, elapsed time, intervention counts, rework rates (M08–M10, M12), impact recall (M07), rubric ratings (M11), reviewer agreement (M14), evidence completeness (M15) are not calculable.
- G4 — Content of S05, S08, S09, S14 skill bodies and the BMAD CR-01 independent evaluation files were not read in full. Statements about them rest on S06/S07.
- G5 — The course AI-use declaration wording and the group name are not in the repository.
- G6 — The BMAD CR-01 independent evidence lacks a recorded frozen checkout and criterion-level raw observations (S06 §3.1).
