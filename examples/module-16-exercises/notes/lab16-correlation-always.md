# Lab 16 — Correlation on Every Error

## Step 1 — Success path

Activate Ravi success still echoes/logs lab-request-001.

## Step 2 — Failure path

Not-found CUS-9999 response includes same correlation field.

## Step 3 — Missing header

Policy idea: generate a correlation if missing — note for later labs.
If the incoming request has no Correlation Id header, the server should generate a new correlation ID instead of leave it blank.
## Scope
Pre-lab only — do not finish the full graded lab in this exercise.