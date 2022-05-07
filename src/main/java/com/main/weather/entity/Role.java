package com.main.weather.entity;

import lombok.Getter;

@Getter
public enum Role {
  ROLE_ADMIN("admin"),
  ROLE_USER("user");

  public String description;

  Role(String description) {
    this.description = description;
  }
}
