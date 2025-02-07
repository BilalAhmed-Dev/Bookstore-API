package com.backend.test.bookstore.controller;

import com.backend.test.bookstore.entity.CartItem;
import com.backend.test.bookstore.service.CartService;
import com.backend.test.bookstore.utils.msgutils.Msg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cart") // Base path for all cart-related operations
public class CartController {
    @Autowired
    CartService cartService;

    @PostMapping("/add")
    public Msg addCartItem(@RequestParam("bookId")int bookId){
        System.out.println("addCartItem");
        return cartService.addCartItem(bookId);
    }

    @GetMapping("/getCart")
    public List<CartItem> getCartList(){
        System.out.println("getCart");
        return cartService.getCartByUserId();
    }

    @PutMapping("/decreaseCartAmount")
    public void decreaseCartAmount(@RequestParam("bookId")int bookId){
        System.out.println("decreaseCartAmount");
        cartService.decreaseAmount(bookId);
    }

    @DeleteMapping("/deleteCartItem")
    public void deleteCartItem(@RequestParam("bookId")int bookId){
        System.out.println("deleteCartItem");
        cartService.deleteCartItem(bookId);
    }

    @DeleteMapping("/deleteAllCartItem")
    public void deleteAllCartItem(){
        System.out.println("deleteAllCartItem");
        cartService.deleteAll();
    }
}
