package com.backend.test.bookstore.exceptions;


import com.backend.test.bookstore.exceptions.base.TestProjectAuthorisationException;

public class InvalidTokenException extends TestProjectAuthorisationException {
    public InvalidTokenException(String message) {
        super(message);
    }
}