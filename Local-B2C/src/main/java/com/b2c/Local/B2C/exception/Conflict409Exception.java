package com.b2c.Local.B2C.exception;

import org.springframework.http.HttpStatus;

public class Conflict409Exception extends Abstract4xxException{

    private static final HttpStatus HTTP_STATUS = HttpStatus.CONFLICT;

    public Conflict409Exception (String message) { super(HTTP_STATUS, message); }
}
