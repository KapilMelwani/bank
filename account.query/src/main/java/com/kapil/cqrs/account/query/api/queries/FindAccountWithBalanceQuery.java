package com.kapil.cqrs.account.query.api.queries;

import com.kapil.cqrs.account.query.api.dto.EqualityType;
import com.kapil.cqrs.core.queries.BaseQuery;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class FindAccountWithBalanceQuery extends BaseQuery {
  private EqualityType equalityType;
  private double balance;
}
