package com.kapil.cqrs.core.infrastructure;

import com.kapil.cqrs.core.events.BaseEvent;
import java.util.List;

/*
Its purpose is to provide an interface abstraction for accessing the event store business logic.
 */
public interface EventStore {
  void saveEvents(String aggregateId, Iterable<BaseEvent> events, int expectedVersion);
  List<BaseEvent> getEvents(String aggregateId);
  List<String> getAllAggregateIds();
}
