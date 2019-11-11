package com.ysnerruk.api.dto;

import io.swagger.annotations.ApiModelProperty;

public class ReviewDataDTO {
	@ApiModelProperty(position = 0)
    private OfferIdDataDTO offer;
	@ApiModelProperty(position = 1)
    private Boolean rating;
	@ApiModelProperty(position = 2)
    private String description;

    public Boolean getRating() {
        return rating;
    }

    public void setRating(Boolean rating) {
        this.rating = rating;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

	public OfferIdDataDTO getOffer() {
		return offer;
	}

	public void setOffer(OfferIdDataDTO offer) {
		this.offer = offer;
	}
}