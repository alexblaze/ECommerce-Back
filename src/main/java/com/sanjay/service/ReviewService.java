package com.sanjay.service;

import java.util.List;

import com.sanjay.exception.ProductException;
import com.sanjay.modal.Review;
import com.sanjay.modal.User;
import com.sanjay.request.ReviewRequest;

public interface ReviewService {

	public Review createReview(ReviewRequest req,User user) throws ProductException;
	
	public List<Review> getAllReview(Long productId);
	
	
}
