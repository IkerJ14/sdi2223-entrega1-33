package com.uniovi.sdi2223entrega133.repositories;

import com.uniovi.sdi2223entrega133.entities.Conversation;
import com.uniovi.sdi2223entrega133.entities.Offer;
import com.uniovi.sdi2223entrega133.entities.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface ConversationRepository extends CrudRepository<Conversation, Long> {

    @Query("SELECT c FROM Conversation c WHERE c.offer = ?1 AND c.buyer = ?2")
    Conversation findByOfferAndBuyer(Offer offer, User buyer);


}
