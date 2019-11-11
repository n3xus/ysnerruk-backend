package com.ysnerruk.api.service;

import com.ysnerruk.api.exception.ApiException;
import com.ysnerruk.api.model.Offer;
import com.ysnerruk.api.model.Review;
import com.ysnerruk.api.model.User;
import com.ysnerruk.api.repository.OfferRepository;
import com.ysnerruk.api.repository.ReviewRepository;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class ReviewService {

    @Autowired
    private ReviewRepository reviewRepository;
    
    @Autowired
    private OfferRepository offerRepository;

    public ReviewService() {
    }

    public Review create(Review review, User sender) {
    	if (review == null || 
			review.getOffer() == null ||
			review.getOffer().getId() == null) {
    		throw new ApiException("Bad review", HttpStatus.BAD_REQUEST);
    	}
    	
    	if (sender == null) {
        		throw new ApiException("Invalid sender", HttpStatus.BAD_REQUEST);
    	}
    	
    	Offer offer = offerRepository.getOne(review.getOffer().getId());
    	if (offer.getTaker() == null) {
    		throw new ApiException("Offer not ready for review", HttpStatus.BAD_REQUEST);
    	}
    	
    	List<Review> existingReviews = reviewRepository.findAllByOfferId(review.getOffer().getId());
    	for (Review existingReview: existingReviews) {
    		if (existingReview.getReviewBy().equals(sender)) {
    			throw new ApiException("User already left review", HttpStatus.BAD_REQUEST);
    		}
    	}
    		
    	// Set now as create time
    	Date date = new Date();
    	review.setReviewDate(date);
    	
    	// Update reviewBy and reviewFor
    	review.setReviewBy(sender);
    	if (offer.getMaker().getId().equals(sender.getId())) {
    		// Sender is Maker -> reviewFor Taker
    		review.setReviewFor(offer.getTaker());
    	} else {
    		// Sender is Taker -> reviewFor Maker
    		review.setReviewFor(offer.getMaker());
    	}
    	
        review = reviewRepository.save(review);
        reviewRepository.flush();
        return review;
    }

    public void delete(int id) {
        reviewRepository.deleteById(id);
    }

    public Review get(int id) {
        Review review = reviewRepository.getOne(id);
        if (review == null) {
            throw new ApiException("Review Not Found", HttpStatus.NOT_FOUND);
        }
        return review;
    }
    
    public List<Review> getByOffer(String id) {
    	Integer offerId;
    	try {
    		offerId = Integer.decode(id);
    	} catch (Exception e) {
    		throw new ApiException("OfferId not integer", HttpStatus.NOT_FOUND);
    	}
    	
    	if (!offerRepository.existsById(offerId)) {
    		throw new ApiException("Offer Not Found", HttpStatus.NOT_FOUND);
    	}
        List<Review> reviews = reviewRepository.findAllByOfferId(offerId);
        return reviews;
    }

    public Review editReview(Review map) {
        return reviewRepository.save(map);
    }
}
