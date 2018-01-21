package com.oguzhanuzman.github.gardirop.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class RestException extends RuntimeException {
    private static final HttpStatus DEFAULT_HTTP_STATUS = HttpStatus.INTERNAL_SERVER_ERROR;

    private HttpStatus httpStatus;

    public RestException(String message) {
        this(message, DEFAULT_HTTP_STATUS);
    }

    RestException(String message, HttpStatus httpStatus) {
        super(message);
        this.httpStatus = httpStatus;
    }

}
