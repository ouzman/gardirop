package com.oguzhanuzman.github.gardirop.exception.product;

import com.oguzhanuzman.github.gardirop.exception.base.RestException;

public class ProductImageException extends RestException {
    public ProductImageException(String message) {
        super(message);
    }

    public ProductImageException(String message, Throwable cause) {
        super(message, cause);
    }
}
