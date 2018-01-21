package com.oguzhanuzman.github.gardirop.controller.rest;

import com.oguzhanuzman.github.gardirop.controller.rest.dto.ProductCategoryCreateDto;
import com.oguzhanuzman.github.gardirop.controller.rest.dto.ProductCategoryDetailDto;
import com.oguzhanuzman.github.gardirop.service.ProductCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/product-category")
public class ProductCategoryRestController {
    private final ProductCategoryService productCategoryService;

    @Autowired
    public ProductCategoryRestController(ProductCategoryService productCategoryService) {
        this.productCategoryService = productCategoryService;
    }

    @GetMapping
    public List<ProductCategoryDetailDto> list() {
        return this.productCategoryService.listDetails();
    }

    @PostMapping
    public ProductCategoryDetailDto create(ProductCategoryCreateDto productCategoryCreateDto) {
        return this.productCategoryService.create(productCategoryCreateDto);
    }
}
