package com.unkn0wnl.dev.notes.api.exception;

import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;

import static org.apache.logging.log4j.LogManager.getLogger;

@ControllerAdvice
@RestController
public class GlobalExceptionHandler {

    private static final Logger LOGGER;

    static {
        LOGGER = getLogger(GlobalExceptionHandler.class);
    }

    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<JsonResponse> handleException(Exception e) {
        LOGGER.error(e);
        LOGGER.error(Arrays.toString(e.getStackTrace()));
        return new ResponseEntity<JsonResponse>(new JsonResponse(e.getMessage()), HttpStatus.BAD_REQUEST);
    }

    private class JsonResponse {
        String message;

        public JsonResponse() {
        }

        public JsonResponse(String message) {
            super();
            this.message = message;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }
    }

}