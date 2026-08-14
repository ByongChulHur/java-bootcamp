# Lab 32 — Fallback Contract

## Step 1 — Fields kept
- customerId — always echoed back so the UI knows which customer this response is for
- displayName — sourced from CRM's own data, not from Account Profile, so it's safe to keep
- status — shown as UNKNOWN rather than omitted, so the UI has a defined value to render

## Step 2 — Fields dropped
- balance — only Account Profile can provide this; showing a stale or guessed number risks a false impression of the customer's finances
- tier — same reasoning, sourced only from the degraded dependency
- lastLogin — same reasoning; omitted rather than guessed

## Step 3 — API signal
Chosen: HTTP 200 with `degraded=true`.
Justification: the CRM request itself did not fail — the page still renders with valid, non-fabricated fields (customerId, displayName, status=UNKNOWN). A 503 would tell the client the whole request failed, which would likely blank out the entire page instead of showing the degraded-but-still-useful partial view. Marking `degraded=true` lets the UI distinguish "successful but incomplete" from "call failed," while never claiming a write succeeded.

## Step 4 — User message
"Account details temporarily limited."

## Scope
Pre-lab only — do not finish the full graded lab in this exercise.