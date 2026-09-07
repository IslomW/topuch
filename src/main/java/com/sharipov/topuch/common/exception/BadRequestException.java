package com.sharipov.topuch.common.exception;

import com.sharipov.topuch.common.constant.Code;
import org.springframework.http.HttpStatus;

public class BadRequestException extends ApiException {

    public BadRequestException(String message, Code code) {
        super(message, code, HttpStatus.BAD_REQUEST);
    }

    @Override
    public String getLogMessage() {
        return "Bad Request";
    }
}
