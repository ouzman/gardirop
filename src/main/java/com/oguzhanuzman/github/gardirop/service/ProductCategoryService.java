package com.oguzhanuzman.github.gardirop.service;

import com.oguzhanuzman.github.gardirop.controller.rest.dto.ProductCategoryCreateDto;
import com.oguzhanuzman.github.gardirop.controller.rest.dto.ProductCategoryDetailDto;
import com.oguzhanuzman.github.gardirop.controller.rest.dto.ProductCategoryUpdateDto;
import com.oguzhanuzman.github.gardirop.exception.ProductCategoryAlreadyExists;
import com.oguzhanuzman.github.gardirop.exception.ProductCategoryNotFound;
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
        validateCreateDto(productCategoryCreateDto);

        ProductCategory newProductCategory = this.productCategoryRepository.save(new ProductCategory(productCategoryCreateDto.getName()));

        return ProductCategoryDetailDto.of(newProductCategory);
    }

    public ProductCategoryDetailDto update(ProductCategoryUpdateDto productCategoryUpdateDto) {
        ProductCategory productCategory = getOne(productCategoryUpdateDto.getId());
        validateUpdateDto(productCategory, productCategoryUpdateDto);

        productCategory.setName(productCategoryUpdateDto.getName());
        ProductCategory updatedProductCategory = productCategoryRepository.save(productCategory);

        return ProductCategoryDetailDto.of(updatedProductCategory);
    }


    private ProductCategory getOne(Long id) {
        ProductCategory productCategory = this.productCategoryRepository.findOne(id);
        if (productCategory == null) {
            throw new ProductCategoryNotFound(id);
        }
        return productCategory;
    }

    private void validateCreateDto(ProductCategoryCreateDto dto) {
        if (this.productCategoryRepository.existsByName(dto.getName())) {
            throw new ProductCategoryAlreadyExists();
        }
    }

    private void validateUpdateDto(ProductCategory productCategory, ProductCategoryUpdateDto dto) {
        if (!productCategory.getName().equals(dto.getName()) && this.productCategoryRepository.existsByName(dto.getName())) {
            throw new ProductCategoryAlreadyExists();
        }
    }
}
