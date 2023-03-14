package com.uniovi.sdi2223entrega133.services;

import com.uniovi.sdi2223entrega133.entities.*;
import com.uniovi.sdi2223entrega133.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    public void init() {
    }
    public List<User> getUsers() {
        List<User> users = new ArrayList<User>();
        userRepository.findAll().forEach(users::add);
        return users;
    }
    public  User getUserByEmail(String email){
        return userRepository.findByEmail(email);
    }
    public User getUser(Long id) {
        return userRepository.findById(id).get();
    }
    public void addUser(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    public List<Offer> getBoughtOffersFromUser(User user) {
        return userRepository.getBoughtOffers(user);
    }
}
