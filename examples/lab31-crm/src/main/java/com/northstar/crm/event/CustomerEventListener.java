package com.northstar.crm.event;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class CustomerEventListener {

  private static final Logger log = LoggerFactory.getLogger(CustomerEventListener.class);
  private final ProcessedEventStore store;

  public CustomerEventListener(ProcessedEventStore store) {
    this.store = store;
  }

  @KafkaListener(topics = "${crm.kafka.customer-events-topic}")
  public void onCustomerEvent(
          @Payload CustomerEvent event,
          @Header(KafkaHeaders.RECEIVED_KEY) String key) {

    if (key == null || !key.equals(event.customerId()))
      throw new InvalidCustomerEventException("key mismatch");

    if (!store.markIfNew(event.eventId())) {
      log.info("duplicate_event_ignored id={}", event.eventId());
      return;
    }

    log.info("customer_event_received id={} customerId={} correlationId={}",
            event.eventId(), event.customerId(), event.correlationId());
  }
}