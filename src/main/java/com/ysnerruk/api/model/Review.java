package com.ysnerruk.api.model;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne(optional = false)
    @JoinColumn(nullable = false)
    private User reviewBy;
    @ManyToOne(optional = false)
    @JoinColumn(nullable = false)
    private User reviewFor;
    @ManyToOne(optional = false)
    @JoinColumn(nullable = false)
    private Offer offer;
    @Column()
    private Boolean rating;
    @Column()
    private Date reviewDate;
    @Column(length = 500)
    private String description;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public User getReviewBy() {
        return reviewBy;
    }

    public void setReviewBy(User reviewBy) {
        this.reviewBy = reviewBy;
    }

    public User getReviewFor() {
        return reviewFor;
    }

    public void setReviewFor(User reviewFor) {
        this.reviewFor = reviewFor;
    }
    
    public Offer getOffer() {
        return offer;
    }

    public void setOffer(Offer offer) {
        this.offer = offer;
    }

    public Boolean getRating() {
        return rating;
    }

    public void setRating(Boolean rating) {
        this.rating = rating;
    }

    public Date getReviewDate() {
        return reviewDate;
    }

    public void setReviewDate(Date reviewDate) {
        this.reviewDate = reviewDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
