package com.hansung.shoppingmall.dto;

import com.hansung.shoppingmall.entity.Item;
import lombok.Getter;

@Getter
public class ItemResponseDto {

    public ItemResponseDto(Item item) {
        this.name = item.getName();
        this.id = item.getId();
        this.price = item.getPrice();
        this.image = item.getImage();
        this.description = item.getDescription();
    }

    private final Long id;
    private final String name;
    private final int price;
    private final String image;
    private final String description;
}
