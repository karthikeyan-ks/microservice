package com.delta.service.services.impl;

import io.jsonwebtoken.Claims;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Date;
import java.util.function.Function;

public interface IJwtService {
    public String extractUsername(String token);
    public <T> T extractClaim(
            String token,
            Function<Claims, T> claimResolver
    );
    public String generateToken(UserDetails userDetails);
    public boolean isTokenExpired(String token);
    public boolean isTokenValid(
            String token,
            UserDetails userDetails
    );
}
