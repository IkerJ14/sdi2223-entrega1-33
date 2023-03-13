package com.uniovi.sdi2223entrega133.controllers;

import com.uniovi.sdi2223entrega133.entities.User;
import com.uniovi.sdi2223entrega133.services.SecurityService;
import com.uniovi.sdi2223entrega133.services.UserService;
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
import com.uniovi.sdi2223entrega133.services.RolesService;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Arrays;
import java.util.Date;

@Controller
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private RolesService rolesService;
    @Autowired
    private SecurityService securityService;
    @Autowired
    private SignUpFormValidator signUpFormValidator;
    @RequestMapping(value = {"/home"}, method = RequestMethod.GET)
    public String home(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return "home";
    }
    @RequestMapping(value = "/signup", method = RequestMethod.GET)
    public String signup(Model model) {
        model.addAttribute("user", new User());
        return "signup";
    }
    @RequestMapping(value = "/signup", method = RequestMethod.POST)
    public String signup(@Validated User user, BindingResult result, Model model) {
        signUpFormValidator.validate(user,result);
        if(result.hasErrors()){
            model.addAttribute("user", user);
            return "signup";

        }
        user.setRole(rolesService.getRoles()[0]);
        userService.addUser(user);
        securityService.autoLogin(user.getEmail(), user.getPasswordConfirm());
        return "redirect:home";
    }
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login() {
        return "login";
    }

    @RequestMapping("/user/list")
    public String getList(Model model) {
        model.addAttribute("usersList", userService.getUsers());
        return "user/list";
    }
    @RequestMapping("/user/list/update")
    public String updateList(Model model){
        model.addAttribute("usersList", userService.getUsers() );
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
        return "redirect:list";
    }
}
