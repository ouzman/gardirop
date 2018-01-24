package com.oguzhanuzman.github.gardirop.controller.rest.dto.productcategory;


import com.oguzhanuzman.github.gardirop.persistence.ProductCategory;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ProductCategoryDetailDto {
    private Long id;
    private String name;

    public static ProductCategoryDetailDto of(ProductCategory productCategory) {
        if (productCategory == null) {
            return null;
        }

        return new ProductCategoryDetailDto(productCategory.getId(), productCategory.getName());
    }
}
