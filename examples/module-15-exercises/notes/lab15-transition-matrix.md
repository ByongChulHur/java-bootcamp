# Lab 15 — Transition Matrix

## Reference

| From | To | Allowed? |
| --- | --- | --- |
| PROSPECT | ACTIVE | yes (Ravi activate) |
| ACTIVE | ACTIVE | no-op  already active, return current state without error  |
| ACTIVE | PROSPECT | no |

## Step 2 — Amina

CUS-1001 already ACTIVE which means activate again should be no-op based on my policy.

## Step 3 — Illegal list

1. ACTIVE -> PROSPECT (If it is an active customer already make it to Prospect status should not be allowed)
2. PROSPECT -> PROSPECT (It's basically retriggering with no real transition target. So I decide this should be rejected)

## Step 4 — Boundary

exception HTTP mapping waits for Lab 16.

## Scope
Pre-lab only — do not finish the full graded lab in this exercise.