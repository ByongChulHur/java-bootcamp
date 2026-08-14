# Lab 30 — Event Envelope Sketch

## Step 1 — Headers
Shared envelope fields used for every event: `eventType`, `eventVersion`, `occurredAt`,
`correlationId`, `customerId`, `payload`.

## Step 2 — Amina sample
```json
{
  "eventType": "CustomerCreated",
  "eventVersion": 1,
  "occurredAt": "2026-08-11T10:00:00Z",
  "correlationId": "lab-request-001",
  "customerId": "CUS-1001",
  "payload": {
    "fullName": "Amina Khan",
    "status": "ACTIVE"
  }
}
```
Key used when producing: `CUS-1001` (matches `payload`-level customer identity, no PII beyond
name/status is included).

## Step 3 — Ravi sample
```json
{
  "eventType": "CustomerStatusChanged",
  "eventVersion": 1,
  "occurredAt": "2026-08-11T10:05:00Z",
  "correlationId": "lab-request-001",
  "customerId": "CUS-1002",
  "payload": {
    "previousStatus": "ACTIVE",
    "newStatus": "SUSPENDED"
  }
}
```
Key used when producing: `CUS-1002`.

## Step 4 — Compatibility note
Consumers must ignore unknown fields inside `payload` — the envelope is forward-compatible,
not fixed, so adding a new optional field later (e.g. `reason`) should not break existing
consumers that don't know about it yet.

## Scope
Pre-lab only — do not finish the full graded lab in this exercise.