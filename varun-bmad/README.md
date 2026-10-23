# My Instructions — Varun / BMAD Track

## My goal

I will build and evolve the baseline Java desktop GUI calculator using the BMAD workflow. Our team will compare my track with George's Spec Kit track using the same shared requirements, acceptance criteria, change request, tests, and evaluation rubric.

## What I will read first

From the project root, I will read:

1. `project.md`
2. `shared/baseline-requirements.md`
3. `shared/acceptance-criteria.md`
4. `shared/experiment-protocol.md` once we freeze it
5. `shared/evaluation-rubric.md` once we freeze it

I will not invent a separate interpretation of our product requirements. If I find an ambiguity, I will record the question and request a team decision before changing scope.

## Step 1 — I will record my environment

Before I start, I will record:
- Date and start time
- Operating system and Java/JDK version
- BMAD version and installation method
- Coding agent/model and version
- Relevant configuration
- Repository commit and any existing files

I will inspect my installed BMAD setup and identify its actual supported workflow, commands, and required artefacts. I will not assume commands or file names from another BMAD version.

## Step 2 — I will plan using native BMAD

I will use BMAD's installed, prescribed workflow to understand the project, plan the work, and create the artefacts it expects. I will provide the shared baseline requirements and acceptance criteria.

My workflow should make requirements, user stories or equivalent work items, acceptance criteria, architecture/HLD, detailed design/LLD where appropriate, implementation tasks, and test plans clear and traceable. I will use BMAD's native artefact names and locations. **I will not create a generic `project.md` inside my BMAD project unless the installed workflow requires it**; our study-level `project.md` is already the common guide.

### My copy-ready master prompt

```text
I am working in the BMAD implementation track for our controlled requirements-engineering study.

Read these shared documents before planning or changing code:
- shared/baseline-requirements.md
- shared/acceptance-criteria.md
- shared/experiment-protocol.md, if present and frozen
- shared/evaluation-rubric.md, if present and frozen

First inspect my installed BMAD version and follow its actual native workflow and required artefacts. Do not guess commands or impose an artefact structure from another version.

Build only the baseline Java desktop GUI calculator defined by our shared requirements. Treat those documents as the source of truth. Do not add scientific-calculator features, unrelated features, or unapproved requirements.

Make the chain from requirements to stories/work items, acceptance criteria, architecture/HLD, detailed design/LLD where appropriate, implementation tasks, code, and tests as traceable as the native workflow supports. Identify assumptions and ambiguities explicitly; do not silently resolve scope-changing ambiguities.

Before implementation, present the plan and any blocking questions. After we agree on the plan, implement in small reviewable steps. Run the shared acceptance checks, fix failures, and report exactly what passed, failed, or was not tested.

Preserve native BMAD artefacts. Capture prompts, revisions, decisions, commands, test logs, commits/diffs, human interventions, and elapsed/active effort in my track's evidence directory. Do not access or copy George's generated artefacts or code.

When my baseline is complete, stop. Do not implement CR-01 until our team has frozen both baselines and explicitly released the shared change request.
```

## Step 3 — I will implement the baseline

1. I will follow BMAD's native sequence and will not skip required analysis or review steps just to reach code faster.
2. I will keep the calculator within the baseline scope.
3. I will map each requirement to the artefacts and tests where possible.
4. I will implement in small steps and review my diffs.
5. I will run the acceptance criteria and record exact results.
6. I will save evidence as I work, not from memory at the end.

## Step 4 — I will freeze my baseline

When my baseline meets our shared criteria, I will:
- Run all available acceptance tests.
- Record unresolved defects or criteria I could not test.
- Commit the result and record the commit hash.
- Tag or otherwise identify the baseline freeze point.
- Record tool/model versions and total effort.
- Stop and wait. I will not begin the change request until our team releases CR-01.

## Step 5 — I will handle the change phase only after release

I will read the frozen `shared/change-request-CR-01.md`.

Before editing code, I will use BMAD's native artefacts and workflow to:
1. Identify affected requirements and user stories/work items.
2. Identify affected HLD/LLD elements, components, interfaces, and UI behaviour.
3. Identify impacted and new tests, including regression tests.
4. Explain dependencies, risks, and likely side effects.
5. Save this impact analysis as evidence **before implementation**.

Then I will update my native artefacts, implement CR-01, review my diff, and run the same shared baseline regression tests plus all change-specific acceptance checks. I will record missed impacts, defects, rework, and human interventions.

## My evidence directory

I will use an `evidence/` directory within my track (or the equivalent existing structure) to preserve:
- Environment and version record
- Prompts and important agent responses
- Native artefact snapshots/revisions
- Impact analysis
- Commands and test logs
- Commits and diffs
- Defect/rework log
- Effort and intervention log
- Final results against our common rubric

## My boundaries

- I will work only in my Varun/BMAD track.
- I will never copy George's code or generated artefacts.
- I will treat frozen shared documents as read-only and propose amendments through our team instead of changing only my track's copy.
- I will collect evidence and use our frozen common rubric for evaluation later.
