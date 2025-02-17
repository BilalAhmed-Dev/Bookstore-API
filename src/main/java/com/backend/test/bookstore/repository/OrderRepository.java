package com.backend.test.bookstore.repository;
import com.backend.test.bookstore.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
public interface OrderRepository extends JpaRepository<Order,Integer> {
    @Query(value = "from Order where user.userId = :userId ")
    List<Order> getOrderByUserId(@Param("userId") String userId );
}
