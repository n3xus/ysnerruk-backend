package com.ysnerruk.api.model;


import java.util.Date;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Entity
public class Message{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(optional=true)
    @JoinColumn(nullable=true)
    private User sender;

    @ManyToOne(optional=true)
    @JoinColumn(nullable=true)
    private User receiver;

    @Column
    private String message;

    @Column
    private Date date;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id){
        this.id = id;
    }

    public User getReceiver() {
        return receiver;
    }

    public void setReceiver(User receiver){
        this.receiver = receiver;
    }

    public User getSender(){
        return sender;
    }

    public void setSender(User sender){
        this.sender = sender;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Date getdate() {
        return date;
    }

    public void setdate(Date date) {
        this.date = date;
    }
}
