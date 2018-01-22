package com.oguzhanuzman.github.gardirop.service;

import com.oguzhanuzman.github.gardirop.controller.rest.product.ProductCreateDto;
import com.oguzhanuzman.github.gardirop.controller.rest.product.ProductDetailDto;
import com.oguzhanuzman.github.gardirop.persistence.Product;
import com.oguzhanuzman.github.gardirop.persistence.ProductCategory;
import com.oguzhanuzman.github.gardirop.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {
    private final ProductRepository productRepository;
    private final ProductCategoryService productCategoryService;

    @Autowired
    public ProductService(ProductRepository productRepository, ProductCategoryService productCategoryService) {
        this.productRepository = productRepository;
        this.productCategoryService = productCategoryService;
    }

    public List<ProductDetailDto> listDetails() {
        return this.productRepository.findAll().stream()
                .map(ProductDetailDto::of)
                .collect(Collectors.toList());
    }

    public ProductDetailDto create(ProductCreateDto productCreateDto) {
        validateCreateDto(productCreateDto);

        ProductCategory category = this.productCategoryService.getOne(productCreateDto.getCategory());

        Product newProduct = this.productRepository.save(
                new Product(
                        productCreateDto.getName(),
                        productCreateDto.getPrice(),
                        category
                )
        );

        return ProductDetailDto.of(newProduct);
    }

    private void validateCreateDto(ProductCreateDto dto) {
        // nothing for now
    }

}
