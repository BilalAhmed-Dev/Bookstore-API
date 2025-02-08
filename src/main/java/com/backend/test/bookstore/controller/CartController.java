package com.backend.test.bookstore.controller;

import com.backend.test.bookstore.entity.CartItem;
import com.backend.test.bookstore.service.CartService;
import com.backend.test.bookstore.serviceimpl.JwtServiceImpl;
import com.backend.test.bookstore.utils.Msg;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/cart") // Base path for all cart-related operations
public class CartController {

    private final CartService cartService;
    private final JwtServiceImpl jwtService;

    public CartController(CartService cartService, JwtServiceImpl jwtService) {
        this.cartService = cartService;
        this.jwtService = jwtService;
    }

    @PostMapping("/add")
    public Msg addCartItem(@RequestParam("bookId")int bookId, HttpServletRequest request) {
        System.out.println("addCartItem");
        Integer userId = jwtService.getLoggedInUserId(request, "accessToken");
        return cartService.addCartItem(bookId, userId);
    }

    @GetMapping("/getCart")
    public List<CartItem> getCartList(HttpServletRequest request){
        Integer userId = jwtService.getLoggedInUserId(request, "accessToken");
        System.out.println("getCart");
        return cartService.getCartByUserId(userId);
    }

    @PutMapping("/decreaseCartAmount")
    public Msg decreaseCartAmount(@RequestParam("bookId")int bookId , HttpServletRequest request){
        Integer userId = jwtService.getLoggedInUserId(request, "accessToken");
        System.out.println("decreaseCartAmount");
        return cartService.decreaseAmount(bookId, userId);
    }

    @DeleteMapping("/deleteCartItem")
    public Msg deleteCartItem(@RequestParam("bookId")int bookId , HttpServletRequest request){
        Integer userId = jwtService.getLoggedInUserId(request, "accessToken");
        System.out.println("deleteCartItem");
        return cartService.deleteCartItem(bookId, userId);
    }

    @DeleteMapping("/deleteAllCartItem")
    public Msg deleteAllCartItem(HttpServletRequest request){
        Integer userId = jwtService.getLoggedInUserId(request, "accessToken");
        System.out.println("deleteAllCartItem");
        return cartService.deleteAll(userId);
    }
}
