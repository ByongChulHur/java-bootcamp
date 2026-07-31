# Lab 16 — Failure to Status Map

## Reference

| Failure | Status idea |
| --- | --- |
| CUS-9999 not found | 404 / SOAP Client fault |
| Activate Amina illegal transition | 409 or 422 |
| Validation blank name | 400 |
| Unexpected bug | 500 (generic message) |

## Step 2 — Choose conflict

Pick 409 vs 422 for illegal activate and write one reason.
I think 409 fits better because the request itself is valid however it conflicts with the customer's current's state because Amina is already active.
This is a state conflict situation which 409 is meant for.

## Step 3 — Never

If it returns 200, the client will assume it succeeded, which means that
even though it actually failed, the result will show as success. Because
of this, each failure above must return its own proper HTTP status code
so the client can figure out whether it succeeded or not.

## Scope
Pre-lab only — do not finish the full graded lab in this exercise.