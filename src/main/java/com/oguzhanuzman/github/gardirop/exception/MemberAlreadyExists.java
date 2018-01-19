package com.oguzhanuzman.github.gardirop.exception;

public class MemberAlreadyExists extends RestException {
    public MemberAlreadyExists() {
        super("Selected username already in use!");
    }
}
