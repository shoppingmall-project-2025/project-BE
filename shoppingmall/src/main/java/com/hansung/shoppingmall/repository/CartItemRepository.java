package com.hansung.shoppingmall.repository;

import com.hansung.shoppingmall.entity.Cart;
import com.hansung.shoppingmall.entity.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CartItemRepository extends JpaRepository<CartItem,Long> {
    Optional<CartItem> findByCart_IdAndItem_Id(Long cartId, Long itemId);

    List<CartItem> findByCart_Id(Long cartId);

    void deleteByCart_Id(Long cartId);
}
