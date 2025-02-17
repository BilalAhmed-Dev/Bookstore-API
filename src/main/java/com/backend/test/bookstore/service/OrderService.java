package com.backend.test.bookstore.service;
import com.backend.test.bookstore.dto.GetOrderDTO;
import com.backend.test.bookstore.dto.NewOrderDTO;
import com.backend.test.bookstore.entity.Order;

import java.util.List;

public interface OrderService {
    List<GetOrderDTO> getOrder(String userId );
    Order addOrder(NewOrderDTO newOrderDTO, String userId );
}
