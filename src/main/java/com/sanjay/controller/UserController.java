package com.sanjay.controller;

import com.sanjay.response.SuccessResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sanjay.exception.UserException;
import com.sanjay.modal.User;
import com.sanjay.service.UserService;

@RestController
@RequestMapping("/api/users")
public class UserController {
	
	private UserService userService;
	
	public UserController(UserService userService) {
		this.userService=userService;
	}

	@GetMapping("/profile")
	public ResponseEntity<?> getUserProfileHandler(@RequestHeader("Authorization") String jwt) throws UserException{



		return SuccessResponse.configure(userService.findUserProfileByJwt(jwt));
	}

}
