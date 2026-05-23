package com.delta.service.dtos.user.request;

import java.util.Set;

public class UserRequestDto {
    private String username;
    private String password;
    private String email;
    private Set<Long> role_id;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Set<Long> getRole_id() {
        return role_id;
    }

    public void setRole_id(Set<Long> role_id) {
        this.role_id = role_id;
    }
}
