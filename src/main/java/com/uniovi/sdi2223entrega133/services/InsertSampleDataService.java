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
        user3.setPassword("user03");
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
                add(new Offer("Película", "DVD de La Chaqueta Metálica", LocalDate.of(2011, 9, 17), 25, user1));
                add(new Offer("Disco duro externo", "Disco duro de 1 TB", LocalDate.of(2017, 5, 20), 75, user1));
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

        Set user3Offers = new HashSet<Offer>() {
            {
                add(new Offer("Playeros", "Playeros de montaña", LocalDate.of(2019, 5, 14), 40, user3));
                add(new Offer("Lápices", "Juego de lápices HB", LocalDate.of(2016, 3, 10), 15, user3));
                add(new Offer("Camiseta", "Camiseta roja", LocalDate.of(2020, 9, 17), 18, user3));
                add(new Offer("Pantalón", "Pantalón vaquero", LocalDate.of(2020, 7, 19), 25, user3));
                add(new Offer("Bolsa de tela", "Bolsa de tela para compra", LocalDate.of(2022, 4, 10), 12, user3));
                add(new Offer("Gafas de sol", "Lentes solares polarizadas", LocalDate.of(2019, 4, 8), 20, user3));
                add(new Offer("Televisión", "Televisión 4K", LocalDate.of(2017, 9, 2), 500, user3));
                add(new Offer("Silla", "Silla de oficina", LocalDate.of(2017, 4, 12), 90, user3));
                add(new Offer("Sofá", "Sofá de cuero", LocalDate.of(2020, 6, 10), 250, user3));
                add(new Offer("Teclado", "Teclado Bluetooth para ordenador", LocalDate.of(2021, 4, 13), 30, user3));
            }
        };

        user3.setOffers(user3Offers);

        Set user4Offers = new HashSet<Offer>() {
            {
                add(new Offer("Libro", "Dune", LocalDate.of(2017, 3, 6), 35, user4));
                add(new Offer("Guitarra", "Guitarra española", LocalDate.of(2012, 10, 7), 150, user4));
                add(new Offer("Lata de pintura", "Pintura blanca para paredes", LocalDate.of(2020, 9, 17), 18, user4));
                add(new Offer("Brocha", "Brocha de pintor", LocalDate.of(2020, 7, 19), 25, user4));
                add(new Offer("Disco duro externo", "Disco duro externo de 2TB", LocalDate.of(2018, 7, 9), 90, user4));
                add(new Offer("Cortacesped", "Cortacesped eléctrico", LocalDate.of(2015, 2, 10), 300, user4));
                add(new Offer("Armario", "Armario ropero", LocalDate.of(2014, 8, 12), 190, user4));
                add(new Offer("Tumbona", "Tumbona para jardín", LocalDate.of(2014, 12, 1), 100, user4));
                add(new Offer("Teléfono móvil", "Móvil con 8GB de RAM", LocalDate.of(2022, 4, 16), 230, user4));
                add(new Offer("Secadora", "Secadora blanca de 8 kg", LocalDate.of(2017, 3, 9), 650, user4));
            }
        };

        user4.setOffers(user4Offers);

        Set user5Offers = new HashSet<Offer>() {
            {
                add(new Offer("Pesas", "Juego de mancuernas de hasta 30 kg", LocalDate.of(2016, 7, 18), 40, user5));
                add(new Offer("Videojuego", "StarCraft Remastered", LocalDate.of(2010, 9, 8), 60, user5));
                add(new Offer("Lata de pintura", "Pintura blanca para paredes", LocalDate.of(2020, 9, 17), 18, user5));
                add(new Offer("Brocha", "Brocha de pintor", LocalDate.of(2020, 7, 19), 25, user5));
                add(new Offer("Disco duro externo", "Disco duro externo de 2TB", LocalDate.of(2018, 7, 9), 90, user5));
                add(new Offer("Semillas", "Semillas de tomate para jardín", LocalDate.of(2023, 2, 15), 15, user5));
                add(new Offer("Cortina", "Cortina para ducha", LocalDate.of(2019, 11, 19), 20, user5));
                add(new Offer("Teléfono móvil", "Móvil con 8GB de RAM", LocalDate.of(2022, 4, 16), 230, user5));
                add(new Offer("Mando", "Mando universal de televisión", LocalDate.of(2018, 7, 13), 28, user5));
                add(new Offer("Disco de música (CD)", "The Beatles - Revolver", LocalDate.of(2017, 5, 17), 28, user5));
            }
        };

        user5.setOffers(user5Offers);

        Set user6Offers = new HashSet<Offer>() {
            {
                add(new Offer("Tebeos", "Lote de 40 números de Mortadelo y Filemón", LocalDate.of(2018, 8, 14), 60, user6));
                add(new Offer("Película", "BluRay de Lawrence de Arabia", LocalDate.of(2013, 4, 17), 45, user6));
                add(new Offer("Poster", "La Gioconda, 40x60", LocalDate.of(2018, 3, 12), 21, user6));
                add(new Offer("Pala", "Pala de metal tipo Y", LocalDate.of(2017, 10, 17), 18, user6));
                add(new Offer("USB", "USB 3.0 de 256 MB", LocalDate.of(2015, 11, 19), 23, user6));
                add(new Offer("Gomas", "Pack de 24 gomas de borrar", LocalDate.of(2022, 12, 5), 11, user6));
                add(new Offer("Persiana", "Persiana para ventana", LocalDate.of(2017, 9, 11), 27, user6));
                add(new Offer("Cámara", "Cámara de fotos de 18-55 mm", LocalDate.of(2020, 7, 14), 450, user6));
                add(new Offer("Sellos", "Pack de 200 sellos postales franceses, 1950-1990", LocalDate.of(2020, 12, 11), 18, user6));
                add(new Offer("Album de fotos", "Album para fotografías de 200 páginas", LocalDate.of(2021, 9, 20), 20, user6));
            }
        };

        user6.setOffers(user6Offers);

        Set user7Offers = new HashSet<Offer>() {
            {
                add(new Offer("Taburete", "Taburete de madera", LocalDate.of(2014, 9, 13), 40, user7));
                add(new Offer("Sartenes", "Juego de sartenes antiadherentes", LocalDate.of(2018, 5, 12), 63, user7));
                add(new Offer("Toallas", "3 toallas de ducha grandes", LocalDate.of(2019, 12, 9), 25, user7));
                add(new Offer("Caja de herramientas", "Caja con herramientas de bricolaje", LocalDate.of(2021, 8, 13), 57, user7));
                add(new Offer("Caña de pescar", "Caña para pescar de 2 metros", LocalDate.of(2020, 9, 2), 30, user7));
                add(new Offer("Calculadora", "Calculadora científica", LocalDate.of(2018, 8, 3), 19, user7));
                add(new Offer("Comic", "Tomo recopilatorio de Superman de 400 páginas", LocalDate.of(2015, 3, 16), 50, user7));
                add(new Offer("Raqueta", "Raqueta de bádminton", LocalDate.of(2018, 3, 27), 35, user7));
                add(new Offer("Televisión", "Televisión de 24 pulgadas", LocalDate.of(2017, 5, 23), 200, user7));
                add(new Offer("Bate", "Bate de beisbol de madera", LocalDate.of(2022, 4, 16), 25, user7));
            }
        };

        user7.setOffers(user7Offers);

        Set user8Offers = new HashSet<Offer>() {
            {
                add(new Offer("Sandwichera", "Sandwichera de 700W", LocalDate.of(2019, 1, 22), 40, user8));
                add(new Offer("Cuchillos", "Juego de cuchillos para cortar jamón", LocalDate.of(2020, 6, 18), 95, user8));
                add(new Offer("Trapos", "Pack de 4 trapos de limpieza del hogar", LocalDate.of(2023, 1, 25), 15, user8));
                add(new Offer("Botas", "Botas de montaña", LocalDate.of(2019, 7, 12), 80, user8));
                add(new Offer("Albornoz", "Albornoz azul de algodón", LocalDate.of(2020, 4, 12), 20, user8));
                add(new Offer("Camisa", "Camisa de cuadros", LocalDate.of(2022, 6, 13), 18, user8));
                add(new Offer("Libro", "La Familia de Pascual Duarte", LocalDate.of(2018, 7, 26), 25, user8));
                add(new Offer("Periódico", "Periódico viejo en buen estado, 20-07-1969", LocalDate.of(2021, 6, 17), 40, user8));
                add(new Offer("Sobres", "Pack de 20 sobres A4 para correo", LocalDate.of(2022, 4, 15), 10, user8));
                add(new Offer("Vestido", "Vestido de fiesta verde", LocalDate.of(2019, 3, 10),  30, user8));
            }
        };

        user8.setOffers(user8Offers);

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
