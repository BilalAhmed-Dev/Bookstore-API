package com.backend.test.bookstore.controller;

import com.backend.test.bookstore.dto.NewUserDTO;
import com.backend.test.bookstore.dto.AuthenticationResponseDTO;
import com.backend.test.bookstore.records.TokenContainerDTO;
import com.backend.test.bookstore.serviceimpl.AuthenticationServiceImpl;
import com.backend.test.bookstore.serviceimpl.JwtServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;


@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    private final AuthenticationServiceImpl authService;
    private final JwtServiceImpl jwtService;

    public AuthenticationController(AuthenticationServiceImpl authService, JwtServiceImpl jwtService) {
        this.authService = authService;
        this.jwtService = jwtService;
    }

    @PostMapping("signup")
    @CrossOrigin
    public ResponseEntity<AuthenticationResponseDTO> register(
            @RequestBody NewUserDTO user
    ) {
        return ResponseEntity.ok(authService.register(user));
    }


    @PostMapping("login")
    @CrossOrigin
    public ResponseEntity<AuthenticationResponseDTO> login(
            @RequestBody NewUserDTO user
    ) {
        return ResponseEntity.ok(authService.login(user));
    }

    @PostMapping("logout")
    @CrossOrigin
    public ResponseEntity<String> logout(@RequestBody TokenContainerDTO tokenContainer){
        String logoutMessage = authService.logout(tokenContainer.accessToken(), tokenContainer.refreshToken());
        return ResponseEntity.ok(logoutMessage);
    }

    @PostMapping("/refresh_token")
    public ResponseEntity<AuthenticationResponseDTO> refreshToken(
            HttpServletRequest request
    ) {
        // Only valid refresh tokens are permitted for use at this endpoint.
        Integer loggedInUserId = jwtService.getLoggedInUserId(request, "refreshToken");
        return ResponseEntity.ok(authService.refreshToken(loggedInUserId));
    }
}
