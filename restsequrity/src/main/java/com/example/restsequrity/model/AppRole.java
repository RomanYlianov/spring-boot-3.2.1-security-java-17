package com.example.restsequrity.model;

import org.springframework.security.core.GrantedAuthority;

public enum AppRole implements GrantedAuthority {

    USER, ROOT;

    @Override
    public String getAuthority() {
        return name();
    }
}
