package com.backend.test.bookstore.exceptions.base;

public sealed abstract class TestProjectException extends RuntimeException
        permits
        TestProjectInternalServerException,
        TestProjectValidationException,
        TestProjectNotFoundException,
        TestProjectAuthorisationException {
    public TestProjectException(String message) {
        super(message);
    }

    public TestProjectException(String message, Throwable cause) {
        super(message, cause);
    }
}
