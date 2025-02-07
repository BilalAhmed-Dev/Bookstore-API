package com.backend.test.bookstore.controller;

import com.backend.test.bookstore.dto.GetOrderDTO;
import com.backend.test.bookstore.dto.NewOrderDTO;
import com.backend.test.bookstore.entity.Order;
import com.backend.test.bookstore.service.OrderService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/order") // Base path for user-related endpoints
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping("/getOrders")
    public List<GetOrderDTO> getOrder(){
        System.out.println("getOrder");
        return orderService.getOrder();
    }

    @PostMapping("/checkout")
    public Order checkout(@RequestBody NewOrderDTO newOrderDTO){
        System.out.println("checkOut");
        return orderService.addOrder(newOrderDTO);
    }
}
