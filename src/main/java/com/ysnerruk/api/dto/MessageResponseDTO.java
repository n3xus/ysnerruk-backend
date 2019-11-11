package com.ysnerruk.api.dto;

import java.util.Date;

import com.ysnerruk.api.model.Message;

import io.swagger.annotations.ApiModelProperty;

public class MessageResponseDTO {
	
	
	@ApiModelProperty(position = 0)
    private Integer id;
	@ApiModelProperty(position = 1)
    private UserResponseDTO sender;
    @ApiModelProperty(position = 2)
    private UserResponseDTO receiver;
    @ApiModelProperty(position = 3)
    private String message;
    @ApiModelProperty(position = 4)
    private Date sendDate;
    @ApiModelProperty(position=5)
    private String receiverUserName;
    @ApiModelProperty(position=6)
    private String senderUserName;

    public String getReceiverUserName() {
        return receiverUserName;
    }

    public void setReceiverUserName(String receiverUserName) {
        this.receiverUserName = receiverUserName;
    }

    public String getSenderUserName() {
        return senderUserName;
    }

    public void setSenderUserName(String senderUserName) {
        this.senderUserName = senderUserName;
    }

    public Integer getId() {
        return id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }

    public UserResponseDTO getSender() {
        return sender;
    }

    public void setSender(UserResponseDTO sender) {
        this.sender = sender;
    }

    public UserResponseDTO getReceiver() {
        return receiver;
    }

    public void setReceiver(UserResponseDTO receiver) {
        this.receiver = receiver;
    }
    
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
    
    public Date getDate() {
        return sendDate;
    }

    public void setDate(Date date) {
        this.sendDate = date;
    }
}
