# STATUS / RESUME POINT — Varun / BMAD Track (Baseline only)

**Last updated:** 2026-10-23 (EEST), Epic 3 built; baseline implementation complete
**Current phase:** All 9 stories built (sprint-status: all `review`). Story 3.1 had the 3 review layers; **3.2 and 3.3 skipped them at Varun's request (minimal version)**. Acceptance results and traceability: `evidence/04-acceptance-results-and-traceability.md`.
**Tests:** display 172 run, 0 failed, 0 skipped; headless 57 skipped (window tests). Log: `evidence/story-3-3-final-test-log.txt`.
**Git:** branch `bmad-baseline`. The single Epic 3 commit covers everything since `0ace55c`; commit `ebac834` (hash recorded in file 04). Nothing pushed.
**Still needed from Varun:** repeat BASE-AC-01 (Wi-Fi off, `mvn -q compile exec:java`, screenshot of the final window) and save it in `evidence/`; George's answers to LOG-01..LOG-11. Then freeze/tag only when Varun says; stop and wait for CR-01 release.
**Hard stop rules:** (1) No code until plan approved. (2) Baseline only: do NOT read or implement `shared/change-request-CR-01.md`. (3) Do not edit `shared/*` or look at George's track.

## How to resume in a new session

Tell the agent: *"Read `bmad-calculator/evidence/00-STATUS.md` and continue from the Next step."* It should then read `bmad-calculator/README.md`, `shared/baseline-requirements.md`, `shared/acceptance-criteria.md`, and the files in this `evidence/` folder plus the planning artefacts below.

## Done

| # | Item | Where |
|---|---|---|
| 1 | Read shared docs, project.md, ReadMe.md, track README | — |
| 2 | Environment/version record (macOS 15.7.1, Corretto 22.0.2, Maven 3.9.16, BMAD 6.12.4, Claude Sonnet 5.5) | `evidence/01-environment.md` |
| 3 | Decisions D-01…D-07 (all PROPOSED; D-06 confirmed; D-07 "Select an operation" chosen by Varun) + ambiguities A-01…A-04 | `evidence/02-decisions-log.md` |
| 4 | **PRD** (`bmad-prd`, fast path), status final. FR-1…10, NFR-1…5, UJ-1…4 | `_bmad-output/planning-artifacts/prds/prd-bmad-calculator-2026-10-23/` (`prd.md`, `addendum.md`, `.memlog.md`) |
| 5 | **UX** step skipped (optional; layout fixed by D-01 / AD-9) | memlog of architecture |
| 6 | **Architecture spine** (`bmad-architecture`, fast path), status final, linter: 0 findings. AD-1…AD-10; layered functional core + presenter + Swing view | `_bmad-output/planning-artifacts/architecture/architecture-bmad-calculator-2026-10-23/ARCHITECTURE-SPINE.md` |
| 7 | **Epics and stories** (`bmad-create-epics-and-stories`): 3 epics, 9 stories, status final | `_bmad-output/planning-artifacts/epics.md` |
| 8 | **Readiness gate** (judged by agent): PASS with caveats below; one gap (NFR-2 had no AC) fixed. **Sprint planning** run: `sprint-status.yaml` generated, 9 stories all `backlog` | `_bmad-output/implementation-artifacts/sprint-status.yaml` |

| 9 | Shared-impact decisions logged for George's agent in `shared/experiment-log.md` (LOG-01…LOG-11, all Proposed). **Written but NOT committed; waiting for Varun's approval before committing that file only.** Proposals were ALSO applied (tagged `[PROPOSED LOG-nn]`, uncommitted) in `shared/baseline-requirements.md` and `shared/acceptance-criteria.md` (new BASE-AC-17/18/19). Plan artefacts (PRD/epics) already assume these, incl. AC-17…19 which epics.md does not yet reference; update if the team changes anything. | `shared/*` |

## Deviations from BMAD native process (record for the study)

- PRD and architecture used **fast path**, not coaching (Varun delegated to recommendations).
- PRD reviewer gate / input-reconciliation subagents and architecture reviewer-gate subagents **not run** (hobby/study stakes). Architecture linter was run; technology versions were checked manually on Maven Central.
- UX skill skipped.
- Story 3.3 was executed without a separate `spec-3-3-*.md`, and 3.2/3.3 without the 3 review subagents, at Varun's request for a minimal path; results are in `evidence/04-acceptance-results-and-traceability.md`.
- D-05 amended: JUnit Jupiter **6.1.3** (current) instead of "JUnit 5".
- Agent self-judged the sprint readiness gate (no independent reviewer).

## Caveats / open items for the team

- D-01…D-05 and D-07 are not yet agreed with George; shared docs still not frozen; `experiment-protocol.md`, `evaluation-rubric.md`, `experiment-log.md` empty. D-07 amends shared behaviour (new error message).
- PRD open questions: Enter from operation buttons? (OQ-3); how to evidence BASE-AC-01 offline (OQ-2); input grammar sign-off (OQ-4).
- Story 1.1's "documented run command" (exec:java vs jar) is deferred to the build story.
- Prompts/responses are not yet saved verbatim to `evidence/` (README asks for this); effort/intervention log below is approximate.

## Next step (resume here)

1. Varun repeats the AC-01 manual check on the final GUI and saves the screenshot; update file 04 AC-01 row.
2. Optionally mark stories `done` in `sprint-status.yaml` after Varun's review.
3. Wait for the team (LOG-01..11, F-09) and for the CR-01 release. Do not read `shared/change-request-CR-01.md` before then.

## Build conventions

- Pin `JAVA_HOME=/Users/varunsondur/Library/Java/JavaVirtualMachines/corretto-22.0.2/Contents/Home` for every Maven command (default `mvn` picks Homebrew JDK 26).
- Code in `bmad-calculator/src/`, tests in `bmad-calculator/tests/`, `pom.xml` at `bmad-calculator/`.

## Intervention / effort log (approximate)

| Time (EEST) | Event |
|---|---|
| 16:54 | Session start; environment recorded |
| ~17:00 | Agent asked 9 questions; Varun: "go with your recommendations" (intervention 1) |
| ~17:05 | Varun interrupted to require a persistent status file (intervention 2) |
| ~17:10 | Varun instructed: do not commit anything until told (intervention 3) |
| ~17:25 | Varun chose option (a) for no-operation error (intervention 4) |
| ~17:35 | PRD, architecture, epics, sprint planning completed; stopped for approval |
| ~18:10 | Varun approved plan and `bmad-build`; chose commit of shared/planning files on `main` (05a2c8d), CLAUDE.md on `main` (da24512), branch `bmad-baseline` with evidence + BMAD install (1c48975) (interventions 5-7) |
| ~18:15 | Varun approved Story 1.1 spec ("approve and continue") (intervention 8) |
| ~18:40 | Varun gave standing approval: run 3 review subagents for every story, commit each story on `bmad-baseline`, and continue through stories 1.2-3.3. Agent will still HALT for an Open Question / intent gap in a spec, or an unresolved review loopback. Per-story spec checkpoints are treated as pre-approved (intervention 9). Deviation: standing approval replaces the per-story "Approve and continue" choice. |
| 18:40 | Story 1.1 built (mvn test: 2 run, 0 fail; headless: 1 skipped). Review subagents launched for 1.1. |

## Findings noticed during build (carry into the report; see memory `report-material-to-include`)

- F-06 Planning slicing overlap: architecture rule AD-2 (total core API) forced error handling into Story 1.2, and the presenter in Story 1.3 had to surface errors, so Epic 2 stories (2.1 to 2.3) became mostly end-to-end verification rather than new code. A different story split (by behaviour, not layer) would avoid this. Observed 2026-10-23.
- F-07 Review layers: Verification Gap found 0 to 1 gaps per story, Blind Hunter and Edge Case Hunter each returned 8 to 15 items, of which about half were verified false or rejected as low. Triage was needed on every finding.
- F-08 Review recurring themes: reviewers repeatedly flagged (a) unbounded input length / "never throws" overstatement, (b) skipped GUI tests reading as green, (c) accessibility. (a) and (c) were rejected as out of baseline scope; (b) was mitigated by recording display and headless runs separately.
- F-09 Open requirement question from review (for the team via `shared/experiment-log.md` if wanted): should a shown result or error clear when the user edits an operand or picks another operation? Current behaviour and spec: output stays until the next Calculate.
