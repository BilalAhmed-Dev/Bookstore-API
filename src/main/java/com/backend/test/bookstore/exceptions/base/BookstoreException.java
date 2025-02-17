package com.backend.test.bookstore.exceptions.base;

public sealed abstract class BookstoreException extends RuntimeException
        permits
        BookstoreInternalServerException,
        BookstoreValidationException,
        BookstoreNotFoundException,
        BookstoreAuthorisationException {
    public BookstoreException(String message) {
        super(message);
    }

}
