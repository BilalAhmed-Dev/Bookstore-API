package com.backend.test.bookstore.serviceimpl;

import com.backend.test.bookstore.dao.BookDao;
import com.backend.test.bookstore.dao.CartDao;
import com.backend.test.bookstore.entity.CartItem;
import com.backend.test.bookstore.service.CartService;
import com.backend.test.bookstore.utils.Msg;
import com.backend.test.bookstore.utils.MsgUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
public class CartServiceImpl implements CartService {

    private final CartDao cartDao;

    private final BookDao bookDao;

    public CartServiceImpl(CartDao cartDao, BookDao bookDao) {
        this.cartDao = cartDao;
        this.bookDao = bookDao;
    }

    @Override
    public Msg addCartItem(int bookId, String userId ) {

            CartItem cartItem= cartDao.getCartItemByUserIdAndBookId(userId, bookId);
            if(cartItem==null){
                CartItem newCartItem = new CartItem();
                newCartItem.setUserId(userId);
                newCartItem.setAmount(1);
                newCartItem.setBook(bookDao.findOne(bookId));
                if(bookDao.findOne(bookId).getInventory()<=0){
                    return MsgUtil.makeMsg(MsgUtil.ERROR,MsgUtil.ERROR_INVENTORY);
                }
                cartDao.saveCartItem(newCartItem);
            }
            else{
                cartItem.setAmount(cartItem.getAmount()+1);
                if(bookDao.findOne(bookId).getInventory()<=cartItem.getAmount()){
                    return MsgUtil.makeMsg(MsgUtil.ERROR,MsgUtil.ERROR_INVENTORY);
                }
                cartDao.saveCartItem(cartItem);
            }
            return MsgUtil.makeMsg(MsgUtil.SUCCESS,MsgUtil.SUCCESS_MSG);

    }

    @Override
    public Msg decreaseAmount(int bookId, String userId ) {

            CartItem cartItem= cartDao.getCartItemByUserIdAndBookId(userId,bookId);
            if(cartItem!=null&&cartItem.getAmount()>1){
                cartItem.setAmount(cartItem.getAmount()-1);
                cartDao.saveCartItem(cartItem);
                return MsgUtil.makeMsg(MsgUtil.SUCCESS,MsgUtil.SUCCESS_MSG);
            }
            else{
                System.out.println("only one left");
                return MsgUtil.makeMsg(MsgUtil.ERROR,MsgUtil.ERROR_CARTDECREASE);
            }

    }

    @Override
    public Msg deleteCartItem(int bookId, String userId ) {

            CartItem cartItem= cartDao.getCartItemByUserIdAndBookId(userId,bookId);
            if(cartItem!=null){
                cartDao.deleteCartItemById(cartItem.getCartItemId());
            }
            return MsgUtil.makeMsg(MsgUtil.SUCCESS,MsgUtil.SUCCESS_MSG);

    }

    @Override
    @Transactional
    public Msg deleteAll(String userId ) {

            cartDao.deleteCartByUserId(userId);
            return MsgUtil.makeMsg(MsgUtil.SUCCESS,MsgUtil.SUCCESS_MSG);

    }

    @Override
    public List<CartItem> getCartByUserId(String userId ) {

            return cartDao.getCartItemsByUserId(userId);


    }
}
