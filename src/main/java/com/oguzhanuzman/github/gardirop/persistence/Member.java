package com.oguzhanuzman.github.gardirop.persistence;

import com.oguzhanuzman.github.gardirop.enums.Permission;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Fetch;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Member {
    @Id
    @GeneratedValue
    private Long id;
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
