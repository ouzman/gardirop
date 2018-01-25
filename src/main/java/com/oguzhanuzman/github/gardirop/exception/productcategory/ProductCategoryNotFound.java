package com.oguzhanuzman.github.gardirop.exception.productcategory;

import javax.persistence.EntityNotFoundException;

public class ProductCategoryNotFound extends EntityNotFoundException {
    public ProductCategoryNotFound(Long id) {
        super(String.format("Product category #%d not found!", id));
    }
}
