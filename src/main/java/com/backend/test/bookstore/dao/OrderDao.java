package com.backend.test.bookstore.dao;

import com.backend.test.bookstore.entity.Order;

import java.util.List;

public interface OrderDao {
    List<Order> getOrderByUserId(String userId);
    Order saveOrder(Order order);
}
