package com.uniovi.sdi2223entrega133.controllers;

import com.uniovi.sdi2223entrega133.entities.Offer;
import com.uniovi.sdi2223entrega133.entities.User;
import com.uniovi.sdi2223entrega133.services.OffersService;
import com.uniovi.sdi2223entrega133.services.UserService;
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

import java.security.Principal;
import java.time.LocalDate;
import java.util.LinkedList;

@Controller
public class OfferController {
    @Autowired
    private OffersService offersService;

    @Autowired
    private UserService usersService;

    @Autowired
    private OfferValidator offerValidator;

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

        return "offer/list";
    }

    @RequestMapping("/offer/user_list")
    public String getUserList(Model model, Principal principal){
        String email = principal.getName();
        User user = usersService.getUserByEmail(email);

        model.addAttribute("userOfferList", offersService.getOffersByUser(user));

        return "offer/user_list";
    }

    @RequestMapping(value="/offer/add")
    public String getOffer(Model model){
        Offer offer = new Offer();
        offer.setPrice(0.0);
        model.addAttribute("offer", offer);
        return "offer/add";
    }

    @RequestMapping(value = "/offer/add", method = RequestMethod.POST)
    public String setOffer(Principal principal, @ModelAttribute @Validated Offer offer, BindingResult result) {
        offerValidator.validate(offer, result);
        if (result.hasErrors()) {
            return "/offer/add";
        }

        String email = principal.getName();
        User user = usersService.getUserByEmail(email);
        offer.setUser(user);
        offer.setDate(LocalDate.now());
        offer.setSold(false);

        offersService.addOffer(offer);
        return "redirect:/offer/user_list";
    }

    @RequestMapping("/offer/delete/{id}")
    public String deleteMark(@PathVariable Long id) {
        offersService.deleteOffer(id);
        return "redirect:/offer/user_list";
    }

    @RequestMapping("/offer/user_list/update")
    public String updateUserList(Model model, Principal principal) {
        String email = principal.getName();
        User user = usersService.getUserByEmail(email);

        model.addAttribute("userOfferList", offersService.getOffersByUser(user));
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

        return "offer/list :: tableOffers";
    }

    @RequestMapping("/offer/{id}/nosold")
    public String buyOffer(@PathVariable Long id, Principal principal) {
        String userEmail = principal.getName();
        User user = usersService.getUserByEmail(userEmail);
        Offer offer = offersService.getOffer(id).get();
        offersService.buyOffer(offer, user);
        return "redirect:/offer/purchaseList";
    }

    @RequestMapping("/offer/purchaseList")
    public String getPurchaseList(Model model, Principal principal) {
        String userEmail = principal.getName();
        User user = usersService.getUserByEmail(userEmail);

        model.addAttribute("purchases", user.getBoughtOffers());
        return "offer/purchaseList";
    }

}
