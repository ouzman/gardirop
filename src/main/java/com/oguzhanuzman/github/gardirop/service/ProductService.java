package com.oguzhanuzman.github.gardirop.service;

import com.oguzhanuzman.github.gardirop.controller.rest.product.ProductCreateDto;
import com.oguzhanuzman.github.gardirop.controller.rest.product.ProductDetailDto;
import com.oguzhanuzman.github.gardirop.controller.rest.product.ProductUpdateDto;
import com.oguzhanuzman.github.gardirop.enums.Permission;
import com.oguzhanuzman.github.gardirop.exception.ProductNotFound;
import com.oguzhanuzman.github.gardirop.exception.ProductUpdateForbidden;
import com.oguzhanuzman.github.gardirop.persistence.Member;
import com.oguzhanuzman.github.gardirop.persistence.Product;
import com.oguzhanuzman.github.gardirop.persistence.ProductCategory;
import com.oguzhanuzman.github.gardirop.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.AuditorAware;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {
    private final ProductRepository productRepository;
    private final ProductCategoryService productCategoryService;
    private final AuditorAware<Member> auditorAware;

    @Autowired
    public ProductService(ProductRepository productRepository, ProductCategoryService productCategoryService, AuditorAware<Member> auditorAware) {
        this.productRepository = productRepository;
        this.productCategoryService = productCategoryService;
        this.auditorAware = auditorAware;
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

    public ProductDetailDto update(ProductUpdateDto productUpdateDto) {
        Product product = this.getOne(productUpdateDto.getId());
        validateUpdateDto(product, productUpdateDto);

        product.setName(productUpdateDto.getName());
        product.setPrice(productUpdateDto.getPrice());

        Product updatedProduct = this.productRepository.save(product);
        return ProductDetailDto.of(updatedProduct);
    }


    private Product getOne(Long id) {
        Product product = this.productRepository.findOne(id);
        if (product == null) {
            throw new ProductNotFound(id);
        }
        return product;
    }

    private void validateCreateDto(ProductCreateDto dto) {
        // nothing for now
    }

    private void validateUpdateDto(Product product, ProductUpdateDto dto) {
        Member currentAuditor = auditorAware.getCurrentAuditor();
        if (currentAuditor == null) {
            throw new ProductUpdateForbidden("Full authentication is required to access this resource");
        }

        boolean currentAuditorIsAdmin = currentAuditor.getPermissions().contains(Permission.ADMIN);
        boolean currentAuditorIsOwner = product.getCreatedBy().equals(currentAuditor);

        if (!currentAuditorIsAdmin && !currentAuditorIsOwner) {
            throw new ProductUpdateForbidden();
        }
    }
}
