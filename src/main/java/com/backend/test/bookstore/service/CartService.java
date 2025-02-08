package com.backend.test.bookstore.service;
import com.backend.test.bookstore.entity.CartItem;
import com.backend.test.bookstore.utils.Msg;

import java.util.List;
public interface CartService {
    Msg addCartItem(int bookId, Integer userId);
    Msg decreaseAmount(int bookId, Integer userId);
    Msg deleteCartItem(int bookId, Integer userId);
    Msg deleteAll(Integer userId);
    List<CartItem> getCartByUserId(Integer userId);
}
