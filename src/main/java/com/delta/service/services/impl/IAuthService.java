package com.delta.service.services.impl;

import com.delta.service.dtos.user.reponse.CreateUserResponseDto;
import com.delta.service.dtos.user.request.UserRequestDto;

public interface IAuthService {
    public CreateUserResponseDto createUser(UserRequestDto userRequestDto);
}
