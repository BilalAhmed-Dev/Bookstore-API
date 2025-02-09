package com.backend.test.bookstore.dao;
import com.backend.test.bookstore.entity.CartItem;

import java.util.List;

public interface CartDao {
    CartItem getCartItemById(Integer cartItemId);
    List<CartItem> getCartItemsByUserId(String userId );

    CartItem getCartItemByUserIdAndBookId(String userId ,Integer bookId);
    void saveCartItem(CartItem cartItem);
    void deleteCartItemById(Integer cartItemId);
    void deleteCartByUserId(String userId );

}

