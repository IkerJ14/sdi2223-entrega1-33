package com.uniovi.sdi2223entrega133.services;

import com.uniovi.sdi2223entrega133.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
@Service
public class InsertSampleDataService {
    @Autowired
    private UserService userService;

    @Autowired
    private RolesService rolesService;
    @PostConstruct
    public void init(){
        User user1 = new User("hola@gmail.com", "Pedro", "Díaz");
        user1.setPassword("123456");
        user1.setRole(rolesService.getRoles()[0]);
        User user2 = new User("test@test.com", "Lucas", "Núñez");
        user2.setPassword("123456");
        user2.setRole(rolesService.getRoles()[0]);
        User user3 = new User("admin@email.com", "Admin", "Admin");
        user3.setPassword("admin");
        user3.setRole(rolesService.getRoles()[1]);

        userService.addUser(user1);
        userService.addUser(user2);
        userService.addUser(user3);

    }
}
