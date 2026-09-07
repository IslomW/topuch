package com.sharipov.topuch.common.exception;

import com.sharipov.topuch.common.constant.Code;
import org.springframework.http.HttpStatus;

public class ForbiddenException extends ApiException {

    public ForbiddenException(String message, Code code) {
        super(message, code, HttpStatus.FORBIDDEN);
    }

    @Override
    public String getLogMessage() {
        return "Forbidden";
    }
}
