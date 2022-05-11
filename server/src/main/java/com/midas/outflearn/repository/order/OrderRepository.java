package com.midas.outflearn.repository.order;

import java.util.List;

import com.midas.outflearn.model.order.Order;

public interface OrderRepository {
	Order insert(Order order);

	List<Order> findAll();
}
