package com.backend.test.bookstore.service;

import com.backend.test.bookstore.entity.User;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

public interface JwtService {
    Integer extractUserId(String token);

    Integer getLoggedInUserId(HttpServletRequest request, String tokenType);

    Date extractExpiration(String token);

    String extractAndValidateToken(HttpServletRequest request, String expectedType);

    Boolean validateToken(String token, String expectedType, boolean validateTokeType);

    String generateAccessToken(User user);

    String generateRefreshToken(User user);

    void blacklistToken(String token, Date expiryDate);

    void cleanUpExpiredTokens();
}