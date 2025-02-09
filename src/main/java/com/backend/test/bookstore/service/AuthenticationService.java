package com.backend.test.bookstore.service;

import com.backend.test.bookstore.dto.AuthenticationResponseDTO;
import com.backend.test.bookstore.dto.LoginDTO;
import com.backend.test.bookstore.dto.NewUserDTO;

public interface AuthenticationService {
    AuthenticationResponseDTO register(NewUserDTO user);

    AuthenticationResponseDTO login(LoginDTO userRequest);

    String logout(String accessToken, String refreshToken);

    AuthenticationResponseDTO refreshToken(Integer loggedInUserId);
}