# Lab 31 — Fill Spring Kafka TODOs

## Step 1 — Filled snippet

```java
// application.yml ideas
spring.kafka.bootstrap-servers: localhost:9092
spring.kafka.consumer.group-id: crm-notifications

@Service
class CustomerEventPublisher {
  private final KafkaTemplate<String, String> template;

  void publishCreated(String customerId, String json) {
    // key must be the customerId (e.g. "CUS-1001" / "CUS-1002"),
    // never a random UUID -- this keeps events for the same
    // customer ordered in the same partition.
    template.send("crm.customer-events.v1", customerId, json); // topic
  }
}

@KafkaListener(topics = "crm.customer-events.v1", groupId = "crm-notifications")
void onEvent(String payload) { /* TODO: parse + idempotent handle */ }

// TODO Lab 31: route poison messages to crm.customer-events.v1.dlq
```

## Step 2 — Fill blanks

All blanks filled: bootstrap-servers=localhost:9092, group-id=crm-notifications,
topic=crm.customer-events.v1 (used twice: publish + listen).

## Step 3 — Key reminder

Added comment above template.send(): the key argument must always be the
customerId fixture (CUS-1001 for Amina, CUS-1002 for Ravi), never a random
UUID, since same-key events must land in the same partition to stay ordered.

## Step 4 — DLT blank

Added: // TODO Lab 31: route poison messages to crm.customer-events.v1.dlq

## Scope

Pre-lab only — the full graded lab has not been started yet.