package com.sanjay.service;

import com.sanjay.exception.UserException;
import com.sanjay.modal.SignupRequest;
import com.sanjay.repository.SignUpRequestRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SignUpRequestServiceImplementation implements SignUpRequestService {
    private final SignUpRequestRepository signupRequestRepository;

    public SignUpRequestServiceImplementation(SignUpRequestRepository signupRequestRepository) {
        this.signupRequestRepository = signupRequestRepository;
    }

    @Override
    public SignupRequest save(SignupRequest signupRequest) {
        return signupRequestRepository.save((signupRequest));
    }

    @Override
    public Optional<SignupRequest> findBySignupRequestIdAndEmailAndCode(Integer requestId, String email, String code) {
        return signupRequestRepository.findByIdAndEmailAndCode(requestId,email,code);
    }

    @Override
    public SignupRequest getBySignupRequestIdAndEmailAndCode(Integer requestId, String email, String code) {
        return signupRequestRepository.findByIdAndEmailAndCode(requestId,email,code)
                .orElseThrow(()-> new UserException("Signup email not found or you may have already verified your email"));
    }

    @Override
    public void delete(SignupRequest signupRequest) {
signupRequestRepository.delete(signupRequest);
    }
}
