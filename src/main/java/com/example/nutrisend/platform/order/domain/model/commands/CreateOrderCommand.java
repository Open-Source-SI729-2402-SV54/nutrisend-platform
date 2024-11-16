package com.example.nutrisend.platform.order.domain.model.commands;

import java.util.List;

public record CreateOrderCommand(
        String userId,
        List<CreateOrderItemCommand> items
) {
    public CreateOrderCommand {
        if (userId == null || userId.isBlank()) {
            throw new IllegalArgumentException("User ID cannot be null or empty");
        }
        if (items == null || items.isEmpty()) {
            throw new IllegalArgumentException("Items cannot be null or empty");
        }
    }
}

