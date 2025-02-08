package com.backend.test.bookstore.exceptions;


import com.backend.test.bookstore.exceptions.base.BookstoreAuthorisationException;

public class InvalidTokenException extends BookstoreAuthorisationException {
    public InvalidTokenException(String message) {
        super(message);
    }
}