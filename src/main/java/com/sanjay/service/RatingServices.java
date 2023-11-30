package com.sanjay.service;

import java.util.List;

import com.sanjay.exception.ProductException;
import com.sanjay.modal.Rating;
import com.sanjay.modal.User;
import com.sanjay.request.RatingRequest;

public interface RatingServices {
	
	public Rating createRating(RatingRequest req,User user) throws ProductException;
	
	public List<Rating> getProductsRating(Long productId);

}
