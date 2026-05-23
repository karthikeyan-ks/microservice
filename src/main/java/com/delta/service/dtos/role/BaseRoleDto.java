package com.delta.service.dtos.role;

public class BaseRoleDto<T> {
    private String message;
    private String token;
    private T data;

    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }

    public String getToken() { return token; }
    public void setToken(String token) { this.token = token; }

    public T getData() { return data; }
    public void setData(T data) { this.data = data; }
}
