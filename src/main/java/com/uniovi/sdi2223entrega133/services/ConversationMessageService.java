package com.uniovi.sdi2223entrega133.services;

import com.uniovi.sdi2223entrega133.entities.ConversationMessage;
import com.uniovi.sdi2223entrega133.repositories.ConversationMessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ConversationMessageService {

    @Autowired
    private ConversationMessageRepository conversationMessageRepository;

    public void addMessage(ConversationMessage message) {
        conversationMessageRepository.save(message);
    }

}
