package com.sanjay.service;

import com.sanjay.config.email.EmailService;
import com.sanjay.dto.LoginDto;
import com.sanjay.dto.LoginResponse;
import com.sanjay.dto.SignUpDto;
import com.sanjay.exception.UserException;
import com.sanjay.helper.LocalAssert;
import com.sanjay.helper.SignUpEmailHelper;
import com.sanjay.modal.SignupRequest;
import com.sanjay.modal.User;
import com.sanjay.repository.UserRepository;
import com.sanjay.response.AuthResponse;
import org.springframework.security.crypto.encrypt.Encryptors;
import org.springframework.security.crypto.encrypt.TextEncryptor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriBuilder;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.time.LocalDateTime;

@Service
public class AuthServiceImplementation implements AuthService {
private final UserService userService;

private final UserRepository userRepository;

private final PasswordEncoder passwordEncoder;

private final SignUpRequestService signUpRequestService;

private EmailService emailService;
    private static final String DEAD = "DE4D";
    private static final String FACE = "FAC3";

    public AuthServiceImplementation(UserService userService,SignUpRequestService signUpRequestService, EmailService emailService, UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.emailService =emailService;
        this.signUpRequestService=signUpRequestService;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public String signupCustomer(SignUpDto signUpDto) {

        String email = signUpDto.getEmail();
        String password = signUpDto.getPassword();
        String firstName=signUpDto.getFirstName();
        String lastName=signUpDto.getLastName();

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
        createdUser.setCreatedAt(LocalDateTime.now());

        User savedUser= userService.save(createdUser);

        createSignupEmailVerification(email,firstName,lastName);





return "Success";
    }

    URI createSignupEmailVerification(String email, String firstName, String lastname) {




        TextEncryptor textEncryptor = Encryptors.text(DEAD, FACE);
        String code = textEncryptor.encrypt(email);


        SignupRequest signupRequest = new SignupRequest();
        signupRequest.setFirstName(firstName);
        signupRequest.setLastName(lastname);
        signupRequest.setEmail(email);

        signupRequest.setCode(code);
        signupRequest.setVerified(false);
        signupRequest.setCreated(LocalDateTime.now());
        signupRequest.setExpiry(LocalDateTime.now().plusHours(48));



        signupRequest = signUpRequestService.save(signupRequest);
        UriBuilder uriBuilder = UriComponentsBuilder.newInstance();


        URI uri = URI.create(String.format("http://localhost:3000/verify-signup/%s/%s/%s",
                signupRequest.getId(), code, email));
        new SignUpEmailHelper(emailService).emailRequestVerification(email, uri.toString());

        return uri;
    }


    @Override
    public String verifySignup(Integer requestId, String email, String code) {
SignupRequest signupRequest = signUpRequestService.getBySignupRequestIdAndEmailAndCode(requestId,email,code);
LocalAssert.isFalse(signupRequest.isVerified(), "Your Email already verified");
boolean signUpExpired = LocalDateTime.now().isAfter(signupRequest.getExpiry());
if(signUpExpired){
    signUpRequestService.delete(signupRequest);
    userService.deleteByUsername(email);
    throw new UserException("Verification Link has expired. Please signup again");
}
signupRequest.setVerified(true);
signUpRequestService.save(signupRequest);
User user = userService.getByEmail(email);
userService.save(user);

        SignUpEmailHelper signUpEmailHelper = new SignUpEmailHelper(emailService);
        signUpEmailHelper.emailVerifiedConfirmation(signupRequest);
        signUpRequestService.delete(signupRequest);

        LoginDto loginDto = new LoginDto();
        loginDto.setEmail(email);
        loginDto.setPassword(user.getPassword());

        return "Success";

    }

}
