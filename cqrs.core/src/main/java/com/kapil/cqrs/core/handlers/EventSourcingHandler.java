package com.kapil.cqrs.core.handlers;

import com.kapil.cqrs.core.domain.AggregateRoot;

/*
Used to save and retrieve aggregate roots.

 */
public interface EventSourcingHandler<T> {
  void save(AggregateRoot aggregateRoot);
  T getById(String id);
  void republishEvents();
}
