package com.oguzhanuzman.github.gardirop.controller.rest;

import com.oguzhanuzman.github.gardirop.controller.rest.dto.productcategory.ProductCategoryCreateDto;
import com.oguzhanuzman.github.gardirop.controller.rest.dto.productcategory.ProductCategoryDetailDto;
import com.oguzhanuzman.github.gardirop.controller.rest.dto.productcategory.ProductCategoryUpdateDto;
import com.oguzhanuzman.github.gardirop.service.ProductCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static com.oguzhanuzman.github.gardirop.Constants.Security.Role;

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
    @Secured(Role.ADMIN)
    public ProductCategoryDetailDto create(@Valid ProductCategoryCreateDto productCategoryCreateDto) {
        return this.productCategoryService.create(productCategoryCreateDto);
    }

    @PutMapping("/{id}")
    @Secured(Role.ADMIN)
    public ProductCategoryDetailDto update(@Valid ProductCategoryUpdateDto productCategoryUpdateDto) {
        return this.productCategoryService.update(productCategoryUpdateDto);
    }

    @DeleteMapping("/{id}")
    @Secured(Role.ADMIN)
    public void delete(@PathVariable Long id) {
        productCategoryService.delete(id);
    }
}
