package com.oguzhanuzman.github.gardirop.controller.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.validation.BindException;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@AllArgsConstructor
public class ValidationFailedDto {
    private String message;
    private List<String> errors;

    public static ValidationFailedDto of(BindException ex) {
        return new ValidationFailedDto(
                "Validation failed!",
                ex.getFieldErrors().stream()
                        .map(DefaultMessageSourceResolvable::getDefaultMessage)
                        .collect(Collectors.toList())
        );
    }
}
