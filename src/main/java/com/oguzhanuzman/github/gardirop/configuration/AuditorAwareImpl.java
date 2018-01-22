package com.oguzhanuzman.github.gardirop.configuration;

import com.oguzhanuzman.github.gardirop.persistence.Member;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class AuditorAwareImpl implements AuditorAware<Member> {
    @Override
    public Member getCurrentAuditor() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null) {
            return null;
        }

        if (!(authentication.getPrincipal() instanceof SecurityPrincipal)) {
            return null;
        }

        SecurityPrincipal principal = (SecurityPrincipal) authentication.getPrincipal();
        if (principal == null) {
            return null;
        }

        return principal.getMember();
    }
}
