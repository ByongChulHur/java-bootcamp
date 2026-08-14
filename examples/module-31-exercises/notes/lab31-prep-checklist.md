# Lab 31 prep checklist

## Earlier exercise files present?

| File | Present? (yes/no) |
| ---- | ----------------- |
| notes/lab31-spring-kafka.md | yes |
| notes/lab31-listener-sketch.md | yes |
| notes/lab31-todos.md | yes |
| notes/lab31-error-dlt-notes.md | yes |
| notes/lab31-idempotency-plan.md | yes |

## Fixtures (verify)

| ID | Name | Status |
| -- | ---- | ------ |
| CUS-1001 | Amina Khan | ACTIVE |
| CUS-1002 | Ravi Singh | PROSPECT |

## Topic dependency

Lab 31 depends on the topic `crm.customer-events.v1` (plus its DLQ)
already created in Lab 30. This module does not create the topic from
scratch; it wires Spring Kafka publishing/consuming on top of it.

## Tooling

JDK 21 + Maven, running on Windows PowerShell. The `spring-kafka`
dependency (plus `spring-kafka-test` for EmbeddedKafka) will be added
in the Lab 31 starter project itself, not in this pre-lab exercise.

## Scope statement

Pre-lab only — prepare for lab; do not complete full Lab 31 now.

## Self mark

Overall prep: Pass

All five pre-lab notes exist and are complete, the required topic from
Lab 30 is identified, and the tooling/environment is confirmed. Ready
to start Lab 31 coding.