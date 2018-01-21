package com.oguzhanuzman.github.gardirop.controller.rest.productcategory;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

@Getter
@Setter
@NoArgsConstructor
public class ProductCategoryCreateDto {
    @Length(min = 3, max = 255, message = "Name should be at least 3, most 255 characters long!")
    @NotEmpty(message = "Name shouldn't be empty!")
    private String name;
}
