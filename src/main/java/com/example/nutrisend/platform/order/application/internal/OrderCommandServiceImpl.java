package com.example.nutrisend.platform.order.application.internal;

import com.example.nutrisend.platform.order.domain.model.aggregates.Order;
import com.example.nutrisend.platform.order.domain.model.aggregates.OrderItem;
import com.example.nutrisend.platform.order.domain.model.commands.CreateOrderCommand;
import com.example.nutrisend.platform.order.domain.model.commands.DeleteOrderCommand;
import com.example.nutrisend.platform.order.domain.model.commands.UpdateOrderCommand;
import com.example.nutrisend.platform.order.domain.services.OrderCommandService;
import com.example.nutrisend.platform.order.domain.valueObjects.Meal;
import com.example.nutrisend.platform.order.infrastructure.persistence.jpa.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class OrderCommandServiceImpl implements OrderCommandService {

    private final OrderRepository orderRepository;

    @Autowired
    public OrderCommandServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public Optional<Order> handle(CreateOrderCommand command) {
        String orderId = UUID.randomUUID().toString();

        List<OrderItem> orderItems = command.items().stream()
                .map(item -> {
                    Meal meal = Meal.fromId(item.id());
                    return new OrderItem(meal.getName(), meal.getPrice(), meal.getCategory().getName(), item.quantity());
                })
                .toList();

        double total = orderItems.stream()
                .mapToDouble(orderItem -> orderItem.getPrice() * orderItem.getQuantity())
                .sum();

        Order order = new Order(command.userId(), orderItems, total);

        orderRepository.save(order);

        return Optional.of(order);
    }

    @Override
    public boolean delete(DeleteOrderCommand command) {
        Optional<Order> orderOpt = orderRepository.findById(command.orderId());
        if (orderOpt.isPresent()) {
            orderRepository.delete(orderOpt.get());
            return true;
        }
        return false;
    }

    @Override
    public Optional<Order> update(UpdateOrderCommand command) {
        Optional<Order> orderOpt = orderRepository.findById(command.orderId());

        if (orderOpt.isPresent()) {
            Order orderToUpdate = orderOpt.get();

            List<OrderItem> updatedItems = command.items().stream()
                    .map(item -> {
                        Meal meal = Meal.fromId(item.id());
                        return new OrderItem(meal.getName(), meal.getPrice(), meal.getCategory().getName(), item.quantity());
                    })
                    .toList();

            double total = updatedItems.stream()
                    .mapToDouble(orderItem -> orderItem.getPrice() * orderItem.getQuantity())
                    .sum();

            Order updatedOrder = new Order(orderToUpdate.getUserId(), updatedItems, total);

            orderRepository.save(updatedOrder);

            return Optional.of(updatedOrder);
        }

        return Optional.empty();
    }
}