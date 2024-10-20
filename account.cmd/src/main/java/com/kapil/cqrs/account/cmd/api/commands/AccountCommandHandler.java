package com.kapil.cqrs.account.cmd.api.commands;

import com.kapil.cqrs.account.cmd.domain.AccountAggregate;
import com.kapil.cqrs.core.handlers.EventSourcingHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountCommandHandler implements CommandHandler {

  @Autowired
  EventSourcingHandler<AccountAggregate> eventSourcingHandler;

  @Override
  public void handle(OpenAccountCommand command) {
    // In the constructor of the AccountAggregate we open the account
    var aggregate = new AccountAggregate(command);
    eventSourcingHandler.save(aggregate);

  }

  @Override
  public void handle(DepositFundsCommand command) {
    var aggregate = eventSourcingHandler.getById(command.getId());
    aggregate.depositFunds(command.getAmount());
    eventSourcingHandler.save(aggregate);
  }

  @Override
  public void handle(WithdrawFundsCommand command) {
    var aggregate = eventSourcingHandler.getById(command.getId());
    if(command.getAmount() > aggregate.getBalance() ) {
      throw new IllegalArgumentException("Withdrawal declined. Insufficient funds");
    } else {
      aggregate.withdrawFunds(command.getAmount());
      eventSourcingHandler.save(aggregate);
    }
  }

  @Override
  public void handle(CloseAccountCommand command) {
    var aggregate = eventSourcingHandler.getById(command.getId());
    aggregate.closeAccount();
    eventSourcingHandler.save(aggregate);
  }

  @Override
  public void handle(RestoreReadDbCommand command) {
    eventSourcingHandler.republishEvents();
  }
}
