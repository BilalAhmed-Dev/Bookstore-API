package com.backend.test.bookstore.exceptions;

import com.backend.test.bookstore.exceptions.base.BookstoreAuthorisationException;

public class InvalidUsernameOrPasswordException extends BookstoreAuthorisationException {
    public InvalidUsernameOrPasswordException(String message) {
        super(message);
    }
}