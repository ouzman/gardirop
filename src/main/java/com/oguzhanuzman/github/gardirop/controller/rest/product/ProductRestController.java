package com.oguzhanuzman.github.gardirop.controller.rest.product;

import com.oguzhanuzman.github.gardirop.Constants;
import com.oguzhanuzman.github.gardirop.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/product")
public class ProductRestController {
    private final ProductService productService;

    @Autowired
    public ProductRestController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public List<ProductDetailDto> list() {
        return this.productService.listDetails();
    }

    @PostMapping
    @Secured(Constants.Security.Role.MEMBER)
    public ProductDetailDto create(@Valid ProductCreateDto productCreateDto) {
        return this.productService.create(productCreateDto);
    }
}
