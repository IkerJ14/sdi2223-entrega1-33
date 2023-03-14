package com.uniovi.sdi2223entrega133.repositories;

import com.uniovi.sdi2223entrega133.entities.Offer;
import com.uniovi.sdi2223entrega133.entities.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface UserRepository extends CrudRepository<User, Long> {
    User findByEmail(String email) ;

    @Query("select o from Offer o where o.buyer = ?1")
    List<Offer> getBoughtOffers(User buyer);
}