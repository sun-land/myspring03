package com.sparta.myspring03.model;

public enum UserRoleEnum {
    USER("ROLE_USER"),
    ADMIN("ROLE_ADMIN");

    private final String role;

    UserRoleEnum(String role) {
        this.role = role;
    }

    public String getAuthority() {
        return this.role;
    }
}
