Step 1 & 2 — Classify Each Dependency

controller → service: Acceptable — the controller delegates business work inward to the service it owns.

service → repository: Acceptable — the service asks the repository to persist or find data it needs.

repository → entity: Acceptable — the repository works with domain objects to save or find them.

entity → controller: Problematic — domain state should not depend on the transport layer above it.

repository → controller: Problematic — persistence should not depend on the presentation layer above it.

service → DTO: Needs context — acceptable for this lab's simple mapping, but the service should not leak transport-specific shapes beyond that.

DTO → repository: Problematic — a boundary model should not perform storage; that inverts its role.

Step 3 Detect and Repair a Cycle

Bad flow: controller → service → repository → controller.

In the given "Bad Flow", there are no starting point, the changes can go both directions which can be a huge problem. For example, a change in controller
could force a change in the repository. Layers can no loger be tested in isolation too since the testing the repository now requires understanding
the controller it depends on. 

Repaired flow: controller → service → repository → entity.

Step 4 — Write one architecture rule
Higher-level request handling may call inward services and repositories.
Domain/entity and repository packages must not import controller classes.