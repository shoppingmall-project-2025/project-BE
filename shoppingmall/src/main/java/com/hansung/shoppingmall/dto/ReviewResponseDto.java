package com.hansung.shoppingmall.dto;

import com.hansung.shoppingmall.entity.Review;
import lombok.Getter;

import java.time.LocalDate;

@Getter
public class ReviewResponseDto {

    public ReviewResponseDto(Review review) {
        this.id = review.getId();
        this.userId = review.getUser().getId();
        this.itemId = review.getItem().getId();
        this.rating = review.getRating();
        this.title = review.getTitle();
        this.content = review.getContent();
        this.createdAt = review.getCreatedAt();
    }

    private Long id;
    private Long userId;
    private Long itemId;
    private int rating;
    private String title;
    private String content;
    private LocalDate createdAt;
}
