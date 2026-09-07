package com.sharipov.topuch.common.response;

import java.time.Instant;
import java.util.List;

public record ErrorResponse(
        int status,
        String code,
        String message,
        List<FieldErrorResponse> errors,
        Instant timestamp,
        String path
) {
}
