package com.uniovi.sdi2223entrega133.services;

import com.uniovi.sdi2223entrega133.entities.*;
import com.uniovi.sdi2223entrega133.repositories.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;

import java.util.*;

@Service
public class LogsService {
    @Autowired
    LogsRepository repo;

    public void addLog(Log log){
        repo.save(log);
    }
    public List<Log> getLogs(){
        return new LinkedList<>(repo.findAll());
    }

    public void deleteAll(){
        repo.deleteAll();
    }
}
