package com.sharipov.topuch.common.exception;

import com.sharipov.topuch.common.constant.Code;
import org.springframework.http.HttpStatus;

public class ApiException extends RuntimeException {

    private final Code code;
    private final HttpStatus status;

    public ApiException(String message, Code code, HttpStatus status) {
        super(message);
        this.code = code;
        this.status = status;
    }

    public Code getCode() {
        return code;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public String getLogMessage() {
        return "API error";
    }
}
