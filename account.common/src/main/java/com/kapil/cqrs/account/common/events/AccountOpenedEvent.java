package com.kapil.cqrs.account.common.events;

import com.kapil.cqrs.account.common.dtos.AccountType;
import com.kapil.cqrs.core.events.BaseEvent;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class AccountOpenedEvent extends BaseEvent {
  private String accountHolder;
  private AccountType accountType;
  private Date createdDate;
  private Double openingBalance;
}
