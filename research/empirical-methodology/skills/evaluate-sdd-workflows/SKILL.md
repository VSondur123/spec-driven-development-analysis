---
name: evaluate-sdd-workflows
description: Prepare or apply evidence-linked comparison matrices for this repository's BMAD and GitHub Spec Kit calculator study, using its agreed protocol and frozen inputs. Use for evaluation preparation or authorised assessment of both ready phase submissions.
---

# Evaluate the SDD Calculator Workflows

This is a repository-scoped research skill. Compare complete tool–agent–operator workflows. Do not infer tool-only causation or generate missing native implementation artefacts. The companion procedure is a draft until the team adopts a recorded revision.

## Locate instructions and select mode

Locate the study root containing `project.md` and `shared/`. Read applicable repository instructions, then `project.md`, shared baseline requirements, baseline acceptance rules/checks, and the experiment log. Follow shared authority and native-workflow isolation rules. An evaluation request does not authorise code fixes, shared-input edits, commits, messages, PR approvals, or installation.

Read these companion files relative to this `SKILL.md`:

- [Protocol](../../protocol-recommendations.md): readiness, snapshots, execution, review.
- [Rubric](../../evaluation-rubric-proposal.md): statuses, counts, units, anchors.
- [Templates](../../evaluation-templates.md): output records and baseline inventory.
- [Evidence checklist](../../evidence-checklist.md): capture and missing-data rules.

For interpreting findings, also read [validity threats](../../validity-threats.md). If the team has adopted different shared protocol/rubric revisions, use them as authority and disclose conflicts with these draft companions. Do not invent a reconciliation. If companions are missing, identify the missing procedure rather than improvising scores.

**Prepare mode:** Use for drafting or when phase readiness/adoption is not established. Read shared baseline inputs and research documents only. Produce requested empty instruments, evidence requests, or a readiness-gap list. Do not inspect either implementation for comparative findings, read the unreleased CR, assign scores, or claim tests ran.

**Evaluate mode:** Use only when the request and study record establish authorisation to assess both ready, frozen phase submissions, with identified snapshots and an adopted common protocol/rubric. A completed submission may contain failures. If evidence for those gates is absent, finish independent preparation and list the exact missing decisions. Do not inspect one ready track while the other is still implementing. Preserve the team's separation of evaluators and implementation operators.

Session authorisation and existing recorded decisions count; do not ask again for permission already supplied. PR approval alone does not establish adoption of a particular protocol/rubric revision. The draft companions may be used for preparation without implying team adoption.

## Assess an authorised phase

1. Record the manifest: phase, input/protocol/rubric revisions, adoption date, both root paths and immutable commits, evaluator/reviewers, environments, evaluator model/tool, prior exposure, and readiness/access decision. Both tracks may be folders within one repository. Work from identified snapshots; document any approved patch rather than silently using dirty files.
2. Inventory native artefacts without modifying them. Use search to locate candidate requirement links, then verify their meaning in context. Record availability, origin, and tool/operator authorship where known. A reconstructed link is evaluator evidence, not native traceability. Implementation targets can be code, configuration, or required documentation; equivalent native document structures are acceptable.
3. Fill one requirement matrix per track, the clause audit, detailed test/subcase records, evidence index, and combined comparison table. Derive IDs, counts, and clauses from the adopted shared-input revision. The templates reflect 13 requirement IDs and 16 draft ACs on `main` at `cb1a051`; reconcile them at freeze rather than adopting unmerged track-specific changes. Inventory change IDs only from released frozen inputs. Never infer all scope is covered just from these counts.
4. Use existing verifiable run evidence, or execute appropriate native commands/manual checks when in scope and feasible. Record inputs, expected/actual, layer, observer/date, environment, exit status, and raw evidence. GUI behaviour requires GUI observation. If a display or required environment is unavailable, record the actual blocker. Do not count code inspection or a test filename as Pass.
5. Apply the rubric's subcase aggregation and denominators. Fully executed parent count C differs from P+F when a failed criterion has unexecuted subcases. Separate availability, execution, native/inferred traceability, and ratings. Unknown data stays unknown; do not infer zero effort or failed functionality from missing records. Preserve the primary planned run, repair/rerun records, exclusion reasons, and conflicting valid observations.
6. Draft criterion ratings with evidence and rationale, labelled as provisional until human review. Retain independent reviewer scores and disagreements. Agent ratings are not two independent human reviews. Do not assign an overall total, rank tools by file/test counts, or claim statistical superiority.
7. For an explicitly released change phase, preserve the original pre-coding impact analysis and baseline/change snapshots. Populate the agreed affected-element register before calculating omissions, preserving reviewer exposure and uncertainty. Review propagation and baseline regressions using the released checks. Do not treat a retrospective impact narrative as pre-coding evidence or the final diff as a complete impact oracle.

## Output and handoff

Write only to the user-authorised research output location, preserving previous evaluations and master templates. When producing evaluation results is requested without a destination, use a new dated directory under `research/empirical-methodology/` and report its path. Leave native artefacts, shared inputs, and other members' work untouched.

Produce the populated manifest, evidence index, requirement/clause matrices, acceptance/subcase records, descriptive metrics, criterion ratings, and findings/review/deviation records needed for the requested phase. Use the companion templates rather than duplicating schemas here. Every result must point to supporting evidence at a known revision; unsupported items stay NR, Not run, Blocked, or unavailable as defined by the rubric.

Explain observations separately from interpretations and include missing evidence, unresolved judgments, reproduction limits, and relevant validity threats. State exactly what was inspected/executed versus reported by operators. If the procedure was adopted after baseline work, disclose that chronology. The deliverable is a reviewable evaluation record; it does not replace human verification or supply approval for implementation changes.
