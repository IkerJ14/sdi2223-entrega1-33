package com.uniovi.sdi2223entrega133.repositories;

import com.uniovi.sdi2223entrega133.entities.Offer;
import com.uniovi.sdi2223entrega133.entities.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface OffersRepository extends CrudRepository<Offer, Long> {
    @Query("SELECT r FROM Offer r WHERE r.user = ?1 ORDER BY r.id ASC")
    List<Offer> findAllByUser(User user);
}
