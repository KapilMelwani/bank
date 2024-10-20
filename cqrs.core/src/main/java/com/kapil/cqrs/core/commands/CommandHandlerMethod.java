package com.kapil.cqrs.core.commands;

/*
Represents a method that can handle a command that extends BaseCommand.
 */
@FunctionalInterface
public interface CommandHandlerMethod<T extends BaseCommand> {
  void handle(T command);
}
