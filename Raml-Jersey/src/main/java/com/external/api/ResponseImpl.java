package com.external.api;

import javax.validation.constraints.NotNull;

public class ResponseImpl implements Response {
  @NotNull
  private Number code;

  @NotNull
  private String message;

  public Number getCode() {
    return this.code;
  }

  public void setCode(Number code) {
    this.code = code;
  }

  public String getMessage() {
    return this.message;
  }

  public void setMessage(String message) {
    this.message = message;
  }
}
