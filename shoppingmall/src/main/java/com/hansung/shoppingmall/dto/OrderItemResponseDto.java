package com.hansung.shoppingmall.dto;

import com.hansung.shoppingmall.entity.OrderItem;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class OrderItemResponseDto {

    public OrderItemResponseDto(OrderItem orderItem) {
        this.itemId = orderItem.getItem().getId();
        this.itemName = orderItem.getItem().getName();
        this.price = orderItem.getItem().getPrice();
        this.quantity = orderItem.getQuantity();
        this.subtotal=orderItem.getLineTotal();
    }
    private Long itemId;
    private String itemName;
    private int quantity;
    private int price;
    private int subtotal;
}
