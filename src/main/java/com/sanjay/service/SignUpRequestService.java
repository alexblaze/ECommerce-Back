package com.sanjay.service;

import com.sanjay.modal.SignupRequest;

import java.util.Optional;

public interface SignUpRequestService {

    SignupRequest save(SignupRequest signupRequest);

    Optional<SignupRequest> findBySignupRequestIdAndEmailAndCode(Integer requestId, String email, String code);

    SignupRequest getBySignupRequestIdAndEmailAndCode(Integer requestId, String email, String code);

    void delete(SignupRequest signupRequest);
}
