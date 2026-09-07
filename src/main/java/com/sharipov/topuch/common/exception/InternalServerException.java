package com.sharipov.topuch.common.exception;

import org.springframework.http.HttpStatus;

public class InternalServerException extends ApiException {

    public InternalServerException(String message, String code) {
        super(message, code, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public String getLogMessage() {
        return "Internal Server Error";
    }
}
