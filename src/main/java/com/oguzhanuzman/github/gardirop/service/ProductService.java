package com.oguzhanuzman.github.gardirop.service;

import com.oguzhanuzman.github.gardirop.controller.dto.FileUploadDto;
import com.oguzhanuzman.github.gardirop.controller.rest.dto.product.ProductCreateDto;
import com.oguzhanuzman.github.gardirop.controller.rest.dto.product.ProductDetailDto;
import com.oguzhanuzman.github.gardirop.controller.rest.dto.product.ProductSearchDto;
import com.oguzhanuzman.github.gardirop.controller.rest.dto.product.ProductUpdateDto;
import com.oguzhanuzman.github.gardirop.enums.Permission;
import com.oguzhanuzman.github.gardirop.exception.product.ProductImageException;
import com.oguzhanuzman.github.gardirop.exception.product.ProductNotFound;
import com.oguzhanuzman.github.gardirop.exception.product.ProductUpdateForbidden;
import com.oguzhanuzman.github.gardirop.persistence.Member;
import com.oguzhanuzman.github.gardirop.persistence.Product;
import com.oguzhanuzman.github.gardirop.persistence.ProductCategory;
import com.oguzhanuzman.github.gardirop.repository.ProductRepository;
import com.oguzhanuzman.github.gardirop.specs.ProductSpecs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.AuditorAware;
import org.springframework.http.InvalidMediaTypeException;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.MimeType;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.data.jpa.domain.Specifications.where;

@Service
public class ProductService {
    private final ProductRepository productRepository;
    private final ProductCategoryService productCategoryService;
    private final AuditorAware<Member> auditorAware;
    private final S3Service s3Service;

    @Autowired
    public ProductService(ProductRepository productRepository, ProductCategoryService productCategoryService, AuditorAware<Member> auditorAware, S3Service s3Service) {
        this.productRepository = productRepository;
        this.productCategoryService = productCategoryService;
        this.auditorAware = auditorAware;
        this.s3Service = s3Service;
    }

    public List<ProductDetailDto> listDetails(ProductSearchDto productSearchDto) {
        ProductCategory category = productCategoryService.findOne(productSearchDto.getCategory());
        return this.productRepository
                .findAll(where(ProductSpecs.byDeletedFalse())
                        .and(ProductSpecs.byCategory(category)))
                .stream()
                .map(p -> ProductDetailDto.of(p, s3Service::getProductImageUrl))
                .collect(Collectors.toList());
    }

    public ProductDetailDto create(ProductCreateDto productCreateDto) {
        ProductCategory category = this.productCategoryService.getOne(productCreateDto.getCategory());

        Product newProduct = this.productRepository.save(
                new Product(productCreateDto.getName(), productCreateDto.getPrice(), category)
        );

        return ProductDetailDto.of(newProduct, s3Service::getProductImageUrl);
    }

    public ProductDetailDto update(ProductUpdateDto productUpdateDto) {
        Product product = this.getOne(productUpdateDto.getId());
        validateUpdateRequirements(product);

        product.setName(productUpdateDto.getName());
        product.setPrice(productUpdateDto.getPrice());

        Product updatedProduct = this.productRepository.save(product);
        return ProductDetailDto.of(updatedProduct, s3Service::getProductImageUrl);
    }

    public void delete(Long id) {
        Product product = this.getOne(id);
        validateDeleteRequirements(product);

        product.setDeleted(true);
        this.productRepository.save(product);
    }

    @Transactional
    public void uploadImage(FileUploadDto fileUploadDto) {
        Product product = this.getOne(fileUploadDto.getId());
        MultipartFile file = fileUploadDto.getFile();
        validateUploadImageRequirements(product, fileUploadDto);

        InputStream inputStream = extractMultipartFileForInputStream(file);

        String imageKey = s3Service.addProductImage(product, inputStream, file.getName(), file.getContentType());
        product.getImageKeys().add(imageKey);
        this.productRepository.save(product);
    }

    private Product getOne(Long id) {
        Product product = this.productRepository.findByIdAndDeletedFalse(id);
        if (product == null) {
            throw new ProductNotFound(id);
        }
        return product;
    }

    private void validateUpdateRequirements(Product product) {
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

    private void validateDeleteRequirements(Product product) {
        this.validateUpdateRequirements(product);
    }

    private void validateUploadImageRequirements(Product product, FileUploadDto fileUploadDto) {
        this.validateUpdateRequirements(product);

        MultipartFile file = fileUploadDto.getFile();
        if (file == null) {
            throw new ProductImageException("No file provided!");
        }

        String contentType = file.getContentType();
        if (contentType == null) {
            throw new ProductImageException("Invalid content type!");
        }

        MediaType mediaType;
        try {
            mediaType = MediaType.parseMediaType(contentType);
        } catch (InvalidMediaTypeException e) {
            throw new ProductImageException("Unknown content type!", e);
        }

        if (!mediaType.isCompatibleWith(MimeType.valueOf("image/*"))) {
            throw new ProductImageException("Unsupported content type!");
        }
    }

    private InputStream extractMultipartFileForInputStream(MultipartFile multipartFile) {
        try {
            return multipartFile.getInputStream();
        } catch (IOException e) {
            throw new ProductImageException("An error occurred while reading the file!", e);
        }
    }
}
