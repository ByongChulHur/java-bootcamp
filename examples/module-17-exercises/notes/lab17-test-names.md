# Lab 17 — Expressive Test Names

## Step 1 — Pattern

Use methodName_state_expectedOutcome style.

## Step 2 — Examples

Write names for Amina already ACTIVE reject, Ravi PROSPECT activate success, CUS-9999 not found.

- activateCustomer_whenAminaAlreadyActive_thenRejects
- activateCustomer_whenRaviIsProspect_thenActivatesSuccessfully
- activateCustomer_whenCustomerIdNotFound_thenThrowsNotFoundException

## Step 3 — Anti-name

Reject names like `test1` / `testActivate`.
These examples are not good choice since the following names gives any information about which fixture or expected outcome is being tested.

## Scope
Pre-lab only — do not finish the full graded lab in this exercise.