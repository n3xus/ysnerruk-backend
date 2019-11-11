package com.ysnerruk.api.dto;

import io.swagger.annotations.ApiModelProperty;


public class OfferResponseDTO {

	@ApiModelProperty(position = 0)
    private Integer id;
    @ApiModelProperty(position = 1)
    private UserResponseDTO maker;
    @ApiModelProperty(position = 2)
    private UserResponseDTO taker;
	@ApiModelProperty(position = 3)
    private String description;
    @ApiModelProperty(position = 4)
    private Integer offerAmount;
    @ApiModelProperty(position = 5)
    private Integer receiveAmount;
    @ApiModelProperty(position = 6)
    private String offerCurrency;
    @ApiModelProperty(position = 7)
    private String receiveCurrency;

    
    public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public UserResponseDTO getMaker() {
		return maker;
	}
	public void setMaker(UserResponseDTO maker) {
		this.maker = maker;
	}
	public UserResponseDTO getTaker() {
		return taker;
	}
	public void setTaker(UserResponseDTO taker) {
		this.taker = taker;
	}
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