package com.example.nutrisend.platform.order.application.internal;
import com.example.nutrisend.platform.order.domain.model.aggregates.Order;
import com.example.nutrisend.platform.order.domain.model.queries.GetOrdersByIdQuery;
import com.example.nutrisend.platform.order.domain.services.OrderQueryService;
import com.example.nutrisend.platform.order.infrastructure.persistence.jpa.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderQueryServiceImpl implements OrderQueryService {

    private final OrderRepository orderRepository;

    @Autowired
    public OrderQueryServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public Optional<Order> handle(GetOrdersByIdQuery query) {
        return orderRepository.findById(query.id());
    }

    @Override
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }
}