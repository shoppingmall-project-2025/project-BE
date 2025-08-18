package com.hansung.shoppingmall.repository;

import com.hansung.shoppingmall.entity.Review;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;



public interface ReviewRepository extends JpaRepository<Review,Long> {
    Page<Review> findByUser_Id(Long userId, Pageable pageable);

    Page<Review> findByItem_Id(Long itemId,Pageable pageable);
}
