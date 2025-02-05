package com.backend.test.bookstore.exceptions.base;

public abstract non-sealed class TestProjectNotFoundException extends TestProjectException {
    public TestProjectNotFoundException(String message) {
        super(message);
    }
}