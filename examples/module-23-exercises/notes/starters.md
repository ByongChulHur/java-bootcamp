# Lab 23 — Boot Starters Inventory

| Starter | Why for CRM lab |
| --- | --- |
| spring-boot-starter-web | Exposes REST endpoints (/api/customers create/get) and runs an embedded Tomcat server, with Jackson auto-converting responses to JSON |
| spring-boot-starter-actuator | Adds the /actuator/health endpoint so we can smoke-test that the CRM app started successfully |
| spring-boot-starter-test | Provides JUnit 5, MockMvc, and Spring Test tools to write the context-load test and the HTTP integration test for CUS-1001/CUS-1002 |
| spring-boot-starter-validation (optional) | Enables @Valid on request DTOs so invalid customer data (e.g. missing name) returns 400 automatically |

## Scope
Pre-lab only.