# Lab 21 — Cardinality Anti-Patterns

| Label | OK? |
| --- | --- |
| outcome=success|failure | yes |
| customerId=CUS-1001 | no |
| correlationId=lab-request-001 | no  |

## Where ids go

Ids such as customerId and correlationId must never be used as metric labels,
because each unique id value creates a brand-new time series that never gets
reused. 

## Good metric sketch

crm.customer.create{outcome="success"}
crm.customer.create{outcome="failure", reason="validation"}

This tracks the same information (create attempts, success vs failure) without
ever putting a customerId or correlationId in the metric. If we need to know
which specific customer failed, we trace it through the correlationId in the
logs, not through the metric label.

## Scope

Pre-lab only.