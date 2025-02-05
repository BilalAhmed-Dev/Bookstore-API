package com.backend.test.bookstore.exceptions.generic;

import com.backend.test.bookstore.exceptions.base.TestProjectAuthorisationException;

public class TestProjectMissingRightsException extends TestProjectAuthorisationException {
    public TestProjectMissingRightsException(String message) {
        super(message);
    }
}
