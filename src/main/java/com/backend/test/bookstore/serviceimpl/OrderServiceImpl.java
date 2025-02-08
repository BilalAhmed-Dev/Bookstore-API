package com.backend.test.bookstore.serviceimpl;

import com.backend.test.bookstore.dao.BookDao;
import com.backend.test.bookstore.dao.CartDao;
import com.backend.test.bookstore.dao.OrderDao;
import com.backend.test.bookstore.dao.UserDao;
import com.backend.test.bookstore.dto.GetOrderDTO;
import com.backend.test.bookstore.dto.NewOrderDTO;
import com.backend.test.bookstore.entity.Book;
import com.backend.test.bookstore.entity.Order;
import com.backend.test.bookstore.entity.OrderItem;
import com.backend.test.bookstore.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.LinkedList;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderDao orderDao;

    @Autowired
    private CartDao cartDao;

    @Autowired
    private BookDao bookDao;
    @Autowired
    private UserDao userDao;

    @Override
    public List<GetOrderDTO> getOrder(Integer userId) {
            List<Order> orderList;
            orderList=orderDao.getOrderByUserId(userId);
          
            List<GetOrderDTO> getOrderDTOList=new LinkedList<>();
            for(Order order:orderList){
                getOrderDTOList.add(new GetOrderDTO(order));
            }
            return getOrderDTOList;
       
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public Order addOrder(NewOrderDTO newOrderDTO, Integer userId) {

            Order newOrder = new Order();
            Timestamp orderTime = new Timestamp(System.currentTimeMillis());
            newOrder.setTime(orderTime);
            newOrder.setUser(userDao.getUserById(userId));
            List<OrderItem> orderItemList = new LinkedList<>();
            for (NewOrderDTO.OrderItem orderItem: newOrderDTO.getOrderItemList()) {
                OrderItem newOrderItem = new OrderItem();
                Book book = bookDao.findOne(orderItem.getBookId());
                int newInventory=book.getInventory()-orderItem.getBookNumber();
                if(newInventory < 0) continue;
                book.setInventory(newInventory);
                newOrderItem.setBook(book);
                newOrderItem.setBookNumber(orderItem.getBookNumber());
                newOrderItem.setPrice(book.getPrice());
                newOrderItem.setOrder(newOrder);
                orderItemList.add(newOrderItem);
                bookDao.saveBook(book);
            }
            newOrder.setOrderItem(orderItemList);
            cartDao.deleteCartByUserId(userId);
            if(orderItemList.isEmpty()) return newOrder;
            return orderDao.saveOrder(newOrder);
       
    }


}