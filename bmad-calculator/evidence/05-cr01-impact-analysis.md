# CR-01 impact analysis (BMAD track)

**Date:** 2026-11-03 (planning only; no source code edited). **Branch:** `cr-01` (from `main` at `1e39815`). **Tool:** `bmad-correct-course`, BMAD 6.12.4, Claude Sonnet 5.5, batch mode.

The full analysis is the Sprint Change Proposal: `_bmad-output/planning-artifacts/sprint-change-proposal-2026-11-03.md` (status: approved and implemented; the Epic 4 split, LOG-16 and LOG-21 were approved 2026-11-03; CR-01 released and complete per Varun, 2026-11-07). It covers affected epics/stories, PRD/architecture conflicts, affected source and test files, the proposed Epic 4 (stories 4.1 to 4.4), the square-root algorithm, risks and handoff.

Summary of impact: change is local to `core/Operation`, `core/Calculator`, `core/ErrorKind` and `ui/CalculatorFrame`; presenter expected unchanged; `OperationTest` (asserts 4 operations) will need updating; PRD §3/§5/§6.2/FR-7, architecture AD-4/5/7/9, and `epics.md` need amendment.

## Decisions and deviations

| ID | Entry |
|---|---|
| D-08 | Correct-course ran in **Batch** mode, not the recommended Incremental mode, to give one approval point. |
| D-09 | Shared log: LOG-17..20 agreed by Varun for the BMAD track on 2026-11-03 (George pending); LOG-16 and LOG-21 open. Update 2026-11-07: George agreed; LOG-16 to LOG-21 approved (reported by Varun). |
| D-10 | `bmad-build` step 1 says to HALT on a dirty working tree. The tree is dirty because Varun told the agent not to commit during planning, so the check was waived by that instruction and the run continued. |
| D-11 | Epic 4 context (`epic-4-context.md`) compiled inline by the agent, not by a subagent. |
| D-12 | Story 4.1 spec written as a full `dispatch` spec (not `oneshot`): it touches core, UI and tests. No Open Questions remained; stopped at Checkpoint 1 for Varun's approval. |
| D-13 | Story 4.1 implemented directly by the agent instead of a dispatched implementation subagent, because subagent use was not authorised for this session. |
| D-14 | `bmad-build` step 5 creates a local commit; skipped because Varun said no commits for now. |
| D-15 | Review layers not run for Story 4.4 (evidence story; the owner approved the spec and did not ask for the layers, as for baseline 3.2 and 3.3). |

## Interventions (2026-11-03)

| Event |
|---|
| Varun approved the square-root algorithm and the start of Story 4.1 (2026-11-03). |
| Varun released CR-01, asked for branch `cr-01`, and agreed LOG-12..15 and LOG-17..20; no commits for now ("just develop and plan"). |

## Missed impacts

None recorded yet. Record here anything found during implementation that is absent from the proposal.

## Missed impacts found while implementing

- 2026-11-03, Story 4.1: `OperationTest` (asserts 4 operations) was predicted. Not predicted: the two GUI tests that list the operation buttons (`everyControlHasAStableName`, `initialStateHasNothingSelectedAndNoOutput`) needed `op-square` added. No source file other than `Operation` and `Calculator` changed; the frame and presenter needed no edit, as predicted.

## Story 4.1 review (2026-11-03)

Three review layers ran; 15 findings triaged (details in the spec's Review Triage Log): 0 high, 0 medium, 3 low patched (weak layout assertion, untested error persistence on selecting `x²`, missing square value cases), 4 rejected as false-or-covered groups, 2 low rejected as out of scope (operand length, placeholder), 1 maybe-false deferred (visual layout check). Rework: one patch round, tests only. Same pattern as F-07: about half the findings were false or rejected.

## Story 4.2 review (2026-11-03)

Three review layers ran on a cumulative diff (4.1 and 4.2, because nothing is committed): 13 findings triaged (spec Review Triage Log). 0 high, 0 medium; 2 low patched (null first operand for unary, reciprocal selection replacing others), 5 rejected as false (already agreed in LOG-19/20/21 or CR-01), 6 low rejected (design hook, package-private contract, length cap, covered paths, process note). Verification Gap layer found no gaps. Rework: one patch round, tests only. No new missed impact: frame and presenter again needed no edit. Test run after patches: 257 run, 0 failed, 0 skipped (display).

## Story 4.3 review (2026-11-03)

Three review layers ran on the cumulative diff: 12 findings triaged (spec Review Triage Log). 0 high, 0 medium; 2 low patched (malformed `apply` Javadoc, tiny-square test row), 4 false (already agreed in LOG-19, LOG-21, CR-01 or verified in code), 6 low rejected. Verification Gap layer found no gaps. Defect: the first test run failed 1 of 302 because of a wrong test input (`1e41` instead of `1e40`); the code was correct, confirmed against an independent calculation; recorded as a test defect, rework about two minutes. Recurring review finding across 4.1 to 4.3: per-operation rules live in `Calculator` by operation identity (AD-7 claim does not hold); study finding. Test run after patches: 304 run, 0 failed, 0 skipped (display).

## Story 4.4 (2026-11-03)

Clear-after-unary tests added; `assertInitialState` helper found incomplete (checked only four of seven operation buttons) and fixed: a missed impact. Final runs: display 316 run, 0 failed, 0 skipped; headless 316 run, 0 failed, 80 skipped; clean copy 316 run, 0 failed. Results per check: `06-cr01-acceptance-results-and-traceability.md`. The CR-01 build phase is complete; nothing is committed.
