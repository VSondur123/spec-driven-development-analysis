# Spec Kit Calculator Constitution

## Core Principles

### I. Shared Requirements Are the Source of Truth
`shared/baseline-requirements.md` and `shared/acceptance-criteria.md` define the product. This
track MUST NOT reinterpret, extend or silently resolve them. Any ambiguity or gap MUST be
recorded in `shared/experiment-log.md` and confirmed by the owner before it affects the spec,
plan or code. Every requirement (`BASE-FR-*`, `BASE-NFR-*`) and acceptance check (`BASE-AC-*`)
MUST be traceable to a spec item, plan decision, task, code and test.
Rationale: both tracks must deliver identical observable behaviour for the comparison to be valid.

### II. Baseline Scope Only
Only the baseline is built: a Java desktop GUI for add, subtract, multiply and divide, with
clear/reset and defined handling of invalid input and division by zero. Scientific operations,
history, memory, persistence, networking and unrequested styling are out of scope. The change
request (`shared/change-request-CR-01.md`) MUST NOT be read or implemented until the owner
releases it to both tracks.
Rationale: early scope growth would contaminate the controlled change phase.

### III. Observable Correctness Without Crashes
Behaviour MUST match the frozen requirements: exact decimal arithmetic, the agreed rounding and
display policy, the agreed input grammar, inline errors (never dialogs), and the agreed error
priority. Invalid input, an unselected operation and division by zero MUST NOT crash the
application or show infinity/NaN or any misleading value as a result. The user MUST be able to
recover by correcting input or clearing. The numeric and message rules live only in the shared
documents and are referenced, not restated, here.
Rationale: avoids two copies of one rule drifting apart.

### IV. Test Evidence Is Honest and Reproducible
Every `BASE-AC-*` check MUST have a recorded result of Pass, Fail, Blocked or Not run. A result
is Pass only if the behaviour was observed. Manual checks (such as `BASE-AC-01`) MUST be marked
as manual and backed by a screenshot or note. Failures MUST record steps, expected result, actual
result and evidence. Build, run and test commands MUST work from a clean checkout.
Rationale: the study compares evidence quality, so evidence cannot be inferred or rounded up.

### V. Reviewable, Extensible Structure
Code and tests MUST be organised so the later change request can be analysed and added without
rewriting the baseline (`BASE-NFR-04`). Calculation logic MUST be separable from the GUI so it
can be tested without a display. The design stays as simple as the requirements allow; any added
abstraction or dependency MUST be justified in the plan.
Rationale: CR-01 will extend this codebase, and its impact analysis depends on clear boundaries.

## Technology and Environment Constraints

- Language: Java. Application type: desktop GUI. No network access is needed to run it.
- Evaluation environment for this track: Windows 11, Amazon Corretto 22, Maven 3.9.x. Every
  documented build command MUST pin `JAVA_HOME` explicitly, because the default JDK may differ
  from the one `java` reports.
- The GUI toolkit is not fixed by this constitution. It MUST be chosen and justified in the plan,
  then recorded with Java version, build tool, OS and dependency versions.
- Dependency versions MUST be checked against the package registry before pinning.

## Workflow and Quality Gates

- Follow the installed Spec Kit workflow in order: constitution, specify, plan, tasks, implement.
  Verify each command against the installed version; do not add artefacts the tool does not
  produce. Any deviation or manual substitute MUST be disclosed to the owner and logged.
- The owner MUST approve the plan before any code is written.
- Work only inside this track's folder. Do not read or copy the other track's generated
  artefacts. `shared/*` is read-only for track work.
- Evidence MUST be recorded as work happens: versions, prompts, decisions, commands, test logs,
  defects, rework, interventions and effort. `evidence/00-STATUS.md` MUST be updated after every
  major step. History is appended to, never rewritten.
- Git: no commit, tag, push or pull request unless the owner asks in that conversation. The
  baseline freeze (commit, tag, hash) happens only after all `BASE-AC-*` results are recorded.

## Governance

This constitution governs this track's Spec Kit artefacts. It cannot override the shared
requirements, `project.md` or CLAUDE.md; if it conflicts with them, those documents win and the
constitution MUST be amended.

Amendments: propose the change with its reason, get owner approval, then record it in
`evidence/` as a dated entry. If the amendment touches a shared rule, record it in
`shared/experiment-log.md` instead of changing it for this track only.

Versioning: MAJOR for removing or redefining a principle, MINOR for adding a principle or section
or materially expanding one, PATCH for wording fixes.

Compliance: the plan MUST include a constitution check.

**Version**: 1.0.0 | **Ratified**: 2026-10-27 | **Last Amended**: 2026-10-27
