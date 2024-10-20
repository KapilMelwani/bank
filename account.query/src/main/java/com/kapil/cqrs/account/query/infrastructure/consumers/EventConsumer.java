package com.kapil.cqrs.account.query.infrastructure.consumers;

import com.kapil.cqrs.account.common.events.AccountClosedEvent;
import com.kapil.cqrs.account.common.events.AccountOpenedEvent;
import com.kapil.cqrs.account.common.events.FundsDepositedEvent;
import com.kapil.cqrs.account.common.events.FundsWithdrawEvent;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.messaging.handler.annotation.Payload;

public interface EventConsumer {
  void consume(@Payload AccountOpenedEvent event, Acknowledgment acknowledgment);
  void consume(@Payload FundsDepositedEvent event, Acknowledgment acknowledgment);
  void consume(@Payload FundsWithdrawEvent event, Acknowledgment acknowledgment);
  void consume(@Payload AccountClosedEvent event, Acknowledgment acknowledgment);
}
