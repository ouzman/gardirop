package com.oguzhanuzman.github.gardirop.service;

import com.oguzhanuzman.github.gardirop.exception.RestException;

public class ProductCategoryHasProducts extends RestException {
    public ProductCategoryHasProducts() {
        super("Delete failed! Product category has existing products!");
    }
}
