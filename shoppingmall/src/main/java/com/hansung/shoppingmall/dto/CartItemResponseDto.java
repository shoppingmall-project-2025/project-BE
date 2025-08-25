package com.hansung.shoppingmall.dto;

import com.hansung.shoppingmall.entity.CartItem;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CartItemResponseDto {
    private Long cartItemId; // 장바구니 상품 ID
    private String itemName;
    private int quantity;
    private int itemPrice;
    private int totalPrice;
    private String thumbnailUrl;

    // CartItem 엔티티를 DTO로 변환하는 생성자
    public CartItemResponseDto(CartItem cartItem) {
        this.cartItemId = cartItem.getId();
        this.itemName = cartItem.getItem().getName();
        this.quantity = cartItem.getQuantity();
        this.itemPrice = cartItem.getItem().getPrice();
        this.totalPrice = cartItem.getQuantity() * cartItem.getItem().getPrice();
        this.thumbnailUrl = cartItem.getItem().getThumbnailImageUrl();
    }
}

