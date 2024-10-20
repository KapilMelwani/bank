package com.kapil.cqrs.core.events;

import java.util.Date;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/*
This is the model for the event store, which is used to store events in the MongoDB database.
 */
@Data
@Builder
@Document(collection = "eventStore")
public class EventModel {
  @Id
  private String id;
  private Date timestamp;
  private String aggregateIdentifier;
  private String aggregateType;
  private int version;
  private String eventType;
  private BaseEvent eventData;
}
