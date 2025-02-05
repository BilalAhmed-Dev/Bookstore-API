package com.backend.test.bookstore.exceptions;

import com.backend.test.bookstore.exceptions.base.TestProjectNotFoundException;

public class UserNotFoundException extends TestProjectNotFoundException {
    public UserNotFoundException(String message) {
        super(message);
    }
}