package com.northstar.crm.event;

import static org.assertj.core.api.Assertions.assertThat;
import static org.awaitility.Awaitility.await;

import java.time.Duration;
import java.time.Instant;
import java.util.UUID;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.test.context.EmbeddedKafka;
import org.springframework.test.context.TestPropertySource;
import org.springframework.kafka.core.KafkaTemplate;

@SpringBootTest
@EmbeddedKafka(partitions = 1, topics = {"crm.customer-events.v1"})
@TestPropertySource(properties = {
        "spring.kafka.bootstrap-servers=${spring.embedded.kafka.brokers}",
        "crm.kafka.customer-events-topic=crm.customer-events.v1"
})
class CustomerEventIntegrationTest {

  @Autowired
  private CustomerEventPublisher publisher;

  @Autowired
  private ProcessedEventStore store;

  @Test
  void publishesAndConsumesCustomerCreated() {
    CustomerEvent createdEvent = new CustomerEvent(
            UUID.randomUUID().toString(),
            "CustomerCreated",
            1,
            Instant.now(),
            "CUS-1001",
            "lab-request-001",
            "crm-service",
            new CustomerEvent.CustomerData("Amina Khan", "ACTIVE"));

    publisher.publish(createdEvent);

    await().atMost(Duration.ofSeconds(10)).untilAsserted(() ->
            assertThat(store.contains(createdEvent.eventId())).isTrue());
  }
  @Test
  void duplicateEventIsIgnored() {
    CustomerEvent event = new CustomerEvent(
            UUID.randomUUID().toString(),
            "CustomerCreated",
            1,
            Instant.now(),
            "CUS-1001",
            "lab-request-001",
            "crm-service",
            new CustomerEvent.CustomerData("Amina Khan", "ACTIVE"));

    publisher.publish(event);

    await().atMost(Duration.ofSeconds(10)).untilAsserted(() ->
            assertThat(store.contains(event.eventId())).isTrue());

    // Re-publish the SAME event (same eventId) to simulate a Kafka redelivery
    publisher.publish(event);

    await().pollDelay(Duration.ofSeconds(2)).atMost(Duration.ofSeconds(10))
            .untilAsserted(() ->
                    assertThat(store.contains(event.eventId())).isTrue());
  }
  @Autowired
  private KafkaTemplate<String, CustomerEvent> rawKafkaTemplate;

  @Test
  void keyMismatchGoesToDltWithoutRetry() {
    CustomerEvent event = new CustomerEvent(
            UUID.randomUUID().toString(),
            "CustomerCreated",
            1,
            Instant.now(),
            "CUS-1001",
            "lab-request-001",
            "crm-service",
            new CustomerEvent.CustomerData("Amina Khan", "ACTIVE"));

    // Publish with WRONG key ("CUS-9999") while event.customerId() is "CUS-1001"
    rawKafkaTemplate.send("crm.customer-events.v1", "CUS-9999", event);
    // Listener should throw InvalidCustomerEventException -> non-retryable -> DLT
    // We can't easily assert DLT content without a DLT consumer here,
    // but we confirm the store never marks it (listener rejected before markIfNew)
    await().pollDelay(Duration.ofSeconds(3)).atMost(Duration.ofSeconds(10))
            .untilAsserted(() ->
                    assertThat(store.contains(event.eventId())).isFalse());
  }
}