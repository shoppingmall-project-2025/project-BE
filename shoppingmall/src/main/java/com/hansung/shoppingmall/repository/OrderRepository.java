package com.hansung.shoppingmall.repository;

import com.hansung.shoppingmall.entity.Order;
import com.hansung.shoppingmall.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order,Long> {
    List<Order> findByUser_Id(Long userId);

    List<Order> user(User user);

    Optional<Order> findByIdAndUser_Id(Long orderId, Long userId);
}
