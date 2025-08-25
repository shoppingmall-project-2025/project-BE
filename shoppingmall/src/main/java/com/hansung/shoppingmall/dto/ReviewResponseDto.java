package com.hansung.shoppingmall.dto;

import com.hansung.shoppingmall.entity.Review;
import lombok.Getter;

import java.time.LocalDate;

@Getter
public class ReviewResponseDto {
    private Long id;
    private Long userId;
    private Long itemId;
    private int rating;
    private String title;
    private String content;
    private LocalDate createdAt;
}
