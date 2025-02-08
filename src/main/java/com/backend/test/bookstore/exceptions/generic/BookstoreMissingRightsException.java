package com.backend.test.bookstore.exceptions.generic;

import com.backend.test.bookstore.exceptions.base.BookstoreAuthorisationException;

public class BookstoreMissingRightsException extends BookstoreAuthorisationException {
    public BookstoreMissingRightsException(String message) {
        super(message);
    }
}
