---
title: 'Story 3.2: Documented, reproducible build and architecture rules'
type: 'chore'
created: '2026-10-23'
status: 'done'
baseline_commit: '0ace55cb4fcecb14cc658d206c974c7460dc55f7'
route: 'dispatch'
review_loop_iteration: 0
story_key: '3-2-documented-reproducible-build-and-architecture-rules'
context:
  - '{project-root}/_bmad-output/implementation-artifacts/epic-3-context.md'
---

<frozen-after-approval reason="human-owned intent — do not modify unless human renegotiates">

## Intent

**Problem:** BASE-AC-15 and NFR-4/5 need documented, reproducible build/run/test commands, and the layering rule (AD-1) is only enforced by convention.

**Approach:** Keep `BUILD.md` as the build document (pinned `JAVA_HOME`, prerequisites, commands), verify it from a copy containing only tracked project files, and add a small `ArchitectureRulesTest` that fails on forbidden imports.

## Boundaries & Constraints

**Always:** `JAVA_HOME` pinned to JDK 22 in documented commands; the test reads source files only; keep it to two checks (core, presenter).

**Never:** Changes to application behaviour; new dependencies; extra test scaffolding beyond the two checks (Varun: keep tests minimal).

## I/O & Edge-Case Matrix

| Scenario | Input / State | Expected Output / Behavior |
|----------|--------------|---------------------------|
| Clean copy | `pom.xml`, `BUILD.md`, `src`, `tests` only | `mvn test` BUILD SUCCESS |
| Forbidden import in core/presenter | `import javax.swing...` | `ArchitectureRulesTest` fails naming the file |
| Lower layer imports higher | core imports presenter/ui, presenter imports ui | `ArchitectureRulesTest` fails |

</frozen-after-approval>

## Code Map

- `bmad-calculator/BUILD.md` -- build doc; added layout and architecture-test notes
- `bmad-calculator/tests/calculator/architecture/ArchitectureRulesTest.java` -- new

## Tasks & Acceptance

**Execution:**
- [x] `bmad-calculator/BUILD.md` -- layout and architecture rule notes -- BASE-AC-15, NFR-5
- [x] `bmad-calculator/tests/calculator/architecture/ArchitectureRulesTest.java` -- two import-scan checks -- AD-1, NFR-4
- [x] clean-copy build run recorded in `evidence/story-3-2-clean-build-log.txt`

**Acceptance Criteria:**
- Given a copy with only `pom.xml`, `BUILD.md`, `src`, `tests`, when `mvn test` runs with JDK 22, then BUILD SUCCESS.
- Given a forbidden import, when `ArchitectureRulesTest` runs, then it fails.

## Implementation Notes

- Clean-copy run: javac 22.0.2, 172 run, 0 failed, 0 skipped (display present), `mvn compile` ok. Log: `evidence/story-3-2-clean-build-log.txt`.
- The failing path of `ArchitectureRulesTest` was NOT exercised by a deliberate break (Varun declined editing production source for a mutation check and asked for minimal testing). It is verified by reading only; recorded as untested.
- The launch step (`mvn -q compile exec:java`) was not re-run here (needs a display and opens a window); it is covered by the owner-observed AC-01 check and will be re-confirmed in Story 3.3.
- A true "clean checkout" from git is not possible before the single end-of-Epic-3 commit; a file copy was used instead. Re-do from the commit after it exists if wanted.

## Spec Change Log

## Review Triage Log

Three subagent layers were NOT run for this story: Varun asked for the minimal version for 3.2 and 3.3 (deviation from the standing per-story review approval, recorded in `evidence/00-STATUS.md`).

## Verification

**Commands:**
- `export JAVA_HOME=$(/usr/libexec/java_home -v 22) && cd bmad-calculator && mvn test` -- expected: BUILD SUCCESS
