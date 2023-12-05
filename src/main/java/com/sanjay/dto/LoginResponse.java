package com.sanjay.dto;

import com.sanjay.user.domain.UserRole;

public class LoginResponse {
    private Integer userId;
    private UserRole userRole;
    private boolean isTempPassword;
    private String jwt;

    public LoginResponse() {
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public UserRole getUserRole() {
        return userRole;
    }

    public void setUserRole(UserRole userRole) {
        this.userRole = userRole;
    }

    public boolean isTempPassword() {
        return isTempPassword;
    }

    public void setTempPassword(boolean tempPassword) {
        isTempPassword = tempPassword;
    }

    public String getJwt() {
        return jwt;
    }

    public void setJwt(String jwt) {
        this.jwt = jwt;
    }
}
