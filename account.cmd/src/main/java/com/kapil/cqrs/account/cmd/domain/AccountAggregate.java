package com.kapil.cqrs.account.cmd.domain;

import com.kapil.cqrs.account.cmd.api.commands.OpenAccountCommand;
import com.kapil.cqrs.account.common.events.AccountClosedEvent;
import com.kapil.cqrs.account.common.events.AccountOpenedEvent;
import com.kapil.cqrs.account.common.events.FundsDepositedEvent;
import com.kapil.cqrs.account.common.events.FundsWithdrawEvent;
import com.kapil.cqrs.core.domain.AggregateRoot;
import java.util.Date;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class AccountAggregate extends AggregateRoot {

  private Boolean active;
  private Double balance;

  // The command that "creates" an instance of the Aggregate should always be handled in the constructor of the Aggregate
  public AccountAggregate(OpenAccountCommand command) {
    raiseEvent(AccountOpenedEvent.builder()
        .id(command.getId())
        .accountHolder(command.getAccountHolder())
        .createdDate(new Date())
        .accountType(command.getAccountType())
        .openingBalance(command.getOpeningBalance())
        .build());
  }

  /*
  Required by reflection in the aggregate root base class. An account is opened (active)
  with N amount of funds. This changes the state of the account aggregate.
   */
  public void apply(AccountOpenedEvent event) {
    this.id = event.getId();
    this.active = true;
    this.balance = event.getOpeningBalance();
  }

  public void depositFunds(Double amount) {
    if (!active) {
      throw new IllegalStateException("Funds cannot be deposited into a closed bank account!");
    }
    if (amount <= 0) {
      throw new IllegalStateException("The deposit amount must be greater than 0.");
    } else {
      raiseEvent(FundsDepositedEvent.builder()
          .id(this.id)
          .amount(amount)
          .build());
    }
  }

  /*
  Reducing the balance of the account by the amount deposited, changing the state of the account aggregate.
   */
  public void apply(FundsDepositedEvent event) {
    this.id = event.getId();
    this.balance += event.getAmount();
  }

  public void withdrawFunds(Double amount) {
    if (!active) {
      throw new IllegalStateException("Funds cannot be withdrawed from a closed bank account!");
    }
    raiseEvent(FundsWithdrawEvent.builder()
        .id(this.id)
        .amount(amount)
        .build());
  }


  /*
  Required by reflection in the aggregate root base class. Funds are withdrawn from the account and the balance is reduced
  by the amount withdrawn. This changes the state of the account aggregate.
   */
  public void apply(FundsWithdrawEvent event) {
    this.id = event.getId();
    this.balance -= event.getAmount();
  }

  public void closeAccount() {
    if(!this.active) {
      throw new IllegalStateException("The bank account has already been closed!");
    }
    raiseEvent(AccountClosedEvent.builder()
        .id(this.id)
        .build());
  }

  /*
  Required by reflection in the aggregate root base class. The account is closed and the active status is set to false.
  This changes the state of the account aggregate.
   */
  public void apply(AccountClosedEvent event) {
    this.id = event.getId();
    this.active = false;
  }
}
