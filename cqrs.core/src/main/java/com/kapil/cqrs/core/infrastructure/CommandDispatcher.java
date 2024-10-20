package com.kapil.cqrs.core.infrastructure;

import com.kapil.cqrs.core.commands.BaseCommand;
import com.kapil.cqrs.core.commands.CommandHandlerMethod;

/*
Used to register command handlers and to send or dispatch commands.
 */
public interface CommandDispatcher {
  <T extends BaseCommand> void registerHandler(Class<T> type, CommandHandlerMethod<T> handler);
  void send(BaseCommand command);
}
