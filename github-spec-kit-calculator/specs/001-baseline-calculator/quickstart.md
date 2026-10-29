# Quickstart: Baseline Calculator

Validation and run guide. **The build, test and run commands below were run on 2026-10-27**, first in the working tree and then from a clean checkout of commit 419f92f (`BASE-AC-15`). The real output and any correction are recorded in `evidence/` (`evidence/logs/`, `evidence/06-test-results.md`).

Details: [plan.md](plan.md), [data-model.md](data-model.md), [contracts/calculation-contract.md](contracts/calculation-contract.md), [contracts/ui-contract.md](contracts/ui-contract.md).

## Prerequisites

- Windows 11
- Amazon Corretto 22 at `C:\Program Files\Amazon Corretto\jdk22.0.2_9`
- Apache Maven 3.9.x on `PATH` (3.9.16 is recorded in `evidence/01-environment.md`)
- Internet access for the first build only, to download Maven dependencies. The running application needs none.

The machine's default JDK is Temurin 25, so `JAVA_HOME` must be set explicitly in every shell used for a build.

## Build and test (PowerShell, from the track folder)

```powershell
$env:JAVA_HOME = 'C:\Program Files\Amazon Corretto\jdk22.0.2_9'
$env:Path = "$env:JAVA_HOME\bin;$env:Path"
mvn -version        # expect: Java version 22.0.2, vendor Amazon.com Inc.
mvn -B clean verify
```

Expected: the build succeeds, all automated tests pass, and `target/baseline-calculator-1.0.0.jar` exists. Test counts and the log are saved under `evidence/`.

## Run

```powershell
& "$env:JAVA_HOME\bin\java.exe" -jar target\baseline-calculator-1.0.0.jar
```

Expected: one window opens in the initial state (fields empty, no operation selected, no result, no error).

## Validation scenarios

| # | Scenario | Steps | Expected | Check |
|---|---|---|---|---|
| 1 | Launch offline (manual) | Turn networking off. Run the command above. | The window opens. Keep a screenshot or note. | `BASE-AC-01` |
| 2 | Add | Enter `7` and `5`, click +, click Calculate. | Result `12` | `BASE-AC-04` |
| 3 | Divide | Enter `1` and `3`, click ÷, press Enter in a field. | Result `0.3333333333` | `BASE-AC-18`, `-19` |
| 4 | Missing operation | Enter `7` and `5`, click Calculate. | Error "Select an operation", no result | `BASE-AC-17` |
| 5 | Invalid number | Enter `1,5` and `2`, click +, click Calculate. | Error "Invalid number", no result | `BASE-AC-12` |
| 6 | Division by zero | Enter `7` and `0`, click ÷, click Calculate. | Error "Cannot divide by zero", no result | `BASE-AC-13` |
| 7 | Recover | After scenario 5, change `1,5` to `1.5`, click Calculate. | Result `3.5` | `BASE-AC-14` |
| 8 | Clear | After any calculation, click Clear. | Fields empty, no operation, no result, no error | `BASE-AC-10` |

Scenarios 2 to 8 are also covered by the automated tests. The full list of checks (`BASE-AC-01` to `BASE-AC-19`) and the result template are in `shared/acceptance-criteria.md`; results are recorded per check as Pass, Fail, Blocked or Not run.

## Clean-checkout check (`BASE-AC-15`)

Clone the repository to a new folder, set `JAVA_HOME` as above, and run the build and run commands. Record whether they work unchanged and any prerequisite that had to be added.
