package com.kapil.cqrs.account.cmd;

import com.kapil.cqrs.account.cmd.api.commands.CloseAccountCommand;
import com.kapil.cqrs.account.cmd.api.commands.CommandHandler;
import com.kapil.cqrs.account.cmd.api.commands.DepositFundsCommand;
import com.kapil.cqrs.account.cmd.api.commands.OpenAccountCommand;
import com.kapil.cqrs.account.cmd.api.commands.RestoreReadDbCommand;
import com.kapil.cqrs.account.cmd.api.commands.WithdrawFundsCommand;
import com.kapil.cqrs.core.infrastructure.CommandDispatcher;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CommandApplication {

  @Autowired
  private CommandDispatcher commandDispatcher;

  @Autowired
  private CommandHandler commandHandler;

  @PostConstruct
  public void registerHandlers() {
    commandDispatcher.registerHandler(OpenAccountCommand.class, commandHandler::handle);
    commandDispatcher.registerHandler(CloseAccountCommand.class, commandHandler::handle);
    commandDispatcher.registerHandler(DepositFundsCommand.class, commandHandler::handle);
    commandDispatcher.registerHandler(WithdrawFundsCommand.class, commandHandler::handle);
    commandDispatcher.registerHandler(RestoreReadDbCommand.class, commandHandler::handle);
  }


  public static void main(String[] args) {

    SpringApplication.run(CommandApplication.class, args);
  }
}
