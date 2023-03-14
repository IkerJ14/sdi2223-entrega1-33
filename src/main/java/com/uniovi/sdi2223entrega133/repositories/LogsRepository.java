package com.uniovi.sdi2223entrega133.repositories;

import com.uniovi.sdi2223entrega133.entities.*;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.*;

import java.util.*;

public interface LogsRepository extends CrudRepository<Log, Long> {

    @Query("SELECT l FROM Log l ")
    List<Log> findAll();
}
