package com.kapil.cqrs.account.query.domain;

import com.kapil.cqrs.account.common.dtos.AccountType;
import com.kapil.cqrs.core.domain.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Builder
@AllArgsConstructor
@Data
public class BankAccount extends BaseEntity {
  @Id
  private String id;
  private String accountHolder;
  private Date creationDate;
  private AccountType accountType;
  private Double balance;
}
