package com.oguzhanuzman.github.gardirop.persistence.base;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
@Getter
@Setter
public class UniqueEntity {
    @Id
    @GeneratedValue
    private Long id;
}
