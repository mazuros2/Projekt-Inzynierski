package dev.projekt_inzynierski.configurationJWT.Authentication;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;


import java.util.List;

@Component
public class UserRoleValidator {

    public void userRoleValidator(String... allowedRoles) {
        var authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .noneMatch(role -> List.of(allowedRoles).contains(role))) {
            throw new AccessDeniedException("Nie masz wymaganych uprawnień!");
        }
    }

}
