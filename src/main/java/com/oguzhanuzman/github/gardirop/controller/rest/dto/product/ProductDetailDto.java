package com.oguzhanuzman.github.gardirop.controller.rest.dto.product;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.oguzhanuzman.github.gardirop.Constants;
import com.oguzhanuzman.github.gardirop.controller.rest.dto.member.MemberDetailDto;
import com.oguzhanuzman.github.gardirop.controller.rest.dto.productcategory.ProductCategoryDetailDto;
import com.oguzhanuzman.github.gardirop.persistence.Product;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Getter
@Setter
@AllArgsConstructor
public class ProductDetailDto {

    private Long id;
    private String name;
    private BigDecimal price;
    private ProductCategoryDetailDto category;
    private MemberDetailDto createdBy;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = Constants.Format.JACKSON_DATE_FORMAT)
    private LocalDateTime creationDate;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = Constants.Format.JACKSON_DATE_FORMAT)
    private LocalDateTime modificationDate;
    private List<String> images;

    public static ProductDetailDto of(Product product, Function<String, String> imageKeyToUrlMapper) {
        if (product == null) {
            return null;
        }

        List<String> imageUrls = product.getImageKeys().stream().map(imageKeyToUrlMapper).collect(Collectors.toList());

        return new ProductDetailDto(
                product.getId(),
                product.getName(),
                product.getPrice(),
                ProductCategoryDetailDto.of(product.getCategory()),
                MemberDetailDto.of(product.getCreatedBy()),
                product.getCreationDate(),
                product.getModificationDate(),
                imageUrls
        );
    }
}
