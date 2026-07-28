## Failure Experiments

| # | Experiment | Observed | Restored |
|---|---|---|---|
| 1 | Bad spring.version | Dependency resolution failure | Yes, restored to 6.2.3 |
| 2 | assertTrue(false) in PlaceholderTest | Test failed, verify failed | Yes, restored to assertTrue(true) |
| 3 | mvn install twice | Second install succeeded, snapshot overwritten | N/A (no restore needed) |
| 4 | Cold vs warm verify | First run slower (downloads) | N/A |
| 5 | Removed test scope from JUnit | JUnit appeared as compile scope in tree | Yes, restored <scope>test</scope> |

1. Which design decision most affected build correctness?

Using test scope for JUnit was the key decision — without it, JUnit would ship inside the production JAR.

2. Which failure was hardest to diagnose?

Personally the bad spring.version was hardest, since the error came from Maven Central, not my own code.

3. What evidence proves the lifecycle walk was real?

docs/lifecycle-evidence.md shows each phase (validate through install) run and recorded separately, not just one package call.

4. What breaks first at ten times the dependency count?

The dependency tree gets much harder to read, and version conflicts between transitive dependencies become more likely.

5. Which concern should move to shared infrastructure?

Dependency resolution should move to a shared artifact repository (Nexus/Artifactory) so every developer and CI agent uses the same cache.

6. What must change before real customer data is used?

Real secrets must never live in the dev profile — they belong in a proper secrets manager, not pom.xml.

7. How does this lab connect to Lab 8 and Lab 10+?

For the Lab 8, it was mainly focusing on built the package structure. After that in Lab 9, we makes it buildable with Maven
Lab 10+ will add real logic on top of this working build.

8. What signal matters most when verify fails?

The Surefire test failure output, since most verify failures come from a failing test or a dependency resolution error.

9. Why is test scope on JUnit more than a style preference?

It's functional and reason for it is that test scope keeps JUnit out of the final JAR entirely, while compile scope would ship it into production.

10. When Spring Boot arrives, what stays stable vs changes first?

Coordinates, profiles, and JUnit's test scope stay stable. The spring-context placeholder gets replaced by Spring Boot starters first.


Architecture Note — NOW vs LATER

Lab 9 was mainly focusing on build-time stuff — making sure pom.xml coordinates, dependencies, plugins, and lifecycle phases all work together to produce a reliable customer-service.jar. Spring is only there as a placeholder dependency to practice scopes, no real @SpringBootApplication code yet.

React, Spring Boot API, PostgreSQL, and Kafka are all future work (Lab 22+). Building this JAR doesn't actually create a customer like CUS-1001 in any database — it just proves the build itself works.