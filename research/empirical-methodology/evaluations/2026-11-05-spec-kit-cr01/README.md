# Spec Kit CR-01 acceptance and baseline regression evaluation

Amin’s independent evaluation with Codex assistance, 2026-11-05 (Europe/Helsinki, EET, UTC+02:00). Original implementation by George. Baseline comparator: `88f56d0979e2fae1fb5d59e8681975e3a1fc2a27`. Changed snapshot: published tag `speckit-cr01-frozen` resolving to **`9e523775e31c42bb9f164ddf84e9ff736b2c2fdc`**. Common test plans and acceptance inputs are from repository revision `42e9c41`.

## Results

- **CR-01: 21 Pass, 0 Fail, 1 Blocked, 0 Not run** across 22 parents. CR-AC-21 remains blocked because offline baseline launch is unverified.
- **Separate baseline regression: 18 Pass, 0 Fail, 1 Blocked, 0 Not run** across 19 parents. Original baseline outcomes are unchanged.
- **0 observed regressions among 18 comparable, previously passing parents.** Offline regression status is undetermined; this is not a claim that all 19 checks passed.
- **Native build: 116 tests, 0 failures, 0 errors, 0 skipped**, exit 0. The suite explicitly runs headlessly; it is separate from desktop GUI evidence.
- GUI records include all required subcases in the performed checks. Additional unary Enter and malformed Second number cases are supplementary and do not enlarge denominators.

## Evidence index

| Evidence | Location / claim |
|---|---|
| CR-01 parent and subcase results | [Sheet 08](08-cr01-evaluator-execution-sheet.md) |
| Separate baseline regression | [Sheet 09](09-cr01-baseline-regression-sheet.md) |
| Native build/test stdout/stderr | [build.txt](build.txt) |
| Exact environment and snapshot | [environment.txt](environment.txt) |
| CR GUI expected/actual records and screenshots | [change/gui-observations.tsv](change/gui-observations.tsv), `change/screenshots/` |
| Regression expected/actual records and screenshots | [regression/gui-observations.tsv](regression/gui-observations.tsv), `regression/screenshots/` |
| Documented native JAR launch | [native-launch.png](native-launch.png), [native-launch.txt](native-launch.txt) |
| Reproducible evaluator automation | [Cr01GuiEvaluator.java](Cr01GuiEvaluator.java), [BaselineGuiEvaluator.java](BaselineGuiEvaluator.java) |

## Method and commands

A fresh local clone was made using `git clone --no-hardlinks --no-checkout . tmp/spec-kit-cr01/snapshot`, then detached at `speckit-cr01-frozen`. The source/POM/tests were not modified; tracked state remains clean. Local-clone origin and the separate dependency cache are disclosed deviations from a remote clone/default cache. Windows PowerShell shell syntax in the native quickstart is adapted to macOS.

From the study root:

```sh
export JAVA_HOME=/Library/Java/JavaVirtualMachines/amazon-corretto-22.jdk/Contents/Home
export PATH="$JAVA_HOME/bin:$PATH"
study_root="$PWD"
cd tmp/spec-kit-cr01/snapshot/github-spec-kit-calculator
"$study_root/tmp/spec-kit-evaluation/tools/apache-maven-3.9.16/bin/mvn" \
  -B -Dmaven.repo.local="$study_root/tmp/spec-kit-evaluation/maven-repository" clean verify
java -jar target/baseline-calculator-1.0.0.jar
```

The separate GUI harnesses load the same unmodified JAR, launch its real application window and use Java Robot OS mouse and keyboard input. Swing queries locate controls/read actual field and label values. Screenshots capture the calculator window. No `setText`, `doClick` or calculator-engine calls supply acceptance outcomes. The baseline harness is reused from the original evaluation, extended only to check that Clear resets all seven operation controls.

To compile/run from the study root (use new output directories for reruns):

```sh
java_home=/Library/Java/JavaVirtualMachines/amazon-corretto-22.jdk/Contents/Home
jar=tmp/spec-kit-cr01/snapshot/github-spec-kit-calculator/target/baseline-calculator-1.0.0.jar
run_dir=research/empirical-methodology/evaluations/2026-11-05-spec-kit-cr01
"$java_home/bin/javac" -cp "$jar" -d tmp/spec-kit-cr01 "$run_dir/Cr01GuiEvaluator.java" "$run_dir/BaselineGuiEvaluator.java"
"$java_home/bin/java" -cp "tmp/spec-kit-cr01:$jar" Cr01GuiEvaluator online tmp/spec-kit-cr01/new-change-rerun
"$java_home/bin/java" -cp "tmp/spec-kit-cr01:$jar" BaselineGuiEvaluator online tmp/spec-kit-cr01/new-regression-rerun
```

Runs must execute sequentially with a real display and foreground focus; keyboard/mouse interaction from other applications can interfere. Preserve new rerun directories rather than overwrite primary observations.

## Limitations and review

Offline launch is skipped under Amin’s standing instruction, following the baseline’s unsuccessful offline setup. No network settings were changed during this CR-01 run. The omission stays in both denominators and blocks the complete regression group, CR-AC-21. The comparison baseline likewise has no verified offline Pass.

These are AI-assisted evaluator observations, not an assertion that Amin manually performed every case or that two independent human reviewers scored it. Codex had prior exposure from the baseline evaluation and read UI/build code to locate controls. Representative screenshots were visually inspected alongside the timestamped actual-output assertions. The CR-AC-08 screenshot contains a transient system overlay near the top; its entire error message remains visible and state is independently recorded. No failing primary product result was discarded or repaired.

Protocol/rubric draft-5 adoption is reported by Thanh in PR #15; a formal adoption decision reference/date remains unavailable and shared headers still describe a proposal. No adoption history, subjective rubric scores, impact-analysis metrics or effort totals are invented. BMAD CR-01 is outside this run. Results cover recorded checks, not general defect-free operation or tool-only superiority. Relevant limits: VT-C02 (GUI versus engine), VT-C06 (missing evidence), VT-I05 (environment differences), and human-review limits.
