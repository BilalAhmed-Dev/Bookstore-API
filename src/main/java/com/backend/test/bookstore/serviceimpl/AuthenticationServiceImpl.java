package com.backend.test.bookstore.serviceimpl;

import com.backend.test.bookstore.dao.UserDao;
import com.backend.test.bookstore.dto.LoginDTO;
import com.backend.test.bookstore.dto.NewUserDTO;
import com.backend.test.bookstore.dto.AuthenticationResponseDTO;
import com.backend.test.bookstore.dto.UserInfoDTO;
import com.backend.test.bookstore.entity.User;
import com.backend.test.bookstore.exceptions.InvalidUsernameOrPasswordException;
import com.backend.test.bookstore.exceptions.UserExistException;
import com.backend.test.bookstore.service.AuthenticationService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.UUID;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {

    private final UserDao userDao;
    private final PasswordEncoder passwordEncoder;
    private final JwtServiceImpl jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthenticationServiceImpl(UserDao userDao,
                                     PasswordEncoder passwordEncoder,
                                     JwtServiceImpl jwtService,
                                     AuthenticationManager authenticationManager) {
        this.userDao = userDao;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
    }

    @Override
    public AuthenticationResponseDTO register(NewUserDTO user) {
        if (userDao.existsByUsername(user.getUsername())) {
            throw new UserExistException("Username already taken");
        }

        if (userDao.existsByEmail(user.getEmail())) {
            throw new UserExistException("Email already registered");
        }

        User userEntity = new User();
        UUID uuid = UUID.randomUUID();
        userEntity.setUserId(uuid.toString());
        userEntity.setUsername(user.getUsername());
        userEntity.setEmail(user.getEmail());
        userEntity.setPassword(passwordEncoder.encode(user.getPassword()));
        User savedUser = userDao.saveUser(userEntity);

        return buildAuthenticationResponse(savedUser);
    }
    @Override
    public AuthenticationResponseDTO login(LoginDTO userRequest) {
        authenticateUser(userRequest.getEmail(), userRequest.getPassword());

        User user = userDao.getByEmail(userRequest.getEmail());


        return buildAuthenticationResponse(user);
    }
    @Override
    public String logout(String accessToken, String refreshToken) {
        Date accessTokenExpiry = jwtService.extractExpiration(accessToken);
        Date refreshTokenExpiry = jwtService.extractExpiration(refreshToken);

        jwtService.blacklistToken(accessToken, accessTokenExpiry);
        jwtService.blacklistToken(refreshToken, refreshTokenExpiry);

        return "Logout successful!";
    }
    @Override
    public AuthenticationResponseDTO refreshToken(String loggedInUserId ) {
        User user = userDao.getUserById(loggedInUserId);
        return buildAuthenticationResponse(user);
    }

    private void authenticateUser(String userEmail, String password) {
        User user = userDao.getByEmail(userEmail);

        boolean passwordMatches = passwordEncoder.matches(password, user.getPassword());
        if (!passwordMatches) {
            throw new InvalidUsernameOrPasswordException("Wrong user name or password");

        }
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(user.getUsername(), password)
        );

    }

    private AuthenticationResponseDTO buildAuthenticationResponse(User user) {
        UserInfoDTO userInfo = new UserInfoDTO();
        userInfo.setUsername(user.getUsername());
        userInfo.setEmail(user.getEmail());
        userInfo.setId(user.getUserId());


        String accessToken = jwtService.generateAccessToken(user);
        String refreshToken = jwtService.generateRefreshToken(user);

        return new AuthenticationResponseDTO(accessToken, refreshToken, userInfo);
    }
}
