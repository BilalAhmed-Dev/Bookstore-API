package com.backend.test.bookstore.exceptions;

import com.backend.test.bookstore.exceptions.base.TestProjectAuthorisationException;

public class InvalidUsernameOrPasswordException extends TestProjectAuthorisationException {
    public InvalidUsernameOrPasswordException(String message) {
        super(message);
    }
}