package com.oguzhanuzman.github.gardirop.controller.rest.dto;


import com.oguzhanuzman.github.gardirop.persistence.ProductCategory;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ProductCategoryDetailDto {
    private Long id;
    private String name;

    public static ProductCategoryDetailDto of(ProductCategory productCategory) {
        return new ProductCategoryDetailDto(productCategory.getId(), productCategory.getName());
    }
}
