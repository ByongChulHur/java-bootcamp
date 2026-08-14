# Kafka Notes — Lab 30 (Northstar CRM)

## Frozen values for Lab 31

| Item | Lab value |
| ---- | --------- |
| Bootstrap (host) | `localhost:9092` |
| Primary topic | `crm.customer-events.v1` (3 partitions) |
| DLQ topic | `crm.customer-events.v1.dlq` (1 partition) |
| Record key | `customerId` (`CUS-1001`, `CUS-1002`) |
| Sample correlation | `lab-request-001` |
| Demo groups | `crm-notifications` (competing), `crm-audit` (independent) |

## Commands to reproduce

```powershell
docker compose up -d

docker exec crm-kafka /opt/kafka/bin/kafka-topics.sh --bootstrap-server localhost:9092 --create --topic crm.customer-events.v1 --partitions 3 --replication-factor 1
docker exec crm-kafka /opt/kafka/bin/kafka-topics.sh --bootstrap-server localhost:9092 --create --topic crm.customer-events.v1.dlq --partitions 1 --replication-factor 1

docker exec crm-kafka /opt/kafka/bin/kafka-consumer-groups.sh --bootstrap-server localhost:9092 --describe --group crm-notifications
```

Note: PLAINTEXT and RF=1 are lab-only. Production needs TLS/SASL and RF>=3.

## Produce → consume
Producer sends a keyed record to the topic. The leader broker writes it to the partition log
and assigns an offset. Consumers poll and read in offset order; each group tracks its own
committed offset independently.

## Keying
We key events by `customerId` so the same customer's events always land on the same
partition, keeping that customer's history in order (Kafka only guarantees order per partition).

## DLQ
`crm.customer-events.v1.dlq` exists so Lab 31 can move failing records aside without blocking
the main group. Not wired to logic yet.

## Ordering and delivery semantics
1. Same key → same partition → order preserved per customer.
2. No order guarantee across different customers/partitions.
3. At-least-once delivery — Lab 31 must handle duplicates (idempotent on `eventId`).
4. DLQ keeps poison records from blocking the main group.

## Reflection

1. Keying every event by `customerId` was the design decision that most affected
   correctness — without it, a customer's `CustomerCreated` and `CustomerStatusChanged`
   events could land on different partitions and be read out of order.

2. The end-to-end evidence is the Step 5 consume output: CUS-1001's two events both showed
   up on Partition 0 with increasing offsets (0, 1), and the Java producer in Step 6 printed
   `topic=crm.customer-events.v1 partition=0 offset=3` after a real send.

3. The consumer group rebalance in Step 7 was the hardest to diagnose — one consumer
   (`crm-notifications` member 2) received zero records, and it took a
   `kafka-consumer-groups.sh --describe` call to see it had been assigned an empty partition
   while the other member held both partitions with data.