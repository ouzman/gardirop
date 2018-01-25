package com.oguzhanuzman.github.gardirop.persistence;

import com.oguzhanuzman.github.gardirop.persistence.base.SoftDeletableAuditableUniqueEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Product extends SoftDeletableAuditableUniqueEntity {
    @Column(nullable = false)
    private String name;
    @Column(nullable = false, precision = 8, scale = 2)
    private BigDecimal price;
    @ManyToOne(optional = false)
    private ProductCategory category;
    @ElementCollection
    @CollectionTable(
            name = "product_image_keys",
            joinColumns = @JoinColumn(name = "product_id")
    )
    private List<String> imageKeys = new ArrayList<>();

    public Product(String name, BigDecimal price, ProductCategory category) {
        this(name, price, category, new ArrayList<>());
    }

    public Product(String name, BigDecimal price, ProductCategory category, List<String> imageKeys) {
        this.name = name;
        this.price = price;
        this.category = category;
        this.imageKeys = imageKeys;
    }

}