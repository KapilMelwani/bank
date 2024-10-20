package com.kapil.cqrs.account.query.api.queries;

import com.kapil.cqrs.core.queries.BaseQuery;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class FindAccountByAccountHolderQuery extends BaseQuery {
  private String accountHolder;
}
