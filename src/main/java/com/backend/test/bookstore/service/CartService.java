package com.backend.test.bookstore.service;
import com.backend.test.bookstore.entity.CartItem;
import com.backend.test.bookstore.utils.msgutils.Msg;

import java.util.List;
public interface CartService {
    Msg addCartItem(int bookId);
    Msg decreaseAmount(int bookId);
    Msg deleteCartItem(int bookId);
    Msg deleteAll();
    List<CartItem> getCartByUserId();
}
