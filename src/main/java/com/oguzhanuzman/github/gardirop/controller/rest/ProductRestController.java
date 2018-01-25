package com.oguzhanuzman.github.gardirop.controller.rest;

import com.oguzhanuzman.github.gardirop.controller.rest.dto.product.ProductCreateDto;
import com.oguzhanuzman.github.gardirop.controller.rest.dto.product.ProductDetailDto;
import com.oguzhanuzman.github.gardirop.controller.rest.dto.product.ProductSearchDto;
import com.oguzhanuzman.github.gardirop.controller.rest.dto.product.ProductUpdateDto;
import com.oguzhanuzman.github.gardirop.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static com.oguzhanuzman.github.gardirop.Constants.Security.Role;

@RestController
@RequestMapping("/api/product")
public class ProductRestController {
    private final ProductService productService;

    @Autowired
    public ProductRestController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public List<ProductDetailDto> list(ProductSearchDto productSearchDto) {
        return this.productService.listDetails(productSearchDto);
    }

    @PostMapping
    @Secured(Role.MEMBER)
    public ProductDetailDto create(@Valid ProductCreateDto productCreateDto) {
        return this.productService.create(productCreateDto);
    }

    @PutMapping("/{id}")
    @Secured(Role.MEMBER)
    public ProductDetailDto update(@Valid ProductUpdateDto productUpdateDto) {
        return this.productService.update(productUpdateDto);
    }

    @DeleteMapping("/{id}")
    @Secured(Role.MEMBER)
    public void delete(@PathVariable Long id) {
        this.productService.delete(id);
    }
}
