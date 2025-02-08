package com.backend.test.bookstore.dto;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ErrorResponseDTO {
    // Getters required for JSON serialization
    private final LocalDateTime timestamp;
    private final int status;
    private final String error;
    private final String message;
    private final String details;

    public ErrorResponseDTO(LocalDateTime timestamp, int status,
                         String error, String message, String details) {
        this.timestamp = timestamp;
        this.status = status;
        this.error = error;
        this.message = message;
        this.details = details;
    }

}
