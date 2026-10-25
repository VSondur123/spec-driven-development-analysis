# Working rules for this repository

This repository is a controlled Requirements Engineering study comparing two spec-driven development workflows (BMAD and GitHub Spec Kit) on the same Java GUI calculator. These rules apply to every agent and person working here, whichever tool or track they use.

## Read first, in this order

1. `project.md` (study guide, roles, workflow, fairness rules)
2. `shared/baseline-requirements.md` and `shared/acceptance-criteria.md` (source of truth)
3. `shared/experiment-log.md` (open decisions, proposals, deviations)
4. Your own track's README and its `evidence/00-STATUS.md`, if present (resume point)

Work only in your own track's folder. Do not read, copy or build on the other track's generated artefacts (specs, designs, tasks, code, evidence).

## Source of truth and changes to shared files

- `shared/*` is read-only for track work. Do not reinterpret or extend a requirement inside one track.
- If you find an ambiguity or gap, do not resolve it silently. Add an entry to `shared/experiment-log.md`, make the proposal visible, and wait for the owner to confirm it.
- A proposed edit to a shared document is tagged `[PROPOSED LOG-nn]` next to the changed text and stays proposed until the team agrees. Remove the tag on agreement; revert the text on rejection.
- Never edit the shared documents for one track only.

## Stages and gates

- Build only the baseline. Do not read or implement the change request (`shared/change-request-CR-01.md`) until the owner says it has been released to both tracks.
- Stop for the owner's approval after planning and before writing code.
- Follow the tool's installed, native workflow. Inspect the installed version and its actual commands; do not assume them from another version. Do not add artefacts the tool does not produce on its own, and do not fill a gap with a manual substitute without telling the owner. Gaps are findings for the study.
- Do not guess requirements. Ask the owner when a decision belongs to them.

## Git

- Do not commit, tag, push or open pull requests unless the owner asks for it in that conversation. An earlier approval does not cover later changes.
- When asked to commit part of the work, commit only the files the owner names.

## Evidence and records

- Record as you work, not from memory at the end: environment and tool/model versions, prompts, decisions, commands, test logs, defects, rework, human interventions and elapsed or active effort.
- Record every deviation from the tool's default process (for example skipped optional steps, a shortened path, an amended technology choice) and why.
- Report test outcomes exactly: Pass, Fail, Blocked or Not run. A test counts as Pass only if the behaviour was observed.
- Keep a resume file for the track (`evidence/00-STATUS.md`): what is done, what is pending, the next step, and any blocker. Update it after every major step so work can restart from a new session.
- Keep summaries linked to raw evidence. Do not rewrite history; add dated entries.

## Build hygiene

- Record the Java version, build tool, GUI toolkit and operating system. Pin `JAVA_HOME` explicitly in documented build commands, because the default JDK on a machine may differ from the one `java` reports.
- Check current versions of dependencies against the package registry before pinning them.
- Keep the documented build, run and test commands reproducible from a clean checkout.

## Style

- State outcomes plainly. Say when something was not done or not verified.
- Match the surrounding documents' tone and structure; do not add unrequested features or styling.
