package com.kapil.cqrs.account.cmd.infrastructure;

import com.kapil.cqrs.account.cmd.domain.AccountAggregate;
import com.kapil.cqrs.account.cmd.domain.EventStoreRepository;
import com.kapil.cqrs.core.events.BaseEvent;
import com.kapil.cqrs.core.events.EventModel;
import com.kapil.cqrs.core.exceptions.AggregateNotFoundException;
import com.kapil.cqrs.core.exceptions.ConcurrencyException;
import com.kapil.cqrs.core.infrastructure.EventStore;
import com.kapil.cqrs.core.producers.EventProducer;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/*
 * The code checks just the last element in the eventStream because it represents the most recent state of the aggregate.
 * In an event-sourced system, each event represents a state change, and the version of the last event indicates the current
 * version of the aggregate. By comparing the version of the last event with the expectedVersion, the system ensures that no
 * other changes have been made to the aggregate since the caller last read it, thus preventing conflicting updates.
 *
 * Event sourcing is based on building the state of the aggregate based on the order of the sequence of events. For the state to be correct,
 * it is important that the ordering of events is enforced by implementing event versioning. Optimistic concurrency control is then used to
 * ensure that only the expected event versions can alter the state of the aggregate at any given point in time.
 * This is in especially important when two or more clients requests are made at the same time to alter the state of the aggregate.
 */
@Service
public class AccountEventStore implements EventStore {

  @Autowired
  private EventStoreRepository eventStoreRepository;

  @Autowired
  EventProducer eventProducer;

  @Override
  public void saveEvents(String aggregateId, Iterable<BaseEvent> events, int expectedVersion) {
    var eventStream = eventStoreRepository.findByAggregateIdentifier(aggregateId);
    if (expectedVersion != -1
        && eventStream.get(eventStream.size() - 1).getVersion() != expectedVersion) {
      throw new ConcurrencyException();
    }
    var version = expectedVersion;
    for (var event : events) {
      version++;
      event.setVersion(version);
      var eventModel = EventModel.builder()
          .timestamp(new Date())
          .aggregateIdentifier(aggregateId)
          .aggregateType(AccountAggregate.class.getTypeName())
          .eventData(event)
          .eventType(event.getClass().getTypeName())
          .version(version)
          .build();
      var persistedEvent = eventStoreRepository.save(eventModel);
      if (!persistedEvent.getId().isEmpty()) {
        eventProducer.produce(event.getClass().getSimpleName(), event);
      }
    }
  }

  @Override
  public List<BaseEvent> getEvents(String aggregateId) {
    var eventStream = eventStoreRepository.findByAggregateIdentifier(aggregateId);
    if(eventStream == null || eventStream.isEmpty()) {
      throw new AggregateNotFoundException("Incorrect account ID provided!");
    }
    return eventStream.stream().map(EventModel::getEventData).collect(Collectors.toList());
  }

  @Override
  public List<String> getAllAggregateIds() {
    var eventStream =  eventStoreRepository.findAll();
    if(eventStream == null || eventStream.isEmpty()) {
      throw new IllegalStateException("Could not retrieve any aggregate IDs!");
    }
    return eventStream.stream().map(EventModel::getAggregateIdentifier).toList();
  }
}
