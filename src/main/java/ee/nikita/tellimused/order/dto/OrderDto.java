package ee.nikita.tellimused.order.dto;

import ee.nikita.tellimused.order.entity.Order;
import ee.nikita.tellimused.order.entity.OrderStatus;

import java.time.Instant;
import java.util.List;

public record OrderDto(Long id, String orderNumber, OrderStatus status, Instant createdAt, List<OrderItemDto> items) {
    public static OrderDto from(Order order) {
        return new OrderDto(
                order.getId(),
                order.getOrderNumber(),
                order.getStatus(),
                order.getCreatedAt(),
                order.getItems().stream()
                        .map(OrderItemDto::from)
                        .toList()
        );
    }
}
