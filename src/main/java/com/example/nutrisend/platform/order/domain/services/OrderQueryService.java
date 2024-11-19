package com.example.nutrisend.platform.order.domain.services;

import com.example.nutrisend.platform.order.domain.model.aggregates.Order;
import com.example.nutrisend.platform.order.domain.model.queries.GetOrdersByIdQuery;

import java.util.List;
import java.util.Optional;

public interface OrderQueryService {
    Optional<Order> handle(GetOrdersByIdQuery query);
    List<Order> getAllOrders();
}