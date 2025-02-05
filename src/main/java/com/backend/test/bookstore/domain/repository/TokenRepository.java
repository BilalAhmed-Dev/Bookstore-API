package com.backend.test.bookstore.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.backend.test.bookstore.domain.entity.BlacklistedToken;

import java.util.Date;
import java.util.Optional;


public interface TokenRepository extends JpaRepository<BlacklistedToken, Long> {

    Optional<BlacklistedToken> findByToken(String token);

    void deleteByExpiryDateBefore(Date now);
}