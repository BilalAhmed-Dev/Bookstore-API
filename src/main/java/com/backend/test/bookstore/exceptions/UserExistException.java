package com.backend.test.bookstore.exceptions;

import com.backend.test.bookstore.exceptions.base.BookstoreAuthorisationException;

public class UserExistException extends BookstoreAuthorisationException {
    public UserExistException(String message) {
        super(message);
    }
}