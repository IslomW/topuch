package com.sharipov.topuch.web.advice;

import com.sharipov.topuch.domain.exception.PostNotFound;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

public class GlobalExceptionHandler {

    @ExceptionHandler(PostNotFound.class)
    public ResponseEntity<ErrorResponse> handlePostNotFoundException(PostNotFound ex) {
        ErrorResponse errorResponse = new ErrorResponse("POST_NOT_FOUND", ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
    }

}
