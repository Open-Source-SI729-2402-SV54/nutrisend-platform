package com.example.nutrisend.platform.order.domain.model.commands;

public record UpdateOrderItemCommand(
        int id,
        int quantity
) {
    public UpdateOrderItemCommand {
        if (quantity <= 0) {
            throw new IllegalArgumentException("Quantity must be greater than zero");
        }
    }
}
