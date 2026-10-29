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
