package com.sanjay.response;

import com.sanjay.user.domain.UserRole;

public class AuthResponse {
	
	private String jwt;
	
	private boolean status;

	private UserRole userRole;
	
	public AuthResponse() {
		// TODO Auto-generated constructor stub
	}

	public AuthResponse(String jwt, boolean status, UserRole userRole) {
		super();
		this.jwt = jwt;
		this.status = status;
		this.userRole = userRole;
	}

	public UserRole getUserRole() {
		return userRole;
	}

	public void setUserRole(UserRole userRole) {
		this.userRole = userRole;
	}

	public boolean isStatus() {
		return status;
	}



	public String getJwt() {
		return jwt;
	}

	public void setJwt(String jwt) {
		this.jwt = jwt;
	}

	public boolean getStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}
	
	

}
