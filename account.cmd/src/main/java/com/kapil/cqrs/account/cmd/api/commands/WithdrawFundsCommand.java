package com.kapil.cqrs.account.cmd.api.commands;

import com.kapil.cqrs.core.commands.BaseCommand;
import lombok.Data;

@Data
public class WithdrawFundsCommand extends BaseCommand {
  private Double amount;

  public WithdrawFundsCommand(String id, Double amount) {
    super(id);
    this.amount = amount;
  }
}
