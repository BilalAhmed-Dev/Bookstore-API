package com.backend.test.bookstore.controller.advice;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import com.backend.test.bookstore.domain.model.ApiExceptionContainer;
import com.backend.test.bookstore.exceptions.base.TestProjectAuthorisationException;
import com.backend.test.bookstore.exceptions.base.TestProjectException;
import com.backend.test.bookstore.exceptions.base.TestProjectNotFoundException;
import com.backend.test.bookstore.exceptions.base.TestProjectValidationException;

import java.time.LocalDateTime;


@ControllerAdvice(basePackages = "com.backend.test.bookstore.controller")
public class CommonAdvice {


    @ExceptionHandler(TestProjectValidationException.class)
    public ResponseEntity<ApiExceptionContainer> handle(TestProjectValidationException testProjectValidationException) {
        System.out.println("EXCEPTION: " + testProjectValidationException.getClass().getSimpleName() + "  " + LocalDateTime.now());
        System.out.println(testProjectValidationException.getMessage());
        ApiExceptionContainer apiExceptionContainer = new ApiExceptionContainer(testProjectValidationException.getClass().getSimpleName(), testProjectValidationException.getMessage(), null);
        return ResponseEntity.badRequest().body(apiExceptionContainer);
    }

    @ExceptionHandler(TestProjectNotFoundException.class)
    public ResponseEntity<ApiExceptionContainer> handle(TestProjectNotFoundException testProjectNotFoundException) {
        System.out.println("EXCEPTION: " + testProjectNotFoundException.getClass().getSimpleName() + "  " + LocalDateTime.now());
        if (testProjectNotFoundException.getMessage() != null) {
            System.out.println(testProjectNotFoundException.getMessage());
        }
        ApiExceptionContainer apiExceptionContainer = new ApiExceptionContainer(testProjectNotFoundException.getClass().getSimpleName(), testProjectNotFoundException.getMessage(), null);
        return ResponseEntity.status(404).body(apiExceptionContainer);
    }

    @ExceptionHandler(TestProjectAuthorisationException.class)
    public ResponseEntity<ApiExceptionContainer> handle(TestProjectAuthorisationException testProjectAuthorisationException) {
        System.out.println("EXCEPTION: " + testProjectAuthorisationException.getClass().getSimpleName() + "  " + LocalDateTime.now());
        if (testProjectAuthorisationException.getMessage() != null) {
            System.out.println(testProjectAuthorisationException.getMessage());
        }
        ApiExceptionContainer apiExceptionContainer = new ApiExceptionContainer(testProjectAuthorisationException.getClass().getSimpleName(), testProjectAuthorisationException.getMessage(), null);
        return ResponseEntity.status(403).body(apiExceptionContainer);
    }

    @ExceptionHandler(TestProjectException.class)
    public ResponseEntity<ApiExceptionContainer> handle(TestProjectException TestProjectException) {
        System.out.println("EXCEPTION: " + TestProjectException.getClass().getSimpleName() + "  " + LocalDateTime.now());
        if (TestProjectException.getMessage() != null) {
            System.out.println(TestProjectException.getMessage());
        }

        ApiExceptionContainer apiExceptionContainer = new ApiExceptionContainer(TestProjectException.getClass().getSimpleName(), TestProjectException.getMessage(), null);
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
