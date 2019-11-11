package com.ysnerruk.api.controller;

import com.ysnerruk.api.dto.CreateOfferDataDTO;
import com.ysnerruk.api.dto.OfferResponseDTO;

import com.ysnerruk.api.exception.ApiException;
import com.ysnerruk.api.model.Offer;
import com.ysnerruk.api.model.User;
import com.ysnerruk.api.service.OfferService;
import com.ysnerruk.api.service.UserService;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/offers")
@Api(tags = "offers")
public class OfferController {

    @Autowired
    private OfferService offerService;

    @Autowired

    private UserService userService;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_CLIENT')")
    @ApiOperation(value = "${OfferController.getOffer}", response = OfferResponseDTO.class)
    @ApiResponses(value = {//
            @ApiResponse(code = 400, message = "Something went wrong"), //
            @ApiResponse(code = 403, message = "Access Denied"), //
            @ApiResponse(code = 404, message = "The offer doesn't exist")})
    public OfferResponseDTO getOffer(@ApiParam("Id") @PathVariable Integer id){
        return modelMapper.map(offerService.getOffer(id), OfferResponseDTO.class);
    }
        
    @GetMapping("")
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_CLIENT')")
    @ApiOperation(value = "${OfferController.listOffers}", response = OfferResponseDTO.class)
    @ApiResponses(value = {//
            @ApiResponse(code = 400, message = "Something went wrong"), //
            @ApiResponse(code = 403, message = "Access Denied")})
    public List<OfferResponseDTO> listOffers(){
    	// Map list of Offers to List of OfferResponseDTO
        return offerService.listOffers().stream()
        		.map(offer -> modelMapper.map(offer, OfferResponseDTO.class))
        		.collect(Collectors.toList());
    }

    @PostMapping("")
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_CLIENT')")
    @ApiOperation(value = "${OfferController.createOffer}")
    @ApiResponses(value = {//
            @ApiResponse(code = 400, message = "Something went wrong"), //
            @ApiResponse(code = 403, message = "Access denied")})
    public Integer createOffer(HttpServletRequest req, @ApiParam("CreateOffer") @RequestBody CreateOfferDataDTO offer) {    	

        return offerService.createOffer(getSenderByJwt(req), modelMapper.map(offer, Offer.class));
    }
    
    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_CLIENT')")
    @ApiOperation(value = "${OfferController.editOffer}")
    @ApiResponses(value = {//
            @ApiResponse(code = 400, message = "Something went wrong"), //
            @ApiResponse(code = 403, message = "Access denied")})
    public void editOffer(HttpServletRequest req, @ApiParam("OfferId") @PathVariable Integer id, @ApiParam("EditOffer") @RequestBody CreateOfferDataDTO offer) {

        offerService.editOffer(getSenderByJwt(req), id, modelMapper.map(offer, Offer.class));

    }
    
    @PatchMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_CLIENT')")
    @ApiOperation(value = "${OfferController.editOffer}")
    @ApiResponses(value = {//
            @ApiResponse(code = 400, message = "Something went wrong"), //
            @ApiResponse(code = 403, message = "Access denied")})
    public void acceptOffer(HttpServletRequest req, @ApiParam("OfferId") @PathVariable Integer id, @ApiParam("EditOffer") @RequestBody CreateOfferDataDTO offer) {

        offerService.acceptOffer(getSenderByJwt(req), id);
    }

    @DeleteMapping(value = "/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_CLIENT')")
    @ApiOperation(value = "${OfferController.delete}")
    @ApiResponses(value = {//
            @ApiResponse(code = 400, message = "Something went wrong"), //
            @ApiResponse(code = 403, message = "Access denied"), //
            @ApiResponse(code = 404, message = "The user doesn't exist")})
    public void delete(HttpServletRequest req, @ApiParam("OfferId") @PathVariable Integer id){

        offerService.deleteOffer(getSenderByJwt(req), id);
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