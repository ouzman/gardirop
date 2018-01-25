package com.oguzhanuzman.github.gardirop.exception.productcategory;

import com.oguzhanuzman.github.gardirop.exception.base.RestException;

public class ProductCategoryAlreadyExists extends RestException {
    public ProductCategoryAlreadyExists() {
        super("Selected name already in use!");
    }
}
