package com.sanjay.service;

import com.sanjay.exception.UserException;
import com.sanjay.modal.User;
import com.sanjay.user.domain.UserRole;

public interface AuthService {

    String signupCustomer(User user);

    String changeRole(UserRole userRole, String jwt);

}
