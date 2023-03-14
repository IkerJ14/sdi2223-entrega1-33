package com.uniovi.sdi2223entrega133.handlers;

import com.uniovi.sdi2223entrega133.controllers.*;
import com.uniovi.sdi2223entrega133.entities.*;
import com.uniovi.sdi2223entrega133.services.*;
import org.slf4j.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.*;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

@Component
public class LoginFailHandler implements AuthenticationFailureHandler {
    @Autowired
    private LogsService logService;
    final static Logger logger = LoggerFactory.getLogger(UserController.class);
    private final RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        Log log2 = new Log("LOGIN_ERR", new Date(), "Usuario: Intento conectarse");
        logService.addLog(log2);
        redirectStrategy.sendRedirect(request, response, "/login");
    }
}
