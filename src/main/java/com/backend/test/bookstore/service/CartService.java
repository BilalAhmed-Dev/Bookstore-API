package com.backend.test.bookstore.service;
import com.backend.test.bookstore.entity.CartItem;
import com.backend.test.bookstore.utils.Msg;

import java.util.List;
public interface CartService {
    Msg addCartItem(int bookId, String userId );
    Msg decreaseAmount(int bookId, String userId );
    Msg deleteCartItem(int bookId, String userId );
    Msg deleteAll(String userId );
    List<CartItem> getCartByUserId(String userId );
}
