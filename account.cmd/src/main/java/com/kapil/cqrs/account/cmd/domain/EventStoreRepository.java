package com.kapil.cqrs.account.cmd.domain;

import com.kapil.cqrs.core.events.EventModel;
import java.util.List;
import java.util.Optional;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/*
This is the repository for the event store, which is used to store events in the MongoDB database.
We created findByAggregateIdentifier because aggregateIdentifier is the field we use to query the events.
 */
@Repository
public interface EventStoreRepository extends MongoRepository<EventModel, String> {
  List<EventModel> findByAggregateIdentifier(String aggregateIdentifier);
}
