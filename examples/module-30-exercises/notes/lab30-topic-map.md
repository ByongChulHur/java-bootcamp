# Lab 30 — Topic and Key Map

## Reference
| Concept | Northstar choice |
| --- | --- |
| Main topic | crm.customer-events.v1 |
| DLQ topic | crm.customer-events.v1.dlq |
| Partitions (lab) | 3 |
| Record key | customerId (e.g. CUS-1001) |
| Account* events (future) | _(not decided yet — placeholder row)_ |

## Step 2 — Keying reason
Using `customerId` as the key means Kafka always sends the same customer's events to the
same partition. Since order is only guaranteed **within** a partition, this keeps all of
Amina's (`CUS-1001`) events in order relative to each other, and all of Ravi's (`CUS-1002`)
events in order relative to each other — even though Amina's and Ravi's events may land in
different partitions.

## Step 3 — Versioning
The `.v1` suffix lets the team introduce a new topic (`crm.customer-events.v2`) later if the
event payload schema changes in a breaking way, without forcing existing consumers to
handle both old and new formats on the same topic.

## Step 4 — DLQ trigger
1. A consumer fails to deserialize a record (malformed/corrupt JSON payload).
2. A record is missing a required field (e.g. `customerId`) and fails validation.

## Scope
Pre-lab only — do not finish the full graded lab in this exercise.