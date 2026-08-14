# Lab 31 — Spring Kafka notes

## How to run

```powershell
cd C:\Users\andyh\java-bootcamp\examples\lab31-crm
mvn -q test
```

Uses `@EmbeddedKafka` for tests, no Docker broker needed.
Topic: `crm.customer-events.v1`. Group: `crm-notifications`.

## Publish path

`CustomerEventPublisher.publish(event)` calls
`kafkaTemplate.send(topic, event.customerId(), event)`, keyed by
customerId so same-customer events stay ordered in one partition.
Logs `customer_event_published` on success,
`customer_event_publish_failed` on failure.

## Idempotency

`CustomerEventListener` checks key == customerId first (throws
`InvalidCustomerEventException` if not), then calls
`store.markIfNew(event.eventId())`. `ProcessedEventStore` keeps an
in-memory `Set<String>` of seen eventIds — duplicates are logged as
`duplicate_event_ignored` and skipped, so at-least-once delivery still
results in once-only side effects.

**Note:** in-memory store resets on restart. Production needs a
durable, shared store (e.g. a DB unique-key table).

## Error handling and DLT

`KafkaErrorConfig` uses `DefaultErrorHandler` +
`DeadLetterPublishingRecoverer`, `FixedBackOff(500ms, 2 retries)`.
Non-retryable (skip straight to DLT):
- `InvalidCustomerEventException` (key mismatch)
- `UnsupportedEventVersionException` (bad version)

**DLT naming decision:** uses Spring's default `.DLT` suffix
(`crm.customer-events.v1.DLT`), not Lab 30's `.dlq`. No custom
recoverer destination was configured.

DLT headers include original topic/partition/offset and exception
info (`kafka_dlt-original-topic`, `kafka_dlt-exception-fqcn`, etc).