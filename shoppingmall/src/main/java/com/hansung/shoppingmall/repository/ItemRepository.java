package com.hansung.shoppingmall.repository;

import com.hansung.shoppingmall.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item,Long> {
}
