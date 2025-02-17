package com.backend.test.bookstore.dto;

import com.backend.test.bookstore.entity.Order;
import com.backend.test.bookstore.entity.OrderItem;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.LinkedList;
import java.util.List;

@Getter
@Setter
public class GetOrderDTO {
    @Getter
    @Setter
    @AllArgsConstructor
    public static class OrderItems{
        Integer bookId;
        String bookName;
        Integer price;
        Integer bookNumber;
    }

    private Timestamp time;
    private String userId ;
    private String username;
    private List<GetOrderDTO.OrderItems> orderItemList;
    public GetOrderDTO(Order order){
        this.userId=order.getUser().getUserId();
        this.username=order.getUser().getUsername();
        this.time=order.getTime();
        orderItemList=new LinkedList<>();
        for(OrderItem orderItem:order.getOrderItem()){
            orderItemList.add(new GetOrderDTO.OrderItems(orderItem.getBook().getBookId(),orderItem.getBook().getTitle(),orderItem.getPrice(),orderItem.getBookNumber()));
        }


    }
}
