package com.kapil.cqrs.account.cmd.infrastructure;

import com.kapil.cqrs.account.cmd.domain.AccountAggregate;
import com.kapil.cqrs.core.domain.AggregateRoot;
import com.kapil.cqrs.core.events.BaseEvent;
import com.kapil.cqrs.core.handlers.EventSourcingHandler;
import com.kapil.cqrs.core.infrastructure.EventStore;
import com.kapil.cqrs.core.producers.EventProducer;
import java.util.Comparator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/*
Its responsibile for retrieving all events for a given aggregate from the Event Store and to invoke the
replayEvents method on the AggregateRoot to recreate the latest state of the aggregate.
 */
@Service
public class AccountEventSourcingHandler implements EventSourcingHandler<AccountAggregate> {

  @Autowired
  private EventStore eventStore;

  @Autowired
  private EventProducer eventProducer;

  @Override
  public void save(AggregateRoot aggregateRoot) {
    eventStore.saveEvents(aggregateRoot.getId(), aggregateRoot.getUncommittedChanges(), aggregateRoot.getVersion());
    aggregateRoot.markChangesAsCommitted();
  }

  @Override
  public AccountAggregate getById(String id) {
    var accountAggregate = new AccountAggregate();
    var events = eventStore.getEvents(id);
    // If events are not null and not empty, replay the events to build the aggregate
    if(events != null && !events.isEmpty()) {
      accountAggregate.replayEvents(events);
      var latestVersion = events.stream().map(BaseEvent::getVersion).max(Comparator.naturalOrder());
      accountAggregate.setVersion(latestVersion.get());
    }
    return accountAggregate;
  }

  @Override
  public void republishEvents() {
    var aggregateIds = eventStore.getAllAggregateIds();
    for (var aggregateId : aggregateIds) {
      var aggregate = getById(aggregateId);
      if(aggregate != null && aggregate.getActive()) {
        var events = eventStore.getEvents(aggregateId);
        for(var event : events) {
          eventProducer.produce(event.getClass().getSimpleName(), event);
        }
      }
    }
  }
}
