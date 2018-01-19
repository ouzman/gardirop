package com.oguzhanuzman.github.gardirop.controller;

import com.oguzhanuzman.github.gardirop.controller.rest.dto.BindExceptionDto;
import com.oguzhanuzman.github.gardirop.controller.rest.dto.RestExceptionDto;
import com.oguzhanuzman.github.gardirop.exception.RestException;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class ExceptionHandlingController {

    @ExceptionHandler(RestException.class)
    @ResponseBody
    public RestExceptionDto restExceptionHandler(Exception ex) {
        return RestExceptionDto.of(ex);
    }

    @ExceptionHandler(BindException.class)
    @ResponseBody
    public BindExceptionDto validationFailed(BindException ex) {
        return BindExceptionDto.of(ex);
    }
}
