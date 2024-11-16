package com.example.nutrisend.platform.order.infrastructure.persistence.jpa.repositories;

import com.example.nutrisend.platform.order.domain.model.aggregates.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

}