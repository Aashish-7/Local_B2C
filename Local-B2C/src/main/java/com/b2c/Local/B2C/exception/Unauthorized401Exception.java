package com.b2c.Local.B2C.exception;

import org.springframework.http.HttpStatus;

public class Unauthorized401Exception extends com.b2c.Local.B2C.exception.Abstract4xxException {

    private static final HttpStatus HTTP_STATUS = HttpStatus.UNAUTHORIZED;

    public Unauthorized401Exception (String message) { super(HTTP_STATUS, message); }

}
