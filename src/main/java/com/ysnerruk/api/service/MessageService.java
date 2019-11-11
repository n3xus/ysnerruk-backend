package com.ysnerruk.api.service;

import java.util.List;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import com.ysnerruk.api.exception.ApiException;
import com.ysnerruk.api.model.User;
import com.ysnerruk.api.model.Message;
import com.ysnerruk.api.model.Offer;
import com.ysnerruk.api.repository.MessageRepository;
import com.ysnerruk.api.repository.OfferRepository;
import com.ysnerruk.api.security.JWTProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class MessageService {

    @Autowired
    private MessageRepository messageRepository;
	
	public Integer sendMessage(Message message, User sender, Date date) {
        message.setSender(sender);
        message.setdate(date);
        messageRepository.save(message);
        return message.getId();
    }
	
    // Returns a list of all existing offers.
    public List<Message> getMessages(User user) {
    	return messageRepository.findByUserId(user.getId());
    }
	
	
}
