package com.hansung.shoppingmall.repository;

import com.hansung.shoppingmall.entity.Cart;
import com.hansung.shoppingmall.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CartRepository extends JpaRepository<Cart,Long> {
    Optional<Cart> findByUser_Id(Long userId);

    Long user(User user);
}
