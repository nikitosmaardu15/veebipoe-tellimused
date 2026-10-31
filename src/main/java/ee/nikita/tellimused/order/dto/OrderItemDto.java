package ee.nikita.tellimused.order.dto;

import ee.nikita.tellimused.order.entity.OrderItem;

import java.math.BigDecimal;

public record OrderItemDto(Long id, String name, Integer quantity, BigDecimal unitPrice) {
    public static OrderItemDto from(OrderItem item) {
        return new OrderItemDto(
                item.getId(),
                item.getName(),
                item.getQuantity(),
                item.getUnitPrice()
        );
    }
}
