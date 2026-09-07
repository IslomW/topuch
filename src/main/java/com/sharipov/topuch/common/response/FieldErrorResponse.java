package com.sharipov.topuch.common.response;

public record FieldErrorResponse(
        String field,
        String code,
        String message
) {
}
