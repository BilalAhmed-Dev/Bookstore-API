package com.backend.test.bookstore.domain.model;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class AuthInfo {
    String username;
    String email;
    String password;
}
