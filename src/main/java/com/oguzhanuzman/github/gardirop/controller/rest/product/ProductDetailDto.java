package com.oguzhanuzman.github.gardirop.controller.rest.product;

import com.oguzhanuzman.github.gardirop.controller.rest.productcategory.ProductCategoryDetailDto;
import com.oguzhanuzman.github.gardirop.persistence.Product;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
public class ProductDetailDto {
    private Long id;
    private String name;
    private BigDecimal price;
    private ProductCategoryDetailDto category;

    public static ProductDetailDto of(Product product) {
        return new ProductDetailDto(
                product.getId(),
                product.getName(),
                product.getPrice(),
                ProductCategoryDetailDto.of(product.getCategory())
        );
    }
}
