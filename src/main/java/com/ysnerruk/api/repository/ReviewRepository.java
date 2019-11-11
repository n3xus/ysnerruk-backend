package com.ysnerruk.api.repository;

import com.ysnerruk.api.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

import javax.transaction.Transactional;

public interface ReviewRepository extends JpaRepository<Review, Integer> {
//    Integer (Review review);
//    void update(Review review);
//    Review get(int id);
//    @Transactional
//    void deleteById(int id);
	
	List<Review> findAllByOfferId(Integer offerId);
}
