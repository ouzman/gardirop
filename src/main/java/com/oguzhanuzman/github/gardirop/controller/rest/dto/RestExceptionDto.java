package com.oguzhanuzman.github.gardirop.controller.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class RestExceptionDto {
    private String message;

    public static RestExceptionDto of(Exception e) {
        return new RestExceptionDto(e.getMessage());
    }
}
