---
title: 'Story 1.1: Project scaffold and launchable window'
type: 'feature'
created: '2026-10-23'
status: 'done'
baseline_commit: '1c4897553b0b4496da61bbe8fe50ea28af0e513e'
route: 'dispatch'
review_loop_iteration: 0
story_key: '1-1-project-scaffold-and-launchable-window'
context:
  - '{project-root}/_bmad-output/implementation-artifacts/epic-1-context.md'
---

<frozen-after-approval reason="human-owned intent — do not modify unless human renegotiates">

## Intent

**Problem:** There is no buildable project. Nothing can be compiled, tested or launched, so BASE-AC-01 (GUI opens offline) and the build part of BASE-AC-15 cannot be checked, and Stories 1.2 and 1.3 have nowhere to live.

**Approach:** Create the Maven project with the pinned toolchain, the package skeleton from the architecture spine, a `Main` that opens an empty window titled "Calculator" on the Event Dispatch Thread, and a short `BUILD.md` with the exact build, test and run commands.

## Boundaries & Constraints

**Always:** Java release 22, JDK Corretto 22 via explicitly set `JAVA_HOME`; Maven with `sourceDirectory=src` and `testSourceDirectory=tests`; JUnit Jupiter 6.1.3, maven-compiler-plugin 3.16.0, maven-surefire-plugin 3.6.0, exec-maven-plugin 3.6.4; window created only on the EDT; GUI tests skipped via `assumeFalse(GraphicsEnvironment.isHeadless())`; `calculator.core` and `calculator.presenter` stay free of `java.awt`/`javax.swing`.

**Never:** Any calculation, operand field, button or result control (Story 1.3); any `core` logic (Story 1.2); a network call; a second build tool; editing `README.md` (it is the owner's instruction file) or any file under `shared/`.

## I/O & Edge-Case Matrix

| Scenario | Input / State | Expected Output / Behavior | Error Handling |
|----------|--------------|---------------------------|----------------|
| Run with display | `mvn compile exec:java` with `JAVA_HOME` pinned, no network | Window titled "Calculator" opens, stays open | N/A |
| Test, headless | `mvn test` with no display | Build succeeds; GUI test reported skipped | N/A |
| Test, display present | `mvn test` | Build succeeds; GUI smoke test passes | N/A |
| Wrong JDK | `JAVA_HOME` unset, JDK older than 22 | Build fails with a clear Maven release error | `BUILD.md` states the required JDK and how to set `JAVA_HOME` |

</frozen-after-approval>

## Code Map

- `bmad-calculator/pom.xml` -- new; Maven build, no parent
- `bmad-calculator/src/calculator/Main.java` -- new; entry point
- `bmad-calculator/src/calculator/ui/CalculatorFrame.java` -- new; empty `JFrame` for now
- `bmad-calculator/src/calculator/core/`, `src/calculator/presenter/` -- new empty packages (add `package-info.java` so they exist)
- `bmad-calculator/tests/calculator/` -- new; smoke and environment tests
- `bmad-calculator/BUILD.md` -- new; commands
- Reuse: `bmad-calculator/README.md` is the owner's instruction file; do not touch.

## Tasks & Acceptance

**Execution:**
- [x] `bmad-calculator/pom.xml` -- create with the pinned plugin and dependency versions, `maven.compiler.release` 22, UTF-8, source/test directories, exec plugin `mainClass` `calculator.Main` -- reproducible build (BASE-NFR-05)
- [x] `bmad-calculator/src/calculator/ui/CalculatorFrame.java` -- `JFrame` titled "Calculator", `EXIT_ON_CLOSE`, sized and centred, no controls -- placeholder for Story 1.3
- [x] `bmad-calculator/src/calculator/Main.java` -- open `CalculatorFrame` via `SwingUtilities.invokeLater` -- AD-8
- [x] `bmad-calculator/src/calculator/core/package-info.java`, `.../presenter/package-info.java` -- package docs stating the no-AWT/Swing rule -- AD-1
- [x] `bmad-calculator/tests/calculator/ui/CalculatorFrameSmokeTest.java` -- guarded by `assumeFalse(isHeadless())`; builds the frame on the EDT, asserts title "Calculator" -- BASE-AC-01
- [x] `bmad-calculator/tests/calculator/RuntimeEnvironmentTest.java` -- asserts `Runtime.version().feature() >= 22` -- BASE-NFR-01
- [x] `bmad-calculator/BUILD.md` -- pinned `JAVA_HOME` export, test command, run command, prerequisites, and a note that BASE-AC-01 offline is a manual check -- BASE-AC-15, LOG-08

**Acceptance Criteria:**
- Given a clean checkout and `JAVA_HOME` set to Corretto 22, when `mvn -q test` runs, then the build succeeds and at least `RuntimeEnvironmentTest` passes.
- Given a display and networking disabled, when `mvn -q compile exec:java` runs, then a window titled "Calculator" opens (manual check, recorded in `evidence/`).
- Given a headless environment, when `mvn test` runs, then the GUI test is skipped, not failed.

## Implementation Notes

- Implemented directly by the main session (no subagent): `pom.xml`, `Main`, `CalculatorFrame`, two `package-info.java`, two tests, `BUILD.md`. `README.md` and `shared/` untouched.
- Verified 2026-10-23 with `JAVA_HOME` = Corretto 22.0.2: `mvn test` -> BUILD SUCCESS, 2 tests run, 0 failed, 0 skipped (display present). With `-Djava.awt.headless=true`: BUILD SUCCESS, GUI test skipped (1), runtime test passed (1).
- Surefire 3.6.0 discovered JUnit Jupiter 6.1.3 tests (risk noted in Design Notes did not occur). It resolved `junit-platform-launcher` 6.1.3 and engine 1.14.4 artifacts alongside.
- `mvn -q compile exec:java` launched; process stayed alive for 6 s with no output, then was killed. Not yet done: manual offline check and screenshot (BASE-AC-01), left for the owner.
- Window minimum size 420x260 and centring are free choices; no spec requirement.
- After review (2026-10-23): added two GUI tests and changed to `setMinimumSize`; `BUILD.md` revised; verification log saved to `evidence/story-1-1-test-log.txt`.

## Spec Change Log

## Review Triage Log

Layers: Blind Hunter (BH, 8 findings), Edge Case Hunter (EC, 13), Verification Gap (VG, 1 gap + 2 other). Reviewed 2026-10-23 at the same model capability as the session. Each row: finding, verdict, route, evidence.

| # | Source | Finding | Verdict / route | Evidence |
|---|---|---|---|---|
| 1 | VG gap, BH-1, EC-6 | No test covers `Main`, window visibility or close operation; title test used a literal | medium / patch | Removing `setVisible(true)` or changing `EXIT_ON_CLOSE` left tests green. Fixed: added `baseAc01_main_shows_the_calculator_window` and `baseAc01_closing_the_window_ends_the_application`; title test uses `CalculatorFrame.TITLE`. 4 tests pass, 3 skipped headless |
| 2 | BH-3, EC-9 | `BUILD.md` hard-codes a personal `JAVA_HOME` path | low / patch | Verified in file. Fixed: `/usr/libexec/java_home -v 22` form plus `javac -version` check; evaluation machine path kept as a labelled note |
| 3 | BH-7, EC-3 | `setPreferredSize(420x260)` before `pack()` forces size and can clip Story 1.3 controls | low / patch | `pack()` honours an explicit preferred size, so larger content would clip. Fixed: `setMinimumSize` instead |
| 4 | BH-8 | No saved test log or evidence of the `mvn test` run | low / patch | True: only prose in notes. Fixed: `evidence/story-1-1-test-log.txt`. Other claims in BH-8 (checkboxes, status updates, hand-edited `last_updated`) are false: tasks marked [x], notes written, `last_updated` is edited by hand by the sync step per instructions |
| 5 | EC-10, EC-11 | Headless launch and Maven exit on window close undocumented | low / patch | Doc-only; added two lines to `BUILD.md` Notes. EC-13 (Maven exits on close) is intended behaviour, not a defect |
| 6 | BH-2, EC-7, EC-12-claim, VG other 1 | No enforcer rule pins JDK 22/Corretto; `RuntimeEnvironmentTest` accepts any JDK 22+ | low / rejected | D-05 and the spec require `JAVA_HOME` pinned in the documented commands, which `BUILD.md` does. Enforcement would add a plugin and was not asked for. Residual weakness noted as a finding for the report |
| 7 | BH-4, VG other 2 | Pinned versions not checked against the registry | false | Checked on Maven Central 2026-10-23 and recorded in the architecture memlog (`version` entry) and spine Stack table; surefire resolved all artefacts during the run |
| 8 | BH-5 | Non-standard `src`/`tests` directories not recorded | false | Recorded in architecture AD-10, PRD addendum and this spec's Boundaries. No resources exist yet |
| 9 | BH-6 | No test enforcing package import rules | low / rejected | Both packages are empty; nothing can violate the rule yet. Story 3.2 owns `ArchitectureRulesTest` per `epics.md` |
| 10 | EC-1, EC-2 | No handling of `HeadlessException` / constructor exceptions on the EDT | low / rejected | App needs a display by design (documented). Guards would add branches for a case not shown to occur in the supported environment |
| 11 | EC-4 | Frame not disposed if assertion fails in test | false | Only `getTitle()` runs between construction and dispose; also now in `finally` after patch 1 |
| 12 | EC-5 | Broken `DISPLAY` makes GUI test fail instead of skip | low / rejected | Environment edge case; guard adds complexity; headless detection works as specified |
| 13 | EC-8 | surefire relies on implicit `junit-platform-launcher` resolution | low / rejected | Versions are pinned and resolution worked on a clean download; explicit dependency adds a pom entry not shown necessary |
| 14 | EC-12 | Spec says "clear Maven release error" but the message comes from javac | false | A wrong JDK still fails the Maven build with an explicit "release version 22 not supported" message; also the fix would edit the frozen spec |

No `intent_gap` or `bad_spec` findings, so no loopback (`review_loop_iteration` stays 0). No `defer` entries.

## Design Notes

Run command is `mvn -q compile exec:java` (decided here, since the architecture left "jar vs exec:java" open). A runnable jar can be added later without changing behaviour. Risk to check first: surefire 3.6.0 must discover JUnit Jupiter 6.1.3 tests; if it does not, report it rather than downgrading silently.

## Verification

**Commands:**
- `export JAVA_HOME=/Users/varunsondur/Library/Java/JavaVirtualMachines/corretto-22.0.2/Contents/Home && cd bmad-calculator && mvn -q test` -- expected: BUILD SUCCESS, tests run, none failed
- `mvn -q compile exec:java` (same `JAVA_HOME`) -- expected: window "Calculator" appears

**Manual checks (if no CLI):**
- Disable Wi-Fi, run the app, take a screenshot of the open window and save it under `evidence/`.
