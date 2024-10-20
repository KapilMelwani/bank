package com.kapil.cqrs.account.query.api.queries;

import com.kapil.cqrs.account.query.api.dto.EqualityType;
import com.kapil.cqrs.account.query.domain.AccountRepository;
import com.kapil.cqrs.account.query.infrastructure.AccountQueryDispatcher;
import com.kapil.cqrs.core.domain.BaseEntity;
import com.kapil.cqrs.core.infrastructure.QueryDispatcher;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountQueryHandler implements QueryHandler {

  @Autowired
  private AccountRepository accountRepository;

  @Override
  public List<BaseEntity> handle(FindAllAccountsQuery query) {
    var iterator =  accountRepository.findAll();
    var accounts = new ArrayList<BaseEntity>();
    iterator.forEach(accounts::add);
    return accounts;
  }

  @Override
  public List<BaseEntity> handle(FindAccountByIdQuery query) {
    var bankAccount = accountRepository.findById(query.getId());
    if(bankAccount.isEmpty()) {
      return null;
    }
    List<BaseEntity> bankAccountList = new ArrayList<>();
    bankAccountList.add(bankAccount.get());
    return bankAccountList;
  }

  @Override
  public List<BaseEntity> handle(FindAccountByAccountHolderQuery query) {
    var bankAccount = accountRepository.findByAccountHolder(query.getAccountHolder());
    if(bankAccount.isEmpty()) {
      return null;
    }
    List<BaseEntity> bankAccountList = new ArrayList<>();
    bankAccountList.add(bankAccount.get());
    return bankAccountList;
  }

  @Override
  public List<BaseEntity> handle(FindAccountWithBalanceQuery query) {
    if(EqualityType.GREATER_THAN.equals(query.getEqualityType())) {
      return accountRepository.findByBalanceGreaterThan(query.getBalance());
    } else {
      return accountRepository.findByBalanceLessThan(query.getBalance());
    }
  }
}
