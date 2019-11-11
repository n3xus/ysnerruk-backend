package com.ysnerruk.api.service;

import org.springframework.http.HttpStatus;

import com.ysnerruk.api.exception.ApiException;
import com.ysnerruk.api.model.Offer;


public class OfferValidatorService {
	static Integer CURRENCY_LENGTH = 3;
	static Integer MINIMUM_AMOUNT = 0;
	static Integer MINIMUM_DESCRIPTION_LENGTH = 0;
	static Integer MAXIMUM_DESCRIPTION_LENGTH = 500;
	
	static void validateCurrency(String currency) {
		if (currency == null || !CURRENCY_LENGTH.equals(currency.length())) {
			throw new ApiException("Currency length should be 3", HttpStatus.BAD_REQUEST);
		}
	}
	
	static void validateAmount(Integer amount) {
		if (amount == null || MINIMUM_AMOUNT.equals(amount.intValue())) {
			throw new ApiException("Amount should be greater than 0", HttpStatus.BAD_REQUEST);
		}
	}
	
	static void validateDescription(String description) {
		if (description == null ||
				MINIMUM_DESCRIPTION_LENGTH > description.length() ||
				MAXIMUM_DESCRIPTION_LENGTH < description.length()) {
			throw new ApiException("No description", HttpStatus.BAD_REQUEST);
		}
	}
	
	static void validateCreateOffer(Offer offer) {
		if (offer == null) {
			throw new ApiException("Offer does not exist", HttpStatus.BAD_REQUEST);
		}
		validateAmount(offer.getOfferAmount());
		validateAmount(offer.getOfferAmount());
		validateCurrency(offer.getOfferCurrency());
		validateCurrency(offer.getReceiveCurrency());
		validateDescription(offer.getDescription());
		if (offer.getReceiveCurrency().equals(offer.getOfferCurrency())) {
			throw new ApiException("Offer and receive same currency", HttpStatus.BAD_REQUEST);
		}
	}
}
