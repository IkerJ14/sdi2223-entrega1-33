package com.uniovi.sdi2223entrega133.validators;

import com.uniovi.sdi2223entrega133.entities.ConversationMessage;
import com.uniovi.sdi2223entrega133.entities.Offer;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class MessageValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return Offer.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        ConversationMessage message = (ConversationMessage) target;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "textMessage", "Error.empty");
    }
}
