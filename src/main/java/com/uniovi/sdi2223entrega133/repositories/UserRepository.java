package com.uniovi.sdi2223entrega133.repositories;

import com.uniovi.sdi2223entrega133.entities.User;
import org.springframework.data.repository.CrudRepository;


public interface UserRepository extends CrudRepository<User, Long> {
    User findByEmail(String email);
}