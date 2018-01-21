package com.oguzhanuzman.github.gardirop.exception;

public class ProductCategoryAlreadyExists extends RestException {
    public ProductCategoryAlreadyExists() {
        super("Selected name already in use!");
    }
}
