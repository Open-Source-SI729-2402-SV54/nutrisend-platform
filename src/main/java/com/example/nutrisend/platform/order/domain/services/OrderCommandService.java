package com.example.nutrisend.platform.order.domain.services;

import com.example.nutrisend.platform.order.domain.model.aggregates.Order;
import com.example.nutrisend.platform.order.domain.model.commands.CreateOrderCommand;
import com.example.nutrisend.platform.order.domain.model.commands.DeleteOrderCommand;
import com.example.nutrisend.platform.order.domain.model.commands.UpdateOrderCommand;

import java.util.Optional;

public interface OrderCommandService {
    Optional<Order> handle(CreateOrderCommand command);

    Optional<Order> update(UpdateOrderCommand command);

    boolean delete(DeleteOrderCommand command);
}