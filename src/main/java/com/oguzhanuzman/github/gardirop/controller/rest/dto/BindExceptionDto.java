package com.oguzhanuzman.github.gardirop.controller.rest.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.validation.BindException;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class BindExceptionDto {
    private List<String> errors;

    public static BindExceptionDto of(BindException ex) {
        return new BindExceptionDto(
                ex.getFieldErrors().stream()
                        .map(DefaultMessageSourceResolvable::getDefaultMessage)
                        .collect(Collectors.toList())
        );
    }
}
