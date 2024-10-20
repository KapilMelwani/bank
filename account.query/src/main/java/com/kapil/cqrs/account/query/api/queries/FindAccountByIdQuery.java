package com.kapil.cqrs.account.query.api.queries;

import com.kapil.cqrs.core.queries.BaseQuery;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
public class FindAccountByIdQuery extends BaseQuery {
  private String id;
}
