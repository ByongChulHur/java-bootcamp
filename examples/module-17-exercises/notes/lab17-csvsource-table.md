# Lab 17 — CsvSource Table Design

## Reference

| inputStatus | valid? |
| --- | --- |
| ACTIVE | true |
| PROSPECT | true |
| ACTVE | false |
|  | false |

## Step 2 — Extra row

Add one more invalid status of your choice.
| inputStatus | valid? |
| --- | --- |
| closed | false |

I added "closed" as an additional invalid case. This is because compare to other given cases, this checks case snsitivity which means that the system
should not accept a status just because it matches a valid value except for letter case.

## Step 3 — JDK/Maven

tests will run with JDK 21 via Maven Surefire in the timed lab.

## Step 4 — Boundary

stubbing collaborators waits for Lab 18.

## Scope
Pre-lab only — do not finish the full graded lab in this exercise.