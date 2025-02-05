package com.backend.test.bookstore.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.backend.test.bookstore.domain.entity.UserEntity;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Long> {

    Optional<UserEntity> findByUsername(String username);
    Optional<UserEntity> findByEmail(String userEmail);



}