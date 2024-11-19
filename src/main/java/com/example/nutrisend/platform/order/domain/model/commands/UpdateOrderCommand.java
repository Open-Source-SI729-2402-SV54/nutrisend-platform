package com.example.nutrisend.platform.order.domain.model.commands;

import java.util.List;

public record UpdateOrderCommand(
        Long orderId,
        List<UpdateOrderItemCommand> items // Lista de ítems a actualizar
) {
    public UpdateOrderCommand {
        if (orderId == null) {
            throw new IllegalArgumentException("Order ID cannot be null");
        }
        if (items == null || items.isEmpty()) {
            throw new IllegalArgumentException("Items cannot be null or empty");
        }
    }
}