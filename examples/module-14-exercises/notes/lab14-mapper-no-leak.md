# Lab 14 — Entity vs DTO

## Step 1 — Definitions

Entity is the persistence shape used internally to store and represent domain
state in the database (via ORM). It may contain business logic, relationships
to other entities, and internal-only fields.

DTO is the API contract shape used to transfer data across the API boundary
to clients. It contains only the fields the consumer needs, has no business
logic, and has no persistence annotations.

## Step 2 — Leak risks

1. Internal flags / audit columns — fields like `internalRiskFlag` or
   `auditCreatedAt` are used for internal business rules but have no reason
   to be visible to an API consumer. If we return the entity directly, these
   leak out.
2. Lazy relations — entities often hold references to other entities
   (e.g. a Customer entity linked to its Orders). Serializing an entity
   directly can accidentally trigger lazy loading and expose nested internal
   objects (or even throw LazyInitializationException), which a flat DTO
   avoids entirely.

## Step 3 — Fixture DTO fields

For customer Amina Khan (CUS-1001, ACTIVE), the response DTO should only
contain: customerId, fullName, status.

record CustomerDto(String customerId, String fullName, String status) {}

No persistence annotations (e.g. @Entity, @Id, @Column) belong on this DTO —
it is a pure data-carrying record for the API boundary only.

## Scope
Pre-lab only — do not finish the full graded lab in this exercise.