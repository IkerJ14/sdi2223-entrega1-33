package com.uniovi.sdi2223entrega133.entities;


import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
public class ConversationMessage {

    @Id
    @GeneratedValue
    private Long id;

    private String text;
    private LocalDateTime date;
    private String nameSender;

    @ManyToOne
    @JoinColumn(name = "conversation_id")
    private Conversation conversation;

    public ConversationMessage() {  }

    public ConversationMessage(String nameSender, LocalDateTime date, String text) {
        this.nameSender = nameSender;
        this.date = date;
        this.text = text;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public String getNameSender() {
        return nameSender;
    }

    public void setNameSender(String nameSender) {
        this.nameSender = nameSender;
    }

    public Conversation getConversation() {
        return conversation;
    }

    public void setConversation(Conversation conversation) {
        this.conversation = conversation;
    }
}
