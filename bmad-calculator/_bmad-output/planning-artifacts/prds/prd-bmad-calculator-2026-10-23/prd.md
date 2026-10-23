---
title: Baseline Desktop Calculator
status: final
created: 2026-10-23
updated: 2026-10-23
---

# PRD: Baseline Desktop Calculator

## 0. Document Purpose

This PRD defines the baseline (pre-change) product for the BMAD track of the Tampere University spec-driven-development comparison study. It is written for the study team and for downstream BMAD workflow owners (architecture, epics and stories, build). The source of truth is `shared/baseline-requirements.md` and `shared/acceptance-criteria.md`; this PRD restates them in BMAD form without changing their meaning. Shared IDs (`BASE-FR-nn`, `BASE-NFR-nn`, `BASE-AC-nn`) are carried inline on each FR for traceability. Terms are defined in the Glossary (§3) and used verbatim. Assumptions are tagged `[ASSUMPTION]` inline and indexed in §9. Decisions D-01 to D-05 in `evidence/02-decisions-log.md` are **proposed** and pending team confirmation.

## 1. Vision

A small Java desktop calculator that lets a person type two numbers, choose one of four basic arithmetic operations, and see a correct result — or a clear, recoverable error — in a single window, fully offline.

It is deliberately minimal. Its value is as a controlled, fully specified case: the same externally observable behaviour will be built by two different spec-driven workflows and later extended by one shared change request, so that the workflows can be compared on how well they carry requirements into design, tasks, code and tests.

## 2. Target User

### 2.1 Jobs To Be Done

- Functional: compute `a + b`, `a − b`, `a × b`, `a ÷ b` for integer and decimal values, including negatives, and trust the displayed result.
- Functional: recover quickly after a mistake (bad input, divide by zero) without restarting.
- Contextual (study): a tester can verify every behaviour by following the written acceptance checks and observing the result.

### 2.2 Non-Users (v1)

Anyone needing scientific, statistical, programmer, financial or graphing functions; anyone needing history, memory or multi-user features.

### 2.3 Key User Journeys

- **UJ-1. Mia adds two numbers.** Mia, a student, opens the calculator offline. Entry state: window open, fields empty, no error shown. She types `7` and `5`, clicks `+`, clicks Calculate (or presses Enter). The result `12` appears. Resolution: she can immediately enter new numbers.
- **UJ-2. Mia divides and recovers.** She enters `7` and `0`, selects `÷`, calculates, and sees "Cannot divide by zero" and no result. She changes the second number to `2`, calculates again, and sees `3.5`; the error is gone.
- **UJ-3. Mia fixes a typo.** She enters `1,5` and `2`, calculates, and sees "Invalid number". She corrects it to `1.5` and gets a correct result. Edge case: leaving a field empty shows "Enter both numbers".
- **UJ-4. Mia starts over.** After several calculations she clicks Clear; both fields, selected operation, result and error all return to their initial state.

## 3. Glossary

- **Calculator** — the desktop application defined by this PRD.
- **Operand** — one of the two numbers the user types. The first is **Operand A** (left), the second **Operand B** (right).
- **Operation** — one of Addition (`+`), Subtraction (`−`), Multiplication (`×`), Division (`÷`).
- **Selected Operation** — the Operation most recently chosen; none is selected in the initial state.
- **Calculate** — the action that evaluates Operand A, Selected Operation, Operand B. Triggered by the Calculate button or the Enter key.
- **Result** — the numeric value shown after a successful Calculate.
- **Error Message** — inline text naming why Calculate did not produce a Result. Exactly one of: "Enter both numbers", "Select an operation", "Invalid number", "Cannot divide by zero".
- **Clear** — the action that returns the Calculator to the Initial State.
- **Initial State** — both Operands empty, no Selected Operation, no Result, no Error Message.
- **Valid Number** — text consisting of an optional leading `-`, one or more digits, and optionally a single `.` followed by one or more digits. `[ASSUMPTION: no leading '+', no exponent, no comma decimals, no thousands separators, no whitespace padding other than leading/trailing spaces being trimmed]`
- **Display Policy** — rules for rendering a Result as text (see FR-8).

## 4. Features

### 4.1 Operand Entry

**Description:** The user types Operand A and Operand B into two labelled text fields. Realizes UJ-1, UJ-3.

#### FR-1: Enter operands (BASE-FR-02)

The user can type an integer or decimal value, including a negative value, into each Operand field, and the text is shown as typed. Realizes UJ-1.

**Consequences (testable):**
- Typing `7` or `-7` is accepted and displayed (BASE-AC-02, BASE-AC-08).
- Typing `1.2` is accepted and displayed (BASE-AC-03).
- Both Operand fields carry visible labels.

### 4.2 Operation Selection and Calculation

**Description:** The user selects one of four Operations and invokes Calculate. The Selected Operation is visibly indicated. Realizes UJ-1, UJ-2.

#### FR-2: Launch offline (BASE-FR-01)

The Calculator launches as a Java desktop GUI window using the documented command, without any network connection.

**Consequences (testable):**
- With the network disabled, the window opens and is usable (BASE-AC-01).

#### FR-3: Select an operation (BASE-FR-03)

The user can select Addition, Subtraction, Multiplication, or Division, and the Selected Operation is visibly identifiable.

**Consequences (testable):**
- Exactly one Operation is shown as selected after a selection; selecting another replaces it.
- Controls display `+`, `−`, `×`, `÷`.

#### FR-4: Calculate (BASE-FR-04)

The user can Calculate by clicking the Calculate button or pressing Enter, and the Result is shown in a visible Result area. `[ASSUMPTION: Enter works when focus is in either Operand field]`

**Consequences (testable):**
- `7 + 5` → `12` (BASE-AC-04); `7 - 5` → `2` (BASE-AC-05); `7 * 5` → `35` (BASE-AC-06); `7 / 2` → `3.5` (BASE-AC-07).
- `-7 + 2` → `-5` (BASE-AC-08); `1.2 + 2.3` → `3.5` (BASE-AC-09).
- If no Operation is selected, Calculate shows the Error Message "Select an operation" and no Result (decision D-07, proposed amendment to the shared docs).
- Precedence when several faults exist: "Enter both numbers", then "Select an operation", then "Invalid number". `[ASSUMPTION]`

#### FR-5: Consecutive calculations (BASE-AC-16)

The user can perform several Calculates in sequence without restarting; each Result corresponds to the current Operands and Selected Operation only.

**Consequences (testable):**
- After computing `7 + 5` then `7 * 5`, the Result is `35`, not influenced by the earlier value.

### 4.3 Reset

#### FR-6: Clear (BASE-FR-05)

The user can click Clear to return the Calculator to the Initial State. Realizes UJ-4.

**Consequences (testable):**
- After Clear, both Operand fields are empty, no Operation is selected, the Result area is empty and no Error Message is shown (BASE-AC-10).

### 4.4 Validation and Error Handling

**Description:** Invalid or incomplete input never produces a Result and never crashes the Calculator. Errors appear inline in a dedicated, identifiable Error Message area. Realizes UJ-2, UJ-3.

#### FR-7: Invalid and incomplete input (BASE-FR-06)

If either Operand is empty (after trimming), Calculate shows "Enter both numbers"; if either Operand is not a Valid Number, Calculate shows "Invalid number". In both cases no Result is shown and the Calculator remains usable.

**Consequences (testable):**
- Empty Operand A or B → "Enter both numbers" (BASE-AC-11).
- `abc`, `1.2.3`, `1,5`, `1e3`, `--1`, `.` → "Invalid number" (BASE-AC-12).
- When both a fault of emptiness and malformation exist (e.g. A empty, B `abc`), "Enter both numbers" takes precedence. `[ASSUMPTION]`

#### FR-8: Display Policy (BASE-FR-07 numeric aspects; BASE-AC-07, BASE-AC-09)

Arithmetic uses exact decimal arithmetic. A Result is displayed as plain decimal text with no exponent notation and no trailing zeros; division is rounded half-up to 10 decimal places. `[ASSUMPTION: scale 10 and HALF_UP are proposed decision D-03]`

**Consequences (testable):**
- `1.2 + 2.3` → `3.5`, not `3.5000000000000004` or `3.50`.
- `1 / 3` → `0.3333333333`.
- `2 / 2` → `1`.
- A Result of zero is shown as `0` (never `-0` or `0E-10`).

#### FR-9: Division by zero (BASE-FR-07)

Dividing by zero shows the Error Message "Cannot divide by zero", shows no Result, and never displays Infinity or NaN. Realizes UJ-2.

**Consequences (testable):**
- `7 / 0` and `7 / 0.0` → "Cannot divide by zero" (BASE-AC-13).
- `0 / 5` → `0` (valid).

#### FR-10: Error recovery (BASE-FR-08)

After any Error Message, the user can correct an Operand and Calculate again, or click Clear, and get a correct outcome. A successful Calculate or Clear removes the previous Error Message; a failed Calculate removes any previous Result. Realizes UJ-2, UJ-3.

**Consequences (testable):**
- Error then corrected input → correct Result, no Error Message (BASE-AC-14).
- Result then failed Calculate → Result area empty.

**Feature-specific NFRs:** Errors must not be shown in a modal dialog (proposed D-04).

## 5. Non-Goals (Explicit)

- No scientific operations (trigonometry, logarithms, roots, exponentiation) — these arrive only through a later, separately frozen change request. `[NON-GOAL for MVP]`
- No graphing, history, memory buttons, variables, unit conversion, symbolic algebra.
- No accounts, networking, persistence, databases, or external services.
- No styling or features that change the interaction model; visual polish is not a goal.
- No chained expressions or operator precedence (one operation on two operands per Calculate).

## 6. MVP Scope

### 6.1 In Scope

FR-1 to FR-10 above; a documented build/run/test procedure; automated tests covering BASE-AC-01…16 where automatable.

### 6.2 Out of Scope for MVP

Everything in §5. The change request CR-01 is intentionally excluded and has not been consulted.

## 7. Cross-Cutting Non-Functional Requirements

- **NFR-1 (BASE-NFR-01):** Runs locally on the agreed Java runtime and operating system used for evaluation (proposed: Java 22, macOS).
- **NFR-2 (BASE-NFR-02):** Each Calculate completes fast enough to appear instantaneous in interactive use (target: under 100 ms on the evaluation machine). `[ASSUMPTION: threshold]`
- **NFR-3 (BASE-NFR-03):** No invalid input or arithmetic error causes an uncaught exception or application exit.
- **NFR-4 (BASE-NFR-04):** Calculation logic is separate from GUI code so it can be unit-tested and extended later without rewriting the GUI.
- **NFR-5 (BASE-NFR-05):** Build, run and test commands are documented and reproduce from a clean checkout (BASE-AC-15).

## 8. Success Metrics

**Primary**
- **SM-1**: All of BASE-AC-01…16 recorded Pass. Validates FR-1 to FR-10, NFR-3, NFR-5.
- **SM-2**: Every FR and NFR traceable to at least one story and at least one test. Validates all.

**Counter-metrics (do not optimize)**
- **SM-C1**: Number of features or UI polish beyond the baseline — extra scope harms comparability. Counterbalances SM-1.

## 9. Open Questions

1. ~~No-operation behaviour~~ — **Resolved 2026-10-23 by Varun: add "Select an operation" (D-07).** Still needs agreement with George and a recorded amendment to the shared docs.
2. AC-01 "opens offline" is only manually verifiable; agree on the evidence form (screenshot / checklist).
3. Is Enter required to work from the operation buttons too, or only from the Operand fields?
4. Confirm input grammar (A-04), including whitespace trimming.

## 10. Assumptions Index

- §3 Valid Number grammar (no `+`, exponent, comma, thousands separators).
- FR-4 Enter key works in either Operand field.
- FR-4 error precedence order.
- FR-7 emptiness takes precedence over malformation.
- FR-8 scale 10, HALF_UP (D-03).
- NFR-2 100 ms threshold.
