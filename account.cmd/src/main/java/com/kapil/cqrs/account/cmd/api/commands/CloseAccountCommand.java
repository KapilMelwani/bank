package com.kapil.cqrs.account.cmd.api.commands;

import com.kapil.cqrs.core.commands.BaseCommand;
import lombok.Data;

@Data
public class CloseAccountCommand extends BaseCommand {
  public CloseAccountCommand(String id) {
    super(id);
  }
}
