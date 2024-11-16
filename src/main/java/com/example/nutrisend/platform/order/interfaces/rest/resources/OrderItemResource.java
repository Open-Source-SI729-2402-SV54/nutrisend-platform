package com.example.nutrisend.platform.order.rest.resources;

public record OrderItemResource(
        String name,
        double price,
        String category,
        int quantity
) {
}
