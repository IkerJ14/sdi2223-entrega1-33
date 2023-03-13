package com.uniovi.sdi2223entrega133.services;

import com.uniovi.sdi2223entrega133.entities.Offer;
import com.uniovi.sdi2223entrega133.entities.User;
import com.uniovi.sdi2223entrega133.repositories.OffersRepository;
import com.uniovi.sdi2223entrega133.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Service
public class OffersService {
    @Autowired
    private OffersRepository offersRepository;
    @Autowired
    private UserRepository usersRepository;

    public Page<Offer> getOffers(Pageable pageable) {
        Page<Offer> offers = new PageImpl<Offer>(new LinkedList<Offer>());
        offers = offersRepository.findAll(pageable);
        return offers;
    }

    public Page<Offer> searchOffersByTitle(Pageable pageable, String searchText) {
        Page<Offer> marks = new PageImpl<Offer>(new LinkedList<Offer>());
        searchText = "%"+searchText+"%";
        marks = offersRepository.searchByTitle(pageable, searchText);
        return marks;
    }

    public void addOffer(Offer offer) {
        offersRepository.save(offer);
    }

    public void deleteOffer(Long id) {
        offersRepository.deleteById(id);
    }

    public List<Offer> getOffersByUser(User user) {
        return offersRepository.findAllByUser(user);
    }

    public Optional<Offer> getOffer(Long id) {
        return offersRepository.findById(id);
    }

    public int buyOffer(Offer offer, User user) {
        if (offer.getUser().getEmail().equals(user.getEmail())) {
            return 1;
        } else if (offer.getPrice() > user.getCartera()) {
            return 2;
        } else if (offer.isSold()) {
            return 3;
        } else {
            offer.setSold(true);
            offer.setBuyer(user);
            user.setCartera(user.getCartera() - offer.getPrice());
            user.buyOffer(offer);
            usersRepository.save(user);
            offersRepository.save(offer);
        }
        return 0;
    }
}
