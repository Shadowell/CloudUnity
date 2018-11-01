package com.external.api;

public interface Response {
  Number getCode();

  void setCode(Number code);

  String getMessage();

  void setMessage(String message);
}
