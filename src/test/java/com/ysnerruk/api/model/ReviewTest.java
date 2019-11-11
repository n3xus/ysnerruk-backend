package com.ysnerruk.api.model;

import static org.junit.Assert.*;

import org.junit.Test;

public class ReviewTest {

    @Test
    public void testGetAndSetId() {
        Review review = new Review();
        assertNull(review.getId());
        Integer id = 10;
        review.setId(id);
        assertEquals(id, review.getId());
    }
    
    @Test
    public void testGetAndSetReviewBy() {
        Review review = new Review();
        assertNull(review.getReviewBy());
        User user = new User();
        review.setReviewBy(user);
        assertEquals(user, review.getReviewBy());
    }
    
    @Test
    public void testGetAndSetReviewFor() {
        Review review = new Review();
        assertNull(review.getReviewFor());
        User user = new User();
        review.setReviewFor(user);
        assertEquals(user, review.getReviewFor());
    }
    
    @Test
    public void testGetAndSetRating() {
        Review review = new Review();
        assertNull(review.getRating());
        Boolean rating = true;
        review.setRating(rating);
        assertEquals(rating, review.getRating());
    }
    
    @Test
    public void testGetAndSetDecription() {
        Review review = new Review();
        assertNull(review.getRating());
        String description = "blah";
        review.setDescription(description);
        assertEquals(description, review.getDescription());
    }
}