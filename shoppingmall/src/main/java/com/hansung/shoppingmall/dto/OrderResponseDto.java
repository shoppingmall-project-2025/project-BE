package com.hansung.shoppingmall.dto;

import com.hansung.shoppingmall.entity.Order;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class OrderResponseDto {

    private Long id;
    private String orderNumber;
    private Integer totalAmount;
    private LocalDateTime orderTime;
    private List<OrderItemResponseDto> items;

    public OrderResponseDto(Order order) {
        this.id = order.getId();
        this.orderNumber = order.getOrderNumber();
        this.totalAmount = order.getAmount();
        this.orderTime = order.getOrderDate();
        this.items = order.getOrderItems().stream()
                .map(OrderItemResponseDto::new)
                .toList(); // 필요시 아이템 매핑으로 교체
    }
}
