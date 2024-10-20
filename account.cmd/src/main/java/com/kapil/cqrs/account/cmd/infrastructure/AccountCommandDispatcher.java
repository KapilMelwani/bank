package com.kapil.cqrs.account.cmd.infrastructure;

import com.kapil.cqrs.core.commands.BaseCommand;
import com.kapil.cqrs.core.commands.CommandHandlerMethod;
import com.kapil.cqrs.core.infrastructure.CommandDispatcher;
import java.util.HashMap;
import java.util.LinkedList;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Map;

/*
Used to register command handlers and to send or dispatch account commands. The command needs to be
dispatched to the correct handler based on the type of the command.
 - The handler is registered with the dispatcher using the registerHandler method.
 - The send method is used to dispatch the command to the correct handler.
 */
@Service
public class AccountCommandDispatcher implements CommandDispatcher {

  private final Map<Class<? extends BaseCommand>,List<CommandHandlerMethod>> routes = new HashMap<>();

  @Override
  public <T extends BaseCommand> void registerHandler(Class<T> type,
      CommandHandlerMethod<T> handler) {
    var handlers = routes.computeIfAbsent(type, c -> new LinkedList<>());
    handlers.add(handler);
  }

  @Override
  public void send(BaseCommand command) {
    var handlers = routes.get(command.getClass());
    if(handlers == null || handlers.isEmpty()) {
      throw new RuntimeException("No command handler registered.");
    }
    if(handlers.size() > 1) {
      throw new RuntimeException("Cannot send command with more than one handler.");
    }
    handlers.get(0).handle(command);
  }
}
