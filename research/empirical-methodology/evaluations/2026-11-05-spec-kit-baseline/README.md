# Spec Kit baseline — setup and native reproduction

Prepared on 2026-11-05 for Amin, assisted by Codex. This is a baseline-only record; no CR-01 evaluation or comparative rubric scoring was performed.

## Snapshot and environment

- Tested commit: `88f56d0979e2fae1fb5d59e8681975e3a1fc2a27`.
- Fresh local clone: `tmp/spec-kit-evaluation/baseline/`, detached at that commit. Tracked files remained clean after the build. The clone was made with `--no-hardlinks` from the existing local repository; no remote checkout or implementation edits were needed.
- Shared test plan/input preparation revision: `a52224b`; sheet 07 copied into this directory without changing procedures or expected outcomes. LOG-12–15 additions are included in that common sheet/shared acceptance revision.
- macOS 27.0.1, Apple Silicon, Amazon Corretto 22.0.2, Maven 3.9.16. Raw environment and exact SHA are in `environment.txt`.
- Portable Maven: `tmp/spec-kit-evaluation/tools/apache-maven-3.9.16/`.
- Isolated dependency cache: `tmp/spec-kit-evaluation/maven-repository/`.
- Protocol/rubric draft-5 adoption is reported by Thanh in PR #15. The decision date/reference is still missing; shared document headers still say proposal. This record does not invent an adoption date or assign formal scores.

## Observed execution

1. The first native build attempt could not resolve Maven Central because the execution sandbox restricted networking. Exit 1; retained as an environment-blocked attempt in `build-primary.txt`. No product failure inferred.
2. Retried the same build with network access. Exit 0; `BUILD SUCCESS`; 81 tests, 0 failures, 0 errors, 0 skipped. Full output: `build-network-retry.txt`; class totals: `native-test-summary.txt`. This rerun addresses the environment restriction and does not replace the initial raw record.
3. The POM explicitly configures `java.awt.headless=true`. The native Swing-panel tests do not establish real-window visibility, OS keyboard interaction, or offline launch.
4. A desktop launch inside the sandbox exited 134 without output. A retry outside the sandbox launched the JAR, and Amin confirmed that its window was visible. Output is captured in `launch-networked.txt`. Networking was enabled; this is not the BASE-AC-01 offline check.

5. Java Robot then drove real OS mouse clicks and keyboard events against the same frozen JAR in a displayed calculator window. There are 35 timestamped GUI observations, including all required subcases of the performed checks. All 18 performed parent criteria passed. BASE-AC-15 combines the clean build, Amin’s visible-launch confirmation, and the independently observed displayed window. No acceptance Pass is derived solely from the headless suite.
6. A partial initial GUI automation attempt did not activate the Java app, so OS input was not delivered. Preserved raw assertions and screenshots are in `invalid-input-delivery-attempt/`. They are excluded as invalid input-delivery observations. The harness was corrected to request application foreground, a separate visible input probe succeeded, then the complete valid GUI run took place from 15:47:59 to 15:49:35 EET. No implementation source or POM was changed.
7. Offline launch remains unverified. Setup briefly disabled Wi-Fi but route output was inconclusive; the offline GUI was not launched. The `finally` block restored Wi-Fi. Amin requested no further offline attempt. BASE-AC-01 is Blocked, retained in D=19.

**Result: 18 Pass / 0 Fail / 1 Blocked / 0 Not run.** Verified coverage and complete execution are 18/19 (94.74%); all 18 performed parents passed. The completed sheet contains the actual outputs, per-subcase evidence and limitations. This is AI-assisted evaluation with explicit provenance, not a claim that Amin manually executed every scenario or that two humans independently reviewed it.

## Commands

From the study repository root, build with:

```sh
export JAVA_HOME=/Library/Java/JavaVirtualMachines/amazon-corretto-22.jdk/Contents/Home
export PATH="$JAVA_HOME/bin:$PATH"
study_root="$PWD"
cd tmp/spec-kit-evaluation/baseline/github-spec-kit-calculator
"$study_root/tmp/spec-kit-evaluation/tools/apache-maven-3.9.16/bin/mvn" \
  -B -Dmaven.repo.local="$study_root/tmp/spec-kit-evaluation/maven-repository" clean verify
```

The shared native quickstart uses PowerShell/Windows. The commands above adapt the shell and paths to macOS and isolate Maven's cache; source and POM are unchanged. Native build command: `clean verify`. Native launch command: `java -jar target/baseline-calculator-1.0.0.jar`.

To launch from the repository root:

```sh
sh research/empirical-methodology/evaluations/2026-11-05-spec-kit-baseline/run-calculator.sh
```

Keep tools, clone and cache under `tmp/`; they are ignored by Git. This launch script requires those local prepared files.

## Evidence and reproduction

- `07-evaluator-execution-sheet.md`: completed evaluator-owned sheet; the populated track copy links back to this evidence.
- `gui-observations.tsv`: raw timestamped expected/actual GUI records and outcome assertions.
- `screenshots/`: calculator-window captures; representative captures were visually inspected by Codex alongside all component-state assertions.
- `BaselineGuiEvaluator.java`: evaluator-owned automation source. OS Robot supplies input; Swing queries only locate controls and read state. No direct `setText`, `doClick`, or calculator-engine invocation supplies acceptance outcomes.
- `invalid-input-delivery-attempt/`: excluded partial automation attempt, preserved with its original assertions and images.
- `offline-network-state.txt` and `run-offline-check.py`: unsuccessful offline prerequisite setup and restoration record; no offline Pass.
- `build-primary.txt`, `build-network-retry.txt`, `native-test-summary.txt`, `environment.txt`: native build and environment evidence.

Compile and reproduce the online GUI checks from the repository root (this repeats the run and should use a new evidence directory):

```sh
java_home=/Library/Java/JavaVirtualMachines/amazon-corretto-22.jdk/Contents/Home
jar=tmp/spec-kit-evaluation/baseline/github-spec-kit-calculator/target/baseline-calculator-1.0.0.jar
run_dir=research/empirical-methodology/evaluations/2026-11-05-spec-kit-baseline
"$java_home/bin/javac" -cp "$jar" -d tmp/spec-kit-evaluation "$run_dir/BaselineGuiEvaluator.java"
"$java_home/bin/java" -cp "tmp/spec-kit-evaluation:$jar" BaselineGuiEvaluator online tmp/spec-kit-evaluation/new-rerun
```

The real desktop GUI must be available and allowed to take foreground focus. Keep mouse/keyboard free while Robot drives the calculator. BASE-AC-19 uses Enter in First number. These checks do not replace later human review or establish behaviour outside the recorded inputs. Native test counts are context, not a workflow quality score. No CR-01 results or formal rubric judgments are included.

The Spec Kit baseline sheet and Amin’s response in LOG-23 have been updated locally. BMAD’s results, test procedures, acceptance criteria and implementation snapshots are unchanged.
