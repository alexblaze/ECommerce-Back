package com.sanjay.service;

import com.sanjay.exception.UserException;
import com.sanjay.modal.User;

public interface AuthService {

    String signupCustomer(User user);

}
