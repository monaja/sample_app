package com.brokersystems.audit;

import org.springframework.data.domain.AuditorAware;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import com.brokersystems.setups.model.User;

public class SpringSecurityAuditorAware implements AuditorAware<String> {

    public String getCurrentAuditor() {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || !authentication.isAuthenticated() ||
                authentication instanceof AnonymousAuthenticationToken) {
            return null;
        }

        return ((User) authentication.getPrincipal()).getUsername();
    }
}
