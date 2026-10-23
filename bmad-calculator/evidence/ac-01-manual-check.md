# BASE-AC-01 manual check (GUI opens without network access)

| Field | Value |
|---|---|
| Test ID | BASE-AC-01 |
| Track / phase | Varun / BMAD, Baseline |
| Date | 2026-10-23, about 18:15 EEST |
| Performed by | Varun (reported to the agent; the agent did not observe it) |
| Command | `mvn -q compile exec:java` with `JAVA_HOME` pinned to Corretto 22 |
| Condition | Wi-Fi off (as reported by Varun) |
| Build under test | Story 1.1, commit `f5ae2f0` plus uncommitted review patches at that time: window titled "Calculator", no controls yet |
| Status | **Pass** (owner-observed) for "window opens", build state of Story 1.1 |
| Evidence | `evidence/result of mvn -q compile exec:java.png` (removed from the repo by Varun on 2026-10-23; replaced by the screenshots in the second table below) |
| Limits | The screenshot does not show the network state, so "offline" rests on the owner's report. It was taken before the controls existed, so it does not cover the full GUI |
| Follow-up | Repeat on the finished GUI during Story 3.3 (final acceptance), with network indicator visible in the screenshot, and record a new row with the final commit hash |

## Repeat on the final GUI (2026-10-23)

| Field | Value |
|---|---|
| Test ID | BASE-AC-01 (final GUI) |
| Date | 2026-10-23 |
| Performed by | Varun (reported to the agent; the agent viewed the screenshots but did not observe the run) |
| Command | `mvn -q compile exec:java` with `JAVA_HOME` pinned to Corretto 22 |
| Condition | Wi-Fi off (as reported by Varun) |
| Build under test | Commit `943c73f` (all 9 stories) |
| Status | **Pass** (owner-observed): the full GUI opens with all controls |
| Evidence | `evidence/screenshot/add.png` (`1212 + 21` = `1233`), `evidence/screenshot/substract.png` (`12 - 1212` = `-1200`), `evidence/screenshot/error.png` (`12.22222222`, empty second operand: "Enter both numbers") |
| Limits | The screenshots show only the application window, not the network indicator, so "offline" rests on the owner's report. The screenshots also show correct results for BASE-AC-04-style addition and subtraction, and the empty-operand error (BASE-AC-11); those are already covered by automated tests. |
