# STATUS / RESUME POINT — Varun / BMAD Track (Baseline only)

**Last updated:** 2026-10-23 17:05 (EEST)
**Current phase:** BMAD planning COMPLETE. **STOPPED — awaiting Varun's approval of the plan before any code (D-06).**
**Git:** nothing committed (user instruction: do not commit until told).
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
- D-05 amended: JUnit Jupiter **6.1.3** (current) instead of "JUnit 5".
- Agent self-judged the sprint readiness gate (no independent reviewer).

## Caveats / open items for the team

- D-01…D-05 and D-07 are not yet agreed with George; shared docs still not frozen; `experiment-protocol.md`, `evaluation-rubric.md`, `experiment-log.md` empty. D-07 amends shared behaviour (new error message).
- PRD open questions: Enter from operation buttons? (OQ-3); how to evidence BASE-AC-01 offline (OQ-2); input grammar sign-off (OQ-4).
- Story 1.1's "documented run command" (exec:java vs jar) is deferred to the build story.
- Prompts/responses are not yet saved verbatim to `evidence/` (README asks for this); effort/intervention log below is approximate.

## Next step

1. **Varun reviews/approves the plan** (PRD, architecture spine, epics.md, sprint-status.yaml).
2. On approval: `bmad-build` for stories 1.1 → 3.3 in order (epic 1, then 2, then 3), updating `sprint-status.yaml`; then optional `bmad-code-review`.
3. Run BASE-AC-01…16, save results using the template in `shared/acceptance-criteria.md` §4 to `evidence/`.
4. Freeze: (only when Varun says) commit, record hash, tag, record effort; stop and wait for CR-01 release.

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
