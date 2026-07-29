# Lab 12 — Target API Sketch

## Step 1 — Methods

- `CustomerService.findById(String customerId)`
- `CustomerService.activateProspect(String customerId)` // CUS-1002 Ravi: PROSPECT -> ACTIVE
- `CustomerService.validateStatus(String status)` // maybe -- confirm before adding

## Step 2 — Ravi path
Calling activateProspect("CUS-1002") moves Ravi's status from PROSPECT to ACTIVE.



## Step 3 — Keep out

- SOAP endpoints
- Spring annotations like `@Controller`, `@RestController`
- Actual persistence implementation (e.g. database connection code)

## Step 4 — Prep boundary

Write: *Do not complete full Lab 12 refactor in pre-lab.*

## Scope
Pre-lab only — do not finish the full graded lab in this exercise.