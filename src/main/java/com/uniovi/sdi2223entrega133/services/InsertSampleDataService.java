package com.uniovi.sdi2223entrega133.services;

import com.uniovi.sdi2223entrega133.entities.Offer;
import com.uniovi.sdi2223entrega133.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

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

        Set user1Offers = new HashSet<Offer>() {
            {
                add(new Offer("Mesita", "Mesita de noche", LocalDate.of(2022, 5, 20), 100, user1));
                add(new Offer("Sillón", "Sillón reclinable", LocalDate.of(2020, 4, 15), 500, user1));
                add(new Offer("Zapatillas", "Zapatillas gruesas", LocalDate.of(2019, 3, 5), 20, user1));
                add(new Offer("Libro", "La Conjura de los Necios", LocalDate.of(2015, 10, 18), 15, user1));
                add(new Offer("Auriculares", "Auriculares para PC", LocalDate.of(2023, 1, 10), 40, user1));
                add(new Offer("Camiseta", "Camiseta roja", LocalDate.of(2018, 2, 17), 12, user1));
                add(new Offer("Bicicleta", "Bicicleta de montaña", LocalDate.of(2015, 6, 12), 250, user1));
                add(new Offer("Libro", "1984", LocalDate.of(2022, 4, 11), 18, user1));
                add(new Offer("Película", "La Chaqueta Metálica", LocalDate.of(2011, 9, 17), 25, user1));
                add(new Offer("Disco duro", "Disco duro de 1 TB", LocalDate.of(2017, 5, 20), 75, user1));
            }
        };

        user1.setOffers(user1Offers);

        Set user2Offers = new HashSet<Offer>() {
            {
                add(new Offer("Mesa", "Mesa de comedor", LocalDate.of(2021, 5, 14), 120, user2));
                add(new Offer("Rotuladores", "Juego de rotuladores de colores", LocalDate.of(2019, 8, 10), 15, user2));
                add(new Offer("Playeros", "Playeros de marca Nike", LocalDate.of(2020, 12, 18), 25, user2));
                add(new Offer("Traje", "Traje formal para eventos", LocalDate.of(2021, 11, 9), 75, user2));
                add(new Offer("Pisapapeles", "Pisapapeles para oficina", LocalDate.of(2010, 6, 17), 50, user2));
                add(new Offer("Bañador", "Bañador para playa", LocalDate.of(2017, 9, 15), 12, user2));
                add(new Offer("Portatil", "Ordenador portatil", LocalDate.of(2015, 6, 12), 780, user2));
                add(new Offer("Libro", "La Historia Interminable", LocalDate.of(2020, 3, 17), 23, user2));
                add(new Offer("Sudadera", "Sudadera para correr", LocalDate.of(2021, 7, 27), 28, user2));
                add(new Offer("Disco de vinilo", "Pink Floyd - The Dark Side of The Moon", LocalDate.of(2012, 9, 22), 60, user2));
            }
        };

        user2.setOffers(user2Offers);

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
