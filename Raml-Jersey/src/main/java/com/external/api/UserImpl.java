package com.external.api;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class UserImpl implements com.external.api.User {
  @NotNull
  private String name;

  @NotNull
  private Number telephone;

  @NotNull
  @Min(0)
  @Max(150)
  private int age;

  public String getName() {
    return this.name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Number getTelephone() {
    return this.telephone;
  }

  public void setTelephone(Number telephone) {
    this.telephone = telephone;
  }

  public int getAge() {
    return this.age;
  }

  public void setAge(int age) {
    this.age = age;
  }
}
