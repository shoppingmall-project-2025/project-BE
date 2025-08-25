package com.hansung.shoppingmall.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ItemRequestDto {
    private String name;
    private Integer price;
    private String thumbnailImage;
    private String detailImage;
    private String description;
}
