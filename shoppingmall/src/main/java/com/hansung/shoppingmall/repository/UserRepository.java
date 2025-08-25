package com.hansung.shoppingmall.repository;
import com.hansung.shoppingmall.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long>{
    User findByUserId(String userId);
}
