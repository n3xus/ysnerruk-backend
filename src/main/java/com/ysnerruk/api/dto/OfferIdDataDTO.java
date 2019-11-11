package com.ysnerruk.api.dto;

import io.swagger.annotations.ApiModelProperty;

public class OfferIdDataDTO {
    @ApiModelProperty(position = 0)
    private Integer id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
