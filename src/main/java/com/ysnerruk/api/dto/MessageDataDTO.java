package com.ysnerruk.api.dto;

import com.ysnerruk.api.model.Role;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;
import java.util.Date;


public class MessageDataDTO {
    @ApiModelProperty
    private UserIdDataDTO receiver;

    @ApiModelProperty
    private String message;

    public UserIdDataDTO getReceiver() {
        return receiver;
    }

    public void setReceiver(UserIdDataDTO receiver) {
        this.receiver = receiver;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
