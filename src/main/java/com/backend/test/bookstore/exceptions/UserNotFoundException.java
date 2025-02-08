package com.backend.test.bookstore.exceptions;

import com.backend.test.bookstore.exceptions.base.BookstoreNotFoundException;

public class UserNotFoundException extends BookstoreNotFoundException {
    public UserNotFoundException(String message) {
        super(message);
    }
}