# Lab 31 — Listener Sketch

## Step 1 — Method outline

@KafkaListener(topics = "crm.customer-events.v1", groupId = "crm-notifications")
void onCustomerEvent(CustomerEvent event) { ... }

## Step 2 — Second group

@KafkaListener(topics = "crm.customer-events.v1", groupId = "crm-audit")
void onCustomerEventForAudit(CustomerEvent e) { ... }

Same topic, different group id. Each group tracks its own offsets,
so both groups receive the full stream of events independently.

## Step 3 — Payload type

Using a typed CustomerEvent DTO instead of String/JsonNode, because
it gives compile-time safety and direct field access like
event.customerId().

## Step 4 — Correlation

Log correlationId (e.g. "lab-request-001") right when the event is
received, before running any business logic:
log.info("customer_event_received id={} correlationId={}",
event.eventId(), event.correlationId());

## Scope

Pre-lab only — the full graded lab has not been started yet.