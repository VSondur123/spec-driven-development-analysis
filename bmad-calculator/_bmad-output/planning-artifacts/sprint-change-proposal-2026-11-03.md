---
title: Sprint Change Proposal — CR-01 Scientific Operations
status: implemented-2026-11-03; CR-01 released and complete (Varun, 2026-11-07)
created: 2026-11-03
workflow: bmad-correct-course (BMAD 6.12.4), batch mode
trigger: shared/change-request-CR-01.md v0.2, released by Varun on 2026-11-03
baseline: main at 1e39815 (baseline code from commit 740c78d, tag baseline-frozen)
---

# Sprint Change Proposal — CR-01 Scientific Operations

## 1. Issue Summary

CR-01 (frozen, released to both tracks) adds three unary operations to the finished baseline calculator: square `x²`, square root `√x`, reciprocal `1/x`. Each uses the First number field only. The baseline is complete (9 stories, all `review`; 172 tests pass; BASE-AC-01..19 recorded).

The change is not a defect or a pivot. It is a planned scope extension, so the correct-course checklist is used for impact analysis only.

Decisions already made for the BMAD track (`shared/experiment-log.md`): LOG-12..15 agreed; LOG-17..20 agreed by Varun on 2026-11-03 (George's response pending). LOG-16 (CR-AC criteria not copied into `acceptance-criteria.md`; CR-01 header still "Proposed") is open: §6 of CR-01 is treated as the frozen CR acceptance criteria meanwhile.

Mode: **Batch** (all proposals shown together; Varun approves or edits at the end). Deviation from the skill's recommended "Incremental" mode, chosen to keep one approval point; recorded in `evidence/02-decisions-log.md` terms as D-08.

## 2. Impact Analysis

### 2.1 Epic and story impact

| Existing item | Impact |
|---|---|
| Epic 1 (Stories 1.1–1.3) | No change to the stories. The code they produced is extended (`Operation`, `CalculatorFrame`). |
| Epic 2 (2.1–2.3) | Behavioural change: "either operand empty" and "invalid operand" now depend on the operation's arity. The stories stay valid for binary operations. |
| Epic 3 (3.1–3.3) | Clear needs no change (CR-AC-20 checks it for unary). 3.2 (build docs, architecture rules) and 3.3 (acceptance evidence) are re-run as regression. |
| New | Epic 4 with four stories (section 4.1). |

### 2.2 Artefact conflicts

| Artefact | Conflict or gap |
|---|---|
| PRD §5 Non-Goals | "No scientific operations (…roots…)" is now outdated; §6.2 says CR-01 is excluded; §3 Glossary lists exactly four Operations and four Error Messages ("Exactly one of"). |
| PRD FR-3, FR-4, FR-7, FR-9 | Written for two operands and four operations. FR-7 ("if either Operand is empty") must become arity-aware. |
| Architecture AD-4 | Error text source lists four messages; a fifth is needed ("Cannot take square root of a negative number"). |
| Architecture AD-5 | Validation order and "either operand" wording must become arity-aware; domain checks per operation. |
| Architecture AD-7 | "Binary function" enum must carry arity. The claim "adding an operation touches only the enum plus the view's button list" will not hold fully: `Calculator.evaluate` changes too. This is a finding for the study. |
| Architecture AD-9 | New control names (`op-square`, `op-sqrt`, `op-reciprocal`). |
| Epics FR coverage map | Needs new FRs for CR-01. |
| UX | None (step skipped in baseline). |

### 2.3 Technical impact

Files expected to change (no code edited yet):

- `src/calculator/core/Operation.java`: add arity and three constants; unary `apply`.
- `src/calculator/core/Calculator.java`: arity-aware empty/invalid checks (Second number ignored for unary, malformed text included, LOG-17), domain checks, square-root algorithm.
- `src/calculator/core/ErrorKind.java`: add `NEGATIVE_SQRT`.
- `src/calculator/ui/CalculatorFrame.java`: three more toggle buttons in the operation row (layout may need a second row; Second number stays visible and enabled, LOG-20).
- `src/calculator/presenter/*`: no change expected (selection keeps result/error; Calculate path is generic).
- Existing tests that will break or need updating: `OperationTest` asserts `Operation.values().length == 4`; any test enumerating error messages; GUI tests that assume the operation row has four buttons. To be confirmed during Story 4.1.
- New tests: core, presenter, GUI for CR-AC-01..20; regression run for BASE-AC-01..19 recorded individually (CR-AC-21).

## 3. Recommended Approach

**Direct Adjustment** (add Epic 4, amend PRD, architecture and epics in place). No rollback, no scope reduction.

Rationale: the baseline architecture already isolates `core`, `presenter` and `ui` (AD-1, AD-2, AD-7), so the change is local to three source files plus tests. Scope classification: **Minor to Moderate**. It needs backlog additions (new epic and stories) but no replan.

Effort estimate: small (about the size of Epic 2). Risks: (1) square-root rounding correctness, (2) regression in baseline error precedence, (3) layout width with seven operation buttons, (4) the unary empty-input message "Enter both numbers" is mandated by CR-01 §5 although it reads oddly for one-operand operations (raised as LOG-21, see section 6).

### 3.1 Square-root algorithm (design decision for approval)

CR-01 requires a deterministic square root, rounded to 10 decimal places HALF_UP, without double rounding. Proposal: work on exact integers.

1. Let `N = floor(a × 10^20)` as a `BigInteger`; `r = N.sqrt()` (floor). `r` is `floor(√a × 10^10)`.
2. Round half-up: if `a × 10^20 ≥ r² + r + 1/4` (exact `BigDecimal` comparison) then `r + 1`, else `r`.
3. Result is `r × 10^-10` as a `BigDecimal`, formatted by `ResultFormatter` (AD-4).

This avoids `Math.sqrt` (double, forbidden by AD-3) and avoids `BigDecimal.sqrt(MathContext)` significant-digit rounding (double rounding risk). Check cases: `√2 = 1.4142135624`, `√9 = 3`, `√0 = 0`.

## 4. Detailed Change Proposals

### 4.1 Epics and stories (new Epic 4)

Split by behaviour, not by layer (lesson from finding F-06: the baseline split by layer made Epic 2 mostly verification).

**Epic 4: Scientific operations (CR-01)** — a user can square, take the square root of, or take the reciprocal of the First number, with the same error handling and recovery as the baseline.

- **Story 4.1: Unary operation support and Square.** Arity on `Operation`; arity-aware validation (empty/invalid check on First number only; Second number ignored, LOG-17, LOG-18); `x²` control in the GUI; Second number stays visible (LOG-20). Covers CR-FR-01..04, 09; CR-AC-01..04, 14..17.
- **Story 4.2: Reciprocal.** `1/x` control; 10-decimal HALF_UP division; zero (`0`, `0.0`, `-0.0`) gives "Cannot divide by zero". Covers CR-FR-07, 08; CR-AC-09..13, 19.
- **Story 4.3: Square root.** `√x` control; algorithm in section 3.1; negative input gives "Cannot take square root of a negative number" via new `ErrorKind.NEGATIVE_SQRT`. Covers CR-FR-05, 06; CR-AC-05..08, 18.
- **Story 4.4: CR-01 regression and acceptance evidence.** Clear after unary (CR-AC-20), BASE-AC-01..19 run against the changed app and recorded individually (CR-AC-21), build/test instructions re-verified (CR-AC-22), evidence and traceability file updated. Covers CR-FR-10, 11.

### 4.2 PRD (old → new)

- **§5 Non-Goals.** OLD: "No scientific operations (trigonometry, logarithms, roots, exponentiation) — these arrive only through a later, separately frozen change request." NEW: keep the sentence but add: "Exception: CR-01 (frozen 2026-11-03) adds square, square root and reciprocal."
- **§6.2.** OLD: "The change request CR-01 is intentionally excluded and has not been consulted." NEW: "CR-01 is in scope from 2026-11-03; see §11."
- **§3 Glossary.** Add unary operations, arity, and the fifth Error Message; "Operand B is required only for binary Operations."
- **FR-7.** OLD: "If either Operand is empty (after trimming)…" NEW: "If a required Operand is empty… A unary Operation requires only Operand A."
- **New §11 CR-01 requirements** mapping `CR-FR-01..11` to FR-11..FR-15 (square, square root, reciprocal, unary input policy, unary error handling), with LOG-17..20 as decisions.

### 4.3 Architecture (old → new)

- **AD-4.** Add `NEGATIVE_SQRT` → "Cannot take square root of a negative number". Reciprocal reuses `DIVIDE_BY_ZERO`.
- **AD-5.** NEW order: (1) a required operand is blank; (2) `op == null`; (3) a required operand is not a Valid Number; (4) domain error (divisor zero, negative radicand, zero reciprocal input). Required operands: A for unary; A and B for binary. B is ignored for unary operations.
- **AD-7.** `Operation` carries `arity` (UNARY/BINARY) and an apply function that takes B only when binary. Note: `Calculator.evaluate` also changes.
- **AD-9.** Control names `op-square`, `op-sqrt`, `op-reciprocal`.
- **Capability map.** Add rows for FR-11..15.
- **Version bump.** Spine status `final` → amended 2026-11-03 (change recorded; baseline text kept in git history).

### 4.4 Epics file and sprint status

Append Epic 4 (4.1–4.4) to `epics.md` with acceptance criteria taken from CR-01 §6; add stories 4-1..4-4 as `backlog` and `epic-4: backlog` to `sprint-status.yaml`. Existing entries unchanged.

## 5. Implementation Handoff

- **Scope:** Minor/Moderate. Route to Developer agent (`bmad-build`, per-story spec and review layers as in the baseline), after Varun's approval.
- **Order:** 4.1, then 4.2 and 4.3, then 4.4.
- **Success criteria:** CR-AC-01..22 recorded individually; BASE-AC-01..19 re-run and recorded individually; all tests green on display and headless; no baseline behaviour changed.
- **Evidence to keep:** this proposal, impact analysis (this file plus `evidence/05-cr01-impact-analysis.md`), per-story test logs, defect and rework log, interventions and effort log.
- **Missed-impact tracking:** anything found during implementation that is not in section 2 is recorded as a missed impact.

## 6. Open points for Varun

1. Approve, edit or reject sections 3, 3.1, 4.1 to 4.4.
2. **LOG-21 (proposed):** CR-01 §5 says empty input uses the baseline "Enter both numbers" message. For a unary operation this reads oddly. Keep as mandated, or propose a shared wording change?
3. LOG-16: copy CR-AC-01..22 into `shared/acceptance-criteria.md` and update the CR-01 header? Needs both tracks.
4. Commit policy: no commits so far; changes sit uncommitted on branch `cr-01`.

## 7. Approval record

- 2026-11-03, Varun: Epic 4 split approved; LOG-16 (copy CR-AC to shared file) and LOG-21 approved. Artefacts (PRD, architecture, epics, sprint-status) amended the same day. Square-root algorithm (3.1) and the start of coding are **not yet explicitly approved**.
