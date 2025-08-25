package com.hansung.shoppingmall.entity;

import com.hansung.shoppingmall.dto.ItemRequestDto;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private int price;

    private String thumbnailImageUrl;

    private String detailImageUrl;

    private String description;

    public void update(ItemRequestDto dto) {
        this.name = dto.getName();
        this.price = dto.getPrice();
        this.thumbnailImageUrl = dto.getThumbnailImage();
        this.detailImageUrl = dto.getDetailImage();
        this.description = dto.getDescription();
    }
}