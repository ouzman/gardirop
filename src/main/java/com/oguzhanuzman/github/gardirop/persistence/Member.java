package com.oguzhanuzman.github.gardirop.persistence;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

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
    @Column(nullable = false)
    private String password;

    public Member(String screenName, String username, String password) {
        this.screenName = screenName;
        this.username = username;
        this.password = password;
    }
}
