package com.b2c.Local.B2C.exception.dto;

import com.b2c.Local.B2C.exception.Abstract4xxException;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter @Setter
@NoArgsConstructor
public class ErrorResponseDto {

    HttpStatus httpStatus;

    String message;


    public ErrorResponseDto(HttpStatus httpStatus, String message) {
        this.httpStatus = httpStatus;
        this.message = message;
    }

    @Override
    public String toString() {
        return "ErrorResponseDto{" +
                "httpStatus=" + httpStatus +
                ", message='" + message + '\'' +
                '}';
    }

    public ErrorResponseDto(Abstract4xxException exception) {
        this.httpStatus = exception.getHttpStatus();
        this.message = exception.getMessage();

    }

}
