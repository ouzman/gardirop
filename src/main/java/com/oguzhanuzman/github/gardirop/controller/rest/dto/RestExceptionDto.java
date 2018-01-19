package com.oguzhanuzman.github.gardirop.controller.rest.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class RestExceptionDto {
    private String message;

    public static RestExceptionDto of(Exception e) {
        return new RestExceptionDto(e.getMessage());
    }
}
