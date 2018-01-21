package com.oguzhanuzman.github.gardirop.persistence;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class ProductCategory {
    @Id
    @GeneratedValue
    private Long id;
    @Column(nullable = false)
    private String name;

    public ProductCategory(String name) {
        this.name = name;
    }
}
