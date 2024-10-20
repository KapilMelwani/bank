package com.kapil.cqrs.core.queries;

import com.kapil.cqrs.core.domain.BaseEntity;
import java.util.List;

@FunctionalInterface
public interface QueryHandlerMethod<T extends BaseQuery> {
  List<BaseEntity> handle(T query);
}
