package com.example.nutrisend.platform.order.domain.model.commands;

public record CreateOrderItemCommand(
        int id,
        int quantity
) {
    public CreateOrderItemCommand {
        if (quantity <= 0) {
            throw new IllegalArgumentException("Quantity must be greater than zero");
        }
    }
}

