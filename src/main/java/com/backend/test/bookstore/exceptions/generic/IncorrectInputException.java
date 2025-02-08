package com.backend.test.bookstore.exceptions.generic;

import com.backend.test.bookstore.exceptions.base.BookstoreValidationException;

public class IncorrectInputException extends BookstoreValidationException {
    public IncorrectInputException(String message) {
        super(message);
    }
}