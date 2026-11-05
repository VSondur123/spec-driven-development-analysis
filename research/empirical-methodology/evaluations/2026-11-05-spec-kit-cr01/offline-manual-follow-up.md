# Offline launch — Amin’s manual follow-up

**Evidence ID:** MANUAL-OFFLINE-01. **Report received:** 2026-11-05, Europe/Helsinki. **Observer:** Amin. **Method/provenance:** named evaluator’s manual report in this conversation; not an AI-observed or automated run.

Amin reported: “i checked it and count it as pass the offline launch in github-spec-kit”. Asked whether this covered baseline, CR-01 or both separately, Amin confirmed: **“both were passed”**.

| Version identified by Amin | Study snapshot associated with that version | Criterion | Reported result |
|---|---|---|---|
| Spec Kit baseline | `88f56d0979e2fae1fb5d59e8681975e3a1fc2a27` | BASE-AC-01 at baseline | Pass — offline launch |
| Spec Kit CR-01 | `9e523775e31c42bb9f164ddf84e9ff736b2c2fdc` | BASE-AC-01 in the CR-01 baseline regression | Pass — offline launch |

The snapshot mapping above uses the study’s frozen baseline and CR-01 versions. Amin identified both versions, but did not independently recite their commit hashes. Exact observation times, network-disabling steps, launch commands, working-tree states and new screenshots were not supplied. These details are not reconstructed or attributed to Amin. The result is accepted as his named manual observation of successful offline launch; it is not independent corroboration by Codex.

This is a supplementary follow-up, not a replacement of the original raw records. The initial baseline run was 18 Pass / 1 Blocked; the initial CR-01 run was 21 Pass / 1 Blocked, with regression 18 Pass / 1 Blocked. Those blocked offline checks and the earlier unsuccessful setup remain part of the history. The raw automated TSV files and screenshots are unchanged.

With this follow-up included: baseline **19/19 Pass**; CR-01 baseline regression **19/19 Pass**; aggregate CR-AC-21 **Pass**; CR-01 **22/22 Pass**. No regressions were observed across the recorded 19 baseline criteria, with offline continuity based on Amin’s manual report in both versions. This does not establish general defect-free behaviour or two independent human reviews.
