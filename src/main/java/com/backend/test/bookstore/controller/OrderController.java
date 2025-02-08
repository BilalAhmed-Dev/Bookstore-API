package com.backend.test.bookstore.controller;

import com.backend.test.bookstore.dto.GetOrderDTO;
import com.backend.test.bookstore.dto.NewOrderDTO;
import com.backend.test.bookstore.entity.Order;
import com.backend.test.bookstore.service.OrderService;

import com.backend.test.bookstore.serviceimpl.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/order") // Base path for user-related endpoints
public class OrderController {

    private final OrderService orderService;
    private final JwtService jwtService;

    public OrderController(OrderService orderService, JwtService jwtService) {
        this.orderService = orderService;
        this.jwtService = jwtService;
    }

    @GetMapping("/getOrders")
    public List<GetOrderDTO> getOrder(HttpServletRequest request){
        System.out.println("getOrder");
        Integer userId = jwtService.getLoggedInUserId(request, "accessToken");

        return orderService.getOrder(userId);
    }

    @PostMapping("/checkout")
    public Order checkout(@RequestBody NewOrderDTO newOrderDTO, HttpServletRequest request){
        System.out.println("checkOut");
        Integer userId = jwtService.getLoggedInUserId(request, "accessToken");
        return orderService.addOrder(newOrderDTO, userId);
    }
}
