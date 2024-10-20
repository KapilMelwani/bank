package com.kapil.cqrs.account.query.infrastructure.handlers;

import com.kapil.cqrs.account.common.events.AccountClosedEvent;
import com.kapil.cqrs.account.common.events.AccountOpenedEvent;
import com.kapil.cqrs.account.common.events.FundsDepositedEvent;
import com.kapil.cqrs.account.common.events.FundsWithdrawEvent;

/*
Its purpose is to provide an interface abstraction through which events can be handled once they are
consumed. It is important not to confuse the event handler with the event sourcing handler.
The event handler resides on the query side of C and affects the read database while the event sourcing
handler resides on the command side and ultimately impacts the right database or event store.

It's responsible to update the read database via the AccountRepository after a new event was consumed from Kafka.
 */
public interface EventHandler {
  void on(AccountOpenedEvent event);
  void on(AccountClosedEvent event);
  void on(FundsDepositedEvent event);
  void on(FundsWithdrawEvent event);
}
