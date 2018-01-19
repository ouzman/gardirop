package com.oguzhanuzman.github.gardirop.controller.rest.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class MemberCreateDto {
    private String screenName;
    private String username;
    private String password;
}
