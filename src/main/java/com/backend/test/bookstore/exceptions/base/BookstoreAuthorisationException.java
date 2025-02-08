package com.backend.test.bookstore.exceptions.base;

public abstract non-sealed class BookstoreAuthorisationException extends BookstoreException {
    public BookstoreAuthorisationException(String message) {
        super(message);
    }
}