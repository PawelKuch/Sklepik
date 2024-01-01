package com.example.demo;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findOrderByUser(User user);
    Order findOrderByOrderId(String orderId);
    Order findByUser(User user);
}
