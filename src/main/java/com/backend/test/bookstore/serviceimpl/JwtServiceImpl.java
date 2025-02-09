package com.backend.test.bookstore.serviceimpl;


import com.backend.test.bookstore.entity.BlacklistedToken;
import com.backend.test.bookstore.entity.User;
import com.backend.test.bookstore.repository.TokenRepository;
import com.backend.test.bookstore.service.JwtService;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import com.backend.test.bookstore.configuration.data.AuthenticationConfiguration;

import com.backend.test.bookstore.exceptions.InvalidTokenException;

import javax.crypto.SecretKey;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@Service
public class JwtServiceImpl implements JwtService {

    private final AuthenticationConfiguration authenticationConfiguration;
    private static final Logger logger = LoggerFactory.getLogger(JwtServiceImpl.class);
    private final TokenRepository tokenRepository;

    public JwtServiceImpl(AuthenticationConfiguration authenticationConfiguration, TokenRepository tokenRepository) {
        this.authenticationConfiguration = authenticationConfiguration;
        this.tokenRepository = tokenRepository;
    }


    // TOKEN EXTRACTION

    @Override
    public Integer extractUserId(String token) {
        Claims claims = extractAllClaims(token);
        String subject = claims.getSubject();

        try {
            return Integer.parseInt(subject);
        } catch (NumberFormatException e) {
            throw new InvalidTokenException("User ID in token is not a valid number");
        }
    }

    @Override
    public Integer getLoggedInUserId(HttpServletRequest request, String tokenType) {
        String token = extractAndValidateToken(request, tokenType);
        return extractUserId(token);
    }

    @Override
    public Date extractExpiration(String token) {
        Claims claims = extractAllClaims(token);
        return claims.getExpiration();
    }

    @Override
    public String extractAndValidateToken(HttpServletRequest request, String expectedType) {
        String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            throw new InvalidTokenException("Authorization header missing or invalid");
        }

        String token = authHeader.substring(7); // Extract token
        validateToken(token, expectedType, true); // Validate token against type
        return token; // Return valid token
    }



    // TOKEN VALIDATION
    @Override
    public Boolean validateToken(String token, String expectedType, boolean validateTokeType) {
        if (isTokenBlacklisted(token) || isTokenExpired(token)) {
            throw new InvalidTokenException("Token is invalid or expired");
        }

        try {
            JwtParser parser = Jwts.parser()
                    .setSigningKey(getSigninKey());

            parser.parseClaimsJws(token);

            if (!validateTokenType(token, expectedType) && validateTokeType) {
                throw new InvalidTokenException("Token type mismatch: expected " + expectedType);
            }

        } catch (Exception e) {
            throw new InvalidTokenException("JWT validation error: " + e.getMessage());
        }
        return Boolean.TRUE;
    }

    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    private boolean validateTokenType(String token, String expectedType) {
        Claims claims = extractAllClaims(token);
        String tokenType = claims.get("type", String.class);
        return expectedType.equals(tokenType);
    }

    private boolean isTokenBlacklisted(String token) {
        return tokenRepository.findByToken(token).isPresent();
    }









    // TOKEN GENERATION
    public String generateAccessToken(User user) {
        return generateToken(user, authenticationConfiguration.getAccess(), "accessToken");
    }

    public String generateRefreshToken(User user) {
        return generateToken(user, authenticationConfiguration.getRefresh(), "refreshToken");
    }






    // TOKEN BLACKLISTING
    public void blacklistToken(String token, Date expiryDate) {
        BlacklistedToken blacklistedToken = new BlacklistedToken();
        blacklistedToken.setToken(token);
        blacklistedToken.setExpiryDate(expiryDate);
        tokenRepository.save(blacklistedToken);
    }

    public void cleanUpExpiredTokens() {
        tokenRepository.deleteByExpiryDateBefore(new Date());
    }

    // UTILITY TOKEN METHODS
    private String generateToken(User user, Long expireTime, String tokenType) {
        return Jwts.builder()
                .setSubject(user.getUserId().toString())  // Changed to setSubject
                .setIssuedAt(new Date())                 // Changed to setIssuedAt
                .setExpiration(new Date(System.currentTimeMillis() + expireTime)) // Changed to setExpiration
                .claim("type", tokenType)
                .signWith(getSigninKey())
                .compact();
    }
    //     GET INFORMATION STORED IN THE TOKEN
    //     userID
    //     expirationDate
    //     tokenType - ( accessToken OR refreshToken )
    private Claims extractAllClaims(String token) {
        JwtParser parser = Jwts.parser()
                .setSigningKey(getSigninKey());

        return parser.parseClaimsJws(token).getBody();
    }

    private SecretKey getSigninKey() {
        byte[] keyBytes = Decoders.BASE64.decode(authenticationConfiguration.getSecret());
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
