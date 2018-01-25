package com.oguzhanuzman.github.gardirop.exception.product;

import javax.persistence.EntityNotFoundException;

public class ProductNotFound extends EntityNotFoundException {
    public ProductNotFound(Long id) {
        super(String.format("Product #%d not found!", id));
    }
}
