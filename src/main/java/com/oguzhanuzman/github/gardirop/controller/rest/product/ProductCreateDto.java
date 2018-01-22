package com.oguzhanuzman.github.gardirop.controller.rest.product;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Digits;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
public class ProductCreateDto {
    @NotEmpty(message = "Name shouldn't be empty!")
    @Length(min = 3, max = 255, message = "Name should be at least 3, most 255 characters long!")
    private String name;

    @Digits(integer = 6, fraction = 2, message = "Price should be at least 0.00, most 999999.99 TRY!")
    @Min(value = 0, message = "Price should be at least 0.00, most 999999.99 TRY!")
    private BigDecimal price;

    @NotNull
    @Min(value = 0, message = "Category should be valid product category id!")
    private Long category;
}
