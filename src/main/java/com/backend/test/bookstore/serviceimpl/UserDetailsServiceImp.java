package com.backend.test.bookstore.serviceimpl;

import com.backend.test.bookstore.entity.User;
import com.backend.test.bookstore.exceptions.UserNotFoundException;
import com.backend.test.bookstore.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


import java.util.Collections;

@Service
public class UserDetailsServiceImp implements UserDetailsService {

    private final UserRepository repository;

    public UserDetailsServiceImp(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = repository.getByUsername(username).orElseThrow(() -> new UserNotFoundException("User not exists by Username or Email"));;

        return new org.springframework.security.core.userdetails.User(
                username,
                user.getPassword(),
                Collections.emptySet()
        );
    }
}
