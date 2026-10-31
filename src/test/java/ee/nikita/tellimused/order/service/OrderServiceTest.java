package ee.nikita.tellimused.order.service;

import ee.nikita.tellimused.TestcontainersConfiguration;
import ee.nikita.tellimused.order.dto.OrderDto;
import ee.nikita.tellimused.order.dto.OrderItemDto;
import ee.nikita.tellimused.order.entity.OrderStatus;
import ee.nikita.tellimused.order.exception.OrderNotFoundException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@Import(TestcontainersConfiguration.class)
class OrderServiceTest {

    @Autowired
    private OrderService orderService;

    @Test
    void getOrders_returnsAllOrdersWithItems() {
        PageRequest pageRequest = PageRequest.of(0, 20, Sort.by(Sort.Direction.DESC, "createdAt"));

        Page<OrderDto> page = orderService.getOrders(pageRequest);
        List<OrderDto> orders = page.getContent();

        assertEquals(2, orders.size());
        assertEquals("ORD-1002", orders.get(0).orderNumber());
        assertEquals("ORD-1001", orders.get(1).orderNumber());
        assertEquals(1, orders.get(0).items().size());
        assertEquals(2, orders.get(1).items().size());
    }

    @Test
    void getOrders_returnsOnlyRequestedPage() {
        PageRequest pageRequest = PageRequest.of(0, 1);

        Page<OrderDto> page = orderService.getOrders(pageRequest);

        assertEquals(1, page.getContent().size());
        assertEquals(2, page.getTotalElements());
        assertEquals(2, page.getTotalPages());
    }

    @Test
    void getOrder_returnsOrderWithItems() {
        OrderDto order = orderService.getOrder(1L);

        assertEquals("ORD-1001", order.orderNumber());
        assertEquals(OrderStatus.PAID, order.status());
        assertEquals(2, order.items().size());

        OrderItemDto keyboard = findItem(order, "Klaviatuur");
        assertEquals(1, keyboard.quantity());
        assertEquals(new BigDecimal("49.90"), keyboard.unitPrice());
    }

    @Test
    void getOrder_throwsExceptionWhenOrderDoesNotExist() {
        assertThrows(OrderNotFoundException.class, () -> orderService.getOrder(999L));
    }

    private OrderItemDto findItem(OrderDto order, String name) {
        for (OrderItemDto item : order.items()) {
            if (item.name().equals(name)) {
                return item;
            }
        }
        throw new AssertionError("Item not found: " + name);
    }
}
