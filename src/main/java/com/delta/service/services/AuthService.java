package com.delta.service.services;

import com.delta.service.dtos.user.BaseUserDto;
import com.delta.service.dtos.user.reponse.CreateUserResponseDto;
import com.delta.service.dtos.user.request.UserRequestDto;
import com.delta.service.entities.Role;
import com.delta.service.entities.User;
import com.delta.service.exceptions.InvalidRoleException;
import com.delta.service.repos.RoleRepository;
import com.delta.service.repos.UserRepository;
import com.delta.service.services.impl.IAuthService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class AuthService implements IAuthService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthService(
            UserRepository userRepository,
            RoleRepository roleRepository,
            PasswordEncoder passwordEncoder
            ) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public CreateUserResponseDto createUser(UserRequestDto userRequestDto) {
        CreateUserResponseDto createUserResponseDto = new CreateUserResponseDto();
        if (userRepository.existsByEmail(userRequestDto.getEmail())) {
            createUserResponseDto.setMessage("Email already taken");
            createUserResponseDto.setError(true);
            return createUserResponseDto;
        }
        if (userRepository.existsByUsername(userRequestDto.getUsername())) {
            createUserResponseDto.setMessage("Username already taken");
            createUserResponseDto.setError(true);
            return createUserResponseDto;
        }
        // Create user instance
        try {
            User user = constructUser(userRequestDto);
            userRepository.save(user);
            BaseUserDto baseUserDto = mapToBaseUserDto(user);
            createUserResponseDto.setUser(baseUserDto);
            createUserResponseDto.setMessage("Created account");
            createUserResponseDto.setError(false);
        } catch (InvalidRoleException e) {
            createUserResponseDto.setMessage(e.getMessage());
            createUserResponseDto.setError(true);
            return createUserResponseDto;
        }

        return createUserResponseDto;
    }

    private User constructUser(UserRequestDto userRequestDto) throws InvalidRoleException {
        User user = new User();
        user.setEmail(userRequestDto.getEmail());
        user.setUsername(userRequestDto.getUsername());
        String encodedPassword = passwordEncoder.encode(userRequestDto.getPassword());
        user.setPassword(encodedPassword);
        Set<Role> roles = new HashSet<>();
        if (userRequestDto.getRole_id() == null ||
                userRequestDto.getRole_id().isEmpty()) {
            throw new InvalidRoleException("No roles added");
        }

        for (Long roleId: userRequestDto.getRole_id()) {
            Role role = roleRepository.findById(roleId)
                    .orElseThrow(
                    () -> new InvalidRoleException("Invalid role"));

            roles.add(role);
        }

        user.setRoles(roles);
        user.setDeleted(false);
        user.setCreated_at(new Date());
        return user;
    }

    private BaseUserDto mapToBaseUserDto(User user) {
        BaseUserDto baseUserDto = new BaseUserDto();
        baseUserDto.setEmail(user.getEmail());
        baseUserDto.setUsername(user.getUsername());
        baseUserDto.setRoles(user.getRoles());
        return baseUserDto;
    }


}
