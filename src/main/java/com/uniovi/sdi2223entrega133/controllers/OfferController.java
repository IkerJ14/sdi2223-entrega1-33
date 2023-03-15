package com.uniovi.sdi2223entrega133.controllers;

import com.uniovi.sdi2223entrega133.entities.*;
import com.uniovi.sdi2223entrega133.services.*;
import com.uniovi.sdi2223entrega133.validators.OfferValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;
import java.time.LocalDate;
import java.util.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Controller
public class OfferController {
    final static Logger logger = LoggerFactory.getLogger(OfferController.class);
    @Autowired
    private OffersService offersService;

    @Autowired
    private UserService usersService;

    @Autowired
    private OfferValidator offerValidator;

    @Autowired
    private LogsService logService;

    @RequestMapping("/offer/list")
    public String getList(Pageable pageable, Model model, Principal principal,
                          @RequestParam(value = "", required = false) String searchText){
        String email = principal.getName();
        User user = usersService.getUserByEmail(email);
        Page<Offer> offers = new PageImpl<Offer>(new LinkedList<Offer>());
        if (searchText != null && !searchText.isEmpty()) {
            offers = offersService.searchOffersByTitle(pageable, searchText);
        } else {
            offers = offersService.getOffers(pageable);
        }

        model.addAttribute("offerList", offers.getContent());
        model.addAttribute("page", offers);

        Log log2 = new Log("PET", new Date(), "OfferController: GET: offer/list");
        logService.addLog(log2);
        logger.info("Se realizo peticion get /offer/list");
        return "offer/list";
    }

    @RequestMapping("/offer/user_list")
    public String getUserList(Model model, Principal principal){
        String email = principal.getName();
        User user = usersService.getUserByEmail(email);

        model.addAttribute("userOfferList", offersService.getOffersByUser(user));

        Log log2 = new Log("PET", new Date(), "OfferController: GET: offer/user_list");
        logService.addLog(log2);
        logger.info("Se realizo peticion get /offer/user_list");
        return "offer/user_list";
    }

    @RequestMapping(value="/offer/add")
    public String getOffer(Model model){
        Offer offer = new Offer();
        offer.setPrice(0.0);
        model.addAttribute("offer", offer);
        Log log2 = new Log("PET", new Date(), "OfferController: GET: offer/add");
        logService.addLog(log2);
        logger.info("Se realizo peticion get /offer/add");
        return "offer/add";
    }

    @RequestMapping(value = "/offer/add", method = RequestMethod.POST)
    public String setOffer(Principal principal, @ModelAttribute @Validated Offer offer, BindingResult result) {
        offerValidator.validate(offer, result);
        if (result.hasErrors()) {
            logger.info("Se realizo peticion post /offer/add pero resulto erronea");
            return "offer/add";
        }

        String email = principal.getName();
        User user = usersService.getUserByEmail(email);
        offer.setUser(user);
        offer.setDate(LocalDate.now());
        offer.setSold(false);

        offersService.addOffer(offer);
        Log log2 = new Log("PET", new Date(), "OfferController: POST: offer/add");
        logService.addLog(log2);
        logger.info("Se realizo peticion post /offer/add");
        return "redirect:/offer/user_list";
    }

    @RequestMapping("/offer/delete/{id}")
    public String deleteMark(@PathVariable Long id) {
        offersService.deleteOffer(id);
        Log log2 = new Log("PET", new Date(), "OfferController: GET: offer/delete/" + id);
        logService.addLog(log2);
        logger.info("Se realizo peticion get /offer/delete/" + id);
        return "redirect:/offer/user_list";
    }

    @RequestMapping("/offer/user_list/update")
    public String updateUserList(Model model, Principal principal) {
        String email = principal.getName();
        User user = usersService.getUserByEmail(email);

        model.addAttribute("userOfferList", offersService.getOffersByUser(user));
        logger.info("Se realizo peticion get /offer/user_list/update");
        Log log2 = new Log("PET", new Date(), "OfferController: GET: offer/user_list/update");
        logService.addLog(log2);
        return "offer/user_list :: tableUserOffers";
    }

    @RequestMapping("/offer/list/update")
    public String updateList(Pageable pageable, Model model, Principal principal) {
        String email = principal.getName();
        User user = usersService.getUserByEmail(email);
        Page<Offer> offers = new PageImpl<Offer>(new LinkedList<Offer>());
        offers = offersService.getOffers(pageable);

        model.addAttribute("offerList", offers.getContent());
        model.addAttribute("page", offers);

        logger.info("Se realizo peticion get /offer/list/update");
        Log log2 = new Log("PET", new Date(), "OfferController: GET: offer/list/update");
        logService.addLog(log2);
        return "offer/list :: tableOffers";
    }

    @RequestMapping("/offer/{id}/nosold")
    public String buyOffer(@PathVariable Long id, Principal principal, RedirectAttributes redAtt) {
        String userEmail = principal.getName();
        User user = usersService.getUserByEmail(userEmail);
        Offer offer = offersService.getOffer(id).get();

        int error = offersService.buyOffer(offer, user);

        switch (error) {
            case 0:
                redAtt.addFlashAttribute("error", false);
                return "redirect:/offer/purchaseList";
            case 1:
                redAtt.addFlashAttribute("error_owner", true);
                break;
            case 2:
                redAtt.addFlashAttribute("error_price", true);
                break;
            case 3:
                redAtt.addFlashAttribute("error_sold", true);
                break;
        }
        logger.info("Se realizo peticion get /offer/"+ id + "/nosold");
        Log log2 = new Log("PET", new Date(), "OfferController: GET: /offer/"+ id + "/nosold");
        logService.addLog(log2);

        return "redirect:/offer/list";
    }

    @RequestMapping("/offer/purchaseList")
    public String getPurchaseList(Model model, Principal principal) {
        String userEmail = principal.getName();
        User user = usersService.getUserByEmail(userEmail);

        model.addAttribute("purchases", user.getBoughtOffers());
        logger.info("Se realizo peticion get /offer/purchaseList");
        Log log2 = new Log("PET", new Date(), "OfferController: GET: offer/purchaseList");
        logService.addLog(log2);
        model.addAttribute("purchases", usersService.getBoughtOffersFromUser(user));
        return "offer/purchaseList";
    }

}
