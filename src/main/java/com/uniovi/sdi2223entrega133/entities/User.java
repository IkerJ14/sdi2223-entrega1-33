package com.uniovi.sdi2223entrega133.entities;

import javax.persistence.*;

@Entity
public class User {

    public long getId() {
        return id;
    }
    public User() {
    }
    public void setId(long id) {
        this.id = id;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getPasswordConfirm() {
        return passwordConfirm;
    }

    public void setPasswordConfirm(String passwordConfirm) {
        this.passwordConfirm = passwordConfirm;
    }

    @Id
    @GeneratedValue
    private long id;
    @Column(unique = true)
    private String mail;
    private String name;
    private String lastName;
    private String password;

    private String role;

    @Transient //propiedad que no se almacena en la tabla.
    private String passwordConfirm;


    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }
}
