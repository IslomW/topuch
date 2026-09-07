package com.sharipov.topuch.common.exception;

import com.sharipov.topuch.common.constant.Code;
import org.springframework.http.HttpStatus;

public class ConflictException extends ApiException {

    public ConflictException(String message, Code code) {
        super(message, code, HttpStatus.CONFLICT);
    }

    @Override
    public String getLogMessage() {
        return "Conflict";
    }
}
