package com.vsu.demo.exception;

import com.vsu.demo.response.ErrorCode;

public class ValidationException extends RuntimeException {


    private final ErrorCode errorCode;

    public ErrorCode getErrorCode() {
        return errorCode;
    }

    public ValidationException(ErrorCode errorCode, Throwable cause) {
        super(cause);
        this.errorCode = errorCode;
    }

}