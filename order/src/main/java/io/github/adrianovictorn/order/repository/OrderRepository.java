package io.github.adrianovictorn.order.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import io.github.adrianovictorn.order.model.Order;

public interface OrderRepository extends JpaRepository<Order,Long> {
    
}
