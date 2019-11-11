package com.ysnerruk.api.model;


import javax.persistence.*;
import javax.validation.constraints.Size;


@Entity
public class Offer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(optional=false)
    @JoinColumn(nullable=false)
    private User maker;

    @ManyToOne(optional=true)
    @JoinColumn(nullable=true)
    private User taker;

    @Column()
    private String description;

    @Column()
    private Integer offerAmount;
    
    @Column()
    private Integer receiveAmount;
    
    @Column()
    private String offerCurrency;

    @Column()
    private String receiveCurrency;
        
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public User getMaker() {
        return maker;
    }

    public void setMaker(User maker) {
        this.maker = maker;
    }
    
    public User getTaker() {
        return taker;
    }

    public void setTaker(User taker) {
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
		if (offerAmount < 1) {
			return;
		}
		this.offerAmount = offerAmount;
	}

	public Integer getReceiveAmount() {
		return receiveAmount;
	}

	public void setReceiveAmount(Integer receiveAmount) {
		if (receiveAmount < 1) {
			return;
		}
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