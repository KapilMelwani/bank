package com.kapil.cqrs.account.common.events;

import com.kapil.cqrs.core.events.BaseEvent;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class FundsDepositedEvent extends BaseEvent {
  private Double amount;
}
