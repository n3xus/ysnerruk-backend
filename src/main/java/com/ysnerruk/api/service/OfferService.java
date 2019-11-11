package com.ysnerruk.api.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import com.ysnerruk.api.exception.ApiException;
import com.ysnerruk.api.model.Message;
import com.ysnerruk.api.model.Offer;
import com.ysnerruk.api.model.User;
import com.ysnerruk.api.repository.MessageRepository;
import com.ysnerruk.api.repository.OfferRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;


@Service
public class OfferService {

    @Autowired
    private OfferRepository offerRepository;
    
    @Autowired
    private MessageRepository messageRepository;
        
    // Returns an offer given the id.
    public Offer getOffer(Integer id) {
    	if (id == null) {
    		throw new ApiException("The offer doesn't exist", HttpStatus.BAD_REQUEST);
    	}
    	
        Optional<Offer> offer = offerRepository.findById(id);
        if (offer == null || offer.isEmpty()) {
            throw new ApiException("The offer doesn't exist", HttpStatus.BAD_REQUEST);
        } else {
        	return offer.get();
        }
    }

    // Returns a list of all existing offers.
    public List<Offer> listOffers() {
    	return offerRepository.findAll();
    }
    
    // Creates an offer if none currently exists.
    // Returns the id of the newly created offer.
    public Integer createOffer(User sender, Offer offer) {
    	// Require an offer to be given
    	if (offer == null) {
    		throw new ApiException("No offer given", HttpStatus.BAD_REQUEST);
    	} else if (sender == null) {
    		throw new ApiException("Sender Id was not resolved", HttpStatus.BAD_REQUEST);
    	}
    	    	
    	// Validate new offer
    	OfferValidatorService.validateCreateOffer(offer);
    	offer.setMaker(sender);
    	
    	// Create new offer
    	offerRepository.save(offer);
    	offerRepository.flush();
    	return offer.getId();
    }
    
    public void editOffer(User sender, Integer id, Offer offer) {
    	// Require an offer to be given
    	if (offer == null || id == null) {
    		throw new ApiException("The offer was not received", HttpStatus.BAD_REQUEST);
    	} else if (sender == null) {
    		throw new ApiException("Sender Id was not resolved", HttpStatus.BAD_REQUEST);
    	}
    	
    	// Set Id to API parameter.
    	offer.setId(id);
    	OfferValidatorService.validateCreateOffer(offer);

    	
    	Optional<Offer> existingOffer = offerRepository.findById(offer.getId());
    	if (existingOffer.isPresent()) {
    		// Offer exists
    		if (!existingOffer.get().getMaker().equals(sender)) {
    			throw new ApiException("Sender is not maker", HttpStatus.BAD_REQUEST);
    		} else if (existingOffer.get().getTaker() != null) {
    			// Offer has already been accepted
    			throw new ApiException("Cannot edit accepted offer", HttpStatus.BAD_REQUEST);
    		}
    		offer.setMaker(sender);
    		offerRepository.save(offer);
    	}
    }
    
    // Accepts an existing offer
    public void acceptOffer(User sender, Integer id) {
    	// Require an offer to be given
    	if (sender == null || id == null) {
    		throw new ApiException("Sender Id was not resolved", HttpStatus.BAD_REQUEST);
    	}
    	
    	Optional<Offer> offerOption = offerRepository.findById(id);
    	if (offerOption.isPresent()) {
    		Offer offer = offerOption.get();
    		// Offer exists
    		if (offer.getMaker().equals(sender)) {
    			throw new ApiException("Sender can not be maker and taker", HttpStatus.BAD_REQUEST);
    		} else if (offer.getTaker() != null) {
    			// Offer has already been accepted
    			throw new ApiException("Offer already accepted", HttpStatus.BAD_REQUEST);
    		}
    		offer.setTaker(sender);
    		offerRepository.save(offer);
    		
    		Message msg = new Message();
    		msg.setMessage("I have accepted your offer");
    		msg.setSender(offer.getTaker());
    		msg.setReceiver(offer.getMaker());
    		msg.setdate(new Date());
    		messageRepository.save(msg);
    	}
    }
    
    
    // Deletes an offer if the sender was the maker.
    public void deleteOffer(User sender, Integer id) {
    	// Require an sender
    	if (sender == null || id == null) {
    		throw new ApiException("Sender Id was not resolved", HttpStatus.BAD_REQUEST);
    	}
    	
    	// Ensure offer exists
    	Optional<Offer> offer = offerRepository.findById(id);
    	if (offer.isEmpty()) {
    		throw new ApiException("Offer doesn't exist", HttpStatus.BAD_REQUEST);
    	}
    
    	// Ensure sender is maker
    	if (offer.get().getMaker().getId().equals(sender.getId())) {
    		offerRepository.delete(offer.get());
    	} else {
    		throw new ApiException("You did not create this offer", HttpStatus.BAD_REQUEST);
    	}
    }
}