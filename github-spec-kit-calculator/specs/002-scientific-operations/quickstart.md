# Quickstart: Scientific Operations (CR-01)

Validation and run guide for the changed application. **The build, test and run commands below were run on 2026-11-05**, first in the working tree and then from a clean clone of commit 411ab32 (`BASE-AC-15`, `CR-AC-22`). The real output is recorded in `evidence/logs/` (`cr-t037-final-verify.txt`, `cr-t043-clean-checkout-build.txt`, `cr-t043-clean-checkout-launch.txt`) and in `evidence/08-cr01-test-results.md`. The baseline commands in [../001-baseline-calculator/quickstart.md](../001-baseline-calculator/quickstart.md) are the starting point and are expected to stay the same.

Details: [plan.md](plan.md), [data-model.md](data-model.md), [contracts/calculation-contract.md](contracts/calculation-contract.md), [contracts/ui-contract.md](contracts/ui-contract.md).

## Prerequisites

Unchanged from the baseline: Windows 11, Amazon Corretto 22 at `C:\Program Files\Amazon Corretto\jdk22.0.2_9`, Apache Maven 3.9.x on `PATH`, internet access for the first build only. `JAVA_HOME` must be set explicitly in every shell used for a build (the default JDK on this machine is Temurin 25).

## Build and test (PowerShell, from the track folder)

```powershell
$env:JAVA_HOME = 'C:\Program Files\Amazon Corretto\jdk22.0.2_9'
$env:Path = "$env:JAVA_HOME\bin;$env:Path"
mvn -version        # expect: Java version 22.0.2, vendor Amazon.com Inc.
mvn -B clean verify
```

Expected: the build succeeds and all automated tests pass (116 tests on 2026-11-05: the 81 baseline tests plus 35 new ones), and `target/baseline-calculator-1.0.0.jar` exists. The jar name stays as in the baseline unless the owner decides otherwise.

## Run

```powershell
& "$env:JAVA_HOME\bin\java.exe" -jar target\baseline-calculator-1.0.0.jar
```

Expected: one window opens in the initial state, now with seven operation buttons (`+`, `−`, `×`, `÷`, `x²`, `√x`, `1/x`).

## Validation scenarios (CR-01)

| # | Scenario | Steps | Expected | Check |
|---|---|---|---|---|
| 1 | Controls visible | Launch and look at the window. | `x²`, `√x` and `1/x` are visible and identifiable. | `CR-AC-01` |
| 2 | Square | Enter `-3`, leave Second empty, click `x²`, Calculate. | Result `9` | `CR-AC-02`, `CR-AC-16` |
| 3 | Square root | Enter `2`, click `√x`, Calculate. | Result `1.4142135624` | `CR-AC-06` |
| 4 | Negative root | Enter `-1`, click `√x`, Calculate. | Error "Cannot take square root of a negative number", no result | `CR-AC-08` |
| 5 | Reciprocal | Enter `3`, click `1/x`, press Enter in the First field. | Result `0.3333333333` | `CR-AC-10`, Enter |
| 6 | Reciprocal of zero | Enter `-0.0`, click `1/x`, Calculate. | Error "Cannot divide by zero", no result | `CR-AC-13` |
| 7 | Second number ignored | Enter `4`, put `abc` in Second, click `1/x`, Calculate. | Result `0.25`, no error | `CR-AC-17`, LOG-17 |
| 8 | Empty and malformed | With each unary operation: empty First, then `abc`. | "Enter both numbers", then "Invalid number"; no crash | `CR-AC-14`, `CR-AC-15` |
| 9 | Recover | After scenario 4, change to `16`, Calculate. | Result `4` | `CR-AC-18` |
| 10 | Clear | After any unary calculation, click Clear. | Fields empty, no operation, no result, no error | `CR-AC-20` |

Scenarios 2 to 10 except the visual part of 1 are also covered by automated tests. Scenarios 1, 5 and 10 are to be observed by the owner in the real window. The square root of `-0.0` is deliberately not part of any scenario.

## Baseline re-run (`CR-AC-21`)

Run every `BASE-AC-01` to `BASE-AC-19` against the changed application, one result per check, using the table in [plan.md](plan.md). Results go into a new CR-01 results file in `evidence/`; the baseline results file stays unchanged.

## Clean-checkout check (`CR-AC-22`, `BASE-AC-15`)

After the owner requests a commit of the changed code, clone it to a new short path, set `JAVA_HOME` as above and run the build and run commands unchanged. Record whether they work and any prerequisite that had to be added.
