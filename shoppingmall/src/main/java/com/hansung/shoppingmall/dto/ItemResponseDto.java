package com.hansung.shoppingmall.dto;

import com.hansung.shoppingmall.entity.Item;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ItemResponseDto {

    public ItemResponseDto(Item item) {
        this.name = item.getName();
        this.id = item.getId();
        this.price = item.getPrice();
        this.thumbnailImage = item.getThumbnailImageUrl();
        this.detailImage = item.getDetailImageUrl();
        this.description = item.getDescription();
    }

    private Long id;
    private String name;
    private int price;
    private String thumbnailImage;
    private String detailImage;
    private String description;
}
