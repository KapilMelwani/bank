package com.kapil.cqrs.account.query.infrastructure.consumers;

import com.kapil.cqrs.account.common.events.AccountClosedEvent;
import com.kapil.cqrs.account.common.events.AccountOpenedEvent;
import com.kapil.cqrs.account.common.events.FundsDepositedEvent;
import com.kapil.cqrs.account.common.events.FundsWithdrawEvent;
import com.kapil.cqrs.account.query.infrastructure.handlers.EventHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Service;

@Service
public class AccountEventConsumer implements EventConsumer {

  @Autowired
  EventHandler eventHandler;

  @KafkaListener(topics = "AccountOpenedEvent", groupId = "${spring.kafka.consumer.group-id}")
  @Override
  public void consume(AccountOpenedEvent event, Acknowledgment acknowledgment) {
    try {
      eventHandler.on(event);
      acknowledgment.acknowledge();
    } catch (IllegalStateException e) {
      System.out.println("Error Kapu: " + e.getMessage());
    }

  }

  @KafkaListener(topics = "FundsDepositedEvent", groupId = "${spring.kafka.consumer.group-id}")
  @Override
  public void consume(FundsDepositedEvent event, Acknowledgment acknowledgment) {
    try {
      eventHandler.on(event);
      acknowledgment.acknowledge();
    } catch (IllegalStateException e) {
      System.out.println("Error Kapu: " + e.getMessage());
    }
  }

  @KafkaListener(topics = "FundsWithdrawEvent", groupId = "${spring.kafka.consumer.group-id}")
  @Override
  public void consume(FundsWithdrawEvent event, Acknowledgment acknowledgment) {
    try {
      eventHandler.on(event);
      acknowledgment.acknowledge();
    } catch (IllegalStateException e) {
      System.out.println("Error Kapu: " + e.getMessage());
    }
  }

  @KafkaListener(topics = "AccountClosedEvent", groupId = "${spring.kafka.consumer.group-id}")
  @Override
  public void consume(AccountClosedEvent event, Acknowledgment acknowledgment) {
    try {
      eventHandler.on(event);
      acknowledgment.acknowledge();
    } catch (IllegalStateException e) {
      System.out.println("Error Kapu: " + e.getMessage());
    }
  }
}
