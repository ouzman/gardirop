package com.oguzhanuzman.github.gardirop.exception.productcategory;

import com.oguzhanuzman.github.gardirop.exception.base.RestException;

public class ProductCategoryHasProducts extends RestException {
    public ProductCategoryHasProducts() {
        super("Delete failed! Product category has existing products!");
    }
}
