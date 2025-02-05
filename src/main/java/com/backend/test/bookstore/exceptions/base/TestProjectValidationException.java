package com.backend.test.bookstore.exceptions.base;

public abstract non-sealed class TestProjectValidationException extends TestProjectException {
    public TestProjectValidationException(String message) {
        super(message);
    }
}