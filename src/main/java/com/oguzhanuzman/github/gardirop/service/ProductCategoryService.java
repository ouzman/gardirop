package com.oguzhanuzman.github.gardirop.service;

import com.oguzhanuzman.github.gardirop.controller.rest.dto.productcategory.ProductCategoryCreateDto;
import com.oguzhanuzman.github.gardirop.controller.rest.dto.productcategory.ProductCategoryDetailDto;
import com.oguzhanuzman.github.gardirop.controller.rest.dto.productcategory.ProductCategoryUpdateDto;
import com.oguzhanuzman.github.gardirop.exception.productcategory.ProductCategoryAlreadyExists;
import com.oguzhanuzman.github.gardirop.exception.productcategory.ProductCategoryHasProducts;
import com.oguzhanuzman.github.gardirop.exception.productcategory.ProductCategoryNotFound;
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
        return this.productCategoryRepository.findByDeletedFalse().stream()
                .map(ProductCategoryDetailDto::of)
                .collect(Collectors.toList());
    }

    public ProductCategoryDetailDto create(ProductCategoryCreateDto productCategoryCreateDto) {
        validateCreateRequirements(productCategoryCreateDto);

        ProductCategory newProductCategory = this.productCategoryRepository.save(new ProductCategory(productCategoryCreateDto.getName()));

        return ProductCategoryDetailDto.of(newProductCategory);
    }

    public ProductCategoryDetailDto update(ProductCategoryUpdateDto productCategoryUpdateDto) {
        ProductCategory productCategory = getOne(productCategoryUpdateDto.getId());
        validateUpdateRequirements(productCategory, productCategoryUpdateDto);

        productCategory.setName(productCategoryUpdateDto.getName());
        ProductCategory updatedProductCategory = this.productCategoryRepository.save(productCategory);

        return ProductCategoryDetailDto.of(updatedProductCategory);
    }

    public void delete(Long id) {
        ProductCategory productCategory = getOne(id);
        validateDeleteRequirements(productCategory);

        productCategory.setDeleted(true);
        this.productCategoryRepository.save(productCategory);
    }

    public ProductCategory findOne(Long id) {
        return this.productCategoryRepository.findByIdAndDeletedFalse(id);
    }

    public ProductCategory getOne(Long id) {
        ProductCategory productCategory = this.findOne(id);
        if (productCategory == null) {
            throw new ProductCategoryNotFound(id);
        }
        return productCategory;
    }


    private void validateCreateRequirements(ProductCategoryCreateDto dto) {
        if (this.productCategoryRepository.existsByNameAndDeletedFalse(dto.getName())) {
            throw new ProductCategoryAlreadyExists();
        }
    }

    private void validateUpdateRequirements(ProductCategory productCategory, ProductCategoryUpdateDto dto) {
        if (!productCategory.getName().equals(dto.getName()) && this.productCategoryRepository.existsByNameAndDeletedFalse(dto.getName())) {
            throw new ProductCategoryAlreadyExists();
        }
    }

    private void validateDeleteRequirements(ProductCategory productCategory) {
        if (!productCategory.getProducts().isEmpty()) {
            throw new ProductCategoryHasProducts();
        }
    }
}
