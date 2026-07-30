# Lab 14 — Annotate Paper DTO

## Reference

| Field | Constraint idea |
| --- | --- |
| fullName | required, non-blank |
| status | optional on create; default PROSPECT |
| customerId | server-assigned or pattern CUS-#### |

## Step 2 — Paper annotations

record CreateCustomerRequest(
@NotBlank String fullName,
String status,
@Pattern(regexp = "CUS-\\d{4}") String customerId
) {}

fullName is required, so it uses @NotBlank. status is optional on create;
the server defaults it to PROSPECT, so no constraint is needed. customerId
is normally server-assigned, but if a client ever supplies it, it must
match the CUS-#### pattern.

## Step 3 — No Spring yet

Explicit: do not wire `@Valid` on a controller in this pre-lab.

## Step 4 — Correlation

The correlation ID (lab-request-001) is not a DTO field. It is passed as
an HTTP header and logged for traceability, not part of the request/response body.

## Scope
Pre-lab only — do not finish the full graded lab in this exercise.