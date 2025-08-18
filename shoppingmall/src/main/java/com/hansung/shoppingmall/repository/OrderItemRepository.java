package com.hansung.shoppingmall.repository;

import com.hansung.shoppingmall.entity.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItem,Long> {
    boolean existsByOrder_User_IdAndItem_Id(Long orderUserId, Long itemId);
}
