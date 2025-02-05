package com.backend.test.bookstore.exceptions.base;


public abstract non-sealed class TestProjectInternalServerException extends TestProjectException {
    public TestProjectInternalServerException(String message) {
        super(message);
    }
}