package com.uniovi.sdi2223entrega133.controllers;

import com.uniovi.sdi2223entrega133.entities.*;
import com.uniovi.sdi2223entrega133.services.*;
import com.uniovi.sdi2223entrega133.validators.MessageValidator;
import org.slf4j.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
import java.time.format.DateTimeFormatter;
import java.util.LinkedList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
@Controller
public class ConversationController {
    final static Logger logger = LoggerFactory.getLogger(ConversationController.class);
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

    @Autowired
    private LogsService logService;
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
        ConversationMessage message = new ConversationMessage();

        model.addAttribute("conversation", conversation);
        model.addAttribute("message", message);

        logger.info("Se realizo peticion get /conversation/" + idOffer + "/" + emailBuyer);
        Log log2 = new Log("PET", new Date(), "ConversationController: GET: conversation/"+ idOffer + "/" + emailBuyer);
        logService.addLog(log2);
        return "/conversation/offer_conversation";
    }

    @RequestMapping("/conversation/message")
    public String getMessage(@RequestParam Long idOffer, @RequestParam Long idBuyer, Model model, Principal principal,
                             @ModelAttribute @Validated ConversationMessage message, BindingResult result) {
        Offer offer = offersService.getOffer(idOffer).get();
        User buyer = usersService.getUser(idBuyer);
        Conversation conversation = conversationService.getConversationByOfferAndBuyer(offer, buyer);

        messageValidator.validate(message, result);
        for (Object object : result.getAllErrors()) {
            if(object instanceof FieldError) {
                FieldError fieldError = (FieldError) object;

                System.out.println(fieldError.getField());
            }

            if(object instanceof ObjectError) {
                ObjectError objectError = (ObjectError) object;

                System.out.println(objectError.getCode());
            }
        }
        if (result.hasErrors()) {
            conversationService.addConversation(conversation);
            model.addAttribute("conversation", conversation);
            model.addAttribute("message", message);
            return "/conversation/offer_conversation";
        }

        String email = principal.getName();
        User currentUser = usersService.getUserByEmail(email);

        message.setNameSender(currentUser.getName());

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime now = LocalDateTime.now();
        String formatDateTime = now.format(formatter);

        message.setDate(formatDateTime);

        conversationService.addMessageToConversation(conversation, message);
        conversationService.addConversation(conversation);

        model.addAttribute("conversation", conversation);
        model.addAttribute("message", message);

        logger.info("Se realizo peticion get /conversation/message");
        Log log2 = new Log("PET", new Date(), "ConversationController: GET: conversation/message");
        logService.addLog(log2);
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

        logger.info("Se realizo peticion get /conversation/list");
        Log log2 = new Log("PET", new Date(), "ConversationController: GET: conversation/list");
        logService.addLog(log2);
        return "/conversation/list";
    }
}
