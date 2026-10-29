# BASE-AC-01 manual check — GUI opens without network access

Date: 2026-10-27. Track: George / GitHub Spec Kit. Phase: Baseline. Commit: uncommitted working tree (repo HEAD 5e3e78e).

| Item | Value |
|---|---|
| Procedure | Owner closed the calculator, turned the network off, then launched the jar with `java -jar target\baseline-calculator-1.0.0.jar` (Corretto 22, `JAVA_HOME` pinned) |
| Evidence | `evidence/screenshots/08-offline.png`: the calculator window open, taskbar tray at 2:19 PM 10/27/2026 with a globe icon where the Wi-Fi icon normally is |
| Owner statement | "I turned the network off before launching." (owner, in the chat, 2026-10-27) |
| What the agent saw | The window open in the screenshot, and the globe icon in the tray. The icon suggests no connection but does not prove it by itself; the owner's statement is the confirmation that networking was off |
| Result | **Pass**, on the owner's confirmation together with the screenshot |
| Limits | The agent did not turn the network off or launch this run itself and could not see the screen. A screenshot of the Wi-Fi panel or a failing `ping` would make the evidence stronger; not taken |