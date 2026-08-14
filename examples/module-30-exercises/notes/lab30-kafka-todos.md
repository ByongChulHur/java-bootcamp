# Lab 30 — Fill Kafka Basics TODOs

## Step 1 — Copy the quiz

1. A **topic** is a named stream of records.
2. A **partition** is an ordered subset of a topic; offsets are per partition.
3. The **offset** is the consumer's position in a partition.
4. Consumers in the same **consumer group** compete for partitions; different groups each get a copy.

## Step 2 — Fill blanks
topic / partition / offset / consumer group

## Step 3 — CRM example
Group `crm-notifications` shares partitions (competing); group `crm-audit` reads all `CUS-1001`/`CUS-1002` events independently.

## Step 4 — Self-check
topic, partition, offset, consumer group — confirmed.

## Scope
Pre-lab only — do not finish the full graded lab in this exercise.