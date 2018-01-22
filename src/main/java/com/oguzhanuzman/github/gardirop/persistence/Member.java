package com.oguzhanuzman.github.gardirop.persistence;

import com.oguzhanuzman.github.gardirop.enums.Permission;
import com.oguzhanuzman.github.gardirop.persistence.base.AuditableUniqueEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Member extends AuditableUniqueEntity {
    @Column(nullable = false)
    private String screenName;
    @Column(nullable = false, unique = true)
    private String username;
    @Column(nullable = false, columnDefinition = "text")
    private String password;
    @ElementCollection(targetClass = Permission.class, fetch = FetchType.EAGER)
    @Enumerated(EnumType.STRING)
    private List<Permission> permissions;

    public Member(String screenName, String username, String password, List<Permission> permissions) {
        this.screenName = screenName;
        this.username = username;
        this.password = password;
        this.permissions = permissions;
    }
}
