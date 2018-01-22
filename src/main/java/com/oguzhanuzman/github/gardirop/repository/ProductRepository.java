package com.oguzhanuzman.github.gardirop.repository;

import com.oguzhanuzman.github.gardirop.persistence.Product;
import com.oguzhanuzman.github.gardirop.repository.base.SoftDeletableJpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends SoftDeletableJpaRepository<Product, Long>, JpaSpecificationExecutor<Product> {
}
