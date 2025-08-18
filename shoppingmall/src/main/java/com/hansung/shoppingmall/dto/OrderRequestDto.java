package com.hansung.shoppingmall.dto;

import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class OrderRequestDto {

    @Positive(message = "수량은 최소 1개 필요")
    private int quantity;
}
