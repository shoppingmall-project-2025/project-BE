package com.hansung.shoppingmall.dto;

import com.hansung.shoppingmall.entity.Item;
import lombok.Getter;

@Getter
public class ItemResponseDto {

    public ItemResponseDto(Item item) {
        this.name = item.getName();
        this.id = item.getId();
        this.price = item.getPrice();
        this.thumbnailImage = item.getThumbnailImage();
        this.detailImage = item.getDetailImage();
        this.description = item.getDescription();
    }

    private final Long id;
    private final String name;
    private final int price;
    private final String thumbnailImage;
    private final String detailImage;
    private final String description;
}
