# Resume file — George / GitHub Spec Kit track

Update after every major step. Last updated: 2026-10-27.

## Done

- Read `project.md`, `shared/*` and this track's README. Did not read the BMAD track's artefacts.
- Replied "Agree" to LOG-01 to LOG-09 in `shared/experiment-log.md`. LOG-07 reply notes the OS difference (Windows 11, not macOS). LOG-09: `project.md` is the current role list. These edits are **not committed** yet.
- Installed Amazon Corretto 22.0.2.9.1 and verified Maven 3.9.16 runs on it with `JAVA_HOME` pinned.
- Installed Spec Kit specify-cli 1.2.3 and verified `specify version`, `specify check`.
- Chose the Spec Kit integration: `claude`.
- Wrote `evidence/01-environment.md`.
- Ran `specify init . --integration claude --script ps --non-interactive --force` in this folder (2026-10-27). The first attempt without `--force` stopped because the folder was not empty (README.md and evidence/). Backed up both before the forced run; README.md and evidence/ files were unchanged afterwards. Created `.specify/` (templates, PowerShell scripts, workflow, empty-template `memory/constitution.md`) and `.claude/skills/` (speckit-constitution, -specify, -plan, -tasks, -implement, -analyze, -checklist, -clarify, -converge, -taskstoissues). Exit code 0.
- Constitution v1.0.0 saved to `.specify/memory/constitution.md` (2026-10-27). Drafted from `project.md`, `shared/baseline-requirements.md`, `shared/acceptance-criteria.md`, `shared/experiment-log.md` and CLAUDE.md. The owner reviewed the draft before saving and had one sentence removed (a rule making `/speckit-analyze` findings blocking). The Sync Impact Report comment is still at the top of the file and must be removed before commit.
  - Deviation from the skill's native flow: the skill's step 1 says to run `.specify/scripts/powershell/resolve-template.ps1 constitution-template -Json`. This was not run; the scaffold was read directly from the unfilled `.specify/memory/constitution.md`. No `.specify/extensions.yml` exists, so no hooks were checked or run.
  - Open choice left to the plan: GUI toolkit (Swing or JavaFX). Not fixed by the constitution.
- `/speckit-specify` run (2026-10-27). Input was limited to `shared/baseline-requirements.md` and `shared/acceptance-criteria.md`. Created `specs/001-baseline-calculator/spec.md`, `specs/001-baseline-calculator/checklists/requirements.md` and `.specify/feature.json`. No branch was created (no git extension hook, no `.specify/extensions.yml`). All checklist items pass.
  - The skill raised 3 clarification questions; the owner answered Q1: A, Q2: A, Q3: A. Q1: `.5`, `5.` and `-` alone are "Invalid number", and a spaces-only field is empty. Q2: a shown result or error stays until the next Calculate or Clear. Q3: keep `BASE-NFR-02` wording, no numeric limit.
  - Appended LOG-12, LOG-13 and LOG-14 (Section E) to `shared/experiment-log.md` as **Proposed**. Shared requirements documents were not edited. Not committed.
  - Spec Kit's own `FR-nnn` numbering is kept, with each requirement tagged with its `BASE-*` ID. This is a mapping inside the spec, not an extra artefact.
- `/speckit-plan` run (2026-10-27). `setup-plan.ps1 -Json` copied the template; it reported BRANCH `001-baseline-calculator` although no git branch exists (derived from the feature folder name). Created in `specs/001-baseline-calculator/`: `plan.md`, `research.md`, `data-model.md`, `quickstart.md`, `contracts/calculation-contract.md`, `contracts/ui-contract.md`. No code written. No hooks (no `.specify/extensions.yml`).
  - Decisions for owner approval: GUI toolkit Swing (research D-01); `BigDecimal` with `HALF_UP` (D-02); strict grammar and spaces-only handling per LOG-12 (D-03); Maven standard layout `src/main/java` and `src/test/java` in the track root instead of `source/` and `tests/` named in `project.md` §4 (D-08).
  - Dependency versions checked on Maven Central on 2026-10-27: JUnit Jupiter 6.1.3, Surefire 3.6.1, compiler plugin 3.16.0, jar plugin 3.5.1, resources plugin 3.5.0 (latest stable 3.x chosen where the registry release tag is a beta). Not yet verified to build together.
  - Addition not native to the plan template: a "Requirement Coverage" table in `plan.md`, to satisfy constitution principle I (traceability).
  - **Correction (LOG-15):** `plan.md` and `research.md` state that the negative-tie rounding gap is logged as LOG-15. That was **not true when the plan was written**: `shared/experiment-log.md` had no LOG-15. The plan text was written as if the entry existed. George found the missing entry and added LOG-15 afterwards (Section E, Proposed). While completing the plan I then saw the entry and wrongly noted it was "already there"; it was not. The plan wording is accurate now, but the order of events was: plan claimed it, entry did not exist, George added it.
  - **Gate: waiting for the owner's approval of the plan. No `/speckit-tasks`, no code before it.** (Approved later the same day, see next entry.)
- Plan approved by the owner, 2026-10-27, including Swing (D-01) and the Maven layout (D-08). Recorded in `evidence/02-approvals.md` (AP-01). Started `evidence/03-decisions-log.md` (DL-01 to DL-18) and `evidence/04-prompt-log.md` (P01 to P08; P01 to P08 were reconstructed from the transcript, not logged live). The two small UI additions (focus after Clear, exception safeguard) are logged as design choices DL-12 and DL-13 and are marked "not a requirement" in `contracts/ui-contract.md`. The plan's workflow-gate row was updated to show the approval.
- `/speckit-tasks` run (2026-10-27, `claude-sonnet-5-5`). `setup-tasks.ps1 -Json` listed research.md, data-model.md, contracts/ and quickstart.md. Created `specs/001-baseline-calculator/tasks.md`: 34 tasks. Setup 3 (T001 to T003), Foundational 8 (T004 to T011), US1 6 (T012 to T017), US2 6 (T018 to T023), US3 3 (T024 to T026), US4 3 (T027 to T029), Polish 5 (T030 to T034). 14 tasks marked [P]. Format check: all 34 lines have checkbox, ID, optional [P], [US] labels only in story phases, and a file path or evidence path; no duplicate IDs. No code written. No hooks.
  - Tests are included (the baseline lists "Tests for our shared acceptance criteria" as in scope, and the approved plan maps checks to tests). The Spec Kit template calls tests optional; this is not a deviation from the shared requirements.
  - Tasks T002 and T034 create/update the effort/intervention log (`evidence/05-effort-intervention-log.md`) and test results (`evidence/06-test-results.md`); those two files do not exist yet.
  - `BASE-AC-01` (T028) needs the owner to turn networking off; it stays Blocked until then. `BASE-AC-15` (T029) needs a commit, which only the owner can request; it stays Blocked until then.
  - The root `.gitignore` already ignores `target/` and `*.jar`, so no `.gitignore` task was added. `.claude/` is still undecided (see Pending).
  - **Gate: no `/speckit-implement` and no code until the owner says so.**
- Not yet created: effort/intervention log, test results (tasks T002 creates them).
- `/speckit-analyze` run (2026-10-27, `claude-sonnet-5-5`, read-only). 11 findings: 0 critical, 2 high, 2 medium, 7 low. Coverage 21 of 21 requirements have at least one task; all 19 `BASE-AC-*` checks have a task. No constitution principle contradicted. Logged as P09 in `04-prompt-log.md`.
  - Owner decisions: C3 an interim non-freeze commit is allowed for `BASE-AC-15`, and the owner asks for each commit (AP-02, DL-24); I1 the tab test is dropped (DL-22).
  - Fixes applied after the owner approved the edits (DL-23): C1 status and effort update at each checkpoint (T011, T017, T023, T026, T029); C2 owner-observed steps (T017 launch, T031 real-window run) stay Not run until the owner confirms; I2 plan scope count; U1 `groupId` `study.speckit` in T001; U2 `7 ÷ -0` in T018 and in `contracts/calculation-contract.md`; U3 MVP marked not crash-safe until US2; U4 wider dialog search in T030. LOW findings A1 and D1 needed no change. Task count is still 34.
  - Corrected earlier statement: an earlier chat summary of the analyze severity split was wrong; the figures above are correct.
  - Not changed: `research.md` D-03 still states that only U+0020 is stripped (tab behaviour untested, no LOG entry).
  - Not committed.
- `/speckit-implement` run (2026-10-27, `claude-sonnet-5-5`), after the owner approved implementation (P12) and said "yes" at the checklist gate (1 unchecked item in `checklists/requirements.md`, left open on purpose; the skill does not allow editing it, so it stays unchecked until the results are recorded). No hooks. No `.gitignore` change needed (`target/` and `*.jar` are already ignored at the repo root).
  - Built: `pom.xml` (study.speckit:baseline-calculator:1.0.0, Corretto 22, pinned plugins as in research.md; all versions resolved and built together, which closes the "not yet verified to build together" note), `calculator.core` (Operation, CalculationError, CalculationOutcome, OperandParser, ResultFormatter, Calculator), `calculator.ui` (CalculatorPanel, ErrorMessages, CalculatorApp), four test classes.
  - Result of `mvn -B clean verify` with `JAVA_HOME` pinned: 81 tests, 0 failures, 0 skipped, BUILD SUCCESS, jar `target/baseline-calculator-1.0.0.jar` with `Main-Class: calculator.ui.CalculatorApp` (`evidence/logs/t033-final-verify.txt`). Commit: uncommitted (repo HEAD `5e3e78e`; all of this track's new files are untracked).
  - Tasks: T001 to T016, T018 to T027, T030, T032, T033 done and ticked in `tasks.md`. **Open (need the owner): T017** (launch the jar and confirm `7 + 5` shows `12`), **T028** (`BASE-AC-01`, networking off), **T029** (`BASE-AC-15`, after an interim commit the owner asks for), **T031** (manual run of the real window and `BASE-NFR-02`). **T034** is this status update plus the report to the owner.
  - `BASE-AC` results (`evidence/06-test-results.md`): Pass 15 (04 to 14, 16 to 19, observed in automated headless tests only), Not run 2 (02, 03: the window display has not been seen), Blocked 2 (01, 15). Nothing is marked Pass from an unobserved window.
  - Review (T030, `evidence/logs/t030-review.txt`): no dialog classes, no network, file or persistence classes, no GUI imports in `calculator.core`; only the four operations exist.
  - Decisions and deviations logged as DL-25 to DL-29: red runs before code, separate caption labels, literal symbol characters in `Operation.java` instead of escapes, nested `Parsed` record, open tasks. Three slips by the agent, corrected: a clumsy manifest read (redone), a UTF-8 BOM added by file rewrites (removed), and a failed first attempt to write log entries because of a quoting error (nothing was written; redone).
  - Not committed. No tag.
- Screenshots (2026-10-27): the owner ran the jar and saved eight screenshots in `evidence/screenshots/` (01 to 08). The agent viewed all eight. Recorded in `evidence/06-test-results.md`: `BASE-AC-02` and `BASE-AC-03` now Pass (seen in the real window); a "Real-window observations" table lists what each screenshot shows. T017 ticked. Still open: T028 (`BASE-AC-01` is still Blocked: the window is open in 08, but the offline state is only suggested by a globe icon in the tray), T029, T031 (needs the owner's notes on Enter in the real window, speed, dialogs, `7 / 0.0`).
- The agent built and launched the jar once at the owner's request (`evidence/logs/t017-build-for-owner-launch.txt`, process stayed alive 4 s). That launch was not used as evidence for any result.
- Owner's manual observations (2026-10-27), recorded in `evidence/06-test-results.md` and `evidence/screenshots/ac-01-manual-check.md`: Enter works in both fields, no pop-ups ever appeared, `7 / 0.0` shows "Cannot divide by zero", calculations feel instant, and the network was turned off before launching. Result: `BASE-AC-01` now Pass (owner confirmation plus screenshot 08; the icon alone does not prove offline), `BASE-AC-13` and `BASE-AC-19` updated with the real-window reports, `BASE-NFR-02` Pass as an owner observation (LOG-14 was proposed then; agreed later the same day). T028 and T031 ticked.
  - **Current counts: 18 Pass, 1 Blocked (`BASE-AC-15`), 0 Fail, 0 Not run.** 33 of 34 tasks ticked; only T029 (`BASE-AC-15`) is open. The "Pass" results for `BASE-AC-04` to `BASE-AC-19` come from automated tests, with real-window screenshots for 02 to 07 and owner reports for Enter, pop-ups, `7 / 0.0` and speed. Not checked in the real window by anyone: `BASE-AC-14` recovery and `BASE-AC-16` sequence, and most of the malformed inputs of `BASE-AC-12`; automated tests only.

- Team agreement (2026-10-27): the owner reported that Varun agreed LOG-12 to LOG-15 in `shared/experiment-log.md` (Section E; commit c897a42, already in local `main`, checked with `git cat-file`). Recorded as DL-30. `BASE-NFR-02` Pass no longer rests on a proposed decision (LOG-14 agreed: observation only, no numeric limit). The frozen requirement documents were not changed, and `shared/*` was not edited by this track. Whether to add the suggested cases to `BASE-AC-11`, `12` and `18` is still open for both tracks.
  - Wording: "(proposed)" changed to "(agreed)" in test comments (comments only, no code), `spec.md`, `plan.md`, `research.md`, `data-model.md`, `tasks.md`, contracts and `06-test-results.md` (DL-31). Not edited: `checklists/requirements.md` (the speckit skill forbids editing it); its line 16 still says "awaiting team agreement" and is out of date. Earlier dated entries in the logs were left as written.
  - Sync Impact Report comment removed from `.specify/memory/constitution.md` (DL-32); version stays 1.0.0.
  - `.claude/` holds only `skills/`; there is no `settings.local.json`.

## Pending

- Open Claude Code with `github-spec-kit-calculator/` as the workspace so the `/speckit-*` skills are available (they live in this folder's `.claude/skills`).
- Consider adding `.claude/` to `.gitignore` (Spec Kit warns it may hold credentials). Decide with the owner before committing.
- Owner action: ask for an interim, non-freeze commit (AP-02) so T029 (`BASE-AC-15`, clean checkout) can run. Say exactly which files to commit.
- Then: finish the effort log, review the 19 results, and freeze the baseline (commit, tag, hash) only when the owner asks.
- LOG-12 to LOG-15 are agreed (2026-10-27). They change only if the team reopens them; the tests marked `// LOG-nn (agreed)` would then change.
- Do not read `shared/change-request-CR-01.md` until it is released to both tracks.

## Next step

Owner decides on the interim commit for `BASE-AC-15` (and which files it contains). After the agent runs T029 and records the result, the owner reviews all 19 results and decides whether to freeze the baseline (commit, tag, hash).

## Blockers

- None for the baseline. `shared/change-request-CR-01.md`, `shared/evaluation-rubric.md` and `shared/experiment-protocol.md` are empty (LOG-10); they do not block the baseline.

## Not yet created

Nothing. All evidence files exist (`01` to `06`, `logs/`). `evidence/screenshots/ac-01-manual-check.md` is created when the owner confirms `BASE-AC-01`.
