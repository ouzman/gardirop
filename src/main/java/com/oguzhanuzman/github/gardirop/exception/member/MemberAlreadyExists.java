package com.oguzhanuzman.github.gardirop.exception.member;

import com.oguzhanuzman.github.gardirop.exception.base.RestException;

public class MemberAlreadyExists extends RestException {
    public MemberAlreadyExists() {
        super("Selected username already in use!");
    }
}
