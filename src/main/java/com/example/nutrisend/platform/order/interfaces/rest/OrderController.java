package com.example.nutrisend.platform.order.interfaces.rest;

import com.example.nutrisend.platform.order.domain.model.aggregates.Order;
import com.example.nutrisend.platform.order.domain.model.commands.CreateOrderCommand;
import com.example.nutrisend.platform.order.domain.model.commands.DeleteOrderCommand;
import com.example.nutrisend.platform.order.domain.model.commands.UpdateOrderCommand;
import com.example.nutrisend.platform.order.domain.model.queries.GetOrdersByIdQuery;
import com.example.nutrisend.platform.order.domain.services.OrderCommandService;
import com.example.nutrisend.platform.order.domain.services.OrderQueryService;
import com.example.nutrisend.platform.order.interfaces.rest.resources.CreateOrderResource;
import com.example.nutrisend.platform.order.interfaces.rest.resources.OrderResource;
import com.example.nutrisend.platform.order.interfaces.rest.transform.CreateOrderCommandFromResourceAssembler;
import com.example.nutrisend.platform.order.interfaces.rest.transform.OrderResourceFromEntityAssembler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping(value = "/api/v1/orders", produces = "application/json")
@Tag(name = "Orders", description = "Operations related to orders")
public class OrderController {

    private final OrderQueryService orderQueryService;
    private final OrderCommandService orderCommandService;

    public OrderController(OrderQueryService orderQueryService, OrderCommandService orderCommandService) {
        this.orderQueryService = orderQueryService;
        this.orderCommandService = orderCommandService;
    }

    @Operation(
            summary = "Create an order",
            description = "Create a new order with the provided details"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Order created"),
            @ApiResponse(responseCode = "400", description = "Bad Request")
    })
    @PostMapping
    public ResponseEntity<OrderResource> createOrder(@RequestBody CreateOrderResource resource) {
        CreateOrderCommand command = CreateOrderCommandFromResourceAssembler.toCommandFromResource(resource);

        Optional<Order> savedOrder = orderCommandService.handle(command); // Definición de la variable savedOrder

        return savedOrder.map(o -> new ResponseEntity<>(OrderResourceFromEntityAssembler.toResourceFromEntity(o), CREATED))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @Operation(
            summary = "Get an order by ID",
            description = "Retrieve a specific order by its unique ID"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Order found"),
            @ApiResponse(responseCode = "404", description = "Order not found")
    })
    @GetMapping("{id}")
    public ResponseEntity<OrderResource> getOrderById(@PathVariable Long id) {
        Optional<Order> orderItem = orderQueryService.handle(new GetOrdersByIdQuery(id));
        return orderItem.map(order -> ResponseEntity.ok(OrderResourceFromEntityAssembler.toResourceFromEntity(order)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(
            summary = "Get all orders",
            description = "Retrieve all orders"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Orders retrieved"),
            @ApiResponse(responseCode = "404", description = "No orders found")
    })
    @GetMapping
    public ResponseEntity<List<OrderResource>> getAllOrders() {
        List<Order> orders = orderQueryService.getAllOrders();
        List<OrderResource> orderResources = orders.stream()
                .map(OrderResourceFromEntityAssembler::toResourceFromEntity)
                .toList();
        return ResponseEntity.ok(orderResources);
    }

    @Operation(
            summary = "Update an order",
            description = "Update a specific order by its unique ID"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Order updated"),
            @ApiResponse(responseCode = "404", description = "Order not found"),
            @ApiResponse(responseCode = "400", description = "Bad Request")
    })
    @PutMapping("{id}")
    public ResponseEntity<OrderResource> updateOrder(@PathVariable Long id, @RequestBody UpdateOrderCommand command) {
        if (!command.orderId().equals(id)) {
            return ResponseEntity.badRequest().build(); // Verifica que el ID del pedido coincida
        }

        Optional<Order> updatedOrder = orderCommandService.update(command); // Implementa el método update en OrderCommandService
        return updatedOrder.map(order -> ResponseEntity.ok(OrderResourceFromEntityAssembler.toResourceFromEntity(order)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(
            summary = "Delete an order",
            description = "Delete a specific order by its unique ID"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Order deleted"),
            @ApiResponse(responseCode = "404", description = "Order not found")
    })
    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteOrder(@PathVariable Long id) {
        DeleteOrderCommand command = new DeleteOrderCommand(id);
        boolean deleted = orderCommandService.delete(command); // Implementa el método delete en OrderCommandService
        return deleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}
