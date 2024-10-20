package com.kapil.cqrs.account.cmd.api.dto;

import com.kapil.cqrs.account.common.dtos.BaseResponse;
import lombok.Data;

@Data
public class OpenAccountResponse extends BaseResponse {
  private String id;

  public OpenAccountResponse(String message, String id) {
    super(message);
    this.id = id;
  }
}

