package com.sanjay.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.sanjay.modal.User;

public interface UserRepository extends JpaRepository<User, Long> {
	
	public User findByEmail(String email);


}
