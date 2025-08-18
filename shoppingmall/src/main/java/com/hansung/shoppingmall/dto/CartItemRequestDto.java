package com.hansung.shoppingmall.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CartItemRequestDto {
    @NotNull(message="userId는 필수입니다")
    private Long userId;

    @NotNull(message = "itemId는 필수입니다")
    private Long itemId;

    @Positive(message = "수량은 한개 이상이여야합니다")
    private int quantity;
}
