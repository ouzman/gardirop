package com.oguzhanuzman.github.gardirop.specs;

import com.oguzhanuzman.github.gardirop.persistence.Product;
import com.oguzhanuzman.github.gardirop.persistence.ProductCategory;
import com.oguzhanuzman.github.gardirop.persistence.Product_;
import org.springframework.data.jpa.domain.Specification;

public class ProductSpecs {
    public static Specification<Product> byCategory(ProductCategory productCategory) {
        return (root, query, builder) -> {
            if (productCategory == null) {
                return null;
            }
            return builder.equal(root.get(Product_.category), productCategory);
        };
    }

    public static Specification<Product> byDeletedFalse() {
        return (root, query, builder) -> builder.equal(root.get(Product_.deleted), Boolean.FALSE);
    }

}