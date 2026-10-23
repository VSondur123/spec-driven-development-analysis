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
| Evidence | `evidence/result of mvn -q compile exec:java.png` (shows the open window titled "Calculator") |
| Limits | The screenshot does not show the network state, so "offline" rests on the owner's report. It was taken before the controls existed, so it does not cover the full GUI |
| Follow-up | Repeat on the finished GUI during Story 3.3 (final acceptance), with network indicator visible in the screenshot, and record a new row with the final commit hash |
