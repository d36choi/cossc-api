package com.api.cossc.dto.oauth;


public enum UserRole {
  ADMIN("ROLE_ADMIN", "관리자"),
  USER("ROLE_USER", "일반 사용자");

  private final String role;

  private final String name;


  UserRole(String role, String name) {
    this.role = role;
    this.name = name;
  }

  public String getRole() {
    return role;
  }

  public String getName() {
    return name;
  }
}
