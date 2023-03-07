package com.uniovi.sdi2223entrega133.controllers;

import com.uniovi.sdi2223entrega133.entities.Conversation;
import com.uniovi.sdi2223entrega133.entities.ConversationMessage;
import com.uniovi.sdi2223entrega133.entities.Offer;
import com.uniovi.sdi2223entrega133.entities.User;
import com.uniovi.sdi2223entrega133.services.ConversationMessageService;
import com.uniovi.sdi2223entrega133.services.ConversationService;
import com.uniovi.sdi2223entrega133.services.OffersService;
import com.uniovi.sdi2223entrega133.services.UserService;
import com.uniovi.sdi2223entrega133.validators.MessageValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.LinkedList;

@Controller
public class ConversationController {

    @Autowired
    private OffersService offersService;

    @Autowired
    private UserService usersService;

    @Autowired
    private ConversationService conversationService;

    @Autowired
    private ConversationMessageService conversationMessageService;

    @Autowired
    private MessageValidator messageValidator;

    @RequestMapping("/conversation/{idOffer}/{emailBuyer}")
    public String getConversation(@PathVariable Long idOffer, @PathVariable String emailBuyer, Model model) {
        Offer offer = offersService.getOffer(idOffer).get();

        User buyer = usersService.getUserByEmail(emailBuyer);

        Conversation conversation;

        if (conversationService.getConversationByOfferAndBuyer(offer, buyer) == null) {
            conversation = new Conversation(offer, buyer);
        } else {
            conversation = conversationService.getConversationByOfferAndBuyer(offer, buyer);
        }

        conversationService.addConversation(conversation);

        model.addAttribute("conversation", conversation);
        model.addAttribute("message", new ConversationMessage());

        return "/conversation/offer_conversation";
    }

    @RequestMapping("/conversation/message")
    public String getMessage(@RequestParam String textMessage, @RequestParam Long idOffer, @RequestParam Long idBuyer,
                             @Validated ConversationMessage message, BindingResult result, Model model) {

        Offer offer = offersService.getOffer(idOffer).get();
        User buyer = usersService.getUser(idBuyer);
        Conversation conversation = conversationService.getConversationByOfferAndBuyer(offer, buyer);
        message.setDate(LocalDateTime.now());
        message.setNameSender(buyer.getName());
        message.setText(textMessage);

        messageValidator.validate(message, result);

        if (!result.hasErrors()) {
            conversationMessageService.addMessage(message);
        }

        conversationService.addMessageToConversation(conversation, message);
        conversationService.addConversation(conversation);

        model.addAttribute("conversation", conversation);
        model.addAttribute("message", message);

        return "/conversation/offer_conversation";
    }

    @RequestMapping("/conversation/list")
    public String getList(Pageable pageable, Model model, Principal principal) {
        String email = principal.getName();
        User user = usersService.getUserByEmail(email);
        Page<Conversation> conversations = new PageImpl<Conversation>(new LinkedList<Conversation>());

        conversations = conversationService.getConversationsByUser(pageable, user);

        model.addAttribute("listConversations", conversations.getContent());
        model.addAttribute("page", conversations);

        return "/conversation/list";
    }
}
