# Build, test and run (BMAD track baseline)

## Prerequisites

- JDK 22 (tested with Amazon Corretto 22.0.2) and Apache Maven 3.9.x.
- `JAVA_HOME` must point at the JDK 22 home. Without it Maven may use another installed JDK (on the author's machine plain `mvn` picked Homebrew OpenJDK 26 while `java` was Corretto 22).
- Network is needed only the first time, to download Maven dependencies. The application itself uses no network.

## Commands

Run from the `bmad-calculator/` directory.

```bash
# macOS: select an installed JDK 22. Otherwise set JAVA_HOME to your JDK 22 home directory.
export JAVA_HOME=$(/usr/libexec/java_home -v 22)
"$JAVA_HOME/bin/javac" -version   # expect: javac 22.x
mvn -q test                       # build and run all tests
mvn -q compile exec:java          # launch the calculator window (needs a display)
```

Evaluation machine used by the BMAD track: `JAVA_HOME=/Users/varunsondur/Library/Java/JavaVirtualMachines/corretto-22.0.2/Contents/Home`.

## Notes

- GUI tests are skipped automatically when no display is available (headless).
- The application itself needs a display; launching it on a headless host (for example over SSH) ends with a `HeadlessException`.
- Closing the window ends the application, and with `exec:java` that also ends the Maven process.
- BASE-AC-01 (GUI opens without network access) is a manual check: disable networking, run the launch command, and record a screenshot or short note in `evidence/`.
