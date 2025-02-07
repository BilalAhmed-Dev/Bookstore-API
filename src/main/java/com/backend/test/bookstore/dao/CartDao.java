package com.backend.test.bookstore.dao;
import com.backend.test.bookstore.entity.CartItem;

import java.util.List;

public interface CartDao {
    CartItem getCartItemById(Integer cartItemId);
    List<CartItem> getCartItemsByUserId(Integer userId);

    CartItem getCartItemByUserIdAndBookId(Integer userId,Integer bookId);
    void saveCartItem(CartItem cartItem);
    void deleteCartItemById(Integer cartItemId);
    void deleteCartByUserId(Integer userId);

}

