package ee.nikita.tellimused.order.repository;

import ee.nikita.tellimused.order.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}