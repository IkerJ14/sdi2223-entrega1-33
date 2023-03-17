package com.uniovi.sdi2223entrega133.handlers;


import com.fasterxml.jackson.annotation.*;
import com.uniovi.sdi2223entrega133.controllers.*;
import com.uniovi.sdi2223entrega133.entities.*;
import com.uniovi.sdi2223entrega133.services.*;
import org.slf4j.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

@Component
public class LogoutHandler implements LogoutSuccessHandler {

    @Autowired
    private LogsService logService;
    final static Logger logger = LoggerFactory.getLogger(UserController.class);
    private final RedirectStrategy redirectStrategy= new DefaultRedirectStrategy();
    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        Log log2 = new Log("LOGOUT", new Date(), "Usuario: " + authentication.getName() + " se ha desconectado");
        logService.addLog(log2);
        redirectStrategy.sendRedirect(request, response, "/login");
    }

}
