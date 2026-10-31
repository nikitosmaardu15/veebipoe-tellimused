package ee.nikita.tellimused.order.service;

import ee.nikita.tellimused.order.dto.OrderDto;
import ee.nikita.tellimused.order.exception.OrderNotFoundException;
import ee.nikita.tellimused.order.repository.OrderRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class OrderService {

    private final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public Page<OrderDto> getOrders(Pageable pageable) {
        return orderRepository.findAll(pageable)
                .map(OrderDto::from);
    }

    public OrderDto getOrder(Long id) {
        return orderRepository.findById(id)
                .map(OrderDto::from)
                .orElseThrow(() -> new OrderNotFoundException(id));
    }
}
