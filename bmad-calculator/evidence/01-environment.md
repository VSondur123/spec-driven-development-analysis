# Environment and Version Record — Varun / BMAD Track (Baseline)

| Field | Value |
|---|---|
| Date / start time | 2026-10-23 16:54 EEST |
| Operating system | macOS 15.7.1 (build 24G231), Apple Silicon (arm64) |
| JDK used for the track | Amazon Corretto 22.0.2 (`/Users/varunsondur/Library/Java/JavaVirtualMachines/corretto-22.0.2/Contents/Home`) |
| Build tool | Apache Maven 3.9.16 |
| GUI toolkit | Java Swing (JDK built-in, no extra dependency) |
| Test framework | JUnit 5 (planned; exact version recorded when `pom.xml` is created) |
| BMAD version | 6.12.4 (modules: core, bmm), installed 2026-10-23T13:04Z, built-in source |
| BMAD IDE target | kimi-code (installer manifest); skills live in `.agents/skills/` |
| Coding agent / model | Claude Code, model Claude Sonnet 5.5 (`claude-sonnet-5-5`) acting as operator-proxy for Varun |
| Repo commit at start | `cb1a051b4d9b17a1b91da59daa8baa2e8707d0f1` (branch `main`); `bmad-calculator/.agents/` and `bmad-calculator/_bmad/` untracked at start |
| Existing files at start | `README.md` (instructions), empty `evidence/`, `src/`, `tests/`, `_bmad-output/` |

## Environment notes / deviations

- **JDK mismatch found.** `java -version` reports Corretto 22.0.2, but `mvn -v` reports Homebrew OpenJDK 26.0.1 because `JAVA_HOME` is unset. To make builds reproducible, every build/test command in this track sets `JAVA_HOME` explicitly to Corretto 22.
- Shared files `shared/experiment-protocol.md`, `shared/evaluation-rubric.md`, `shared/experiment-log.md` were empty at start (not frozen). `shared/change-request-CR-01.md` has deliberately not been read.
- BMAD's installed native flow (from `_bmad/_config/bmad-help.csv`): Product Brief (optional) → PRD (required) → UX (optional) → Architecture (required) → Epics & Stories (required) → Sprint Planning (required) → Build → Code Review / QA (optional).
