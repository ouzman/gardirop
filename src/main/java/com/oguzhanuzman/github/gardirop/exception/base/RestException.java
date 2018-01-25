package com.oguzhanuzman.github.gardirop.exception.base;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class RestException extends RuntimeException {
    private static final HttpStatus DEFAULT_HTTP_STATUS = HttpStatus.INTERNAL_SERVER_ERROR;

    private HttpStatus httpStatus;

    public RestException(String message) {
        this(message, DEFAULT_HTTP_STATUS);
    }

    public RestException(String message, Throwable cause) {
        super(message, cause);
    }

    RestException(String message, HttpStatus httpStatus) {
        super(message);
        this.httpStatus = httpStatus;
    }

    public RestException(String message, Throwable cause, HttpStatus httpStatus) {
        super(message, cause);
        this.httpStatus = httpStatus;
    }

}
