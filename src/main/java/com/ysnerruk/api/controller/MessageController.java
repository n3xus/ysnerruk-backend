package com.ysnerruk.api.controller;

import javax.servlet.http.HttpServletRequest;

import com.ysnerruk.api.dto.MessageDataDTO;
import com.ysnerruk.api.dto.MessageResponseDTO;
import com.ysnerruk.api.dto.OfferResponseDTO;
import com.ysnerruk.api.exception.ApiException;
import com.ysnerruk.api.model.Message;
import com.ysnerruk.api.model.Offer;
import com.ysnerruk.api.model.User;
import com.ysnerruk.api.service.MessageService;
import com.ysnerruk.api.service.UserService;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.Date;

@RestController
@RequestMapping("/messages")
@Api(tags = "messages")

public class MessageController {
	
	
    @Autowired
    private MessageService messageService;
    
    @Autowired
    private UserService userService;

    @Autowired
    private ModelMapper modelMapper;	

	@PostMapping("")
    @ApiOperation(value = "${MessageController.sendMessage}")
//    @ApiResponses(value = {//
//            @ApiResponse(code = 400, message = "Something went wrong"), //
//            @ApiResponse(code = 403, message = "Access denied"), //
//            @ApiResponse(code = 500, message = "Expired or invalid JWT token")})
    public Integer sendMessage(HttpServletRequest req, @ApiParam("SendMessage") @RequestBody MessageDataDTO message) {
	    Date date = new Date();
	    System.out.println(date);
        return messageService.sendMessage(modelMapper.map(message, Message.class),getSenderByJwt(req),date);
    }

//    public Integer sendMessage( @ApiParam("SendMessage") @RequestBody MessageDataDTO message) {
//        return messageService.sendMessage(modelMapper.map(message, Message.class));
//    }

	

    @GetMapping("")
    @ApiOperation(value = "${MessageController.getMessages}", response = MessageResponseDTO.class)
//    @ApiResponses(value = {//
//            @ApiResponse(code = 400, message = "Something went wrong"), //
//            @ApiResponse(code = 403, message = "Access Denied")})
    public List<MessageResponseDTO> getMessages(HttpServletRequest req){
    	// Map list of Offers to List of OfferResponseDTO
       return messageService.getMessages(getSenderByJwt(req)).stream().map(message -> modelMapper.map(message,MessageResponseDTO.class))
    		   .collect(Collectors.toList());
    }
	
    // Converts a JWT request to a UserId
    protected User getSenderByJwt(HttpServletRequest req) {
    	User user = this.userService.whoami(req);
    	if (user == null || user.getId() == null) {
    		throw new ApiException("User does not exist", HttpStatus.NOT_FOUND);
    	} else {
    		return user;
    	}

    }
	
	
}
