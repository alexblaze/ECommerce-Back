package com.sanjay.service;

import com.sanjay.exception.ProductException;
import com.sanjay.modal.Cart;
import com.sanjay.modal.User;
import com.sanjay.request.AddItemRequest;

public interface CartService {
	
	public Cart createCart(User user);
	
	public String addCartItem(Long userId,AddItemRequest req) throws ProductException;
	
	public Cart findUserCart(Long userId);

}
