package com.backend.test.bookstore.exceptions.base;

public abstract non-sealed class BookstoreValidationException extends BookstoreException {
    public BookstoreValidationException(String message) {
        super(message);
    }
}