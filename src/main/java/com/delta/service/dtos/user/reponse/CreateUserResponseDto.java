package com.delta.service.dtos.user.reponse;

import com.delta.service.dtos.user.BaseUserDto;

public class CreateUserResponseDto {
    private BaseUserDto user;
    private String Message;
    private boolean error;

    public BaseUserDto getUser() {
        return user;
    }

    public void setUser(BaseUserDto user) {
        this.user = user;
    }

    public String getMessage() {
        return Message;
    }

    public void setMessage(String message) {
        Message = message;
    }

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }
}
