package com.oguzhanuzman.github.gardirop.controller;

import com.oguzhanuzman.github.gardirop.controller.rest.dto.RestExceptionDto;
import com.oguzhanuzman.github.gardirop.controller.rest.dto.ValidationFailedDto;
import com.oguzhanuzman.github.gardirop.exception.base.RestException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ExceptionHandlingController {

    @ExceptionHandler(RestException.class)
    @ResponseBody
    public ResponseEntity<RestExceptionDto> restExceptionHandler(RestException ex) {
        return ResponseEntity
                .status(ex.getHttpStatus())
                .body(RestExceptionDto.of(ex));
    }

    @ExceptionHandler(BindException.class)
    @ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
    @ResponseBody
    public ValidationFailedDto validationFailed(BindException ex) {
        return ValidationFailedDto.of(ex);
    }
}
