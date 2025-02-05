package com.backend.test.bookstore.exceptions;

import com.backend.test.bookstore.exceptions.base.TestProjectAuthorisationException;

public class UserIsNotAdminException extends TestProjectAuthorisationException {
    public UserIsNotAdminException(String message) {
        super(message);
    }
}