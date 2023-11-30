package com.sanjay.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sanjay.modal.Address;

public interface AddressRepository extends JpaRepository<Address, Long> {

}
