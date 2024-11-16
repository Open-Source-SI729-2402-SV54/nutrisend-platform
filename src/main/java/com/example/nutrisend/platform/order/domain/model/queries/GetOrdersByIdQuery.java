package com.example.nutrisend.platform.order.domain.model.queries;

public record GetOrdersByIdQuery(Long id) {
    public GetOrdersByIdQuery {
        if (id == null) {
            throw new IllegalArgumentException("ID cannot be null or empty");
        }
    }
}