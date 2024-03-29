package com.ysnerruk.api.model;

import java.util.Set;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
public class User {
    @ElementCollection(fetch = FetchType.EAGER)
    List<Role> roles;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Size(min = 4, max = 255, message = "Minimum username length: 4 characters")
    @Column(unique = true, nullable = false)
    private String username;

    @Column(unique = true, nullable = false)
    private String email;

    @Size(min = 8, message = "Minimum password length: 8 characters")
    private String password;
    
    @OneToMany(fetch = FetchType.EAGER,mappedBy="maker")
    private Set<Offer> makerOffers;
    
    @OneToMany(mappedBy="taker")
    private Set<Offer> takerOffers;
    
    @OneToMany(mappedBy="reviewFor")
    private Set<Review> reviewFor;
    
    @OneToMany(mappedBy="reviewBy")
    private Set<Review> reviewBy;
    
    public Integer getTradeCount() {
     	if (reviewFor == null) {
    		return 0;
    	}
     	return reviewFor.size();
    }
    
    public Integer getRating() {
    	if (reviewFor == null || reviewFor.size() == 0) {
    		return 0;
    	}
    	int total = reviewFor.size();
    	int rating = 0;
    	
    	for (Review review: reviewFor) {
    		if (review != null && review.getRating()) {
    			rating++;
    		}
    	}
    	return rating * 100 / total;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }
}