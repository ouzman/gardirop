package com.oguzhanuzman.github.gardirop.persistence;

import com.oguzhanuzman.github.gardirop.persistence.base.UniqueEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class ProductCategory extends UniqueEntity {
    @Column(nullable = false)
    private String name;

    public ProductCategory(String name) {
        this.name = name;
    }
}
