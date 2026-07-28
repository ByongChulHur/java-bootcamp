customer-management-platform/
├── pom.xml
├── docs/
│   └── CODING-STANDARDS.md
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/northstar/crm/
│   │   └── resources/
│   │       └── application.properties
│   └── test/
│       ├── java/
│       │   └── com/northstar/crm/
│       └── resources/
└── target/


Step 1 Classify these files

| File | Destination |
| ---- | ----------- |
| `Customer.java` | `src/main/java/com/northstar/crm/...` |
| `CustomerServiceTest.java` | `src/test/java/com/northstar/crm/...` |
| `application.properties` | `src/main/resources/` |
| `sample-customers.json` (test only) | `src/test/resources/` |
| `CODING-STANDARDS.md` | `docs/` |
| `Customer.class` | generated under `target/classes/` |

Step 3
Explain Target.
Target can be genereated from source by Maven which means is that it can be deleted and rebuilt, so it should be ignored rather than committed.

Step 4 — Spot the mistakes
Case 1 Production hava in src/test/java
Maven only packages src/main into the final production JAR.
Code placed in src/test/java gets excluded from the shipped application, even though it looks like it works during tests.

Case 2 Password committed in application.properties.
Committing files that are visible to everyone and hardcoding the secret into those area is a serious security problem.

Case 3target/classes
It is related with what I mentioned in step 3. Since if we hand edit the target/classes which 
will fully regenerated on every build, so manual edits are silently wiped out on the next mvn compile.

Case 4 test fixtures in production resources without a runtime need.
src/main/resources is packaged into the shipped production artifact which means that the test related files should not be included to following area.
It should stay at src/test/resources.