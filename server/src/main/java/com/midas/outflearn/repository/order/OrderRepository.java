package com.midas.outflearn.repository.order;

import com.midas.outflearn.model.order.Order;

public interface OrderRepository {
    Order insert(Order order);
}
