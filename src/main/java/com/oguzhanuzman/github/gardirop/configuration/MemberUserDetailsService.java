package com.oguzhanuzman.github.gardirop.configuration;

import com.oguzhanuzman.github.gardirop.persistence.Member;
import com.oguzhanuzman.github.gardirop.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MemberUserDetailsService implements UserDetailsService {
    private final MemberService memberService;

    @Autowired
    public MemberUserDetailsService(MemberService memberService) {
        this.memberService = memberService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if (username == null) {
            throw new UsernameNotFoundException("Username is not provided!");
        }

        Member member = memberService.findByUsername(username);
        if (member == null) {
            throw new UsernameNotFoundException("User not found!");
        }

        return new SecurityPrincipal(member);
    }
}
