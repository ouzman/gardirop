package com.oguzhanuzman.github.gardirop.controller.rest.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ExceptionDto {
    private String message;

    public static ExceptionDto of(Exception e) {
        return new ExceptionDto(e.getMessage());
    }
}
