package com.sanjay.dto;


import jakarta.validation.constraints.NotBlank;

public class LoginDto {

    @NotBlank(message = "email is  a required field")
    private String email;
    @NotBlank(message = "password is a required field")
    private String password;

    public String getEmail() {
        return email;
    }

    public LoginDto setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public LoginDto setPassword(String password) {
        this.password = password;
        return this;
    }
}
