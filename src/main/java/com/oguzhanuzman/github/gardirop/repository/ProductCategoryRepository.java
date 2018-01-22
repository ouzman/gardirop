package com.oguzhanuzman.github.gardirop.repository;

import com.oguzhanuzman.github.gardirop.persistence.ProductCategory;
import com.oguzhanuzman.github.gardirop.repository.base.SoftDeletableJpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductCategoryRepository extends SoftDeletableJpaRepository<ProductCategory, Long> {
    boolean existsByNameAndDeletedFalse(String name);
}
