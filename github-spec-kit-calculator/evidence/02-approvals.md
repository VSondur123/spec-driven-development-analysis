# Owner approvals — George / GitHub Spec Kit track

Append dated entries; do not rewrite history.

## AP-01 Plan approved (2026-10-27)

| Item | Value |
|---|---|
| Date | 2026-10-27 (time of day not recorded) |
| Approved by | George (owner of this track) |
| What was approved | The plan in `specs/001-baseline-calculator/` (`plan.md`, `research.md`, `data-model.md`, `quickstart.md`, `contracts/`), including: GUI toolkit Swing (research D-01) and the Maven standard folder layout `src/main/java`, `src/test/java` in the track root (D-08). The other plan decisions (D-02 to D-07, dependency versions, Requirement Coverage table) were part of the plan that was approved; the owner named D-01 and D-08 explicitly. |
| Model that produced the plan | `claude-sonnet-5-5` (Claude Code, VS Code extension, Spec Kit skill `speckit-plan`) |
| Spec Kit | specify-cli 1.2.3 |
| Conditions | None. The owner asked that two small UI behaviours (focus after Clear, exception safeguard) be logged as design choices, not requirements (see DL-12, DL-13 in `03-decisions-log.md`). |
| What this approval does not cover | Writing code. The constitution and CLAUDE.md require a stop after planning; this approval releases `/speckit-tasks`. Implementation (`/speckit-implement`) is a separate step and still needs its own go-ahead from the owner. |
| Still open | LOG-12 to LOG-15 in `shared/experiment-log.md` are proposed, not agreed by the team. The plan follows them for this track only. |

## AP-02 Interim commit allowed for BASE-AC-15 (2026-10-27)

| Item | Value |
|---|---|
| Date | 2026-10-27 (time of day not recorded) |
| Approved by | George (owner of this track) |
| What was approved | An interim commit that is **not** the baseline freeze may be made so `BASE-AC-15` (clean checkout) can be checked. The owner asks for each commit separately; an earlier approval does not cover later commits. The freeze (commit, tag, hash) still comes last, after every `BASE-AC-*` result is recorded. |
| Context | `/speckit-analyze` finding C3: a clean checkout needs a commit, but the constitution allows the freeze only after all results are recorded. |
| Applied in | `specs/001-baseline-calculator/tasks.md` rules block, T029 and T033. |
| What this does not cover | Making any commit. No commit exists yet, and none will be made until the owner asks. |


## AP-02 follow-up: LOG-12 to LOG-15 agreed (2026-10-27)

The line "Still open: LOG-12 to LOG-15 proposed" in AP-01 is now out of date. The owner reported on 2026-10-27 that Varun agreed all four (see DL-30). The AP-01 entry is left as written.

## AP-03 CR-01 plan approved (2026-11-04)

| Item | Value |
|---|---|
| Date | 2026-11-04 (time of day not recorded; about 23:30 local) |
| Approved by | George (owner of this track), reply "aprove" |
| What was approved | The CR-01 plan in `specs/002-scientific-operations/` (`plan.md`, `research.md`, `data-model.md`, `quickstart.md`, `contracts/`), including the square-root method (research C-01), the unary-flag design (C-04), the three new buttons in the existing group (C-07), the Requirement Coverage table and the list of 19 baseline checks to re-run. The agent's own choices flagged in the plan (button test names `squareButton`, `squareRootButton`, `reciprocalButton`; extending the existing group; wider window) were part of what was approved. |
| Model that produced the plan | `claude-sonnet-5-5` (Claude Code, VS Code extension, Spec Kit skill `speckit-plan`) |
| Spec Kit | specify-cli 1.2.3 |
| Conditions | None. The square root of `-0.0` stays undefined (DL-46). |
| What this approval does not cover | Writing code. It releases `/speckit-tasks`. `/speckit-implement` needs its own go-ahead. It does not cover any commit. |

## AP-04 CR-01 implementation approved (2026-11-05)

| Item | Value |
|---|---|
| Date | 2026-11-05 (time of day not recorded) |
| Approved by | George (owner of this track), reply "Approved: run /speckit-implement" |
| What was approved | Running `/speckit-implement` on `specs/002-scientific-operations/tasks.md` (49 tasks, with the I1 to I3 fixes of DL-51). |
| Model | `claude-sonnet-5-5` (Claude Code, VS Code extension) |
| Conditions | The owner-marked tasks (T022, T029, T034, T039, T042, T043) wait for the owner. No commit, tag or push without a separate yes. The square root of `-0.0` stays undefined and untested (DL-46). |
| What this approval does not cover | Any commit (T042 needs its own yes and file list), any push, the freeze. |

## AP-05 CR-01 interim commit approved (2026-11-05)

| Item | Value |
|---|---|
| Date | 2026-11-05 (time of day not recorded) |
| Approved by | George (owner of this track), reply "yes" to the file list and to removing the Sync Impact Report comment |
| What was approved | (1) Removing the Sync Impact Report comment from `.specify/memory/constitution.md` (version stays 1.1.0). (2) One interim commit on `george/spec-kit-cr01` of the 40 changed or new paths shown in `git status`, plus the 19 `evidence/logs/cr-*.txt` files added with `git add -f`; no `Co-Authored-By` line; repository-local noreply email; not the freeze. |
| What this does not cover | A push, a tag, a freeze, or any later commit (the T043 to T049 edits need their own yes). |

## AP-06 Second commit, delete clone, push and CR-01 freeze approved (2026-11-05)

| Item | Value |
|---|---|
| Date | 2026-11-05 (time of day not recorded) |
| Approved by | George (owner of this track): "yes" to the file list (11 paths) and to the exact commands shown |
| What was approved | (1) A second commit of 11 paths on `george/spec-kit-cr01` (8 modified, 3 logs force-added), noreply email, no `Co-Authored-By`. (2) A local annotated tag `speckit-cr01-frozen` on that commit, not pushed. (3) Deleting the temporary clone `C:/Temp/cr01-clean` after the commit. (4) `git push origin george/spec-kit-cr01`, no `-u`, no force, no tags. |
| What this does not cover | A pull request, a push of any tag, a push to `main`, or any later commit. The freeze record below is uncommitted until the owner asks. |
