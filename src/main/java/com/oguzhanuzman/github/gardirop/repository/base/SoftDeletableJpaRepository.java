package com.oguzhanuzman.github.gardirop.repository.base;

import com.oguzhanuzman.github.gardirop.persistence.base.SoftDeletableAuditableUniqueEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;
import java.util.List;

@NoRepositoryBean
public interface SoftDeletableJpaRepository<T extends SoftDeletableAuditableUniqueEntity, ID extends Serializable> extends JpaRepository<T, ID> {
    List<T> findByDeletedFalse();

    T findByIdAndDeletedFalse(ID id);
}
