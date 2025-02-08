package com.backend.test.bookstore.exceptions.generic;
import com.backend.test.bookstore.exceptions.base.BookstoreNotFoundException;

public class BookstoreEntityNotFoundException extends BookstoreNotFoundException {
    public BookstoreEntityNotFoundException(String message) {
        super(message);
    }
}