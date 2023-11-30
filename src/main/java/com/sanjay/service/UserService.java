package com.sanjay.service;

import com.sanjay.exception.UserException;
import com.sanjay.modal.User;

public interface UserService {
	
	public User findUserById(Long userId) throws UserException;
	
	public User findUserProfileByJwt(String jwt) throws UserException;

}
