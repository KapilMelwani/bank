package com.kapil.cqrs.account.query.api.queries;

import com.kapil.cqrs.core.domain.BaseEntity;
import java.util.List;

public interface QueryHandler {
  List<BaseEntity> handle(FindAllAccountsQuery query);
  List<BaseEntity> handle(FindAccountByIdQuery query);
  List<BaseEntity> handle(FindAccountByAccountHolderQuery query);
  List<BaseEntity> handle(FindAccountWithBalanceQuery query);
}
