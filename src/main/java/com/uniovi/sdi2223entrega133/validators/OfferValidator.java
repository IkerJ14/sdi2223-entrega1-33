package com.uniovi.sdi2223entrega133.validators;

import com.uniovi.sdi2223entrega133.entities.Offer;
import com.uniovi.sdi2223entrega133.services.OffersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class OfferValidator implements Validator {
    @Autowired
    private OffersService offersService;
    @Override
    public boolean supports(Class<?> aClass) {
        return Offer.class.equals(aClass);
    }
    @Override
    public void validate(Object target, Errors errors) {
        Offer offer = (Offer) target;
        System.out.println(((Offer) target).getPrice());
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "title", "Error.empty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "description", "Error.empty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "price", "Error.empty");

        if (offer.getDescription().length() < 10) {
            errors.rejectValue("description", "Error.offer.description.length");
        }

        if (offer.getPrice() != null) {
            if (offer.getPrice() < 0) {
                errors.rejectValue("price", "Error.offer.price.negative");
            }
        }
    }
}
