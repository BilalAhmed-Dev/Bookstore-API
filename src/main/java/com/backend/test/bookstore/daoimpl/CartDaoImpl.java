package com.backend.test.bookstore.daoimpl;

import com.backend.test.bookstore.dao.CartDao;
import com.backend.test.bookstore.entity.CartItem;
import com.backend.test.bookstore.repository.CartItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CartDaoImpl implements CartDao {

    private final CartItemRepository cartItemRepository;

    public CartDaoImpl(CartItemRepository cartItemRepository) {
        this.cartItemRepository = cartItemRepository;
    }

    @Override
    public CartItem getCartItemById(Integer cartItemId) {
        return cartItemRepository.getById(cartItemId);
    }

    @Override
    public List<CartItem> getCartItemsByUserId(String userId ) {
        return cartItemRepository.getByUserId(userId);
    }

    @Override
    public CartItem getCartItemByUserIdAndBookId(String userId , Integer bookId) {
        return cartItemRepository.getCartItemByUserIdAndBookId(userId,bookId);
    }

    @Override
    public void saveCartItem(CartItem cartItem) {
        cartItemRepository.save(cartItem);
    }

    @Override
    public void deleteCartItemById(Integer cartItemId) {
        cartItemRepository.deleteById(cartItemId);
    }

    @Override
    public void deleteCartByUserId(String userId ) {
        cartItemRepository.deleteByUserId(userId);
    }


}
