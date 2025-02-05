package com.backend.test.bookstore.domain.model;

public record ApiExceptionContainer(String name, String message, Object data) {
}
