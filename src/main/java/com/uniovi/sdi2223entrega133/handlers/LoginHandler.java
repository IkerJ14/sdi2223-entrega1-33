package com.uniovi.sdi2223entrega133.handlers;

import com.uniovi.sdi2223entrega133.services.RolesService;
import com.uniovi.sdi2223entrega133.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

@Component
public class LoginHandler implements AuthenticationSuccessHandler {

    @Autowired
    private UserService usersService;

    @Autowired
    private RolesService rolesService;

    private final RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {
        String role = usersService.getUserByEmail(authentication.getName()).getRole();
        if (role.equals(rolesService.getRoles()[0]))
            redirectStrategy.sendRedirect(request, response, "/offer/user_list");
        else
            redirectStrategy.sendRedirect(request, response, "/user/list");
    }
}
