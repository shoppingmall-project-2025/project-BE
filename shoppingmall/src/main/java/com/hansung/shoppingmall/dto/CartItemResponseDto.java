package com.hansung.shoppingmall.dto;

import com.hansung.shoppingmall.entity.CartItem;
import lombok.Getter;

@Getter
public class CartItemResponseDto {
    private final Long id;
    private final String itemName;
    private final Integer quantity;

    public CartItemResponseDto(CartItem cartItem) {
        this.id = cartItem.getId();
        this.itemName = cartItem.getItem().getName();
        this.quantity = cartItem.getQuantity();
    }
}
