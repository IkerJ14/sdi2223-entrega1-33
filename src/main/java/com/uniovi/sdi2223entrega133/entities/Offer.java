package com.uniovi.sdi2223entrega133.entities;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;

@Entity
public class Offer {

    @Id
    @GeneratedValue
    private Long id;

    private String title;

    private String description;
    private LocalDate date;
    private double price;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Offer() {

    }

    public Offer(Long id, String description, LocalDate date, double price) {
        this.id = id;
        this.description = description;
        this.date = date;
        this.price = price;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
