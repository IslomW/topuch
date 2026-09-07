package com.sharipov.topuch.common.exception;

import com.sharipov.topuch.common.constant.Code;
import org.springframework.http.HttpStatus;

public class UnauthorizedException extends ApiException {

    public UnauthorizedException(String message, Code code) {
        super(message, code, HttpStatus.UNAUTHORIZED);
    }

    @Override
    public String getLogMessage() {
        return "Unauthorized";
    }
}
