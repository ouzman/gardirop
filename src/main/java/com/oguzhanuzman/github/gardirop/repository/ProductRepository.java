package com.oguzhanuzman.github.gardirop.repository;

import com.oguzhanuzman.github.gardirop.persistence.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
}
