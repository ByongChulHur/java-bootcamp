# Lab 31 — Error and DLT Notes

## Step 1 — Retryable

Example: a transient network blip while calling the email API — this is
temporary infrastructure trouble, so it should be retried with a bounded
backoff, since it may succeed on the next attempt.

## Step 2 — Non-retryable

Example: JSON payload missing `customerId` — this is a permanent contract
violation. Retrying can never fix malformed data, so after a limited number
of attempts it should be routed to the Dead Letter Topic instead of
retrying forever.

## Step 3 — Ops note

Support replays the DLT after the consumer bug is fixed, using the
correlationId `lab-request-001` to trace which original request the
failed event belongs to.

## Step 4 — No runtime

Confirmed: this is a pre-lab planning exercise only. No code is run and
nothing is actually published to the DLT from the CLI at this stage.

## Scope

Pre-lab only — the full graded lab has not been started yet.