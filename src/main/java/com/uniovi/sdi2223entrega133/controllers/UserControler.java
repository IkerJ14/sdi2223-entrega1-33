package com.uniovi.sdi2223entrega133.controllers;

import com.uniovi.sdi2223entrega133.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class UserControler {
    @Autowired
    private UserService userService;
}
