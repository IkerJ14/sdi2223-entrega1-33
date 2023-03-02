package com.uniovi.sdi2223entrega133.services;

import org.springframework.stereotype.Service;

@Service
public class RolesService {
    String[] roles = {"ROLE_ESTANDAR", "ROLE_ADMIN"};
    public String[] getRoles() {
        return roles;
    }
}
