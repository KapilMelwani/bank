package com.kapil.cqrs.account.cmd.infrastructure;

import com.kapil.cqrs.core.events.BaseEvent;
import com.kapil.cqrs.core.producers.EventProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class AccountEventProducer implements EventProducer {

  @Autowired
  KafkaTemplate<String, Object> kafkaTemplate;

  @Override
  public void produce(String topicName, BaseEvent event) {
    this.kafkaTemplate.send(topicName, event);
  }
}
