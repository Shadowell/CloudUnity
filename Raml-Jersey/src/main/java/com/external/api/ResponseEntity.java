package com.external.api;

public interface ResponseEntity {
  Number getCode();

  void setCode(Number code);

  String getMessage();

  void setMessage(String message);
}
