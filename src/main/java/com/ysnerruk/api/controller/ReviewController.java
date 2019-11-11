package com.ysnerruk.api.controller;

import com.ysnerruk.api.dto.ReviewDataDTO;
import com.ysnerruk.api.dto.ReviewResponseDTO;
import com.ysnerruk.api.model.Review;
import com.ysnerruk.api.service.ReviewService;
import com.ysnerruk.api.service.UserService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/review")
@Api(tags = "review")
public class ReviewController {
	
	@Autowired
    private ReviewService reviewService;
	@Autowired
    private ModelMapper modelMapper;
	@Autowired
	private UserService userService;
    

    @PostMapping("/")
    @ApiOperation(value = "${ReviewController.create}", response = ReviewResponseDTO.class)
    public ReviewResponseDTO create(HttpServletRequest req,//
    		@ApiParam("Create Review") @RequestBody ReviewDataDTO review) {
    	return modelMapper.map(reviewService.create(modelMapper.map(review, Review.class), userService.whoami(req)), ReviewResponseDTO.class);
    }

    @PutMapping(value = "/{id}")
    @ApiOperation(value = "${ReviewController.update}", response = ReviewResponseDTO.class)
    public ReviewResponseDTO update(@ApiParam("Update Review") @RequestBody ReviewDataDTO review) {
        return modelMapper.map(//
        		reviewService.editReview(modelMapper.map(review, Review.class)),//
        		ReviewResponseDTO.class);
    }

    @DeleteMapping(value = "/{id}")
    @ApiOperation(value = "${ReviewController.delete}")
    public void delete(@ApiParam("id") @PathVariable int id) {
        reviewService.delete(id);
    }
    
    @GetMapping(value = "/")
    @ApiOperation(value = "${ReviewController.get}", response = ReviewResponseDTO.class)
    public List<ReviewResponseDTO> get(@ApiParam("offerId") @RequestParam String offerId) {
    	List<Review> reviews = reviewService.getByOffer(offerId);
    	return reviews.parallelStream().map(review -> modelMapper.map(review, ReviewResponseDTO.class))
    			.collect(Collectors.toList());
    }
}