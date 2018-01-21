package com.oguzhanuzman.github.gardirop.exception;

import org.springframework.http.HttpStatus;

public class ProductCategoryNotFound extends RestException {
    public ProductCategoryNotFound(Long id) {
        super(String.format("Product category #%d not found!", id), HttpStatus.NOT_FOUND);
    }
}
