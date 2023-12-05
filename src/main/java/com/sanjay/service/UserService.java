package com.sanjay.service;

import com.sanjay.exception.UserException;
import com.sanjay.modal.User;

public interface UserService {

	User save(User user);
	public User findUserById(Long userId) throws UserException;
	
	public User findUserProfileByJwt(String jwt) throws UserException;

	void deleteByUsername(String email);

	User getByEmail(String email);




}
