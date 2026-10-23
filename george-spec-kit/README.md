# My Instructions — George / GitHub Spec Kit Track

## My goal

I will build and evolve the same Java desktop GUI calculator using GitHub Spec Kit. Our track will use the same shared requirements, acceptance criteria, change request, tests, and evaluation rubric as Varun's BMAD track.

## What I will read first

From the project root, I will read:

1. `project.md`
2. `shared/baseline-requirements.md`
3. `shared/acceptance-criteria.md`
4. `shared/experiment-protocol.md` once we freeze it
5. `shared/evaluation-rubric.md` once we freeze it

## Step 1 — I will record my environment

I will record the date/time, OS, Java/JDK version, Spec Kit version and installation method, coding agent/model and version, relevant configuration, repository commit, and existing files.

I will inspect my installed Spec Kit version and verify its supported commands and agent workflow. I will not assume a command works just because it appears in an older guide.

## Step 2 — I will use the native Spec Kit workflow

I will follow the installed GitHub Spec Kit process, including its native constitution, specification, plan, task breakdown, implementation, and validation workflow as supported by my installed version. I will preserve the native artefact names and locations.

### My copy-ready master prompt

```text
I am working in the GitHub Spec Kit implementation track for our controlled requirements-engineering study.

Read these shared documents before planning or changing code:
- shared/baseline-requirements.md
- shared/acceptance-criteria.md
- shared/experiment-protocol.md, if present and frozen
- shared/evaluation-rubric.md, if present and frozen

Inspect my installed GitHub Spec Kit version and follow its actual supported workflow and commands. Do not guess commands or replace the native workflow with an unrelated process.

Use Spec Kit's native constitution, specification, plan, task breakdown, implementation, and validation artefacts as appropriate for my installed version. Build only the baseline Java desktop GUI calculator specified by our shared documents. Those documents are the source of truth. Do not add scientific-calculator features or unrelated functionality.

Keep the chain from requirements to specification, design/architecture decisions, tasks, implementation, and tests traceable. State assumptions and ambiguities explicitly. Do not silently resolve ambiguities that could alter scope; ask for clarification or record a blocking question.

Present the plan and any blocking questions before implementation. After we agree on the plan, implement in small reviewable steps. Run the shared acceptance checks and report precisely what passed, failed, or was not tested.

Preserve the native Spec Kit artefacts. Capture prompts, decisions, revisions, commands, test logs, commits/diffs, human interventions, and elapsed/active effort in my track's evidence directory. Do not access or copy Varun's generated artefacts or code.

When my baseline is complete, stop. Do not implement CR-01 until our team has frozen both baselines and explicitly released the shared change request.
```

## Step 3 — I will implement the baseline

1. I will create and refine the native specification from our shared baseline requirements.
2. I will establish project principles/constitution and the implementation plan as my installed workflow expects.
3. I will break the plan into actionable tasks and make dependencies explicit.
4. I will implement only the baseline scope.
5. I will run the shared acceptance checks and record results.
6. I will preserve intermediate artefact versions and source-control history.

## Step 4 — I will freeze my baseline

When my baseline is ready, I will:
- Run the available acceptance tests.
- Record failures, limitations, and untested criteria.
- Commit and record the commit hash.
- Tag or otherwise identify the baseline freeze point.
- Record tool/model versions and effort.
- Stop until our team releases CR-01.

## Step 5 — I will handle the change phase only after release

I will read the frozen `shared/change-request-CR-01.md`.

Before editing code, I will use my native Spec Kit workflow to document:
1. Which specification requirements and tasks are affected.
2. Which architecture/design components, interfaces, and UI interactions are affected.
3. What new and regression tests I need.
4. Dependencies, risks, and potential side effects.

I will save the impact analysis before implementation. Then I will update the native specification, plan, and tasks as required; implement the change; review my diff; and run the same baseline regression tests and change-specific acceptance checks. I will record missed impacts, defects, rework, and human interventions.

## My evidence directory

I will preserve:
- Environment and version record
- Initial and subsequent prompts
- Constitution/specification/plan/tasks and revisions
- Pre-coding impact analysis
- Commands and test logs
- Commits and diffs
- Defect/rework log
- Effort and intervention log
- Final results against our common rubric

## My boundaries

- I will work only in my George/Spec Kit track.
- I will not copy Varun's code or generated artefacts.
- I will treat frozen shared documents as read-only.
- I will preserve Spec Kit's native workflow rather than force BMAD artefact names onto my track.
- I will not score my track using a different rubric.
