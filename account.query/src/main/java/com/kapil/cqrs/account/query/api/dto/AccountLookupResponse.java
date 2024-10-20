package com.kapil.cqrs.account.query.api.dto;

import com.kapil.cqrs.account.common.dtos.BaseResponse;
import com.kapil.cqrs.account.query.domain.BankAccount;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class AccountLookupResponse extends BaseResponse {
    private List<BankAccount> accounts;

    public AccountLookupResponse(String message) {
        super(message);
    }

    public void setMessage(String message) {
        super.setMessage(message);
    }

}
