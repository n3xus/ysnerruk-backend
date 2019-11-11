package com.ysnerruk.api.dto;

import io.swagger.annotations.ApiModelProperty;


public class CreateOfferDataDTO {
  
    @ApiModelProperty(position = 2)
    private String description;
    @ApiModelProperty(position = 3)
    private Integer offerAmount;
    @ApiModelProperty(position = 4)
    private Integer receiveAmount;
    @ApiModelProperty(position = 5)
    private String offerCurrency;
    @ApiModelProperty(position = 6)
    private String receiveCurrency;

    public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Integer getOfferAmount() {
		return offerAmount;
	}
	public void setOfferAmount(Integer offerAmount) {
		this.offerAmount = offerAmount;
	}
	public Integer getReceiveAmount() {
		return receiveAmount;
	}
	public void setReceiveAmount(Integer receiveAmount) {
		this.receiveAmount = receiveAmount;
	}
	public String getOfferCurrency() {
		return offerCurrency;
	}
	public void setOfferCurrency(String offerCurrency) {
		this.offerCurrency = offerCurrency;
	}
	public String getReceiveCurrency() {
		return receiveCurrency;
	}
	public void setReceiveCurrency(String receiveCurrency) {
		this.receiveCurrency = receiveCurrency;
	}    
}