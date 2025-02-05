package com.backend.test.bookstore.exceptions.generic;

import com.backend.test.bookstore.exceptions.base.TestProjectValidationException;

public class IncorrectInputException extends TestProjectValidationException {
    public IncorrectInputException(String message) {
        super(message);
    }
}