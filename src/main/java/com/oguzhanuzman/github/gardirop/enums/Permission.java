package com.oguzhanuzman.github.gardirop.enums;

import com.oguzhanuzman.github.gardirop.Constants;
import lombok.Getter;

@Getter
public enum Permission {
    MEMBER(Constants.Security.Role.MEMBER),
    ADMIN(Constants.Security.Role.ADMIN);

    private String springSecurityRole;

    Permission(String springSecurityRole) {
        this.springSecurityRole = springSecurityRole;
    }
}
