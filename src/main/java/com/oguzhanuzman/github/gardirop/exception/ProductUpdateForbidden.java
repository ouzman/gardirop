package com.oguzhanuzman.github.gardirop.exception;

import org.springframework.security.access.AccessDeniedException;

public class ProductUpdateForbidden extends AccessDeniedException {
    public ProductUpdateForbidden(String message) {
        super(message);
    }

    public ProductUpdateForbidden() {
        super("Only owners can update product!");
    }
}
