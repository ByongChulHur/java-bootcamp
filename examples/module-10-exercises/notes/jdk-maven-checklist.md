JDK / Maven Version Check

1. Commands
- `java -version` → should show 21.x
- `mvn -version` → Java version should be 21.x

2. PATH trap (actually happened)
- `java --version` showed 25 instead of 21, even though JDK 21
  was listed above JDK 25 in PATH.
- Root cause: JAVA_HOME was still pointing to jdk-25.
- Fix: check both PATH order and JAVA_HOME, then restart terminal/IDE.

3. Workspace
- Files saved in: java-bootcamp\examples\module-10-exercises\notes\

4. Out of scope
- Do not run `mvn clean compile` until JDK version is confirmed 21.x
  and the timed Lab 10 session begins.