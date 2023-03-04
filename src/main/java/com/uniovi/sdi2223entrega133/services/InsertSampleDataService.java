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
        User user1 = new User("user01@email.com", "Pedro", "Díaz");
        user1.setPassword("user01");
        user1.setRole(rolesService.getRoles()[0]);
        User user2 = new User("user02@email.com", "Lucas", "Núñez");
        user2.setPassword("user02");
        user2.setRole(rolesService.getRoles()[0]);
        User user3 = new User("user03@email.com", "Paco", "Flores");
        user3.setPassword("user03s");
        user3.setRole(rolesService.getRoles()[0]);
        User user4 = new User("user04@email.com", "Felipe", "Rodríguez");
        user4.setPassword("user04");
        user4.setRole(rolesService.getRoles()[0]);
        User user5 = new User("user05@email.com", "Esteban", "Colás");
        user5.setPassword("user05");
        user5.setRole(rolesService.getRoles()[0]);
        User user6 = new User("user06@email.com", "Pelayo", "Fernández");
        user6.setPassword("user06");
        user6.setRole(rolesService.getRoles()[0]);
        User user7 = new User("user07@email.com", "Enrique", "Suárez");
        user7.setPassword("user07");
        user7.setRole(rolesService.getRoles()[0]);
        User user8 = new User("user08@email.com", "Pepe", "Rodríguez");
        user8.setPassword("user08");
        user8.setRole(rolesService.getRoles()[0]);
        User user9 = new User("user09@email.com", "Antonio", "Tizón");
        user9.setPassword("user09");
        user9.setRole(rolesService.getRoles()[0]);
        User user10 = new User("user10@email.com", "Adriana", "López");
        user10.setPassword("user10");
        user10.setRole(rolesService.getRoles()[0]);
        User user11 = new User("user11@email.com", "Angustias", "Sanz");
        user11.setPassword("user11");
        user11.setRole(rolesService.getRoles()[0]);
        User user12 = new User("user12@email.com", "Rodrígo", "Díaz");
        user12.setPassword("user12");
        user12.setRole(rolesService.getRoles()[0]);
        User user13 = new User("user13@email.com", "Amador", "Casal");
        user13.setPassword("user13");
        user13.setRole(rolesService.getRoles()[0]);
        User user14 = new User("user14@email.com", "Pedro", "García");
        user14.setPassword("user14");
        user14.setRole(rolesService.getRoles()[0]);
        User user15 = new User("user15@email.com", "José", "Martínez");
        user15.setPassword("user15");
        user15.setRole(rolesService.getRoles()[0]);


        User admin = new User("admin@email.com", "Admin", "Admin");
        admin.setPassword("admin");
        admin.setRole(rolesService.getRoles()[1]);

        userService.addUser(user1);
        userService.addUser(user2);
        userService.addUser(user3);
        userService.addUser(user4);
        userService.addUser(user5);
        userService.addUser(user6);
        userService.addUser(user7);
        userService.addUser(user8);
        userService.addUser(user9);
        userService.addUser(user10);
        userService.addUser(user11);
        userService.addUser(user12);
        userService.addUser(user13);
        userService.addUser(user14);
        userService.addUser(user15);
        userService.addUser(admin);
    }
}
