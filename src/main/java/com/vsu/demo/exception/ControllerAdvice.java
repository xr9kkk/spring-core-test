package com.vsu.demo.exception;

import com.vsu.demo.response.FonbetErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ControllerAdvice {
    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<FonbetErrorResponse> handleValidationException(final ValidationException exception ) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new FonbetErrorResponse(exception.getErrorCode()));
    }
}
