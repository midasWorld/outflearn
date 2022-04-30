package com.midas.outflearn.service.order;

import com.midas.outflearn.model.order.Order;
import com.midas.outflearn.repository.order.OrderRepository;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    private final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public Order create(Order order) {
        return orderRepository.insert(order);
    }
}
