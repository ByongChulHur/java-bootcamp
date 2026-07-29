Northstar CRM — Lab 8 Skeleton
Overview

Maven Java skeleton for the Northstar Customer Management Platform. Layered structure only — no Spring, JPA, or HTTP yet.

How to compile/run
mvn clean compile
java -cp target/classes com.northstar.crm.Main

Expected output:
Northstar CRM skeleton — Lab 8
Packages: controller, service, repository, entity, dto, config, exception
Examples: CUS-1001 Amina Khan ACTIVE | CUS-1002 Ravi Singh PROSPECT

Docs
docs/CODING-STANDARDS.md
docs/layer-flow.md

Design decisions
Why layers: controller, service, repository, entity, dto, config, exception keep transport, business rules, and persistence separated so each can change or be tested independently.
Why stubs: Lab 8 proves the structure compiles before any real behavior is added. Stub methods throw UnsupportedOperationException on purpose — that is expected, not a bug.