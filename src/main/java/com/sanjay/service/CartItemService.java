package com.sanjay.service;

import com.sanjay.exception.CartItemException;
import com.sanjay.exception.UserException;
import com.sanjay.modal.Cart;
import com.sanjay.modal.CartItem;
import com.sanjay.modal.Product;

public interface CartItemService {
	
	public CartItem createCartItem(CartItem cartItem);
	
	public CartItem updateCartItem(Long userId, Long id,CartItem cartItem) throws CartItemException, UserException;
	
	public CartItem isCartItemExist(Cart cart,Product product,String size, Long userId);
	
	public void removeCartItem(Long userId,Long cartItemId) throws CartItemException, UserException;
	
	public CartItem findCartItemById(Long cartItemId) throws CartItemException;
	
}
