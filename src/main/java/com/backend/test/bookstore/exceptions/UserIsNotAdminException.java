package com.backend.test.bookstore.exceptions;

import com.backend.test.bookstore.exceptions.base.BookstoreAuthorisationException;
import com.backend.test.bookstore.exceptions.base.BookstoreNotFoundException;

public class UserIsNotAdminException extends BookstoreAuthorisationException {
    public UserIsNotAdminException(String message) {
        super(message);
    }
}