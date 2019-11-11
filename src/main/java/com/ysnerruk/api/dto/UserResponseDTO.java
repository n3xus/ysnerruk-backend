package com.ysnerruk.api.dto;

import com.ysnerruk.api.model.Role;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

public class UserResponseDTO {

    //@ApiModelProperty(position = 3)
    //List<Role> roles;
    @ApiModelProperty(position = 0)
    private Integer id;
    @ApiModelProperty(position = 1)
    private String username;
    @ApiModelProperty(position = 2)
    private String email;
    @ApiModelProperty(position = 3)
    private Integer tradeCount = 0;
    @ApiModelProperty(position = 4)
    private Integer rating = 0;


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
    /*
    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }
    */
	public Integer getTradeCount() {
		return tradeCount;
	}

	public void setTradeCount(Integer tradeCount) {
		this.tradeCount = tradeCount;
	}

	public Integer getRating() {
		return rating;
	}

	public void setRating(Integer rating) {
		this.rating = rating;
	}
}