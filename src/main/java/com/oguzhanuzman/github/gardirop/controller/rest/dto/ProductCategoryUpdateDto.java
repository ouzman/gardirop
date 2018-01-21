package com.oguzhanuzman.github.gardirop.controller.rest.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class ProductCategoryUpdateDto extends ProductCategoryCreateDto {
    @NotNull(message = "Id shouldn't be empty!")
    @Min(value = 1, message = "Id should be valid product category id!")
    private Long id;
}
