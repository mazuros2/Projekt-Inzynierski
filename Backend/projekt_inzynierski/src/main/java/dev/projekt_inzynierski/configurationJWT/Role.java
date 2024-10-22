package dev.projekt_inzynierski.configurationJWT;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
    ZAWODNIK,
    MENADZER_KLUBU,
    TRENER,
    SKAUT;


    @Override
    public String getAuthority() {
        return this.name();
    }
}
