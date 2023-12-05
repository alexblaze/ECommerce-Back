package com.sanjay.repository;

import com.sanjay.modal.SignupRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SignUpRequestRepository extends JpaRepository<SignupRequest,Integer> {
    Optional<SignupRequest> findByIdAndEmailAndCode(Integer id, String email,String code);
}
