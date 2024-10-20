package com.kapil.cqrs.account.query.domain;

import com.kapil.cqrs.core.domain.BaseEntity;
import java.util.List;
import java.util.Optional;
import org.springframework.data.repository.CrudRepository;

public interface AccountRepository extends CrudRepository<BankAccount, String> {
  Optional<BankAccount> findByAccountHolder(String accountHolder);
  List<BaseEntity> findByBalanceGreaterThan(Double balance);
  List<BaseEntity> findByBalanceLessThan(Double balance);
}
