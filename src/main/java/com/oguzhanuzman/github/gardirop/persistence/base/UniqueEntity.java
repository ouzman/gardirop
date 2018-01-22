package com.oguzhanuzman.github.gardirop.persistence.base;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
@Getter
@Setter
public class UniqueEntity {
    @GenericGenerator(
            name = "gardiropSequenceGenerator",
            strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
            parameters = @Parameter(name = "sequence_name", value = "hibernate_sequence")
    )
    @Id
    @GeneratedValue(generator = "gardiropSequenceGenerator")
    private Long id;
}
