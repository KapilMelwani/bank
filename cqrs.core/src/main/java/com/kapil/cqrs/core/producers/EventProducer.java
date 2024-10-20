package com.kapil.cqrs.core.producers;

import com.kapil.cqrs.core.events.BaseEvent;

public interface EventProducer {
  void produce(String topicName, BaseEvent event);
}
