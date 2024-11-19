package com.example.nutrisend.platform.order.interfaces.rest.resources;

import java.util.List;

public record OrderResource(
        Long id,
        String userId,
        List<com.example.nutrisend.platform.order.rest.resources.OrderItemResource> items,
        double total
) {
}