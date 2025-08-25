package com.hansung.shoppingmall.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CartItemRequestDto {
    private Long userId;
    private Long itemId;
    private int quantity;
}
