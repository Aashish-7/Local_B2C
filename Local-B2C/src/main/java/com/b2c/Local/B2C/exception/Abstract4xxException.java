package com.b2c.Local.B2C.exception;

import org.springframework.http.HttpStatus;

public abstract class Abstract4xxException extends RuntimeException {

    private final HttpStatus httpStatus;
    private String message;

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Abstract4xxException (HttpStatus httpStatus, String message) {
        this.message = message;
        this.httpStatus = httpStatus;
    }

}
