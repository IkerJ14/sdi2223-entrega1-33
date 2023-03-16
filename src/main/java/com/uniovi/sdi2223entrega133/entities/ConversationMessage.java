package com.uniovi.sdi2223entrega133.entities;


import javax.persistence.*;

@Entity
public class ConversationMessage {

    @Id
    @GeneratedValue
    private Long id;

    private String textMessage;
    private String date;
    private String nameSender;

    @ManyToOne
    @JoinColumn(name = "conversation_id")
    private Conversation conversation;

    public ConversationMessage() {  }

    public ConversationMessage(String textMessage) {
        this.textMessage = textMessage;
    }

    public ConversationMessage(String nameSender, String date, String textMessage) {
        this.nameSender = nameSender;
        this.date = date;
        this.textMessage = textMessage;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getTextMessage() {
        return textMessage;
    }

    public void setTextMessage(String text) {
        this.textMessage = text;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
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
