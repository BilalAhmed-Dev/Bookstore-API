package com.backend.test.bookstore.exceptions.base;

public abstract non-sealed class BookstoreNotFoundException extends BookstoreException {
    public BookstoreNotFoundException(String message) {
        super(message);
    }
}