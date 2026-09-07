package com.sharipov.topuch.common.exception;

import com.sharipov.topuch.common.response.ErrorResponse;
import com.sharipov.topuch.common.response.FieldErrorResponse;
import jakarta.persistence.EntityNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.Instant;
import java.util.List;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidation(
            MethodArgumentNotValidException exception,
            HttpServletRequest request) {

        List<FieldErrorResponse> errors = exception.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(this::mapToFieldError)
                .toList();

        log.warn("Validation failed: path={}, errors={}", request.getRequestURI(), errors);

        return buildResponse(
                HttpStatus.BAD_REQUEST,
                "VALIDATION_ERROR",
                "Request validation failed",
                errors,
                request.getRequestURI()
        );
    }

    @ExceptionHandler(ApiException.class)
    public ResponseEntity<ErrorResponse> handleApiException(
            ApiException exception,
            HttpServletRequest request) {

        log.warn("{}: path={}, message={}, code={}",
                exception.getLogMessage(),
                request.getRequestURI(),
                exception.getMessage(),
                exception.getCode());

        return buildResponse(
                exception.getStatus(),
                exception.getCode().toString(),
                exception.getMessage(),
                List.of(),
                request.getRequestURI()
        );
    }

    @ExceptionHandler({IllegalArgumentException.class, EntityNotFoundException.class})
    public ResponseEntity<ErrorResponse> handleBadRequest(
            RuntimeException exception,
            HttpServletRequest request) {

        log.warn("Invalid request: path={}, message={}", request.getRequestURI(), exception.getMessage());
        return buildResponse(
                HttpStatus.BAD_REQUEST,
                "BAD_REQUEST",
                exception.getMessage(),
                List.of(),
                request.getRequestURI()
        );
    }

    @ExceptionHandler({IllegalStateException.class, OptimisticLockingFailureException.class})
    public ResponseEntity<ErrorResponse> handleConflict(
            RuntimeException exception,
            HttpServletRequest request) {

        log.warn("State conflict: path={}, message={}", request.getRequestURI(), exception.getMessage());
        return buildResponse(
                HttpStatus.CONFLICT,
                "CONFLICT",
                exception.getMessage(),
                List.of(),
                request.getRequestURI()
        );
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGeneral(
            Exception exception,
            HttpServletRequest request) {

        log.error("Unexpected error: path={}", request.getRequestURI(), exception);
        return buildResponse(
                HttpStatus.INTERNAL_SERVER_ERROR,
                "INTERNAL_SERVER_ERROR",
                "An unexpected error occurred",
                List.of(),
                request.getRequestURI()
        );
    }

    private ResponseEntity<ErrorResponse> buildResponse(
            HttpStatus status,
            String code,
            String message,
            List<FieldErrorResponse> errors,
            String path) {

        ErrorResponse response = new ErrorResponse(
                status.value(),
                code,
                message,
                errors,
                Instant.now(),
                path
        );

        return ResponseEntity.status(status).body(response);
    }

    private FieldErrorResponse mapToFieldError(FieldError error) {
        return new FieldErrorResponse(
                error.getField(),
                error.getCode(),
                error.getDefaultMessage()
        );
    }
}
