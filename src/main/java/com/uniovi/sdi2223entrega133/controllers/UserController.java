package com.uniovi.sdi2223entrega133.controllers;

import com.uniovi.sdi2223entrega133.entities.*;
import com.uniovi.sdi2223entrega133.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.uniovi.sdi2223entrega133.validators.SignUpFormValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.security.Principal;
import java.util.*;
import com.uniovi.sdi2223entrega133.services.RolesService;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Arrays;
import java.util.Date;

@Controller
public class UserController {
    final static Logger logger = LoggerFactory.getLogger(UserController.class);
    @Autowired
    private UserService userService;
    @Autowired
    private RolesService rolesService;
    @Autowired
    private SecurityService securityService;
    @Autowired
    private SignUpFormValidator signUpFormValidator;

    @Autowired
    private LogsService logService;
    @RequestMapping(value = {"/home"}, method = RequestMethod.GET)
    public String home(Model model, Principal principal) {
        User user = userService.getUserByEmail(principal.getName());
        model.addAttribute("activeUser", user);

        Log log = new Log("PET", new Date(), "UserController: GET: home");
        logService.addLog(log);
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        logger.info("Se realizo peticion get /home");
        return "home";
    }
    @RequestMapping(value = "/signup", method = RequestMethod.GET)
    public String signup(Model model) {

        Log log = new Log("ALTA", new Date(), "UserController: GET: signup");
        logService.addLog(log);
        model.addAttribute("user", new User());
        logger.info("Se realizo peticion get /signup");
        return "signup";
    }
    @RequestMapping(value = "/signup", method = RequestMethod.POST)
    public String signup(@Validated User user, BindingResult result, Model model) {
        signUpFormValidator.validate(user,result);
        if(result.hasErrors()){
            model.addAttribute("user", user);
            logger.info("Se realizo peticion post /signup pero resulto erronea");
            return "signup";

        }
        user.setRole(rolesService.getRoles()[0]);
        userService.addUser(user);
        securityService.autoLogin(user.getEmail(), user.getPasswordConfirm());
        Log log = new Log("PET", new Date(), "UserController: POST: signup");
        logService.addLog(log);
        Log log2 = new Log("ALTA", new Date(), "UserController: POST: signup( "+user.getEmail()+ ", " + user.getName() + ", " + user.getLastName() + ", " + user.getPassword() + ", " + user.getPasswordConfirm() +" )");
        logService.addLog(log2);
        logger.info("Se realizo peticion post /signup");
        return "redirect:/offer/user_list";
    }
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login() {

        logger.info("Se realizo peticion get /login");
        return "login";
    }

    @RequestMapping("/user/list")
    public String getList(Model model) {
        model.addAttribute("usersList", userService.getUsers());
        Log log2 = new Log("PET", new Date(), "UserController: GET: user/list");
        logService.addLog(log2);
        logger.info("Se realizo peticion get /user/list");
        return "user/list";
    }
    @RequestMapping("/user/list/update")
    public String updateList(Model model){
        model.addAttribute("usersList", userService.getUsers() );
        Log log2 = new Log("PET", new Date(), "UserController: GET: user/list/update");
        logService.addLog(log2);
        logger.info("Se realizo peticion get /user/list/update");
        return "user/list :: tableUsers";
    }

    @RequestMapping("/user/delete")
    public String delete(@RequestParam(required = false) String id, Model model) {
        if(id == null)
            return "redirect:list";

        Arrays.stream(id.split(","))
                .map(Long::parseLong)
                .forEach(userService::deleteUser);

        model.addAttribute("usersList", userService.getUsers());
        Log log2 = new Log("PET", new Date(), "UserController: GET: user/delete");
        logService.addLog(log2);
        return "redirect:list";
    }
}
