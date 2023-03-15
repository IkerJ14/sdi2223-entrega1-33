package com.uniovi.sdi2223entrega133.repositories;

import com.uniovi.sdi2223entrega133.entities.Offer;
import com.uniovi.sdi2223entrega133.entities.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface OffersRepository extends CrudRepository<Offer, Long> {
    @Query("SELECT r FROM Offer r WHERE r.user = ?1 ORDER BY r.title ASC")
    List<Offer> findAllByUser(User user);

    @Query("SELECT r FROM Offer r WHERE (LOWER(r.title) LIKE LOWER(?1)) ORDER BY r.title ASC")
    Page<Offer> searchByTitle(Pageable pageable, String searchText);

    @Query("SELECT r FROM Offer r ORDER BY r.title ASC")
    Page<Offer> findAll(Pageable pageable);
}
