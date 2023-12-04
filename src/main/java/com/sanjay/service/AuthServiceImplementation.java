package com.sanjay.service;

import com.sanjay.config.email.EmailService;
import com.sanjay.exception.UserException;
import com.sanjay.helper.SignUpEmailHelper;
import com.sanjay.modal.User;
import com.sanjay.repository.UserRepository;
import org.springframework.security.crypto.encrypt.Encryptors;
import org.springframework.security.crypto.encrypt.TextEncryptor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriBuilder;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@Service
public class AuthServiceImplementation implements AuthService {
private final UserService userService;

private final UserRepository userRepository;

private final PasswordEncoder passwordEncoder;

private EmailService emailService;
    private static final String DEAD = "DE4D";
    private static final String FACE = "FAC3";

    public AuthServiceImplementation(UserService userService, EmailService emailService, UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.emailService =emailService;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public String signupCustomer(User user) {

        String email = user.getEmail();
        String password = user.getPassword();
        String firstName=user.getFirstName();
        String lastName=user.getLastName();

        User isEmailExist=userRepository.findByEmail(email);

        // Check if user with the given email already exists
        if (isEmailExist!=null) {
            throw new UserException(String.format("%s is already registered", email));
        }

        // Create new user
        User createdUser= new User();
        createdUser.setEmail(email);
        createdUser.setFirstName(firstName);
        createdUser.setLastName(lastName);
        createdUser.setPassword(passwordEncoder.encode(password));

        User savedUser= userService.save(createdUser);

        createSignupEmailVerification(email,firstName,lastName);





return "Success";
    }

    URI createSignupEmailVerification(String email, String firstName, String lastname) {

        String referer;


        TextEncryptor textEncryptor = Encryptors.text(DEAD, FACE);
        String code = textEncryptor.encrypt(email);


        User user = new User();
                user.setFirstName(firstName);
                user.setLastName(lastname);
                user.setEmail(email);

                user.setCode(code);



        user = userService.save(user);
        UriBuilder uriBuilder = UriComponentsBuilder.newInstance();


        URI uri = URI.create(String.format("http://localhost:3000/verify-signup/%s/%s/%s",
                user.getId(), code, email));
        new SignUpEmailHelper(emailService).emailRequestVerification(email, uri.toString());

        return uri;
    }


}
