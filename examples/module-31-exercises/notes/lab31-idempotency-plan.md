# Lab 31 — Idempotency Plan

## Step 1 — Why duplicates

1. Producer retry: if the producer doesn't receive an acknowledgment in
   time, it may resend the same event, even though the original send
   actually succeeded.
2. Consumer rebalance/reprocess: if a consumer crashes or a rebalance
   happens before the offset is committed, another consumer instance
   will re-read and reprocess the same message.

## Step 2 — Business key

Propose using the event's own `eventId` (a UUID assigned at publish time)
as the idempotency key, since it uniquely identifies one occurrence of
"Amina (CUS-1001) was created", regardless of how many times Kafka
redelivers it. As a fallback if eventId were missing, a composite key
like `customerId + eventType + occurredAt` (e.g. "CUS-1001" +
"CREATED" + timestamp) could also identify a unique business event.

## Step 3 — Store idea

Before triggering the notification side effect (e.g. sending an email),
check a processed-events store: if `eventId` is already marked as
processed, skip the side effect; otherwise mark it first, then proceed.

## Step 4 — Out of scope

This is a paper design only — the ProcessedEventStore itself will not
be implemented or coded yet in this exercise.

## Scope

Pre-lab only — the full graded lab has not been started yet.