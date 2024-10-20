package com.kapil.cqrs.core.domain;

import com.kapil.cqrs.core.events.BaseEvent;
import java.lang.reflect.InvocationTargetException;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import lombok.Data;

/*
Represents an aggregate root, which is the core domain object that is used to apply events and raise events.
Aggregate is a representation of a cluster of domain objects that can be treated as a single unit.
 */
@Data
public abstract class AggregateRoot {
  protected String id;
  private int version = -1;

  private final List<BaseEvent> changes = new ArrayList<>();
  private final Logger logger = Logger.getLogger(AggregateRoot.class.getName());


  /*
  Gets the uncommitted changes, which are the events that have been raised but not yet committed.
   */
  public List<BaseEvent> getUncommittedChanges() {
    return changes;
  }

  /*
  Marks the changes as committed, which means that the events have been saved to the event store.
   */
  public void markChangesAsCommitted() {
    changes.clear();
  }

  /*
  Using reflection, applies the change to the aggregate by invoking the apply method for the event.
   */
  protected void applyChange(BaseEvent event, Boolean isNewEvent) {
    try {
      var method = getClass().getDeclaredMethod("apply", event.getClass());
      method.setAccessible(true);
      method.invoke(this, event);
    } catch (NoSuchMethodException e) {
      logger.log(Level.WARNING, MessageFormat.format("The apply method was not found in the aggregate for {0}", event.getClass().getName()));
      throw new RuntimeException(e);
    } catch (Exception e) {
      logger.log(Level.SEVERE, "Error applying event to aggregate", e);
    } finally {
      // If it's not a new event we don't apply it to the list of changes.
      if(isNewEvent) {
        changes.add(event);
      }
    }
  }

  /*
  Raises an event, which is used to apply the change to the aggregate.
  This is used when a command is executed and an event is raised.
   */
  public void raiseEvent(BaseEvent event) {
    applyChange(event, true);
  }

  /*
  Replays the events, which is used to apply the changes to the aggregate.
  This is used to recreate the state of the aggregate from the event store.
   */
  public void replayEvents(Iterable<BaseEvent> events) {
    events.forEach(event -> applyChange(event,false));
  }
}
