package com.backend.test.bookstore.dto;

public record ApiExceptionContainer(String name, String message, Object data) {
}
