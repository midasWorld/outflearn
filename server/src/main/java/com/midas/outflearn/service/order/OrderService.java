package com.midas.outflearn.service.order;

import com.midas.outflearn.dto.order.OrderQueryDto;
import com.midas.outflearn.model.order.Order;
import com.midas.outflearn.repository.order.OrderQueryJdbcRepository;
import com.midas.outflearn.repository.order.OrderRepository;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.google.common.base.Preconditions.checkArgument;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final OrderQueryJdbcRepository orderQueryJdbcRepository;

    public OrderService(OrderRepository orderRepository, OrderQueryJdbcRepository orderQueryJdbcRepository) {
        this.orderRepository = orderRepository;
        this.orderQueryJdbcRepository = orderQueryJdbcRepository;
    }

    public Order create(Order order) {
        return orderRepository.insert(order);
    }

    public List<Order> findAll() {
        return orderRepository.findAll();
    }

    public List<OrderQueryDto> findAllOrderQueryDto() {
        return orderQueryJdbcRepository.findAllOrderQueryDto();
    }

    public OrderQueryDto findOrderQueryDtoById(Long orderId) {
        checkArgument(orderId != null, "orderId must be provided.");

        return orderQueryJdbcRepository.findOrderQueryDtoById(orderId)
            .orElseThrow(() -> new IllegalArgumentException("Could not found order with orderId=" + orderId));
    }
}
