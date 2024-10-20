package com.kapil.cqrs.account.cmd.api.commands;

import com.kapil.cqrs.account.common.dtos.AccountType;
import com.kapil.cqrs.core.commands.BaseCommand;
import lombok.Data;

@Data
public class OpenAccountCommand extends BaseCommand {
  private String accountHolder;
  private AccountType accountType;
  private Double openingBalance;

  public OpenAccountCommand(String id, String accountHolder, AccountType accountType, Double openingBalance) {
    super(id);
    this.accountHolder = accountHolder;
    this.accountType = accountType;
    this.openingBalance = openingBalance;
  }
}
