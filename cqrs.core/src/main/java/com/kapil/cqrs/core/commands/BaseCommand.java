package com.kapil.cqrs.core.commands;

import com.kapil.cqrs.core.messages.Message;
import lombok.Data;
import lombok.NoArgsConstructor;

/*
Represents a command that can be handled by a command handler.
 */
@Data
@NoArgsConstructor
public abstract class BaseCommand extends Message {
  public BaseCommand(String id) {
    super(id);
  }
}
