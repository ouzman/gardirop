package com.oguzhanuzman.github.gardirop.controller;

import com.oguzhanuzman.github.gardirop.controller.rest.dto.ValidationFailedDto;
import com.oguzhanuzman.github.gardirop.controller.rest.dto.RestExceptionDto;
import com.oguzhanuzman.github.gardirop.exception.RestException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ExceptionHandlingController {

    @ExceptionHandler(RestException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public RestExceptionDto restExceptionHandler(Exception ex) {
        return RestExceptionDto.of(ex);
    }

    @ExceptionHandler(BindException.class)
    @ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
    @ResponseBody
    public ValidationFailedDto validationFailed(BindException ex) {
        return ValidationFailedDto.of(ex);
    }
}
