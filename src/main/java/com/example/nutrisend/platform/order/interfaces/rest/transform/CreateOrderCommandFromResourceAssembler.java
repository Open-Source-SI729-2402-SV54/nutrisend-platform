package com.example.nutrisend.platform.order.interfaces.rest.transform;

import com.example.nutrisend.platform.order.domain.model.commands.CreateOrderCommand;
import com.example.nutrisend.platform.order.domain.model.commands.CreateOrderItemCommand;
import com.example.nutrisend.platform.order.interfaces.rest.resources.CreateOrderResource;

public class CreateOrderCommandFromResourceAssembler {
    public static CreateOrderCommand toCommandFromResource(CreateOrderResource resource) {
        return new CreateOrderCommand(
                resource.userId(),
                resource.items().stream()
                        .map(item -> new CreateOrderItemCommand(
                                item.id(),
                                item.quantity()
                        ))
                        .toList()
        );
    }
}
