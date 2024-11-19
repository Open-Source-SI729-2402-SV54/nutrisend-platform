package com.example.nutrisend.platform.order.domain.model.aggregates;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // O String, dependiendo de cómo estés manejando los IDs

    @Column(nullable = false)
    private String userId;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private List<OrderItem> items; // Asegúrate de que OrderItem también esté anotado como entidad si es necesario

    @Column(nullable = false)
    private double total;

    public Order() {
        // Constructor vacío requerido por JPA
    }

    public Order(String userId, List<OrderItem> items, double total) {
        this.userId = userId;
        this.items = items;
        this.total = total;
    }

    // Getters y setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public List<OrderItem> getItems() {
        return items;
    }

    public void setItems(List<OrderItem> items) {
        this.items = items;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }
}