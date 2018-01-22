package com.oguzhanuzman.github.gardirop.persistence.base;

import com.oguzhanuzman.github.gardirop.persistence.Member;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.EntityListeners;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@Getter
@Setter
@EntityListeners(AuditingEntityListener.class)
@MappedSuperclass
public class AuditableUniqueEntity extends UniqueEntity {
    @CreatedBy
    @ManyToOne
    private Member createdBy;
    @CreatedDate
    private LocalDateTime creationDate;

    @LastModifiedBy
    @ManyToOne
    private Member modifiedBy;
    @LastModifiedDate
    private LocalDateTime modificationDate;
}
