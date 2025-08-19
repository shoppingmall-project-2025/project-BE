package com.hansung.shoppingmall.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ItemRequestDto {
    @NotBlank(message = "상품명은 필수 입니다")
    private String name;

    @NotNull(message="상품 가격은 필수 입니다")
    private Integer price;

    private String thumbnailImage;
    private String detailImage;
    private String description;
}
