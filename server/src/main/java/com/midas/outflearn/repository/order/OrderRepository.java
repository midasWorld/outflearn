package com.midas.outflearn.repository.order;

import com.midas.outflearn.model.order.Order;

import java.util.List;

public interface OrderRepository {
    Order insert(Order order);

    List<Order> findAll();
}
