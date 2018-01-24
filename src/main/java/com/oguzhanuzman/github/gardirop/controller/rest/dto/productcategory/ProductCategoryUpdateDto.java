package com.oguzhanuzman.github.gardirop.controller.rest.dto.productcategory;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class ProductCategoryUpdateDto extends ProductCategoryCreateDto {
    @NotNull(message = "Id shouldn't be empty!")
    private Long id;
}
