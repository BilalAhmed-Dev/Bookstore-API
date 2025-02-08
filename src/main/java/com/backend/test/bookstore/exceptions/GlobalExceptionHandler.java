package com.backend.test.bookstore.exceptions;

import com.backend.test.bookstore.records.ApiExceptionContainer;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import com.backend.test.bookstore.exceptions.base.BookstoreAuthorisationException;
import com.backend.test.bookstore.exceptions.base.BookstoreException;
import com.backend.test.bookstore.exceptions.base.BookstoreNotFoundException;
import com.backend.test.bookstore.exceptions.base.BookstoreValidationException;

import java.time.LocalDateTime;


@ControllerAdvice(basePackages = "com.backend.test.bookstore.controller")
public class GlobalExceptionHandler {


    @ExceptionHandler(BookstoreValidationException.class)
    public ResponseEntity<ApiExceptionContainer> handle(BookstoreValidationException opinaValidationException) {
        System.out.println("EXCEPTION: " + opinaValidationException.getClass().getSimpleName() + "  " + LocalDateTime.now());
        System.out.println(opinaValidationException.getMessage());
        ApiExceptionContainer apiExceptionContainer = new ApiExceptionContainer(opinaValidationException.getClass().getSimpleName(), opinaValidationException.getMessage(), null);
        return ResponseEntity.badRequest().body(apiExceptionContainer);
    }

    @ExceptionHandler(BookstoreNotFoundException.class)
    public ResponseEntity<ApiExceptionContainer> handle(BookstoreNotFoundException opinaNotFoundException) {
        System.out.println("EXCEPTION: " + opinaNotFoundException.getClass().getSimpleName() + "  " + LocalDateTime.now());
        if (opinaNotFoundException.getMessage() != null) {
            System.out.println(opinaNotFoundException.getMessage());
        }
        ApiExceptionContainer apiExceptionContainer = new ApiExceptionContainer(opinaNotFoundException.getClass().getSimpleName(), opinaNotFoundException.getMessage(), null);
        return ResponseEntity.status(404).body(apiExceptionContainer);
    }

    @ExceptionHandler(BookstoreAuthorisationException.class)
    public ResponseEntity<ApiExceptionContainer> handle(BookstoreAuthorisationException opinaAuthorisationException) {
        System.out.println("EXCEPTION: " + opinaAuthorisationException.getClass().getSimpleName() + "  " + LocalDateTime.now());
        if (opinaAuthorisationException.getMessage() != null) {
            System.out.println(opinaAuthorisationException.getMessage());
        }
        ApiExceptionContainer apiExceptionContainer = new ApiExceptionContainer(opinaAuthorisationException.getClass().getSimpleName(), opinaAuthorisationException.getMessage(), null);
        return ResponseEntity.status(403).body(apiExceptionContainer);
    }

    @ExceptionHandler(BookstoreException.class)
    public ResponseEntity<ApiExceptionContainer> handle(BookstoreException opinaException) {
        System.out.println("EXCEPTION: " + opinaException.getClass().getSimpleName() + "  " + LocalDateTime.now());
        if (opinaException.getMessage() != null) {
            System.out.println(opinaException.getMessage());
        }

        ApiExceptionContainer apiExceptionContainer = new ApiExceptionContainer(opinaException.getClass().getSimpleName(), opinaException.getMessage(), null);
        return ResponseEntity.internalServerError().body(apiExceptionContainer);
    }

    @ExceptionHandler(Throwable.class)
    public synchronized ResponseEntity<ApiExceptionContainer> handle(Throwable throwable) {
        System.out.println("EXCEPTION THROWABLE: " + throwable.getClass().getSimpleName() + "  " + LocalDateTime.now());
        throwable.printStackTrace();

        ApiExceptionContainer apiExceptionContainer = new ApiExceptionContainer(throwable.getClass().getSimpleName(), "Sunucu hatası. En kısa zamanda ilgileniyoruz.", null);
        return ResponseEntity.internalServerError().body(apiExceptionContainer);
    }
}
