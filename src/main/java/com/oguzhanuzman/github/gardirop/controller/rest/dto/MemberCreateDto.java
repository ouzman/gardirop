package com.oguzhanuzman.github.gardirop.controller.rest.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;

@NoArgsConstructor
@Getter
@Setter
public class MemberCreateDto {
    @Min(
            value = 5,
            message = "Screen name should be longer than 5 characters!"
    )
    private String screenName;
    @Min(
            value = 5,
            message = "Username should be longer than 5 characters!"
    )
    private String username;
    @Min(
            value = 8,
            message = "Password should be longer than 8 characters!"
    )
    @Pattern(
            regexp = "(?=.*\\p{Lu})(?=.*\\p{Ll})(?=.*[!@#$&*])(?=.*[0-9])",
            message = "Password should contains one upper case, one lower case, one special character and one alphanumeric character!"
    )
    private String password;
}
