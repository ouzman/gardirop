package com.oguzhanuzman.github.gardirop.persistence;

import com.oguzhanuzman.github.gardirop.persistence.base.SoftDeletableAuditableUniqueEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class ProductCategory extends SoftDeletableAuditableUniqueEntity {
    @Column(nullable = false, unique = true)
    private String name;
    @OneToMany(mappedBy = "category")
    private List<Product> products;

    public ProductCategory(String name) {
        this.name = name;
    }
}
