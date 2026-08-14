# Lab 31 - Spring Kafka Roles

## Reference Mapping

| Kafka Idea      | Spring Boot Piece                  |
|------------------|--------------------------------------|
| Produce record    | KafkaTemplate.send(...)              |
| Consume record     | @KafkaListener                        |
| Bootstrap servers | spring.kafka.bootstrap-servers        |
| Group id           | spring.kafka.consumer.group-id         |

## CRM Produce Story

When HTTP creates Amina (CUS-1001) and the database save succeeds,
KafkaTemplate publishes an event to "crm.customer-events.v1" using
"CUS-1001" as the key.

kafkaTemplate.send("crm.customer-events.v1", "CUS-1001", event)

## CRM Consume Story

The listener in group "crm-notifications" listens to
"crm.customer-events.v1" and processes the event.

## Open Question

Should we use StringSerializer or JsonSerializer for the value?