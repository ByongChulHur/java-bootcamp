package com.northstar.crm.event;

import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import org.springframework.stereotype.Component;

@Component
public class ProcessedEventStore {

  private final Set<String> seen = ConcurrentHashMap.newKeySet();

  public boolean markIfNew(String eventId) {
    return seen.add(eventId);
  }

  public boolean contains(String eventId) {
    return seen.contains(eventId);
  }
}