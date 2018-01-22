package com.oguzhanuzman.github.gardirop.exception;

import javax.persistence.EntityNotFoundException;

public class ProductCategoryNotFound extends EntityNotFoundException {
    public ProductCategoryNotFound(Long id) {
        super(String.format("Product category #%d not found!", id));
    }
}
