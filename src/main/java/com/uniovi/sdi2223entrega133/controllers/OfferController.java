package com.uniovi.sdi2223entrega133.controllers;

import com.uniovi.sdi2223entrega133.entities.Offer;
import com.uniovi.sdi2223entrega133.entities.User;
import com.uniovi.sdi2223entrega133.services.OffersService;
import com.uniovi.sdi2223entrega133.services.UserService;
import com.uniovi.sdi2223entrega133.validators.OfferValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.security.Principal;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;

@Controller
public class OfferController {
    @Autowired
    private OffersService offersService;

    @Autowired
    private UserService userService;

    @Autowired
    private OfferValidator offerValidator;

    @RequestMapping("/offer/user_list")
    public String getList(Model model, Principal principal){
        String email = principal.getName();
        User user = userService.getUserByEmail(email);

        model.addAttribute("userOfferList", offersService.getOffersByUser(user));

        return "offer/user_list";
    }

    @RequestMapping(value="/offer/add")
    public String getOffer(Model model){
        Offer offer = new Offer();
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
        User user = userService.getUserByEmail(email);
        offer.setUser(user);
        // TODO: Check if form does not need a date field
//        Calendar c = Calendar.getInstance();
//        c.setTime(new Date());
        offer.setDate(LocalDate.now());

        offersService.addOffer(offer);
        return "redirect:/offer/user_list";
    }

    @RequestMapping("/offer/user_list/update")
    public String updateList(Model model, Principal principal) {
        String email = principal.getName();
        User user = userService.getUserByEmail(email);

        model.addAttribute("userOfferList", offersService.getOffersByUser(user));
        return "offer/user_list :: tableUserOffers";
    }
}
