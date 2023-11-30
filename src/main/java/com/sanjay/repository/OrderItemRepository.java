package com.sanjay.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sanjay.modal.OrderItem;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {

}
