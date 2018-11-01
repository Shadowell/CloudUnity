package com.external.api;

import javax.validation.constraints.NotNull;

public class UserImpl implements User {
  @NotNull
  private String name;

  @NotNull
  private Number telephone;

  @NotNull
  private Number age;

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

  public Number getAge() {
    return this.age;
  }

  public void setAge(Number age) {
    this.age = age;
  }
}
