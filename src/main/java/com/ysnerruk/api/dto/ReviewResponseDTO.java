package com.ysnerruk.api.dto;

import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

public class ReviewResponseDTO {

    @ApiModelProperty
    private Integer id;
    @ApiModelProperty(position = 1)
    private OfferResponseDTO offer;
    @ApiModelProperty(position = 2)
    private UserResponseDTO reviewBy;
    @ApiModelProperty(position = 3)
    private UserResponseDTO reviewFor;
    @ApiModelProperty(position = 4)
    private Boolean rating;
    @ApiModelProperty(position = 6)
    private String description;
    @ApiModelProperty(position = 7)
    private Date reviewDate;

    public OfferResponseDTO getOffer() {
		return offer;
	}

	public void setOffer(OfferResponseDTO offer) {
		this.offer = offer;
	}

	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public UserResponseDTO getReviewBy() {
        return reviewBy;
    }

    public void setReviewBy(UserResponseDTO reviewBy) {
        this.reviewBy = reviewBy;
    }

    public UserResponseDTO getReviewFor() {
        return reviewFor;
    }

    public void setReviewFor(UserResponseDTO reviewFor) {
        this.reviewFor = reviewFor;
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