package com.sanjay.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public class SignUpDto {
    @NotBlank(message = "first name is a required field")
    private String firstName;
    @NotBlank(message = "first name is a required field")
    private String lastName;
    @NotBlank(message = "email is a required field")
    @Email(message = "invalid email")
    private String email;
    @NotBlank(message = "phone number is a required field")

    private String phoneNumber;
    @NotBlank(message = "message is a required field")
    private String password;

    public SignUpDto() {
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
