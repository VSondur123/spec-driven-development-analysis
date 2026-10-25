# Proposed Evidence Checklist

**Date:** 2026-10-25. **Status:** Draft to share with both operators after team review.

Collect records in each operator's own track. Keep raw evidence and link summaries to it. The checklist helps preserve records now; it is not a claim that any record exists. Missing historical evidence should be marked unavailable or retrospective, never fabricated. Applicable repository instructions and the [shared requirements](../../shared/baseline-requirements.md) remain authoritative.

## 1. Preserve during work

- [ ] Record operator, phase, dates/timezone, tool/version, agent/model identifier, available settings, OS, JDK/build tool, GUI toolkit, and configuration changes. Mark undisclosed settings unknown.
- [ ] Save initial inputs and subsequent prompts/commands with timestamps or ordered interaction IDs. Record which shared-input revision each used.
- [ ] Preserve native requirements/specification, planning, design, tasks, and test artefacts with their original paths and revisions. Do not create missing native artefacts retrospectively to satisfy this checklist.
- [ ] Record human clarification, review, manual corrections, retries, configuration problems, tool failures, and optional/skipped native workflow steps, with reasons and evidence links.
- [ ] Record active sessions with start/end/pause information and activity; distinguish setup/training, baseline implementation, change implementation, and evaluation.
- [ ] Save the first failure, reproduction, fix/rework, and rerun as separate records. Record defects independently of failed-check counts.
- [ ] Preserve contradictions, unresolved decisions, and departures from the agreed procedure. Do not overwrite prior outcomes.

Raw stdout/stderr files may be ignored by the repository's `*.log` or `logs/` rules. Choose a tracked text evidence path in the operator's track, or reference an approved retained artefact, and verify it is actually retained. Keep credentials and private data out of shared transcripts; mark redactions and their analytical impact.

## 2. Baseline freeze

- [ ] Record exact track root, commit, clean/dirty state, and shared requirements/acceptance revision. Commit timestamps alone do not establish the ordering of uncommitted work.
- [ ] Record the baseline-complete/frozen declaration and date, including known failures. A failure must not disappear from the record to achieve a freeze.
- [ ] Preserve build/run/test commands, actual environment versions, explicit `JAVA_HOME`, prerequisites, exit statuses, and full output. Do not guess dependency versions or change them for evaluation.
- [ ] Preserve criterion-level results for every adopted baseline AC and required subcase, including expected/actual, observer, date, commit, method/layer, and evidence. The current `main` draft has sixteen criteria; reconcile the inventory after freeze.
- [ ] Identify the primary planned evaluation run; retain unexecuted sibling subcases, rerun reasons, invalid-procedure exclusions, and inconsistent observations.
- [ ] Preserve manual GUI notes for required launch/offline behaviour, interactions, and error/recovery where automation does not observe them. Include the actual network-disabling procedure for offline launch evidence. Check any keyboard behaviour only against agreed inputs, rather than assuming a particular interaction pattern.
- [ ] Index native requirement-to-spec/design/task/code/verification links, with gaps explicit. Leave evaluator reconstruction to separate research outputs.
- [ ] List unavailable prompts, time periods, or other records; label any dated recollections as retrospective estimates.
- [ ] Update the operator's existing resume/status file where applicable repository instructions require one.

## 3. Change release and implementation — later only

- [ ] Owner records release of identical frozen CR and change acceptance revisions to both operators after baseline freezes, plus delivery times and any prior exposure.
- [ ] Each operator saves the first impact analysis before coding, including affected requirements, artefacts, interfaces, tasks, tests, dependencies, and risks.
- [ ] Preserve native artefact revisions, source/test diffs from baseline, decisions, intervention and session records.
- [ ] Record the change freeze snapshot and both change-specific and baseline regression outcomes.
- [ ] Retain the reviewer-agreed affected-element reference set, its granularity, disagreements, and matching to the original impact analysis; mark unavailable inputs explicitly.
- [ ] Preserve any changed input version, failed first attempt, fixes/reruns, missed affected elements, and deviations.

Do not read the unreleased change request just to fill this checklist.

## 4. Evaluator evidence package — when both phase submissions are ready

- [ ] Record approved procedure/rubric revision and adoption date, reviewers, prior exposure, access authority, both frozen snapshots, and shared inputs.
- [ ] Index evidence by track and phase. Each reference identifies revision, path, section/lines or test name, observation date, source/observer, and the claim it supports.
- [ ] For missing artefacts, retain the examined scope and availability status; distinguish confirmed absence from unavailable access.
- [ ] Keep acceptance outcomes, artefact links, and rubric ratings in separate tables. Preserve required subcase records and distinguish decisive parent outcomes from full execution completion.
- [ ] Record reproduction attempts separately from operator reports, including setup blockages and unexecuted checks.
- [ ] Keep independent rating sheets, disagreements, reconciliation, and any unresolved ratings.
- [ ] Record evaluator tool/model, instructions, skill/document revision, outputs and human verification where an agent assists.
- [ ] Preserve all denominators, calculations, exclusions, uncertainty, deviations, and applicable validity notes.

The [templates](evaluation-templates.md) provide the corresponding record formats. Empty checkboxes indicate preparation, not verified gaps in a submission.
