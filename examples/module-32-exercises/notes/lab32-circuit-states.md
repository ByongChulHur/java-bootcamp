# Lab 32 — Circuit States

## Step 1 — Closed
Normal calls flow through to Account Profile; both successes and failures are counted within the sliding window, but a success does not reset the failure count. Once the failure rate exceeds the configured threshold, the breaker moves to OPEN.

## Step 2 — Open
Calls fail fast without ever reaching Account Profile — the fallback (AccountSummary.unavailable) is returned immediately. This protects Account Profile from being hammered while it is unhealthy and gives it time to recover. After the wait duration elapses, the breaker moves to HALF_OPEN.

## Step 3 — Half-open
A limited number of trial calls are allowed through to probe whether Account Profile has recovered. If all trial calls succeed, the breaker closes and normal traffic resumes. If any trial call fails, the breaker reopens and waits again before the next probe.

## Step 4 — Draw
[CLOSED] --failure threshold exceeded--> [OPEN]
[OPEN] --wait duration elapses--------> [HALF-OPEN]
[HALF-OPEN] --trial calls all succeed-------> [CLOSED]
[HALF-OPEN] --any trial call fails----------> [OPEN]

## Scope
Pre-lab only — do not finish the full graded lab in this exercise.