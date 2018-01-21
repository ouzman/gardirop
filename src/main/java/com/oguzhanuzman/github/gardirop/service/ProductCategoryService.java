package com.oguzhanuzman.github.gardirop.service;

import com.oguzhanuzman.github.gardirop.controller.rest.dto.ProductCategoryCreateDto;
import com.oguzhanuzman.github.gardirop.controller.rest.dto.ProductCategoryDetailDto;
import com.oguzhanuzman.github.gardirop.exception.ProductCategoryAlreadyExists;
import com.oguzhanuzman.github.gardirop.persistence.ProductCategory;
import com.oguzhanuzman.github.gardirop.repository.ProductCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductCategoryService {
    private final ProductCategoryRepository productCategoryRepository;

    @Autowired
    public ProductCategoryService(ProductCategoryRepository productCategoryRepository) {
        this.productCategoryRepository = productCategoryRepository;
    }

    public List<ProductCategoryDetailDto> listDetails() {
        return this.productCategoryRepository.findAll().stream()
                .map(ProductCategoryDetailDto::of)
                .collect(Collectors.toList());
    }

    public ProductCategoryDetailDto create(ProductCategoryCreateDto productCategoryCreateDto) {
        if (this.productCategoryRepository.existsByName(productCategoryCreateDto.getName())) {
            throw new ProductCategoryAlreadyExists();
        }

        ProductCategory newProductCategory = this.productCategoryRepository.save(
                new ProductCategory(productCategoryCreateDto.getName())
        );

        return ProductCategoryDetailDto.of(newProductCategory);
    }
}
