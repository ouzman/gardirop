package com.oguzhanuzman.github.gardirop.controller;

import com.oguzhanuzman.github.gardirop.controller.rest.dto.ExceptionDto;
import com.oguzhanuzman.github.gardirop.exception.RestException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class ExceptionHandlingController {

    @ExceptionHandler(RestException.class)
    @ResponseBody
    public ExceptionDto restExceptionHandler(Exception ex) {
        return ExceptionDto.of(ex);
    }
}
