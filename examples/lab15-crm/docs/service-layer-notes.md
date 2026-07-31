# Lab 15 — service layer notes

## Status transition table

| From | Allowed to |
| ---- | ---------- |
| PROSPECT | ACTIVE, CLOSED |
| ACTIVE | SUSPENDED, CLOSED |
| SUSPENDED | ACTIVE, CLOSED |
| CLOSED | (none) |

## Wiring

- Shared `InMemoryCustomerRepository` instance for `CustomerValidator` + `DefaultCustomerService`
- No `HashMap` / JDBC / `EntityManager` in the `service` package

## Same-status call policy

When `changeStatus(id, sameStatus, correlationId)` is called with a status
the customer is already in, we need to decide how it's handled.

**Decision: Reject**

I personally think Reject is right decision for this situation. Because
the ALLOWED map in CustomerValidator do not have transition from any
status to itself, for example ALLOWED.get(ACTIVE) does not contain ACTIVE
inside it, and this means when changeStatus called with same status it
automatically throw IllegalStateException without we writing extra code
for it.

Also, I decide to keep this behavior as official policy instead of adding
separate no-op logic, because a repeated same-status request should get
clear error message rather than silently pass through as success. Because
of this reason, keeping the transition table as single consistent set of
rules inside ALLOWED map, without carving out extra exception case for
no-op, keeps the code simpler and more predictable for later maintain.


