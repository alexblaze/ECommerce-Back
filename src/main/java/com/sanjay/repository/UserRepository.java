package com.sanjay.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.sanjay.modal.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
	
	User findByEmail(String email);

	Optional<User> findByIdAndEmailAndCode(Integer id, String email, String code);

	boolean exitsByEmail(String username);
	void deleteByEmail(String username);

}
