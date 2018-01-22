package com.oguzhanuzman.github.gardirop.persistence.base;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.MappedSuperclass;

@Getter
@Setter
@MappedSuperclass
public class SoftDeletableAuditableUniqueEntity extends AuditableUniqueEntity {
    private boolean deleted;
}
