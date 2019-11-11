package com.ysnerruk.api.repository;

import java.util.List;
import java.util.stream.Collectors;

import com.ysnerruk.api.model.Message;
import org.springframework.data.jpa.repository.JpaRepository;


public interface MessageRepository extends JpaRepository<Message, Integer> {


    public default List<Message> findByUserId(Integer id){
    	List <Message> allMessages = this.findAll();
    	return allMessages.stream().filter(message -> message.getReceiver().getId()==id || message.getSender().getId()==id).collect(Collectors.toList());
    }
}