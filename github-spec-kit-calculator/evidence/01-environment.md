# Environment record — George / GitHub Spec Kit track

Recorded: 2026-10-27, about 06:15 (+02:00). Append dated entries; do not rewrite history.

## Machine and toolchain

| Item | Value |
|---|---|
| Operating system | Windows 11 Home 10.0.26200, amd64 |
| JDK for builds | Amazon Corretto 22.0.2.9.1 (OpenJDK 22.0.2) |
| `JAVA_HOME` for builds | `C:\Program Files\Amazon Corretto\jdk22.0.2_9` (pin explicitly in every documented command) |
| Default JDK on the machine | Eclipse Temurin 25.0.4.1 at `C:\jdk-25.0.4.1+1` (used by plain `java`/`mvn` when `JAVA_HOME` is unset) |
| Build tool | Apache Maven 3.9.16 (`C:\apache-maven-3.9.16`) |
| GUI toolkit | Not chosen yet (decided during planning) |
| Spec Kit | specify-cli 1.2.3, tag `v1.2.3`, commit `5ea8b1cf0a38699340a38e00d02770308997d8c0` |
| Spec Kit install method | `uv tool install specify-cli --from git+https://github.com/github/spec-kit.git@v1.2.3` |
| uv / Python / Git | uv 0.12.19; system Python 3.12.10 (the Spec Kit CLI runs on its own Python 3.14.7); Git 2.55.0 |
| Repository commit at start | `258a6d0` (main) |

## Coding agent and model

| Item | Value |
|---|---|
| Agent | Claude Code, VS Code extension |
| Model | `claude-opus-5-5` at session start, switched to `claude-sonnet-5-5` during setup. Record the model used for each Spec Kit step in the decisions log. |
| Spec Kit integration | `claude` (chosen by George on 2026-10-27; Claude Code is the agent already in use and `specify check` reports it available) |

## How the versions were chosen

- Corretto 22 matches the common environment suggested in `shared/experiment-log.md` LOG-07. Installed with `winget install Amazon.Corretto.22.JDK` (22.0.2.9, the only 22 build offered). It is not an LTS release.
- Maven 3.9.16 was already installed. Not changed.
- Spec Kit v1.2.3 was the latest release on 2026-10-27 (GitHub releases API, published 2026-10-22).
- Deviation from LOG-07: the suggested OS is macOS 15.x; this track runs on Windows 11. Noted in the experiment log.

## Verification (observed)

- Corretto `java -version`: openjdk 22.0.2, Corretto-22.0.2.9.1.
- `mvn -version` with `JAVA_HOME` pinned to the Corretto path: Java 22.0.2, vendor Amazon.com Inc.
- `specify version`: CLI Version 1.2.3, Platform Windows.
- `specify check`: Claude Code reported available.
- Not yet checked: behaviour in a fresh terminal (PATH may differ from this session).

## Existing files at start

Repository at commit `258a6d0`. This track's folder held only `README.md`. No Spec Kit project existed.
