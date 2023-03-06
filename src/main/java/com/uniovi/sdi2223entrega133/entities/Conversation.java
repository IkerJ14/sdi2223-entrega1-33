package com.uniovi.sdi2223entrega133.entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
public class Conversation {
    @Id
    @GeneratedValue
    private Long id;

    @OneToMany(mappedBy = "conversation", cascade = CascadeType.ALL)
    private List<ConversationMessage> messages = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "offer_id")
    private Offer offer;

    @ManyToOne
    @JoinColumn(name = "buyer_id")
    private User buyer;

    public Conversation() {  }

    public Conversation(Offer offer, User buyer) {
        this.offer = offer;
        this.buyer = buyer;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public List<ConversationMessage> getMessages() {
        return messages;
    }

    public void addMessage(ConversationMessage message) {
        message.setConversation(this);
        this.messages.add(message);
    }

    public Offer getOffer() {
        return offer;
    }

    public void setOffer(Offer offer) {
        this.offer = offer;
    }

    public User getBuyer() {
        return buyer;
    }

    public void setBuyer(User buyer) {
        this.buyer = buyer;
    }

}
