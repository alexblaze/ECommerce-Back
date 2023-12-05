package com.sanjay.controller;

import com.sanjay.config.email.EmailPlaceHolders;
import com.sanjay.config.email.EmailService;
import com.sanjay.dto.LoginDto;
import com.sanjay.dto.LoginResponse;
import com.sanjay.dto.SignUpDto;
import com.sanjay.helper.SignUpEmailHelper;
import com.sanjay.response.SuccessResponse;
import com.sanjay.service.AuthService;
import jakarta.mail.MessagingException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.sanjay.config.JwtTokenProvider;
import com.sanjay.exception.UserException;
import com.sanjay.modal.User;
import com.sanjay.repository.UserRepository;
import com.sanjay.request.LoginRequest;
import com.sanjay.response.AuthResponse;
import com.sanjay.service.CartService;
import com.sanjay.service.CustomUserDetails;

import jakarta.validation.Valid;
import org.springframework.web.util.UriBuilder;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {

	private UserRepository userRepository;
	private PasswordEncoder passwordEncoder;
	private JwtTokenProvider jwtTokenProvider;
	private CustomUserDetails customUserDetails;
	private CartService cartService;
	private EmailService emailService;
	private final AuthService authService;

	
	public AuthController(UserRepository userRepository, AuthService authService, EmailService emailService,PasswordEncoder passwordEncoder,JwtTokenProvider jwtTokenProvider,CustomUserDetails customUserDetails,CartService cartService) {
		this.userRepository=userRepository;
		this.passwordEncoder=passwordEncoder;
		this.jwtTokenProvider=jwtTokenProvider;
		this.customUserDetails=customUserDetails;
		this.authService =authService;
		this.cartService=cartService;
		this.emailService=emailService;
	}

	@PostMapping("/signup")
	public ResponseEntity<?> customerSignup(@RequestBody @Valid SignUpDto signUpDto){
		String message = authService.signupCustomer(signUpDto);
		return SuccessResponse.configure(message);
	}
	
//	@PostMapping("/signup")
//	public ResponseEntity<?> createUserHandler(@Valid @RequestBody User user) throws UserException{
//
//		  	String email = user.getEmail();
//	        String password = user.getPassword();
//	        String firstName=user.getFirstName();
//	        String lastName=user.getLastName();
//
//	        User isEmailExist=userRepository.findByEmail(email);
//
//	        // Check if user with the given email already exists
//	        if (isEmailExist!=null) {
//	        // System.out.println("--------- exist "+isEmailExist).getEmail());
//
//	            throw new UserException("Email Is Already Used With Another Account");
//	        }
//
//	        // Create new user
//			User createdUser= new User();
//			createdUser.setEmail(email);
//			createdUser.setFirstName(firstName);
//			createdUser.setLastName(lastName);
//	        createdUser.setPassword(passwordEncoder.encode(password));
//
//
//
//	        User savedUser= userRepository.save(createdUser);
//
//	        cartService.createCart(savedUser);
//
//	        Authentication authentication = new UsernamePasswordAuthenticationToken(email, password);
//	        SecurityContextHolder.getContext().setAuthentication(authentication);
//
//	        String token = jwtTokenProvider.generateToken(authentication);
//
//	        AuthResponse authResponse= new AuthResponse(token,true);
//		UriBuilder uriBuilder = UriComponentsBuilder.newInstance();
//			URI uri = uriBuilder.host("http://localhost:3000/")
//					.scheme("http")
//					.path("verify-signup/{i}/{e}")
//					.build(user.getId(),email);
//
//			new SignUpEmailHelper(emailService).emailRequestVerification(email, uri.toString());
//
//	        return new ResponseEntity<AuthResponse>(authResponse,HttpStatus.OK);
//
//	}
//


	@PutMapping("verify-signup/{i}/{c}/{e}")
	public ResponseEntity<?> verifySignUp(@PathVariable int i,@PathVariable String c, @PathVariable String e){
		String message = authService.verifySignup(i,e,c);
		return SuccessResponse.configure(message);
	}


//	@PostMapping("/signin")
//	public ResponseEntity<?> authenticate(@Validated @RequestBody LoginDto loginDto){
//		LoginResponse authResponse = authService.authenticate(loginDto,false);
//		return SuccessResponse.configure(authResponse);
//	}


	@PostMapping("/signin")
    public ResponseEntity<AuthResponse> signin(@RequestBody LoginRequest loginRequest) {
        String username = loginRequest.getEmail();
        String password = loginRequest.getPassword();



        Authentication authentication = authenticate(username, password);
        SecurityContextHolder.getContext().setAuthentication(authentication);


        String token = jwtTokenProvider.generateToken(authentication);
        AuthResponse authResponse= new AuthResponse();

		authResponse.setStatus(true);
		authResponse.setJwt(token);

        return new ResponseEntity<AuthResponse>(authResponse,HttpStatus.OK);
    }
	
	private Authentication authenticate(String username, String password) {
        UserDetails userDetails = customUserDetails.loadUserByUsername(username);
        
        System.out.println("sign in userDetails - "+userDetails);
        
        if (userDetails == null) {
        	System.out.println("sign in userDetails - null " + userDetails);
            throw new BadCredentialsException("Invalid username or password");
        }
        if (!passwordEncoder.matches(password, userDetails.getPassword())) {
        	System.out.println("sign in userDetails - password not match " + userDetails);
            throw new BadCredentialsException("Invalid username or password");
        }
        return new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
    }
}
