package com.backend.test.bookstore.exceptions.generic;

import com.backend.test.bookstore.exceptions.base.TestProjectNotFoundException;

public class TestProjectEntityNotFoundException extends TestProjectNotFoundException {
    public TestProjectEntityNotFoundException(String message) {
        super(message);
    }
}