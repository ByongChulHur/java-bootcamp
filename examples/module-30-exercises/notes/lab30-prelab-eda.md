# Lab 30 — Why Async for CRM

## Step 1 — List sync pain
Customer service creates `CUS-1001` Amina Khan over HTTP with correlation `lab-request-001`.
If email, audit, and analytics are called synchronously in the same request:

1. **Slower response** — the client waits for all four calls to finish, not just one.
2. **Cascading failure** — if email service is down, the whole create-customer request fails,
   even though the customer was already saved.
3. **Tight coupling** — Customer service must know about email/audit/analytics directly.
   Adding a new consumer means changing Customer service code.

## Step 2 — Event idea
Customer service publishes a `CustomerCreated` event to Kafka; email, audit, and analytics
each consume it independently, without Customer service calling them directly.

## Step 3 — Coupling check
**False.** Kafka stores the event on disk, so the Audit consumer can read it later even if
the Customer service is down.

## Scope
Pre-lab only — do not finish the full graded lab in this exercise.