package com.kapil.cqrs.core.infrastructure;

import com.kapil.cqrs.core.domain.BaseEntity;
import com.kapil.cqrs.core.queries.BaseQuery;
import com.kapil.cqrs.core.queries.QueryHandlerMethod;
import java.util.List;

public interface QueryDispatcher {
  <T extends BaseQuery> void registerHandler(Class<T> type, QueryHandlerMethod<T> handler);
  <U extends BaseEntity> List<U> send(BaseQuery query);
}
