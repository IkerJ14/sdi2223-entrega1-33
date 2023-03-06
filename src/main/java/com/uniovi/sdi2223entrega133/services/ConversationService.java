package com.uniovi.sdi2223entrega133.services;

import com.uniovi.sdi2223entrega133.entities.Conversation;
import com.uniovi.sdi2223entrega133.entities.ConversationMessage;
import com.uniovi.sdi2223entrega133.entities.Offer;
import com.uniovi.sdi2223entrega133.entities.User;
import com.uniovi.sdi2223entrega133.repositories.ConversationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ConversationService {
    @Autowired
    ConversationRepository conversationRepository;

    public void addConversation(Conversation conversation) {
        conversationRepository.save(conversation);
    }

    public Conversation getConversationByOfferAndBuyer(Offer offer, User buyer) {
        return conversationRepository.findByOfferAndBuyer(offer, buyer);
    }

    public void addMessageToConversation(Conversation conversation, ConversationMessage message) {
        conversation.addMessage(message);
    }
}
