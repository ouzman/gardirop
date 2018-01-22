package com.oguzhanuzman.github.gardirop.service;

import com.oguzhanuzman.github.gardirop.controller.rest.product.ProductDetailDto;
import com.oguzhanuzman.github.gardirop.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {
    private final ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<ProductDetailDto> listDetails() {
        return this.productRepository.findAll().stream()
                .map(ProductDetailDto::of)
                .collect(Collectors.toList());
    }
}
