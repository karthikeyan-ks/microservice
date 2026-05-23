package com.delta.service.controllers;

import com.delta.service.dtos.user.reponse.CreateUserResponseDto;
import com.delta.service.dtos.user.request.UserRequestDto;
import com.delta.service.services.impl.IAuthService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/auth")
public class AuthController {

    private final IAuthService authService;

    public AuthController(IAuthService authService){
        this.authService = authService;
    }

    @PostMapping("/create_user")
    public CreateUserResponseDto createUser(@Valid @RequestBody UserRequestDto userRequestDto) {
        return authService.createUser(userRequestDto);
    }
}
