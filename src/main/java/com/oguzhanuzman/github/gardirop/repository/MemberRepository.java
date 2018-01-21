package com.oguzhanuzman.github.gardirop.repository;

import com.oguzhanuzman.github.gardirop.persistence.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {
    Member findByUsername(String username);

    boolean existsByUsername(String username);
}
