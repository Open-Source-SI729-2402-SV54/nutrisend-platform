package com.example.nutrisend.platform.order.domain.model.commands;

public record DeleteOrderCommand(Long orderId) {
    public DeleteOrderCommand {
        if (orderId == null) {
            throw new IllegalArgumentException("Order ID cannot be null");
        }
    }
}