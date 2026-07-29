# Lab 12 — Equals vs ==

## Reference

| Check | Use | Why |
| --- | --- | --- |
| status ACTIVE? | Objects.equals / enum | String identity is unsafe |
| same Customer instance? | == | Reference equality only |
| id CUS-1001? | equals | Value equality |

## Step 2 — Bad snippet

Write a bad line: `if (status == "ACTIVE")` and label it Fail.

// Fail — unsafe String identity comparison
if (status == "ACTIVE") {
customer.markActive();
}

## Step 3 — Good snippet

Write a good conceptual check for Amina ACTIVE using equals or enum.
// Good — Amina CUS-1001, safe status check
if (Objects.equals(customer.getStatus(), "ACTIVE")) {
// handle Amina's active-customer logic safely
}

## Step 4 — JDK note

Note: prefer enums on JDK 21 sketches when status set is closed.

JDK 21 sketches should prefer an enum. Enums make invalid values impossible at compile time, unlike Strings which can
contain any typo.

## Scope
Pre-lab only — do not finish the full graded lab in this exercise.