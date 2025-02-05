package com.backend.test.bookstore.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.backend.test.bookstore.domain.entity.UserEntity;
import com.backend.test.bookstore.domain.repository.UserRepository;

import java.util.Collections;

@Service
public class UserDetailsServiceImp implements UserDetailsService {

    private final UserRepository repository;

    public UserDetailsServiceImp(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity user = repository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not exists by Username or Email"));

        return new org.springframework.security.core.userdetails.User(
                username,
                user.getPassword(),
                Collections.emptySet()
        );
    }
}
