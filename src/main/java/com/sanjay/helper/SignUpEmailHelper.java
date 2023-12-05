package com.sanjay.helper;

import com.sanjay.config.email.EmailPlaceHolders;
import com.sanjay.config.email.EmailService;
import com.sanjay.modal.SignupRequest;
import jakarta.mail.MessagingException;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

public class SignUpEmailHelper {
    private final EmailService emailService;

    public SignUpEmailHelper(EmailService emailService) {
        this.emailService = emailService;
    }
    public void emailRequestVerification(String email, String verificationUrl) {
        Map<String, Object> emailVerification = new HashMap<>();
        emailVerification.put(EmailPlaceHolders.TITLE, "Verify your email");
        emailVerification.put(EmailPlaceHolders.PRE_HEADER, String.format("We have received your new Signup Request for %s", email));
        emailVerification.put(EmailPlaceHolders.MESSAGE, "We first need to verify your email. " +
                "After you have verified your email, we will ask you to send additional information. " +
                "The details will follow in your next email. Your verification link is:");
        emailVerification.put(EmailPlaceHolders.HYPERLINK, verificationUrl);
        emailVerification.put(EmailPlaceHolders.HYPERLINK_TEXT, "Verification Link");
        emailVerification.put(EmailPlaceHolders.PARA_TWO, "Please verify your email by clicking the link above.");

        try {
            emailService.sendTemplateEmail(email, "Signup Request Received", emailVerification);
        } catch (MessagingException | UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }


    public void emailVerifiedConfirmation(SignupRequest signupRequest) {
        Map<String, Object> confirmationEmail = new HashMap<>();
        confirmationEmail.put(EmailPlaceHolders.TITLE, "Account Successfully verified");
        confirmationEmail.put(EmailPlaceHolders.PRE_HEADER, "You Account has been successfully verified");
        confirmationEmail.put(EmailPlaceHolders.MESSAGE, String.format("Your email: %s has been successfully verified", signupRequest.getEmail()));
        try {
            emailService.sendTemplateEmail(signupRequest.getEmail(), "Account Successfully verified", confirmationEmail);
        } catch (MessagingException | UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

}
