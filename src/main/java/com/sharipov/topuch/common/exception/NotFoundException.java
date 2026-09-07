package com.sharipov.topuch.common.exception;

import com.sharipov.topuch.common.constant.Code;
import com.sharipov.topuch.common.constant.MessageKey;
import org.springframework.http.HttpStatus;

public class NotFoundException extends ApiException {

    public NotFoundException(String message, Code code) {
        super(message, code, HttpStatus.NOT_FOUND);
    }


    public NotFoundException categoryNotFound(){
        return new NotFoundException(MessageKey.CATEGORY_NOT_FOUND, Code.DATA_NOT_FOUND);
    }


    @Override
    public String getLogMessage() {
        return "Not Found";
    }
}
