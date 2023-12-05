package com.sanjay.service;

import com.sanjay.dto.LoginDto;
import com.sanjay.dto.LoginResponse;
import com.sanjay.dto.SignUpDto;
import com.sanjay.exception.UserException;
import com.sanjay.modal.User;
import com.sanjay.response.AuthResponse;

public interface AuthService {

    String signupCustomer(SignUpDto signUpDto);



    String verifySignup(Integer requestId, String email, String code);

}
