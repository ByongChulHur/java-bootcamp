# Lab 23 — Auto-Config Versus Ownership

| Boot / auto-config gift | Still owned by the team |
| --- | --- |
| Embedded Tomcat + DispatcherServlet (server auto-starts, requests auto-routed) | Customer create/get business rules (what counts as valid, what statuses are allowed) |
| Jackson JSON mapping (Java objects auto-converted to JSON) | Fixture IDs and data — CUS-1001 (Amina Khan, ACTIVE), CUS-1002 (Ravi Singh, PROSPECT) |
| Actuator health infrastructure (the /actuator/health endpoint mechanism itself) | Which endpoints to expose publicly vs restrict (e.g. /actuator/env should be locked down) |

## One-sentence rule
Auto-configuration wires the plumbing (server, routing, JSON), but it never invents business logic — that stays owned by the team.

## Scope
Pre-lab only.