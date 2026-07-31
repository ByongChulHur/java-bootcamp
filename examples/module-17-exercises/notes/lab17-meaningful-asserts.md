# Lab 17 — Meaningful Asserts

## Step 1 — Weak

`assertNotNull(result)` after activate — label weak.
The following is seens as "weak" and this is because if a bug caused the status filed to never be updated to ACTIVE, the shown result still be non-null and
this makes the following assertion to be passed.
## Step 2 — Strong

Assert Ravi id CUS-1002 and status ACTIVE after activate.

If the status update is broken the this assertion fails immediately instead of silently passing the case. C

## Step 3 — Exception assert

Plan `assertThrows` for activating Amina under your illegal policy.

call `assertThrows(IllegalStateException.class, () -> 
service.activateCustomer("CUS-1001"))` because Amina is already
ACTIVE, so activating her again should be rejected as an illegal
transition.

## Step 4 — Prep only

Write: *Prepare for Lab 17; do not complete full suite now.*

## Scope
Pre-lab only — do not finish the full graded lab in this exercise.