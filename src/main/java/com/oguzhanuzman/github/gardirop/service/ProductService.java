package com.oguzhanuzman.github.gardirop.service;

import com.oguzhanuzman.github.gardirop.controller.rest.product.ProductCreateDto;
import com.oguzhanuzman.github.gardirop.controller.rest.product.ProductDetailDto;
import com.oguzhanuzman.github.gardirop.controller.rest.product.ProductSearchDto;
import com.oguzhanuzman.github.gardirop.controller.rest.product.ProductUpdateDto;
import com.oguzhanuzman.github.gardirop.enums.Permission;
import com.oguzhanuzman.github.gardirop.exception.ProductNotFound;
import com.oguzhanuzman.github.gardirop.exception.ProductUpdateForbidden;
import com.oguzhanuzman.github.gardirop.persistence.Member;
import com.oguzhanuzman.github.gardirop.persistence.Product;
import com.oguzhanuzman.github.gardirop.persistence.ProductCategory;
import com.oguzhanuzman.github.gardirop.repository.ProductRepository;
import com.oguzhanuzman.github.gardirop.specs.ProductSpecs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.AuditorAware;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.data.jpa.domain.Specifications.where;

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

    public List<ProductDetailDto> listDetails(ProductSearchDto productSearchDto) {
        ProductCategory category = productCategoryService.findOne(productSearchDto.getCategory());
        return this.productRepository
                .findAll(where(ProductSpecs.byDeletedFalse())
                        .and(ProductSpecs.byCategory(category)))
                .stream()
                .map(ProductDetailDto::of)
                .collect(Collectors.toList());
    }

    public ProductDetailDto create(ProductCreateDto productCreateDto) {
        validateCreateDto(productCreateDto);

        ProductCategory category = this.productCategoryService.getOne(productCreateDto.getCategory());

        Product newProduct = this.productRepository.save(
                new Product(productCreateDto.getName(), productCreateDto.getPrice(), category)
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

    public void delete(Long id) {
        Product product = this.getOne(id);
        validateDelete(product);

        product.setDeleted(true);
        this.productRepository.save(product);
    }

    private Product getOne(Long id) {
        Product product = this.productRepository.findByIdAndDeletedFalse(id);
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

    private void validateDelete(Product product) {
        this.validateUpdateDto(product, null);
    }
}
