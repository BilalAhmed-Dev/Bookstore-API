package com.backend.test.bookstore.exceptions.base;


public abstract non-sealed class BookstoreInternalServerException extends BookstoreException {
    public BookstoreInternalServerException(String message) {
        super(message);
    }
}