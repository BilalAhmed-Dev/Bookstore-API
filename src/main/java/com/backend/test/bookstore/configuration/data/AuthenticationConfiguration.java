package com.backend.test.bookstore.configuration.data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@ConfigurationProperties("jwt")
@Configuration
@Getter
@Setter
public class AuthenticationConfiguration {
    private String secret;          // Maps to jwt.secret
    private Long expirationMs;      // Maps to jwt.expiration.ms
    private Long refreshExpirationMs; // Maps to jwt.refresh-expiration.ms
}