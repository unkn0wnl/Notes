package com.unkn0wnl.dev.notes.api.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class RestAppException extends RuntimeException {

    public RestAppException() {
    }

    public RestAppException(String message) {
        super(message);
    }

    public RestAppException(String message, Throwable cause) {
        super(message, cause);
    }

    public RestAppException(Throwable cause) {
        super(cause);
    }

    public RestAppException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}