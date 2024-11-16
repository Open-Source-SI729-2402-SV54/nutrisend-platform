package com.example.nutrisend.platform.order.interfaces.rest.resources;

import java.util.List;

public record CreateOrderResource(
        String userId,
        List<CreateOrderItemResource> items
) {
}