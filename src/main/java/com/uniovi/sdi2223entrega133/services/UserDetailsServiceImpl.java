package com.uniovi.sdi2223entrega133.services;

import com.uniovi.sdi2223entrega133.entities.User;
import com.uniovi.sdi2223entrega133.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service("userDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String mail) throws UsernameNotFoundException {
       User user = userRepository.findByEmail(mail);
        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
        grantedAuthorities.add(new SimpleGrantedAuthority(user.getRole()));
        if (user == null) {
            throw new UsernameNotFoundException(mail);
        }
        return new org.springframework.security.core.userdetails.User(
                user.getEmail(), user.getPassword(), grantedAuthorities);
    }
}