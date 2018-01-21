package com.oguzhanuzman.github.gardirop.repository;

import com.oguzhanuzman.github.gardirop.persistence.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductCategoryRepository extends JpaRepository<ProductCategory, Long> {
    boolean existsByName(String name);
}
